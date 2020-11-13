package model;

import java.util.Map;

public abstract class MachineInstruction {
    private AddressingMode addressingMode;
    private RegName regName;

    public MachineInstruction() {

    }

    public MachineInstruction(AddressingMode a) {
        addressingMode = a;
    }

    public MachineInstruction(RegName r) {
        regName = r;
    }

    public MachineInstruction(AddressingMode a, RegName r) {
        addressingMode = a;
        regName = r;
    }

    public AddressingMode getAddressingMode() {
        return addressingMode;
    }

    protected void setAddressingMode(AddressingMode a) {
        addressingMode = a;
    }

    protected RegName getRegName() {
        return regName;
    }

    protected void setRegName(RegName r) {
        regName = r;
    }

    public abstract boolean execute(Memory m, Map<RegName, Register> regMap);
}