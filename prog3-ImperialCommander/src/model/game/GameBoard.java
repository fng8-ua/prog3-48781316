/**
 * @author Fernando Navarro Gonzalez
 * @author 48781316H
 */

package model.game;

import java.util.Set;

import model.Board;
import model.Coordinate;
import model.Fighter;
import model.Side;
import model.exceptions.InvalidSizeException;


// TODO: Auto-generated Javadoc
/**
 * The Class GameBoard.
 */
public class GameBoard extends Board{

	/**
	 * Instantiates a new game board.
	 *
	 * @param size the size
	 * @throws InvalidSizeException the invalid size exception
	 */
	public GameBoard(int size) throws InvalidSizeException {
		super(size);
		
	}
	
	/**
	 * Num fighters.
	 *
	 * @param side the side
	 * @return the int
	 */
	public int numFighters(Side side) {
		int num = 0;
		Set<Coordinate> tablero = board.keySet();
		
		for(Coordinate c : tablero) {
			Fighter f = board.get(c);
			if(f.getSide() == side) {
				num++;
			}
		}
		
		
		return num;
	}
	

	/**
	 * To string.
	 *
	 * @return the string
	 */
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
				Coordinate c = new Coordinate(j,i);
				Fighter f = board.get(c);
				
				if(f == null) { 
					builder.append(" ");
				} else {
					builder.append(f.getSymbol()); 
				}
			}
			
			if(i != tam-1) {
				builder.append("\n");
			}
			
		}
		
		return builder.toString();
	}

}
