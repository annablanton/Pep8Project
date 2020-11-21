package test;

import model.Decimal;
import model.Hex;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DecimalTest {
	Decimal d;

	@Before
	public void setUp() {
		d = new Decimal(523);
	}

	@Test
	public void testGetVal() {
		assertEquals("523", d.getVal());
	}

	@Test
	public void testSetVal() {
		d.setVal(20);
		assertEquals("20", d.getVal());
	}

	@Test
	public void testEquals() {
		Decimal d2 = new Decimal(523);
		assertTrue(d.equals(d2));
	}

	@Test
	public void testNotEquals() {
		Decimal d2 = new Decimal(10);
		assertFalse(d.equals(d2));
	}

	@Test
	public void testCompareToEqual() {
		Decimal d2 = new Decimal(523);
		assertEquals(0, d.compareTo(d2));
	}

	@Test
	public void testCompareToLessThan() {
		Decimal d2 = new Decimal(1000);
		assertEquals(-1, d.compareTo(d2));
	}

	@Test
	public void testCompareToGreaterThan() {
		Decimal d2 = new Decimal(100);
		assertEquals(1, d.compareTo(d2));
	}

	@Test
	public void testToString() {
		assertEquals("523", d.toString());
	}
}
