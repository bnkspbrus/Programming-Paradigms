package expression.type;

public abstract class AbstractNonDivisibleType<T> implements TypeOperations<T> {

    private boolean nullArgs(T left, T right) {
        return left == null || right == null;
    }

    @Override
    public T add(T left, T right) {
        if (nullArgs(left, right)) {
            return null;
        }
        return addImpl(left, right);
    }

    protected abstract T addImpl(T left, T right);

    @Override
    public T divide(T left, T right) {
        if (nullArgs(left, right)) {
            return null;
        }
        return divideImpl(left, right);
    }

    protected abstract T divideImpl(T left, T right);

    @Override
    public T subtract(T left, T right) {
        if (nullArgs(left, right)) {
            return null;
        }
        return subtractImpl(left, right);
    }

    protected abstract T subtractImpl(T left, T right);

    @Override
    public T negate(T left) {
        if (left == null) {
            return null;
        }
        return negateImpl(left);
    }

    protected abstract T negateImpl(T left);

    @Override
    public T multiply(T left, T right) {
        if (nullArgs(left, right)) {
            return null;
        }
        return multiplyImpl(left, right);
    }

    protected abstract T multiplyImpl(T left, T right);
}
