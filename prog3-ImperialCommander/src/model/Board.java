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
	private Map<Coordinate, Fighter_prac2> board;
	
	/**
	 * Instantiates a new board.
	 *
	 * @param size the size
	 */
	public Board(int size) {
		board = new HashMap<Coordinate,Fighter_prac2>();
		this.size = size;
	}
	
	/**
	 * Gets the fighter.
	 *
	 * @param c the c
	 * @return the fighter
	 */
	public Fighter_prac2 getFighter(Coordinate c) {
		Objects.requireNonNull(c);
			Fighter_prac2 f = null;
			
			if(board.containsKey(c)) {
				f = board.get(c);
				f = new Fighter_prac2(board.get(c));
			}			
			return f;
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
	public boolean onBoard(Fighter_prac2 f) {

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
	public boolean removeFighter(Fighter_prac2 f) {
		Objects.requireNonNull(f);
		
		Coordinate c = f.getPosition();
		boolean borrada = false;
		
		if(c != null) {
			if(board.get(c) != null && board.get(c).equals(f)){
				board.remove(c);
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
		
		if((c.getX() >= 0 && c.getX() <= size-1) && (c.getY() >= 0 && c.getY() <= size-1)) {
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
	public int batalla(Fighter_prac2 nuestro, Fighter_prac2 enemigo) {
		int res;
		
		res = nuestro.fight(enemigo);
		
			nuestro.getMotherShip().updateResults(res);
			enemigo.getMotherShip().updateResults(-res);
		
		
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
	public boolean sonAmigos(Fighter_prac2 f1, Fighter_prac2 f2) {
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
	public boolean hayBatalla(Coordinate c, Fighter_prac2 f) {
		Fighter_prac2 otro;
		boolean hay = false;
		
		if(inside(c)) {
			otro = board.get(c);
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
	public int launch(Coordinate c, Fighter_prac2 f) {
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		Fighter_prac2 enemy;
		int result = 0;
		
		/**
		  -  Comprobamos si est� vac�o
		  -  Si no lo est� comprobamos si tiene un caza de nuestro bando
		  -  Si no lo es luchamos
		 
		 */
		

		if(!inside(c)) {
			result = 0;
		} else {
			if(board.containsKey(c)) {
				enemy = board.get(c);
				
				if(!sonAmigos(f,enemy)) {
					result = batalla(f,enemy);
					
					if(result == 1) {
						board.put(c,f);
						f.setPosition(c);
						enemy.setPosition(null);
					}
				}
			} else {
				board.put(c,f);
				f.setPosition(c);
				
			}
		}
		
		return result;
	
	}
	
	
	/**
	 * Patrol.
	 *
	 * @param f the f
	 */
	public void patrol(Fighter_prac2 f) {
		Objects.requireNonNull(f);
		Coordinate pos = f.getPosition();
		Fighter_prac2 enemy;
		Set<Coordinate> neighbours;
		int res;
		
		if(pos != null) {
			Fighter_prac2 nuestro = board.get(pos);
			
			if(nuestro == f) {
				neighbours = getNeighborhood(pos);
				
				for(Coordinate c: neighbours) {
					enemy = board.get(c);
					
					if(enemy != null && !sonAmigos(f,enemy)) {
						res = batalla(f,enemy);
						
						if(res == 1) {
							board.remove(c);
							enemy.setPosition(null);
						} else {
							board.remove(pos);
							f.setPosition(null);
							break;
						}
					}
				}
			}
		}
	}
}
