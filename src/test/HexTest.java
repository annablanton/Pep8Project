package test;

import model.Hex;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HexTest {
	Hex h;

	@Before
	public void setUp() {
		h = new Hex(523);
	}

	@Test
	public void testGetVal() {
		assertEquals("20B", h.getVal());
	}

	@Test
	public void testSetVal() {
		h.setVal(50);
		assertEquals("32", h.getVal());
	}

	@Test
	public void testEquals() {
		Hex h2 = new Hex(523);
		assertTrue(h.equals(h2));
	}

	@Test
	public void testNotEquals() {
		Hex h2 = new Hex(10);
		assertFalse(h.equals(h2));
	}

	@Test
	public void testCompareToEqual() {
		Hex h2 = new Hex(523);
		assertEquals(0, h.compareTo(h2));
	}

	@Test
	public void testCompareToLessThan() {
		Hex h2 = new Hex(1000);
		assertEquals(-1, h.compareTo(h2));
	}

	@Test
	public void testCompareToGreatherThan() {
		Hex h2 = new Hex(10);
		assertEquals(1, h.compareTo(h2));
	}

	@Test
	public void testToString() {
		assertEquals("0x20B", h.toString());
	}
}
