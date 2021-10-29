package model.fighters;

import model.Fighter;
import model.Ship;

public class TIEInterceptor extends Fighter{

	protected TIEInterceptor(Ship mother) {
		super(mother);
		addVelocity(40);
		addAttack(5);
		addShield(-50);
	}

	@Override
	public char getSymbol() {
		return 'i';
	}
	
	
}
