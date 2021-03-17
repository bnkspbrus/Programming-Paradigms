package expression.operation;

import expression.TripleExpression;
import expression.exceptions.EvaluatingException;
import expression.exceptions.ParsingException;
import expression.type.TypeOperations;

public class Negate<T> extends AbstractUnary<T> {

    public Negate(TripleExpression<T> unary, TypeOperations<T> type) {
        super(unary, type);
    }

    protected T evaluateImpl(T unary) throws ParsingException, EvaluatingException {
        return type.negate(unary);
    }
}
