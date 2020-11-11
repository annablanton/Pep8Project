package model;

import view.GUI;

/**
 * Class for machine object.
 * Stores CPU, memory, and a StringBuffer for output.
 * Has methods for running and storing assembly code, as well as for getting
 * the output.
 * @author Group 6: Anna Blanton
 * @version 10/17/2020
 */
public class Machine {
	private CPU cpu;
	private Memory mem;
	private final GUI pep8View;

	public Machine(GUI view) {
		cpu = new CPU(view);
		mem = new Memory();
		pep8View = view;
	}
	
	public boolean fetchExecute() {
		return cpu.fetchExecute(mem);
	}

	public void store(short addr, byte ... d) {
		mem.store(addr, d);
		pep8View.setMemText(mem.getMemoryDump());
	}

	public void run() {
		boolean done = false;
		while (!done) {
			done = fetchExecute();
		}
		//Sets the output text of the GUI.
		pep8View.setMemText(mem.getMemoryDump());
		pep8View.setRegistersText(cpu.getRegisters());
	}

	/**
	 * Resets the machine by creating a new memory and cpu.
	 */
	public void reset() {
		cpu = new CPU(pep8View);
		mem = new Memory();
	}

}
