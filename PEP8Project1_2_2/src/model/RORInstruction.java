package model;

import view.GUI;

import java.util.Map;

public class RORInstruction extends MachineInstruction{
    public RORInstruction(RegName r) {
        super("0010001", r);
    }

    public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view) {
        Register regA = regMap.get(RegName.A);

        if (getRegName() == RegName.A) {
            regA.load(alu.rotateRight(regA));
        } else {
            throw new UnsupportedOperationException("Index register not currently supported");
        }
        return false;
    }
}
