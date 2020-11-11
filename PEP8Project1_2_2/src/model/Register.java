package model;

/**
 * Class for register object.
 * Stores 16-bit register and contains methods for getting and setting the register value.
 * @author Group 6: Anna Blanton
 * @version 10/14/2020
 */
public class Register {
	private short reg;
	public Register() {
		reg = 0x0000;
	}
	
	public short getReg() {
		return reg;
	}
	
	public void load(short val) {
		reg = val;
	}
}
