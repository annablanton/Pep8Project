package controller;

import model.Decode;
import model.Machine;
import view.GUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class is designed to instantiate both the Machine and GUI and facilitate
 * communication between them. Also contains buttons for GUI.
 * 
 * @author Group 6: Walter Kagel
 * @version 10/17/2020
 */
public class Pep8Sim implements ActionListener {

	private static GUI myPep8View;
	private static Machine myMachine;
	private JPanel upPanel = new JPanel();
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private Font myFont = new Font("Plain", Font.BOLD, 17);
	private JTextArea ObjCode;
	private JTextArea sourceTab;
	private JTextArea AsListing;
	private JPanel lineStartPanel;
	private JTabbedPane tabbedPane;
	private JTextArea traceTab;
	private JTabbedPane tabbedPane2;
	private JTextArea BatchIO;
	private JTextArea Terminal;
	private int batchIndex = -1;
	private Scanner wordsScanner;
	private JMenuBar menuBar;
	private JMenu File;
	private JMenuItem newMenu;
	private JMenuItem openMenu;
	private JMenu Edit;
	private JMenuItem cutMenu;
	private JMenuItem pasteMenu;
	private JMenu Build;
	private JMenuItem loadMenu;
	private JMenuItem runMenu;
	private Decode dec;

	/**
	 * Main method of the program.
	 * 
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		Pep8Sim p = new Pep8Sim();
		myPep8View = new GUI(p.getUpPanel(), p.getLineStartPanel(), p.getBatchIO(), p.getMenuBar());
		myMachine = new Machine(myPep8View, p);
		// Use a listener to tell when someone presses start, read in code, store it to
		// memory, and run code.
//        myPep8View.addPropertyChangeListener("Start", e -> {
//            String code = (String) e.getNewValue();
//            String[] codeArray = code.split(" ");
//            byte[] byteArray = new byte[codeArray.length];
//            try {
//                for (int i = 0; i < codeArray.length; i++) {
//                    byteArray[i] = (byte) (Integer.parseUnsignedInt(codeArray[i], 16));
//                }
//            } catch (Exception exception) {
//                JOptionPane.showMessageDialog(myPep8View, "Error in loading Object Code.");
//                return;
//            }
//            myMachine.store((short) 0, byteArray);
//            myMachine.run();
//        });
//        myPep8View.addPropertyChangeListener("New", e -> myMachine.reset());
	}

	private JMenuBar getMenuBar() {
		return menuBar;
	}

	private JTabbedPane getBatchIO() {
		return tabbedPane2;
	}

	private JPanel getLineStartPanel() {
		return lineStartPanel;
	}

	private JPanel getUpPanel() {
		return upPanel;
	}

	public Pep8Sim() {
		UpPanel();
		lineStartPanel();
		BatchIO();
		dec = new Decode();
	}


	private void UpPanel() {
		/* setting main UpPanel */
		upPanel = new JPanel();
		upPanel.setLayout(new GridLayout(1, 9));
		upPanel.setPreferredSize(new Dimension(screenSize.width * 3 / 4, screenSize.height * 3 / 50));
		/* setting buttons on main upPanel */
		final JButton newButton = new JButton("New");
		newButton.setFont(myFont);
		newButton.addActionListener(this);
		newButton.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 50));

		JButton startButton = new JButton("Start");
		startButton.setFont(myFont);
		startButton.addActionListener(this);
		startButton.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 50));

		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		saveButton.setFont(myFont);
		saveButton.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 50));

		JButton runCodeButton = new JButton("Run CODE");
		runCodeButton.addActionListener(this);
		runCodeButton.setFont(myFont);
		runCodeButton.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 50));

		JButton unDoButton = new JButton("Undo");
		unDoButton.setFont(myFont);
		unDoButton.setEnabled(false);
		unDoButton.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 50));

		JButton reDoButton = new JButton("Redo");
		reDoButton.setFont(myFont);
		reDoButton.setEnabled(false);
		reDoButton.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 50));

		/* Setting empty panels to fill empty areas of upPanel */
		JPanel emptyPanel = new JPanel();
		emptyPanel.setLayout(new GridLayout(1, 1));
		emptyPanel.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 50));
		JPanel emptyPanel2 = new JPanel();
		emptyPanel2.setLayout(new GridLayout(1, 1));
		emptyPanel2.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 50));
		JPanel emptyPanel3 = new JPanel();
		emptyPanel3.setLayout(new GridLayout(1, 1));
		emptyPanel3.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 50));

		/* Adding whole buttons in UpPanel */
		upPanel.add(newButton);
		upPanel.add(startButton);
		upPanel.add(saveButton);
		upPanel.add(runCodeButton);
		upPanel.add(unDoButton);
		upPanel.add(reDoButton);
		upPanel.add(emptyPanel);
		upPanel.add(emptyPanel2);
		upPanel.add(emptyPanel3);
	}

	/**
	 * Performs actions based on the action of the user. Open, Load, Save, Start,
	 * etc.
	 * 
	 * @param e The event triggered.
	 */
	public void actionPerformed(ActionEvent e) {
		String userinput = e.getActionCommand();
		if (userinput.equals("Open") || userinput.equals("Load")) {
			JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			FileNameExtensionFilter restrict = new FileNameExtensionFilter("ONLY .txt file", "txt");
			chooser.addChoosableFileFilter(restrict);
			int read = chooser.showSaveDialog(null);
			// if the user selects a file
			if (read == JFileChooser.APPROVE_OPTION) {
				try {
					BufferedReader in;
					in = new BufferedReader(new FileReader(chooser.getSelectedFile()));
					String line = in.readLine();
					while (line != null) {
						ObjCode.setText(ObjCode.getText() + line + "\n"); // line + "\n"
						ObjCode.setText(ObjCode.getText() + line + "\n");
						line = in.readLine();
					}
					in.close();
				} catch (IOException ex) {
					throw new RuntimeException("creation is not possible.");
				}
			} else
				// ObjCode.setText("Please choose .txt file or type object Code here");
				ObjCode.setText("Please choose .txt file or type object Code here");
		} else if (userinput.equals("Save")) {
			try {
				wordsScanner = new Scanner(
						"INPUT:" + ObjCode.getText().toString() + "\n OUTPUT:" + myPep8View.getOutput().toString());
				Scanner wordsScanner = new Scanner(
						"INPUT:" + ObjCode.getText() + "\n OUTPUT:" + myPep8View.getOutput());
				myPep8View.setWordsScanner(wordsScanner);
				final PrintWriter writer = new PrintWriter(new FileOutputStream("src/result.txt"));

				while (wordsScanner.hasNextLine()) {
					while (myPep8View.getwordsScanner().hasNextLine()) {
						writer.println(wordsScanner.nextLine());
					}
					writer.close();
				}
			} catch (final FileNotFoundException exception) {
				System.out.println("Please check your words input file name \n" + exception);
			}
		} else if (userinput.equals("Start") || userinput.equals("Run CODE") || userinput.equals("Run")) {
			String [] sourceCode = sourceTab.getText().split("\\r?\\n");
			String code = "";
			try {
				String unformattedCode = dec.assemblyToHex(sourceCode);
				code = dec.format(unformattedCode);
				ObjCode.setText(code);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String[] codeArray = code.split(" ");
			byte[] byteArray = new byte[codeArray.length];
			try {
				int i = 0;
//                    for (int i = 0; i < codeArray.length; i++) {
//                        byteArray[i] = (byte) (Integer.parseUnsignedInt(codeArray[i], 16));
//                    }
				while (i < codeArray.length && !codeArray[i].equals("zz")) {
					byteArray[i] = (byte) (Integer.parseUnsignedInt(codeArray[i], 16));
					i++;
				}
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(myPep8View, "Error in loading Object Code.");
				return;
			}
			myMachine.store((short) 0, byteArray);
			myMachine.run();
		} else if (userinput.equals("New")) {
			myMachine.reset();
		} else if (userinput.equals("Cut Object Code")) {
			ObjCode.selectAll();
			ObjCode.cut();
		} else if (userinput.equals("Paste into Object Code")) {
			ObjCode.paste();
		}
	}

	private void lineStartPanel() {
		/* setting main Line Start Panel */
		lineStartPanel = new JPanel();
		lineStartPanel.setLayout(new GridLayout(3, 1));
		lineStartPanel.setBorder(new TitledBorder(null, "CODE", TitledBorder.CENTER, TitledBorder.TOP, myFont, null));
		lineStartPanel.setPreferredSize(new Dimension(screenSize.width / 4, screenSize.height * 9 / 14));
		/* Create first sub-panel with Tabbedpane */
		tabbedPane = new JTabbedPane();
		tabbedPane.setSize(screenSize.width / 4, screenSize.height * 3 / 14);
		tabbedPane.setFont(myFont);

		/*
		 * Setting source code and trace tabs with text areas and add to main Line Start
		 * Panel
		 */
		sourceTab = new JTextArea("Please type your Souce Code here", screenSize.width / 4, screenSize.height * 3 / 14);
		sourceTab.setFont(myFont);
		traceTab = new JTextArea(null, screenSize.width / 4, screenSize.height * 3 / 14);
		traceTab.setEditable(false);
		tabbedPane.addTab("Code", sourceTab);
		tabbedPane.addTab("Trace", traceTab);
		lineStartPanel.add(tabbedPane);

		/* Setting object code text areas and add to main Line Start Panel */
		ObjCode = new JTextArea(null, screenSize.width / 4, screenSize.height * 3 / 14);
		ObjCode.setFont(myFont);
		ObjCode.setBorder(new TitledBorder(null, "Object Code", TitledBorder.CENTER, TitledBorder.TOP, myFont, null));
		ObjCode.setLineWrap(true);
		lineStartPanel.add(ObjCode);

		/* Setting Aslisting text areas and add to main Line Start Panel */
		AsListing = new JTextArea(null, screenSize.width / 4, screenSize.height * 3 / 14);
		AsListing.setFont(myFont);
		AsListing.setEditable(false);
		AsListing.setBorder(
				new TitledBorder(null, "Assembler Listing", TitledBorder.CENTER, TitledBorder.TOP, myFont, null));
		lineStartPanel.add(AsListing);
	}

	private void BatchIO() {
		tabbedPane2 = new JTabbedPane();
		tabbedPane2.setPreferredSize(new Dimension(screenSize.width / 4, screenSize.height * 3 / 14));
		tabbedPane2.setFont(myFont);
		BatchIO = new JTextArea(null, screenSize.width / 4, screenSize.height * 3 / 14);
		BatchIO.setFont(myFont);
		Terminal = new JTextArea(null, screenSize.width / 4, screenSize.height * 3 / 14);
		tabbedPane2.addTab("BatchI/O", BatchIO);
		tabbedPane2.addTab("Terminal I/O", Terminal);
	}

	/**
	 * This returns the characters stored in the BatchIO text area one character at
	 * a time.
	 * 
	 * @return The next character from batch input based upon how many times this
	 *         function has been called.
	 */
	public char getBatchInput() {
		batchIndex++;
		return (BatchIO.getText()).charAt(batchIndex);
	}

	/*
	 * setBatchInput function for testing purposes only
	 */
	public void setBatchInput(String s) {
		BatchIO.setText(s);
	}

	/**
	 * Constructor for Menu Bar with sub-menu.
	 */
	private void MenuBar() {
		/* Create file menu with sub-menu */
		menuBar = new JMenuBar();
		File = new JMenu("File");
		File.setFont(myFont);
		newMenu = new JMenuItem("New");
		newMenu.addActionListener(this);
		openMenu = new JMenuItem("Open");
		openMenu.addActionListener(this);
		newMenu.setFont(myFont);
		openMenu.setFont(myFont);
		File.add(newMenu);
		File.add(openMenu);

		/* Create edit menu with sub-menu */
		Edit = new JMenu("Edit");
		Edit.setFont(myFont);
		cutMenu = new JMenuItem("Cut Object Code");
		cutMenu.addActionListener(this);
		pasteMenu = new JMenuItem("Paste into Object Code");
		pasteMenu.addActionListener(this);
		cutMenu.setFont(myFont);
		pasteMenu.setFont(myFont);
		Edit.add(cutMenu);
		Edit.add(pasteMenu);

		/* Create build menu with sub-menu */
		Build = new JMenu("Build");
		Build.setFont(myFont);
		loadMenu = new JMenuItem("Load");
		loadMenu.addActionListener(this);
		runMenu = new JMenuItem("Run");
		runMenu.addActionListener(this);
		loadMenu.setFont(myFont);
		runMenu.setFont(myFont);
		Build.add(loadMenu);
		Build.add(runMenu);

		/* Adding all menu buttons to menubar */
		menuBar.add(File);
		menuBar.add(Build);
		menuBar.add(Edit);
	}

	public void reset() {
		ObjCode.setText("");
		BatchIO.setText("");
		sourceTab.setText("");
		AsListing.setText("");
		Terminal.setText("");
		batchIndex = -1;
		traceTab.setText("");
	}

	public void setBatchIndex() {
		batchIndex = -1;
	}
}
