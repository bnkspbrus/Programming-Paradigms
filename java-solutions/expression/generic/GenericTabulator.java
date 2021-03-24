package expression.generic;

import expression.TripleExpressionGeneric;
import expression.exceptions.ParsingException;
import expression.parser.ExpressionParser;
import expression.type.*;

import java.util.Arrays;
import java.util.Map;

public class GenericTabulator implements Tabulator {

    private String expression;
    private int x1, y1, z1;
    private final Map<String, TypeOperations<?>> CHANGE_TYPE = Map.of(
            "i", new IntegerType(true),
            "d", new DoubleType(),
            "bi", new BigIntegerType(),
            "u", new IntegerType(false),
            "f", new FloatType(),
            "b", new ByteType());

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws ParsingException {
        this.expression = expression;
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        return fillArray(CHANGE_TYPE.get(mode), new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1]);
    }

    private <T> Object[][][] fillArray(TypeOperations<T> type, Object[][][] array) throws ParsingException {
//        TripleExpression<T> tripleExpression = new ExpressionParser<>(type);
        ExpressionParser<T> ex = new ExpressionParser<>(type);
        TripleExpressionGeneric<T> tripleExpression = ex.parse(expression);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                for (int k = 0; k < array[0][0].length; k++) {
                    array[i][j][k] = tripleExpression.evaluate(type.valueOf(x1 + i + ""), type.valueOf(y1 + j + ""), type.valueOf(z1 + k + ""));
                }
            }
        }
        return array;
    }

    public static void main(String[] args) throws ParsingException {
        GenericTabulator gt = new GenericTabulator();
        Object[][][] res = gt.tabulate("i", "x + y + z", 1, 2, 3, 4, 5, 6);
        System.out.println(Arrays.deepToString(res));
    }
}
