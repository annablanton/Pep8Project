package model;

public class Decimal extends Number {
    public Decimal(int theVal) {
        super(theVal);
    }

    public String getVal() {
        return Integer.toString(super.getRawVal());
    }
}
