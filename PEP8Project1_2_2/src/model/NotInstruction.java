package model;

import view.GUI;

import java.util.Map;

public class NotInstruction extends MachineInstruction {
    public NotInstruction(RegName r) {super("0001100");}

    public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view) {
        Register regA = regMap.get(RegName.A);

        if (getRegName() == RegName.A) {
            regA.load(alu.not(regA));
        } else {
            throw new UnsupportedOperationException("Index register not currently supported");
        }
        return false;
    }
}
