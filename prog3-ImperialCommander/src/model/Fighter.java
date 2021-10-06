package model;

public class Fighter {

	private String type;
	private int velocity;
	private int attack;
	private int shield;
	private int id;
	private static int nextId = 1;
	private Coordinate position;
	private Ship motherShip;
	
	Fighter(String type, Ship mother) {
		this.type = type;
		id = nextId;
		
		velocity = 100;
		attack = 80;
		shield = 80;
		position = null;
	}
	
	public Fighter(Fighter f) {
		type = f.type;
		id = f.id;
		velocity = f.velocity;
		attack = f.attack;
		shield = f.shield;
	}
	
	public static void ResetNextId() {
		nextId = 1;
	}
	
	public String getType() {return type;}
	public int getId() {return id;}
	public int getVelocity() {return velocity;}
	public int getAttack() {return attack;}
	public int getShield() {return shield;}
	public Side getSide() {return motherShip.getSide();}
	public Coordinate getPosition() {return position;}
	public Ship getMotherShip() {return motherShip;}
	
	public void setPosition(Coordinate p) {
		position = null;
		
		position.add(p);
	}
	
	public void addAttack(int at) {
		attack += at;
	}
	
	public void addVelocity(int vel) {
		velocity += vel;
	}
	
	public void addShield(int sh) {
		shield += sh;
	}
	
	public boolean isDestroyed() {
		if(shield <= 0)
			return true;
		else
			return false;
	}
	
	public int getDamage(int n, Fighter enemy) {
		int damage;
		
		damage = (n*attack)/300;
		
		return damage;
	}
	
	@Override
	public String toString() {
		return "("+ type + " " + id + " " + motherShip.getSide() " [" + position.getX() + "," + position.getY() + "] " + "{" + attack "," + velocity + "," + shield + "})";                           
	}
	
	public int fight(Fighter enemy) {
		if(shield <= 0 || enemy.isDestroyed())
			return 0;
		
		
		do {
			//TODO
		} while();
	}
	

	
	
}
