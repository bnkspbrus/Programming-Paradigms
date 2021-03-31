package expression.operation;

import expression.TripleExpressionGeneric;
import expression.exceptions.OverflowException;
import expression.type.TypeOperations;

public class Multiply<T> extends AbstractBinary<T> {

    public Multiply(TripleExpressionGeneric<T> left, TripleExpressionGeneric<T> right) {
        super(left, right);
    }

    @Override
    protected T evaluateImpl(T left, T right, TypeOperations<T> type) throws OverflowException {
        return type.multiply(left, right);
    }
}
