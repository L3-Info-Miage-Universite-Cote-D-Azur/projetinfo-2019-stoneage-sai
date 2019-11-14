package stoneage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class JoueurIATest {
    Zone zoneChasse, zone;
    Choix choix ,choix2;
    JoueurIA j;
    Inventaire inventaire;
    
    
    @BeforeEach
    void setUp(){
        j = new JoueurIA();
        inventaire = new Inventaire();
    }
    
    
    @Test
    void placerOuvrier() {
        //Si nbNourriture < NbOuvriers, alors on choisi la zone chasse en placant le nombre d'ouvriers dispo.
        inventaire.setNourriture(0);
        choix2 = j.placerOuvriers(inventaire);
        zoneChasse = new Zone(2);  //zone chasse
        choix = new Choix(zoneChasse, 5);  //5 = NbouvriersDispo
        
        assertEquals(choix, choix2);
        
        //Si NbNourriture > NbOuvriers
        inventaire.resetInventory();
        choix2 = j.placerOuvriers(inventaire);
        choix = new Choix(choix2.zoneChoisie,choix2.nbOuvriersChoisie);
        
        assertEquals(choix, choix2);      
    }

    
    @Test
    void recupeRes() {
    	inventaire.resetInventory();
    	zone = new Zone(1);
    	j.recupeRes(inventaire, zone);
    	assertEquals(inventaire.getNbOutils(), 1); //Si on choisi la zone 1, alors on a un outil en plus.
    	
    }
}
