package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import model.exceptions.FighterAlreadyInBoardException;
//import model.FighterFactory;
import model.exceptions.FighterIsDestroyedException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.InvalidSizeException;
import model.exceptions.OutOfBoundsException;

// TODO: Auto-generated Javadoc
/**
 * The Class Board.
 */
public class Board {
	
	/** The size. */
	private int size;
	
	/** The fighters. */
	private Map<Coordinate, Fighter> board;
	
	/**
	 * Instantiates a new board.
	 *
	 * @param size the size
	 * @throws InvalidSizeException 
	 */
	public Board(int size) throws InvalidSizeException {
		if(size < 5) {
			throw new InvalidSizeException(size);
		}
		board = new HashMap<Coordinate,Fighter>();
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
			Fighter f = null;
			
			if(board.containsKey(c)) {
				f = board.get(c);
				return f.copy();
			}			
			return null;
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
	 * @throws FighterNotInBoardException si el fighter no está en el tablero
	 */
	public boolean removeFighter(Fighter f) throws FighterNotInBoardException {
		Objects.requireNonNull(f);
		
		Coordinate c = f.getPosition();
		boolean borrada = false;
		
		if(c != null) {
			if(board.get(c) != null && board.get(c).equals(f)){
				board.remove(c);
				borrada = true;
			} else {
				throw new FighterNotInBoardException(f);
			}
		} else {
			throw new FighterNotInBoardException(f);
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
	 * @throws OutOfBoundsException si la coordenada pasada no está en el tablero
	 */
	public Set<Coordinate> getNeighborhood(Coordinate c) throws OutOfBoundsException{
		Objects.requireNonNull(c);
		
		if(!inside(c)) {
			throw new OutOfBoundsException(c);
		}
		
		TreeSet<Coordinate> neighborhoodTS = new TreeSet<Coordinate>();
		Set<Coordinate> neighborhood = c.getNeighborhood();
		
		for(Coordinate cor: neighborhood) {
			if(inside(cor)) {
				neighborhoodTS.add(cor);
			}
		}
		
		
		
		return neighborhoodTS;
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
		
			try {
				res = nuestro.fight(enemigo);
				nuestro.getMotherShip().updateResults(res);
				enemigo.getMotherShip().updateResults(-res);
			} catch (FighterIsDestroyedException e) {
				throw new RuntimeException();
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
	 * Coloca un fighter si la coordenada est� vac�a o si ganamos la batalla contra
	 * el fighter enemigo que haya en esa posici�n.
	 *
	 * @param c la coordenada
	 * @param f fighter que queremos colocar
	 * @return the int
	 * @throws FighterAlreadyInBoardException si el caza ya está en el tablero
	 * @throws OutOfBoundsException si la posición está fuera del tablero
	 */
	public int launch(Coordinate c, Fighter f) throws FighterAlreadyInBoardException, OutOfBoundsException {
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		Fighter enemy;
		int result = 0;
		
		/**
		  -  Comprobamos si est� vac�o
		  -  Si no lo est� comprobamos si tiene un caza de nuestro bando
		  -  Si no lo es luchamos
		 
		 */
		
		if(f.getPosition() != null) {
			throw new FighterAlreadyInBoardException(f);
		}
		

		if(!inside(c)) {
			throw new OutOfBoundsException(c);
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
	 * @throws FighterNotInBoardException 
	 *
	 */
	public void patrol(Fighter f) throws FighterNotInBoardException {
		Objects.requireNonNull(f);
		int battleResult;
		
		if(f.getPosition() == null || board.get(f.getPosition()) == null) {
			throw new FighterNotInBoardException(f);
		} else {
			try {
				Set<Coordinate> neighbours = getNeighborhood(f.getPosition());
				
				for(Coordinate c: neighbours) {
					if(board.get(c) != null && !sonAmigos(f,board.get(c))) {
						
					}
				}
			} catch (OutOfBoundsException e) {
				throw new RuntimeException();
			}
		}
		
		
		
	}
}
