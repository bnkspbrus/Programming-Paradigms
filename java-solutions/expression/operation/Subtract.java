package expression.operation;

import expression.TripleExpressionGeneric;
import expression.exceptions.OverflowException;
import expression.type.TypeOperations;

public class Subtract<T> extends AbstractBinary<T> {

    public Subtract(TripleExpressionGeneric<T> left, TripleExpressionGeneric<T> right) {
        super(left, right);
    }

    @Override
    protected T evaluateImpl(T left, T right, TypeOperations<T> type) throws OverflowException {
        return type.subtract(left, right);
    }
}
