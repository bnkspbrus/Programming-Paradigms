package expression.operation;

import expression.TripleExpressionGeneric;
import expression.type.TypeOperations;

public class Add<T> extends AbstractBinary<T> {

    public Add(TripleExpressionGeneric<T> left, TripleExpressionGeneric<T> right, TypeOperations<T> type) {
        super(left, right, type);
    }

    @Override
    protected T evaluateImpl(T left, T right) {
        return type.add(left, right);
    }
}
