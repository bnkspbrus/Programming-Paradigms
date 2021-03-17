package expression.operation;

import expression.TripleExpression;
import expression.exceptions.ParsingException;

public class Variable<T> implements TripleExpression<T> {

    final String name;

    public Variable(String name) {
        this.name = name;
    }

    public T evaluate(T x, T y, T z) throws ParsingException {
        switch (name) {
            case ("x"):
                return x;
            case ("y"):
                return y;
            default:
                return z;
        }
    }
}
