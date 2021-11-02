package model.fighters;

import model.Fighter;
import model.Ship;

public class TIEFighter extends Fighter{

	protected TIEFighter(Ship mother) {
		super(mother);
		addVelocity(10);
		addAttack(5);
		addShield(-10);
	}

	@Override
	public char getSymbol() {
		return 'f';
	}
	
	@Override
	public Fighter copy() {
		return new TIEFighter(this.getMotherShip());
	}
	
}
