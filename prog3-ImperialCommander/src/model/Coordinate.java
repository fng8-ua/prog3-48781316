package model;

public class Coordinate {
	private int x;
	private int y;
	
	public class Coordinate implements Comparable<Coordinate>{
		
	}
	
	@Override
	public int compareTo(Coordinate otra) {
		
		if((x - otra.x) < 0)
			return x - otra.x;
			
		
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
