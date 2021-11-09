package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import model.fighters.AWing;
import model.fighters.TIEBomber;
import model.fighters.TIEFighter;
import model.fighters.TIEInterceptor;
import model.fighters.XWing;
import model.fighters.YWing;

@SuppressWarnings("unused")
public class FightersPreTest {

	Ship rebelShip, imperialShip;
	Board board;
	
	@Before
	public void setUp() throws Exception {
		rebelShip = new Ship("Tydirium", Side.REBEL);
		imperialShip = new Ship("Lanzadera T-4a", Side.IMPERIAL);
		board = new Board(5);
	}

	@Test
	public void testCreateFighter() {
		Fighter rebel = FighterFactory.createFighter("AWing", rebelShip);
		assertTrue(rebel instanceof Fighter);
		assertTrue(rebel instanceof AWing);
		
		Fighter imperial = FighterFactory.createFighter("TIEBomber", imperialShip);
		assertTrue(imperial instanceof Fighter);
		assertTrue(imperial instanceof TIEBomber);	
	}
	
	@Test
	public void testCopy() {
		Fighter fx = FighterFactory.createFighter("XWing", rebelShip);
		Fighter fy = FighterFactory.createFighter("YWing", rebelShip);
		Fighter fa = FighterFactory.createFighter("AWing", rebelShip);
		Fighter fb = FighterFactory.createFighter("TIEBomber", imperialShip);
		Fighter ff = FighterFactory.createFighter("TIEFighter", imperialShip);
		Fighter fi = FighterFactory.createFighter("TIEInterceptor", imperialShip);
		assertEquals("copy XWing",fx,fx.copy());
		assertEquals("copy YWing",fy,fy.copy());
		assertEquals("copy AWing",fa,fa.copy());
		assertEquals("copy TIEBomber",fb,fb.copy());
		assertEquals("copy TIEFighter",ff,ff.copy());
		assertEquals("copy TIEInterceptor",fi,fi.copy());
		
		XWing fxx = (XWing)fx;
		XWing fxcopy = (XWing) fx.copy();
		assertEquals("copy XWing as XWing",fxx,fxcopy);
		
		YWing fyy = (YWing)fy;
		YWing fycopy = (YWing) fy.copy();
		assertEquals("copy YWing as YWing",fyy,fycopy);
		
		AWing faa = (AWing)fa;
		AWing facopy = (AWing) fa.copy();
		assertEquals("copy XWing as XWing",faa,facopy);
		
		TIEBomber fbb = (TIEBomber)fb;
		TIEBomber fbcopy = (TIEBomber) fb.copy();
		assertEquals("copy TIEBomber as TIEBomber",fbb,fbcopy);
		
		TIEFighter fff = (TIEFighter)ff;
		TIEFighter ffcopy = (TIEFighter) ff.copy();
		assertEquals("copy TIEFighter as TIEFighter",fff,ffcopy);
		
		TIEInterceptor fii = (TIEInterceptor)fi;
		TIEInterceptor ficopy = (TIEInterceptor) fi.copy();
		assertEquals("copy TIEInterceptor as TIEInterceptor",fii,ficopy);
		
		//fail("completa el test añadiendo pruebas similares para los cazas que faltan por probar");
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testAWing() {
		Fighter fa = FighterFactory.createFighter("AWing", rebelShip);
		Fighter fx = FighterFactory.createFighter("XWing", rebelShip);
		Fighter fy = FighterFactory.createFighter("YWing", rebelShip);
		Fighter tb = FighterFactory.createFighter("TIEBomber", imperialShip);
		Fighter tf = FighterFactory.createFighter("TIEFighter", imperialShip);
		Fighter ti = FighterFactory.createFighter("TIEInterceptor", imperialShip);
		assertEquals("velocity",140,fa.getVelocity());
		assertEquals("attack",85,fa.getAttack());
		assertEquals("shield",30,fa.getShield());
		assertEquals("symbol",'A',fa.getSymbol());
		
		assertEquals("getDamage(50,TIEBomber)",28,fa.getDamage(50, tb));
		assertEquals("getDamage(50,TIEFighter)",14,fa.getDamage(50, tf));
		assertEquals("getDamage(50,TIEInterceptor)",14,fa.getDamage(50, ti));
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testXWing() {
		Fighter fa = FighterFactory.createFighter("AWing", rebelShip);
		Fighter fx = FighterFactory.createFighter("XWing", rebelShip);
		Fighter fy = FighterFactory.createFighter("YWing", rebelShip);
		Fighter tb = FighterFactory.createFighter("TIEBomber", imperialShip);
		Fighter tf = FighterFactory.createFighter("TIEFighter", imperialShip);
		Fighter ti = FighterFactory.createFighter("TIEInterceptor", imperialShip);
		assertEquals("velocity",110,fx.getVelocity());
		assertEquals("attack",100,fx.getAttack());
		assertEquals("shield",80,fx.getShield());
		assertEquals("symbol",'X',fx.getSymbol());
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testYWing() {
		Fighter fa = FighterFactory.createFighter("AWing", rebelShip);
		Fighter fx = FighterFactory.createFighter("XWing", rebelShip);
		Fighter fy = FighterFactory.createFighter("YWing", rebelShip);
		Fighter tb = FighterFactory.createFighter("TIEBomber", imperialShip);
		Fighter tf = FighterFactory.createFighter("TIEFighter", imperialShip);
		Fighter ti = FighterFactory.createFighter("TIEInterceptor", imperialShip);
		assertEquals("velocity",80,fy.getVelocity());
		assertEquals("attack",70,fy.getAttack());
		assertEquals("shield",110,fy.getShield());
		assertEquals("symbol",'Y',fy.getSymbol());
		
		assertEquals("getDamage(50,TIEBomber)",5,fy.getDamage(50, tb));
		assertEquals("getDamage(50,TIEFighter)",3,fy.getDamage(50, tf));
		assertEquals("getDamage(50,TIEInterceptor)",3,fy.getDamage(50, ti));
		
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testTIEBomber() {
		
		Fighter fa = FighterFactory.createFighter("AWing", rebelShip);
		Fighter fx = FighterFactory.createFighter("XWing", rebelShip);
		Fighter fy = FighterFactory.createFighter("YWing", rebelShip);
		Fighter tb = FighterFactory.createFighter("TIEBomber", imperialShip);
		Fighter tf = FighterFactory.createFighter("TIEFighter", imperialShip);
		Fighter ti = FighterFactory.createFighter("TIEInterceptor", imperialShip);
		assertEquals("velocity",70,tb.getVelocity());
		assertEquals("attack",30,tb.getAttack());
		assertEquals("shield",115,tb.getShield());
		assertEquals("symbol",'b',tb.getSymbol());
	
		assertEquals("getDamage(50,XWing)",14,ti.getDamage(50, fx));
		assertEquals("getDamage(50,YWing)",28,ti.getDamage(50, fy));
		assertEquals("getDamage(50,XWing)",7,ti.getDamage(50, fa));
		
		
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testTIEFighter() {
		
		Fighter fa = FighterFactory.createFighter("AWing", rebelShip);
		Fighter fx = FighterFactory.createFighter("XWing", rebelShip);
		Fighter fy = FighterFactory.createFighter("YWing", rebelShip);
		Fighter tb = FighterFactory.createFighter("TIEBomber", imperialShip);
		Fighter tf = FighterFactory.createFighter("TIEFighter", imperialShip);
		Fighter ti = FighterFactory.createFighter("TIEInterceptor", imperialShip);
		assertEquals("velocity",110,tf.getVelocity());
		assertEquals("attack",85,tf.getAttack());
		assertEquals("shield",70,tf.getShield());
		assertEquals("symbol",'f',tf.getSymbol());
	
		assertEquals("getDamage(50,XWing)",14,tf.getDamage(50, fx));
		assertEquals("getDamage(50,YWing)",14,tf.getDamage(50, fy));
		assertEquals("getDamage(50,XWing)",14,tf.getDamage(50, fa));
		
		
	}
	
	@Test
	public void testTIEInterceptor() {
		
		Fighter fa = FighterFactory.createFighter("AWing", rebelShip);
		Fighter fx = FighterFactory.createFighter("XWing", rebelShip);
		Fighter fy = FighterFactory.createFighter("YWing", rebelShip);
		Fighter tb = FighterFactory.createFighter("TIEBomber", imperialShip);
		Fighter tf = FighterFactory.createFighter("TIEFighter", imperialShip);
		Fighter ti = FighterFactory.createFighter("TIEInterceptor", imperialShip);
		assertEquals("velocity",145,ti.getVelocity());
		assertEquals("attack",85,ti.getAttack());
		assertEquals("shield",60,ti.getShield());
		assertEquals("symbol",'i',ti.getSymbol());
	
		assertEquals("getDamage(50,XWing)",14,ti.getDamage(50, fx));
		assertEquals("getDamage(50,YWing)",28,ti.getDamage(50, fy));
		assertEquals("getDamage(50,XWing)",7,ti.getDamage(50, fa));
		
		
	}
	
	
	
	
}
