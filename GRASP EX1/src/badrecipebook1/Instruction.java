package badrecipebook1;

//Low Coupling
//Information Expert
public class Instruction {
	private String instruction;

	public Instruction(String instr) {
		instruction = instr;
	}

	public String getInstruction() {
		return instruction;
	}
}