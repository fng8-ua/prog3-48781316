/*
 * 
 */
package model.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class NoFighterAvailableException.
 */
@SuppressWarnings("serial")
public class NoFighterAvailableException extends Exception{

	/** The type. */
	private String type;
	
	/**
	 * Instantiates a new no fighter available exception.
	 *
	 * @param type the type
	 */
	public NoFighterAvailableException(String type) {
		super();
		this.type = type;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "ERROR: specified fighter of type " + type + " not found";
	}
	
}
