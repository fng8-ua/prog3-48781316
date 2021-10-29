package model.fighters;

import model.Fighter;
import model.Ship;

public class AWing extends Fighter{

	protected AWing(Ship mother) {
		super(mother);
		addVelocity(40);
		addAttack(5);
		addShield(-50);
	}

	@Override
	public char getSymbol() {
		return 'A';
	}
	
	
}
