package test;

import model.ProgramCounter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * JUnit4 tests for the ProgramCounter class.
 * All tests should pass.
 * @author Group 6: Walter Kagel
 * @version 10/18/2020
 */
public class ProgramCounterTest {
    ProgramCounter pc;

    @Before
    public void setUp() {
        pc = new ProgramCounter();
    }

    @Test
    public void testOffset() {
        pc.offset((byte) 2);
        assertEquals(2, Short.toUnsignedInt(pc.getReg()));
    }
}
