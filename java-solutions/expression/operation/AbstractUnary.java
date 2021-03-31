package expression.operation;

import expression.TripleExpressionGeneric;
import expression.exceptions.OverflowException;
import expression.type.TypeOperations;

public abstract class AbstractUnary<T> implements TripleExpressionGeneric<T> {
    protected TripleExpressionGeneric<T> unaryD;

    AbstractUnary(TripleExpressionGeneric<T> unary) {
        this.unaryD = unary;
    }

    public T evaluate(T x, T y, T z, TypeOperations<T> type) throws OverflowException {
        T unary = unaryD.evaluate(x, y, z, type);
        return evaluateImpl(unary, type);
    }

    protected abstract T evaluateImpl(T unary, TypeOperations<T> type) throws OverflowException;
}

