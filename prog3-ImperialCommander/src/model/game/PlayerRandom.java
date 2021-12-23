/**
 * @author Fernando Navarro Gonzalez
 * @author 48781316H
 */
package model.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.Coordinate;
import model.RandomNumber;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;
import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerRandom.
 */
public class PlayerRandom implements IPlayer{
	
	/** The board. */
	private GameBoard board;
	
	/** The ship. */
	private GameShip ship;
	
	/** The num fighters. */
	private int numFighters;
	
	/**
	 * Instantiates a new player random.
	 *
	 * @param side the side
	 * @param numFighters the num fighters
	 */
	public PlayerRandom(Side side, int numFighters) {
		Objects.requireNonNull(side);
		Objects.requireNonNull(numFighters);
		
		ship = new GameShip("PlayerRandom " + side + " Ship", side);
		this.numFighters = numFighters;
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
		StringBuilder builder = new StringBuilder();
		String[] IMPERIALtypes = {"TIEFighter", "TIEBomber", "TIEInterceptor"};
		String[] REBELtypes = {"XWing", "YWing", "AWing"};
		String[] types = (this.ship.getSide() == Side.IMPERIAL)? IMPERIALtypes:REBELtypes;
		
		
			for(int i = 0; i < types.length; i++) {
				int num = RandomNumber.newRandomNumber(numFighters);
				if(num != 0) {
					if(builder.length() > 0) {
						builder.append(":");
					}
					
					builder.append(num + "/" + types[i]);
				}
			}
		
		if(builder.length() > 0) {
			ship.addFighters(builder.toString());
		}
		
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
		/*TODO falla el test testNextPlayManyTimes1 de PlayerRandomPreTest
		 * */
		
		int option = RandomNumber.newRandomNumber(100);
		List<Integer> idList = new ArrayList<>();
		
		if(option == 99) {
			return false;
		} else {
			try {
				if(option >= 85) {
					idList = ship.getFightersId("");
					
					if(idList.isEmpty()) {
						System.out.println("ERROR: Empty list.");
					} else {
						int randomId = RandomNumber.newRandomNumber(idList.size());
						int id = idList.get(randomId);
						ship.improveFighter(id, option, board);
						
					}
					
					
					
				} else if (option >= 25) {
					idList = ship.getFightersId("ship");
					
					if(idList.isEmpty()) {
						System.out.println("ERROR: La nave no tiene cazas");
					} else {
						int randomId = RandomNumber.newRandomNumber(idList.size());
						int id = idList.get(randomId);
						
						int x = RandomNumber.newRandomNumber(board.getSize());
						int y = RandomNumber.newRandomNumber(board.getSize());
						
						Coordinate c = new Coordinate(x,y);
						ship.launch(id, c, board);
						
					}
					
					
					
				} else if (option >= 0) {
					idList = ship.getFightersId("board");
					
					if(idList.isEmpty()) {
						System.out.println("ERROR: Empty list.");
					} else {
						int randomId = RandomNumber.newRandomNumber(idList.size());
						int id = idList.get(randomId);
						ship.patrol(id, board);
					}	
				}
			} catch(WrongFighterIdException | RuntimeException | FighterAlreadyInBoardException | OutOfBoundsException | FighterNotInBoardException e) {
				throw new RuntimeException(e);
			}
			
			
			return true;
		}
		
		

	}
	
	@Override
	public WinsScore getWinsScore() {
		return ship.getWinsScore();
	}

	@Override
	public DestroyedFightersScore getDestroyedFightersScore() {
		return ship.getDestroyedFightersScore();
	}

}