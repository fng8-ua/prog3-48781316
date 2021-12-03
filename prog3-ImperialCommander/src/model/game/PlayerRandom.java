package model.game;

import java.util.List;
import java.util.Objects;

import model.Coordinate;
import model.RandomNumber;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

public class PlayerRandom implements IPlayer{
	
	private GameBoard board;
	private GameShip ship;
	private int numFighters;
	
	public PlayerRandom(Side side, int numFighters) {
		Objects.requireNonNull(side);
		Objects.requireNonNull(numFighters);
		
		ship = new GameShip("PlayerRandom " + side + " Ship", side);
		this.numFighters = numFighters;
		
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
		int option = RandomNumber.newRandomNumber(100);
		
		if(option == 99) {
			return false;
		} else {
			try {
				List<Integer> idListShip = null;
				List<Integer> idListBoard = null;
				List<Integer> idList = null;
				
				idListShip = ship.getFightersId("ship");
				idListBoard = ship.getFightersId("board");
				idList = ship.getFightersId("");
				
				if(option >= 85 && option <= 98) {
					
					if(extractedIdListError(idList)) {
						return true;
					}
						
						int id = idList.get(RandomNumber.newRandomNumber(numFighters));
						ship.improveFighter(id, option, board);
						
					
					
					
	
				}else if(option >= 25 && option <= 84) {
					
					extractedIdListError(idListShip); 
						
						int id1 = idListShip.get(RandomNumber.newRandomNumber(numFighters));
						int x = RandomNumber.newRandomNumber(board.getSize());
						int y = RandomNumber.newRandomNumber(board.getSize());
						
						Coordinate c = new Coordinate(x,y);
						ship.launch(id1, c, board);
						
					
						
				}else if(option >= 0 && option <= 24) {
					
					extractedIdListError(idListBoard); 
						int id1 = idListBoard.get(RandomNumber.newRandomNumber(idListBoard.size()));
						ship.patrol(id1, board);
						
					
				}
			
			} catch (FighterAlreadyInBoardException | OutOfBoundsException | WrongFighterIdException | FighterNotInBoardException e) {
				throw new RuntimeException(e);
		}
		
	}
		return true;

}

	private boolean extractedIdListError(List<Integer> idList) {
		if(idList.isEmpty()) {
			
			System.out.println("ERROR: There are no id's in the list.");
			return true;
			
		} else {
			return false;
		}
	}

}