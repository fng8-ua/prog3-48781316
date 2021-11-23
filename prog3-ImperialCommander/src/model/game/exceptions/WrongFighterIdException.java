package model.game.exceptions;


@SuppressWarnings("serial")
public class WrongFighterIdException extends Exception{
	
	@SuppressWarnings("unused")
	private int id;
	
	public WrongFighterIdException(int id) {
		super();
		this.id = id;
	}
	
	public String getMessage() {
		return "ERROR: wrong fighter id exception";
	}
	
}
