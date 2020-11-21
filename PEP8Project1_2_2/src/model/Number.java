package model;

/**
 * Parent class for concrete Number types.
 * Stores shared behavior for a few functions and handles storage of the actual String value for the Number.
 * This allows callers of its child classes to use integer values which are converted to Strings by the child class
 * which is then passed to the parent class's methods to store.
 * @author Anna Blanton
 */
public abstract class Number {
    private String val;

    /**
     * Number constructor.
     * Called by its children's constructors after an initial string for the val field has been created.
     * @param theVal initial value for val
     */
    public Number(String theVal) {
        val = theVal;
    }

    /**
     * Getter for val field.
     * @return current String stored in Number class
     */
    public String getVal() {
        return val;
    }

    /**
     * Internal setter for val field.
     * Called by its children's setVal methods to store the val String.
     * @param theVal String value to store
     */
    protected void setVal(String theVal) {
        val = theVal;
    }

    /**
     * Public setter for val field.
     * @param theVal integer value to set corresponding internal String to
     */
    public abstract void setVal(int theVal);

    /**
     * Checks for equality between two Numbers.
     * @param x Number to compare to
     * @return true if values of Numbers are equal, false if not
     */
    protected boolean equals(Number x) {
        return val.equals(x.getVal());
    }

    /**
     * Compares two Numbers
     * @param x Number to compare to
     * @return -1 if this Number object's value is less than passed argument Number object's value, 0 if equal, 1 if greater than
     */
    protected int compareTo(Number x) {
        String temp1 = val;
        String temp2 = x.getVal();
        if (temp1.length() < temp2.length()) {
            String p = "0";
            p=p.repeat(temp2.length()-temp1.length());
            p+= temp1;
            temp1 = p;
        }
        else if (temp2.length() < temp1.length()) {
            String p = "0";
            p=p.repeat(temp1.length()-temp2.length());
            p+=temp2;
            temp2 = p;
        }
        int cmp = temp1.compareTo(temp2);
        if (cmp < 0) {
            return -1;
        }
        else if (cmp == 0) {
            return 0;
        }
        else {
            return 1;
        }
    }

    /**
     * Returns a string representation of the Number class.
     * @return String representation of Number
     */
    public abstract String toString();
}
