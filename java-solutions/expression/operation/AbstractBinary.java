package expression.operation;

import expression.TripleExpression;
import expression.type.TypeOperations;

public abstract class AbstractBinary<T> implements TripleExpression<T> {
    TripleExpression<T> leftD, rightD;
    TypeOperations<T> type;

    public AbstractBinary(TripleExpression<T> left, TripleExpression<T> right, TypeOperations<T> type) {
        leftD = left;
        rightD = right;
        this.type = type;
    }

    public T evaluate(T x, T y, T z) {
        T left = leftD.evaluate(x, y, z);
        T right = rightD.evaluate(x, y, z);
        return evaluateImpl(left, right);
    }

    protected abstract T evaluateImpl(T left, T right);
}
