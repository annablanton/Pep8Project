package model;

/**
 * Class for program counter object.
 * Has method for adding an offset value to the register.
 * @author Group 6: Anna Blanton
 * @version 10/14/2020
 */
public class ProgramCounter extends Register {
	public ProgramCounter() {
		super();
	}
	
	public void offset(byte i) {
		load((short) (getReg() + i));
	}

	public void reset() {
		load((short) 0);
	}
}
