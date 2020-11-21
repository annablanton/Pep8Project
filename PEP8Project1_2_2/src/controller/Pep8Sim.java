package controller;

import model.Machine;
import view.GUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Scanner;

/**
 * This class is designed to instantiate both the Machine and GUI and facilitate communication between them.
 * @author Group 6: Walter Kagel
 * @version 10/17/2020
 */
public class Pep8Sim {

    private GUI myPep8View;
    private Machine myMachine;

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

    /**
     * Performs actions based on the action of the user. Open, Load, Save, Start, etc.
     * @param e The event triggered.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String userinput = e.getActionCommand();
        if(userinput.equals("Open")||userinput.equals("Load")) {
            JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("ONLY .txt file", "txt");
            chooser.addChoosableFileFilter(restrict);
            int read = chooser.showSaveDialog(null);
            // if the user selects a file
            if (read == JFileChooser.APPROVE_OPTION){
                try {
                    BufferedReader in;
                    in = new BufferedReader(new FileReader(chooser.getSelectedFile()));
                    String line = in.readLine();
                    while (line != null) {
                        //ObjCode.setText(ObjCode.getText()+ line + "\n" ); //line + "\n"
                        myPep8View.setObjCode(myPep8View.getObjCodeText() + line + "\n");
                        line = in.readLine();
                    } in.close();
                } catch (IOException ex) {
                    throw new RuntimeException(
                            "creation is not possible.");
                }
            }
            else
                //ObjCode.setText("Please choose .txt file or type object Code here");
                myPep8View.setObjCode("Please choose .txt file or type object Code here");
        } else if(userinput.equals("Save")) {
            try {
                //wordsScanner = new Scanner("INPUT:"+ObjCode.getText().toString()+"\n OUTPUT:" + Outputtext.getText().toString());
                Scanner wordsScanner = new Scanner("INPUT:"+myPep8View.getObjCodeText()+"\n OUTPUT:" + myPep8View.getOutput());
                myPep8View.setWordsScanner(wordsScanner);
                final PrintWriter writer = new PrintWriter(new FileOutputStream("src/result.txt"));

                //while (wordsScanner.hasNextLine()) {
                while (myPep8View.getwordsScanner().hasNextLine()) {
                    writer.println(wordsScanner.nextLine());
                }
                writer.close();
            } catch (final FileNotFoundException exception) {
                System.out.println("Please check your words input file name \n" + exception);
            }
        } else if(userinput.equals("Start")||userinput.equals("Run CODE")||userinput.equals("Run")) {
            //firePropertyChange("Start", null, ObjCode.getText());
            firePropertyChange("Start", null, myPep8View.getObjCodeText());
        } else if(userinput.equals("New")) {
            //ObjCode.setText("");
            myPep8View.setObjCode("");
            //Accumulatorout1.setText("");
            myPep8View.setAccum1("");
            //Accumulatorout2.setText("");
            myPep8View.setAccum2("");
            //Instructionout1.setText("");
            myPep8View.setInstrOut("");
            //Operand1.setText("");
            myPep8View.setOp1("");
            //Operand2.setText("");
            myPep8View.setOp2("");
            //ProgramCounterout1.setText("");
            myPep8View.setProgOut1("");
            //ProgramCounterout2.setText("");
            myPep8View.setProgOut2("");
            //Memory.setText("");
            myPep8View.setMem("");
            //BatchIO.setText("");
            myPep8View.setBatchInput("");
            //Outputtext.setText("");
            myPep8View.clearOutputText();
            //batchIndex = -1;
            myPep8View.setBatchIndex();
//            setCbox(false);
//            setNbox(false);
//            setVbox(false);
//            setZbox(false);
            myPep8View.setCbox(false);
            myPep8View.setNbox(false);
            myPep8View.setVbox(false);
            myPep8View.setZbox(false);
            firePropertyChange("New", null, null);








        } else if(userinput.equals("Cut Object Code")) {
            myPep8View.getObjCode().cut();
        } else if(userinput.equals("Paste into Object Code")) {
            myPep8View.getObjCode().paste();;
        }
    }
}
