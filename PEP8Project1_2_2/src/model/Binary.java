package model;

/**
 * Concrete Binary class.
 * Creates and stores binary strings from passed integer values.
 */
public class Binary extends Number {
    /**
     * Binary constructor.
     * Creates a binary string (padded to at least 8 bits) and passes the string to the Number constructor for storage.
     * @param theVal number to convert to binary string
     */
    public Binary(int theVal) {
        super(toPaddedBinaryString(theVal));
    }

    /**
     * Setter for value field.
     * @param theVal integer value to set corresponding internal binary String to
     */
    public void setVal(int theVal) {
        setVal(toPaddedBinaryString(theVal));
    }

    /**
     * Static method to create a binary string left padded to at least 8 bits from the passed integer.
     * @param theVal integer to convert to binary string
     * @return padded binary string
     */
    private static String toPaddedBinaryString(int theVal) {
        StringBuilder s = new StringBuilder();
        while (theVal != 0 && theVal != -1) {
            s.append((theVal % 2 == 1 || theVal % 2 == -1) ? "1" : "0");
            theVal >>= 1;
        }
        if (theVal == 0) {
            s.append("0");
        } else {
            s.append("1");
        }
        s.reverse();
        String b = s.toString();
        if (b.length() < 8) {
//            StringBuilder padding = new StringBuilder();
//            for (int i = b.length(); i < 8; i++) {
//                padding.append("0");
//            }
            String p = Character.toString(b.charAt(0));
            p = p.repeat(8-b.length());
            p+=b;
            b = p;
        }
        return b;
    }

    /**
     * Checks for equality between two Binary numbers.
     * @param b Binary number for comparison
     * @return true if equal, false if not
     */
    public boolean equals(Binary b) {
        return super.equals(b);
    }

    /**
     * Compares two Binary objects.
     * @param b Binary number for comparison
     * @return -1 if this Binary object's value is less than passed Binary object's value, 0 if equal, 1 if greater than
     */
    public int compareTo(Binary b) {
        return super.compareTo(b);
    }

    /**
     * String representation of Binary object's value.
     * Concatenates the string "0b" with the binary string to indicate that it is a binary string.
     * @return typed binary string
     */
    public String toString() {
        return "0b" + getVal();
    }

}
