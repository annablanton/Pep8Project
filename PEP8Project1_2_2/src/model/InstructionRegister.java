package model;

/**
 * Class for InstructionRegister object.
 * Extends standard register behavior to include the specifier portion
 * of the instruction register.
 * @author Group 6: Anna Blanton
 * @version 10/14/2020
 */
public class InstructionRegister extends Register {
	private byte specifier;
	public InstructionRegister() {
		super();
		specifier = 0x00;
	}

	public byte getSpecifier() {
		return specifier;
	}

	public void setSpecifier(byte s) {
		specifier = s;
	}
}
