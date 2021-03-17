package expression.type;

import expression.exceptions.EvaluatingException;
import expression.exceptions.ParsingException;
import expressionGit.exceptions.OverflowException;

public class IntegerType implements TypeOperations<Integer> {

    private final boolean checkMode;

    public IntegerType(boolean checkMode) {
        this.checkMode = checkMode;
    }

    private boolean mainCondBin(Integer left, Integer right) {
        return left == null || right == null;
    }

    @Override
    public Integer add(Integer left, Integer right) throws ParsingException, EvaluatingException {
        if (checkMode) {
            if (mainCondBin(right, left)) {
                return null;
            }
            if (left >= 0 && right >= 0 && left + right < 0 || left < 0 && right < 0 && left + right >= 0) {
//            throw new EvaluatingException(left + " + " + right + " - Overflow\n");
                return null;
            }
        }
        return left + right;

    }

    @Override
    public Integer divide(Integer left, Integer right) throws ParsingException {
        if (checkMode) {
            if (mainCondBin(right, left)) {
                return null;
            }
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
    public Integer subtract(Integer left, Integer right) throws ParsingException, EvaluatingException {
        if (checkMode) {
//            if (mainCondBin(left, right)) {
//                return null;
//            }
//            if (left == Integer.MIN_VALUE && right == -1) {
////            throw new EvaluatingException(left + " - " + right + " - Overflow\n");
//                return null;
//            }
//            if (right == 0) {
//                return null;
//            }
            if (left >= 0 && right < 0 && left - Integer.MAX_VALUE > right || left <= 0 && right > 0 && Integer.MIN_VALUE - left > -right) {
                return null;
            }
        }
//        if (left >= 0 && right < 0 && left - Integer.MAX_VALUE > right
//                || left <= 0 && right > 0 && Integer.MIN_VALUE - left > -right) {
//            throw new EvaluateException("--overflow\n");
//        }
        return left - right;

    }

    @Override
    public Integer negate(Integer left) throws ParsingException, EvaluatingException {
        if (checkMode) {
            if (left == null) {
                return null;
            }
            if (left == Integer.MIN_VALUE) {
//            throw new EvaluatingException("-1 * " + left + " - Overflow\n");
                return null;
            }
        }
        return -left;
    }

    @Override
    public Integer multiply(Integer left, Integer right) throws ParsingException, EvaluatingException {
        if (checkMode) {
            if (mainCondBin(right, left)) {
                return null;
            }
            if (left >= 1 && right >= 1 && Integer.MAX_VALUE / left < right
                    || left < 0 && right < 0 && Integer.MAX_VALUE / left > right
                    || left < -1 && right >= 0 && Integer.MIN_VALUE / left < right
                    || left >= 0 && right < -1 && Integer.MIN_VALUE / right < left) {
//            throw new EvaluatingException(left + " * " + right + " - Overflow\n");
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
//            throw new EvaluateException(s + " - overflow\n");
            return null;
        }
//        int ret = 0, pos = 0;
//        boolean neg = (s.charAt(pos) == '-');
//        if (neg)
//            pos++;
//        do {
//            ret = ret * 10 + s.charAt(pos) - '0';
//        } while (++pos < s.length());
//        if (neg)
//            return -ret;
//        return ret;
    }
}
