package model;

import controller.Pep8Sim;
import view.GUI;

import java.util.Map;

public class NotInstruction extends MachineInstruction {
    public NotInstruction(RegName r) {super("0001100", r);}

    public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view, Pep8Sim controller) {
        Register regA = regMap.get(RegName.A);

        if (getRegName() == RegName.A) {
            regA.load(alu.not(regA));
        } else {
            throw new UnsupportedOperationException("Index register not currently supported");
        }
        return false;
    }

    public static String getIdentifier() {
        return "0001100";
    }
}
