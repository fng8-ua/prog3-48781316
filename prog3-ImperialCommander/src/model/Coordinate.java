/**
 * @author Fernando Navarro Gonzalez
 * @author 48781316H
 */
package model;

import java.util.Set;
import java.util.TreeSet;

// TODO: Auto-generated Javadoc
/**
 * La clase coordinate.
 */
public class Coordinate implements Comparable<Coordinate> {
	
	/** Coordenada X. */
	private int x;
	
	/** Coordenada Y. */
	private int y;
	
	
	/**
	 * Compara dos coordenadas.
	 *
	 * @param otra coordenada
	 * @return valor negativo o positivo seg�n cual de las dos es m�s grande
	 */
	@Override
	public int compareTo(Coordinate otra) {
		
		if((x - otra.x) != 0)
			return x - otra.x;
		else
			return y - otra.y;
				
	}
		
	
	/**
	 * Crea una nueva coordenada.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Instancia una nueva coordenada copiando otra.
	 *
	 * @param c the c
	 */
	public Coordinate(Coordinate c) {
		this.x = c.x;
		this.y = c.y;
	}
	
	/**vecinas.add(new Coordinate(x-1,y-1));
	 * Devuelve la x.
	 *
	 * @return x
	 */
	public int getX(){
	    return x;
	}

    /**
     * Devuelve la y.
     *
     * @return y
     */
    public int getY(){
        return y;
    }

    /**
     * Suma a la coordenada una nueva coordenada de la cual se pasan solo los valores.
     *
     * @param x the x
     * @param y the y
     * @return la nueva coordenada
     */
    public Coordinate add(int x, int y){
    	int newx = this.x + x;
    	int newy = this.y + y;
    	
    	return new Coordinate(newx, newy);
    }

    /**
     * Suma a la coordenada una nueva coordenada pasada por par�metro.
     *
     * @param c the c
     * @return la nueva coordenada
     */
    public Coordinate add(Coordinate c){
    	int newx = this.x + c.x;
    	int newy = this.y + c.y;
    	
    	return new Coordinate(newx, newy);
    }

    
/**
 * Devuelve las posiciones vecinas a esta coordenada.
 * 
 * ej:
 * 
 * 	   o   o   o
 * 	   o   x   o   
 *     o   o   o   
 *  
 *  
 *  Se devuelven las posiciones marcadas por o.
 * @return vector de coordenadas vecinas
 */
public Set<Coordinate> getNeighborhood() {
		
		Set<Coordinate> vecinas = new TreeSet<Coordinate>();
		
		//Ahora tenemos que recorrer las 9 posiciones de la matriz alrededor de la coordenada
		
		for(int j = this.y-1; j <= this.y + 1; j++) {
			for(int i = this.x-1; i <= this.x + 1; i++) {
				
				if(i==x && j==y) {}
				else {
				vecinas.add(new Coordinate(i,j));
				}
			}
		}
		
		
		
		return vecinas;
	}
    
    /**
     * Devuelve una cadena de las coordenadas.
     *
     * @return la cadena creada
     */
    @Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
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
