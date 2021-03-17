package expression.type;

import expression.exceptions.EvaluatingException;
import expression.exceptions.ParsingException;

public class FloatType implements TypeOperations<Float> {

    @Override
    public Float add(Float left, Float right) {
        return left + right;
    }

    @Override
    public Float divide(Float left, Float right) {
        return left / right;
    }

    @Override
    public Float subtract(Float left, Float right) {
        return left - right;
    }

    @Override
    public Float negate(Float left) {
        return -left;
    }

    @Override
    public Float multiply(Float left, Float right) {
        return left * right;
    }

    @Override
    public Float valueOf(String s) {
        return Float.parseFloat(s);
    }
}
