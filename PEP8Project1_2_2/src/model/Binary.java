package model;

public class Binary extends Number {
    public Binary(int theVal) {
        super(toPaddedBinaryString(theVal));
    }

    public void setVal(int theVal) {
        setVal(toPaddedBinaryString(theVal));
    }

    private static String toPaddedBinaryString(int theVal) {
        String b = Integer.toBinaryString(theVal);
        if (b.length() < 8) {
//            StringBuilder padding = new StringBuilder();
//            for (int i = b.length(); i < 8; i++) {
//                padding.append("0");
//            }
            String p = "0";
            p = p.repeat(8-b.length());
            p+=b;
            b = p;
        }
        return b;
    }

    public boolean equals(Binary b) {
        return super.equals(b);
    }

    public int compareTo(Binary b) {
        return super.compareTo(b);
    }

    public String toString() {
        return "0b" + getVal();
    }

}
