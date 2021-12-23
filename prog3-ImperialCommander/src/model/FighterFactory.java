/*
 * 
 */
package model;

import java.util.Objects;
import java.lang.reflect.*;


// TODO: Auto-generated Javadoc
/**
 * A factory for creating Fighter objects.
 */
public class FighterFactory {
	
	/**
	 * Creates a new Fighter object.
	 *
	 * @param type the type
	 * @param mother the mother
	 * @return the fighter
	 */
	public static Fighter createFighter(String type, Ship mother) {
		Objects.requireNonNull(type);
		Objects.requireNonNull(mother);

		
			String className = "model.fighters." + type;
			
			try {
				return  (Fighter) Class.forName(className).getConstructor(Ship.class).newInstance(mother);
			} catch (Exception | NoClassDefFoundError e) {
				return null;
			}	
	}
		

}
