package model;

import java.util.ArrayList;
import java.util.List;

public class Ship {
	
	private String name;
	private Side side;
	private int wins;
	private int losses;
	private ArrayList<Fighter> fleet;

	public Ship(String name, Side side) {
		this.name = name;
		this.side = side;
		wins = losses = 0;
	}
	
	public String getName() {return name;}
	public Side getSide() {return side;}
	public int getWins() {return wins;}
	public int getLosses() {return losses;}
	public List<Fighter> getFleetTest() {return fleet;}
	
	public void addFighters(String fd) {
		
	}
	
	public void updateResults(int r) {
		if(r == 1)
			wins++;
		if(r == -1)
			losses++;
	}
	
	public Fighter getFirstAvailableFighter(String t) {
		
		for(Fighter faf: fleet) {
			if(!t.isEmpty()) {
				if(!faf.isDestroyed() && faf.getType() == t) {
					return faf;
				}
					
			} else {
				if(!faf.isDestroyed()) {
					return faf;
				}
			}
		}
	}
	
	public void purgeFleet() {
		for(Fighter f: fleet) {
			if(f.isDestroyed()) {
				fleet.remove(f);
			}
		}
	}
	
	public String showFleet() {
		StringBuilder str = new StringBuilder();
		
		if(!fleet.isEmpty()) {
			for(Fighter f: fleet) {
				str.append(f.toString());
				
				if(f.isDestroyed()) {
					str.append(" (X) ");
				}
				
				str.append("\n");
			}
			
			return str.toString();
		} else {
			return str.toString();
		}	
	}
	
	// Cuenta el numero de fighters del mismo tipo
	public int typeCounter(String typeName) {
		int counter = 0;
		
		for(Fighter f: fleet) {
			if(f.getType() == typeName) {
				counter++;
			}
		}
		return counter;
	}
	
	public String myFleet() {
		StringBuilder str = new StringBuilder();
		
		// Estoy pensando que si tengo mi función typeCounter voy a tener que llamarla
		// para todos los fighter.
		
		
	}
	
	public String toString() {}
}
