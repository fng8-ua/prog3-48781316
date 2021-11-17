package model.exceptions;

@SuppressWarnings("serial")
public class NoFighterAvailableException extends Exception{

	private String type;
	
	public NoFighterAvailableException(String type) {
		super();
		this.type = type;
	}
	
	public String getMessage() {
		return "ERROR: specified fighter of type" + type + " not found";
	}
	
}
