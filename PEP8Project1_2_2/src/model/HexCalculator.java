package model;

import java.util.function.BiFunction;

public class HexCalculator extends Calculator {

    public HexCalculator() {

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
        return new Hex(num);
    }

    public Number convert(Decimal d) {
        int num = decimalToInt(d);
        return new Hex(num);
    }

    public Number convert(Hex h) {
        int num = hexToInt(h);
        return new Hex(num);
    }

    private Hex operation(Number x, Number y, BiFunction<Integer, Integer, Integer> op) {
        Hex xHex = (Hex) convert(x);
        Hex yHex = (Hex) convert(y);

        int num1 = hexToInt(xHex);
        int num2 = hexToInt(yHex);

        return new Hex(op.apply(num1, num2));
    }
}
