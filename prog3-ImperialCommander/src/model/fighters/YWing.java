package model.fighters;

import model.Fighter;
import model.Ship;

public class YWing extends Fighter{
	
	protected YWing(Ship mother) {
		super(mother);
		addVelocity(-20);
		addAttack(-50);
		addShield(30);
	}

	@Override
	public char getSymbol() {
		return 'Y';
	}

}
