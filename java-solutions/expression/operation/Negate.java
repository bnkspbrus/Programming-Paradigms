package expression.operation;

import expression.TripleExpressionGeneric;
import expression.type.TypeOperations;

public class Negate<T> extends AbstractUnary<T> {

    public Negate(TripleExpressionGeneric<T> unary, TypeOperations<T> type) {
        super(unary, type);
    }

    protected T evaluateImpl(T unary) {
        return type.negate(unary);
    }
}
