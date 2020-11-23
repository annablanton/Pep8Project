package model.instructions;

import controller.Pep8Sim;
import model.*;
import view.GUI;

import java.util.Map;

public class CharInputInstruction extends MachineInstruction {
	public CharInputInstruction(AddressingMode a) {
		super("01001", a);
	}

	public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view, Pep8Sim controller) {
		InstructionRegister instrReg = (InstructionRegister) regMap.get(RegName.INSTRUCTION);
		ProgramCounter progCounter = (ProgramCounter) regMap.get(RegName.PC);

		loadInstrOperand(m, instrReg, progCounter);
		byte scannedInput = (byte) controller.getBatchInput();
		if (getAddressingMode() == AddressingMode.IMMEDIATE) {
			throw new UnsupportedOperationException(
					"Immediate addressing mode not supported by character input" + "instruction");
		} else if (getAddressingMode() == AddressingMode.DIRECT) {
			m.storeCharacter(instrReg.getReg(), scannedInput);
		} else if (getAddressingMode() == AddressingMode.INDIRECT) {
			short addr1 = instrReg.getReg();
			short addr2 = m.getData(addr1);
			m.storeCharacter(addr2, scannedInput);
		}
		return false;
	}

	public static String getIdentifier() {
		return "01001";
	}
}
