package model.game;

import java.util.List;

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
		
		ship = new GameShip("PlayerRandom " + side + " Ship", side);
		this.numFighters = numFighters;
		
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
		StringBuilder builder = new StringBuilder();
		String[] IMPERIALtypes = new String[] {"TIEFighter", "TIEBomber", "TIEInterceptor"};
		String[] REBELtypes = new String[] {"XWing", "YWing", "AWing"};
		
		if(this.ship.getSide().equals(Side.REBEL)) {
			
			for(int i = 0; i < REBELtypes.length; i++) {
				int num = RandomNumber.newRandomNumber(numFighters-1);
				if(num != 0) {
					builder.append(num + "/" + REBELtypes[i]);
				}
				if(i<REBELtypes.length-1) {
					builder.append(":");
				}
			}
			
		} else if(this.ship.getSide().equals(Side.IMPERIAL)) {
			
			for(int i = 0; i < IMPERIALtypes.length; i++) {
				int num = RandomNumber.newRandomNumber(numFighters-1);
				if(num != 0) {
					builder.append(num + "/" + IMPERIALtypes[i]);
				}
				if(i<IMPERIALtypes.length-1) {
					builder.append(":");
				}
			}
			
		}
		
		if(builder != null) {
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
					if(idList.isEmpty()) {
						System.out.println("ERROR: There are no id's in the list.");
					}
					
					int id = idList.get(RandomNumber.newRandomNumber(numFighters-1));
	
					ship.improveFighter(id, option, board);
	
				}else if(option >= 25 && option <= 84) {
					
					if(idListShip.isEmpty()) {
						System.out.println("ERROR: There are no id's in the list.");
					}
					
					int id1 = idListShip.get(RandomNumber.newRandomNumber(numFighters-1));
	
					int x = RandomNumber.newRandomNumber(board.getSize()-1);
					int y = RandomNumber.newRandomNumber(board.getSize()-1);
							
					Coordinate c = new Coordinate(x,y);
						
					ship.launch(id1, c, board);
						
				}else if(option >= 0 && option <= 24) {
					
					if(idListBoard.isEmpty()) {
						System.out.println("ERROR: There are no id's in the list.");
					}
					
					int id1 = idListBoard.get(RandomNumber.newRandomNumber(numFighters-1));
					
					ship.patrol(id1, board);
					
				}
			
			} catch (FighterAlreadyInBoardException | OutOfBoundsException | WrongFighterIdException | FighterNotInBoardException e) {
				throw new RuntimeException(e);
		}
		
	}
		return true;

}

}