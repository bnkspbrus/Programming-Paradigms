package expression.type;

public class ByteType extends AbstractNonDivisibleType<Byte> {

    @Override
    public Byte addImpl(Byte left, Byte right) {
        return (byte) (left + right);
    }

    @Override
    public Byte divideImpl(Byte left, Byte right) {
        if (right == 0) {
            return null;
        }
        return (byte) (left / right);
    }

    @Override
    public Byte subtractImpl(Byte left, Byte right) {
        return (byte) (left - right);
    }

    @Override
    public Byte negateImpl(Byte left) {
        return (byte) -left;
    }

    @Override
    public Byte multiplyImpl(Byte left, Byte right) {
        return (byte) (left * right);
    }

    @Override
    public Byte valueOf(String s) {
        return (byte) Integer.parseInt(s);
    }
}
