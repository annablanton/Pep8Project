package model;

import controller.Pep8Sim;
import model.instructions.*;
import view.GUI;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A class which represents a virtual CPU. Used by the Machine class to execute
 * instructions stored in memory.
 * 
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

	private Pep8Sim controller;

	/**
	 * Used to tell the cpu if it is the first instruction and it needs to reset the
	 * program counter.
	 */
	private boolean isFirst;

	private Map<RegName, Register> registerMap;

	private Map<String, Function<AddressingMode, MachineInstruction>> branchInstrMap;

	private Map<String, Function<RegName, MachineInstruction>> regInstrMap;

	private Map<String, Function<AddressingMode, MachineInstruction>> addrInstrMap;

	private Map<String, BiFunction<AddressingMode, RegName, MachineInstruction>> twoOperandMap;

	/**
	 * Initializes our CPU
	 */
	public CPU(GUI view, Pep8Sim ctlr) {
		instrReg = new InstructionRegister();
		progCounter = new ProgramCounter();
		regA = new Register();
		pep8View = view;
		myALU = new ALU(pep8View);
		controller = ctlr;
		registerMap = Map.ofEntries(Map.entry(RegName.A, regA), Map.entry(RegName.PC, progCounter),
				Map.entry(RegName.INSTRUCTION, instrReg));

		branchInstrMap = Map.ofEntries(
				Map.entry(UnconditionalBranchInstruction.getIdentifier(), UnconditionalBranchInstruction::new),
				Map.entry(BRLEInstruction.getIdentifier(), BRLEInstruction::new),
				Map.entry(BRLTInstruction.getIdentifier(), BRLTInstruction::new),
				Map.entry(BREQInstruction.getIdentifier(), BREQInstruction::new),
				Map.entry(BRNEInstruction.getIdentifier(), BRNEInstruction::new),
				Map.entry(BRGEInstruction.getIdentifier(), BRGEInstruction::new),
				Map.entry(BRGTInstruction.getIdentifier(), BRGTInstruction::new),
				Map.entry(BRVInstruction.getIdentifier(), BRVInstruction::new),
				Map.entry(BRCInstruction.getIdentifier(), BRCInstruction::new));

		regInstrMap = Map.ofEntries(Map.entry(NotInstruction.getIdentifier(), NotInstruction::new),
				Map.entry(NegInstruction.getIdentifier(), NegInstruction::new),
				Map.entry(ASLInstruction.getIdentifier(), ASLInstruction::new),
				Map.entry(ASRInstruction.getIdentifier(), ASRInstruction::new),
				Map.entry(ROLInstruction.getIdentifier(), ROLInstruction::new),
				Map.entry(RORInstruction.getIdentifier(), RORInstruction::new));

		addrInstrMap = Map.ofEntries(Map.entry(CharInputInstruction.getIdentifier(), CharInputInstruction::new),
				Map.entry(CharOutputInstruction.getIdentifier(), CharOutputInstruction::new));

		twoOperandMap = Map.ofEntries(Map.entry(AddInstruction.getIdentifier(), AddInstruction::new),
				Map.entry(SubtractInstruction.getIdentifier(), SubtractInstruction::new),
				Map.entry(AndInstruction.getIdentifier(), AddInstruction::new),
				Map.entry(OrInstruction.getIdentifier(), OrInstruction::new),
				Map.entry(CompareInstruction.getIdentifier(), CompareInstruction::new),
				Map.entry(LoadInstruction.getIdentifier(), LoadInstruction::new),
				Map.entry(LoadByteInstruction.getIdentifier(), LoadByteInstruction::new),
				Map.entry(StoreInstruction.getIdentifier(), StoreInstruction::new),
				Map.entry(StoreByteInstruction.getIdentifier(), StoreByteInstruction::new));
	}

	/**
	 * The CPU reads and executes an instruction from memory based on the value of
	 * progCounter.
	 * 
	 * @param m The memory that the CPU will be reading from.
	 */
	public boolean fetchExecute(Memory m) {
		if (isFirst) {
			progCounter.reset();
			isFirst = false;
		}

		instrReg.setSpecifier(m.getInstruction(progCounter.getReg()));
		progCounter.offset((byte) 1);
		MachineInstruction mi = decode(m);

		return execute(m, mi);
	}

	/**
	 * Decodes an instruction and returns an integer representing it.
	 * 
	 * @param m The memory that the CPU will be reading from.
	 * @return Integer representing the instruction stored in memory.
	 */
	private MachineInstruction decode(Memory m) {
		Binary binInstr = new Binary(instrReg.getSpecifier());
		String binString = binInstr.getVal();

		for (int i = 0; i < twoOperandMap.size(); i++) {
			if (twoOperandMap.containsKey(binString.substring(0, 4))) {
				return getTwoOperandInstr(twoOperandMap.get(binString.substring(0, 4)), binString.substring(4));
			}
		}

		for (int i = 0; i < addrInstrMap.size(); i++) {
			if (addrInstrMap.containsKey(binString.substring(0, 5))) {
				return getAddrModeInstr(addrInstrMap.get(binString.substring(0, 5)), binString.substring(5));
			}
		}

		for (int i = 0; i < regInstrMap.size(); i++) {
			if (regInstrMap.containsKey(binString.substring(0, 7))) {
				return getRegInstr(regInstrMap.get(binString.substring(0, 7)), binString.substring(7));
			}
		}

		for (int i = 0; i < branchInstrMap.size(); i++) {
			if (branchInstrMap.containsKey(binString.substring(0, 7))) {
				return getBranchInstr(branchInstrMap.get(binString.substring(0, 7)), binString.substring(7));
			}
		}

		if (binString.equals("00000000")) {
			return new StopInstruction();
		}

		throw new UnsupportedOperationException("Machine instruction unsupported");
	}

	/**
	 * Executes the instruction stored in memory.
	 * 
	 * @param m
	 * @return
	 */
	private boolean execute(Memory m, MachineInstruction mi) {
		boolean end = false;
		try {
			end = mi.execute(m, registerMap, myALU, pep8View, controller);
		} catch (Exception E) {
			System.out.println("Error in Execution!");
		}

		return end;
	}

	private MachineInstruction getTwoOperandInstr(BiFunction<AddressingMode, RegName, MachineInstruction> f, String s) {
		RegName r;
		if (s.startsWith("0")) {
			r = RegName.A;
		} else if (s.startsWith("1")) {
			r = RegName.INDEX;
		} else {
			throw new IllegalArgumentException("Non-binary string passed");
		}
		return switch (s.substring(1)) {
		case "000" -> f.apply(AddressingMode.IMMEDIATE, r);
		case "001" -> f.apply(AddressingMode.DIRECT, r);
		case "010" -> f.apply(AddressingMode.INDIRECT, r);
		default -> throw new UnsupportedOperationException("Addressing mode unsupported");
		};
	}

	private MachineInstruction getAddrModeInstr(Function<AddressingMode, MachineInstruction> f, String s) {
		return switch (s) {
		case "000" -> f.apply(AddressingMode.IMMEDIATE);
		case "001" -> f.apply(AddressingMode.DIRECT);
		case "010" -> f.apply(AddressingMode.INDIRECT);
		default -> throw new UnsupportedOperationException("Addressing mode unsupported");
		};
	}

	private MachineInstruction getBranchInstr(Function<AddressingMode, MachineInstruction> f, String s) {
		return switch (s) {
		case "0" -> f.apply(AddressingMode.IMMEDIATE);
		default -> throw new UnsupportedOperationException("Addressing mode unsupported");
		};
	}

	private MachineInstruction getRegInstr(Function<RegName, MachineInstruction> f, String s) {
		return switch (s) {
		case "0" -> f.apply(RegName.A);
		case "1" -> f.apply(RegName.INDEX);
		default -> throw new IllegalArgumentException("Non-binary string passed");
		};
	}

	/**
	 * A helper method which converts a boolean array to a short.
	 * 
	 * @param boolArray The boolean array that we wish to convert.
	 * @return A short value representing the input boolean array.
	 */
	private short toShort(boolean[] boolArray) {
		short rtnShort = 0;
		short[] twoPow = { 16384, 8192, 4096, 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1 };
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
	 * Method that returns the values in all of the cpu registers.
	 * 
	 * @return short[] {InstRegSpec, InstrRegOperand, ProgCountReg, AccumReg}
	 */
	public short[] getRegisters() {
		return new short[] { instrReg.getSpecifier(), instrReg.getReg(), progCounter.getReg(), regA.getReg() };
	}

}
