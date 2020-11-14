package model;

import view.GUI;

import java.util.Arrays;

/**
 * Represents an Arithmetic and Logic Unit used by a virtual computer.
 * @author Group 6: John Morton
 * @version 10/16/2020
 */
public class ALU {
	
	/**
	 * Initializes our ALU.
	 */

	private Flag nFlag;
	private Flag zFlag;
	private Flag vFlag;
	private Flag cFlag;
	private GUI view;

	public ALU(GUI v) {
		nFlag = Flag.N;
		zFlag = Flag.Z;
		vFlag = Flag.V;
		cFlag = Flag.C;
		view = v;
	}
	
	/**
	 * Adds two shorts together, this method breaks the shorts down to simulate the internal workings of an ALU.
	 * @param r The Register.
	 * @param x2 The second short value.
	 * @return The sum of our two short values.
	 */
	public short add(Register r, short x2) {
		resetFlags();
		short x1 = r.getReg();
		boolean[] boolArray1 = toBoolArray(x1);
		boolean[] boolArray2 = toBoolArray(x2);
		boolean val1;
		boolean val2;
		cFlag.setFlag(false);
		boolean[] booleanResult = new boolean[16];
		if(boolArray1.length == boolArray2.length) {
			for (int i = boolArray1.length - 1; i >= 0; i--) {
				val1 = boolArray1[i];
				val2 = boolArray2[i];
				if (val1 && val2) {
					booleanResult[i] = cFlag.isSet();
					//cFlag should be true at the end if the last bit had a carry
					cFlag.setFlag(true);
					view.setCbox(true);
				} else if (val1 || val2) {
					if (cFlag.isSet()) {
						booleanResult[i] = false;
						cFlag.setFlag(true);
						view.setCbox(true);
					} else {
						booleanResult[i] = true;
						cFlag.setFlag(false);
						view.setCbox(false);
					}
				} else {
					booleanResult[i] = cFlag.isSet();
					cFlag.setFlag(false);
					view.setCbox(false);
				}
			}
			if ((boolArray1[0] == boolArray2[0]) && (booleanResult[0]!=boolArray1[0])) {
				vFlag.setFlag(true);
				view.setVbox(true);
			}
		}
		short s = toShort(booleanResult);
		if (s == 0) {
			zFlag.setFlag(true);
			view.setZbox(true);
		} else if (s < 0) {
			nFlag.setFlag(true);
			view.setNbox(true);
		}
		return s;
	}
	
	/**
	 * Add two short values together when one of them is stored in a register.
	 * @param x The first short value that we want to add.
	 * @param reg The register where our second short value is stored.
	 */
	public short add(short x, Register reg) {
		return add(reg, x);
	}
	
	/**
	 * Subtract two short values, this method breaks them down to simulate the inner workings of an ALU.
	 * @param r The Register we are subtracting from.
	 * @param x2 The short value that we wish to subtract from r.
	 */
	public short subtract(Register r, short x2) {
		//subtract by adding the negative of the second number to the first
		boolean[] boolArray2 = toBoolArray(x2);
		for (int i = 0; i < boolArray2.length; i++) {
			boolArray2[i] = !boolArray2[i];
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
		return add(r, toShort(boolArray2));
	}

	public short and(Register r, short x2) {
		short x1 = r.getReg();
		resetFlags();
		short ret = (short) (x1 & x2);
		if (ret < 0) {
			nFlag.setFlag(true);
			view.setNbox(true);
		} else if (ret == 0) {
			zFlag.setFlag(true);
			view.setZbox(true);
		}
		return ret;
	}

	public short or(Register r, short x2) {
		resetFlags();
		short x1 = r.getReg();
		short ret = (short) (x1 | x2);
		if (ret < 0) {
			nFlag.setFlag(true);
			view.setNbox(true);
		} else if (ret == 0) {
			zFlag.setFlag(true);
			view.setZbox(true);
		}
		return ret;
	}

	public void compare(Register r, short x2) {
		resetFlags();
		short x1 = r.getReg();
		//by subtracting without returning, comparison is complete
		subtract(r, x2);
	}

	public short not(Register r) {
		resetFlags();
		short x = r.getReg();
		//System.out.println("x " + x);
		x= (short) ((~x)&0x0000FFFF);
		//System.out.println("x " + x);
		if (x < 0) {
			nFlag.setFlag(true);
			view.setNbox(true);
		} else if (x == 0) {
			zFlag.setFlag(true);
			view.setZbox(true);
		}
		return x;
	}

	public short negate(Register r) {
		resetFlags();
		short x = r.getReg();
		x = (short) (~x);
		x+=1;
		if (x < 0) {
			nFlag.setFlag(true);
			view.setNbox(true);
		} else if (x == 0) {
			zFlag.setFlag(true);
			view.setZbox(true);
		}
		return x;
	}

	public short arithShiftLeft(Register r) {
		resetFlags();
		short x = r.getReg();
		boolean[] boolArr1 = toBoolArray(x);
		cFlag.setFlag(boolArr1[0]);
		view.setCbox(boolArr1[0]);
		x <<= 1;
		boolean[] boolArr2 = toBoolArray(x);
		if (boolArr2[0] != boolArr1[0]) {
			vFlag.setFlag(true);
			view.setVbox(true);
		}

		if (x < 0) {
			nFlag.setFlag(true);
			view.setNbox(true);
		} else if (x == 0) {
			zFlag.setFlag(true);
			view.setZbox(true);
		}

		return x;
	}

	public short arithShiftRight(Register r) {
		resetFlags();
		short x = r.getReg();
		boolean[] boolArr = toBoolArray(x);
		cFlag.setFlag(boolArr[boolArr.length-1]);
		view.setCbox(boolArr[boolArr.length-1]);

		x >>= 1;
		if (x < 0) {
			nFlag.setFlag(true);
			view.setNbox(true);
		} else if (x == 0) {
			zFlag.setFlag(true);
			view.setZbox(true);
		}

		return x;
	}

	public short rotateLeft(Register r) {
		short x = r.getReg();
		boolean carryBit = cFlag.isSet();
		resetFlags();
		boolean[] boolArr1 = toBoolArray(x);
		cFlag.setFlag(boolArr1[0]);
		view.setCbox(boolArr1[0]);
		x <<= 1;
		boolean[] boolArr2 = toBoolArray(x);
		boolArr2[boolArr2.length-1]=carryBit;
		return toShort(boolArr2);
	}

	public short rotateRight(Register r) {
		short x = r.getReg();
		boolean carryBit = cFlag.isSet();
		resetFlags();
		boolean[] boolArr1 = toBoolArray(x);
		cFlag.setFlag(boolArr1[boolArr1.length-1]);
		view.setCbox(boolArr1[boolArr1.length-1]);
		x >>= 1;
		boolean[] boolArr2 = toBoolArray(x);
		boolArr2[0] = carryBit;
		return toShort(boolArr2);
	}



	public boolean nFlagIsSet() {
		return nFlag.isSet();
	}

	public boolean zFlagIsSet() {
		return zFlag.isSet();
	}

	public boolean vFlagIsSet() {
		return vFlag.isSet();
	}

	public boolean cFlagIsSet() {
		return cFlag.isSet();
	}
	
	/**
	 * A helper function which converts a short into a 16 bit boolean array.
	 * @param x The short that we will manipulate.
	 * @return A boolean array equivalent to our short value.
	 */
	private boolean[] toBoolArray(short x) {
		boolean[] rtnArray = new boolean[16];
//		short[] twoPow = {16384, 8192, 4096, 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
//		for (int i = 0; i < rtnArray.length; i++) {
//			if (i == 0) {
//				if (x < 0) {
//					rtnArray[i] = true;
//					x = (short) (Short.toUnsignedInt(x) - 32768);
//				} else {
//					rtnArray[i] = false;
//				}
//			} else {
//				if (x >= twoPow[i - 1]) {
//					rtnArray[i] = true;
//					x -= twoPow[i - 1];
//				} else {
//					rtnArray[i] = false;
//				}
//			}
//		}

		String b = new Binary(x).getVal();
		for (int i = 0; i < b.length(); i++) {
			rtnArray[rtnArray.length-1-i] = b.charAt(b.length() - 1 - i) == '1';
		}
		for (int i = rtnArray.length-b.length()-1; i >= 0; i--) {
			rtnArray[i] = rtnArray[i+1];
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
	private void resetFlags() {
		view.setNbox(false);
		view.setZbox(false);
		view.setVbox(false);
		view.setCbox(false);
		nFlag.setFlag(false);
		zFlag.setFlag(false);
		vFlag.setFlag(false);
		cFlag.setFlag(false);
	}
}