/*
 * 
 */
package model.exceptions;
import model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class OutOfBoundsException.
 */
@SuppressWarnings("serial")
public class OutOfBoundsException extends Exception{
	
	/** The c. */
	private Coordinate c;
	
	/**
	 * Instantiates a new out of bounds exception.
	 *
	 * @param c the c
	 */
	public OutOfBoundsException(Coordinate c) {
		super();
		this.c = c;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "ERROR: coordinate " + c.toString() + " is out of the board.";
	}
	
}
