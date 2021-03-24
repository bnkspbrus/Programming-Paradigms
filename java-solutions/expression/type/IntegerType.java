package expression.type;

public class IntegerType extends AbstractNonDivisibleType<Integer> {

    private final boolean checkMode;

    public IntegerType(boolean checkMode) {
        this.checkMode = checkMode;
    }

    @Override
    public Integer addImpl(Integer left, Integer right) {
        if (checkMode) {
            if (left >= 0 && right >= 0 && left + right < 0 || left < 0 && right < 0 && left + right >= 0) {
                return null;
            }
        }
        return left + right;

    }

    @Override
    public Integer divideImpl(Integer left, Integer right) {
        if (checkMode) {
            if (left == Integer.MIN_VALUE && right == -1) {
                return null;
            }
        }
        if (right == 0) {
            return null;
        }

        return left / right;
    }

    @Override
    public Integer subtractImpl(Integer left, Integer right) {
        if (checkMode) {
            if (left >= 0 && right < 0 && left - Integer.MAX_VALUE > right || left <= 0 && right > 0 && Integer.MIN_VALUE - left > -right) {
                return null;
            }
        }
        return left - right;

    }

    @Override
    public Integer negateImpl(Integer left) {
        if (checkMode) {
            if (left == Integer.MIN_VALUE) {
                return null;
            }
        }
        return -left;
    }

    @Override
    public Integer multiplyImpl(Integer left, Integer right) {
        if (checkMode) {
            if (left >= 1 && right >= 1 && Integer.MAX_VALUE / left < right
                    || left < 0 && right < 0 && Integer.MAX_VALUE / left > right
                    || left < -1 && right >= 0 && Integer.MIN_VALUE / left < right
                    || left >= 0 && right < -1 && Integer.MIN_VALUE / right < left) {
                return null;
            }
        }
        return left * right;
    }

    @Override
    public Integer valueOf(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
