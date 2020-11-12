package model;

public class Binary extends Number {
    public Binary(int theVal) {
        super(theVal);
    }
    public String getVal() {
        int v = super.getRawVal();
        String returnMe = "";
        for (int i = v; i > 0; i >>= 1) {
            String temp;
            if (i % 2 == 0) {
                temp = "0";
            } else {
                temp = "1";
            }
            returnMe = temp + returnMe;
        }

        while (returnMe.length() < 8) {
            returnMe = "0" + returnMe;
        }
        return returnMe;
    }

}
