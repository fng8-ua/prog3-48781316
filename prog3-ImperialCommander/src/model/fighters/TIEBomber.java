package model.fighters;

import model.Fighter;
import model.Ship;

public class TIEBomber extends Fighter{

	public TIEBomber(Ship mother) {
		super(mother);
		addVelocity(-30);
		addAttack(-50);
		addShield(35);
	}
	
	private TIEBomber(TIEBomber f) {
		super(f);
	}

	@Override
	public char getSymbol() {
		return 'b';
	}
	
	@Override
	public Fighter copy() {
		return new TIEBomber(this);
	}
	
	@Override
	public int getDamage(int n, Fighter enemy) {
		int damage;
		
		damage = (n*this.getAttack())/300;
		
		if(enemy.getSymbol() == 'X' || enemy.getSymbol() == 'Y') {
			damage = damage/2;
		} else if(enemy.getSymbol() == 'A') {
			damage = damage/3;
		}
		
		return damage;
	}
	
}
