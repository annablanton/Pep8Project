package model.instructions;

import controller.Pep8Sim;
import model.*;
import view.GUI;

import java.util.Map;

public class ASRInstruction extends MachineInstruction {
	public ASRInstruction(RegName r) {
		super("0001111", r);
	}

	public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view, Pep8Sim controller) {
		Register regA = regMap.get(RegName.A);

		if (getRegName() == RegName.A) {
			regA.load(alu.arithShiftRight(regA));
		} else {
			throw new UnsupportedOperationException("Index register not currently supported");
		}
		return false;
	}

	public static String getIdentifier() {
		return "0001111";
	}
}
