package model.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Coordinate;
import model.Fighter;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.NoFighterAvailableException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

public class PlayerFile implements IPlayer{

	private GameBoard board;
	private GameShip ship;
	private BufferedReader br;
	
	public PlayerFile(Side side, BufferedReader br) {

		ship = new GameShip("PlayerRandom " + side + " Ship", side);
		this.br = br;
	}
	
	@Override
	public void setBoard(GameBoard gb) {
		board = gb;
	}

	@Override
	public GameShip getGameShip() {
		return ship;
	}

	@Override
	public void initFighters() {
		String brLine = new String();
		try {
			brLine = br.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		ship.addFighters(brLine);
	}

	@Override
	public boolean isFleetDestroyed() {
		return ship.isFleetDestroyed();
	}

	@Override
	public String showShip() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(ship.toString() + "\n");
		builder.append(ship.showFleet());
		
		return builder.toString();
	}

	@Override
	public void purgeFleet() {
		ship.purgeFleet();
	}

	@Override
	public boolean nextPlay() {
		String brLine = new String();
		try {
			brLine = br.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		ArrayList<String> tokens = new ArrayList<String>();
		brLine.split(brLine);
		
		try {
			switch(tokens.get(0)) {
				case "exit":
					return false;
			case "improve":
					if(tokens.size() == 3) {
						if(Integer.parseInt(tokens.get(2)) >= 100) {
							System.out.println("ERROR: qty must be less than 100.");
							return true;
						} else {
							ship.improveFighter(Integer.parseInt(tokens.get(1)), Integer.parseInt(tokens.get(2)), board);
						}
					} else {
						System.out.println("ERROR: incorrect number o arguments.");
						return true;
					}
					return true;
					
				case "patrol":
					if(tokens.size() == 2) {
						ship.patrol(Integer.parseInt(tokens.get(1)), board);
					} else {
						System.out.println("ERROR: incorrect number o arguments.");
						return true;
					}
					return true;
					
				case "launch":
					if(tokens.size() == 3) {
						int x = Integer.parseInt(tokens.get(1));
						int y = Integer.parseInt(tokens.get(2));
						Coordinate c = new Coordinate(x,y);
						
						Fighter f = ship.getFirstAvailableFighter(null);
						
						ship.launch(f.getId(), c, board);
						
					} else if(tokens.size() == 4) {
						try {
							Integer.parseInt(tokens.get(3));
						} catch(NumberFormatException e) {
							int x = Integer.parseInt(tokens.get(1));
							int y = Integer.parseInt(tokens.get(2));
							Coordinate c = new Coordinate(x,y);
							
							Fighter f = ship.getFirstAvailableFighter(tokens.get(3));
							
							ship.launch(f.getId(), c, board);
						}
						
						int x = Integer.parseInt(tokens.get(1));
						int y = Integer.parseInt(tokens.get(2));
						Coordinate c = new Coordinate(x,y);
						
						Fighter f = ship.getFirstAvailableFighter(null);
						
						ship.launch(f.getId(), c, board);
						
						
					} else {
						System.out.println("ERROR: incorrect number o arguments.");
						return true;
					}
					
				default:
					System.out.println("ERROR: incorrect argument.");
					return true;
					
			}
		} catch(FighterAlreadyInBoardException | WrongFighterIdException | FighterNotInBoardException | NoFighterAvailableException | OutOfBoundsException e) {
			throw new RuntimeException(e);
		}
	}
		

}
