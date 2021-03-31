"use strict"

function Const(value) {
    this.value = value;
}

Const.prototype = {
    evaluate: function () {
        return this.value;
    },
    toString: function () {
        return this.value.toString();
    }
}

let SWITCH = {
    x: 0,
    y: 1,
    z: 2
}

function Variable(name) {
    this.name = name;
}

Variable.prototype = {
    evaluate: function () {
        return arguments[SWITCH[this.name]];
    },
    toString: function () {
        return this.name.toString();
    }
}

function Binary(left, right) {
    this.left = left;
    this.right = right;
}

Binary.prototype = {
    evaluate: function (...args) {
        return this.func(this.left.evaluate(...args), this.right.evaluate(...args));
    },
    toString: function () {
        return this.left.toString() + " " + this.right.toString() + " " + this.sign;
    }
}

function Add(left, right) {
    // Binary.call(this, left, right);
    // this.sign = "+";
    // this.func = (a, b) => a + b;
    initBin.call(this, left, right, "+", (a, b) => a + b);
}

Add.prototype = Object.create(Binary.prototype);

function Subtract(left, right) {
    // Binary.call(this, left, right);
    // this.sign = "-";
    // this.func = (a, b) => a - b;
    initBin.call(this, left, right, "-", (a, b) => a - b);
}

Subtract.prototype = Object.create(Binary.prototype);

function Multiply(left, right) {
    // Binary.call(this, left, right);
    // this.sign = "*";
    // this.func = (a, b) => a * b;
    initBin.call(this, left, right, "*", (a, b) => a * b);
}

Multiply.prototype = Object.create(Binary.prototype);

function Divide(left, right) {
    // Binary.call(this, left, right);
    // this.sign = "/";
    // this.func = (a, b) => a / b;
    initBin.call(this, left, right, "/", (a, b) => a / b);
}

Divide.prototype = Object.create(Binary.prototype);

function Unary(left) {
    this.left = left;
}

Unary.prototype = {
    evaluate: function (...args) {
        return this.func(this.left.evaluate(...args));
    },
    toString: function () {
        return this.left.toString() + " " + this.sign;
    }
}

function Negate(left) {
    // Unary.call(this, left);
    // this.sign = "negate";
    // this.func = a => -a;
    initUn.call(this, left, "negate", a => -a);
}

Negate.prototype = Object.create(Unary.prototype);

function ArcTan(left) {
    // Unary.call(this, left);
    // this.sign = "atan";
    // this.func = a => Math.atan(a);
    initUn.call(this, left, "atan", a => Math.atan(a));
}

ArcTan.prototype = Object.create(Unary.prototype);

function ArcTan2(left, right) {
    // Binary.call(this, left, right);
    // this.sign = "atan2";
    // this.func = (a, b) => Math.atan2(a, b);
    initBin.call(this, left, right, "atan2", (a, b) => Math.atan2(a, b));
}

ArcTan2.prototype = Object.create(Binary.prototype);

function initBin(left, right, sign, func) {
    Binary.call(this, left, right);
    this.sign = sign;
    this.func = func;
}

function initUn(left, sign, func) {
    Unary.call(this, left);
    this.sign = sign;
    this.func = func;
}

