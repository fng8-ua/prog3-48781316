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
	
	public boolean inside(Coordinate c) {
		Objects.requireNonNull(c);
		
		if((c.getX() > 0 && c.getX() < size-1) && (c.getY() > 0 && c.getY() < size-1)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public Set<Coordinate> getNeighborhood(Coordinate c){
		Objects.requireNonNull(c);
		return c.getNeighborhood();
	}
	
	/**
	 * Coloca un fighter si la coordenada está vacía o si ganamos la batalla contra
	 * el fighter enemigo que haya en esa posición.
	 * 
	 * @param c la coordenada
	 * @param f fighter que queremos colocar
	 * @return
	 */
	public int launch(Coordinate c, Fighter f) {
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		
		int result;
		Fighter posible;
		
		if(inside(c)) {
			posible = fighters.get(c);
			if(posible == null) {
				fighters.put(c, f);
				return 0;
			} else {
				if(posible.getSide() != f.getSide()){
					result = f.fight(posible);
					if(result == 1) {
						f.getMotherShip().updateResults(1);
						posible.getMotherShip().updateResults(-1);
						fighters.remove(c);
						fighters.put(c,f);
						return result;
					} else {
						f.getMotherShip().updateResults(-1);
						posible.getMotherShip().updateResults(1);
						return result;
					}
				} else {
					return 0;
				}
			}
		}
		
		
	}
	
	public void patrol(Fighter f) {
		Objects.requireNonNull(f);
		
	}
}
