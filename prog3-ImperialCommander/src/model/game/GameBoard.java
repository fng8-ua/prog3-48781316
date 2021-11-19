package model.game;

import java.util.Set;

import model.Board;
import model.Coordinate;
import model.Fighter;
import model.Ship;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.InvalidSizeException;
import model.exceptions.NoFighterAvailableException;
import model.exceptions.OutOfBoundsException;

public class GameBoard extends Board{

	public GameBoard(int size) throws InvalidSizeException {
		super(size);
		
	}
	
	public int numFighters(Side side) {
		int num = 0;
		Set<Coordinate> tablero = board.keySet();
		
		for(Coordinate c : tablero) {
			Fighter f = board.get(c);
			if(f != null && f.getMotherShip().getSide().equals(side)) {
				num++;
			}
		}
		
		
		return num;
	}
	

	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Set<Coordinate> tablero = board.keySet();
		int tam = this.getSize();
		
		builder.append("  "); 
		
		for(int i = 0; i < tam; i++) {
			builder.append(i);
		}
		builder.append("\n");
		
		builder.append("  ");
		for(int i = 0; i < tam; i++) {
			builder.append("-");
		}
		builder.append("\n");
		
		for(int i = 0; i < tam; i++) {
			builder.append(i + "|");
			
			for(Coordinate c : tablero) {
				Fighter f = board.get(c);
				
				
			}
			
			builder.append("\n");
		}
		
		return builder.toString();
	}
	
	public static void main(String[] args) throws InvalidSizeException, FighterAlreadyInBoardException, OutOfBoundsException, NoFighterAvailableException {
		//Creamos los tableros y las naves
		Board board;
		Ship rebelShip, imperialShip;
		
		// Inicializamos los tableros y las naves
		board = new Board(10);
		rebelShip = new Ship("Alderaan",Side.REBEL);
		imperialShip = new Ship("Lanzadera T-4a", Side.IMPERIAL);
		
		// añadimos fighters a las naves
		rebelShip.addFighters("5/XWing:12/AWing:3/YWing:2/XWing");
		
		// los ponemos en el board
		Coordinate c = new Coordinate(1,0);
		board.launch(c, rebelShip.getFirstAvailableFighter("XWing"));
		
		// mostramos el board
		GameBoard gb = new GameBoard(board.getSize());
		System.out.print(gb);
	}

}
