package model.game.score;

import model.Side;

// TODO: Auto-generated Javadoc
/**
 * The Class Score.
 *
 * @param <T> the generic type
 */
public abstract class Score<T> implements Comparable<Score<T>> {
	
	/** The score. */
	protected int score;
	
	/** The side. */
	private Side side;
	
	/**
	 * Constructor que almacena el bando recibido como argumento 
	 * e inicializa el atributo score a cero.
	 *
	 * @param side the side
	 */
	public Score(Side side){
		this.side = side;
		score = 0;
	}
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return "Player " + side + ": " + score;
	}
	
	/**
	 * Compare to.
	 *
	 * @param other the other
	 * @return the int
	 */
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
	
	/**
	 * Score.
	 *
	 * @param sc the sc
	 */
	public void score(T sc) {
		score++;
	}
}
