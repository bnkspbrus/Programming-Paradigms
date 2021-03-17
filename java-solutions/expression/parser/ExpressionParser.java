package expression.parser;

import expression.*;
import expression.exceptions.ParsingException;
import expression.operation.*;
import expression.type.TypeOperations;

import java.util.*;

public class ExpressionParser<T> implements Parser<T> {

    private String expression;

    private final TypeOperations<T> type;

    public ExpressionParser(TypeOperations<T> type) {
        this.type = type;
    }

    public TripleExpression<T> parse(String expression) throws ParsingException {
        pos = 0;
        name = "begin_line";
        this.expression = expression;
        TripleExpression<T> res = thirdPriority();
        if (!name.equals("end_line")) {
            throw new ParsingException("No opening parenthesis\n");
        }
        return res;
    }


    private TripleExpression<T> firstPriority() throws ParsingException {
        TripleExpression<T> res;
        String prevName = name;
        nextName();
        switch (name) {
            case "(":
                res = thirdPriority(); // last priority
                if (!name.equals(")"))
                    throw new ParsingException("No closing parenthesis\n");
                nextName();
                break;
            case "-":
                negate = false;
                res = new Negate<>(firstPriority(), type);
                break;
            case "x":
            case "y":
            case "z":
                res = new Variable<>(name);
                nextName();
                break;
            default:
                if (isNumber(name)) {
                    res = new Const<>(type.valueOf(name));
                    nextName();
                } else {
                    throw new ParsingException("No " + getPosition(prevName) + " argument\n");
                }
        }
        return res;
    }

    private String getPosition(String prevName) {
        if (prevName.equals("begin_line") || prevName.equals("(")) {
            return "first";
        }
        if (name.equals("end_line") || name.equals(")")) {
            return "last";
        }
        return "middle";
    }

    private TripleExpression<T> secondPriority() throws ParsingException {
        TripleExpression<T> left = firstPriority();
        while (true) {
            switch (name) {
                case "*":
                    left = new Multiply<>(left, firstPriority(), type);
                    break;
                case "/":
                    left = new Divide<>(left, firstPriority(), type);
                    break;
                default:
                    return left;
            }
        }
    }

    private TripleExpression<T> thirdPriority() throws ParsingException {
        TripleExpression<T> left = secondPriority();
        while (true) {
            switch (name) {
                case "-":
                    if (!negate) {
                        left = new Subtract<>(left, secondPriority(), type);
                    }
                    break;
                case "+":
                    left = new Add<>(left, secondPriority(), type);
                    break;
                default:
                    return left;
            }
        }
    }

    ///////////////

    private String name;

    private int pos;

    private void scipWS() {
        while (pos < expression.length() && isWhitespace(expression.charAt(pos))) {
            pos++;
        }
    }

    private String readNumber() {
        int mark = pos++;
        while (pos < expression.length() && isDigit(expression.charAt(pos))) {
            pos++;
        }
        return expression.substring(mark, pos);
    }

    private final Set<String> names = Set.of("+", "-", "*", "/", "(", ")", "x", "y", "z");

    private final Set<String> variables = Set.of("x", "y", "z");

    boolean isVariable(String name) {
        return variables.contains(name);
    }

    private void nextName() {
        scipWS();
        if (pos == expression.length()) {
            name = "end_line";
            return;
        }
        if (expression.charAt(pos) == '-' && !isNumber(name) && !name.equals(")") && !isVariable(name)) {
            scipWS();
            pos++;
            if (pos < expression.length() && isDigit(expression.charAt(pos))) {
                name = "-" + readNumber();
                return;
            }
            negate = true;
            name = "-";
            return;
        }
        if (isDigit(expression.charAt(pos))) {
            name = readNumber();
            return;
        }
        name = readName();
    }

    boolean isNumber(String name) {
        return name.length() > 1 ? isDigit(name.charAt(1)) : isDigit(name.charAt(0));
    }

    private boolean isWhitespace(char c) {
        return c == ' ' || c == '\n' || c == '\t';
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private String readName() {
        int mark = pos++;
        while (pos < expression.length() && !names.contains(expression.substring(mark, pos))) {
            pos++;
        }
        return expression.substring(mark, pos);
    }

    private boolean negate;

//    public static void main(String[] args) throws ParsingException {
//        ExpressionParser<Integer> ex = new ExpressionParser<>(new IntegerType(false));
//        ex.parse("-(-(-\t\t-5 + 16   *x*y) + 1 * z) -(((-11)))");
//    }
}

