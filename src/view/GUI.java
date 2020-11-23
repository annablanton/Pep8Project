package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 * Class for GUI for pep8 virtual machine project. This class mainly includes
 * main frame, up-panel, line-start panel, line-end panel, menu bar and their
 * components. OPEN & Load button open the file chooser to read codes info from
 * .txt file start & run & run code button read object code and print output new
 * button close and reopen the application save button save the input and output
 * result into src/result.txt file Cut object code button is copying the object
 * code paste object code button is pasting copy codes into the object code.
 *
 * @author GROUP 6 Taehong Kim
 * @version 10/18/2020
 */
public class GUI extends JFrame {
	/** SerialVersion for GUI class. **/
	private static final long serialVersionUID = 1L;
	/** Setting Object code text area. **/
//	private JTextArea ObjCode;
	/** Setting Line Start Panel. **/
//	private JPanel lineStartPanel = new JPanel();
	/** Setting Center main Panel. **/
	private JPanel CenterPanel;
	/** Setting font type for titles. **/
	private final Font myFont = new Font("Plain", Font.BOLD, 17);
	/** Setting font types for inside of text area. **/
	private final Font myFont2 = new Font("Plain", Font.CENTER_BASELINE, 9);
	/** Setting CPU panel. **/
	private JPanel CpuPanel;
	/** Setting top of CPU panel. **/
	private JPanel CpupanelTop;
	/** Setting Accumulator text area. **/
	private JTextField Accumulatorout1;
	/** Setting Accumulator second text area. **/
	private JTextField Accumulatorout2;
	/** Setting program counter text area. **/
	private JTextField ProgramCounterout1;
	/** Setting program counter text second area. **/
	private JTextField ProgramCounterout2;
	/** Setting Instruction text area. **/
	private JTextField Instructionout1;
	/** Setting Tabbed panel for batch and terminal. **/
//	private JTabbedPane tabbedPane2;
	/** Setting Batch IO text area. **/
//	private JTextArea BatchIO;
	/** Setting Terminal text area. **/
	private JTextArea Terminal;
	/** Setting Output text area. **/
	private JTextArea Outputtext;
	/** Setting Line-End main Panel. **/
	private JPanel LineEndPanel;
	/** Setting Memory Panel. **/
	private JTextArea Memory;
	/** Setting main Menu bar. **/
//	private JMenuBar menuBar;
	/** Setting sub-menw for new button. **/
	private JMenuItem newMenu;
	//	private JMenu File;
	//	private JMenu Edit;
	//	private JMenuItem cutMenu;
	//	private JMenuItem pasteMenu;
	//	private JMenu Build;
	//	private JMenuItem loadMenu;
	//	private JMenuItem runMenu;
	//	private JMenuItem openMenu;
	/** Setting tab panel. **/
	private JTabbedPane tabbedPane;
	/** Setting source code tab with text area. **/
	private JTextArea sourceTab;
	//	private JTextArea traceTab;
	//	private JTextArea AsListing;
	/** Setting down of cpu panel. **/
	private JPanel Cpupaneldown;
	/** Setting Operand text field1. **/
	private JTextField Operand1;
	/** Setting Operand text field2. **/
	private JTextField Operand2;
	/** Setting words scanner to scanner result. **/
	private Scanner wordsScanner;
	/** Setting screensize to calculate size of application **/

	private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	/** Tells which character of BatchIO to send **/
	private JCheckBox Nbox;
	private JCheckBox Zbox;
	private JCheckBox Vbox;
	private JCheckBox Cbox;
//	private int batchIndex = -1;
	/**
	 * Main method to run CUI methods.
	 * 
	 * @author GROUP6 taehong Kim
	 * @version 10/18/2020
	 */
//	public static void main(String []args) {
//		new GUI();
//	}

	/**
	 * Constructor Virtual simulator GUI with frame.
	 */
	public GUI(JPanel upPanel, JPanel lineStartPanel, JTabbedPane tabbedPane2, JMenuBar menuBar) {
		/** Setting Main frame. **/
		JFrame frame = new JFrame("Virtual Simulator");
		frame.setSize(screenSize.width * 3 / 4, screenSize.height * 3 / 4); // frame size
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
//		MenuBar();
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(upPanel, BorderLayout.PAGE_START);
//		LineStartPanel();
		frame.getContentPane().add(lineStartPanel, BorderLayout.LINE_START);
		CentralPanel(tabbedPane2);
		frame.getContentPane().add(CenterPanel, BorderLayout.CENTER);
		LineEndPanel();
		frame.getContentPane().add(LineEndPanel, BorderLayout.LINE_END);
		frame.pack();

	}

	/**
	 * Constructor Up-Panel with buttons.
	 */
//	private void UpPanel() {
//		/*setting main UpPanel*/
//		upPanel = new JPanel();
//		upPanel.setLayout(new GridLayout(1,9));
//		upPanel.setPreferredSize(new Dimension (screenSize.width*3/4,screenSize.height*3/50));
//		/*setting buttons on main upPanel*/
//		final JButton newButton = new JButton("New");
//		newButton.setFont(myFont);
//		newButton.addActionListener(this);
//		newButton.setPreferredSize(new Dimension (screenSize.width/12,screenSize.height*3/50));
//
//		JButton startButton = new JButton("Start");
//		startButton.setFont(myFont);
//		startButton.addActionListener(this);
//		startButton.setPreferredSize(new Dimension (screenSize.width/12,screenSize.height*3/50));
//
//		JButton saveButton = new JButton("Save");
//		saveButton.addActionListener(this);
//		saveButton.setFont(myFont);
//		saveButton.setPreferredSize(new Dimension (screenSize.width/12,screenSize.height*3/50));
//
//		JButton runCodeButton = new JButton("Run CODE");
//		runCodeButton.addActionListener(this);
//		runCodeButton.setFont(myFont);
//		runCodeButton.setPreferredSize(new Dimension (screenSize.width/12,screenSize.height*3/50));
//
//		JButton unDoButton = new JButton("Undo");
//		unDoButton.setFont(myFont);
//		unDoButton.setEnabled(false);
//		unDoButton.setPreferredSize(new Dimension (screenSize.width/12,screenSize.height*3/50));
//
//		JButton reDoButton = new JButton("Redo");
//		reDoButton.setFont(myFont);
//		reDoButton.setEnabled(false);
//		reDoButton.setPreferredSize(new Dimension (screenSize.width/12,screenSize.height*3/50));
//
//		/*Setting empty panels to fill empty areas of upPanel*/
//		JPanel emptyPanel = new JPanel();
//		emptyPanel.setLayout(new GridLayout(1,1));
//		emptyPanel.setPreferredSize(new Dimension (screenSize.width/12,screenSize.height*3/50));
//		JPanel emptyPanel2 = new JPanel();
//		emptyPanel2.setLayout(new GridLayout(1,1));
//		emptyPanel2.setPreferredSize(new Dimension (screenSize.width/12,screenSize.height*3/50));
//		JPanel emptyPanel3 = new JPanel();
//		emptyPanel3.setLayout(new GridLayout(1,1));
//		emptyPanel3.setPreferredSize(new Dimension (screenSize.width/12,screenSize.height*3/50));
//
//		/*Adding whole buttons in UpPanel*/
//		upPanel.add(newButton);upPanel.add(startButton);upPanel.add(saveButton);
//		upPanel.add(runCodeButton);upPanel.add(unDoButton);upPanel.add(reDoButton);
//		upPanel.add(emptyPanel);upPanel.add(emptyPanel2);upPanel.add(emptyPanel3);
//	}

	/**
	 * Constructor for line start Panel with components small panels.
	 */
//	private void LineStartPanel () {
//		/*setting main Line Start Panel*/
//		lineStartPanel = new JPanel();
//		lineStartPanel.setLayout(new GridLayout(3,1));
//		lineStartPanel.setBorder(new TitledBorder(null, "CODE", TitledBorder.CENTER, TitledBorder.TOP, myFont, null));
//		lineStartPanel.setPreferredSize(new Dimension(screenSize.width/4,screenSize.height*9/14));
//		/*Create first sub-panel with Tabbedpane*/
//		tabbedPane = new JTabbedPane();
//		tabbedPane.setSize(screenSize.width/4,screenSize.height*3/14);
//		tabbedPane.setFont(myFont);
//
//		/*Setting source code and trace tabs with text areas and add to main Line Start Panel*/
//		sourceTab = new JTextArea("Please type your Souce Code here",screenSize.width/4,screenSize.height*3/14);
//		sourceTab.setFont(myFont);
//		traceTab = new JTextArea(null,screenSize.width/4,screenSize.height*3/14);
//		traceTab.setEditable(false);
//		tabbedPane.addTab("Code", sourceTab); tabbedPane.addTab("Trace", traceTab);
//		lineStartPanel.add(tabbedPane);
//
//		/*Setting object code text areas and add to main Line Start Panel*/
//		ObjCode = new JTextArea(null,screenSize.width/4,screenSize.height*3/14);
//		ObjCode.setFont(myFont);
//		ObjCode.setBorder(new TitledBorder(null, "Object Code", TitledBorder.CENTER, TitledBorder.TOP, myFont, null));
//		ObjCode.setLineWrap(true);
//		lineStartPanel.add(ObjCode);
//
//		/*Setting Aslisting text areas and add to main Line Start Panel*/
//		AsListing = new JTextArea(null,screenSize.width/4,screenSize.height*3/14);
//		AsListing.setFont(myFont);
//		AsListing.setEditable(false);
//		AsListing.setBorder(new TitledBorder(null, "Assembler Listing", TitledBorder.CENTER, TitledBorder.TOP, myFont, null));
//		lineStartPanel.add(AsListing);
//	}

	/**
	 * Constructor for main Central Panel with component small panels.
	 */
	private void CentralPanel(JTabbedPane tabbedPane2) {
		/* setting main Center Panel */
		CenterPanel = new JPanel();
		CenterPanel.setLayout(new GridLayout(3, 1));
		CenterPanel.setBorder(new TitledBorder(null, "CPU", TitledBorder.CENTER, TitledBorder.TOP, myFont, null));
		CenterPanel.setPreferredSize(new Dimension(screenSize.width / 4, screenSize.height * 9 / 14));
		/* setting cpu Center sub Panel */
		CpuPanel = new JPanel();
		CpuPanel.setLayout(new GridLayout(2, 1));
		CpuPanel.setBorder(new TitledBorder(null, null, TitledBorder.CENTER, TitledBorder.TOP, myFont, null));
		CpuPanel.setPreferredSize(new Dimension(screenSize.width / 4, screenSize.height * 3 / 14));
		/* setting top Panel */
		CpupanelTop = new JPanel();
		CpupanelTop.setLayout(new GridLayout(1, 8));
		CpupanelTop.setSize(screenSize.width / 4, screenSize.height * 1 / 112);
		// setPreferredSize(new Dimension(screenSize.width/4,screenSize.height*1/112));
		/* Calling components for buttons, labels, and text areas. */
		CpuChecker();
		downCpuPanel();
		AccumulatorPanel();
		IndexRegister();
		StackPointer();
		ProgramCounterPanel();
		InstructionPanel();
		OperandSpecifier();
		Operand();
		BatchIO(tabbedPane2);
	}

	/**
	 * Constructor N,Z,V,C checker with labels for cpu panel.
	 */
	private void CpuChecker() {
		/* adding check box with labels N. */
		/** Setting N check button. **/
		JLabel nmark = new JLabel("N");
		nmark.setFont(myFont);
		nmark.setSize(new Dimension(screenSize.width * 1 / 112, screenSize.height * 1 / 112));
		CpupanelTop.add(nmark);
		Nbox = new JCheckBox();
		Nbox.setSize(new Dimension(screenSize.width * 1 / 112, screenSize.height * 1 / 112));
		Nbox.setEnabled(false);
		CpupanelTop.add(Nbox);

		/* adding check box with labels Z. */
		/** Setting Z check button. **/
		JLabel zmark = new JLabel("Z");
		zmark.setFont(myFont);
		zmark.setSize(new Dimension(screenSize.width * 1 / 112, screenSize.height * 1 / 112));
		CpupanelTop.add(zmark);
		Zbox = new JCheckBox();
		Zbox.setSize(new Dimension(screenSize.width * 1 / 112, screenSize.height * 1 / 112));
		Zbox.setEnabled(false);
		CpupanelTop.add(Zbox);

		/* adding check box with labels V. */
		/** Setting V check button. **/
		JLabel vmark = new JLabel("V");
		vmark.setFont(myFont);
		vmark.setSize(new Dimension(screenSize.width * 1 / 112, screenSize.height * 1 / 112));
		CpupanelTop.add(vmark);
		Vbox = new JCheckBox();
		Vbox.setSize(new Dimension(screenSize.width * 1 / 112, screenSize.height * 1 / 112));
		Vbox.setEnabled(false);
		CpupanelTop.add(Vbox);

		/* adding check box with labels C. */
		/** Setting C check button. **/
		JLabel cmark = new JLabel("C");
		cmark.setFont(myFont);
		cmark.setSize(new Dimension(screenSize.width * 1 / 112, screenSize.height * 1 / 112));
		CpupanelTop.add(cmark);
		Cbox = new JCheckBox();
		Cbox.setSize(new Dimension(screenSize.width * 1 / 112, screenSize.height * 1 / 112));
		Cbox.setEnabled(false);
		CpupanelTop.add(Cbox);
		CpuPanel.add(CpupanelTop, BorderLayout.NORTH);
	}

	/**
	 * Constructor for down of cpu penl in main cpu penl.
	 */
	private void downCpuPanel() {
		/* Setting down of CPU panel */
		Cpupaneldown = new JPanel();
		Cpupaneldown.setLayout(new GridLayout(7, 3));
		Cpupaneldown.setPreferredSize(new Dimension(screenSize.width / 4, screenSize.height * 3 / 16));
		CpuPanel.add(Cpupaneldown, BorderLayout.CENTER);
	}

	/**
	 * Constructor for Accumulator labels, text areas.
	 */
	private void AccumulatorPanel() {
		/** Setting Accumulator label. **/
		JLabel accumulator = new JLabel("Accumulator");
		accumulator.setFont(myFont2);
		accumulator.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 112));
		Cpupaneldown.add(accumulator);
		Accumulatorout1 = new JTextField();
		Accumulatorout1.setEditable(false);
		Accumulatorout1.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 112));
		Accumulatorout1.setFont(myFont2);
		Cpupaneldown.add(Accumulatorout1);
		Accumulatorout2 = new JTextField();
		Accumulatorout2.setEditable(false);
		Accumulatorout2.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 112));
		Accumulatorout2.setFont(myFont2);
		Cpupaneldown.add(Accumulatorout2);
	}

	/**
	 * Constructor for ProgramCounter labels, text areas.
	 */
	private void ProgramCounterPanel() {
		/** Setting Program Counter Label. **/
		JLabel programCounter = new JLabel("ProgramCounter");
		programCounter.setFont(myFont2);
		programCounter.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 112));
		Cpupaneldown.add(programCounter);
		ProgramCounterout1 = new JTextField();
		ProgramCounterout1.setEditable(false);
		ProgramCounterout1.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 112));
		ProgramCounterout1.setFont(myFont2);
		Cpupaneldown.add(ProgramCounterout1);
		ProgramCounterout2 = new JTextField();
		ProgramCounterout2.setEditable(false);
		ProgramCounterout2.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 112));
		ProgramCounterout2.setFont(myFont2);
		Cpupaneldown.add(ProgramCounterout2);
	}

	/**
	 * Constructor for StackPointer labels, text areas.
	 */
	private void StackPointer() {
		/** Setting Stack pointer labels. **/
		JLabel stackPointer = new JLabel("StackPointer");
		stackPointer.setFont(myFont2);
		stackPointer.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 112));
		Cpupaneldown.add(stackPointer);
		/** Setting Stack pointer text areas1. **/
		JTextField stackPointer1 = new JTextField();
		stackPointer1.setEditable(false);
		stackPointer1.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 112));
		stackPointer1.setFont(myFont2);
		Cpupaneldown.add(stackPointer1);
		/** Setting Stack pointer text areas1. **/
		JTextField stackPointer2 = new JTextField();
		stackPointer2.setEditable(false);
		stackPointer2.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 112));
		stackPointer2.setFont(myFont2);
		Cpupaneldown.add(stackPointer2);
	}

	/**
	 * Constructor for Index register labels, text areas.
	 */
	private void IndexRegister() {
		/** Setting Index Register Label. **/
		JLabel indexRegister = new JLabel("IndexRegister");
		indexRegister.setFont(myFont2);
		indexRegister.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 112));
		Cpupaneldown.add(indexRegister);
		/** Setting Index Register text areas1. **/
		JTextField indexRegister1 = new JTextField();
		indexRegister1.setEditable(false);
		indexRegister1.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 112));
		indexRegister1.setFont(myFont2);
		Cpupaneldown.add(indexRegister1);
		/** Setting Index Register text areas2. **/
		JTextField indexRegister2 = new JTextField();
		indexRegister2.setEditable(false);
		indexRegister2.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 112));
		indexRegister2.setFont(myFont2);
		Cpupaneldown.add(indexRegister2);
	}

	/**
	 * Constructor for Operand labels, text areas.
	 */
	private void Operand() {
		/** Setting Operand label. **/
		JLabel operand = new JLabel("(Operand)");
		operand.setFont(myFont2);
		operand.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 112));
		Cpupaneldown.add(operand);
		Operand1 = new JTextField();
		Operand1.setEditable(false);
		Operand1.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 112));
		Operand1.setFont(myFont2);
		Cpupaneldown.add(Operand1);
		Operand2 = new JTextField();
		Operand2.setEditable(false);
		Operand2.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 112));
		Operand2.setFont(myFont2);
		Cpupaneldown.add(Operand2);
	}

	/**
	 * Constructor for Operand specifier labels, text areas.
	 */
	private void OperandSpecifier() {
		/** Setting Operand specifier label. **/
		JLabel operandSpecifier = new JLabel("Operand Specifier");
		operandSpecifier.setFont(myFont2);
		operandSpecifier.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 112));
		Cpupaneldown.add(operandSpecifier);
		/** Setting Operand specifier text field1. **/
		JTextField operandSpecifier1 = new JTextField();
		operandSpecifier1.setEditable(false);
		operandSpecifier1.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 112));
		operandSpecifier1.setFont(myFont2);
		Cpupaneldown.add(operandSpecifier1);
		/** Setting Operand specifier text field2. **/
		JTextField operandSpecifier2 = new JTextField();
		operandSpecifier2.setEditable(false);
		operandSpecifier2.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 112));
		operandSpecifier2.setFont(myFont2);
		Cpupaneldown.add(operandSpecifier2);
	}

	/**
	 * Constructor for InstructionPanel labels, text areas.
	 */
	private void InstructionPanel() {
		/** Setting Instruction Label. **/
		JLabel instruction = new JLabel("Instruction Specifier");
		instruction.setFont(myFont2);
		instruction.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 112));
		Cpupaneldown.add(instruction);
		Instructionout1 = new JTextField();
		Instructionout1.setEditable(false);
		Instructionout1.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 112));
		Instructionout1.setFont(myFont2);
		Cpupaneldown.add(Instructionout1);
		/** Setting Instruction text second area. **/
		JTextField instructionout2 = new JTextField();
		instructionout2.setEditable(false);
		instructionout2.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height * 3 / 112));
		Cpupaneldown.add(instructionout2);
		CpuPanel.add(Cpupaneldown);
		CenterPanel.add(CpuPanel, BorderLayout.SOUTH);
	}

	/**
	 * Constructor for BatchIO and terminal text area with tabs.
	 */
	private void BatchIO(JTabbedPane tabbedPane2) {
//		tabbedPane2 = new JTabbedPane();
//		tabbedPane2.setPreferredSize(new Dimension(screenSize.width/4,screenSize.height*3/14));
//		tabbedPane2.setFont(myFont);
//		BatchIO = new JTextArea(null,screenSize.width/4,screenSize.height*3/14);
//		BatchIO.setFont(myFont);
//		Terminal = new JTextArea(null,screenSize.width/4,screenSize.height*3/14);
//		tabbedPane2.addTab("BatchI/O", BatchIO); tabbedPane2.addTab("Terminal I/O", Terminal);
		CenterPanel.add(tabbedPane2);
		Outputtext = new JTextArea(null, screenSize.width / 4, screenSize.height * 3 / 14);
		Outputtext.setFont(myFont);
		Outputtext.setEditable(true);
		Outputtext.setBorder(new TitledBorder(null, "OUTPUT", TitledBorder.CENTER, TitledBorder.TOP, myFont, null));
		Outputtext.setPreferredSize(new Dimension(screenSize.width / 4, screenSize.height * 3 / 14));
		CenterPanel.add(Outputtext, BorderLayout.CENTER);
	}

	/**
	 * Constructor for Line end Panel with components panels and scroll.
	 */
	private void LineEndPanel() {
		LineEndPanel = new JPanel();
		LineEndPanel.setLayout(new GridLayout(1, 1));
		LineEndPanel.setBorder(new TitledBorder(null, "Memory", TitledBorder.CENTER, TitledBorder.TOP, myFont, null));
		LineEndPanel.setPreferredSize(new Dimension(screenSize.width / 4, screenSize.height * 9 / 14));
		Memory = new JTextArea(null, screenSize.width / 4, screenSize.height * 9 / 14);
		Memory.setFont(myFont);
		Memory.setEditable(false);
		/** Setting Scroll for memory panel. **/
		JScrollPane scroll = new JScrollPane(Memory);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Memory.setBorder(new TitledBorder(null, null, TitledBorder.CENTER, TitledBorder.TOP, myFont, null));
		LineEndPanel.add(scroll);
	}

//	/**
//	 * Constructor for Menu Bar with sub-menu.
//	 */
//	private void MenuBar() {
//		/*Create file menu with sub-menu*/
//		menuBar = new JMenuBar();
//		File = new JMenu("File");
//		File.setFont(myFont);
//		newMenu = new JMenuItem("New");
//		newMenu.addActionListener(this);
//		openMenu = new JMenuItem("Open");
//		openMenu.addActionListener(this);
//		newMenu.setFont(myFont);
//		openMenu.setFont(myFont);
//		File.add(newMenu);
//		File.add(openMenu);
//
//		/*Create edit menu with sub-menu*/
//		Edit = new JMenu("Edit");
//		Edit.setFont(myFont);
//		cutMenu = new JMenuItem("Cut Object Code");
//		cutMenu.addActionListener(this);
//		pasteMenu = new JMenuItem("Paste into Object Code");
//		pasteMenu.addActionListener(this);
//		cutMenu.setFont(myFont);
//		pasteMenu.setFont(myFont);
//		Edit.add(cutMenu);
//		Edit.add(pasteMenu);
//
//		/*Create build menu with sub-menu*/
//		Build = new JMenu("Build");
//		Build.setFont(myFont);
//		loadMenu = new JMenuItem("Load");
//		loadMenu.addActionListener(this);
//		runMenu = new JMenuItem("Run");
//		runMenu.addActionListener(this);
//		loadMenu.setFont(myFont);
//		runMenu.setFont(myFont);
//		Build.add(loadMenu);
//		Build.add(runMenu);
//
//		/*Adding all menu buttons to menubar*/
//		menuBar.add(File);menuBar.add(Build);menuBar.add(Edit);
//		frame.setJMenuBar(menuBar);
//	}

	/**
	 * Performs actions based on the action of the user. Open, Load, Save, Start,
	 * etc.
	 * 
	 * @param e The event triggered.
	 */
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		String userinput = e.getActionCommand();
//		if(userinput.equals("Open")||userinput.equals("Load")) {
//			JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//		    FileNameExtensionFilter restrict = new FileNameExtensionFilter("ONLY .txt file", "txt");
//		    chooser.addChoosableFileFilter(restrict);
//			int read = chooser.showSaveDialog(null);
//		    // if the user selects a file
//            if (read == JFileChooser.APPROVE_OPTION){
//            	try {
//                    BufferedReader in;
//                    in = new BufferedReader(new FileReader(chooser.getSelectedFile()));
//                    String line = in.readLine();
//                    while (line != null) {
//                    	ObjCode.setText(ObjCode.getText()+ line + "\n" ); //line + "\n"
//                        line = in.readLine();
//                    } in.close();
//                } catch (IOException ex) {
//                    throw new RuntimeException(
//                            "creation is not possible.");
//                }
//            }
////            else
////            	ObjCode.setText("Please choose .txt file or type object Code here");
//        } else if(userinput.equals("Save")) {
//        	 try {
//                 wordsScanner = new Scanner("INPUT:"+ObjCode.getText().toString()+"\n OUTPUT:" + Outputtext.getText().toString());
//                 final PrintWriter writer = new PrintWriter(new FileOutputStream("src/result.txt"));
//                 while (wordsScanner.hasNextLine()) {
//                     writer.println(wordsScanner.nextLine());
//                 }
//                 writer.close();
//             } catch (final FileNotFoundException exception) {
//                 System.out.println("Please check your words input file name \n" + exception);
//             }
//        }
////		else if(userinput.equals("Start")||userinput.equals("Run CODE")||userinput.equals("Run")) {
////        	firePropertyChange("Start", null, ObjCode.getText());
//		//}
//         else if(userinput.equals("New")) {
////			ObjCode.setText("");
//			Accumulatorout1.setText("");
//			Accumulatorout2.setText("");
//			Instructionout1.setText("");
//			Operand1.setText("");
//			Operand2.setText("");
//			ProgramCounterout1.setText("");
//			ProgramCounterout2.setText("");
//			Memory.setText("");
////			BatchIO.setText("");
//			Outputtext.setText("");
//			batchIndex = -1;
//			setCbox(false);
//			setNbox(false);
//			setVbox(false);
//			setZbox(false);
//			firePropertyChange("New", null, null);
//        }
////		else if(userinput.equals("Cut Object Code")) {
////       	ObjCode.cut();
////        } else if(userinput.equals("Paste into Object Code")) {
////        	ObjCode.paste();;
////        }
//	}

	// Adding setters for textFields and adding getters for when input is needed.

	/**
	 * Sets the memory text area based on the copy of memory given by the Machine
	 * class.
	 * 
	 * @param memCopy byte array copy of the memory.
	 */
	public void setMemText(byte[] memCopy) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < memCopy.length; i++) {
			if (i % 8 == 0) {
				sb.append("\n");
				String addrHex = String.format("%04x", i);
				sb.append(addrHex.toUpperCase());
				sb.append(": ");
			}
			String hex = String.format("%02x", memCopy[i]);
			sb.append(hex.toUpperCase());
			sb.append(" ");
		}
		sb.replace(0, 1, "");
		Memory.setText(sb.toString());
		Memory.setCaretPosition(0);
	}

	/**
	 * Sets the register text values according to the values passed in. Assumes the
	 * order is as listed below
	 * 
	 * @param registerValues short[] {InstrRegSpec, InstrRegOper, ProgCountReg,
	 *                       AccumReg}
	 */
	public void setRegistersText(short[] registerValues) {
		String instrSpecHex = String.format("%04x", registerValues[0]);
		Instructionout1.setText(instrSpecHex.toUpperCase());
		String operandHex = String.format("%04x", registerValues[1]);
		Operand1.setText(operandHex.toUpperCase());
		Operand2.setText("" + registerValues[1]);
		String progCountHex = String.format("%04x", registerValues[2]);
		ProgramCounterout1.setText(progCountHex.toUpperCase());
		ProgramCounterout2.setText("" + registerValues[2]);
		String accumHex = String.format("%04x", registerValues[3]);
		Accumulatorout1.setText(accumHex.toUpperCase());
		Accumulatorout2.setText("" + registerValues[3]);
	}

	/**
	 * This returns the characters stored in the BatchIO text area one character at
	 * a time.
	 * 
	 * @return The next character from batch input based upon how many times this
	 *         function has been called.
	 */
//	public char getBatchInput() {
//		batchIndex++;
//		return (BatchIO.getText()).charAt(batchIndex);
//	}
//
//	/*
//	*setBatchInput function for testing purposes only
//	 */
//	public void setBatchInput(String s) {
//		BatchIO.setText(s);
//	}

	public void setNbox(boolean b) {
		Nbox.setSelected(b);
	}

	public void setZbox(boolean b) {
		Zbox.setSelected(b);
	}

	public void setVbox(boolean b) {
		Vbox.setSelected(b);
	}

	public void setCbox(boolean b) {
		Cbox.setSelected(b);
	}

	/*
	 * New:
	 */
//	public void setObjCode(String s) {
//		ObjCode.setText(s);
//	}
//	public String getObjCodeText() {
//		return ObjCode.getText();
//	}
//	public JTextArea getObjCode() {
//		return ObjCode;
//	}
	public Scanner getwordsScanner() {
		return wordsScanner;
	}

	public void setWordsScanner(Scanner scanner) {
		wordsScanner = scanner;
	}

	public void setAccum1(String s) {
		Accumulatorout1.setText(s);
	}

	public void setAccum2(String s) {
		Accumulatorout2.setText(s);
	}

	public void setInstrOut(String s) {
		Instructionout1.setText(s);
	}

	public void setOp1(String s) {
		Operand1.setText(s);
	}

	public void setOp2(String s) {
		Operand2.setText(s);
	}

	public void setProgOut1(String s) {
		ProgramCounterout1.setText(s);
	}

	public void setProgOut2(String s) {
		ProgramCounterout2.setText(s);
	}

	public void setMem(String s) {
		Memory.setText(s);
	}

	// public void setBatchIO(String s) { BatchIO.setText(s); } Already have
	// identical method above
	public void clearOutputText() {
		Outputtext.setText("");
	}

	/**
	 * Adds the specified character to the output text area.
	 * 
	 * @param c
	 */
	public void output(char c) {
		Outputtext.setText(Outputtext.getText() + c);
	}

	/*
	 * getOutput method for testing purposes only
	 */
	public String getOutput() {
		return Outputtext.getText();
	}

	public void reset() {
//		ObjCode.setText("");
		Accumulatorout1.setText("");
		Accumulatorout2.setText("");
		Instructionout1.setText("");
		Operand1.setText("");
		Operand2.setText("");
		ProgramCounterout1.setText("");
		ProgramCounterout2.setText("");
		Memory.setText("");
//		BatchIO.setText("");
		Outputtext.setText("");
//		batchIndex = -1;
		setCbox(false);
		setNbox(false);
		setVbox(false);
		setZbox(false);
	}
}