package model;

/**
 * Concrete Decimal class. Creates and stores decimal strings from passed
 * integer values.
 */
public class Decimal extends Number {

	/**
	 * Decimal constructor. Creates a decimal string and passes the string to the
	 * Number constructor for storage.
	 * 
	 * @param theVal number to convert to decimal string
	 */
	public Decimal(int theVal) {
		super(Integer.toString(theVal));
	}

	/**
	 * Setter for value field.
	 * 
	 * @param theVal integer value to set corresponding internal decimal String to
	 */
	public void setVal(int theVal) {
		setVal(Integer.toString(theVal));
	}

	/**
	 * Checks for equality between two Decimal numbers.
	 * 
	 * @param d Decimal number for comparison
	 * @return true if equal, false if not
	 */
	public boolean equals(Decimal d) {
		return super.equals(d);
	}

	/**
	 * Compares two Decimal objects.
	 * 
	 * @param d Decimal number for comparison
	 * @return -1 if this Decimal object's value is less than passed Decimal
	 *         object's value, 0 if equal, 1 if greater than
	 */
	public int compareTo(Decimal d) {
		return super.compareTo(d);
	}

	/**
	 * String representation of Decimal object's value. Equivalent to getVal() for
	 * Decimal.
	 * 
	 * @return decimal string
	 */
	public String toString() {
		return getVal();
	}
}
