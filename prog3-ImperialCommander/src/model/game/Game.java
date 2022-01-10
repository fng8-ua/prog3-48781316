/**
 * @author Fernando Navarro Gonzalez
 * @author 48781316H
 */
package model.game;

import java.util.Objects;

import model.Side;
import model.exceptions.InvalidSizeException;
import model.game.score.DestroyedFightersScore;
import model.game.score.Ranking;
import model.game.score.WinsScore;

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
			board = new GameBoard(BOARD_SIZE);
			
		} catch (InvalidSizeException e) {
			throw new RuntimeException(e);
		}
		this.imperial.setBoard(board);
		this.rebel.setBoard(board);
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
	 * Gets the ranking.
	 *
	 * 
	 */
	public void getRanking() {
		Ranking<WinsScore> rw = new Ranking<>();
		Ranking<DestroyedFightersScore> dr = new Ranking<>();
		
		rw.addScore(imperial.getWinsScore());
		rw.addScore(rebel.getWinsScore());
		
		dr.addScore(imperial.getDestroyedFightersScore());
		dr.addScore(rebel.getDestroyedFightersScore());
		
		System.out.print("RANKING WINS: " + rw.toString() + "\n");
		System.out.print("RANKING DESTROYED: " + dr.toString() + "\n");
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
			getRanking();
			//Aqu� mostrar los rankings haciendo una funci�n a parte	
			System.out.print("BEFORE IMPERIAL\n");
			System.out.print(board.toString() + "\n");
			System.out.print("\n");
			System.out.print(imperial.showShip() + "\n");
			System.out.print(rebel.showShip() + "\n");
			System.out.print("IMPERIAL(" + imperial.getGameShip().getFightersId("board").size() + "):");
			boolean noExit = imperial.nextPlay();
			
			if(imperial.isFleetDestroyed()) {
				winner = Side.REBEL;
			} else if(rebel.isFleetDestroyed()) {
				winner = Side.IMPERIAL;
			}
			
			if(noExit) {
				System.out.print("AFTER IMPERIAL, BEFORE REBEL\n");
				System.out.print(board.toString() + "\n");
				System.out.print("\n");
				System.out.print(imperial.showShip() + "\n");
				System.out.print(rebel.showShip() + "\n");
				
				if(winner == null) {
					//before rebel
					System.out.print("REBEL(" + rebel.getGameShip().getFightersId("board").size() + "):");
					noExit = rebel.nextPlay();
					
					if(noExit) {
						System.out.print("AFTER REBEL\n");
						System.out.print(board.toString() + "\n");
						System.out.print("\n");
						System.out.print(imperial.showShip() + "\n");
						System.out.print(rebel.showShip() + "\n");
						imperial.purgeFleet();
						rebel.purgeFleet();
						if(rebel.isFleetDestroyed()) {
							winner = Side.IMPERIAL;
						} else if(imperial.isFleetDestroyed()) {
							winner = Side.REBEL;
						}
					} else {
						winner = Side.IMPERIAL;
					}
				}
			} else {
				winner = Side.REBEL;
			}
		}
		
		getRanking();
		return winner;
	}
}
