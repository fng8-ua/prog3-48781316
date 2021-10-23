package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class Board.
 */
public class Board {
	
	/** The size. */
	private int size;
	
	/** The fighters. */
	private Map<Coordinate, Fighter> fighters;
	
	/**
	 * Instantiates a new board.
	 *
	 * @param size the size
	 */
	public Board(int size) {
		fighters = new HashMap<Coordinate,Fighter>();
		this.size = size;
	}
	
	/**
	 * Gets the fighter.
	 *
	 * @param c the c
	 * @return the fighter
	 */
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
			return fighters.get(c);	
	}
	
	
	

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {return size;}
	
	/**
	 * On board.
	 *
	 * @param f the f
	 * @return true, if successful
	 */
	public boolean onBoard(Fighter f) {

		if(f.getPosition() != null) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * Removes the fighter.
	 *
	 * @param f the f
	 * @return true, if successful
	 */
	public boolean removeFighter(Fighter f) {
		Objects.requireNonNull(f);
		Coordinate c = f.getPosition();
		boolean borrada = false;
		
		if(c != null) {
			if(fighters.get(c) != null && fighters.get(c).equals(f)){
				fighters.remove(c);
				borrada = true;
			}
		}
		return borrada;
	}
	
	/**
	 * Inside.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	public boolean inside(Coordinate c) {
		Objects.requireNonNull(c);
		
		if((c.getX() > 0 && c.getX() < size-1) && (c.getY() > 0 && c.getY() < size-1)) {
			return true;
		} else {
			return false;
		}
		
	}
							
	/**
	 * Gets the neighborhood.
	 *
	 * @param c the c
	 * @return the neighborhood
	 */
	public Set<Coordinate> getNeighborhood(Coordinate c){
		Objects.requireNonNull(c);
		return c.getNeighborhood();
	}
	

/**
 * Batalla.
 *
 * @param nuestro the nuestro
 * @param enemigo the enemigo
 * @return the int
 */
// Necesitamos un modulo que haga que dos cazas peleen y actualice las posiciones
	public int batalla(Fighter nuestro, Fighter enemigo) {
		int res;
		
		res = nuestro.fight(enemigo);
		if(res == 1) {
			nuestro.getMotherShip().updateResults(1);
			enemigo.getMotherShip().updateResults(-1);
		} else {
			nuestro.getMotherShip().updateResults(-1);
			enemigo.getMotherShip().updateResults(1);
		}
		
		return res;
	}
	
	/**
	 * Esta funci�n dados dos cazas nos dice si son del mismo bando (son amigos), o si por el
	 * contrario son de bandos distintos (enemigos).
	 *
	 * @param f1 the f 1
	 * @param f2 the f 2
	 * @return amigos
	 */
	public boolean sonAmigos(Fighter f1, Fighter f2) {
		boolean amigos;
		
		if(f1.getMotherShip().getSide() == f2.getMotherShip().getSide()) {
			amigos = true;
		} else {
			amigos = false;
		}
		
		return amigos;
	}
	
	/**
	 * Nos dice si va a haber batalla o no queriendo colocar el caza en esa coordenada.
	 *
	 * @param c the c
	 * @param f the f
	 * @return hay
	 */
	public boolean hayBatalla(Coordinate c, Fighter f) {
		Fighter otro;
		boolean hay = false;
		
		if(inside(c)) {
			otro = fighters.get(c);
			if(otro != null) {
				if(!sonAmigos(f,otro)) {
					hay = true;
				} else {
					hay = false;
				}
			} else {
				hay = false;
			}
		} else {
			hay = false;
		}
		
		return hay;
	}
	
	/**
	 * Coloca un fighter si la coordenada est� vac�a o si ganamos la batalla contra
	 * el fighter enemigo que haya en esa posici�n.
	 *
	 * @param c la coordenada
	 * @param f fighter que queremos colocar
	 * @return the int
	 */
	public int launch(Coordinate c, Fighter f) {
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		Fighter otherF;
		int result = 0;
		
		/**
		  -  Comprobamos si est� vac�o
		  -  Si no lo est� comprobamos si tiene un caza de nuestro bando
		  -  Si no lo es luchamos
		 
		 */
		

		if(!inside(c)) {
			result = 0;
		} else {
			otherF = fighters.get(c);
			if(otherF == null || sonAmigos(f,otherF)) {
				
				if(otherF == null) {
					result = 0;
					fighters.put(c,f);
				} else if(sonAmigos(f,otherF)) {
					result = 0;
				}
				
			} else {
				result = batalla(f,otherF);
				if(result == 1) {
					fighters.put(c,f);
				} else {
					fighters.put(c,otherF);
				}
			}
		}		
		return result;		
	}
	
	
	/**
	 * Patrol.
	 *
	 * @param f the f
	 */
	public void patrol(Fighter f) {
		Objects.requireNonNull(f);
		
		
		  if(onBoard(f)) {
			Set<Coordinate> neighbours = getNeighborhood(f.getPosition());
			
			if(inside(f.getPosition())) {
				
				for(Coordinate c: neighbours) {
					
					if(inside(c)) {
						Fighter otherF = fighters.get(c);
						if(otherF != null) {
							if(!sonAmigos(f,otherF)) {
								if(batalla(f,otherF) == 1) {
									removeFighter(otherF);
								} else {
									removeFighter(f);
								}
							}
						}
					} 
					
				}
			}
		}

		
	
		
		
				
	}
}
