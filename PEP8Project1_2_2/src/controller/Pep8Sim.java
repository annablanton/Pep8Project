package controller;

import model.Machine;
import view.GUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

/**
 * This class is designed to instantiate both the Machine and GUI and facilitate communication between them.
 * @author Group 6: Walter Kagel
 * @version 10/17/2020
 */
public class Pep8Sim implements ActionListener {

    private static GUI myPep8View;
    private static Machine myMachine;
    private static JPanel upPanel = new JPanel();
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Font myFont = new Font("Plain", Font.BOLD, 17);

    /**
     * Main method of the program.
     * @param args Unused.
     */
    public static void main(String[] args) {
        Pep8Sim p = new Pep8Sim();
        myPep8View = new GUI(p.UpPanel());
        myMachine = new Machine(myPep8View);
        //Use a listener to tell when someone presses start, read in code, store it to memory, and run code.
        myPep8View.addPropertyChangeListener("Start", e -> {
            String code = (String) e.getNewValue();
            String[] codeArray = code.split(" ");
            byte[] byteArray = new byte[codeArray.length];
            try {
                for (int i = 0; i < codeArray.length; i++) {
                    byteArray[i] = (byte) (Integer.parseUnsignedInt(codeArray[i], 16));
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(myPep8View, "Error in loading Object Code.");
                return;
            }
            myMachine.store((short) 0, byteArray);
            myMachine.run();
        });
        myPep8View.addPropertyChangeListener("New", e -> myMachine.reset());
    }

    /**
     * This is only public for testing purposes
     */
    public JPanel UpPanel() {
        /*setting main UpPanel*/
        upPanel = new JPanel();
        upPanel.setLayout(new GridLayout(1,9));
        upPanel.setPreferredSize(new Dimension (screenSize.width*3/4,screenSize.height*3/50));
        /*setting buttons on main upPanel*/
        final JButton newButton = new JButton("New");
        newButton.setFont(myFont);
        newButton.addActionListener(this);
        newButton.setPreferredSize(new Dimension (screenSize.width/12,screenSize.height*3/50));

        JButton startButton = new JButton("Start");
        startButton.setFont(myFont);
        startButton.addActionListener(this);
        startButton.setPreferredSize(new Dimension (screenSize.width/12,screenSize.height*3/50));

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        saveButton.setFont(myFont);
        saveButton.setPreferredSize(new Dimension (screenSize.width/12,screenSize.height*3/50));

        JButton runCodeButton = new JButton("Run CODE");
        runCodeButton.addActionListener(this);
        runCodeButton.setFont(myFont);
        runCodeButton.setPreferredSize(new Dimension (screenSize.width/12,screenSize.height*3/50));

        JButton unDoButton = new JButton("Undo");
        unDoButton.setFont(myFont);
        unDoButton.setEnabled(false);
        unDoButton.setPreferredSize(new Dimension (screenSize.width/12,screenSize.height*3/50));

        JButton reDoButton = new JButton("Redo");
        reDoButton.setFont(myFont);
        reDoButton.setEnabled(false);
        reDoButton.setPreferredSize(new Dimension (screenSize.width/12,screenSize.height*3/50));

        /*Setting empty panels to fill empty areas of upPanel*/
        JPanel emptyPanel = new JPanel();
        emptyPanel.setLayout(new GridLayout(1,1));
        emptyPanel.setPreferredSize(new Dimension (screenSize.width/12,screenSize.height*3/50));
        JPanel emptyPanel2 = new JPanel();
        emptyPanel2.setLayout(new GridLayout(1,1));
        emptyPanel2.setPreferredSize(new Dimension (screenSize.width/12,screenSize.height*3/50));
        JPanel emptyPanel3 = new JPanel();
        emptyPanel3.setLayout(new GridLayout(1,1));
        emptyPanel3.setPreferredSize(new Dimension (screenSize.width/12,screenSize.height*3/50));

        /*Adding whole buttons in UpPanel*/
        upPanel.add(newButton);upPanel.add(startButton);upPanel.add(saveButton);
        upPanel.add(runCodeButton);upPanel.add(unDoButton);upPanel.add(reDoButton);
        upPanel.add(emptyPanel);upPanel.add(emptyPanel2);upPanel.add(emptyPanel3);
        return upPanel;
    }

    /**
     * Performs actions based on the action of the user. Open, Load, Save, Start, etc.
     * @param e The event triggered.
     */
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
            String code = (String) myPep8View.getObjCode().getText();
            String[] codeArray = code.split(" ");
            byte[] byteArray = new byte[codeArray.length];
            try {
                for (int i = 0; i < codeArray.length; i++) {
                    byteArray[i] = (byte) (Integer.parseUnsignedInt(codeArray[i], 16));
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(myPep8View, "Error in loading Object Code.");
                return;
            }
            myMachine.store((short) 0, byteArray);
            myMachine.run();
        } else if(userinput.equals("New")) {
//            //ObjCode.setText("");
//            myPep8View.setObjCode("");
//            //Accumulatorout1.setText("");
//            myPep8View.setAccum1("");
//            //Accumulatorout2.setText("");
//            myPep8View.setAccum2("");
//            //Instructionout1.setText("");
//            myPep8View.setInstrOut("");
//            //Operand1.setText("");
//            myPep8View.setOp1("");
//            //Operand2.setText("");
//            myPep8View.setOp2("");
//            //ProgramCounterout1.setText("");
//            myPep8View.setProgOut1("");
//            //ProgramCounterout2.setText("");
//            myPep8View.setProgOut2("");
//            //Memory.setText("");
//            myPep8View.setMem("");
//            //BatchIO.setText("");
//            myPep8View.setBatchInput("");
//            //Outputtext.setText("");
//            myPep8View.clearOutputText();
//            //batchIndex = -1;
//            myPep8View.setBatchIndex();
////            setCbox(false);
////            setNbox(false);
////            setVbox(false);
////            setZbox(false);
//            myPep8View.setCbox(false);
//            myPep8View.setNbox(false);
//            myPep8View.setVbox(false);
//            myPep8View.setZbox(false);
            myMachine.reset();








        } else if(userinput.equals("Cut Object Code")) {
            myPep8View.getObjCode().cut();
        } else if(userinput.equals("Paste into Object Code")) {
            myPep8View.getObjCode().paste();;
        }
    }
}
