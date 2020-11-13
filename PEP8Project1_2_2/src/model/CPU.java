package model;

import view.GUI;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * A class which represents a virtual CPU.
 * Used by the Machine class to execute instructions stored in memory.
 * @author Group 6:Anna Blanton, Walter Kagel, John Morton
 * @version 10/18/2020
 */
public class CPU {
	
	/**
	 * Register which stores the current instruction.
	 */
	private InstructionRegister instrReg;
	
	/**
	 * Register which points towards the address of the next instruction.
	 */
	private ProgramCounter progCounter;
	
	/**
	 * Accelerator register which is used for simple arithmetic tasks.
	 */
	private Register regA;
	
	/**
	 * Arithmetic and Logic unit for this CPU
	 */
	private ALU myALU;

	/**
	 * Needed to access input.
	 */
	private GUI pep8View;

	/**
	 * Used to tell the cpu if it is the first instruction and it needs to reset the program counter.
	 */
	private boolean isFirst;

	private Map<RegName, Register> registerMap;
	
	/**
	 * Initializes our CPU
	 */
	public CPU(GUI view) {
		instrReg = new InstructionRegister();
		progCounter = new ProgramCounter();
		regA = new Register();
		myALU = new ALU();
		pep8View = view;
		registerMap = Map.ofEntries(
                Map.entry(RegName.A, regA),
                Map.entry(RegName.PC, progCounter),
                Map.entry(RegName.INSTRUCTION, instrReg)
        );
	}

	/**
	 * The CPU reads and executes an instruction from memory based on the value of progCounter.
	 * @param m The memory that the CPU will be reading from.
	 */
	public boolean fetchExecute(Memory m) {
		if (isFirst) {
			progCounter.reset();
			isFirst = false;
		}

		MachineInstruction test = m.test();
		return execute(m, test, decode(m));
	}

	/**
	 * Decodes an instruction and returns an integer representing it.
	 * @param m The memory that the CPU will be reading from.
	 * @return Integer representing the instruction stored in memory.
	 */
	private int decode(Memory m) {
		instrReg.setSpecifier(m.getInstruction(progCounter.getReg()));
		progCounter.offset((byte) 1);
		instrReg.load(fuseBytes(m.getInstruction(progCounter.getReg()),
				m.getInstruction((short) (progCounter.getReg() + 1))));
		return checkInstruction(instrReg.getSpecifier());
	}
	
	/**
	 * Helper method for decode method, uses byte data to determine the instruction.
	 * @param byte1 The first half of our instruction specifier.
	 * @return Returns an integer indicating the instruction.
	 */
	private int checkInstruction(byte byte1) {
		int rtnVal = 99;
		if (byte1 == 0) {
			//stop execution
			isFirst = true;
			rtnVal = 0;
		} else if (byte1 < 0) {
			if (byte1 == (byte) -64) {
				//load operand into register A
				//immediate addressing mode
				rtnVal = 1;
			} else if (byte1 == (byte) -63) {
				//load operand into register A
				//direct addressing mode
				rtnVal = 2;
			} else if (byte1 == (byte) -31) {
				//store the A register to the location in operand
				rtnVal = 3;
			} else if (byte1 == (byte) -128) {
				//Subtract the operand to the A register
				//immediate addressing mode
				rtnVal = 6;
			} else if (byte1 == (byte) -127) {
				//Subtract the operand to the A register
				//direct addressing mode
				rtnVal = 7;
			}
		} else {
			if (byte1 == (byte) 112) {
				//Add the operand to the A register
				//immediate addressing mode
				rtnVal = 4;
			} else if (byte1 == (byte) 113) {
				//Add the operand to the A register
				//direct addressing mode
				rtnVal = 5;
			} else if (byte1 == (byte) 73) {
				//Char input to location of operand
				rtnVal = 8;
			} else if (byte1 == (byte) 80) {
				//Char output from operand
				//immediate addressing mode
				rtnVal = 9;
			} else if (byte1 == (byte) 81) {
				//Char output from operand
				//direct addressing mode
				rtnVal = 10;
			}
		}
		
		if (rtnVal == 99) {
			System.out.println("Error: Unknown Opcode!");
			System.out.println("Opcode: " + byte1);
		}
		return rtnVal;
	}

	/**
	 * Executes the instruction stored in memory.
	 * @param m
	 * @param instrType
	 * @return
	 */
	private boolean execute(Memory m, MachineInstruction mi, int instrType) {
		boolean end = false;
		if (instrType == 0) {
			//stop execution
			end = true;
		} else {
			try {
				byte operSpec1 = (byte) (((instrReg.getReg() & 0xFF00)) >> 8);
				byte operSpec2 = (byte) (instrReg.getReg() & 0xFF);
				//progCounter.offset((byte) 2);
				if (instrType == 1) {
					//load operand into register A
					//immediate addressing mode
//					short fuse = fuseBytes(operSpec1, operSpec2);
//					regA.load(fuse);

                    mi.execute(m, registerMap);
					
				} else if (instrType == 2) {
					//load operand into register A
					//direct addressing mode
					short fuse = this.calculateDirectAddress(operSpec1, operSpec2);
					byte retrieved = m.getInstruction(fuse);
					byte retrievedN = m.getInstruction((short) (fuse + 1));
					short retr = fuseBytes(retrieved, retrievedN);
					regA.load(retr);
						
				} else if (instrType == 3) {
					progCounter.offset((byte) 2);
					//store the A register to the location in operand
					short whole = regA.getReg();
					boolean[] wholeBool = this.toBoolArray(whole);
					boolean[] half1 = new boolean[8];
					boolean[] half2 = new boolean[8];
					System.arraycopy(wholeBool, 0, half1, 0, half1.length);
					System.arraycopy(wholeBool, 8, half2, 0, half2.length);
					byte sHalf1 = this.toByte(half1);
					byte sHalf2 = this.toByte(half2);
					short fuse = this.calculateDirectAddress(operSpec1, operSpec2);
					m.store(fuse, sHalf1);
					m.store((short) (fuse + 1), sHalf2);
					
				} else if (instrType == 4) {
					progCounter.offset((byte) 2);
					//Add the operand to the A register
					//immediate addressing mode
					short fuse = this.fuseBytes(operSpec1, operSpec2);
					regA.load(myALU.add(regA, fuse));
						
				} else if (instrType == 5) {
					progCounter.offset((byte) 2);
					//Add the operand to the A register
					//direct addressing mode
					short address = this.calculateDirectAddress(operSpec1, operSpec2);
			        short addressR = (short) (address + 1);
			        byte addressVal = m.getInstruction(address);
			        byte addressRVal = m.getInstruction(addressR);
			        short fuse = this.fuseBytes(addressVal, addressRVal);
					regA.load(myALU.add(regA, fuse));
						
				} else if (instrType == 6) {
					progCounter.offset((byte) 2);
					//Subtract the operand to the A register
					//immediate addressing mode
					short fuse = this.fuseBytes(operSpec1, operSpec2);
					regA.load(myALU.subtract(regA, fuse));
					
				} else if (instrType == 7) {
					progCounter.offset((byte) 2);
					//Subtract the operand to the A register
					//direct addressing mode
					short address = this.calculateDirectAddress(operSpec1, operSpec2);
			        short addressR = (short) (address + 1);
			        byte addressVal = m.getInstruction(address);
			        byte addressRVal = m.getInstruction(addressR);
			        short fuse = this.fuseBytes(addressVal, addressRVal);
					regA.load(myALU.subtract(regA, fuse));
					
				} else if (instrType == 8) {
					progCounter.offset((byte) 2);
					//Char input to location of operand
					byte scannedInput = (byte) pep8View.getBatchInput();
					short fuse = fuseBytes(operSpec1, operSpec2);
					m.store(fuse, scannedInput);

				} else if (instrType == 9) {
					progCounter.offset((byte) 2);
					//Char output from operand
					//immediate addressing mode
					short fuse = this.fuseBytes(operSpec1, operSpec2);
					char out = (char) fuse;
					pep8View.output(out);

					
				} else if (instrType == 10) {
					progCounter.offset((byte) 2);
					//Char output from operand
					//direct addressing mode
					short fuse = this.calculateDirectAddress(operSpec1, operSpec2);
					char out = (char) m.getInstruction(fuse);
					pep8View.output(out);
				}
			} catch (Exception E) {
				System.out.println("Error in Execution!");
			}
			
		}
		return end;
	}
	
	/**
	 * Helper function used to calculate the direct address.
	 * @param b1 Byte from the first operand.
	 * @param b2 Byte from the second operand.
	 * @return Short containing the desired address.
	 */
	private short calculateDirectAddress(byte b1, byte b2) {
		boolean[] oper1Array = this.toBoolArray(b1);
		boolean[] oper2Array = this.toBoolArray(b2);
		boolean[] fuseArray = new boolean[16];
		System.arraycopy(oper1Array, 0, fuseArray, 0, oper1Array.length);
		System.arraycopy(oper2Array, 0, fuseArray, 8, oper2Array.length);
		return this.toShort(fuseArray);
	}
	
	/**
	 * A helper function which converts a byte into an 8 bit boolean array.
	 * @param x The byte that we will manipulate.
	 * @return A boolean array equivalent to our byte value.
	 */
	private boolean[] toBoolArray(byte x) {
		boolean[] rtnArray = new boolean[8];
		byte[] twoPow = {64, 32, 16, 8, 4, 2, 1};
		for (int i = 0; i < rtnArray.length; i++) {
			if (i == 0) {
				if (x < 0) {
					rtnArray[i] = true;
					x = (byte) (x - 128);
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
	
	/**
	 * Helper function, Combine 2 bytes into a short
	 * @param b1 The first byte
	 * @param b2 The second byte
	 * @return Short resulting from placing these bytes back to back.
	 */
	private short fuseBytes(byte b1, byte b2) {
		boolean[] oper1Array = this.toBoolArray(b1);
		boolean[] oper2Array = this.toBoolArray(b2);
		boolean[] fuseArray = new boolean[16];
		System.arraycopy(oper1Array, 0, fuseArray, 0, 8);
		System.arraycopy(oper2Array, 0, fuseArray, 8, 8);
		return this.toShort(fuseArray);
	}
	
	/**
	 * A helper function which converts a short into a 16 bit boolean array.
	 * @param x The short that we will manipulate.
	 * @return A boolean array equivalent to our short value.
	 */
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
			System.out.println(rtnArray.length);
		return rtnArray;
	}
	
	/**
	 * A helper method which converts a boolean array to a short.
	 * @param boolArray The boolean array that we wish to convert.
	 * @return A short value representing the input boolean array.
	 */
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

	/**
	 * Method that returns the values in all of the cpu registers.
	 * @return short[] {InstRegSpec, InstrRegOperand, ProgCountReg, AccumReg}
	 */
	public short[] getRegisters() {
		return new short[] {instrReg.getSpecifier(), instrReg.getReg(),
				progCounter.getReg(), regA.getReg()};
	}

}
