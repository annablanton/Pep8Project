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
		System.out.println("getData call" + addr);
		return fuseBytes(mem[Short.toUnsignedInt(addr)], mem[Short.toUnsignedInt(addr)+1]);
	}

	public void storeInstruction(short addr, byte ... d) {
		Objects.requireNonNull(d, "byte array must not be null");
		if (d.length > (Short.MAX_VALUE+1)*2) {
			throw new IllegalArgumentException("Byte array too large");
		}
		int uaddr;
		for (int i = 0; i < d.length; i++) {
			uaddr = (Short.toUnsignedInt(addr) + i) % 65536;
			mem[uaddr] = d[i];
		}
	}

	/*
	 * This is an alias of the storeInstruction method.
	 * Since instructions and characters are both bytes, the operations performed are identical, but
	 * explicitly naming this instruction storeCharacter is clearer when calling it for character input.
	 */
	public void storeCharacter(short addr, byte ... d) {
		storeInstruction(addr, d);
	}

	public char getCharacter(short addr) {
		return (char) mem[Short.toUnsignedInt(addr)];
	}

	public void storeData(short addr, short ... d) {
		Objects.requireNonNull(d, "short array must not be null");
		if (d.length > (Short.MAX_VALUE+1)) {
			throw new IllegalArgumentException("Short array too large");
		}

		for (int i = 0; i < d.length; i++) {
			int uaddr1 = (Short.toUnsignedInt(addr)+2*i) % 65536;
			int uaddr2 = (uaddr1 + 1) % 65536;

			boolean[] wholeBool = this.toBoolArray(d[i]);
			boolean[] half1 = new boolean[8];
			boolean[] half2 = new boolean[8];
			System.arraycopy(wholeBool, 0, half1, 0, half1.length);
			System.arraycopy(wholeBool, 8, half2, 0, half2.length);
			byte sHalf1 = this.toByte(half1);
			byte sHalf2 = this.toByte(half2);

			mem[uaddr1] = sHalf1;
			mem[uaddr2] = sHalf2;
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

	private byte toByte(boolean[] boolArray) {
		byte rtnByte = 0;
		byte[] twoPow = {64, 32, 16, 8, 4, 2, 1};
		for (int i = 0; i < boolArray.length; i++) {
			if (i == 0) {
				if (boolArray[i]) {
					rtnByte = (byte) -128;
				}
			} else {
				if (boolArray[i]) {
					rtnByte += twoPow[i - 1];
				}
			}
		}
		return rtnByte;
	}

}
