package model.exceptions;
import model.Fighter;

@SuppressWarnings("serial")
public class FighterAlreadyInBoardException extends Exception{

	private Fighter fighter;
	
	public FighterAlreadyInBoardException(Fighter fighter) {
		super();
		this.fighter = fighter;
	}
	
	public String getMessage() {
		return "ERROR: " + fighter.toString() + " already in board.";
	}
	
}
