package expression.type;

import expression.exceptions.ParsingException;

public class DoubleType implements TypeOperations<Double> {

    @Override
    public Double add(Double left, Double right) {
        return left + right;
    }

    @Override
    public Double divide(Double left, Double right) {
        return left / right;
    }

    @Override
    public Double subtract(Double left, Double right) {
        return left - right;
    }

    @Override
    public Double negate(Double left) {
        return -left;
    }

    @Override
    public Double multiply(Double left, Double right) {
        return left * right;
    }

    @Override
    public Double valueOf(String s) {
        return Double.parseDouble(s);
//        double ret = 0, div = 1;
//        int pos = 0;
//        boolean neg = (s.charAt(pos) == '-');
//        if (neg)
//            pos++;
//
//        do {
//            ret = ret * 10 + s.charAt(pos) - '0';
//        } while (++pos < s.length());
//
//        if (s.charAt(--pos) == '.') {
//            while (++pos < s.length()) {
//                ret += (s.charAt(pos) - '0') / (div *= 10);
//            }
//        }
//        if (neg)
//            return -ret;
//        return ret;
    }
}

