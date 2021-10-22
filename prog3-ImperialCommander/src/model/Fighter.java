/**
 * @author Fernando Navarro Gonzalez
 * @author 48781316H
 */
package model;

/**
 * Clase Fighter.
 */
public class Fighter {

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
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (velocity != other.velocity)
			return false;
		return true;
	}

	/** Tipo del caza. */
	private String type;
	
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
	 * @param tipo de caza
	 * @param nave a la que pertenece
	 */
	Fighter(String type, Ship mother) {
		this.type = type;
		id = nextId;
		motherShip = mother;
		
		velocity = 100;
		attack = 80;
		shield = 80;
		position = null;
	}
	
	/**
	 * Constructor de un fighter copiando otro.
	 *
	 * @param caza que se debe copiar.
	 */
	public Fighter(Fighter f) {
		type = f.type;
		id = f.id;
		velocity = f.velocity;
		attack = f.attack;
		shield = f.shield;
		motherShip = f.getMotherShip();
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
	public String getType() {return type;}
	
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
	 * Devuelve el ataque del caza
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
	 * Darle una posici�n al caza
	 *
	 * @param la nueva posicion
	 */
	public void setPosition(Coordinate p) {
		position = p;
		
	}
	
	/**
	 * A�ade el ataque al caza
	 *
	 * @param ataque que vamos a�adir
	 */
	public void addAttack(int at) {
		attack += at;
	}
	
	/**
	 * A�ade la velocidad al caza
	 *
	 * @param velocidad a a�adir
	 */
	public void addVelocity(int vel) {
		velocity += vel;
	}
	
	/**
	 * A�ade escudo al caza.
	 *
	 * @param escudo a a�adir
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
	 * Devuelve el da�o que va a causar al enemigo
	 *
	 * @param n (numero aleatorio)
	 * @param enemigo
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
			str.append("("+ type + " " + id + " " + motherShip.getSide() + " null " + "{" + attack + "," + velocity + "," + shield + "})");
		} else {
			str.append("("+ type + " " + id + " " + motherShip.getSide() + " [" + position.getX() + "," + position.getY() + "] " + "{" + attack + "," + velocity + "," + shield + "})");  
		}
		
		return str.toString();
	}
	
	/**
	 * Dos cazas pelean hasta que uno de ellos es destruido por el otro.
	 *
	 * @param el enemigo
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
