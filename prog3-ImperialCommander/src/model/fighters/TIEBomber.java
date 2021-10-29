package model.fighters;

import model.Fighter;
import model.Ship;

public class TIEBomber extends Fighter{

	protected TIEBomber(Ship mother) {
		super(mother);
		addVelocity(-30);
		addAttack(-50);
		addShield(35);
	}

	@Override
	public char getSymbol() {
		return 'b';
	}
	
	
}
