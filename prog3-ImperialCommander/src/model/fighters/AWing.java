/*
 * 
 */
package model.fighters;

import model.Fighter;
import model.Ship;

/**
 * The Class AWing.
 */
public class AWing extends Fighter{

	/**
	 * Instantiates a new a wing.
	 *
	 * @param mother the mother
	 */
	public AWing(Ship mother) {
		super(mother);
		addVelocity(40);
		addAttack(5);
		addShield(-50);
	}
	
	/**
	 * Instantiates a new a wing.
	 *
	 * @param f the f
	 */
	private AWing(AWing f) {
		super(f);
	}

	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	@Override
	public char getSymbol() {
		return 'A';
	}
	
	/**
	 * Copy.
	 *
	 * @return the fighter
	 */
	@Override
	public Fighter copy() {
		return new AWing(this);
	}
	
	/**
	 * Gets the damage.
	 *
	 * @param n the n
	 * @param enemy the enemy
	 * @return the damage
	 */
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
