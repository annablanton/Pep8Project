package model;

import controller.Pep8Sim;
import view.GUI;

import java.util.Map;

public class LoadByteInstruction extends MachineInstruction {
    public LoadByteInstruction(AddressingMode a, RegName r) {
        super("1101", a, r);
    }

    public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view, Pep8Sim controller) {
        InstructionRegister instrReg = (InstructionRegister) regMap.get(RegName.INSTRUCTION);
        ProgramCounter progCounter = (ProgramCounter) regMap.get(RegName.PC);
        Register regA = regMap.get(RegName.A);
        loadInstrOperand(m, instrReg, progCounter);
        if (getAddressingMode() == AddressingMode.IMMEDIATE) {
            if (getRegName() == RegName.A) {
                regA.loadByte(instrReg.getByte());
            } else {
                throw new UnsupportedOperationException("Index register not yet supported");
            }
        }
        else if (getAddressingMode() == AddressingMode.DIRECT) {
            if (getRegName() == RegName.A) {
                regA.loadByte(m.getByte(instrReg.getReg()));
            } else {
                throw new UnsupportedOperationException("Index register not yet supported");
            }
        } else if (getAddressingMode() == AddressingMode.INDIRECT) {
            short addr1 = instrReg.getReg();
            short addr2 = m.getData(addr1);
            if (getRegName() == RegName.A) {
                regA.loadByte(m.getByte(addr2));
            } else {
                throw new UnsupportedOperationException("Index register not yet supported");
            }
        } else {
            throw new UnsupportedOperationException("Unsupported addressing mode");
        }
        return false;
    }

    public static String getIdentifier() {
        return "1101";
    }
}
