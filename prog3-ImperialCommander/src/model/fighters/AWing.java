package model.fighters;

import model.Fighter;
import model.Ship;

public class AWing extends Fighter{

	public AWing(Ship mother) {
		super(mother);
		addVelocity(40);
		addAttack(5);
		addShield(-50);
	}
	
	private AWing(AWing f) {
		super(f);
	}

	@Override
	public char getSymbol() {
		return 'A';
	}
	
	@Override
	public Fighter copy() {
		return new AWing(this);
	}
	
	@Override
	public int getDamage(int n, Fighter enemy) {
		int damage;
		
		damage = (n*this.getAttack())/300;
		
		if(enemy.getSymbol() == 'b') {
			damage = damage*2;
		}
		
		return damage;
	}
	
	
}
