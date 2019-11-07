package stoneage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class DéTest {
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
