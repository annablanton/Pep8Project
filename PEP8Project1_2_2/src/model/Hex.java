package model;

public class Hex extends Number {
    public Hex(int theVal) {
        super(String.format("%02X", theVal));
    }

    public void setVal(int theVal) {
        setVal(String.format("%02X", theVal));
    }

    public boolean equals(Hex h) {
        return super.equals(h);
    }

    public int compareTo(Hex h) {
        return super.compareTo(h);
    }

    public String toString() {
        return "0x" + getVal();
    }
}
