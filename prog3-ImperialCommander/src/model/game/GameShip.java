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

public class GameShip extends Ship{

	public GameShip(String name, Side side) {
		super(name, side);
		
	}
	
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
	
	public void launch(int id, Coordinate c, Board b) throws WrongFighterIdException, FighterAlreadyInBoardException, OutOfBoundsException {
		Objects.requireNonNull(id);
		Objects.requireNonNull(c);
		Objects.requireNonNull(b);
		
		Fighter f = getFighter(id);
		
		b.launch(c, f);
		
	}
	
	public void patrol(int id, Board b) throws WrongFighterIdException, FighterNotInBoardException {
		Objects.requireNonNull(id);
		Objects.requireNonNull(b);
		
		Fighter f = getFighter(id);
		
		b.patrol(f);
	}
	
	public void improveFighter(int id, int qty, Board b) throws WrongFighterIdException {
		Objects.requireNonNull(id);
		Objects.requireNonNull(qty);
		Objects.requireNonNull(b);
		
		Fighter f = getFighter(id);
		
		
		try {
			b.removeFighter(f);
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
