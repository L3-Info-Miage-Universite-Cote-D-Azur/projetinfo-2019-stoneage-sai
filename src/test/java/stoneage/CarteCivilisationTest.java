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
    
    
    
    @Test
    public void getNbBuilderOnIt() {
        for (int i = 0; i < carte.getAllCards().size(); i++) {
            assertTrue(carte.getAllCards().get(i).getNbBuilderOnIt() >= 0 && carte.getAllCards().get(i).getNbBuilderOnIt() <= 3);
        }
    }

    
    @Test
    public void getPartieSuperieur() {
        for (int i = 0; i < carte.getAllCards().size(); i++) {
            assertTrue(carte.getAllCards().get(i).getPartieSuperieur() >= 0 && carte.getAllCards().get(i).getPartieSuperieur() <= 8);
        }
    }
    
    
    
    @Test
    public void initialiseDeck() {
        carte2.initialiseDeck();
        assertEquals(carte2.getAllCards().size(), carte.getAllCards().size());
    }
    
    
    
    @Test
    public void ajouterDansInventaire() {
    	for (int i = 0; i < carte.getAllCards().size(); i++) {
    		carte.getAllCards().get(i).ajouterDansInventaire(inv);
    	}
    	assertEquals(inv.listeDesCarteCivilisation.size(), 36);
    }
    


}
