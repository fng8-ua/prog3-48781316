/**
 * @author Fernando Navarro Gonzalez
 * @author 48781316H
 */
package model.game.exceptions;


// TODO: Auto-generated Javadoc
/**
 * The Class WrongFighterIdException.
 */
@SuppressWarnings("serial")
public class WrongFighterIdException extends Exception{
	
	/** The id. */
	@SuppressWarnings("unused")
	private int id;
	
	/**
	 * Instantiates a new wrong fighter id exception.
	 *
	 * @param id the id
	 */
	public WrongFighterIdException(int id) {
		super();
		this.id = id;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "ERROR: wrong fighter id exception";
	}
	
}
