package model;

import view.GUI;

import java.util.Map;

public class NegInstruction extends MachineInstruction {
    public NegInstruction(RegName r) {
        super("0001101", r);
    }

    public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view) {
        Register regA = regMap.get(RegName.A);

        if (getRegName() == RegName.A) {
            regA.load(alu.negate(regA));
        } else {
            throw new UnsupportedOperationException("Index register not currently supported");
        }
        return false;
    }

    public static String getIdentifier() {
        return "0001101";
    }
}
