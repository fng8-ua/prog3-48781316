package model.fighters;

import model.Fighter;
import model.Ship;

public class XWing extends Fighter{
	
	protected XWing(Ship mother) {
		super(mother);
		addVelocity(10);
		addAttack(20);
		
	}

	@Override
	public char getSymbol() {
		return 'X';
	}
	
}
