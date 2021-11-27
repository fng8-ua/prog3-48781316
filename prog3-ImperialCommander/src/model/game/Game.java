package model.game;

import model.Side;
import model.exceptions.InvalidSizeException;

public class Game {
	private final int BOARD_SIZE = 10;
	private IPlayer rebel;
	private IPlayer imperial;
	private GameBoard board;
	
	public Game(IPlayer imperial, IPlayer rebel) {
		this.rebel = rebel;
		this.imperial = imperial;
		
		try {
			this.board = new GameBoard(BOARD_SIZE);
		} catch (InvalidSizeException e) {
			throw new RuntimeException(e);
		}
	}
	
	public GameBoard getGameBoard() {
		return board;
	}
	
	public Side play() {
		Side ganador;
		imperial.initFighters();
		rebel.initFighters();
		
		while(imperial.nextPlay() && rebel.nextPlay() && 
			  !imperial.isFleetDestroyed() && !rebel.isFleetDestroyed()) {
			StringBuilder output = new StringBuilder();
			
			System.out.println("BEFORE IMPERIAL" + "\n");
			board.toString();
			imperial.showShip();
			System.out.println("\n");
			rebel.showShip();
			
			//TODO juega imperial
			imperial.nextPlay();
			if(rebel.isFleetDestroyed()) {
				ganador = Side.IMPERIAL;
				return Side.IMPERIAL;
			} else {
				System.out.println(Side.IMPERIAL + "(" + imperial.getGameShip().getFightersId("board").size() + "): AFTER IMPERIAL, BEFORE REBEL");
				board.toString();
				imperial.showShip();
				System.out.println("\n");
				rebel.showShip();
				
				//TODO juega rebel
				rebel.nextPlay();
				if(imperial.isFleetDestroyed()) {
					ganador = Side.REBEL;
					return Side.REBEL;
				} else {
					System.out.println(Side.REBEL + "(" + rebel.getGameShip().getFightersId("board").size() + "AFTER REBEL");
					board.toString();
					imperial.showShip();
					System.out.println("\n");
					rebel.showShip();
				}
				
				
			}
			
			
			
		}
		return ganador;
		
		
	}
}
