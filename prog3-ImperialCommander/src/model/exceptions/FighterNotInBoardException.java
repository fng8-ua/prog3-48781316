/**
 * @author Fernando Navarro Gonzalez
 * @author 48781316H
 */
package model.exceptions;
import model.Fighter;


/**
 * The Class FighterNotInBoardException.
 */
@SuppressWarnings("serial")
public class FighterNotInBoardException extends Exception{

	/** The fighter. */
	private Fighter fighter;
	
	/**
	 * Instantiates a new fighter not in board exception.
	 *
	 * @param fighter the fighter
	 */
	public FighterNotInBoardException(Fighter fighter) {
		super();
		this.fighter = fighter;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "ERROR: " + fighter.toString() + " not in board.";
	}
	
}
