package test;

import model.Machine;
import org.junit.Before;
import org.junit.Test;
import view.GUI;

import static org.junit.Assert.assertEquals;

public class MachineTest {
    GUI gui;
    Machine m;

    @Before
    public void setUp() {
        gui = new GUI();
        m = new Machine(gui);
    }

    @Test
    public void testRun() {
        gui.setBatchInput("up");
        m.store((short) 0x0000, new byte[]{(byte) 0x49, (byte) 0x00, (byte) 0x0D, (byte) 0x49, (byte) 0x00,
                (byte) 0x0E, (byte) 0x51, (byte) 0x00, (byte) 0x0E, (byte) 0x51, (byte) 0x00, (byte) 0x0D});
        m.run();
        assertEquals("pu", gui.getOutput());
    }
}
