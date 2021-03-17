package expression.type;

import expression.exceptions.EvaluatingException;
import expression.exceptions.ParsingException;

public interface TypeOperations<T> {
    T add(T left, T right) throws ParsingException, EvaluatingException;

    T divide(T left, T right) throws ParsingException;

    T subtract(T left, T right) throws ParsingException, EvaluatingException;

    T negate(T left) throws ParsingException, EvaluatingException;

    T multiply(T left, T right) throws ParsingException, EvaluatingException;

    T valueOf(String s) throws ParsingException;
}
