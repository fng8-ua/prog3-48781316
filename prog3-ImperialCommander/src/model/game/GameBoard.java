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
		
		// Recorremos las filas
		for(int i = 0; i < tam; i++) {
			builder.append(i + "|");
			
			// Recorremos las columnas
			for(int j = 0; j < tam; j++) {
				Coordinate c = new Coordinate(i,j);
				Fighter f = board.get(c);
				
				if(f == null) { 
					builder.append(" ");
				} else {
					builder.append(f.getSymbol()); 
				}
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
		Coordinate c = new Coordinate(1,1);
		board.launch(c, rebelShip.getFirstAvailableFighter("XWing"));
		
		
		// mostramos el board
		GameBoard gb = new GameBoard(board.getSize());
		gb.launch(c, rebelShip.getFirstAvailableFighter("XWing"));
		
		Coordinate c1 = new Coordinate(4,9);
		gb.launch(c1, rebelShip.getFirstAvailableFighter("XWing"));
		
		Coordinate c2 = new Coordinate(5,9);
		gb.launch(c2, rebelShip.getFirstAvailableFighter("YWing"));
		
		Coordinate c3 = new Coordinate(3,3);
		gb.launch(c3, rebelShip.getFirstAvailableFighter("AWing"));
		System.out.print(gb);
	}

}
