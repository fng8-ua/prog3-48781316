package model.exceptions;
import model.Fighter;

@SuppressWarnings("serial")
public class FighterIsDestroyedException extends Exception{
	
	private Fighter fighter;
	
	public FighterIsDestroyedException(Fighter fighter) {
		super();
		this.fighter = fighter;
	}
	
	public String getMessage() {
		return "ERROR: " + fighter.toString() + " is destroyed.";
	}
	
}
