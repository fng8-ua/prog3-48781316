/**
 * @author Fernando Navarro Gonzalez
 * @author 48781316H
 */
package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * La clase Ship.
 */
public class Ship {
	
	/** El nombre de la nave. */
	private String name;
	
	/** Bando de la nave. */
	private Side side;
	
	/** Victorias de la nave. */
	private int wins;
	
	/** Derrotas de la nave. */
	private int losses;
	
	/** Flota de cazas de la nave. */
	private ArrayList<Fighter> fleet;
	
	/**
	 * Crea una nueva nave.
	 *
	 * @param nombre de la nave
	 * @param bando de la nave
	 */
	public Ship(String name, Side side) {
		this.name = name;
		this.side = side;
		wins = losses = 0;
	}
	
	/**
	 * Devuelve el nombre
	 *
	 * @return el nombre
	 */
	public String getName() {return name;}
	
	/**
	 * Devuelve el bando de la nave.
	 *
	 * @return bando
	 */
	public Side getSide() {return side;}
	
	/**
	 * Devuelve las victorias de la nave.
	 *
	 * @return victorias
	 */
	public int getWins() {return wins;}
	
	/**
	 * Devuelve las derrotas de la nave.
	 *
	 * @return derrotas
	 */
	public int getLosses() {return losses;}
	
	/**
	 * Devuelve la flota
	 *
	 * @return flota
	 */
	public List<Fighter> getFleetTest() {return fleet;}
	
	/**
	 * Añade un fighter a la nave a través de una cadena.
	 *
	 * @param cadena fd
	 */
	
	public void addFighters(String fd) {
		int numTipos;
		String typeName;
		
		// LEER la cadena y separar la información cuando encuentra ":"
		String[] fighters = fd.split(":");
		
		for(int i = 0; i < fighters.length; i++) {
			
			String[] partes = fighters[i].split("/");
			// Ahora la primer elemento será el numTipos y el segundo será el nombre
			
			numTipos = Integer.parseInt(partes[0]);
			typeName = partes[1];
			
			for(int j = 0; i < numTipos; j++) {
				// Crear el numero de tipos indicado y lo añadimos a fleet
				fleet.add(new Fighter(typeName,this));
			}
		}
		
	}
	
	/**
	 * añade una victoria o una derrota según si r es 1  o -1 respectivamente
	 *
	 * @param r
	 */
	public void updateResults(int r) {
		if(r == 1)
			wins++;
		if(r == -1)
			losses++;
	}
	
	/**
	 * Comprueba que el fighter pasado no está destruido, y que si el tipo no está vació
	 * 
	 * @param ftr el fighter
	 * @param type el tipo
	 * @return true o false según si es valido o no
	 */
	public boolean isFighterValid(Fighter ftr, String type) {
		
		if(ftr.isDestroyed()) {
			return false;
		} else if(type.isEmpty()) {
			return true;
		} else if(ftr.getType() == type) {
			return true;
		} else 
			return false;
		}
	}
	
	/**
	 * Devuelve el primer fighter del tipo que nos pasen por parámetro que no esté destruido.
	 *
	 * @param tipo de caza requerido
	 * @return primer caza disponible
	 */
	public Fighter getFirstAvailableFighter(String t) {
		for(Fighter f: fleet) {
			if(isFighterValid(f,t))
				return f;
		}
	}
			
	
	/**
	 * Elimina los cazas destruidos.
	 */
	public void purgeFleet() {
		for(Fighter f: fleet) {
			if(f.isDestroyed()) {
				fleet.remove(f);
			}
		}
	}
	
	/**
	 * Muestra toda la flota de la nave
	 *
	 * @return cadena en la que pone la flota
	 */
	public String showFleet() {
		StringBuilder str = new StringBuilder();
		
		if(!fleet.isEmpty()) {
			for(Fighter f: fleet) {
				str.append(f.toString());
				
				if(f.isDestroyed()) {
					str.append(" (X) ");
				}
				
				str.append("\n");
			}
			
			return str.toString();
		} else {
			return str.toString();
		}	
	}
	
	/**
	 * Devuelve el número de cazas que hay de un tipo.
	 *
	 * @param tipo que buscamos
	 * @return numero de cazas de ese tipo en la flota
	 */
	public int sameType(String type) {
		int counter = 0;
		
		for(Fighter f: fleet) {
			if(f.getType() == type) {
				counter++;
			}
		}
		
		return counter;
	}
	
	/**
	 * Elimina repetidos de una string
	 *
	 * @param cadena con los tipos que hay en flota
	 */ 
	public void quitarRepetidosString(List<String> tipos) {
		
		Set<String> hashSet = new HashSet<String>(tipos);
		tipos.clear();
		tipos.addAll(hashSet);
	}
	
	/**
	 * Muestra un resumen de la flota
	 *
	 * @return cadena de resumen
	 */
	public String myFleet() {
		StringBuilder str = new StringBuilder();
		List<String> tipos = new ArrayList<String>();
		
		
		purgeFleet();
		
		// Guardamos los nombres de los tipos
		for(Fighter f: fleet) {
			tipos.add(f.getType());
		}
		
		// Quitamos los repetidos
		quitarRepetidosString(tipos);
		
		
		Boolean primero = true;
		
		for(int i = 0; i < tipos.size(); i++) {
			if(primero) {
				primero = false;
				str.append(sameType(tipos.get(i))+"/"+tipos.get(i));
			}
			
			str.append(":"+sameType(tipos.get(i))+"/"+tipos.get(i));

		}
		
		return str.toString();
}
	
	/**
	 * Resumen de la nave.
	 *
	 * @return cadena
	 */
	public String toString() {
		// Ship [Alderaan 35/10] 12/XWing:7/AWing
		StringBuilder str = new StringBuilder();
		
		str.append("Ship [" + name + " " + wins + "/" + losses + "] " + myFleet());
		
		return str.toString();
		
	}
}
