package model;

public class BadInputException extends Exception{
	int id;

	public BadInputException(int x) {
		this(x, "no message");
	}

	public BadInputException(int x, String message) {
		super(message);
		id = x;
	}

	public String toString() {
		String output = "";
		output += "Custom Exception:";
		switch (id) {
		case 1:
			output += " Error 1: Citizen's id has less than 9 digits";
			break;
		case 2:
			output += " Error 2: Citizen's id has more than 9 digits";
			break;
		case 3:
			output += " Error 3: Input out of range";
			break;
		case 4:
			output += " Error 4: Age below 18";
			break;
		case 5:
			output += " Error 5: Soldier cannot be older than 21";
		}
		return output;
	}

	private static final long serialVersionUID = 1L;
}


