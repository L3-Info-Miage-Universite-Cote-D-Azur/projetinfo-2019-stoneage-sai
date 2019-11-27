package stoneage;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarteCivilisationTest {
	
	
	private Inventaire inv;
    private CarteCivilisation carte, carte2;
    
    @BeforeEach
    void setUp(){
        inv = new Inventaire();
        carte = new CarteCivilisation();
        carte2 = new CarteCivilisation();
    }
    
    
    @Test
    public void addCardsToList() {
        assertEquals(carte.getAllCards().size(), 36);
    }
    
    
    @Test
    public void getFondDeCarte() {
        for (int i = 0; i < carte.getAllCards().size(); i++) {
            if (i < 16) {
                assertEquals(carte.getAllCards().get(i).getFondDeCarte(), 0);
            }
            else {
                assertEquals(carte.getAllCards().get(i).getFondDeCarte(), 1);
            }
        }
    }
    
    
    @Test
    public void getNumeroCarte() {
        for (int i = 0; i < carte.getAllCards().size(); i++) {
            assertEquals(carte.getAllCards().get(i).getNumeroCarte(), i);
        }
    }

}
