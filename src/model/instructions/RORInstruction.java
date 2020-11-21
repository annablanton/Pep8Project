package model.instructions;

import controller.Pep8Sim;
import model.ALU;
import model.Memory;
import model.RegName;
import model.Register;
import view.GUI;

import java.util.Map;

public class RORInstruction extends MachineInstruction {
	public RORInstruction(RegName r) {
		super("0010001", r);
	}

	public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view, Pep8Sim controller) {
		Register regA = regMap.get(RegName.A);

		if (getRegName() == RegName.A) {
			regA.load(alu.rotateRight(regA));
		} else {
			throw new UnsupportedOperationException("Index register not currently supported");
		}
		return false;
	}

	public static String getIdentifier() {
		return "0010001";
	}
}
