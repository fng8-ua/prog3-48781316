package model.exceptions;
import model.Fighter;

@SuppressWarnings("serial")
public class FighterNotInBoardException extends Exception{

	private Fighter fighter;
	
	public FighterNotInBoardException(Fighter fighter) {
		super();
		this.fighter = fighter;
	}
	
	public String getMessage() {
		return "ERROR: " + fighter.getId() + " not in board.";
	}
	
}
