package model.game.score;

import java.util.SortedSet;
import java.util.TreeSet;

public class Ranking<ScoreType extends Score<?>> {

	private SortedSet<ScoreType> scoreSet;
	
	public Ranking() {
		scoreSet = new TreeSet<>();
		// TODO Auto-generated constructor stub
	}
	
	public void addScore(ScoreType st) {
		scoreSet.add(st);
	}
	
	public ScoreType getWinner(){
		if(scoreSet.isEmpty()) {
			throw new RuntimeException();
		}
		return scoreSet.first();
	}
	
	public SortedSet<ScoreType> getSortedRanking(){
		return scoreSet;
	}
	
	public String toString() {
		return "| " + scoreSet.first().toString() + " |";
	}
}
