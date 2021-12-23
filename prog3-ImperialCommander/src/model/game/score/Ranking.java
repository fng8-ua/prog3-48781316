/**
 * @author Fernando Navarro Gonzalez
 * @author 48781316H
 */
package model.game.score;

import java.util.SortedSet;
import java.util.TreeSet;

// TODO: Auto-generated Javadoc
/**
 * The Class Ranking.
 *
 * @param <ScoreType> the generic type
 */
public class Ranking<ScoreType extends Score<?>> {

	/** The score set. */
	private SortedSet<ScoreType> scoreSet;
	
	/**
	 * Instantiates a new ranking.
	 */
	public Ranking() {
		scoreSet = new TreeSet<>();
		
	}
	
	/**
	 * Adds the score.
	 *
	 * @param st the st
	 */
	public void addScore(ScoreType st) {
		scoreSet.add(st);
	}
	
	/**
	 * Gets the winner.
	 *
	 * @return the winner
	 */
	public ScoreType getWinner(){
		return scoreSet.first();
	}
	
	/**
	 * Gets the sorted ranking.
	 *
	 * @return the sorted ranking
	 */
	public SortedSet<ScoreType> getSortedRanking(){
		return scoreSet;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		// recorrer el scoreSet
		StringBuilder str = new StringBuilder();
		
		str.append("|");
		for(ScoreType score: scoreSet) {
			str.append(score.toString() + "|");
			
		}
		
		return str.toString();
	}
}
