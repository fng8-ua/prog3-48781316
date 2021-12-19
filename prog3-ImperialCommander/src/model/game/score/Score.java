package model.game.score;

import model.Side;

public abstract class Score<T> implements Comparable<Score<T>> {
	
	protected int score;
	private Side side;
	
	public Score(Side side){
		this.side = side;
		score = 0;
	}
	
	public int getScore() {
		return score;
	}
	
	public String toString() {
		return "Player " + side + ": " + score;
	}
	
	public int compareTo(Score<T> other) {
		
		if(this.score < other.score) {
			return 1;
		} else if(this.score > other.score){
			return -1;
		} else if(this.score == other.score){
			return this.side.compareTo(other.side);
		} else {
			return 0;
		}
	}
	
	public void score(T sc) {
		score++;
	}
}
