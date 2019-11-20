package stoneage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class JoueurIATest {
    ArrayList<Zone> listeZones = new ArrayList<Zone>();
    Zone zone1, zone2, zone3, zone4, zone5, zone6;
    Choix actualChoice ,expectedChoice;
    JoueurIA joueurIA;
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

        joueurIA = new JoueurIA();
        inventaire = new Inventaire();
    }


    @Test
    void placerOuvrierNourritureInsuffisante() {
        for(int i=0; i<5 ; i++){
            inventaire.setNourriture(i);
            expectedChoice = new Choix(1, 5-i);
            actualChoice = joueurIA.placerOuvriers(listeZones, inventaire);
            assertEquals(expectedChoice, actualChoice);
        }
    }

    @Test
    void placerOuvrierNourritureSuffisante() {
        inventaire.setNourriture(5);
        actualChoice = joueurIA.placerOuvriers(listeZones, inventaire);
        assertTrue(actualChoice.nbOuvriersChoisie >=0);
        assertTrue(actualChoice.nbOuvriersChoisie <=5);
        assertTrue(actualChoice.nbOuvriersChoisie <=inventaire.getNbOuvrierDispo());
        assertTrue(actualChoice.nbOuvriersChoisie <= listeZones.get(actualChoice.zoneChoisie).getNbPlaceDispo());

    }

    @Test
    void recupeRes() {
    	inventaire.resetInventory();
    	zone1 = new Zone(1);
    	zone1.recupeRes(inventaire, joueurIA);
    	assertEquals(inventaire.getNbOutils(), 1); //Si on choisi la zone 1, alors on a un outil en plus.
    }
}

