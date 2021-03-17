package expression.type;

import expression.exceptions.ParsingException;

import java.math.BigInteger;

public class BigIntegerType implements TypeOperations<BigInteger> {
    @Override
    public BigInteger add(BigInteger left, BigInteger right) {
        return left.add(right);
    }

    @Override
    public BigInteger divide(BigInteger left, BigInteger right) {
        if (right.compareTo(BigInteger.valueOf(0)) == 0) {
            return null;
        }
        return left.divide(right);
    }

    @Override
    public BigInteger subtract(BigInteger left, BigInteger right) {
        return left.subtract(right);
    }

    @Override
    public BigInteger negate(BigInteger left) {
        return left.negate();
    }

    @Override
    public BigInteger multiply(BigInteger left, BigInteger right) {
        return left.multiply(right);
    }

    @Override
    public BigInteger valueOf(String s) {
        return new BigInteger(s);
    }
}
