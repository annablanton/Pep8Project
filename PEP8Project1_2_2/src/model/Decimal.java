package model;

public class Decimal extends Number {
    public Decimal(int theVal) {
        super(Integer.toString(theVal));
    }

    public void setVal(int theVal) {
        setVal(Integer.toString(theVal));
    }

    public boolean equals(Decimal d) {
        return super.equals(d);
    }

    public int compareTo(Decimal d) {
        return super.compareTo(d);
    }

    public String toString() {
        return getVal();
    }
}
