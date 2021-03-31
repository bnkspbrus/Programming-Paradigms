package expression.operation;

import expression.TripleExpressionGeneric;
import expression.exceptions.OverflowException;
import expression.type.TypeOperations;

public class Divide<T> extends AbstractBinary<T> {

    public Divide(TripleExpressionGeneric<T> left, TripleExpressionGeneric<T> right) {
        super(left, right);
    }

    @Override
    protected T evaluateImpl(T left, T right, TypeOperations<T> type) throws OverflowException {
        return type.divide(left, right);
    }
}
