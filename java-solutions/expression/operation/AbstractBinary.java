package expression.operation;

import expression.TripleExpressionGeneric;
import expression.exceptions.OverflowException;
import expression.type.TypeOperations;

public abstract class AbstractBinary<T> implements TripleExpressionGeneric<T> {
    protected TripleExpressionGeneric<T> leftD, rightD;

    public AbstractBinary(TripleExpressionGeneric<T> left, TripleExpressionGeneric<T> right) {
        leftD = left;
        rightD = right;
    }

    public T evaluate(T x, T y, T z, TypeOperations<T> type) throws OverflowException {
        T left = leftD.evaluate(x, y, z, type);
        T right = rightD.evaluate(x, y, z, type);
        return evaluateImpl(left, right, type);
    }

    protected abstract T evaluateImpl(T left, T right, TypeOperations<T> type) throws OverflowException;
}
