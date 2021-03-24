package expression.operation;

import expression.TripleExpression;
import expression.type.TypeOperations;

public class Add<T> extends AbstractBinary<T> {

    public Add(TripleExpression<T> left, TripleExpression<T> right, TypeOperations<T> type) {
        super(left, right, type);
    }

    @Override
    protected T evaluateImpl(T left, T right) {
        return type.add(left, right);
    }
}
