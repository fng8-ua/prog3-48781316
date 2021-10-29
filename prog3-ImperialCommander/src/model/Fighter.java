/**
 * @author Fernando Navarro Gonzalez
 * @author 48781316H
 */
package model;

// TODO: Auto-generated Javadoc
/**
 * Clase Fighter.
 */
public abstract class Fighter {

	
	public abstract Fighter copy();
	public abstract char getSymbol();
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + attack;
		result = prime * result + id;
		result = prime * result + ((motherShip == null) ? 0 : motherShip.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + shield;
		result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
		result = prime * result + velocity;
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fighter other = (Fighter) obj;
		if (attack != other.attack)
			return false;
		if (id != other.id)
			return false;
		if (motherShip == null) {
			if (other.motherShip != null)
				return false;
		} else if (!motherShip.equals(other.motherShip))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (shield != other.shield)
			return false;
		if (getType() == null) {
			if (other.getType() != null)
				return false;
		} else if (!getType().equals(other.getType()))
			return false;
		if (velocity != other.velocity)
			return false;
		return true;
	}

	/** Tipo del caza. */
	// DESAPARECE
	
	/** Velocidad del caza. */
	private int velocity;
	
	/** El ataque del caza. */
	private int attack;
	
	/** El escudo del caza. */
	private int shield;
	
	/** Identificador del caza. */
	private int id;
	
	/** Pr�ximo identificador. */
	private static int nextId = 1;
	
	/** Posici�n del caza en el tablero. */
	private Coordinate position;
	
	/** Nave a la que pertenece el caza. */
	private Ship motherShip;
	
	/**
	 * Crea un nuevo fighter.
	 *
	 * @param type the type
	 * @param mother the mother
	 */
	protected Fighter(Ship mother) {

		id = nextId;
		motherShip = mother;
		
		velocity = 100;
		attack = 80;
		shield = 80;
		position = null;
		nextId++;
	}
	
	/**
	 * Constructor de un fighter copiando otro.
	 *
	 * @param f the f
	 */
	protected Fighter(Fighter f) {
		
		id = f.id;
		velocity = f.velocity;
		attack = f.attack;
		shield = f.shield;
		motherShip = f.motherShip;
		position = f.position;
	}
	
	/**
	 * Resetea el siguiente id.
	 */
	public static void resetNextId() {
		nextId = 1;
	}
	
	/**
	 * Devuelve el tipo.
	 *
	 * @return el tipo
	 */
	public String getType() {
		return getClass().getSimpleName();
	}
	
	/**
	 * Devuelve el id.
	 *
	 * @return id
	 */
	public int getId() {return id;}
	
	/**
	 * Devuelve la velocidad.
	 *
	 * @return la velocidad
	 */
	public int getVelocity() {return velocity;}
	
	/**
	 * Devuelve el ataque del caza.
	 *
	 * @return ataque
	 */
	public int getAttack() {return attack;}
	
	/**
	 * Devuelve el escudo del caza.
	 *
	 * @return escudo
	 */
	public int getShield() {return shield;}
	
	/**
	 * Devuelve el bando en el que est� la nave del caza.
	 *
	 * @return bando
	 */
	public Side getSide() {return motherShip.getSide();}
	
	/**
	 * Devuelve la posici�n.
	 *
	 * @return posici�n
	 */
	public Coordinate getPosition() {return position;}
	
	/**
	 * Nave a la que pertence el caza.
	 *
	 * @return Nave madre
	 */
	public Ship getMotherShip() {return motherShip;}
	
	/**
	 * Darle una posici�n al caza.
	 *
	 * @param p the new position
	 */
	public void setPosition(Coordinate p) {
		position = p;
		
	}
	
	/**
	 * A�ade el ataque al caza.
	 *
	 * @param at the at
	 */
	public void addAttack(int at) {
		attack += at;
		if((attack+at) < 0) {
			attack = 0;
		}
	}
	
	/**
	 * A�ade la velocidad al caza.
	 *
	 * @param vel the vel
	 */
	public void addVelocity(int vel) {
		velocity += vel;
		if((velocity + vel) < 0) {
			velocity = 0;
		}
		
	}
	
	/**
	 * A�ade escudo al caza.
	 *
	 * @param sh the sh
	 */
	public void addShield(int sh) {
		shield += sh;
	}
	
	/**
	 * Comprueba si el caza est� destruido.
	 *
	 * @return devuelve true si lo es�ta y false si no lo est� (destruido)
	 */
	public boolean isDestroyed() {
		if(shield <= 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Devuelve el da�o que va a causar al enemigo.
	 *
	 * @param n (numero aleatorio)
	 * @param enemy the enemy
	 * @return da�o provocado
	 */
	public int getDamage(int n, Fighter enemy) {
		int damage;
		
		damage = (n*attack)/300;
		
		return damage;
	}
	
	/**
	 * Crea una string.
	 *
	 * @return string
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		if(position == null) {
			str.append("("+ getType() + " " + id + " " + motherShip.getSide() + " null " + "{" + velocity + "," + attack + "," + shield + "})");
		} else {
			str.append("("+ getType() + " " + id + " " + motherShip.getSide() + " [" + position.getX() + "," + position.getY() + "] " + "{" + velocity + "," + attack + "," + shield + "})");  
		}
		
		return str.toString();
	}
	
	/**
	 * Dos cazas pelean hasta que uno de ellos es destruido por el otro.
	 *
	 * @param enemy the enemy
	 * @return devuelve 1 si hemos ganado o -1 si hemos perdido
	 */
	public int fight(Fighter enemy) {
		if(shield <= 0 || enemy.isDestroyed())
			return 0;
		
		int n;
		int umbral;
		do {
			n = RandomNumber.newRandomNumber(100);
			umbral = (100*velocity)/(enemy.getVelocity() + velocity);
			
			// Como se utiliza el randomNumber??
			
			if(umbral <= n) {
				// el atacante ser� el caza
				enemy.addShield(-getDamage(n,this));
				
				if(enemy.isDestroyed())
					return 1;
				
			} else {
				// el atacante ser� el enemigo
				this.addShield(-getDamage(100-n,enemy));
				
				if(this.isDestroyed())
					return -1;
			}
			
		} while(!enemy.isDestroyed() && !isDestroyed());
	return 0;
	}
}
