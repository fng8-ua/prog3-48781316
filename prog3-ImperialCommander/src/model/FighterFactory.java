package model;

import java.util.Objects;

import model.fighters.AWing;
import model.fighters.TIEBomber;
import model.fighters.TIEFighter;
import model.fighters.TIEInterceptor;
import model.fighters.XWing;
import model.fighters.YWing;

public class FighterFactory {
	
	public static Fighter createFighter(String type, Ship mother) {
		Objects.requireNonNull(type);
		Objects.requireNonNull(mother);
		
		switch(type) {
		
		case "AWing": //Creamos un AWing
			return new AWing(mother);
		case "XWing": //Creamos un XWing
			return new XWing(mother);
		case "YWing": //Creamos un YWing
			return new YWing(mother);
		case "TIEInterceptor": //Creamos un TIEInterceptor
			return new TIEInterceptor(mother);
		case "TIEFighter": //Creamos un TIEFighter
			return new TIEFighter(mother);
		case "TIEBomber": //Creamos un TIEBomber
			return new TIEBomber(mother);
			
		default: 
			return null;
		}
	}
		

}
