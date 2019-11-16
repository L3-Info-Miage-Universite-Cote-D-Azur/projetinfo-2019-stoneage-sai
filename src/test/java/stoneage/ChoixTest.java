package stoneage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ChoixTest {
	private int nbOuvriersJouer;
	private int zone;
	private Choix choix;
	
	@BeforeEach
	void setUp() {
		nbOuvriersJouer= 4;
		zone=2;
		choix= new Choix(zone, nbOuvriersJouer);
		
	}
	@Test public void test() {
		assertTrue(choix.equals(new Choix(2,4)));	
	}
}