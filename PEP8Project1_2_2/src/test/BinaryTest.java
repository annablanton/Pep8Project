package test;

import model.Binary;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTest {
    Binary b;
    @Before
    public void setUp() {
        b = new Binary(5);
    }
    @Test
    public void testGetVal() {
        assertEquals("00000101", b.getVal());
    }

    @Test
    public void testSetVal() {
        b.setVal(10);
        assertEquals("00001010", b.getVal());
    }

    @Test
    public void testEquals() {
        Binary b2 = new Binary(5);
        assertTrue(b.equals(b2));
    }

    @Test
    public void testNotEquals() {
        Binary b2 = new Binary(10);
        assertFalse(b.equals(b2));
    }

    @Test
    public void testCompareToEqual() {
        Binary b2 = new Binary(5);
        assertEquals(0, b.compareTo(b2));
    }

    @Test
    public void testCompareToGreaterThan() {
        Binary b2 = new Binary(1);
        assertEquals(1, b.compareTo(b2));
    }

    @Test
    public void testCompareToLessThan() {
        Binary b2 = new Binary(512);
        assertEquals(-1, b.compareTo(b2));
    }

    @Test
    public void testToString() {
        assertEquals("0b00000101", b.toString());
    }
}
