package model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * BinaryCalculator class.
 * Contains methods for performing operations on any two Number objects together and returning a Binary object.
 * @author Anna Blanton
 */
public class BinaryCalculator extends Calculator {

    /**
     * BinaryCalculator constructor.
     * The BinaryCalculator has no internal state.
     */
    public BinaryCalculator() {

    }

    /**
     * Add method for BinaryCalculator.
     * @param x first parameter to be added
     * @param y second parameter to be added
     * @return Binary object with sum of x and y
     */
    public Number add(Number x, Number y) {
        return operation(x, y, Integer::sum);
    }

    /**
     * Subtract method for BinaryCalculator.
     * @param x parameter which is subtracted from
     * @param y parameter to be subtracted from x
     * @return Binary object with difference of x and y
     */
    public Number subtract(Number x, Number y) {
        return operation(x, y, (a, b) -> a - b);
    }

    /**
     * Divide method for BinaryCalculator.
     * @param x dividend
     * @param y divisor
     * @return Binary object with quotient of x and y
     */
    public Number divide(Number x, Number y) {
        return operation(x, y, (a, b) -> a / b);
    }

    /**
     * Multiply method for BinaryCalculator.
     * @param x multiplicand
     * @param y multiplier
     * @return Binary object with product of x and y
     */
    public Number multiply(Number x, Number y) {
        return operation(x, y, (a, b) -> a * b);
    }

    /**
     * Binary to Binary converter.
     * @param b Binary object to convert
     * Object returned will be functionally equivalent to original, but this method is necessary to satisfy
     * Converter interface.
     * @return Converted Binary object
     */
    public Number convert(Binary b) {
        int num = binaryToInt(b);
        return new Binary(num);
    }

    /**
     * Hex to Binary converter.
     * @param h Decimal object to convert
     * @return Converted Binary object
     */
    public Number convert(Hex h) {
        int num = hexToInt(h);
        return new Binary(num);
    }

    /**
     * Decimal to Binary converter.
     * @param d Decimal object to convert
     * @return Converted Binary object
     */
    public Number convert(Decimal d) {
        int num = decimalToInt(d);
        return new Binary(num);
    }

    /**
     * Helper method for operations.
     * Takes two Number objects, converts them to Binary objects, then applies the passed operation to the integer values
     * for the Binary objects and returns a new Binary object.
     * @param x first Number operand
     * @param y second Number operand
     * @param op operator
     * @return result of operation
     */
    private Binary operation(Number x, Number y, BiFunction<Integer, Integer, Integer> op) {
        Binary xBinary = (Binary) convert(x);
        Binary yBinary = (Binary) convert(y);

        int num1 = binaryToInt(xBinary);
        int num2 = binaryToInt(yBinary);

        return new Binary(op.apply(num1, num2));
    }

}
