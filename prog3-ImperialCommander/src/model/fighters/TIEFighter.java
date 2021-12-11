/*
 * 
 */
package model.fighters;

import model.Fighter;
import model.Ship;

// TODO: Auto-generated Javadoc
/**
 * The Class TIEFighter.
 */
public class TIEFighter extends Fighter{

	/**
	 * Instantiates a new TIE fighter.
	 *
	 * @param mother the mother
	 */
	public TIEFighter(Ship mother) {
		super(mother);
		addVelocity(10);
		addAttack(5);
		addShield(-10);
	}
	
	/**
	 * Instantiates a new TIE fighter.
	 *
	 * @param f the f
	 */
	private TIEFighter(TIEFighter f) {
		super(f);
	}

	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	@Override
	public char getSymbol() {
		return 'f';
	}
	
	/**
	 * Copy.
	 *
	 * @return the fighter
	 */
	@Override
	public Fighter copy() {
		return new TIEFighter(this);
	}
	
}
