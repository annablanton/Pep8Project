package test;

import model.Memory;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * JUnit4 tests for the memory.
 * All tests should pass.
 * @author Group 6: Walter Kagel
 * @version 10/18/2020
 */
public class MemoryTest {

    Memory m;

    @Before
    public void setUp() {
        m = new Memory();
    }

    @Test
    public void testMemoryConstructor() {
        byte[] temp = new byte[Short.MAX_VALUE*2+1];
        Arrays.fill(temp, (byte) 0);
        for (int i = 0; i < temp.length; i++) {
            assertEquals(temp[i], m.getDataAt((short) i));
        }
    }

    @Test
    public void testStoreOneByte() {
        m.store((short) 0xAB23, (byte) 0xF2);
        assertEquals(242, Byte.toUnsignedInt(m.getDataAt((short) 43811)));
    }

    @Test
    public void testStoreMultiBytes() {
        byte[] arr = {(byte) 0xAB, (byte) 0x4B, (byte) 0xCD, (byte) 0x8F};
        m.store((short) 0xAB23, arr);

        for (int i = 0; i < arr.length; i++) {
            assertEquals(arr[i], m.getDataAt((short) ((short) (0xAB23) + (short) i)));
        }
    }

    @Test(expected=NullPointerException.class)
    public void testStoreNullArray() {
        m.store((short) 0x1234, null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testStoreArrayTooLarge() {
        byte[] d = new byte[65537];
        m.store((short) 0x1234, d);
    }
}
