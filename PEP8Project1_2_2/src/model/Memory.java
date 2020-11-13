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

	public byte getInstruction(short addr) {
		return mem[Short.toUnsignedInt(addr)];
	}

	public short getData(short addr) {
		return fuseBytes(mem[Short.toUnsignedInt(addr)], mem[Short.toUnsignedInt(addr)+1]);
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

	public MachineInstruction test() {
		return new LoadInstruction(AddressingMode.IMMEDIATE, RegName.A);
	}
	public byte[] getMemoryDump() {
		return Arrays.copyOf(mem, mem.length);
	}

	private short fuseBytes(byte b1, byte b2) {
		boolean[] oper1Array = this.toBoolArray(b1);
		boolean[] oper2Array = this.toBoolArray(b2);
		boolean[] fuseArray = new boolean[16];
		System.arraycopy(oper1Array, 8, fuseArray, 0, 8);
		System.arraycopy(oper2Array, 8, fuseArray, 8, 8);
		return this.toShort(fuseArray);
	}

	private boolean[] toBoolArray(short x) {
		boolean[] rtnArray = new boolean[16];
		short[] twoPow = {16384, 8192, 4096, 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
		for (int i = 0; i < rtnArray.length; i++) {
			if (i == 0) {
				if (x < 0) {
					rtnArray[0] = true;
					x = (short) (x - 32768);
				} else {
					rtnArray[0] = false;
				}
			} else {
				if (x >= twoPow[i - 1]) {
					rtnArray[i] = true;
					x-= twoPow[i - 1];
				} else {
					rtnArray[i] = false;
				}
			}
		}
		return rtnArray;
	}

	private short toShort(boolean[] boolArray) {
		short rtnShort = 0;
		short[] twoPow = {16384, 8192, 4096, 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
		for (int i = 0; i < boolArray.length; i++) {
			if (i == 0) {
				if (boolArray[i]) {
					rtnShort = (short) -32768;
				}
			} else {
				if (boolArray[i]) {
					rtnShort += twoPow[i - 1];
				}
			}
		}
		return rtnShort;
	}

}
