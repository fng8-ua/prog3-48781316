package model.fighters;

import model.Fighter;
import model.Ship;

public class XWing extends Fighter{
	
	public XWing(Ship mother) {
		super(mother);
		addVelocity(10);
		addAttack(20);
		
	}
	
	private XWing(XWing f) {
		super(f);
	}

	@Override
	public char getSymbol() {
		return 'X';
	}
	
	@Override
	public Fighter copy() {
		return new XWing(this);
	}
	
}
