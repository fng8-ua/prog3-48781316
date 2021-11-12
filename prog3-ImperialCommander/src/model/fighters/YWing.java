/**
 * @author Fernando Navarro Gonzalez
 * @author 48781316H
 */
package model.fighters;

import model.Fighter;
import model.Ship;


/**
 * The Class YWing.
 */
public class YWing extends Fighter{
	
	/**
	 * Instantiates a new y wing.
	 *
	 * @param mother the mother
	 */
	public YWing(Ship mother) {
		super(mother);
		addVelocity(-20);
		addAttack(-10);
		addShield(30);
	}
	
	/**
	 * Instantiates a new y wing.
	 *
	 * @param f the f
	 */
	private YWing(YWing f) {
		super(f);
	}

	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	@Override
	public char getSymbol() {
		return 'Y';
	}
	
	/**
	 * Copy.
	 *
	 * @return the fighter
	 */
	@Override
	public Fighter copy() {
		return new YWing(this);
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
		
		if(enemy.getSymbol() == 'f' || enemy.getSymbol() == 'i') {
			damage = damage/3;
		} else if(enemy.getSymbol() == 'b') {
			damage = damage/2;
		}
		
		return damage;
	}

}
