package expression.operation;

import expression.TripleExpressionGeneric;
import expression.type.TypeOperations;

public abstract class AbstractUnary<T> implements TripleExpressionGeneric<T> {
    TypeOperations<T> type;
    TripleExpressionGeneric<T> unaryD;

    AbstractUnary(TripleExpressionGeneric<T> unary, TypeOperations<T> type) {
        this.unaryD = unary;
        this.type = type;
    }

    public T evaluate(T x, T y, T z) {
        T unary = unaryD.evaluate(x, y, z);
        return evaluateImpl(unary);
    }

    protected abstract T evaluateImpl(T unary);
}

