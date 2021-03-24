package expression.operation;

import expression.TripleExpression;
import expression.type.TypeOperations;

public class Negate<T> extends AbstractUnary<T> {

    public Negate(TripleExpression<T> unary, TypeOperations<T> type) {
        super(unary, type);
    }

    protected T evaluateImpl(T unary) {
        return type.negate(unary);
    }
}
