package model;

public class DecimalCalculator extends Calculator {
    //TODO implement decimal calculator

    public Number add(Number x, Number y) throws IllegalArgumentException {
        if (!(x.getClass().equals("model.Decimal"))) {
            throw new IllegalArgumentException("Wrong type passed to DecimalCalculator add method for first argument (should be Decimal)");
        }

        if (!(y.getClass().equals("model.Decimal"))) {
            throw new IllegalArgumentException("Wrong type passed to DecimalCalculator add method for second argument (should be Decimal)");
        }
        return new Decimal(5);
    }

    public Number subtract(Number x, Number y) {
        return new Decimal(5);
    }

    public Number divide(Number x, Number y) {
        return new Decimal(5);
    }
    public Number multiply(Number x, Number y) {
        return new Decimal(5);
    }
}
