package expression.type;

import expression.exceptions.OverflowException;

public interface TypeOperations<T> {
    T add(T left, T right) throws OverflowException;

    T divide(T left, T right) throws OverflowException;

    T subtract(T left, T right) throws OverflowException;

    T negate(T left) throws OverflowException;

    T multiply(T left, T right) throws OverflowException;

    T valueOf(String s) throws OverflowException;
}
