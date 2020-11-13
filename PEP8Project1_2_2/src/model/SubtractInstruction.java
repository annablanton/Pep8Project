package model;

import view.GUI;

import java.util.Map;

public class SubtractInstruction extends MachineInstruction {
    public SubtractInstruction(AddressingMode a, RegName r) {
        super(a, r);
    }

    public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view) {
        InstructionRegister instrReg = (InstructionRegister) regMap.get(RegName.INSTRUCTION);
        ProgramCounter progCounter = (ProgramCounter) regMap.get(RegName.PC);
        Register regA = regMap.get(RegName.A);

        loadInstrOperand(m, instrReg, progCounter);
        if (getAddressingMode() == AddressingMode.IMMEDIATE) {
            regA.load(alu.subtract(regA, instrReg.getReg()));
        } else if (getAddressingMode() == AddressingMode.DIRECT) {
            regA.load(alu.subtract(regA, m.getData(instrReg.getReg())));
        }

        return false;
    }
}
