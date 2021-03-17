package expression.type;

import expression.exceptions.EvaluatingException;
import expression.exceptions.ParsingException;

public class ByteType implements TypeOperations<Byte> {

    @Override
    public Byte add(Byte left, Byte right) {
        return (byte) (left + right);
    }

    @Override
    public Byte divide(Byte left, Byte right) {
        if (right == 0) {
            return null;
        }
        return (byte) (left / right);
    }

    @Override
    public Byte subtract(Byte left, Byte right) {
        return (byte) (left - right);
    }

    @Override
    public Byte negate(Byte left) {
        return (byte) -left;
    }

    @Override
    public Byte multiply(Byte left, Byte right) {
        return (byte) (left * right);
    }

    @Override
    public Byte valueOf(String s) {
        return (byte) Integer.parseInt(s);
    }
}
