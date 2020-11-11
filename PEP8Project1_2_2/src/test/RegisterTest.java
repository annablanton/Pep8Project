package test;

import model.Register;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * JUnit4 test for the Register class.
 * All tests should pass.
 * @author Group 6: Walter Kagel
 * @version 10/18/2020
 */
public class RegisterTest {
    Register r;

    @Before
    public void setUp() {
        r = new Register();
    }

    @Test
    public void testRegConstructor() {
        assertEquals(0, r.getReg());
    }

    @Test
    public void testLoad() {
        r.load((short) 0xAC23);
        assertEquals(44067, Short.toUnsignedInt(r.getReg()));
    }
}
