package model.fighters;

import model.Fighter;
import model.Ship;

public class YWing extends Fighter{
	
	public YWing(Ship mother) {
		super(mother);
		addVelocity(-20);
		addAttack(-50);
		addShield(30);
	}
	
	private YWing(YWing f) {
		super(f);
	}

	@Override
	public char getSymbol() {
		return 'Y';
	}
	
	@Override
	public Fighter copy() {
		return new YWing(this);
	}
	
	@Override
	public int getDamage(int n, Fighter enemy) {
		int damage;
		
		damage = (n*this.getAttack())/300;
		
		if(enemy.getSymbol() == 'f' || enemy.getSymbol() == 'i') {
			damage = damage/3;
		} else if(enemy.getSymbol() == 'b') {
			damage = damage/2;
		}
		
		return damage;
	}

}