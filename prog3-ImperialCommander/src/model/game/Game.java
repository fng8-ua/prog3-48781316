package model.game;

import java.util.Objects;

import model.Side;
import model.exceptions.InvalidSizeException;

public class Game {
	private final int BOARD_SIZE = 10;
	private IPlayer rebel;
	private IPlayer imperial;
	private GameBoard board;
	
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
	
	
	public GameBoard getGameBoard() {
		return board;
	}
	
	public Side play() {
		Side ganador = null;
		imperial.initFighters();
		rebel.initFighters();
		
		while(ganador == null) {
			
			System.out.println("BEFORE IMPERIAL" + "\n");
			board.toString();
			
			imperial.purgeFleet();
			rebel.purgeFleet();
			
			imperial.showShip();
			System.out.println("\n");
			rebel.showShip();
			
			//TODO juega imperial
			imperial.nextPlay();
			if(imperial.isFleetDestroyed()){
				ganador = Side.REBEL;
				return Side.REBEL;
			} else if(rebel.isFleetDestroyed()) {
				ganador = Side.IMPERIAL;
				return Side.IMPERIAL;
			} else {
				System.out.println(Side.IMPERIAL + "(" + imperial.getGameShip().getFightersId("board").size() + "): AFTER IMPERIAL, BEFORE REBEL");
				board.toString();
				
				imperial.purgeFleet();
				rebel.purgeFleet();
				
				//TODO cambiar salida por pantalla usando showFleet
				imperial.showShip();
				System.out.println("\n");
				rebel.showShip();
				
								
				//TODO juega rebel
				rebel.nextPlay();
				if(imperial.isFleetDestroyed()) {
					ganador = Side.REBEL;
					return Side.REBEL;
					
				} else if(rebel.isFleetDestroyed()){
					ganador = Side.IMPERIAL;
					return Side.IMPERIAL;
					
				}  else {
					System.out.println(Side.REBEL + "(" + rebel.getGameShip().getFightersId("board").size() + "AFTER REBEL");
					board.toString();
					
					imperial.purgeFleet();
					rebel.purgeFleet();
					
					imperial.showShip();
					System.out.println("\n");
					rebel.showShip();
				}
				
				
				
			}
			
			
			
		}
		return ganador;
		
	
		 
	}
}
