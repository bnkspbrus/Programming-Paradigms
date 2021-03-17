package expression.operation;

import expression.TripleExpression;
import expression.exceptions.ParsingException;
import expression.type.TypeOperations;

public class Divide<T> extends AbstractBinary<T> {

    public Divide(TripleExpression<T> left, TripleExpression<T> right, TypeOperations<T> type) {
        super(left, right, type);
    }

    @Override
    protected T evaluateImpl(T left, T right) throws ParsingException {
        return type.divide(left, right);
    }
}
