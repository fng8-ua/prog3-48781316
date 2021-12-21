package model.game.score;

import model.Side;

// TODO: Auto-generated Javadoc
/**
 * The Class WinsScore.
 */
public class WinsScore extends Score<Integer>{

	/**
	 * Instantiates a new wins score.
	 *
	 * @param side the side
	 */
	public WinsScore(Side side) {
		super(side);
	}
	
	/**
	 * Score.
	 *
	 * @param w the w
	 */
	public void score(Integer w) {
		if(w == 1) {
			score++;
		}
	}


}
