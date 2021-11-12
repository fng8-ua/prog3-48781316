/**
 * @author Fernando Navarro Gonzalez
 * @author 48781316H
 */
package model.fighters;

import model.Fighter;
import model.Ship;


/**
 * The Class TIEInterceptor.
 */
public class TIEInterceptor extends Fighter{

	/**
	 * Instantiates a new TIE interceptor.
	 *
	 * @param mother the mother
	 */
	public TIEInterceptor(Ship mother) {
		super(mother);
		addVelocity(45);
		addAttack(5);
		addShield(-20);
	}
	
	/**
	 * Instantiates a new TIE interceptor.
	 *
	 * @param f the f
	 */
	private TIEInterceptor(TIEInterceptor f) {
		super(f);
	}

	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	@Override
	public char getSymbol() {
		return 'i';
	}
	
	/**
	 * Copy.
	 *
	 * @return the fighter
	 */
	@Override
	public Fighter copy() {
		return new TIEInterceptor(this);
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
		
		if(enemy.getSymbol() == 'Y') {
			damage = damage*2;
		} else if(enemy.getSymbol() == 'A') {
			damage = damage/2;
		}
		
		return damage;
	}
	
}
