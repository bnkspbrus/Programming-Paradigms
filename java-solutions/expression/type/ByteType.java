package expression.type;

import expression.exceptions.EvaluatingException;
import expression.exceptions.ParsingException;

public class ByteType implements TypeOperations<Byte> {

    @Override
    public Byte add(Byte left, Byte right) throws ParsingException, EvaluatingException {
        return (byte) (left + right);
    }

    @Override
    public Byte divide(Byte left, Byte right) throws ParsingException {
        if (right == 0) {
            return null;
        }
        return (byte) (left / right);
    }

    @Override
    public Byte subtract(Byte left, Byte right) throws ParsingException, EvaluatingException {
        return (byte) (left - right);
    }

    @Override
    public Byte negate(Byte left) throws ParsingException, EvaluatingException {
        return (byte) -left;
    }

    @Override
    public Byte multiply(Byte left, Byte right) throws ParsingException, EvaluatingException {
        return (byte) (left * right);
    }

    @Override
    public Byte valueOf(String s) throws ParsingException {
        return (byte)Integer.parseInt(s);
    }
}
