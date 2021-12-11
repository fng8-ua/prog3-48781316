/**
 * @author Fernando Navarro Gonzalez
 * @author 48781316H
 */
package model.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import model.Coordinate;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.NoFighterAvailableException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerFile.
 */
public class PlayerFile implements IPlayer{

	/** The board. */
	private GameBoard board;
	
	/** The ship. */
	private GameShip ship;
	
	/** The br. */
	private BufferedReader br;
	
	/**
	 * Instantiates a new player file.
	 *
	 * @param side the side
	 * @param br the br
	 */
	public PlayerFile(Side side, BufferedReader br) {
		Objects.requireNonNull(side);
		Objects.requireNonNull(br);
		
		ship = new GameShip("PlayerFile " + side + " Ship", side);
		this.br = br;
		board = null;
		
	}
	
	/**
	 * Sets the board.
	 *
	 * @param gb the new board
	 */
	@Override
	public void setBoard(GameBoard gb) {
		Objects.requireNonNull(gb);
		board = gb;
	}

	/**
	 * Gets the game ship.
	 *
	 * @return the game ship
	 */
	@Override
	public GameShip getGameShip() {
		return ship;
	}

	/**
	 * Inits the fighters.
	 */
	@Override
	public void initFighters() {
		String brLine = new String();
		try {
			brLine = br.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		
		ship.addFighters(brLine);
	}

	/**
	 * Checks if is fleet destroyed.
	 *
	 * @return true, if is fleet destroyed
	 */
	@Override
	public boolean isFleetDestroyed() {
		return ship.isFleetDestroyed();
	}

	/**
	 * Show ship.
	 *
	 * @return the string
	 */
	@Override
	public String showShip() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(ship.toString() + "\n");
		builder.append(ship.showFleet());
		
		return builder.toString();
	}

	/**
	 * Purge fleet.
	 */
	@Override
	public void purgeFleet() {
		ship.purgeFleet();
	}

	/**
	 * Next play.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean nextPlay() {
		
		String brLine;
		String[] tokens;
		
		try {
			
			brLine = br.readLine();
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
						} catch (WrongFighterIdException e) {
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
					} catch (WrongFighterIdException | FighterNotInBoardException e) {
						System.out.println(e.getMessage());
					}
				}
				return true;
				
			case "launch":
				if(tokens.length != 3 || tokens.length != 4) {
					System.out.println("ERROR: launch wrong input's length.");
				} else {
					if(tokens.length == 3) {
						
						
						try {
							Coordinate c = new Coordinate(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
							board.launch(c, ship.getFirstAvailableFighter(""));
						} catch (FighterAlreadyInBoardException | OutOfBoundsException
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
								//ship.launch(ship.getFirstAvailableFighter(tokens[3]).getId(), c, board);
								board.launch(c, ship.getFirstAvailableFighter(tokens[3]));
							} catch (FighterAlreadyInBoardException | OutOfBoundsException
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
			
		} catch (IOException e) {}
		
		return true;

	}
		

}
