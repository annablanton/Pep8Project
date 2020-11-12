package model;

import java.util.HashMap;
import java.util.Map;

public class BinaryCalculator extends Calculator {
    //TODO test binary calculator
    public Number add(Number x, Number y) {
        Binary xBinary = (Binary) convert(x);
        Binary yBinary = (Binary) convert(y);
        int num = binaryToInt(xBinary);
        num += binaryToInt(yBinary);
        return new Binary(num);
    }

    public Number subtract(Number x, Number y) {
        Binary xBinary = (Binary) convert(x);
        Binary yBinary = (Binary) convert(y);
        int num = binaryToInt(xBinary);
        num -= binaryToInt(yBinary);
        return new Binary(num);
    }

    public Number divide(Number x, Number y) {
        Binary xBinary = (Binary) convert(x);
        Binary yBinary = (Binary) convert(y);
        int num = binaryToInt(xBinary);
        num /= binaryToInt(yBinary);
        return new Binary(num);
    }
    public Number multiply(Number x, Number y) {
        Binary xBinary = (Binary) convert(x);
        Binary yBinary = (Binary) convert(y);
        int num = binaryToInt(xBinary);
        num *= binaryToInt(yBinary);
        return new Binary(num);
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

}
