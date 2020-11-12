package test;

import model.ALU;
import model.Register;
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
    Register r;

    @Before
    public void setUp() {
        alu = new ALU();
        r = new Register();
    }

    @Test
    public void testAdd() {
        r.load((short) 13);
        assertEquals(20, alu.add(r, (short) 7));
    }

    @Test
    public void testSubtract() {
        r.load((short) 15);
        assertEquals(5, alu.subtract(r, (short)10));
    }

    @Test
    public void testAddOverflow() {
        r.load((short)32767);
        assertEquals(-32765, alu.add(r, (short) 4));
    }

    @Test
    public void testSubtractUnderflow() {
        r.load((short) -32768);
        assertEquals(32765, alu.subtract(r, (short) 3));
    }

    @Test
    public void testSubtractToNeg() {
        r.load((short) 3);
        assertEquals(-3, alu.subtract(r, (short) 6));
    }

    @Test
    public void testAddToPos() {
        r.load((short) -3);
        assertEquals( 3, alu.add(r, (short) 6));
    }
}
