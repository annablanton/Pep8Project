package model;

import java.util.Map;

/**
 * Calculator abstract class. Primarily contains helper methods for child
 * classes.
 * 
 * @author Anna Blanton
 */
public abstract class Calculator implements Convertible {
	/**
	 * Calculator constructor. There is no internal state for the calculator.
	 */
	public Calculator() {

	}

	/**
	 * Abstract add method.
	 * 
	 * @param x first parameter to be added
	 * @param y second parameter to be added
	 * @return sum of x and y
	 */
	public abstract Number add(Number x, Number y);

	/**
	 * Abstract divide method.
	 * 
	 * @param x parameter which is subtracted from
	 * @param y parameter to be subtracted from x
	 * @return difference between x and y
	 */
	public abstract Number subtract(Number x, Number y);

	/**
	 * Abstract divide method.
	 * 
	 * @param x dividend
	 * @param y divisor
	 * @return quotient
	 */
	public abstract Number divide(Number x, Number y);

	/**
	 * Abstract multiply method.
	 * 
	 * @param x multiplicand
	 * @param y multiplier
	 * @return product
	 */
	public abstract Number multiply(Number x, Number y);

	/**
	 * Helper convert method for child classes. Determines type of passed Number,
	 * then calls child class's convert method for the given type.
	 * 
	 * @param x Number to be converted
	 * @return Number converted to caller Calculator type
	 */
	protected Number convert(Number x) {
		if (x.getClass().getName().equals("model.Binary")) {
			return convert((Binary) x);
		} else if (x.getClass().getName().equals("model.Decimal")) {
			return convert((Decimal) x);
		} else if (x.getClass().getName().equals("model.Hex")) {
			return convert((Hex) x);
		} else {
			throw new IllegalArgumentException("Unexpected type for argument");
		}
	}

	/**
	 * Helper method to convert a Hex object to an integer value.
	 * 
	 * @param h Hex object to convert to int
	 * @return int value of Hex String
	 */
	protected static int hexToInt(Hex h) {
		String s = h.getVal();
		int num = 0;
		Map<Character, Integer> m = Map.ofEntries(Map.entry('0', 0), Map.entry('1', 1), Map.entry('2', 2),
				Map.entry('3', 3), Map.entry('4', 4), Map.entry('5', 5), Map.entry('6', 6), Map.entry('7', 7),
				Map.entry('8', 8), Map.entry('9', 9), Map.entry('A', 10), Map.entry('B', 11), Map.entry('C', 12),
				Map.entry('D', 13), Map.entry('E', 14), Map.entry('F', 15));
		for (int i = 0; i < s.length(); i++) {
			num += m.get(s.charAt(i)) * Math.pow(16, s.length() - i - 1);
		}
		return num;
	}

	/**
	 * Helper method to convert a Binary object to an integer value.
	 * 
	 * @param b Binary object for conversion
	 * @return converted int value of Binary String
	 */
	protected static int binaryToInt(Binary b) {
		String s = b.getVal();
		int num = 0;
		for (int i = 0; i < s.length(); i++) {
			num += Integer.parseInt(String.valueOf(s.charAt(i))) * Math.pow(2, s.length() - i - 1);
		}
		return num;
	}

	/**
	 * Helper method to convert a Decimal object to an integer value.
	 * 
	 * @param d Decimal object for conversion
	 * @return converted int value of Decimal String
	 */
	protected static int decimalToInt(Decimal d) {
		String s = d.getVal();
		return Integer.parseInt(s);
	}
}
