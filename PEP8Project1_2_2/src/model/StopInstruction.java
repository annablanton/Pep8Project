package model;

import view.GUI;

import java.util.Map;

public class StopInstruction extends MachineInstruction {
    public StopInstruction() {
        super("00000000");
    }

    public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view) {
        return true;
    }
}
