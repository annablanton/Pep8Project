package model;

import view.GUI;

import java.util.Map;

public class CharOutputInstruction extends MachineInstruction {
    public CharOutputInstruction(AddressingMode a) {
        super(a);
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
        } else if (getAddressingMode() == AddressingMode.INDIRECT) {
            short addr1 = instrReg.getReg();
            short addr2 = m.getData(instrReg.getReg());
            char out = m.getCharacter(addr2);
            view.output(out);
        }
        return false;
    }
}
