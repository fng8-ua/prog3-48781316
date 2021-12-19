/**
 * @author Fernando Navarro Gonzalez
 * @author 48781316H
 */
package model.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.Board;
import model.Coordinate;
import model.Fighter;
import model.Ship;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;
import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

// TODO: Auto-generated Javadoc
/**
 * The Class GameShip.
 */
public class GameShip extends Ship{
	
	private DestroyedFightersScore destroyedFightersScore;
	private WinsScore winsScore;
	
	@Override
	public void updateResults(int r, Fighter f) {
		updateResults(r, f);
		
		if(r == 1) {
			//TODO
		}
	}
	
	public DestroyedFightersScore getDestroyedFightersScore() {
		return destroyedFightersScore;
	}
	
	public WinsScore getWinsScore() {
		return winsScore;
	}
	
	
	
	/**
	 * Instantiates a new game ship.
	 *
	 * @param name the name
	 * @param side the side
	 */
	public GameShip(String name, Side side) {
		super(name, side);
		
	}
	
	/**
	 * Checks if is fleet destroyed.
	 *
	 * @return true, if is fleet destroyed
	 */
	public boolean isFleetDestroyed() {
		boolean fleetDestroyed = true;
		
		for(Fighter f: fleet) {
			if(f != null) {
				if(!f.isDestroyed()) {
					return false;
				}
			}
		}
		
		return fleetDestroyed;
	}
	
	/**
	 * Gets the fighter.
	 *
	 * @param id the id
	 * @return the fighter
	 * @throws WrongFighterIdException the wrong fighter id exception
	 */
	private Fighter getFighter(int id) throws WrongFighterIdException {
		boolean encontrado = false;
		Fighter f = null;
		for(Fighter f1: fleet) {
			if(f1.getId() == id && !f1.isDestroyed() && encontrado == false) {
				encontrado = true;
				f = f1;
			}
		}
		
		if(!encontrado) {
			throw new WrongFighterIdException(id);
		}
		
		return f;
		
	}
	
	/**
	 * Gets the fighters id.
	 *
	 * @param where the where
	 * @return the fighters id
	 */
	@SuppressWarnings("null")
	public List<Integer> getFightersId(String where){
		ArrayList<Integer> lista = new ArrayList<Integer>();
		
		
		if(where == "board") {
			for(Fighter f: fleet) {
				if(!f.isDestroyed() && f.getPosition() != null) {
					lista.add(f.getId());	
				}
				
			}
		} else if(where.equals("ship")) {
			for(Fighter f: fleet) {
				if(f.getPosition() == null && !f.isDestroyed()) {
					lista.add(f.getId());
				}
			}
		} else {
			for(Fighter f: fleet) {
				if(!f.isDestroyed()) {
					lista.add(f.getId());
				}
			}
					
		}
		
		return lista;
	}
	
	/**
	 * Launch.
	 *
	 * @param id the id
	 * @param c the c
	 * @param b the b
	 * @throws WrongFighterIdException the wrong fighter id exception
	 * @throws FighterAlreadyInBoardException the fighter already in board exception
	 * @throws OutOfBoundsException the out of bounds exception
	 */
	public void launch(int id, Coordinate c, Board b) throws WrongFighterIdException, FighterAlreadyInBoardException, OutOfBoundsException {
		Objects.requireNonNull(id);
		Objects.requireNonNull(c);
		Objects.requireNonNull(b);
	
		
		b.launch(c, getFighter(id));
		
		
		
		
	}
	
	/**
	 * Patrol.
	 *
	 * @param id the id
	 * @param b the b
	 * @throws WrongFighterIdException the wrong fighter id exception
	 * @throws FighterNotInBoardException the fighter not in board exception
	 */
	public void patrol(int id, Board b) throws WrongFighterIdException, FighterNotInBoardException {
		Objects.requireNonNull(id);
		Objects.requireNonNull(b);
		
	
		
		b.patrol(getFighter(id));
	}
	
	/**
	 * Improve fighter.
	 *
	 * @param id the id
	 * @param qty the qty
	 * @param b the b
	 * @throws WrongFighterIdException the wrong fighter id exception
	 */
	public void improveFighter(int id, int qty, Board b) throws WrongFighterIdException {
		Objects.requireNonNull(id);
		Objects.requireNonNull(qty);
		Objects.requireNonNull(b);
		
		Fighter f = getFighter(id);
		
		
		
		
		try {
			b.removeFighter(f);
			f.setPosition(null);
		} catch (FighterNotInBoardException e) {
			// no se hace nada
		}
		
		if(qty % 2 != 0) { // es impar
			f.addAttack((qty-1)/2);
			f.addShield((qty+1)/2);
		} else {
			f.addAttack(qty/2);
			f.addShield(qty/2);
		}
		
		
		
	}

}
