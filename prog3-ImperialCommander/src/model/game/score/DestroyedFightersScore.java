package model.game.score;

import model.Fighter;
import model.Side;

// TODO: Auto-generated Javadoc
/**
 * The Class DestroyedFightersScore.
 */
public class DestroyedFightersScore extends Score<Fighter>{

	/**
	 * Instantiates a new destroyed fighters score.
	 *
	 * @param side the side
	 */
	public DestroyedFightersScore(Side side) {
		super(side);
	}
	
	/**
	 * Score.
	 *
	 * @param f the f
	 */
	public void score(Fighter f) {
		if(f != null) {
			score += f.getValue();
		}
	}

}
