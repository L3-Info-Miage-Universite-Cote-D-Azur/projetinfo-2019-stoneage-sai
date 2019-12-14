package stoneage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StoneAgeTest {

	StoneAge stoneAge4J, stoneAge3J, stoneAge2J;
	Joueur joueur;
	
    @BeforeEach
    void setUp(){
    	stoneAge2J = new StoneAge(2, true);
    	stoneAge3J = new StoneAge(3, true);
    	stoneAge4J = new StoneAge(4, true);
    	
    }
    
    
    @Test	
	public void jouer() {
    	stoneAge2J.jouer(1);
		stoneAge3J.jouer(1);
		stoneAge4J.jouer(1);

		assertTrue(stoneAge2J.getNbBatiments() < stoneAge2J.getNbJoueurTotal() || stoneAge2J.getNbCarteDispo() < stoneAge2J.getNbJoueurTotal());
		assertTrue(stoneAge3J.getNbBatiments() < stoneAge3J.getNbJoueurTotal() || stoneAge3J.getNbCarteDispo() < stoneAge3J.getNbJoueurTotal());
		assertTrue(stoneAge4J.getNbBatiments() < stoneAge4J.getNbJoueurTotal() || stoneAge4J.getNbCarteDispo() < stoneAge4J.getNbJoueurTotal());
	}
	
    
    
    @Test	
	public void unTour() {
    	stoneAge4J = new StoneAge(4, true);
    	assertTrue(stoneAge4J.getListeDesJoueurs().get(0) instanceof JoueurIA);
    	assertTrue(stoneAge4J.getListeDesJoueurs().get(1) instanceof JoueurBot2);
    	assertTrue(stoneAge4J.getListeDesJoueurs().get(2) instanceof Joueur);
    	assertTrue(stoneAge4J.getListeDesJoueurs().get(3) instanceof Joueur);
    	
    	stoneAge4J.unTour();
    	assertTrue(stoneAge4J.getListeDesJoueurs().get(0) instanceof JoueurBot2);
    	assertTrue(stoneAge4J.getListeDesJoueurs().get(1) instanceof Joueur);
    	assertTrue(stoneAge4J.getListeDesJoueurs().get(2) instanceof Joueur);
    	assertTrue(stoneAge4J.getListeDesJoueurs().get(3) instanceof JoueurIA);
    	
    	stoneAge4J.unTour();
    	assertTrue(stoneAge4J.getListeDesJoueurs().get(0) instanceof Joueur);
    	assertTrue(stoneAge4J.getListeDesJoueurs().get(1) instanceof Joueur);
    	assertTrue(stoneAge4J.getListeDesJoueurs().get(2) instanceof JoueurIA);
    	assertTrue(stoneAge4J.getListeDesJoueurs().get(3) instanceof JoueurBot2);
    	
    	stoneAge4J.unTour();
    	assertTrue(stoneAge4J.getListeDesJoueurs().get(0) instanceof Joueur);
    	assertTrue(stoneAge4J.getListeDesJoueurs().get(1) instanceof JoueurIA);
    	assertTrue(stoneAge4J.getListeDesJoueurs().get(2) instanceof JoueurBot2);
    	assertTrue(stoneAge4J.getListeDesJoueurs().get(3) instanceof Joueur);
    	
    	stoneAge4J.unTour();
    	assertTrue(stoneAge4J.getListeDesJoueurs().get(0) instanceof JoueurIA);
    	assertTrue(stoneAge4J.getListeDesJoueurs().get(1) instanceof JoueurBot2);
    	assertTrue(stoneAge4J.getListeDesJoueurs().get(2) instanceof Joueur);
    	assertTrue(stoneAge4J.getListeDesJoueurs().get(3) instanceof Joueur);
    }
    
    
    @Test	
	public void calculStat() {
    	stoneAge4J = new StoneAge(4, true);
    	int[][] tab = new int[4][15];
    	
    	stoneAge4J.calculStat(tab);
    	for (int i = 0; i < stoneAge4J.getNbJoueurTotal(); i++) {
    		for (int j = 0; j < 15; j++) {
    			if (j == 2) {
    				assertEquals(tab[i][j], 5);
    			}
    			else if (j == 7) {
    				assertEquals(tab[i][j], 12);
    			}
    			else {
    				assertEquals(tab[i][j], 0);
    			}
    			stoneAge4J.getListeDesInventaires().get(i).setScore(200);
        		stoneAge4J.getListeDesInventaires().get(i).setScoreChamp(4);
    		}
    	}
    	
    	stoneAge4J.calculStat(tab);
    	for (int i = 0; i < stoneAge4J.getNbJoueurTotal(); i++) {
    		for (int j = 0; j < 15; j++) {
    			switch (j) {
    				case 2:
    					assertEquals(tab[i][j], 10);
    					break;
    				case 7 :
    					assertEquals(tab[i][j], 24);
    					break;
    				case 1 :
    					assertEquals(tab[i][j], 200);
    					break;
    				case 8 :
    					assertEquals(tab[i][j], 4);
    					break;
    				default :
    					assertEquals(tab[i][j], 0);
    			}
    		}
    	}
    
    }
}
