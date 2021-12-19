/**
 * @author Fernando Navarro Gonzalez
 * @author 48781316H
 */
package model.game;

import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

// TODO: Auto-generated Javadoc
/**
 * The Interface IPlayer.
 */
public interface IPlayer {
	
	public WinsScore getWinsScore();
	
	public DestroyedFightersScore getDestroyedFightersScore();

	/**
	 * Sets the board.
	 *
	 * @param gb the new board
	 */
	public void setBoard(GameBoard gb);
	
	/**
	 * Gets the game ship.
	 *
	 * @return the game ship
	 */
	public GameShip getGameShip();
	
	/**
	 * Inits the fighters.
	 */
	public void initFighters();
	
	/**
	 * Checks if is fleet destroyed.
	 *
	 * @return true, if is fleet destroyed
	 */
	public boolean isFleetDestroyed();
	
	/**
	 * Show ship.
	 *
	 * @return the string
	 */
	public String showShip();
	
	/**
	 * Purge fleet.
	 */
	public void purgeFleet();
	
	/**
	 * Next play.
	 *
	 * @return true, if successful
	 */
	public boolean nextPlay();
	
}
