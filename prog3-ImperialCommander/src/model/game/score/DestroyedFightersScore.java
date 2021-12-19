package model.game.score;

import model.Fighter;
import model.Side;

public class DestroyedFightersScore extends Score<Fighter>{

	public DestroyedFightersScore(Side side) {
		super(side);
	}
	
	public void score(Fighter f) {
		if(f != null) {
			score += f.getValue();
		}
	}

}
