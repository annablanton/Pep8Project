package test;

import model.*;
import model.Number;
import org.junit.Before;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class HexCalculatorTest {
    HexCalculator hc;
    @Before
    public void setUp() {
        hc = new HexCalculator();
    }

    @Test
    public void testAddTwoBinary() {
        testOpTypes(50, 20, 70, hc::add, Binary::new);
    }

    @Test
    public void testAddTwoDecimal() {
        testOpTypes(30, 20, 50, hc::add, Decimal::new);
    }

    @Test
    public void testAddTwoHex() {
        testOpTypes(10, 5, 15, hc::add, Hex::new);
    }

    @Test
    public void testAddMix() {
        testOpTypes(20, 50, 70, hc::add, Decimal::new, Hex::new);
    }

    @Test
    public void testSubtractTwoBinary() {
        testOpTypes(70, 30, 40, hc::subtract, Binary::new);
    }

    @Test
    public void testSubtractTwoHex() {
        testOpTypes(70, 30, 40, hc::subtract, Hex::new);
    }

    @Test
    public void testSubtractTwoDecimal() {
        testOpTypes(10, 5, 5, hc::subtract, Decimal::new);
    }

    @Test
    public void testSubtractMix() {
        testOpTypes(100, 40, 60, hc::subtract, Decimal::new, Hex::new);
    }

    @Test
    public void testMultiplyTwoBinary() {
        testOpTypes(5, 4, 20, hc::multiply, Binary::new);
    }

    @Test
    public void testMultiplyTwoHex() {
        testOpTypes(10, 4, 40, hc::multiply, Hex::new);
    }

    @Test
    public void testMultiplyTwoDecimal() {
        testOpTypes(2, 3, 6, hc::multiply, Hex::new);
    }

    @Test
    public void testMultiplyMix() {
        testOpTypes(15, 2, 30, hc::multiply, Decimal::new, Hex::new);
    }

    @Test
    public void testDivideTwoBinary() {
        testOpTypes(8, 4, 2, hc::divide, Binary::new);
    }

    @Test
    public void testDivideTwoDecimal() {
        testOpTypes(7, 4, 1, hc::divide, Decimal::new);
    }

    @Test
    public void testDivideTwoHex() {
        testOpTypes(4, 5, 0, hc::divide, Decimal::new);
    }

    @Test
    public void testDivideMix() {
        testOpTypes(55, 7, 7, hc::divide, Decimal::new, Hex::new);
    }

    private void testOpTypes(int x, int y, int expected,
                             BiFunction<model.Number, model.Number, model.Number> testOp, Function<Integer, model.Number> type) {
        testOpTypes(x, y, expected, testOp, type, type);
    }

    private void testOpTypes(int x, int y, int expected,
                             BiFunction<model.Number, model.Number, model.Number> testOp, Function<Integer, model.Number> type1, Function<Integer, model.Number> type2) {
        model.Number n1 = type1.apply(x);
        Number n2 = type2.apply(y);

        assertEquals(new Hex(expected).getVal(), testOp.apply(n1, n2).getVal());
    }
}
