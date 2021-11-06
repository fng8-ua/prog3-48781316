package model.exceptions;
import model.Coordinate;

@SuppressWarnings("serial")
public class OutOfBoundsException extends Exception{
	
	private Coordinate c;
	
	public OutOfBoundsException(Coordinate c) {
		super();
		this.c = c;
	}
	
	public String getMessage() {
		return "ERROR: coordinate " + c.toString() + " is out of the board.";
	}
	
}