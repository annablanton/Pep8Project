package model;

import controller.Pep8Sim;
import view.GUI;

import java.util.Map;

public class StopInstruction extends MachineInstruction {
	public StopInstruction() {
		super("00000000");
	}

	public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view, Pep8Sim controller) {
		return true;
	}

	public static String getIdentifier() {
		return "00000000";
	}
}
