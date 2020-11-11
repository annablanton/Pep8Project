package test;

import model.ALU;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * JUnit4 tests for the ALU class.
 * All tests should pass.
 * @author Group 6: Anna Blanton
 * @version 10/18/2020
 */
public class ALUTest {
    ALU alu;

    @Before
    public void setUp() {
        alu = new ALU();
    }

    @Test
    public void testAdd() {
        assertEquals(20, alu.add((short) 13, (short) 7));
    }

    @Test
    public void testSubtract() {
        assertEquals(5, alu.subtract((short)15, (short)10));
    }

    @Test
    public void testAddOverflow() {
        assertEquals(-32765, alu.add((short) 32767, (short) 4));
    }

    @Test
    public void testSubtractUnderflow() {
        assertEquals(32765, alu.subtract((short) -32768, (short) 3));
    }

    @Test
    public void testSubtractToNeg() {
        assertEquals(-3, alu.subtract((short) 3, (short) 6));
    }

    @Test
    public void testAddToPos() {
        assertEquals( 3, alu.add((short) -3, (short) 6));
    }
}
