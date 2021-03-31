package expression.type;

import expression.exceptions.OverflowException;

public class IntegerType implements TypeOperations<Integer> {

    private final boolean checkMode;

    public IntegerType(boolean checkMode) {
        this.checkMode = checkMode;
    }

    @Override
    public Integer add(Integer left, Integer right) throws OverflowException {
        if (checkMode) {
            if (left >= 0 && right >= 0 && left + right < 0 || left < 0 && right < 0 && left + right >= 0) {
                throw new OverflowException(left + " + " + right + " -- overflow\n");
            }
        }
        return left + right;

    }

    @Override
    public Integer divide(Integer left, Integer right) throws OverflowException {
        if (checkMode) {
            if (left == Integer.MIN_VALUE && right == -1) {
                throw new OverflowException(left + " / " + right + " -- overflow\n");
            }
        }
        if (right == 0) {
            throw new OverflowException("division by zero\n");
        }

        return left / right;
    }

    @Override
    public Integer subtract(Integer left, Integer right) throws OverflowException {
        if (checkMode) {
            if (left >= 0 && right < 0 && left - Integer.MAX_VALUE > right || left <= 0 && right > 0 && Integer.MIN_VALUE - left > -right) {
                throw new OverflowException(left + " - " + right + " -- overflow\n");
            }
        }
        return left - right;

    }

    @Override
    public Integer negate(Integer left) throws OverflowException {
        if (checkMode) {
            if (left == Integer.MIN_VALUE) {
                throw new OverflowException("-" + left + " -- overflow\n");
            }
        }
        return -left;
    }

    @Override
    public Integer multiply(Integer left, Integer right) throws OverflowException {
        if (checkMode) {
            if (left >= 1 && right >= 1 && Integer.MAX_VALUE / left < right || left < 0 && right < 0 && Integer.MAX_VALUE / left > right || left < -1 && right >= 0 && Integer.MIN_VALUE / left < right || left >= 0 && right < -1 && Integer.MIN_VALUE / right < left) {
                throw new OverflowException(left + " * " + right + " -- overflow\n");
            }
        }
        return left * right;
    }

    @Override
    public Integer valueOf(String s) throws OverflowException {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new OverflowException(s + " -- overflow\n");
        }
    }
}
