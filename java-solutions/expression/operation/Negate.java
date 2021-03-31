package expression.operation;

import expression.TripleExpressionGeneric;
import expression.exceptions.OverflowException;
import expression.type.TypeOperations;

public class Negate<T> extends AbstractUnary<T> {

    public Negate(TripleExpressionGeneric<T> unary) {
        super(unary);
    }

    protected T evaluateImpl(T unary, TypeOperations<T> type) throws OverflowException {
        return type.negate(unary);
    }
}


