package test;

import model.Binary;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinaryTest {
    Binary b;
    @Before
    public void setUp() {
        b = new Binary(5);
    }
    @Test
    public void testGetVal() {
        assertEquals(b.getVal(), "00000101");
    }
}
