package expression.type;

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
    }
}

