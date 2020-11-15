package model;

import view.GUI;

import java.util.Map;

public class BRGEInstruction extends MachineInstruction {
    public BRGEInstruction(AddressingMode a) {
        super("0000111", a);
    }

    @Override
    public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view) {
        InstructionRegister instrReg = (InstructionRegister) regMap.get(RegName.INSTRUCTION);
        ProgramCounter progCounter = (ProgramCounter) regMap.get(RegName.PC);

        loadInstrOperand(m, instrReg, progCounter);
        if (getAddressingMode() == AddressingMode.IMMEDIATE) {
            if (!alu.nFlagIsSet()) {
                progCounter.load(instrReg.getReg());
            }
        } else {
            throw new UnsupportedOperationException("Unsupported addressing mode");
        }
        return false;
    }

    public static String getIdentifier() {
        return "0000111";
    }
}
