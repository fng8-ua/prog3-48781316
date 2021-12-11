/**
 * @author Fernando Navarro Gonzalez
 * @author 48781316H
 */
package model.game;

import java.util.Objects;

import model.Side;
import model.exceptions.InvalidSizeException;

// TODO: Auto-generated Javadoc
/**
 * The Class Game.
 */
public class Game {
	
	/** The board size. */
	private final int BOARD_SIZE = 10;
	
	/** The rebel. */
	private IPlayer rebel;
	
	/** The imperial. */
	private IPlayer imperial;
	
	/** The board. */
	private GameBoard board;
	
	/**
	 * Instantiates a new game.
	 *
	 * @param imperial the imperial
	 * @param rebel the rebel
	 */
	public Game(IPlayer imperial, IPlayer rebel) {
		Objects.requireNonNull(imperial);
		Objects.requireNonNull(rebel);
		
		this.rebel = rebel;
		this.imperial = imperial;
		
		try {
			this.board = new GameBoard(BOARD_SIZE);
			imperial.setBoard(board);
			rebel.setBoard(board);
		} catch (InvalidSizeException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * Gets the game board.
	 *
	 * @return the game board
	 */
	public GameBoard getGameBoard() {
		return board;
	}
	
	/**
	 * Show ships data.
	 *
	 * @param rebel the rebel
	 * @param imperial the imperial
	 * @param board the board
	 */
	private void showShipsData(IPlayer rebel, IPlayer imperial, GameBoard board) {
		// showData(rebel,imperial,board,"before")
		// showData(rebel,imperial,board,"middle")
		// showData(rebel,imperial,board,"after")
		StringBuilder str = new StringBuilder();
	
		str.append(board.toString());
		str.append("\n");
		str.append(imperial.getGameShip().toString());
		str.append("\n");
		str.append(rebel.getGameShip().toString());
		str.append("\n");
		
	}
	
	/**
	 * Play.
	 *
	 * @return the side
	 */
	public Side play() {
		Side winner = null;

		
		imperial.initFighters();
		rebel.initFighters();
		
		while(winner == null) {
			//before imperial
			System.out.println("BEFORE IMPERIAL\n");
			System.out.println(board.toString());
			showShipsData(rebel,imperial,board);
			System.out.println("IMPERIAL(" + imperial.getGameShip().getFightersId("board").size() + "): ");
			
			
			boolean noExit = imperial.nextPlay();
			imperial.purgeFleet();
			
			if(noExit) {
				
				//after imperial
				System.out.println("AFTER IMPERIAL, BEFORE REBEL\n");
				System.out.println(board.toString());
				showShipsData(rebel,imperial,board);
				
				if(imperial.isFleetDestroyed()) {
					winner = Side.REBEL;
					return Side.REBEL;
				} else if(rebel.isFleetDestroyed()) {
					winner = Side.IMPERIAL;
					return Side.IMPERIAL;
				} else {
					//before rebel
					System.out.println("REBEL(" + rebel.getGameShip().getFightersId("board").size() + "): ");
					
					noExit = rebel.nextPlay();
					rebel.purgeFleet();
					
					if(noExit) {
						
						//after rebel
						System.out.println("AFTER REBEL\n");
						System.out.println(board.toString());
						showShipsData(rebel,imperial,board);
						
						if(rebel.isFleetDestroyed()) {
							winner = Side.IMPERIAL;
							return Side.IMPERIAL;
						} else if(imperial.isFleetDestroyed()) {
							winner = Side.REBEL;
							return Side.REBEL;
						}
					}
					
					
				}
				
			}	
		}
		
		imperial.purgeFleet();
		rebel.purgeFleet();
		
		return winner;
	}
}
