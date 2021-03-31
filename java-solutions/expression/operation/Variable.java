package expression.operation;

import expression.TripleExpressionGeneric;
import expression.type.TypeOperations;

public class Variable<T> implements TripleExpressionGeneric<T> {

    final String name;

    public Variable(String name) {
        this.name = name;
    }

    public T evaluate(T x, T y, T z, TypeOperations<T> type) {
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
