package model;

public abstract class Number {
    private String val;

    public Number(String theVal) {
        val = theVal;
    }

    public String getVal() {
        return val;
    }

    protected void setVal(String theVal) {
        val = theVal;
    }

    public abstract void setVal(int theVal);

    protected boolean equals(Number x) {
        return val.equals(x.getVal());
    }

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

    public abstract String toString();
}
