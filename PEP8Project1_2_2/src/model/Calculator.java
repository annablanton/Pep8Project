package model;

public abstract class Calculator implements Convertible {
    public abstract Number add(Number x, Number y);
    public abstract Number subtract(Number x, Number y);
    public abstract Number divide(Number x, Number y);
    public abstract Number multiply (Number x, Number y);

    protected Number convert(Number x) {
        if (x.getClass().getName().equals("model.Binary")) {
            return convert((Binary) x);
        }
        else if (x.getClass().getName().equals("model.Decimal")) {
            return convert((Decimal) x);
        }
        else if (x.getClass().getName().equals("model.Hex")) {
            return convert((Hex) x);
        }
        else {
            throw new IllegalArgumentException("Unexpected type for argument");
        }
    }

    protected static int hexToInt(Hex h) {
        String s = h.getVal();
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            num+= Integer.parseInt(String.valueOf(s.charAt(i))) * Math.pow(16, s.length() - i - 1);
        }
        return num;
    }

    protected static int binaryToInt(Binary b) {
        String s = b.getVal();
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            num+= Integer.parseInt(String.valueOf(s.charAt(i))) * Math.pow(2, s.length()-i-1);
        }
        return num;
    }

    protected static int decimalToInt(Decimal d) {
        String s = d.getVal();
        return Integer.parseInt(s);
    }
}
