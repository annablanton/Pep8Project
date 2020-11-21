package test;

import controller.Pep8Sim;
import model.Machine;
import org.junit.Before;
import org.junit.Test;
import view.GUI;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

public class MachineTest {
    GUI gui;
    Machine m;
    Pep8Sim ctlr;

    @Before
    public void setUp() {
        ctlr = new Pep8Sim();
        gui = new GUI(new JPanel(), new JPanel(), new JTabbedPane(), new JMenuBar());
        m = new Machine(gui, ctlr);
    }

    @Test
    public void testRun() {
        ctlr.setBatchInput("up");
        m.store((short) 0x0000, new byte[]{(byte) 0x49, (byte) 0x00, (byte) 0x0D, (byte) 0x49, (byte) 0x00,
                (byte) 0x0E, (byte) 0x51, (byte) 0x00, (byte) 0x0E, (byte) 0x51, (byte) 0x00, (byte) 0x0D});
        m.run();
        assertEquals("pu", gui.getOutput());
    }
}
