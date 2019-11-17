package stoneage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class JoueurBot2Test {
    ArrayList<Zone> listeZones = new ArrayList<Zone>();
    Zone zone1, zone2, zone3, zone4, zone5, zone6;
    Choix actualChoice ,expectedChoice;
    JoueurBot2 joueurBot;
    Inventaire inventaire;


    @BeforeEach
    void setUp(){
        zone1 = new Zone(1);
        zone2 = new Zone(2);
        zone3 = new Zone(3);
        zone4 = new Zone(4);
        zone5 = new Zone(5);
        zone6 = new Zone(6);

        listeZones.add(zone1);
        listeZones.add(zone2);
        listeZones.add(zone3);
        listeZones.add(zone4);
        listeZones.add(zone5);
        listeZones.add(zone6);

        joueurBot = new JoueurBot2();
        inventaire = new Inventaire();
    }


    @Test
    void placerOuvrierNourritureInsuffisante() {
        for(int i=0; i< 5 ; i++){
            inventaire.setNourriture(i);
            if(i<3){
                expectedChoice = new Choix(1, 3);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
            }
            else{
                expectedChoice = new Choix(1, 1);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
            }
        }
    }

    @Test
    void placerOuvrierNourritureSuffisante() {
        inventaire.setNourriture(6);
        if(zone1.getNbPlaceZone() == 1){
            expectedChoice = new Choix(0, 1);
            actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
            assertEquals(expectedChoice, actualChoice);
        }
    }
    /*
	    @Test
	    void recupRes() {
	    	// si zone = 1 recupere 1 outil
	    	inventaire.setNbOutils(1);
	    	zone = new Zone(1);
	    	j.recupeRes(inventaire2, zone);
	    	assertEquals(inventaire.getNbOutils(),inventaire2.getNbOutils());
	    }*/

}

