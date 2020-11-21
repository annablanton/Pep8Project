package model;

/**
 * Concrete Hex class. Creates and stores hex strings from passed integer
 * values.
 */
public class Hex extends Number {
	/**
	 * Hex constructor. Creates a hex string (padded to at least 8 bits) and passes
	 * the string to the Number constructor for storage.
	 * 
	 * @param theVal number to convert to hex string
	 */
	public Hex(int theVal) {
		super(String.format("%02X", theVal));
	}

	/**
	 * Setter for value field.
	 * 
	 * @param theVal integer value to set corresponding internal hex String to
	 */
	public void setVal(int theVal) {
		setVal(String.format("%02X", theVal));
	}

	/**
	 * Checks for equality between two Hex numbers.
	 * 
	 * @param h Hex number for comparison
	 * @return true if equal, false if not
	 */
	public boolean equals(Hex h) {
		return super.equals(h);
	}

	/**
	 * Compares two Hex objects.
	 * 
	 * @param h Hex number for comparison
	 * @return -1 if this Hex object's value is less than passed Hex object's value,
	 *         0 if equal, 1 if greater than
	 */
	public int compareTo(Hex h) {
		return super.compareTo(h);
	}

	/**
	 * String representation of Hex object's value. Concatenates the string "0x"
	 * with the hex string to indicate that it is a hex string.
	 * 
	 * @return typed hex string
	 */
	public String toString() {
		return "0x" + getVal();
	}
}
