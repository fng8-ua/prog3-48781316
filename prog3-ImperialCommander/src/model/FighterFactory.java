package model;

public class FighterFactory {
	
	static Fighter createFighter(String type, Ship mother) {
		
		switch(type) {
		
		case "AWing": //Creamos un AWing
			return null;
		case "XWing": //Creamos un XWing
			return null;
		case "YWing": //Creamos un YWing
			return null;
		case "TIEInterceptor": //Creamos un TIEInterceptor
			return null;
		case "TIEFighter": //Creamos un TIEFighter
			return null;
		case "TIEBomber": //Creamos un TIEBomber
			return null;
			
		default: 
			return null;
		}
	}
		

}
