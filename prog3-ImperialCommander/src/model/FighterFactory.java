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
		
		Class<?> c = null;
		
			try {
				c = Class.forName(className);
			} catch (ClassNotFoundException e1) {
				System.out.println("ERROR: Class not found exception.");
			}
			
			try {
				
				Fighter f = (Fighter) c.getDeclaredConstructor(Ship.class).newInstance(mother);
				return f;
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				
			}
			return null;
			
		
		
			
		
	}
		

}
