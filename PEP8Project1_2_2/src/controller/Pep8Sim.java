package controller;

import model.Machine;
import view.GUI;

import javax.swing.*;

/**
 * This class is designed to instantiate both the Machine and GUI and facilitate communication between them.
 * @author Group 6: Walter Kagel
 * @version 10/17/2020
 */
public class Pep8Sim {

    /**
     * Main method of the program.
     * @param args Unused.
     */
    public static void main(String[] args) {
        GUI pep8View = new GUI();
        Machine pep8Machine = new Machine(pep8View);
        //Use a listener to tell when someone presses start, read in code, store it to memory, and run code.
        pep8View.addPropertyChangeListener("Start", e -> {
            String code = (String) e.getNewValue();
            String[] codeArray = code.split(" ");
            byte[] byteArray = new byte[codeArray.length];
            try {
                for (int i = 0; i < codeArray.length; i++) {
                    byteArray[i] = (byte) (Integer.parseUnsignedInt(codeArray[i], 16));
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(pep8View, "Error in loading Object Code.");
                return;
            }
            pep8Machine.store((short) 0, byteArray);
            pep8Machine.run();
        });
        pep8View.addPropertyChangeListener("New", e -> pep8Machine.reset());
    }
}
