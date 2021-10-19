package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Board {
	private int size;
	private Map<Coordinate, Fighter> fighters;
	
	public Board(int size) {
		fighters = new HashMap<Coordinate,Fighter>();
		this.size = size;
	}
	
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		Fighter f = fighters.get(new Coordinate(c));
		
		return f;
	}
	
	

	public int getSize() {return size;}
	
	public boolean removeFighter(Fighter f) {
		Objects.requireNonNull(f);
		Fighter aEliminar;
		boolean borrado = false;
		
		aEliminar = fighters.get(f.getPosition());
		
		if(aEliminar.equals(f)) {
			fighters.remove(f.getPosition());
			borrado = true;
		}
		
		return borrado;
	}
	
	public boolean inside(Coordinate c) {
		Objects.requireNonNull(c);
		
		if((c.getX() > 0 && c.getX() < size-1) && (c.getY() > 0 && c.getY() < size-1)) {
			return true;
		} else {
			return false;
		}
		
	}
							
	public Set<Coordinate> getNeighborhood(Coordinate c){
		Objects.requireNonNull(c);
		return c.getNeighborhood();
	}
	

// Necesitamos un modulo que haga que dos cazas peleen y actualice las posiciones
	public int batalla(Fighter nuestro, Fighter enemigo) {
		int res;
		
		res = nuestro.fight(enemigo);
		if(res == 1) {
			nuestro.getMotherShip().updateResults(1);
			enemigo.getMotherShip().updateResults(-1);
		}
		
		return res;
	}
	
	/**
	 * Esta funci�n dados dos cazas nos dice si son del mismo bando (son amigos), o si por el
	 * contrario son de bandos distintos (enemigos).
	 * 
	 * @param f1
	 * @param f2
	 * @return amigos
	 */
	public boolean sonAmigos(Fighter f1, Fighter f2) {
		boolean amigos;
		
		if(f1.getMotherShip().getSide() == f2.getMotherShip().getSide()) {
			amigos = true;
		} else {
			amigos = false;
		}
		
		return amigos;
	}
	
	/**
	 * Nos dice si va a haber batalla o no queriendo colocar el caza en esa coordenada.
	 * 
	 * @param c
	 * @param f
	 * @return hay
	 */
	public boolean hayBatalla(Coordinate c, Fighter f) {
		Fighter otro;
		boolean hay = false;
		
		if(inside(c)) {
			otro = fighters.get(c);
			if(otro != null) {
				if(!sonAmigos(f,otro)) {
					hay = true;
				} else {
					hay = false;
				}
			}
		} else {
			hay = false;
		}
		
		return hay;
	}
	
	/**
	 * Coloca un fighter si la coordenada est� vac�a o si ganamos la batalla contra
	 * el fighter enemigo que haya en esa posici�n.
	 * 
	 * @param c la coordenada
	 * @param f fighter que queremos colocar
	 * @return
	 */
	public int launch(Coordinate c, Fighter f) {
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		Fighter otherF = fighters.get(c);
		int result;
		
		/**
		  -  Comprobamos si est� vac�o
		  -  Si no lo est� comprobamos si tiene un caza de nuestro bando
		  -  Si no lo es luchamos
		 
		 */
		

		if(!inside(c)) {
			result = 0;
		} else {
			if(otherF == null || sonAmigos(f,otherF)) {
				result = 0;
				fighters.put(c,f);
			} else {
				result = batalla(f,otherF);
				if(result == 1) {
					fighters.put(c,f);
				} else {
					fighters.put(c,otherF);
				}
			}
		}		
		return result;		
	}
	
	public void patrol(Fighter f) {
		Objects.requireNonNull(f);
		Set<Coordinate> neighbours = getNeighborhood(f.getPosition());
		Fighter otherF;
		
		if(inside(f.getPosition())) {
			
			for(Coordinate c: neighbours) {
				otherF = fighters.get(c);
				if(otherF != null && !sonAmigos(f,otherF)) {
					if(f.fight(otherF) == 1) {
						f.getMotherShip().updateResults(1);
						otherF.getMotherShip().updateResults(-1);
						
					} else {
						f.getMotherShip().updateResults(-1);
						otherF.getMotherShip().updateResults(1);
					}
				}
			}
			
		}
		
	}
}
