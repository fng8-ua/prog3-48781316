package model.game.score;

import model.Side;

public class WinsScore extends Score<Integer>{

	public WinsScore(Side side) {
		super(side);
	}
	
	public void score(Integer w) {
		if(w == 1) {
			score++;
		}
	}


}
