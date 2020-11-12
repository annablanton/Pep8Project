package model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class BinaryCalculator extends Calculator {
    //TODO test binary calculator

    public BinaryCalculator() {
        super();
    }
    public Number add(Number x, Number y) {
        return operation(x, y, Integer::sum);
    }

    public Number subtract(Number x, Number y) {
        return operation(x, y, (a, b) -> a - b);
    }

    public Number divide(Number x, Number y) {
        return operation(x, y, (a, b) -> a / b);
    }
    public Number multiply(Number x, Number y) {
        return operation(x, y, (a, b) -> a * b);
    }

    public Number convert(Binary b) {
        int num = binaryToInt(b);
        return new Binary(num);
    }

    public Number convert(Hex h) {
        int num = hexToInt(h);
        return new Binary(num);
    }

    public Number convert(Decimal d) {
        int num = decimalToInt(d);
        return new Binary(num);
    }

    private Binary operation(Number x, Number y, BiFunction<Integer, Integer, Integer> op) {
        Binary xBinary = (Binary) convert(x);
        Binary yBinary = (Binary) convert(y);

        int num1 = binaryToInt(xBinary);
        int num2 = binaryToInt(yBinary);

        return new Binary(op.apply(num1, num2));
    }

}
