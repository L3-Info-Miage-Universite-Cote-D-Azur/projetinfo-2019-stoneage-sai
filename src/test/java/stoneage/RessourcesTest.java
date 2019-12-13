package stoneage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class RessourcesTest {
	private int coeff;
	private int valeur;
	private Ressources ressources;
	
	@BeforeEach
	void setup() {
		ressources = new Ressources(coeff,"nom",valeur);
	}
	
	@Test
	void GetValeurTest1() { // outils max == 12
		coeff=1;
		valeur=13;
		ressources = new Ressources(coeff,"nom",valeur);
		assertEquals(ressources.getValeur(),12);
	}
	
	@Test
	void GetValeurTest2() { // bois max == 20
		coeff=3;
		valeur=21;
		ressources = new Ressources(coeff,"nom",valeur);
		assertEquals(ressources.getValeur(),20);		
	}
	
	@Test
	void GetValeurTest3() { // argile max == 16
		coeff=4;
		valeur=17;
		ressources = new Ressources(coeff,"nom",valeur);
		assertEquals(ressources.getValeur(),16);		
	}
	
	@Test
	void GetValeurTest4() { // pierre max == 12
		coeff=5;
		valeur=13;
		ressources = new Ressources(coeff,"nom",valeur);
		assertEquals(ressources.getValeur(),12);		
	}
	
	@Test
	void GetValeurTest5() { // or max == 10
		coeff=6;
		valeur=11;
		ressources = new Ressources(coeff,"nom",valeur);
		assertEquals(ressources.getValeur(),10);		
	}
	
	@Test
	 void addValTest() {
		coeff=1;
		valeur=8;
		ressources = new Ressources(coeff,"nom",valeur);
		ressources.addvaleur(1);
		assertEquals(ressources.getValeur(),9);
		
	}
	
	@Test
	 void SubValTest() {
		coeff=1;
		valeur=8;
		ressources = new Ressources(coeff,"nom",valeur);
		ressources.subvaleur(1);
		assertEquals(ressources.getValeur(),7);
		
	}
}
