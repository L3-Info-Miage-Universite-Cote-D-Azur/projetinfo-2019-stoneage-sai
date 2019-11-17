package stoneage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class JoueurBot2Test {
    ArrayList<Zone> listeZones = new ArrayList<Zone>();
    Zone zone1, zone2, zone3, zone4, zone5, zone6;
    Choix actualChoice ,expectedChoice;
    JoueurBot2 joueurBot;
    Inventaire inventaire,inventaireCopy;


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
        inventaireCopy = new Inventaire();
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
    void placerOuvrierNourritureSuffisanteETOutilsDispo() {
        inventaire.setNourriture(6);
        if(zone1.getNbPlaceZone() == 1){
            expectedChoice = new Choix(0, 1);
            actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
            assertEquals(expectedChoice, actualChoice);
        }
    }
    @Test
    void placerOuvrierNourritureSuffisanteETOutilsIndispo() {
        inventaire.setNourriture(6);
        zone1.setNbPlaceDispo(0);

        for(int i=0 ; i< 2; i++){ //2fois pour tester les 2 conditions
            if (zone6.getNbPlaceDispo()>= 5 && inventaire.getNbOuvrierDispo() == 5){
                System.out.println("Test1");
                expectedChoice = new Choix(5, 5);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
                zone6.setNbPlaceDispo(4); // pour passer dans la 2e condition
            }
            else {
                System.out.println("Test2");
                expectedChoice = new Choix(5, 5);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertNotEquals(expectedChoice, actualChoice);
            }
        }
    }


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    void placerOuvrierGlaisieree() {
        inventaire.setNourriture(6);
        zone1.setNbPlaceDispo(0);
        zone6.setNbPlaceDispo(4);
        zone5.setNbPlaceDispo(3);
        for(int i=0 ; i< 2; i++){ //2fois pour tester les 2 conditions
            if (zone4.getNbPlaceDispo()>= 3 && inventaire.getNbOuvrierDispo() >= 3){
                expectedChoice = new Choix(3, 3);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
                zone4.setNbPlaceDispo(2); // pour passer dans la 2e condition
            }
            else {
                expectedChoice = new Choix(3, 3);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertNotEquals(expectedChoice, actualChoice);
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    void recupRes() {
    	inventaire.resetInventory();
    	zone1 = new Zone(1);
    	joueurBot.recupeRes(inventaire, zone1);
    	assertEquals(inventaire.getNbOutils(), 1);
    	
    }
}

