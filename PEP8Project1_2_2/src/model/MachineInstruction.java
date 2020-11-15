package model;

import view.GUI;

import java.util.Map;

public abstract class MachineInstruction {
    private AddressingMode addressingMode;
    private RegName regName;
    String instrString;

    public MachineInstruction(String is) {
        instrString = is;
    }

    public MachineInstruction(String is, AddressingMode a) {
        instrString = is;
        addressingMode = a;
    }

    public MachineInstruction(String is, RegName r) {
        instrString = is;
        regName = r;
    }

    public MachineInstruction(String is, AddressingMode a, RegName r) {
        instrString = is;
        addressingMode = a;
        regName = r;
    }

    public AddressingMode getAddressingMode() {
        return addressingMode;
    }

    protected RegName getRegName() {
        return regName;
    }

    protected void loadInstrOperand(Memory m, InstructionRegister ir, ProgramCounter pc) {
        ir.load(m.getData(pc.getReg()));
        pc.offset((byte) 2);
    }

    public abstract boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view);

    /**
     * This is only here because Java does not support static abstract methods
     * @return error string
     */
    public static String getIdentifier() {
        return "Error: this should never be called";
    }
}