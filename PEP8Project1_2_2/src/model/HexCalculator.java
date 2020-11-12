package model;

import java.util.function.BiFunction;

/**
 * HexCalculator class.
 * Contains methods for performing operations on any two Number objects together and returning a Hex object.
 * @author Anna Blanton
 */
public class HexCalculator extends Calculator {

    /**
     * HexCalculator constructor.
     * The HexCalculator has no internal state.
     */
    public HexCalculator() {

    }

    /**
     * Add method for HexCalculator.
     * @param x first parameter to be added
     * @param y second parameter to be added
     * @return Hex object with sum of x and y
     */
    public Number add(Number x, Number y) {
        return operation(x, y, Integer::sum);
    }

    /**
     * Subtract method for HexCalculator.
     * @param x parameter which is subtracted from
     * @param y parameter to be subtracted from x
     * @return Hex object with difference of x and y
     */
    public Number subtract(Number x, Number y) {
        return operation(x, y, (a, b) -> a - b);
    }

    /**
     * Divide method for HexCalculator.
     * @param x dividend
     * @param y divisor
     * @return Hex object with quotient of x and y
     */
    public Number divide(Number x, Number y) {
        return operation(x, y, (a, b) -> a / b);
    }

    /**
     * Multiply method for HexCalculator.
     * @param x multiplicand
     * @param y multiplier
     * @return Hex object with product of x and y
     */
    public Number multiply(Number x, Number y) {
        return operation(x, y, (a, b) -> a * b);
    }

    /**
     * Binary to Hex converter.
     * @param b Binary object to convert
     * @return Converted Hex object
     */
    public Number convert(Binary b) {
        int num = binaryToInt(b);
        return new Hex(num);
    }

    /**
     * Decimal to Hex converter.
     * @param d Decimal object to convert
     * @return Converted Hex object
     */
    public Number convert(Decimal d) {
        int num = decimalToInt(d);
        return new Hex(num);
    }

    /**
     * Hex to Hex converter.
     * Object returned will be functionally equivalent to original, but this method is necessary to satisfy
     * Converter interface.
     * @param h Hex object to convert
     * @return converted Hex object
     */
    public Number convert(Hex h) {
        int num = hexToInt(h);
        return new Hex(num);
    }

    /**
     * Helper method for operations.
     * Takes two Number objects, converts them to Hex objects, then applies the passed operation to the integer values
     * for the Hex objects and returns a new Hex object.
     * @param x first Number operand
     * @param y second Number operand
     * @param op operator
     * @return result of operation
     */
    private Hex operation(Number x, Number y, BiFunction<Integer, Integer, Integer> op) {
        Hex xHex = (Hex) convert(x);
        Hex yHex = (Hex) convert(y);

        int num1 = hexToInt(xHex);
        int num2 = hexToInt(yHex);

        return new Hex(op.apply(num1, num2));
    }
}
