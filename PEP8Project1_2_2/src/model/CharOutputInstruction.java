package model;

import view.GUI;

import java.util.Map;

public class CharOutputInstruction extends MachineInstruction {
    public CharOutputInstruction(AddressingMode a, RegName r) {
        super(a, r);
    }

    public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view) {
        InstructionRegister instrReg = (InstructionRegister) regMap.get(RegName.INSTRUCTION);
        ProgramCounter progCounter = (ProgramCounter) regMap.get(RegName.PC);
        loadInstrOperand(m, instrReg, progCounter);
        if (getAddressingMode() == AddressingMode.IMMEDIATE) {
            char out = (char) instrReg.getReg();
            view.output(out);
        } else if (getAddressingMode() == AddressingMode.DIRECT) {
            char out = m.getCharacter(instrReg.getReg());
            view.output(out);
        }
        return false;
    }
}
