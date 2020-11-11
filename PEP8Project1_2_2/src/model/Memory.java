package model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Class for memory object.
 * Stores byte array with 16-bit addressability.
 * Contains methods for reading and writing to the byte array.
 * @author Group 6: Anna Blanton, Walter Kagel
 * @version 10/17/2020
 */
public class Memory {
	private final byte[] mem;
	public Memory() {
				mem = new byte[(Short.MAX_VALUE+1)*2];
	}

	public byte getDataAt(short addr) {
		return mem[Short.toUnsignedInt(addr)];
	}

	public void store(short addr, byte ... d) {
		Objects.requireNonNull(d, "byte array must not be null");
		if (d.length > (Short.MAX_VALUE+1)*2) {
			throw new IllegalArgumentException("Byte array too large");
		}
		int uaddr;
		for (int i = 0; i < d.length; i++) {
			uaddr = Short.toUnsignedInt(addr) + i;
			mem[uaddr] = d[i];
		}
	}

	public byte[] getMemoryDump() {
		return Arrays.copyOf(mem, mem.length);
	}

}
