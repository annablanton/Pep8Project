package model;

import controller.Pep8Sim;
import view.GUI;

import java.util.Map;

public class ROLInstruction extends MachineInstruction {
	public ROLInstruction(RegName r) {
		super("0010000", r);
	}

	@Override
	public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view, Pep8Sim controller) {
		Register regA = regMap.get(RegName.A);

		if (getRegName() == RegName.A) {
			regA.load(alu.rotateLeft(regA));
		} else {
			throw new UnsupportedOperationException("Index register not currently supported");
		}
		return false;
	}

	public static String getIdentifier() {
		return "0010000";
	}
}
