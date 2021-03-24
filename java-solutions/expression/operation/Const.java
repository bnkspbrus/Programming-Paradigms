package expression.operation;

import expression.TripleExpression;

public class Const<T> implements TripleExpression<T> {

    final T value;

    public Const(T value) {
        this.value = value;
    }

    public T evaluate(T x, T y, T z) {
        return value;
    }
}
