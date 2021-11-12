/**
 * @author Fernando Navarro Gonzalez
 * @author 48781316H
 */
package model.fighters;

import model.Fighter;
import model.Ship;

/**
 * The Class TIEBomber.
 */
public class TIEBomber extends Fighter{

	/**
	 * Instantiates a new TIE bomber.
	 *
	 * @param mother the mother
	 */
	public TIEBomber(Ship mother) {
		super(mother);
		addVelocity(-30);
		addAttack(-50);
		addShield(35);
	}
	
	/**
	 * Instantiates a new TIE bomber.
	 *
	 * @param f the f
	 */
	private TIEBomber(TIEBomber f) {
		super(f);
	}

	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	@Override
	public char getSymbol() {
		return 'b';
	}
	
	/**
	 * Copy.
	 *
	 * @return the fighter
	 */
	@Override
	public Fighter copy() {
		return new TIEBomber(this);
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
		
		if(enemy.getSymbol() == 'X' || enemy.getSymbol() == 'Y') {
			damage = damage/2;
		} else if(enemy.getSymbol() == 'A') {
			damage = damage/3;
		}
		
		return damage;
	}
	
}
