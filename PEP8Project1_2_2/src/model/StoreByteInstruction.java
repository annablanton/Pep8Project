package model;

import view.GUI;

import java.util.Map;

public class StoreByteInstruction extends MachineInstruction {
    public StoreByteInstruction(AddressingMode a, RegName r) {
        super("1111", a, r);
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
                //System.out.println(instrReg.getReg());
                m.storeByte(instrReg.getReg(), regA.getByte());
            } else {
                throw new UnsupportedOperationException("Index register not yet supported");
            }
        } else if (getAddressingMode() == AddressingMode.INDIRECT) {
            short addr1 = instrReg.getReg();
            short addr2 = m.getData(addr1);
            if (getRegName() == RegName.A) {
                m.storeByte(addr2, regA.getByte());
            } else {
                throw new UnsupportedOperationException("Index register not yet supported");
            }

        }


        return false;
    }

    public static String getIdentifier() {
        return "1111";
    }
}
