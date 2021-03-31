package expression;

import expression.exceptions.OverflowException;
import expression.type.TypeOperations;

public interface TripleExpressionGeneric<T> {
    T evaluate(T x, T y, T z, TypeOperations<T> type) throws OverflowException;
}
