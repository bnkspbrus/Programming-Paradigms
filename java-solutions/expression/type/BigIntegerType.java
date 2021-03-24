package expression.type;

import java.math.BigInteger;

public class BigIntegerType extends AbstractNonDivisibleType<BigInteger> {

    @Override
    public BigInteger valueOf(String s) {
        return new BigInteger(s);
    }

    @Override
    public BigInteger addImpl(BigInteger left, BigInteger right) {
        return left.add(right);
    }

    @Override
    public BigInteger divideImpl(BigInteger left, BigInteger right) {
        if (right.equals(BigInteger.ZERO)) {
            return null;
        }
        return left.divide(right);
    }

    @Override
    public BigInteger subtractImpl(BigInteger left, BigInteger right) {
        return left.subtract(right);
    }

    @Override
    public BigInteger negateImpl(BigInteger left) {
        return left.negate();
    }

    @Override
    public BigInteger multiplyImpl(BigInteger left, BigInteger right) {
        return left.multiply(right);
    }
}
