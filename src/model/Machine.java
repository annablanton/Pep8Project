package model;

import controller.Pep8Sim;
import view.GUI;

/**
 * Class for machine object. Stores CPU, memory, and a StringBuffer for output.
 * Has methods for running and storing assembly code, as well as for getting the
 * output.
 * 
 * @author Group 6: Anna Blanton
 * @version 10/17/2020
 */
public class Machine {
	private CPU cpu;
	private Memory mem;
	private final GUI pep8View;
	private Pep8Sim controller;

	public Machine(GUI view, Pep8Sim ctlr) {
		cpu = new CPU(view, ctlr);
		mem = new Memory();
		pep8View = view;
		controller = ctlr;
	}

	public boolean fetchExecute() {
		return cpu.fetchExecute(mem);
	}

	public void store(short addr, byte... d) {
		mem.storeByte(addr, d);
		pep8View.setMemText(mem.getMemoryDump());
	}

	public void run() {
		boolean done = false;
		while (!done) {
			done = fetchExecute();
		}
		// Sets the output text of the GUI.
		pep8View.setMemText(mem.getMemoryDump());
		pep8View.setRegistersText(cpu.getRegisters());
	}

	/**
	 * Resets the machine by creating a new memory and cpu.
	 */
	public void reset() {
		cpu = new CPU(pep8View, controller);
		mem = new Memory();
		pep8View.reset();
		controller.reset();
	}

}
