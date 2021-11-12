/**
 * @author Fernando Navarro Gonzalez
 * @author 48781316H
 */
package model.exceptions;


/**
 * The Class InvalidSizeException.
 */
@SuppressWarnings("serial")
public class InvalidSizeException extends Exception{

	/** The size. */
	private int size;
	
	/**
	 * Instantiates a new invalid size exception.
	 *
	 * @param size the size
	 */
	public InvalidSizeException(int size) {
		super();
		this.size = size;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "ERROR: size (" + size + ") is smaller than 5.";
	}
	
}
