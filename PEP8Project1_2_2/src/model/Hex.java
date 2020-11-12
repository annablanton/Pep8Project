package model;

public class Hex extends Number {
    public Hex(int theVal) {
        super(theVal);
    }

    public String getVal() {
        String returnMe = "";
        for (int i = super.getRawVal(); i > 0; i >>= 4) {
            int nextNum = i % 16;
            if (nextNum < 10) {
                returnMe = Integer.toString(nextNum) + returnMe;
            } else {
                switch (i % 16) {
                    case 10:
                        returnMe = "A" + returnMe;
                        break;
                    case 11:
                        returnMe = "B" + returnMe;
                        break;
                    case 12:
                        returnMe = "C" + returnMe;
                        break;
                    case 13:
                        returnMe = "D" + returnMe;
                        break;
                    case 14:
                        returnMe = "E" + returnMe;
                        break;
                    case 15:
                        returnMe = "F" + returnMe;
                        break;
                }
            }
        }

        while (returnMe.length() < 2) {
            returnMe = "0" + returnMe;
        }

        returnMe = "0x" + returnMe;
        return returnMe;
    }
}
