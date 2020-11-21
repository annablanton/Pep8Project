package model;

import controller.Pep8Sim;
import view.GUI;

import java.util.Map;

public class BREQInstruction extends MachineInstruction{
    public BREQInstruction(AddressingMode a) {
        super("0000101", a);
    }

    @Override
    public boolean execute(Memory m, Map<RegName, Register> regMap, ALU alu, GUI view, Pep8Sim controller) {
        InstructionRegister instrReg = (InstructionRegister) regMap.get(RegName.INSTRUCTION);
        ProgramCounter progCounter = (ProgramCounter) regMap.get(RegName.PC);

        loadInstrOperand(m, instrReg, progCounter);
        if (getAddressingMode() == AddressingMode.IMMEDIATE) {
            if (alu.zFlagIsSet()) {
                progCounter.load(instrReg.getReg());
            }
        } else {
            throw new UnsupportedOperationException("Unsupported addressing mode");
        }
        return false;
    }

    public static String getIdentifier() {
        return "0000101";
    }
}
