package model;

public abstract class Number {
    private int val;

    public Number(int theVal) {
        val = theVal;
    }
    public abstract String getVal();

    protected int getRawVal() {
        return val;
    }
}
