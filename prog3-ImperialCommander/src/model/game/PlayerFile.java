package model.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

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
		Objects.requireNonNull(side);
		Objects.requireNonNull(br);
		
		ship = new GameShip("PlayerFile " + side + " Ship", side);
		this.br = br;
		
	}
	
	@Override
	public void setBoard(GameBoard gb) {
		Objects.requireNonNull(gb);
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
		
		String[] tokens;
		tokens = brLine.split(" ");
		
			switch(tokens[0]) {
			
			case "exit":
				return false;
				
			case "improve":
				if(tokens.length != 3) {
					System.out.println("ERROR: improve received wrong input's length.");
				} else {
					if(Integer.parseInt(tokens[2]) >= 100) {
						System.out.println("ERROR: improve qty must be less than 100.");
					} else {
						try {
							ship.improveFighter(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), board);
						} catch (NumberFormatException | WrongFighterIdException e) {
							System.out.println(e.getMessage());
						}
					}
				}
				return true;
				
			case "patrol":
				if(tokens.length != 2) {
					System.out.println("ERROR: patrol wrong input's length.");
				} else {
					try {
						ship.patrol(Integer.parseInt(tokens[1]), board);
					} catch (NumberFormatException | WrongFighterIdException | FighterNotInBoardException e) {
						System.out.println(e.getMessage());
					}
				}
				return true;
				
			case "launch":
				if(tokens.length != 3 || tokens.length != 4) {
					System.out.println("ERROR: launch wrong input's length.");
				} else {
					if(tokens.length == 3) {
						Coordinate c = new Coordinate(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
						try {
							ship.launch(ship.getFirstAvailableFighter("").getId(), c, board);
						} catch (WrongFighterIdException | FighterAlreadyInBoardException | OutOfBoundsException
								| NoFighterAvailableException e) {
							System.out.println(e);
						}
					} else if(tokens.length == 4) {
						try {
							Coordinate c = new Coordinate(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));

							try {
								ship.launch(Integer.parseInt(tokens[3]), c, board);
							} catch (WrongFighterIdException | FighterAlreadyInBoardException
									| OutOfBoundsException e) {
								System.out.println(e.getMessage());
							}
							
						} catch(NumberFormatException e) {
							Coordinate c = new Coordinate(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
							try {
								ship.launch(ship.getFirstAvailableFighter(tokens[3]).getId(), c, board);
							} catch (WrongFighterIdException | FighterAlreadyInBoardException | OutOfBoundsException
									| NoFighterAvailableException e1) {
								System.out.println(e.getMessage());
							}
						}
						
						
						
					}
				}
				return true;
				
			default:
				System.out.println("ERROR: wrong keyword " + tokens[0]);
				return true;
			}

	}
		

}
