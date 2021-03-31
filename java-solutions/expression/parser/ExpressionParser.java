package expression.parser;

import expression.*;
import expression.exceptions.OverflowException;
import expression.exceptions.ParsingException;
import expression.operation.*;
import expression.type.TypeOperations;

import java.util.*;

public class ExpressionParser<T> implements ParserGeneric<T> {

    private String expression;

    private final TypeOperations<T> type;

    public TypeOperations<T> getType() {
        return type;
    }

    public ExpressionParser(TypeOperations<T> type) {
        this.type = type;
    }

    public TripleExpressionGeneric<T> parse(String expression) throws ParsingException, OverflowException {
        pos = 0;
        name = "begin_line";
        this.expression = expression;
        TripleExpressionGeneric<T> res = thirdPriority();
        if (!name.equals("end_line")) {
            throw new ParsingException("No opening parenthesis\n");
        }
        return res;
    }


    private TripleExpressionGeneric<T> firstPriority() throws ParsingException, OverflowException {
        TripleExpressionGeneric<T> res;
        String prevName = name;
        nextName();
        switch (name) {
            case "(":
                res = thirdPriority(); // last priority
                if (!name.equals(")")) throw new ParsingException("No closing parenthesis\n");
                nextName();
                break;
            case "-":
//                if (negate) {
                negate = false;
                res = new Negate<>(firstPriority());
                break;
//                }
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

    private TripleExpressionGeneric<T> secondPriority() throws ParsingException, OverflowException {
        TripleExpressionGeneric<T> left = firstPriority();
        while (true) {
            switch (name) {
                case "*":
                    left = new Multiply<>(left, firstPriority());
                    break;
                case "/":
                    left = new Divide<>(left, firstPriority());
                    break;
                default:
                    return left;
            }
        }
    }

    private TripleExpressionGeneric<T> thirdPriority() throws ParsingException, OverflowException {
        TripleExpressionGeneric<T> left = secondPriority();
        while (true) {
            switch (name) {
                case "-":
                    if (!negate) {
                        left = new Subtract<>(left, secondPriority());
                        break;
                    }
                case "+":
                    left = new Add<>(left, secondPriority());
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
        while (pos < expression.length() && Character.isWhitespace(expression.charAt(pos))) {
            pos++;
        }
    }

    private String readNumber() {
        int mark = pos++;
        while (pos < expression.length() && Character.isDigit(expression.charAt(pos))) {
            pos++;
        }
        return expression.substring(mark, pos);
    }

    private final Set<String> NAMES = Set.of("+", "-", "*", "/", "(", ")", "x", "y", "z");

    private final Set<String> VARIABLES = Set.of("x", "y", "z");

    boolean isVariable(String name) {
        return VARIABLES.contains(name);
    }

    private void nextName() {
        scipWS();
        if (pos == expression.length()) {
            name = "end_line";
            return;
        }
        if (expression.charAt(pos) == '-' && !isNumber(name) && !name.equals(")") && !isVariable(name)) {
            pos++;
            scipWS();
            if (pos < expression.length() && Character.isDigit(expression.charAt(pos))) {
                name = "-" + readNumber();
                return;
            }
            negate = true;
            name = "-";
            return;
        }
        if (Character.isDigit(expression.charAt(pos))) {
            name = readNumber();
            return;
        }
        name = readName();
    }

    boolean isNumber(String name) {
        return name.length() > 1 ? Character.isDigit(name.charAt(1)) : Character.isDigit(name.charAt(0));
    }

    private String readName() {
        int mark = pos++;
        while (pos < expression.length() && !NAMES.contains(expression.substring(mark, pos))) {
            pos++;
        }
        return expression.substring(mark, pos);
    }

    private boolean negate;
}

