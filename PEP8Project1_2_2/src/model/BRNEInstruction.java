package model;

import view.GUI;

import java.util.Map;

public class BRNEInstruction extends MachineInstruction {
    public BRNEInstruction(AddressingMode a) {
        super("0000110", a);
    }

    @Override
    public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view) {
        InstructionRegister instrReg = (InstructionRegister) regMap.get(RegName.INSTRUCTION);
        ProgramCounter progCounter = (ProgramCounter) regMap.get(RegName.PC);

        loadInstrOperand(m, instrReg, progCounter);
        if (getAddressingMode() == AddressingMode.IMMEDIATE) {
            if (!alu.zFlagIsSet()) {
                progCounter.load(instrReg.getReg());
            }
        } else {
            throw new UnsupportedOperationException("Unsupported addressing mode");
        }
        return false;
    }
}
