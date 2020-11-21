package test;

import model.InstructionRegister;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * JUNIT4 tests for the instruction register.
 * All tests should pass.
 * @author Group 6: Walter Kagel
 * @version 10/18/2020
 */
public class InstructionRegisterTest {
    InstructionRegister ir;
    @Before
    public void setUp() {
        ir = new InstructionRegister();
    }

    @Test
    public void testInstrRegConstructor() {
        assertEquals(0, ir.getSpecifier());
    }

    @Test
    public void testInstrRegSetter() {
        ir.setSpecifier((byte) 0x32);
        assertEquals(50, Byte.toUnsignedInt(ir.getSpecifier()));
    }
}
