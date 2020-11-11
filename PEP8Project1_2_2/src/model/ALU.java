package model;

/**
 * Represents an Arithmetic and Logic Unit used by a virtual computer.
 * @author Group 6: John Morton
 * @version 10/16/2020
 */
public class ALU {
	
	/**
	 * Initializes our ALU.
	 */
	public ALU() {
	
	}
	
	/**
	 * Adds two shorts together, this method breaks the shorts down to simulate the internal workings of an ALU.
	 * @param x1 The first short value.
	 * @param x2 The second short value.
	 * @return The sum of our two short values.
	 */
	public short add(short x1, short x2) {
		boolean[] boolArray1 = toBoolArray(x1);
		boolean[] boolArray2 = toBoolArray(x2);
		boolean val1 = false;
		boolean val2 = false;
		boolean carry = false;
		boolean[] booleanResult = new boolean[16];
		if(boolArray1.length == boolArray2.length) {
			for (int i = boolArray1.length - 1; i >= 0; i--) {
				val1 = boolArray1[i];
				val2 = boolArray2[i];
				if (val1 & val2) {
					if (carry) {
						booleanResult[i] = true;
						carry = true;
					} else {
						booleanResult[i] = false;
						carry = true;
					}
				} else if (val1 || val2) {
					if (carry) {
						booleanResult[i] = false;
						carry = true;
					} else {
						booleanResult[i] = true;
						carry = false;
					}
				} else {
					if (carry) {
						booleanResult[i] = true;
						carry = false;
					} else {
						booleanResult[i] = false;
						carry = false;
					}
				}
			}
		}
		return toShort(booleanResult);
	}
	
	/**
	 * Add two short values together when one of them is stored in a register.
	 * @param x The first short value that we want to add.
	 * @param reg The register where our second short value is stored.
	 * @return The sum of our two short values.
	 */
	public short add(short x, Register reg) {
		return add(reg.getReg(), x);
	}
	
	/**
	 * Subtract two short values, this method breaks them down to simulate the inner workings of an ALU.
	 * @param x1 The first short value that we want to manipulate.
	 * @param x2 The short value that we wish to subtract from x1.
	 * @return The result of x1 - x2, this will be a short.
	 */
	public short subtract(short x1, short x2) {
		//subtract by adding the negative of the second number to the first
		boolean[] boolArray2 = toBoolArray(x2);
		for (int i = 0; i < boolArray2.length; i++) {
			if (boolArray2[i]) {
				boolArray2[i] = false;
			} else {
				boolArray2[i] = true;
			}
		}
		boolean breaker = false;
		for (int i = boolArray2.length - 1; i >= 0; i--) {
			if (!breaker) {
				if (boolArray2[i]) {
					boolArray2[i] = false;
				} else {
					boolArray2[i] = true;
					breaker = true;
				}
			}
		}
		return add(x1, toShort(boolArray2));
	}
	
	/**
	 * Subtracts two short values, one of which is stored in a register.
	 * @param reg The short value that we will manipulate, stored in a register.
	 * @param x The short value which we will subtract from the value in the register.
	 * @return The result of the subtraction.
	 */
	public short subtract(Register reg, short x) {
		return subtract(reg.getReg(), x);
	}
	
	/**
	 * Subtracts two short values, one of which is stored in a register.
	 * @param x The short value that we will manipulate.
	 * @param reg A register holding a short value which we will subtract from x.
	 * @return The result of our subtraction.
	 */
	public short subtract(short x, Register reg) {
		return subtract(reg.getReg(), x);
	}
	
	/**
	 * A helper function which converts a short into a 16 bit boolean array.
	 * @param x The short that we will manipulate.
	 * @return A boolean array equivalent to our short value.
	 */
	private boolean[] toBoolArray(short x) {
		boolean[] rtnArray = new boolean[16];
		short twoPow[] = {16384, 8192, 4096, 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
		for (int i = 0; i < rtnArray.length; i++) {
			if (i == 0) {
				if (x < 0) {
					rtnArray[i] = true;
					x = (short) (Short.toUnsignedInt(x) - 32768);
				} else {
					rtnArray[i] = false;
				}
			} else {
				if (x >= twoPow[i - 1]) {
					rtnArray[i] = true;
					x -= twoPow[i - 1];
				} else {
					rtnArray[i] = false;
				}
			}
		}
		return rtnArray;
	}
	
	/**
	 * A helper method which converts a boolean array to a short.
	 * @param boolArray The boolean array that we wish to convert.
	 * @return A short value representing the input boolean array.
	 */
	private short toShort(boolean[] boolArray) {
		short rtnShort = 0;
		short twoPow[] = {16384, 8192, 4096, 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
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
