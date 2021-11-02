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
	
	@Override
	public Fighter copy() {
		return new TIEInterceptor(this.getMotherShip());
	}
	
	@Override
	public int getDamage(int n, Fighter enemy) {
		int damage;
		
		damage = (n*this.getAttack())/300;
		
		if(enemy.getSymbol() == 'Y') {
			damage = damage*2;
		} else if(enemy.getSymbol() == 'A') {
			damage = damage/2;
		}
		
		return damage;
	}
	
}
