package model;

import view.GUI;

import java.util.Map;

public class ASRInstruction extends MachineInstruction {
    public ASRInstruction(RegName r) {
        super("0001111", r);
    }

    public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view) {
        Register regA = regMap.get(RegName.A);

        if (getRegName() == RegName.A) {
            regA.load(alu.arithShiftRight(regA));
        } else {
            throw new UnsupportedOperationException("Index register not currently supported");
        }
        return false;
    }
}
