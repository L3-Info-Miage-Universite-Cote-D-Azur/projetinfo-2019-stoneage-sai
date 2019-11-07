package stoneage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ChoixTest {
	private int nbOuvriersJouer;
	private Zone zone;
	private Choix choix;
	
	@BeforeEach
	void setUp() {
		nbOuvriersJouer= 4;
		zone=new Zone(2);
		choix= new Choix(zone, nbOuvriersJouer);
		
	}
	@Test public void test() {
		assertTrue(choix.equals( new Choix(new Zone(2),4)));
		
	}

}