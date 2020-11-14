package model;

import view.GUI;

import java.util.Map;

public class AndInstruction extends MachineInstruction {
    public AndInstruction(AddressingMode a, RegName r) {
        super("1001", a, r);
    }

    public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view) {
        InstructionRegister instrReg = (InstructionRegister) regMap.get(RegName.INSTRUCTION);
        ProgramCounter progCounter = (ProgramCounter) regMap.get(RegName.PC);
        Register regA = regMap.get(RegName.A);

        loadInstrOperand(m, instrReg, progCounter);
        if (getAddressingMode() == AddressingMode.IMMEDIATE) {
            if (getRegName() == RegName.A) {
                regA.load(alu.and(regA, instrReg.getReg()));
            } else {
                throw new UnsupportedOperationException("Index register not yet supported");
            }
        } else if (getAddressingMode() == AddressingMode.DIRECT) {
            if (getRegName() == RegName.A) {
                regA.load(alu.and(regA, m.getData(instrReg.getReg())));
            } else {
                throw new UnsupportedOperationException("Index register not yet supported");
            }
        } else if (getAddressingMode() == AddressingMode.INDIRECT) {
            short addr1 = instrReg.getReg();
            short addr2 = m.getData(addr1);
            short data = m.getData(addr2);
            if (getRegName() == RegName.A) {
                regA.load(alu.and(regA, data));
            } else {
                throw new UnsupportedOperationException("Index register not yet supported");
            }
        } else {
            throw new UnsupportedOperationException("Addressing mode not supported");
        }

        return false;
    }
}
