package model;

import view.GUI;

import java.util.Map;

public class StoreInstruction extends MachineInstruction {
    public StoreInstruction(AddressingMode a, RegName r) {
        super(a, r);
    }

    public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view) {
        InstructionRegister instrReg = (InstructionRegister) regMap.get(RegName.INSTRUCTION);
        ProgramCounter progCounter = (ProgramCounter) regMap.get(RegName.PC);
        Register regA = regMap.get(RegName.A);
        loadInstrOperand(m, instrReg, progCounter);
        if (getAddressingMode() == AddressingMode.IMMEDIATE) {
            throw new UnsupportedOperationException("Illegal operation (cannot use store instruction in immediate mode");
        } else if (getAddressingMode() == AddressingMode.DIRECT) {
            if (getRegName() == RegName.A) {
                m.storeData(instrReg.getReg(), regA.getReg());
            } else {
                throw new UnsupportedOperationException("Index register not yet supported");
            }
        } else if (getAddressingMode() == AddressingMode.INDIRECT) {
            short addr1 = instrReg.getReg();
            short addr2 = m.getData(addr1);
            if (getRegName() == RegName.A) {
                m.storeData(addr2, regA.getReg());
            } else {
                throw new UnsupportedOperationException("Index register not yet supported");
            }

        }


        return false;
    }
}
