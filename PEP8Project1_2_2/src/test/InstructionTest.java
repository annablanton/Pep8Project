package test;

import model.*;
import org.junit.Before;
import org.junit.Test;
import view.GUI;

import javax.swing.*;
import java.util.Map;

import static org.junit.Assert.*;

public class InstructionTest {
    private static final short ADDR_1 = (short) 0xA235;
    private static final short ADDR_2 = (short) 0X2134;
    private static final short DATA = (short) 0X5678;
    private static final short REG_VAL = (short) 0x1AFF;

    MachineInstruction mi;
    Memory m;
    ProgramCounter pc;
    InstructionRegister ir;
    Register regA;
    ALU alu;
    GUI gui;
    Map<RegName, Register> rm;

    @Before
    public void setUp() {
        m = new Memory();
        pc = new ProgramCounter();
        pc.offset((byte) 1);
        ir = new InstructionRegister();
        regA = new Register();
        regA.load(REG_VAL);
        gui = new GUI(new JPanel());
        alu = new ALU(gui);
        rm = Map.ofEntries(
                Map.entry(RegName.PC, pc),
                Map.entry(RegName.INSTRUCTION, ir),
                Map.entry(RegName.A, regA)
        );
        m.storeData((short) 0x0001, ADDR_1);
        m.storeData((short) 0x0003, ADDR_1);
        m.storeData(ADDR_1, ADDR_2);
        m.storeData(ADDR_2, DATA);
    }

    @Test
    public void testLoadInstructionImmediate() {
        mi = new LoadInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals(rm.get(RegName.A).getReg(), ADDR_1);
    }

    @Test
    public void testLoadInstructionDirect() {
        mi = new LoadInstruction(AddressingMode.DIRECT, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals(rm.get(RegName.A).getReg(), ADDR_2);
    }

    @Test
    public void testLoadInstructionIndirect() {
        mi = new LoadInstruction(AddressingMode.INDIRECT, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals(rm.get(RegName.A).getReg(), DATA);
    }

    @Test
    public void testStoreInstructionDirect() {
        mi = new StoreInstruction(AddressingMode.DIRECT, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals(m.getData(ADDR_1), REG_VAL);
    }
    
    @Test
    public void testStoreInstructionIndirect() {
        mi = new StoreInstruction(AddressingMode.INDIRECT, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals(m.getData(ADDR_2), REG_VAL);
    }

    @Test
    public void testLoadByteInstructionImmediate() {
        mi = new LoadByteInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((byte) 0x35, regA.getByte());
    }

    @Test
    public void testLoadByteInstructionDirect() {
        mi = new LoadByteInstruction(AddressingMode.DIRECT, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((byte) 0x21, regA.getByte());
    }

    @Test
    public void testLoadByteInstructionIndirect() {
        mi = new LoadByteInstruction(AddressingMode.INDIRECT, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((byte) 0x56, regA.getByte());
    }

    @Test
    public void testStoreByteInstructionDirect() {
        mi = new StoreByteInstruction(AddressingMode.DIRECT, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((byte) 0xFF, m.getByte(ADDR_1));
    }

    @Test
    public void testStoreByteInstructionIndirect() {
        mi = new StoreByteInstruction(AddressingMode.INDIRECT, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((byte) 0xFF, m.getByte(ADDR_2));
    }

    @Test
    public void testAddInstructionImmediate() {
        mi = new AddInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) (ADDR_1 + REG_VAL), regA.getReg());
    }

    @Test
    public void testAddInstructionDirect() {
        mi = new AddInstruction(AddressingMode.DIRECT, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) (ADDR_2 + REG_VAL), regA.getReg());
    }

    @Test
    public void testAddInstructionIndirect() {
        mi = new AddInstruction(AddressingMode.INDIRECT, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) (DATA + REG_VAL), regA.getReg());
    }

    @Test
    public void testSubtractInstructionImmediate() {
        mi = new SubtractInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) (REG_VAL - ADDR_1), regA.getReg());
    }

    @Test
    public void testSubtractInstructionDirect() {
        mi = new SubtractInstruction(AddressingMode.DIRECT, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) (REG_VAL - ADDR_2), regA.getReg());
    }

    @Test
    public void testSubtractInstructionIndirect() {
        mi = new SubtractInstruction(AddressingMode.INDIRECT, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) (REG_VAL - DATA), regA.getReg());
    }

    @Test
    public void testStopInstruction() {
        mi = new StopInstruction();
        assertTrue(mi.execute(m, rm, alu, gui));
    }

    @Test
    public void testCharInputInstructionDirect() {
        gui.setBatchInput("a");
        mi = new CharInputInstruction(AddressingMode.DIRECT);
        mi.execute(m, rm, alu, gui);
        assertEquals('a', m.getCharacter(ADDR_1));
    }

    @Test
    public void testCharInputInstructionIndirect() {
        gui.setBatchInput("b");
        mi = new CharInputInstruction(AddressingMode.INDIRECT);
        mi.execute(m, rm, alu, gui);
        assertEquals('b', m.getCharacter(ADDR_2));
    }

    @Test
    public void testCharOutputInstructionImmediate() {
        mi = new CharOutputInstruction(AddressingMode.IMMEDIATE);
        mi.execute(m, rm, alu, gui);
        assertEquals("5", gui.getOutput());
    }

    @Test
    public void testCharOutputInstructionDirect() {
        mi = new CharOutputInstruction(AddressingMode.DIRECT);
        mi.execute(m, rm, alu, gui);
        assertEquals("!", gui.getOutput());
    }

    @Test
    public void testCharOutputInstructionIndirect() {
        mi = new CharOutputInstruction(AddressingMode.INDIRECT);
        mi.execute(m, rm, alu, gui);
        assertEquals("V", gui.getOutput());
    }

    @Test
    public void testAndInstructionImmediate() {
        mi = new AndInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) 0x0235, regA.getReg());
    }

    @Test
    public void testAndInstructionDirect() {
        mi = new AndInstruction(AddressingMode.DIRECT, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) 0x0034, regA.getReg());
    }

    @Test
    public void testAndInstructionIndirect() {
        mi = new AndInstruction(AddressingMode.INDIRECT, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) 0x1278, regA.getReg());
    }

    @Test
    public void testASLInstruction() {
        mi = new ASLInstruction(RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) 0x35FE, regA.getReg());
    }

    @Test
    public void testASRInstruction() {
        mi = new ASRInstruction(RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) 0x0D7F, regA.getReg());
    }

    @Test
    public void testBRCInstructionImmediateC1() {
        mi = new BRCInstruction(AddressingMode.IMMEDIATE);
        AddInstruction mi2 = new AddInstruction(AddressingMode.IMMEDIATE, RegName.A);
        m.storeData((short) 0x0001, (short) 0xFFFF);
        mi2.execute(m, rm, alu, gui);
        mi.execute(m, rm, alu, gui);
        assertEquals(ADDR_1, pc.getReg());
    }

    @Test
    public void testBRCInstructionImmediateC0() {
        mi = new BRCInstruction(AddressingMode.IMMEDIATE);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) 0x0003, pc.getReg());
    }

    @Test
    public void testBREQInstructionImmediateZ1() {
        m.storeData((short) 0x0001, REG_VAL);
        mi = new BREQInstruction(AddressingMode.IMMEDIATE);
        SubtractInstruction mi2 = new SubtractInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi2.execute(m, rm, alu, gui);
        mi.execute(m, rm, alu, gui);
        assertEquals(ADDR_1, pc.getReg());
    }

    @Test
    public void testBREQInstructionImmediateZ0() {
        mi = new BREQInstruction(AddressingMode.IMMEDIATE);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) 0x0003, pc.getReg());
    }

    @Test
    public void testBRGEInstructionImmediateN0() {
        mi = new BRGEInstruction(AddressingMode.IMMEDIATE);
        SubtractInstruction mi2 = new SubtractInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi2.execute(m, rm, alu, gui);
        mi.execute(m, rm, alu, gui);
        assertEquals(ADDR_1, pc.getReg());
    }

    @Test
    public void testBRGEInstructionImmediateN1() {
        mi = new BRGEInstruction(AddressingMode.IMMEDIATE);
        AddInstruction mi2 = new AddInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi2.execute(m, rm, alu, gui);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) 0x0005, pc.getReg());
    }

    @Test
    public void testBRGTInstructionImmediateN0Z0() {
        mi = new BRGTInstruction(AddressingMode.IMMEDIATE);
        SubtractInstruction mi2 = new SubtractInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi2.execute(m, rm, alu, gui);
        mi.execute(m, rm, alu, gui);
        assertEquals(ADDR_1, pc.getReg());
    }

    @Test
    public void testBRGTInstructionImmediateN1() {
        mi = new BRGTInstruction(AddressingMode.IMMEDIATE);
        AddInstruction mi2 = new AddInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi2.execute(m, rm, alu, gui);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) 0x0005, pc.getReg());
    }

    @Test
    public void testBRLEInstructionImmediateN1() {
        mi = new BRLEInstruction(AddressingMode.IMMEDIATE);
        AddInstruction mi2 = new AddInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi2.execute(m, rm, alu, gui);
        mi.execute(m, rm, alu, gui);
        assertEquals(ADDR_1, pc.getReg());
    }

    @Test
    public void testBRLEInstructionImmediateN0Z0() {
        mi = new BRLEInstruction(AddressingMode.IMMEDIATE);
        SubtractInstruction mi2 = new SubtractInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi2.execute(m, rm, alu, gui);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) 0x0005, pc.getReg());
    }

    @Test
    public void testBRLTInstructionImmediateN1() {
        mi = new BRLTInstruction(AddressingMode.IMMEDIATE);
        AddInstruction mi2 = new AddInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi2.execute(m, rm, alu, gui);
        mi.execute(m, rm, alu, gui);
        assertEquals(ADDR_1, pc.getReg());
    }

    @Test
    public void testBRLTInstructionImmediateN0() {
        mi = new BRLTInstruction(AddressingMode.IMMEDIATE);
        SubtractInstruction mi2 = new SubtractInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi2.execute(m, rm, alu, gui);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) 0x0005, pc.getReg());
    }

    @Test
    public void testBRNEInstructionImmediateZ0() {
        mi = new BRNEInstruction(AddressingMode.IMMEDIATE);
        SubtractInstruction mi2 = new SubtractInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi2.execute(m, rm, alu, gui);
        mi.execute(m, rm, alu, gui);
        assertEquals(ADDR_1, pc.getReg());
    }

    @Test
    public void testBRNEInstructionImmediateZ1() {
        mi = new BRNEInstruction(AddressingMode.IMMEDIATE);
        m.storeData((short) 0x0001, REG_VAL);
        SubtractInstruction mi2 = new SubtractInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi2.execute(m, rm, alu, gui);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) 0x0005, pc.getReg());
    }

    @Test
    public void testBRVInstructionImmediateV1() {
        m.storeData((short) 0x0001, (short) 0x7FFF);
        mi = new BRVInstruction(AddressingMode.IMMEDIATE);
        AddInstruction mi2 = new AddInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi2.execute(m, rm, alu, gui);
        mi.execute(m, rm, alu, gui);
        assertEquals(ADDR_1, pc.getReg());
    }

    @Test
    public void testBRVInstructionImmediateV0() {
        m.storeData((short) 0x0001, (short) 0x0001);
        mi = new BRVInstruction(AddressingMode.IMMEDIATE);
        AddInstruction mi2 = new AddInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi2.execute(m, rm, alu, gui);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) 0x0005, pc.getReg());
    }

    @Test
    public void testCompareInstructionImmediateEqual() {
        m.storeData((short) 0x0001, REG_VAL);
        mi = new CompareInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertTrue(alu.zFlagIsSet());
        assertFalse(alu.nFlagIsSet());
        assertFalse(alu.vFlagIsSet());
        assertTrue(alu.cFlagIsSet());
    }

    @Test
    public void testCompareInstructionImmediateLessThan() {
        m.storeData((short) 0x0001, (short) (REG_VAL + 1));
        mi = new CompareInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertFalse(alu.zFlagIsSet());
        assertTrue(alu.nFlagIsSet());
        assertFalse(alu.vFlagIsSet());
        assertFalse(alu.cFlagIsSet());
    }

    @Test
    public void testCompareInstructionImmediateGreaterThan() {
        m.storeData((short) 0x0001, (short) (REG_VAL - 1));
        mi = new CompareInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertFalse(alu.zFlagIsSet());
        assertFalse(alu.nFlagIsSet());
        assertFalse(alu.vFlagIsSet());
        assertTrue(alu.cFlagIsSet());
    }

    @Test
    public void testCompareInstructionImmediateSignedOverflow() {
        m.storeData((short) 0x0001, (short) (0x9AFF));
        mi = new CompareInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertFalse(alu.zFlagIsSet());
        assertTrue(alu.nFlagIsSet());
        assertTrue(alu.vFlagIsSet());
        assertFalse(alu.cFlagIsSet());
    }

    @Test
    public void testCompareInstructionDirect() {
        mi = new CompareInstruction(AddressingMode.DIRECT, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertFalse(alu.zFlagIsSet());
        assertTrue(alu.nFlagIsSet());
        assertFalse(alu.vFlagIsSet());
        assertFalse(alu.cFlagIsSet());
    }

    @Test
    public void testCompareInstructionIndirect() {
        mi = new CompareInstruction(AddressingMode.INDIRECT, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertFalse(alu.zFlagIsSet());
        assertTrue(alu.nFlagIsSet());
        assertFalse(alu.vFlagIsSet());
        assertFalse(alu.cFlagIsSet());
    }

    @Test
    public void testNegInstruction() {
        mi = new NegInstruction(RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) 0xE501, regA.getReg());
    }

    @Test
    public void testNotInstruction() {
        mi = new NotInstruction(RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) 0xE500, regA.getReg());
    }

    @Test
    public void testOrInstructionImmediate() {
        mi = new OrInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) 0xBAFF, regA.getReg());
    }

    @Test
    public void testOrInstructionDirect() {
        mi = new OrInstruction(AddressingMode.DIRECT, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) 0x3BFF, regA.getReg());
    }

    @Test
    public void testOrInstructionIndirect() {
        mi = new OrInstruction(AddressingMode.INDIRECT, RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) 0x5EFF, regA.getReg());
    }

    @Test
    public void testROLInstructionC0() {
        mi = new ROLInstruction(RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) 0x35FE, regA.getReg());
        assertFalse(alu.cFlagIsSet());
    }

    @Test
    public void testROLInstructionC1() {
        mi = new ROLInstruction(RegName.A);
        CompareInstruction mi2 = new CompareInstruction(AddressingMode.IMMEDIATE, RegName.A);
        m.storeData((short) 0x0001, (short) 0x0001);
        mi2.execute(m, rm, alu, gui);
        assertTrue(alu.cFlagIsSet());
        mi.execute(m, rm, alu, gui);
        assertEquals((short) 0x35FF, regA.getReg());
        assertFalse(alu.cFlagIsSet());
    }

    @Test
    public void testRORInstructionC0() {
        mi = new RORInstruction(RegName.A);
        mi.execute(m, rm, alu, gui);
        assertEquals((short) 0x0D7F, regA.getReg());
        assertTrue(alu.cFlagIsSet());
    }

    @Test
    public void testRORInstructionC1() {
        mi = new RORInstruction(RegName.A);
        m.storeData((short) 0x0001, (short) 0x0001);
        CompareInstruction mi2 = new CompareInstruction(AddressingMode.IMMEDIATE, RegName.A);
        mi2.execute(m, rm, alu, gui);
        assertTrue(alu.cFlagIsSet());
        mi.execute(m, rm, alu, gui);
        assertEquals((short) 0x8D7F, regA.getReg());
        assertTrue(alu.cFlagIsSet());
    }

    @Test
    public void testUnconditionalBranchInstructionImmediate() {
        mi = new UnconditionalBranchInstruction(AddressingMode.IMMEDIATE);
        mi.execute(m, rm, alu, gui);
        assertEquals(ADDR_1, pc.getReg());
    }
}
