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
            assertEquals(temp[i], m.getInstruction((short) i));
        }
    }

    @Test
    public void testStoreOneByte() {
        m.storeInstruction((short) 0xAB23, (byte) 0xF2);
        assertEquals(242, Byte.toUnsignedInt(m.getInstruction((short) 43811)));
    }

    @Test
    public void testStoreMultiBytes() {
        byte[] arr = {(byte) 0xAB, (byte) 0x4B, (byte) 0xCD, (byte) 0x8F};
        m.storeInstruction((short) 0xAB23, arr);

        for (int i = 0; i < arr.length; i++) {
            assertEquals(arr[i], m.getInstruction((short) ((short) (0xAB23) + (short) i)));
        }
    }

    @Test(expected=NullPointerException.class)
    public void testStoreNullArray() {
        m.storeInstruction((short) 0x1234, null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testStoreArrayTooLarge() {
        byte[] d = new byte[65537];
        m.storeInstruction((short) 0x1234, d);
    }

    @Test
    public void testGetData() {
        byte[] arr = {(byte) 0x12, (byte) 0x34};
        m.storeInstruction((short) 0xAB23, arr);

        assertEquals((short) 0x1234, m.getData((short) 0xAB23));
    }

    @Test
    public void testStoreData() {
        short s = 0x4523;
        m.storeData((short) 0xAB23, s);
        assertEquals((short) 0x4523, m.getData((short) 0xAB23));
    }

    @Test
    public void testStoreDataGetInstr() {
        short s = 0x4523;
        m.storeData((short) 0xAB23, s);
        assertEquals((short) 0x45, m.getInstruction((short) 0xAB23));
    }
}
