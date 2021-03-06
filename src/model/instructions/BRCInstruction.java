package model.instructions;

import controller.Pep8Sim;
import model.*;
import view.GUI;

import java.util.Map;

public class BRCInstruction extends MachineInstruction {
	public BRCInstruction(AddressingMode a) {
		super("0001010", a);
	}

	@Override
	public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view, Pep8Sim controller) {
		InstructionRegister instrReg = (InstructionRegister) regMap.get(RegName.INSTRUCTION);
		ProgramCounter progCounter = (ProgramCounter) regMap.get(RegName.PC);

		loadInstrOperand(m, instrReg, progCounter);
		if (getAddressingMode() == AddressingMode.IMMEDIATE) {
			if (alu.cFlagIsSet()) {
				// System.out.println("true");
				progCounter.load(instrReg.getReg());
			}
			// System.out.println("false");
		} else {
			throw new UnsupportedOperationException("Unsupported addressing mode");
		}
		return false;
	}

	public static String getIdentifier() {
		return "0001010";
	}
}
