package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	// Devuelve el número de cazas que hay de un tipo
	public int sameType(String type) {
		int counter = 0;
		
		for(Fighter f: fleet) {
			if(f.getType() == type) {
				counter++;
			}
		}
		
		return counter;
	}
	
	// Elimina repetidos de una string
	public void quitarRepetidosString(List<String> tipos) {
		
		Set<String> hashSet = new HashSet<String>(tipos);
		tipos.clear();
		tipos.addAll(hashSet);
	}
	
	public String myFleet() {
		StringBuilder str = new StringBuilder();
		List<String> tipos = new ArrayList<String>();
		
		// Guardamos los nombres de los tipos
		for(Fighter f: fleet) {
			tipos.add(f.getType());
		}
		
		// Quitamos los repetidos
		quitarRepetidosString(tipos);
		
		
		Boolean primero = true;
		
		for(int i = 0; i < tipos.size(); i++) {
			if(primero) {
				primero = false;
				str.append(sameType(tipos.get(i))+"/"+tipos.get(i));
			}
			
			str.append(":"+sameType(tipos.get(i))+"/"+tipos.get(i));

		}
		
		return str.toString();
}
	
	public String toString() {
		// Ship [Alderaan 35/10] 12/XWing:7/AWing
		StringBuilder str = new StringBuilder();
		
		str.append("Ship [" + name + " " + wins + "/" + losses + "] " + myFleet());
		
		return str.toString();
		
	}
}
