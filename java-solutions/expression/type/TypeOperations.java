package expression.type;

import expression.exceptions.EvaluatingException;
import expression.exceptions.ParsingException;

public interface TypeOperations<T> {
    T add(T left, T right);

    T divide(T left, T right);

    T subtract(T left, T right);

    T negate(T left);

    T multiply(T left, T right);

    T valueOf(String s);
}
