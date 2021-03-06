package model.instructions;

import controller.Pep8Sim;
import model.*;
import view.GUI;

import java.util.Map;

public class CharOutputInstruction extends MachineInstruction {
	public CharOutputInstruction(AddressingMode a) {
		super("01010", a);
	}

	public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view, Pep8Sim controller) {
		InstructionRegister instrReg = (InstructionRegister) regMap.get(RegName.INSTRUCTION);
		ProgramCounter progCounter = (ProgramCounter) regMap.get(RegName.PC);
		loadInstrOperand(m, instrReg, progCounter);
		// System.out.println(instrReg.getReg());
		if (getAddressingMode() == AddressingMode.IMMEDIATE) {
			// System.out.println(instrReg.getByte());
			// System.out.println((char) ((byte) instrReg.getByte()));
			char out = (char) instrReg.getByte();
			view.output(out);
		} else if (getAddressingMode() == AddressingMode.DIRECT) {
			char out = m.getCharacter(instrReg.getReg());
			view.output(out);
		} else if (getAddressingMode() == AddressingMode.INDIRECT) {
			short addr1 = instrReg.getReg();
			short addr2 = m.getData(instrReg.getReg());
			char out = m.getCharacter(addr2);
			view.output(out);
		}
		return false;
	}

	public static String getIdentifier() {
		return "01010";
	}
}
