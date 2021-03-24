package expression.operation;

import expression.TripleExpressionGeneric;

public class Variable<T> implements TripleExpressionGeneric<T> {

    final String name; // name

    public Variable(String name) {
        this.name = name;
    }

    public T evaluate(T x, T y, T z) {
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
