Program Name: PEP/8 Simulator
Authors: Anna Blanton, Walter Kagel, Taehong Kim, John Morton
Version: Autumn, 2020

This program is a virtual machine which simulates a PEP/8 virtual computer. 
After compiling and executing the code, a user will be able to enter machine instructions into a GUI.
This version of the simulator contains a limited number of instructions. 

This program supports storing and loading data from memory, writing to and reading from an accumulator register, performing addition and subtraction on a value stored in the accumulator register, reading characters from an input, and writing characters to an output. Additional information about the commands to execute these functions will be provided below.

Instructions are entered as hexadecimal values delimited by a space.
Each instruction consists of 3 bytes of data, the first byte is the instruction specifier, the second and third bytes form the operand specifier. Many instructions have an option for both immediate and direct addressing modes, each of these are represented with a slightly different hex value.
 Here is an example of what an instruction may look like:

51 00 07

Several of these instructions can be put together to form a program:

49 00 0D 49 00 0E 51 00 0E 51 00 0D 00

The following are the hex values corresponding with each available instruction:

x00 = Stop Execution 
xC0 = Load the operand into the A register (immediate) 
xC1 = Load the operand into the A register (direct) 
xE1 = Store the A register to the operand 
x70 = Add the operand to the A register (immediate) 
x71 = Add the operand to the A register (direct) 
x80 = Subtract the operand from the A register (immediate) 
x81 = Subtract the operand from the A register (direct) 
x49 = Character input to the operand 
x50 = Character output from the operand (immediate) 
x51 = Character output from the operand (direct) 

For immediate addressing mode the operand will contain the value which will be used.
For direct addressing mode the operand contains an address whose value will be used.

This program contains a memory consisting of a byte array with 16-bit addressability.
After a set of instructions are entered into the GUI they are written to memory.
After that they are read, decoded, and executed by the CPU. 

The programs should be entered into a text box titled “Object Code” on the GUI.
Any desired input should be put into the text box “BatchI/O” on the GUI.
Be sure to include any desired input before executing the program.
Programs can be executed by pressing the “Run CODE” button on the GUI.
After running a program it is best to press the “New” button before running another one, this clears the old data out of the memory.

The GUI displays information about the values stored in the registers as well as the memory.

