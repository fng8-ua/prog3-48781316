/**
 * @author Fernando Navarro Gonzalez
 * @author 48781316H
 */
package model;

import java.util.Set;
import java.util.TreeSet;

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
	 * @return valor negativo o positivo según cual de las dos es más grande
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
	 * @param Coordenada X
	 * @param Coordenada Y
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Instancia una nueva coordenada copiando otra.
	 *
	 * @param la otra coordenada
	 */
	public Coordinate(Coordinate c) {
		this.x = c.x;
		this.y = c.y;
	}
	
	/**
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
     * @param Lo que se le va a sumar a x
     * @param Lo que se le va a sumar a y
     * @return la nueva coordenada
     */
    public Coordinate add(int x, int y){
    	int newx = this.x + x;
    	int newy = this.y + y;
    	
    	return new Coordinate(newx, newy);
    }

    /**
     * Suma a la coordenada una nueva coordenada pasada por parámetro.
     *
     * @param coordenada a sumar
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
    
    /**
     * Devuelve una cadena de las coordenadas.
     *
     * @return la cadena creada
     */
    @Override
	public String toString() {
		return "[x" + x + ",y" + y + "]";
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
