package expression.operation;

import expression.TripleExpressionGeneric;
import expression.type.TypeOperations;

public class Const<T> implements TripleExpressionGeneric<T> {

    final T value;

    public Const(T value) {
        this.value = value;
    }

    public T evaluate(T x, T y, T z, TypeOperations<T> type) {
        return value;
    }
}
