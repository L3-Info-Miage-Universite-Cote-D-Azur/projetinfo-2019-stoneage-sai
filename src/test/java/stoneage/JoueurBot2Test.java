package stoneage;

import stoneage.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;


public class JoueurBot2Test {
	    Zone zone;
	    Choix choix ,choix2;
	    JoueurBot2 j;
	    Inventaire inventaire , inventaire2;
	     


	    @BeforeEach
	    	void setUp(){
	        j = new JoueurBot2();
	        inventaire = new Inventaire();
	        inventaire2 = new Inventaire();
	    }


	    @Test
	    void placerOuvrier() {
	        //Si 3 < nbNourriture < 5, alors on choisi la zone 2 en placant le nombre d'ouvriers dispo.
	        inventaire.setNourriture(4);
	        choix2 = j.placerOuvriers(inventaire);
	        zone = new Zone(2); 
	        choix = new Choix(zone, 1);  //1 = NbouvriersDispo
	        assertEquals(choix, choix2);
	        
	      //Si  nbNourriture < 3, alors on choisi la zone 2 en placant le nombre d'ouvriers dispo.
	        inventaire.setNourriture(2);
	        choix2 = j.placerOuvriers(inventaire);
	        zone = new Zone(2); 
	        choix = new Choix(zone, 3);  //3 = NbouvriersDispo
	        assertEquals(choix, choix2);

	    }
	    @Test
	    void recupRes() {
	    	// si zone = 1 recupere 1 outil
	    	inventaire.setNbOutils(1);
	    	zone = new Zone(1);
	    	j.recupeRes(inventaire2, zone);
	    	assertEquals(inventaire.getNbOutils(),inventaire2.getNbOutils());
	    	
	    }

}
