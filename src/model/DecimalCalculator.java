package model;

import java.util.function.BiFunction;
import java.util.function.IntSupplier;

/**
 * DecimalCalculator class. Contains methods for performing operations on any
 * two Number objects together and returning a Decimal object.
 * 
 * @author Anna Blanton
 */
public class DecimalCalculator extends Calculator {

	/**
	 * DecimalCalculator constructor. The DecimalCalculator has no internal state.
	 */
	public DecimalCalculator() {

	}

	/**
	 * Add method for DecimalCalculator.
	 * 
	 * @param x first parameter to be added
	 * @param y second parameter to be added
	 * @return Decimal object with sum of x and y
	 */
	public Number add(Number x, Number y) throws IllegalArgumentException {
		return operation(x, y, Integer::sum);
	}

	/**
	 * Subtract method for DecimalCalculator.
	 * 
	 * @param x parameter which is subtracted from
	 * @param y parameter to be subtracted from x
	 * @return Decimal object with difference of x and y
	 */
	public Number subtract(Number x, Number y) {
		return operation(x, y, (a, b) -> a - b);
	}

	/**
	 * Divide method for DecimalCalculator.
	 * 
	 * @param x dividend
	 * @param y divisor
	 * @return Decimal object with quotient of x and y
	 */
	public Number divide(Number x, Number y) {
		return operation(x, y, (a, b) -> a / b);
	}

	/**
	 * Multiply method for DecimalCalculator.
	 * 
	 * @param x multiplicand
	 * @param y multiplier
	 * @return Decimal object with product of x and y
	 */
	public Number multiply(Number x, Number y) {
		return operation(x, y, (a, b) -> a * b);
	}

	/**
	 * Binary to Decimal converter.
	 * 
	 * @param b Binary object to convert
	 * @return Converted Decimal object
	 */
	public Number convert(Binary b) {
		int num = binaryToInt(b);
		return new Decimal(num);
	}

	/**
	 * Decimal to Decimal converter. Object returned will be functionally equivalent
	 * to original, but this method is necessary to satisfy Converter interface.
	 * 
	 * @param d Decimal object to convert
	 * @return Converted Decimal object
	 */
	public Number convert(Decimal d) {
		int num = decimalToInt(d);
		return new Decimal(num);
	}

	/**
	 * Hex to Decimal converter.
	 * 
	 * @param h Decimal object to convert
	 * @return Converted Decimal object
	 */
	public Number convert(Hex h) {
		int num = hexToInt(h);
		return new Decimal(num);
	}

	/**
	 * Helper method for operations. Takes two Number objects, converts them to
	 * Decimal objects, then applies the passed operation to the integer values for
	 * the Decimal objects and returns a new Decimal object.
	 * 
	 * @param x  first Number operand
	 * @param y  second Number operand
	 * @param op operator
	 * @return result of operation
	 */
	private Decimal operation(Number x, Number y, BiFunction<Integer, Integer, Integer> op) {
		Decimal xDecimal = (Decimal) convert(x);
		Decimal yDecimal = (Decimal) convert(y);

		int num1 = decimalToInt(xDecimal);
		int num2 = decimalToInt(yDecimal);

		return new Decimal(op.apply(num1, num2));
	}
}
