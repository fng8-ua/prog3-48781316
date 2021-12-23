/**
 * @author Fernando Navarro Gonzalez
 * @author 48781316H
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.exceptions.NoFighterAvailableException;

// TODO: Auto-generated Javadoc
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
	protected ArrayList<Fighter> fleet;
	
	/**
	 * Crea una nueva nave.
	 *
	 * @param name the name
	 * @param side the side
	 */
	public Ship(String name, Side side) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(side);
		
		this.name = name;
		this.side = side;
		wins = losses = 0;
		fleet = new ArrayList<Fighter>();
	}
	
	/**
	 * Devuelve el nombre.
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
	 * Devuelve la flota.
	 *
	 * @return flota
	 */
	public List<Fighter> getFleetTest() {return fleet;}
	
	/**
	 * A�ade un fighter a la nave a trav�s de una cadena.
	 *
	 * @param fd the fd
	 */
	
	public void addFighters(String fd) {
		Objects.requireNonNull(fd);
		int numTipos;
		
		
		// LEER la cadena y separar la informaci�n cuando encuentra ":"
		String[] fighters = fd.split(":");
		
		for(int i = 0; i < fighters.length; i++) {
			
			String[] partes = fighters[i].split("/");
			// Ahora la primer elemento ser� el numTipos y el segundo ser� el nombre
			
			numTipos = Integer.parseInt(partes[0]);
			
			
			for(int j = 1; j <= numTipos; j++) {
				// Crear el numero de tipos indicado y lo a�adimos a fleet
				Fighter f = FighterFactory.createFighter(partes[1],this);
				if(f != null) {
					fleet.add(f);
				}
				
			}
		}
		
	}
	
	/**
	 * a�ade una victoria o una derrota seg�n si r es 1  o -1 respectivamente.
	 *
	 * @param r the r
	 */
	public void updateResults(int r, Fighter f) {
		if(r == 1)
			wins++;
		if(r == -1)
			losses++;
	}
	

	/**
	 * Devuelve el primer fighter del tipo que nos pasen por par�metro que no est� destruido.
	 *
	 * @param t the t
	 * @return primer caza disponible
	 * @throws NoFighterAvailableException the no fighter available exception
	 */
	public Fighter getFirstAvailableFighter(String t) throws NoFighterAvailableException {
		Objects.requireNonNull(t);
		Fighter f = null;
			for(int i = 0; i < fleet.size(); i++) {
				f = fleet.get(i);
				if(f.getPosition() == null && f != null && !f.isDestroyed() && (t.isEmpty() || f.getType().equals(t))){
					return f;
				}
			}
			
		
		throw new NoFighterAvailableException(t);
		

	}
	

			
	
	/**
	 * Elimina los cazas destruidos.
	 */
	public void purgeFleet() {
		ArrayList<Fighter> goodFleet = new ArrayList<Fighter>();
		for(Fighter f: fleet) {
			if(!f.isDestroyed()) {
				goodFleet.add(f);
			}
		}
		
		fleet.clear();
		fleet = goodFleet;
	}
	
	/**
	 * Muestra toda la flota de la nave.
	 *
	 * @return cadena en la que pone la flota
	 */
	public String showFleet() {
		StringBuilder str = new StringBuilder();
		
		if(!fleet.isEmpty()) {
			for(Fighter f: fleet) {
				if(f != null) {
					str.append(f.toString());
					
					if(f.isDestroyed()) {
						str.append(" (X)");
					}
					
					str.append("\n");
				}
			}
			
			return str.toString();
		} else {
			return str.toString();
		}	
	}
	
	/**
	 * Devuelve el n�mero de cazas que hay de un tipo.
	 *
	 * @param type the type
	 * @return numero de cazas de ese tipo en la flota
	 */
	public int sameType(String type) {
		int counter = 0;
		
		for(int i = 0; i < fleet.size(); i++) {
			if(fleet.get(i).getType().equals(type) && !fleet.get(i).isDestroyed()) {
				counter++;
			}
		}
		
		return counter;
	}
	
	
	/**
	 * Muestra un resumen de la flota.
	 *
	 * @return cadena de resumen
	 */
	public String myFleet() {
		
		boolean todosDestruidos = true;;
		
		for(Fighter f: fleet) {
			if(f != null) {
				if(!f.isDestroyed()) {
					todosDestruidos = false;
				}
			}
		}
		
		if(fleet.isEmpty() || todosDestruidos) {
			StringBuilder vacio = new StringBuilder();
			return vacio.toString();
		}
		
		StringBuilder str = new StringBuilder();
		
		
		// Creamos una copia de fleet y le quitamos los repetidos
		// Luego mostramos esa lista usando sameType
		
		ArrayList<String> fleetTypes = new ArrayList<String>();
		// Quitamos repetidos
		for(int i = 0; i < fleet.size(); i++) {
			if(!fleetTypes.contains(fleet.get(i).getType())) {
				fleetTypes.add(fleet.get(i).getType());
			}
		}
		
		Boolean primera = true;
		for(int i = 0; i < fleetTypes.size(); i++) {
			if(primera) {
				if(sameType(fleetTypes.get(i)) != 0) {
					str.append(sameType(fleetTypes.get(i)) + "/" + fleetTypes.get(i));
					primera = false;
				}
				
			} else {
				if(sameType(fleetTypes.get(i)) != 0) {
					str.append(":" + sameType(fleetTypes.get(i)) + "/" + fleetTypes.get(i));
				}
			}
			
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
