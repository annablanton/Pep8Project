package model;

import view.GUI;

import java.util.Map;

public class LoadInstruction extends MachineInstruction {
    public LoadInstruction(AddressingMode a, RegName r) {
        super(a, r);
    }

    public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view) {
        System.out.println("li call");
        InstructionRegister instrReg = (InstructionRegister) regMap.get(RegName.INSTRUCTION);
        ProgramCounter progCounter = (ProgramCounter) regMap.get(RegName.PC);
        Register regA = regMap.get(RegName.A);
        loadInstrOperand(m, instrReg, progCounter);
        if (getAddressingMode() == AddressingMode.IMMEDIATE) {
            if (getRegName() == RegName.A) {
                regA.load(instrReg.getReg());
            } else {
                throw new UnsupportedOperationException("Index register not yet supported");
            }
        }
        else if (getAddressingMode() == AddressingMode.DIRECT) {
            if (getRegName() == RegName.A) {
                regA.load(m.getData(instrReg.getReg()));
            } else {
                throw new UnsupportedOperationException("Index register not yet supported");
            }
        }
        return false;
    }
}