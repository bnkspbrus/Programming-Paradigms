package expression.operation;

import expression.TripleExpressionGeneric;
import expression.type.TypeOperations;

public class Multiply<T> extends AbstractBinary<T> {

    public Multiply(TripleExpressionGeneric<T> left, TripleExpressionGeneric<T> right, TypeOperations<T> type) {
        super(left, right, type);
    }

    @Override
    protected T evaluateImpl(T left, T right) {
        return type.multiply(left, right);
    }
}
