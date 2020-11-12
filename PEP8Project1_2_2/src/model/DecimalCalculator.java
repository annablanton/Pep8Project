package model;

import java.util.function.BiFunction;
import java.util.function.IntSupplier;

public class DecimalCalculator extends Calculator {
    //TODO implement decimal calculator

    public Number add(Number x, Number y) throws IllegalArgumentException {
        return operation(x, y, (a, b) -> a + b);
    }

    public Number subtract(Number x, Number y) {
        return operation(x, y, (a, b) -> a - b);
    }

    public Number divide(Number x, Number y) {
        return operation(x, y, (a,b) -> a / b);
    }
    public Number multiply(Number x, Number y) {
        return operation(x, y, (a, b) -> a * b);
    }

    public Number convert(Binary b) {
        int num = binaryToInt(b);
        return new Decimal(num);
    }

    public Number convert(Decimal d) {
        int num = decimalToInt(d);
        return new Decimal(num);
    }

    public Number convert(Hex h) {
        int num = hexToInt(h);
        return new Decimal(num);
    }

    private Decimal operation(Number x, Number y, BiFunction<Integer, Integer, Integer> op) {
        Decimal xDecimal = (Decimal) convert(x);
        Decimal yDecimal = (Decimal) convert(y);

        int num1 = decimalToInt(xDecimal);
        int num2 = decimalToInt(yDecimal);

        return new Decimal(op.apply(num1, num2));
    }
}
