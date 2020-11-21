package model;

import controller.Pep8Sim;
import view.GUI;

import java.util.Map;

public class OrInstruction extends MachineInstruction {
    public OrInstruction(AddressingMode a, RegName r) {
        super("1010", a, r);
    }

    public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view, Pep8Sim controller) {
        InstructionRegister instrReg = (InstructionRegister) regMap.get(RegName.INSTRUCTION);
        ProgramCounter progCounter = (ProgramCounter) regMap.get(RegName.PC);
        Register regA = regMap.get(RegName.A);

        loadInstrOperand(m, instrReg, progCounter);
        if (getAddressingMode() == AddressingMode.IMMEDIATE) {
            if (getRegName() == RegName.A) {
                regA.load(alu.or(regA, instrReg.getReg()));
            } else {
                throw new UnsupportedOperationException("Index register not yet supported");
            }
        } else if (getAddressingMode() == AddressingMode.DIRECT) {
            if (getRegName() == RegName.A) {
                regA.load(alu.or(regA, m.getData(instrReg.getReg())));
            } else {
                throw new UnsupportedOperationException("Index register not yet supported");
            }
        } else if (getAddressingMode() == AddressingMode.INDIRECT) {
            short addr1 = instrReg.getReg();
            short addr2 = m.getData(addr1);
            short data = m.getData(addr2);
            if (getRegName() == RegName.A) {
                regA.load(alu.or(regA, data));
            } else {
                throw new UnsupportedOperationException("Index register not yet supported");
            }
        } else {
            throw new UnsupportedOperationException("Addressing mode not supported");
        }

        return false;
    }

    public static String getIdentifier() {
        return "1010";
    }
}
