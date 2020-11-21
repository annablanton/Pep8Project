package model;

import controller.Pep8Sim;
import view.GUI;

import java.util.Map;

public class UnconditionalBranchInstruction extends MachineInstruction {
	public UnconditionalBranchInstruction(AddressingMode a) {
		super("0000010", a);
	}

	@Override
	public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view, Pep8Sim controller) {
		InstructionRegister instrReg = (InstructionRegister) regMap.get(RegName.INSTRUCTION);
		ProgramCounter progCounter = (ProgramCounter) regMap.get(RegName.PC);

		loadInstrOperand(m, instrReg, progCounter);
		if (getAddressingMode() == AddressingMode.IMMEDIATE) {
			progCounter.load(instrReg.getReg());
		} else {
			throw new UnsupportedOperationException("Unsupported addressing mode");
		}
		return false;
	}

	public static String getIdentifier() {
		return "0000010";
	}
}
