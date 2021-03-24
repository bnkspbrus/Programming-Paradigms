package expression.operation;

import expression.TripleExpression;
import expression.type.TypeOperations;

public abstract class AbstractUnary<T> implements TripleExpression<T> {
    TypeOperations<T> type;
    TripleExpression<T> unaryD;

    AbstractUnary(TripleExpression<T> unary, TypeOperations<T> type) {
        this.unaryD = unary;
        this.type = type;
    }

    public T evaluate(T x, T y, T z) {
        T unary = unaryD.evaluate(x, y, z);
        return evaluateImpl(unary);
    }

    protected abstract T evaluateImpl(T unary);
}

