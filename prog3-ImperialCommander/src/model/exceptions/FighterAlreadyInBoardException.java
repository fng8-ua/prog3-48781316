/*
 * 
 */
package model.exceptions;
import model.Fighter;

// TODO: Auto-generated Javadoc
/**
 * The Class FighterAlreadyInBoardException.
 */
@SuppressWarnings("serial")
public class FighterAlreadyInBoardException extends Exception{

	/** The fighter. */
	private Fighter fighter;
	
	/**
	 * Instantiates a new fighter already in board exception.
	 *
	 * @param fighter the fighter
	 */
	public FighterAlreadyInBoardException(Fighter fighter) {
		super();
		this.fighter = fighter;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "ERROR: " + fighter.toString() + " already in board.";
	}
	
}
