package stoneage;
import stoneage.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;


public class DéTest {
    /*public int Lancer() { //cette methode retourne un entier entre 1 et 6.
        return dé.nextInt(6)+1; //utiliser la methode nextInt(n) qui donne un nombre au hazare ente 0 et n exclut
    } */
	 private Dé D;
    @BeforeEach
	void setUp() {
        D= new Dé();
	}
	@Test public void test() {
		for (int i =0 ;i>100; i++){ 
			int de=  D.Lancer();
			assertTrue(de >=1 && de <= 6 );
		}// on lance le Dé 100 fois pour verifier si les valeur retourner sont bien entre 1 et6
		
	}

}
