package expression.operation;

import expression.TripleExpressionGeneric;
import expression.type.TypeOperations;

public class Subtract<T> extends AbstractBinary<T> {

    public Subtract(TripleExpressionGeneric<T> left, TripleExpressionGeneric<T> right, TypeOperations<T> type) {
        super(left, right, type);
    }

    @Override
    protected T evaluateImpl(T left, T right) {
        return type.subtract(left, right);
    }
}
