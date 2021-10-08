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
	
	public void updateResults(int r) {}
	
	public Fighter getFirstAvailableFighter(String t) {}
	
	public void purgeFleet() {}
	
	public String showFleet() {}
	
	public String myFleet() {}
	
	public String toString() {}
}
