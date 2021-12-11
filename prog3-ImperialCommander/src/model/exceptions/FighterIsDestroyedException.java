/*
 * 
 */
package model.exceptions;
import model.Fighter;

// TODO: Auto-generated Javadoc
/**
 * The Class FighterIsDestroyedException.
 */
@SuppressWarnings("serial")
public class FighterIsDestroyedException extends Exception{
	
	/** The fighter. */
	private Fighter fighter;
	
	/**
	 * Instantiates a new fighter is destroyed exception.
	 *
	 * @param fighter the fighter
	 */
	public FighterIsDestroyedException(Fighter fighter) {
		super();
		this.fighter = fighter;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "ERROR: " + fighter.toString() + " is destroyed.";
	}
	
}
