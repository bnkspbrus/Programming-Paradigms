"use strict"

function Const(value) {
    this.value = value;
}

Const.prototype = {
    evaluate: function() {
        return this.value;
    },
    toString: function() {
        return this.value.toString();
    }
}

Const.prototype.prefix = Const.prototype.toString;


const SWITCH = new Map([["x", 0], ["y", 1], ["z", 2]]);

function Variable(name) {
    this.name = name;
}

Variable.prototype = {
    evaluate: function() {
        return arguments[SWITCH.get(this.name)];
    },
    toString: function() {
        return this.name.toString();
    }
}
Variable.prototype.prefix = Variable.prototype.toString;

function Binary(left, right) {
    this.left = left;
    this.right = right;
}

Binary.prototype = {
    evaluate: function(...args) {
        return this.func(this.left.evaluate(...args), this.right.evaluate(...args));
    },
    toString: function() {
        return this.left.toString() + " " + this.right.toString() + " " + this.sign;
    },
    prefix() {
        return "(" + this.sign + " " + this.left.prefix() + " " + this.right.prefix() + ")";
    }
}

function createPrototype(base) {
    function Result(sign, func) {
        this.func = func;
        this.sign = sign;
    }

    Result.prototype = Object.create(base.prototype);
    return Result;
}

function createOperation(base, sign, func) {
    function Result(...args) {
        base.call(this, ...args);
    }

    let Prototype = createPrototype(base);
    Result.prototype = new Prototype(sign, func);
    return Result;
}

let Add = createOperation(Binary, "+", (a, b) => a + b);

let Subtract = createOperation(Binary, "-", (a, b) => a - b);

let Multiply = createOperation(Binary, "*", (a, b) => a * b);

let Divide = createOperation(Binary, "/", (a, b) => a / b);

let ArcTan2 = createOperation(Binary, "atan2", (a, b) => Math.atan2(a, b));

function Unary(left) {
    this.left = left;
}

Unary.prototype = {
    evaluate: function(...args) {
        return this.func(this.left.evaluate(...args));
    },
    toString: function() {
        return this.left.toString() + " " + this.sign;
    },
    prefix() {
        return "(" + this.sign + " " + this.left.prefix() + ")";
    }
}

let Negate = createOperation(Unary, "negate", a => -a);

let Sinh = createOperation(Unary, "sinh", a => Math.sinh(a));

let Cosh = createOperation(Unary, "cosh", a => Math.cosh(a));

let ArcTan = createOperation(Unary, "atan", a => Math.atan(a));

const NAME = new Set(["+", "-", "*", "/", "negate", "(", ")", "x", "y", "z", "sinh", "cosh"]);

const BINARY = new Map([["+", Add], ["-", Subtract], ["*", Multiply], ["/", Divide]]);

const UNARY = new Map([["negate", Negate], ["sinh", Sinh], ["cosh", Cosh]]);


function ParsingError(message) {
    this.message = message;
}

ParsingError.prototype = Object.create(Error.prototype);
ParsingError.prototype.name = "ParsingError";
ParsingError.prototype.constructor = ParsingError;

const VARIABLE = new Set(["x", "y", "z"]);

function Tokenizer(string) {
    this.string = string;
    this.pos = 0;
    this.token = "begin_line";
}

Tokenizer.prototype = {
    isWhitespace(ch) {
        return /\s/.test(ch);
    },
    isDigit(ch) {
        return /\d/.test(ch);
    },
    scipWS() {
        while (this.pos < this.string.length && this.isWhitespace(this.string[this.pos])) {
            this.pos++;
        }
    },
    readName() {
        let mark = this.pos;
        while (this.pos < this.string.length && !this.isWhitespace(this.string[this.pos]) &&
        !NAME.has(this.string.substring(mark, this.pos))) {
            this.pos++;
        }
        return this.string.substring(mark, this.pos);
    },
    readNumber() {
        let mark = this.pos;
        while (this.pos < this.string.length && this.isDigit(this.string[this.pos])) {
            this.pos++;
        }
        if (this.pos < this.string.length && !this.isWhitespace(this.string[this.pos]) && this.string[this.pos] !== "(" && this.string[this.pos] !== ")") {
            throw new ParsingError("Unknown variable '" + this.string.substring(mark, this.pos) + "' at position " + this.pos + "\n");
        }
        return this.string.substring(mark, this.pos);
    },
    nextToken() {
        this.scipWS();
        if (this.pos === this.string.length) {
            if (this.token === "begin_line") {
                throw new ParsingError("Empty input\n");
            }
            this.token = "end_line";
            return;
        }
        if (this.string[this.pos] === "-") {
            if (this.isDigit(this.string[this.pos + 1])) {
                this.pos++;
                this.token = "-" + this.readNumber();
                return;
            }
        }
        if (this.isDigit(this.string[this.pos])) {
            this.token = this.readNumber();
            return;
        }
        let name = this.readName();
        if (!NAME.has(name)) {
            if (this.token === "(") {
                throw new ParsingError("Unknown operation '" + name + "' at position " + this.pos + "\n");
            }
            throw new ParsingError("Unknown variable '" + name + "' at position " + this.pos + "\n");
        }
        this.token = name;
    }
}

function ExpressionParser(string) {
    this.tokenizer = new Tokenizer(string);
}

ExpressionParser.prototype.parsePrefix = function() {
    let result = this.parse();
    this.tokenizer.nextToken();
    if (this.tokenizer.token !== "end_line") {
        throw new ParsingError("Excessive info '" + this.tokenizer.token + "' at position " + this.tokenizer.pos + "\n");
    }
    return result;
}

ExpressionParser.prototype.parse = function() {
    this.tokenizer.nextToken();
    if (this.tokenizer.token === "(") {
        this.tokenizer.nextToken();
        if (BINARY.has(this.tokenizer.token) || UNARY.has(this.tokenizer.token)) {
            let result;
            if (BINARY.has(this.tokenizer.token)) {
                try {
                    result = new (BINARY.get(this.tokenizer.token))(this.parse(), this.parse());
                } catch (e) {
                    throw new ParsingError("Invalid binary, expected argument at position " + this.tokenizer.pos + "\n");
                }
            } else {
                try {
                    result = new (UNARY.get(this.tokenizer.token))(this.parse());
                } catch (e) {
                    throw new ParsingError("Invalid unary, expected argument at position " + this.tokenizer.pos + "\n");
                }
            }
            this.tokenizer.nextToken();
            if (this.tokenizer.token !== ")") {
                throw new ParsingError("No closing parenthesis at position " + this.tokenizer.pos + "\n");
            }
            return result;
        } else {
            throw new ParsingError("Operator is absent at position " + this.tokenizer.pos + "\n");
        }
    } else {
        if (VARIABLE.has(this.tokenizer.token)) {
            return new Variable(this.tokenizer.token);
        } else {
            let value = Number(this.tokenizer.token);
            if (!isNaN(value)) {
                return new Const(value);
            }
            throw new ParsingError("Invalid number\n");
        }
    }
}

function parsePrefix(string) {
    let ex = new ExpressionParser(string);
    return ex.parsePrefix();
}


