package model.game;

import java.util.List;

import model.Fighter;
import model.RandomNumber;
import model.Ship;
import model.Side;
import model.exceptions.InvalidSizeException;

public class PlayerRandom implements IPlayer{
	
	private GameBoard board;
	private GameShip ship;
	private int numFighters;
	
	public PlayerRandom(Side side, int numFighters) {
		if(side.toString() == "REBEL") {
			ship = new GameShip("PlayerRandom REBEL Ship", side);
		} else if(side.toString() == "IMPERIAL") {
			ship = new GameShip("PlayerRandom IMPERIAL Ship", side);
		}
		
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
		String[] REBELtypes = new String[] {"TIEFighter", "TIEBomber", "TIEInterceptor"};
		String[] IMPERIALtypes = new String[] {"XWing", "YWing", "AWing"};
		
		if(this.ship.getSide().toString() == "REBEL") {
			
			for(int i = 0; i < REBELtypes.length; i++) {
				int num = RandomNumber.newRandomNumber(numFighters-1);
				if(num != 0) {
					builder.append(num + "/" + REBELtypes[i]);
				}
				
			}
			
		} else if(this.ship.getSide().toString() == "IMPERIAL") {
			
			for(int i = 0; i < IMPERIALtypes.length; i++) {
				int num = RandomNumber.newRandomNumber(numFighters-1);
				if(num != 0) {
					builder.append(num + "/" + IMPERIALtypes[i]);
				}
				
			}
			
		}
		
		if(builder != null) {
			//TODO 
			Ship.addFighters(builder.toString());
		}
		
		
		
		
	}

	@Override
	public boolean isFleetDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String showShip() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void purgeFleet() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean nextPlay() {
		int option = RandomNumber.newRandomNumber(100);
		
		if(option == 99) {
			//TODO abandono (exit)
			return false;
		} else {
			
			List<Integer> idList = null;
			idList = ship.getFightersId("ship");
			
			if(idList.isEmpty()) {
				System.out.println("ERROR: There are no id's in the list.");
			}
			
			if(option >= 85 && option <= 98) {
				
				int id = RandomNumber.newRandomNumber(numFighters-1);
				
				
			}
			
			if(option >= 25 && option <= 84) {
				
			}
			
			if(option >= 0 && option <= 24) {
				
			}
			
			return true;
		}
		
	}

}
