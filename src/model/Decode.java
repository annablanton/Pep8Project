package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Decode {
	public String assemblyToHex(String[] source) throws Exception {
		List<String> sourceCode = new ArrayList<String>(Arrays.asList(source));
		StringBuilder hexBuild = new StringBuilder();

		// remove pre-line comments
		for (String code : source) {
			if (code == null || code.equals("")) {
				sourceCode.remove(code);
			}
			else if (code.startsWith(";")) {
				sourceCode.remove(code);
			}
		}

		if (sourceCode.size() > 0) {
			for (String code : sourceCode) {
				// instruction specifier
				int spec;

				// removing same line comments
				if (code.indexOf(";") != -1) {
					code = code.substring(0, code.indexOf(";"));
				}

				String[] instructions = code.split("[\\s,]+");
				if (instructions.length > 4) {
					throw new Exception("Syntax error: " + code);
				}

				System.out.println(Arrays.deepToString(instructions));
				if (instructions.length > 0) {
					switch (instructions[0]) {
					// Stop
					case "STOP":
						spec = 0x00;
						if (instructions.length > 1) {
							throw new Exception("Syntax error: " + code);
						}
						hexBuild.append(String.format("%02X", spec));
						break;

					// Branch
					case "BR":
						spec = 0X04;
						if (instructions.length < 2) {
							throw new Exception("Syntax error: " + code);
						}
						if (instructions.length < 3) {
							spec += 0x01;
						} else {
							switch (instructions[2]) {
							case "i":
								spec += 0x00;
								break;
							}
						}
						hexBuild.append(String.format("%02X", spec));
						hexBuild.append(String.format("%s", instructions[1].substring(2)));
						break;
					case "BRLE":
						spec = 0X06;
						if (instructions.length < 2) {
							throw new Exception("Syntax error: " + code);
						}
						if (instructions.length < 3) {
							spec += 0x01;
						} else {
							switch (instructions[2]) {
							case "i":
								spec += 0x00;
								break;
							}
						}
						hexBuild.append(String.format("%02X", spec));
						hexBuild.append(String.format("%s", instructions[1].substring(2)));
						break;
					case "BRLT":
						spec = 0X08;
						if (instructions.length < 2) {
							throw new Exception("Syntax error: " + code);
						}
						if (instructions.length < 3) {
							spec += 0x01;
						} else {
							switch (instructions[2]) {
							case "i":
								spec += 0x00;
								break;
							}
						}
						hexBuild.append(String.format("%02X", spec));
						hexBuild.append(String.format("%s", instructions[1].substring(2)));
						break;
					case "BREQ":
						spec = 0X0A;
						if (instructions.length < 2) {
							throw new Exception("Syntax error: " + code);
						}
						if (instructions.length < 3) {
							spec += 0x01;
						} else {
							switch (instructions[2]) {
							case "i":
								spec += 0x00;
								break;
							}
						}
						hexBuild.append(String.format("%02X", spec));
						hexBuild.append(String.format("%s", instructions[1].substring(2)));
						break;
					case "BRNE":
						spec = 0X0C;
						if (instructions.length < 2) {
							throw new Exception("Syntax error: " + code);
						}
						if (instructions.length < 3) {
							spec += 0x01;
						} else {
							switch (instructions[2]) {
							case "i":
								spec += 0x00;
								break;
							}
						}
						hexBuild.append(String.format("%02X", spec));
						hexBuild.append(String.format("%s", instructions[1].substring(2)));
						break;
					case "BRGE":
						spec = 0X0E;
						if (instructions.length < 2) {
							throw new Exception("Syntax error: " + code);
						}
						if (instructions.length < 3) {
							spec += 0x01;
						} else {
							switch (instructions[2]) {
							case "i":
								spec += 0x00;
								break;
							}
						}
						hexBuild.append(String.format("%02X", spec));
						hexBuild.append(String.format("%s", instructions[1].substring(2)));
						break;
					case "BRGT":
						spec = 0X10;
						if (instructions.length < 2) {
							throw new Exception("Syntax error: " + code);
						}
						if (instructions.length < 3) {
							spec += 0x01;
						} else {
							switch (instructions[2]) {
							case "i":
								spec += 0x00;
								break;
							}
						}
						hexBuild.append(String.format("%02X", spec));
						hexBuild.append(String.format("%s", instructions[1].substring(2)));
						break;
					case "BRV":
						spec = 0X12;
						if (instructions.length < 2) {
							throw new Exception("Syntax error: " + code);
						}
						if (instructions.length < 3) {
							spec += 0x01;
						} else {
							switch (instructions[2]) {
							case "i":
								spec += 0x00;
								break;
							}
						}
						hexBuild.append(String.format("%02X", spec));
						hexBuild.append(String.format("%s", instructions[1].substring(2)));
						break;
					case "BRC":
						spec = 0X14;
						if (instructions.length < 2) {
							throw new Exception("Syntax error: " + code);
						}
						if (instructions.length < 3) {
							spec += 0x01;
						} else {
							switch (instructions[2]) {
							case "i":
								spec += 0x00;
								break;
							}
						}
						hexBuild.append(String.format("%02X", spec));
						hexBuild.append(String.format("%s", instructions[1].substring(2)));
						break;

					// Bitwise Operations
					case "NOTA":
						spec = 0X18;
						if (instructions.length > 1) {
							throw new Exception("Syntax error: " + code);
						}
						hexBuild.append(String.format("%02X", spec));
						break;
					case "NEGA":
						spec = 0X1A;
						if (instructions.length > 1) {
							throw new Exception("Syntax error: " + code);
						}
						hexBuild.append(String.format("%02X", spec));
						break;
					case "ASLA":
						spec = 0X1C;
						if (instructions.length > 1) {
							throw new Exception("Syntax error: " + code);
						}
						hexBuild.append(String.format("%02X", spec));
						break;
					case "ASRA":
						spec = 0X1E;
						if (instructions.length > 1) {
							throw new Exception("Syntax error: " + code);
						}
						hexBuild.append(String.format("%02X", spec));
						break;
					case "ROLA":
						spec = 0X20;
						if (instructions.length > 1) {
							throw new Exception("Syntax error: " + code);
						}
						hexBuild.append(String.format("%02X", spec));
						break;
					case "RORA":
						spec = 0X22;
						if (instructions.length > 1) {
							throw new Exception("Syntax error: " + code);
						}
						hexBuild.append(String.format("%02X", spec));
						break;

					// Character In/Out
					case "CHARI":
						spec = 0X48;
						if (instructions.length < 3) {
							throw new Exception("Syntax error: " + code);
						} else {
							switch (instructions[2]) {
							case "i":
								spec += 0x00;
								break;
							case "d":
								spec += 0x01;
								break;
							case "n":
								spec += 0x02;
								break;
							}
						}
						hexBuild.append(String.format("%02X", spec));
						hexBuild.append(String.format("%s", instructions[1].substring(2)));
						break;
					case "CHARO":
						spec = 0X50;
						if (instructions.length < 3) {
							throw new Exception("Syntax error: " + code);
						} else {
							switch (instructions[2]) {
							case "i":
								spec += 0x00;
								break;
							case "d":
								spec += 0x01;
								break;
							case "n":
								spec += 0x02;
								break;
							}
						}
						hexBuild.append(String.format("%02X", spec));
						hexBuild.append(String.format("%s", instructions[1].substring(2)));
						break;

					// Operations
					case "ADDA":
						spec = 0X70;
						if (instructions.length < 3) {
							throw new Exception("Syntax error: " + code);
						} else {
							switch (instructions[2]) {
							case "i":
								spec += 0x00;
								break;
							case "d":
								spec += 0x01;
								break;
							case "n":
								spec += 0x02;
								break;
							}
						}
						hexBuild.append(String.format("%02X", spec));
						System.out.println(instructions[1]);
						hexBuild.append(String.format("%s", instructions[1].substring(2)));
						break;
					case "SUBA":
						spec = 0X80;
						if (instructions.length < 3) {
							throw new Exception("Syntax error: " + code);
						} else {
							switch (instructions[2]) {
							case "i":
								spec += 0x00;
								break;
							case "d":
								spec += 0x01;
								break;
							case "n":
								spec += 0x02;
								break;
							}
						}
						hexBuild.append(String.format("%02X", spec));
						hexBuild.append(String.format("%s", instructions[1].substring(2)));
						break;
					case "ANDA":
						spec = 0X90;
						if (instructions.length < 3) {
							throw new Exception("Syntax error: " + code);
						} else {
							switch (instructions[2]) {
							case "i":
								spec += 0x00;
								break;
							case "d":
								spec += 0x01;
								break;
							case "n":
								spec += 0x02;
								break;
							}
						}
						hexBuild.append(String.format("%02X", spec));
						hexBuild.append(String.format("%s", instructions[1].substring(2)));
						break;
					case "ORA":
						spec = 0XA0;
						if (instructions.length < 3) {
							throw new Exception("Syntax error: " + code);
						} else {
							switch (instructions[2]) {
							case "i":
								spec += 0x00;
								break;
							case "d":
								spec += 0x01;
								break;
							case "n":
								spec += 0x02;
								break;
							}
						}
						hexBuild.append(String.format("%02X", spec));
						hexBuild.append(String.format("%s", instructions[1].substring(2)));
						break;
					case "CPA":
						spec = 0XB0;
						if (instructions.length < 3) {
							throw new Exception("Syntax error: " + code);
						} else {
							switch (instructions[2]) {
							case "i":
								spec += 0x00;
								break;
							case "d":
								spec += 0x01;
								break;
							case "n":
								spec += 0x02;
								break;
							}
						}
						hexBuild.append(String.format("%02X", spec));
						hexBuild.append(String.format("%s", instructions[1].substring(2)));
						break;

					// Load and store
					case "LDA":
						spec = 0XC0;
						if (instructions.length < 3) {
							throw new Exception("Syntax error: " + code);
						} else {
							switch (instructions[2]) {
							case "i":
								spec += 0x00;
								break;
							case "d":
								spec += 0x01;
								break;
							case "n":
								spec += 0x02;
								break;
							}
						}
						hexBuild.append(String.format("%02X", spec));
						hexBuild.append(String.format("%s", instructions[1].substring(2)));
						break;
					case "LDBYTEA":
						spec = 0XD0;
						if (instructions.length < 3) {
							throw new Exception("Syntax error: " + code);
						} else {
							switch (instructions[2]) {
							case "i":
								spec += 0x00;
								break;
							case "d":
								spec += 0x01;
								break;
							case "n":
								spec += 0x02;
								break;
							}
						}
						hexBuild.append(String.format("%02X", spec));
						hexBuild.append(String.format("%s", instructions[1].substring(2)));
						break;
					case "STA":
						spec = 0XE0;
						if (instructions.length < 3) {
							throw new Exception("Syntax error: " + code);
						} else {
							switch (instructions[2]) {
							case "i":
								spec += 0x00;
								break;
							case "d":
								spec += 0x01;
								break;
							case "n":
								spec += 0x02;
								break;
							}
						}
						hexBuild.append(String.format("%02X", spec));
						hexBuild.append(String.format("%s", instructions[1].substring(2)));
						break;
					case "STBYTEA":
						spec = 0XF0;
						if (instructions.length < 3) {
							throw new Exception("Syntax error: " + code);
						} else {
							switch (instructions[2]) {
							case "i":
								spec += 0x00;
								break;
							case "d":
								spec += 0x01;
								break;
							case "n":
								spec += 0x02;
								break;
							}
						}
						hexBuild.append(String.format("%02X", spec));
						hexBuild.append(String.format("%s", instructions[1].substring(2))); 
						break;
					}
				}
			}
		}
		return hexBuild.toString();
	}
	
	public String format(String objCode) {
		StringBuilder build = new StringBuilder();
		int index = 0;
		for (int i = 0; i<objCode.length(); i++) {
			if(index != 2) {
				build.append(objCode.charAt(i));
				index++;
			} else {
				build.append(" ");
				build.append(objCode.charAt(i));
				index = 1;
			}
		}
		return build.toString();
	}

	public static void main(String[] args) throws Exception {
		Decode dec = new Decode();
		String[] ins = new String[] { ";", "STA		0x000D,i", "BR		0x000D", "ADDA		0x000D,d" };
		String[] ins2 = new String[] { "CHARI	0x000D,i", "CHARI	0x000D,d", "CHARI	0x000D,n" };
		
		//:comment,,comment
		String[] ins3 = new String[] {null};
		
		String str = ";Comment\n;this code does this\n\nADDA	0x000D, i\nSTOP";
		String[] ins4 = str.split("\\r?\\n");
		
		System.out.println(dec.assemblyToHex(ins));
		System.out.println(dec.assemblyToHex(ins2));
		System.out.println(dec.assemblyToHex(ins3));
		System.out.println(dec.assemblyToHex(ins4));
		
		String ans = dec.format(dec.assemblyToHex(ins));
		System.out.println(ans);
	}
}
