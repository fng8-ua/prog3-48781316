package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Board {
	private int size;
	private Map<Coordinate, Fighter> fighters;
	
	public Board(int size) {
		fighters = new HashMap<Coordinate,Fighter>();
		this.size = size;
	}
	
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		Fighter f = fighters.get(new Coordinate(c));
		
		return f;
	}
	
	

	public int getSize() {return size;}
	
	public Boolean removeFighter(Fighter f) {
		Objects.requireNonNull(f);
	}
	
	public Boolean inside(Coordinate c) {
		Objects.requireNonNull(c);
		
		if((c.getX() > 0 && c.getX() < size-1) && (c.getY() > 0 && c.getY() < size-1))
		
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
