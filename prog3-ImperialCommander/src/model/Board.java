package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Board {
	private int size;
	
	public Board(int size) {
		Map<Coordinate, Fighter> = new HashMap<Coordinate,Fighter>();
	}
	
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		Fighter f;
		
		
		
		return f;
	}
	
	public int getSize() {return size;}
	
	public Boolean removeFighter(Fighter f) {
		Objects.requireNonNull(f);
	}
	
	public Boolean inside(Coordinate c) {
		Objects.requireNonNull(c);
		
	}
	
	public Set<Coordinate> getNeighborhood(Coordinate c){
		Objects.requireNonNull(c);
		
	}
	
	public int launch(Coordinate c, Fighter f) {
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		
		
	}
	
	public void patrol(Fighter f) {
		Objects.requireNonNull(f);
		
	}
}
