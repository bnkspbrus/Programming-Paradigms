package expression.operation;

import expression.TripleExpression;
import expression.exceptions.EvaluatingException;
import expression.exceptions.ParsingException;
import expression.type.TypeOperations;

public class Multiply<T> extends AbstractBinary<T> {

    public Multiply(TripleExpression<T> left, TripleExpression<T> right, TypeOperations<T> type) {
        super(left, right, type);
    }

    @Override
    protected T evaluateImpl(T left, T right) throws ParsingException, EvaluatingException {
        return type.multiply(left, right);
    }
}
