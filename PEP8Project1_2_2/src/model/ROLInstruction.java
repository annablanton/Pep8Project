package model;

import view.GUI;

import java.util.Map;

public class ROLInstruction extends MachineInstruction {
    public ROLInstruction(RegName r) {
        super("0010000", r);
    }

    @Override
    public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view) {
        Register regA = regMap.get(RegName.A);

        if (getRegName() == RegName.A) {
            regA.load(alu.rotateLeft(regA));
        } else {
            throw new UnsupportedOperationException("Index register not currently supported");
        }
        return false;
    }
}
