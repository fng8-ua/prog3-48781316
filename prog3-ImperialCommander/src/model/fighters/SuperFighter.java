package model.fighters;

import model.Fighter;
import model.Ship;

// TODO: Auto-generated Javadoc
/**
 * The Class SuperFighter.
 */
public class SuperFighter extends Fighter {

	/**
	 * Instantiates a new super fighter.
	 *
	 * @param mother the mother
	 */
	public SuperFighter(Ship mother) {
		super(mother);
		addVelocity(10);
		addShield(-50);
		addAttack(5);
	}

	/**
	 * Instantiates a new super fighter.
	 *
	 * @param other the other
	 */
	private SuperFighter(SuperFighter other) {
        super(other);	
	}

	/**
	 * Copy.
	 *
	 * @return the fighter
	 */
	@Override
	public Fighter copy() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	@Override
	public char getSymbol() {
		// TODO Auto-generated method stub
		return 0;
	}

}
