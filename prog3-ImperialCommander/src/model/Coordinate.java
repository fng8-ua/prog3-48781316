package model;

import java.util.Set;
import java.util.TreeSet;

public class Coordinate implements Comparable<Coordinate> {
	private int x;
	private int y;
	
	
	@Override
	public int compareTo(Coordinate otra) {
		
		if((x - otra.x) != 0)
			return x - otra.x;
		else
			return y - otra.y;
				
	}
		
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coordinate(Coordinate c) {
		this.x = c.x;
		this.y = c.y;
	}
	
	public int getX(){
	    return x;
	}

    public int getY(){
        return y;
    }

    public Coordinate add(int x, int y){
    	int newx = this.x + x;
    	int newy = this.y + y;
    	
    	return new Coordinate(newx, newy);
    }

    public Coordinate add(Coordinate c){
    	int newx = this.x + c.x;
    	int newy = this.y + c.y;
    	
    	return new Coordinate(newx, newy);
    }

    
public Set getNeighborhood() {
		
		Set<Coordinate> vecinas = new TreeSet<Coordinate>();
		
		//Ahora tenemos que recorrer las 9 posiciones de la matriz alrededor de la coordenada
		
			
		Coordinate aux = new Coordinate(x,y);
		
		for(int i = y-1; i <= y+1; i++) {
			for(int j = x-1; j <= x+1; j++) {
				if(j != aux.x && i != aux.y) {
					vecinas.add(new Coordinate(j,i));
				}
			}
		}
		
		
		return vecinas;
	}
    
    @Override
	public String toString() {
		return "[x" + x + ",y" + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
    
    
    
}
