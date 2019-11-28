package stoneage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;

public class JoueurBot2Test {
    ArrayList<Zone> listeZones = new ArrayList<Zone>();
    Zone zone1, zone2, zone3, zone4, zone5, zone6, zone7, zone8, zone9, zone10, zone11, zone12 ,zone13, zone14;
    Choix actualChoice ,expectedChoice;
    JoueurBot2 joueurBot;
    
    Inventaire inventaire,inventaireCopy;
    CarteCivilisation carte;
    ArrayList<CarteCivilisation> listeDesCartes;


    @BeforeEach
    void setUp(){
    	
        zone1 = new Zone(1);
        zone2 = new Zone(2);
        zone3 = new Zone(3); //Foret
        zone4 = new Zone(4); //Glaisiere
        zone5 = new Zone(5); //Carriere
        zone6 = new Zone(6); //riviere
        zone7 = new Zone(7);
        zone8 = new Zone(8);
        zone9 = new Zone(9);
        zone10 = new Zone(10);
        zone11 = new Zone(11);
        zone12 = new Zone(12);
        zone13 = new Zone(13);
        zone14 = new Zone(14);

        listeZones.add(zone1);
        listeZones.add(zone2);
        listeZones.add(zone3);
        listeZones.add(zone4);
        listeZones.add(zone5);
        listeZones.add(zone6);
        listeZones.add(zone7);
        listeZones.add(zone8);
        listeZones.add(zone9);
        listeZones.add(zone10);
        listeZones.add(zone11);
        listeZones.add(zone12);
        listeZones.add(zone13);
        listeZones.add(zone14);
        
        joueurBot = new JoueurBot2("sebastien",2);
        inventaire = new Inventaire();
        inventaireCopy = new Inventaire();
        
        listeDesCartes= new ArrayList<>();
        //Collections.copy(carte.getAllCards(),listeDesCartes);
    }

    
    @Test
    void placerOuvrierNourritureInsuffisante() {
        for(int i=0; i< 10 ; i++){
            inventaire.setNourriture(i);
            if(i<3){
                expectedChoice = new Choix(1, 3);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
            }
            else if(i>=5){
                expectedChoice = new Choix(1, 1);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
            }
        }
    }
    
    @Test
    void placerOuvrierNourritureSuffisanteETBoisinsufisant() {
        inventaire.setNourriture(11);
        inventaire.setNbBois(5);
        if(zone2.getNbPlaceZone() >= 2){
            expectedChoice = new Choix(2, 2);
            actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
            assertEquals(expectedChoice, actualChoice);
        }
    }
    @Test
    void placerOuvrierNourritureSuffisanteEtBoisSuf() {
        inventaire.setNourriture(11);
        inventaire.setNbBois(11);
        if(zone1.getNbPlaceZone() >= 1){
            expectedChoice = new Choix(0, 1);
            actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
            assertEquals(expectedChoice, actualChoice);
        }
    }
    // test pour partie a 2
    @Test
    void placerOuvrierNourritureSuffisanteETOutilsIndispo() { //premier choix 
    	StoneAge stoneAge = new StoneAge(2);
        inventaire.setNourriture(11);
        inventaire.setNbBois(11);
        zone1.setNbPlaceDispo(0);
            if (zone13.getNbPlaceDispo()>= 1){
                expectedChoice = new Choix(12, 1);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
            }
        
        }
    
    @Test
    void placerOuvrierNourritureSuffisanteETOutilsIndispo2() { //deuxieme choix
        inventaire.setNourriture(11);
        inventaire.setNbBois(11);
        zone1.setNbPlaceDispo(0);
        zone13.setNbPlaceDispo(0);
            if (zone11.getNbPlaceDispo()>= 1){
                expectedChoice = new Choix(11, 1);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
            }
        
        }
    
    @Test
    void placerOuvrierNourritureSuffisanteETOutilsIndispo3() { //troisieme choix
        inventaire.setNourriture(11);
        inventaire.setNbBois(11);
        zone1.setNbPlaceDispo(0);
        zone13.setNbPlaceDispo(0);
        zone12.setNbPlaceDispo(0);
            if (zone8.getNbPlaceDispo()>= 1){
                expectedChoice = new Choix(8, 1);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
            }
        
        }
    
    @Test
    void placerOuvrierNourritureSuffisanteETOutilsIndispo4() { //quatrieme choix
        inventaire.setNourriture(11);
        inventaire.setNbBois(11);
        zone1.setNbPlaceDispo(0);
        zone13.setNbPlaceDispo(0);
        zone12.setNbPlaceDispo(0);
        zone9.setNbPlaceDispo(0);
            if (zone7.getNbPlaceDispo()>= 1){
                expectedChoice = new Choix(7, 1);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
            }
        
        }
    
    @Test
    void placerOuvrierNourritureSuffisanteETOutilsIndispo5() { //cinquieme choix
        inventaire.setNourriture(11);
        inventaire.setNbBois(11);
        zone1.setNbPlaceDispo(0);
        zone13.setNbPlaceDispo(0);
        zone12.setNbPlaceDispo(0);
        zone9.setNbPlaceDispo(0);
        zone8.setNbPlaceDispo(0);
            if (zone7.getNbPlaceDispo()>= 1){
                expectedChoice = new Choix(6,1);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
                
            }
        
        }

    @Test
    void placerOuvrierNourritureSuffisanteETOutilsIndispo6() { //sixieme choix
        inventaire.setNourriture(11);
        inventaire.setNbBois(11);
        zone1.setNbPlaceDispo(0);
        zone13.setNbPlaceDispo(0);
        zone12.setNbPlaceDispo(0);
        zone9.setNbPlaceDispo(0);
        zone8.setNbPlaceDispo(0);
        zone7.setNbPlaceDispo(0);
            if (zone3.getNbPlaceDispo()>= 1){
                expectedChoice = new Choix(3, 5);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
            }
        
        }
    
    @Test
    void placerOuvrierNourritureSuffisanteETOutilsIndispo7() { //septieme choix
        inventaire.setNourriture(11);
        inventaire.setNbBois(11);
        zone1.setNbPlaceDispo(0);
        zone13.setNbPlaceDispo(0);
        zone12.setNbPlaceDispo(0);
        zone9.setNbPlaceDispo(0);
        zone8.setNbPlaceDispo(0);
        zone7.setNbPlaceDispo(0);
        zone4.setNbPlaceDispo(0);
            if (zone3.getNbPlaceDispo()>= 1){
                expectedChoice = new Choix(2, 5);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
            }
        
        }
    
    @Test
    void placerOuvrierNourritureSuffisanteETOutilsIndispo72() { 
        inventaire.setNourriture(11);
        inventaire.setNbBois(11);
        zone1.setNbPlaceDispo(0);
        zone13.setNbPlaceDispo(0);
        zone12.setNbPlaceDispo(0);
        zone9.setNbPlaceDispo(0);
        zone8.setNbPlaceDispo(0);
        zone7.setNbPlaceDispo(0);
        zone4.setNbPlaceDispo(0);
        zone3.setNbPlaceDispo(0);
            if (zone3.getNbPlaceDispo()>= 1){
                expectedChoice = new Choix(1, 5);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
            }
        
        }
    //test pour partie a 3
    @Test
    void placerOuvrierNourritureSuffisanteETOutilsIndispo8() { //premier choix 
    	StoneAge stoneAge = new StoneAge(3);
        inventaire.setNourriture(11);
        inventaire.setNbBois(11);
        zone1.setNbPlaceDispo(0);
            if (zone13.getNbPlaceDispo()>= 1){
                expectedChoice = new Choix(13, 1);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
            }
        
        }
    @Test
    void placerOuvrierNourritureSuffisanteETOutilsIndispo9() { //deuxieme choix
        inventaire.setNourriture(11);
        inventaire.setNbBois(11);
        zone1.setNbPlaceDispo(0);
        zone14.setNbPlaceDispo(0);
            if (zone11.getNbPlaceDispo()>= 1){
                expectedChoice = new Choix(12, 1);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
            }
        
        }
    
    @Test
    void placerOuvrierNourritureSuffisanteETOutilsIndispo10() { //troisieme choix
        inventaire.setNourriture(11);
        inventaire.setNbBois(11);
        zone1.setNbPlaceDispo(0);
        zone14.setNbPlaceDispo(0);
        zone13.setNbPlaceDispo(0);
            if (zone8.getNbPlaceDispo()>= 1){
                expectedChoice = new Choix(11, 1);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
            }
        
        }
    
    @Test
    void placerOuvrierNourritureSuffisanteETOutilsIndispo11() { //4 choix
        inventaire.setNourriture(11);
        inventaire.setNbBois(11);
        zone1.setNbPlaceDispo(0);
        zone14.setNbPlaceDispo(0);
        zone13.setNbPlaceDispo(0);
        zone12.setNbPlaceDispo(0);
            if (zone8.getNbPlaceDispo()>= 1){
                expectedChoice = new Choix(9, 1);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
            }
        
        }
    
    @Test
    void placerOuvrierNourritureSuffisanteETOutilsIndispo12() { //5 choix
        inventaire.setNourriture(11);
        inventaire.setNbBois(11);
        zone1.setNbPlaceDispo(0);
        zone14.setNbPlaceDispo(0);
        zone13.setNbPlaceDispo(0);
        zone12.setNbPlaceDispo(0);
        zone10.setNbPlaceDispo(0);
            if (zone8.getNbPlaceDispo()>= 1){
                expectedChoice = new Choix(8, 1);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
            }
        
        }
    
    @Test
    void placerOuvrierNourritureSuffisanteETOutilsIndispo13() { //6 choix
        inventaire.setNourriture(11);
        inventaire.setNbBois(11);
        zone1.setNbPlaceDispo(0);
        zone14.setNbPlaceDispo(0);
        zone13.setNbPlaceDispo(0);
        zone12.setNbPlaceDispo(0);
        zone10.setNbPlaceDispo(0);
        zone9.setNbPlaceDispo(0);
            if (zone8.getNbPlaceDispo()>= 1){
                expectedChoice = new Choix(7, 1);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
            }
        
        }
    
    @Test
    void placerOuvrierNourritureSuffisanteETOutilsIndispo14() { //7 choix
        inventaire.setNourriture(11);
        inventaire.setNbBois(11);
        zone1.setNbPlaceDispo(0);
        zone14.setNbPlaceDispo(0);
        zone13.setNbPlaceDispo(0);
        zone12.setNbPlaceDispo(0);
        zone10.setNbPlaceDispo(0);
        zone9.setNbPlaceDispo(0);
        zone8.setNbPlaceDispo(0);
            if (zone8.getNbPlaceDispo()>= 1){
                expectedChoice = new Choix(6, 1);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
            }
        
        }
    
    @Test
    void placerOuvrierNourritureSuffisanteETOutilsIndispo15() { //8 choix
        inventaire.setNourriture(11);
        inventaire.setNbBois(11);
        zone1.setNbPlaceDispo(0);
        zone14.setNbPlaceDispo(0);
        zone13.setNbPlaceDispo(0);
        zone12.setNbPlaceDispo(0);
        zone10.setNbPlaceDispo(0);
        zone9.setNbPlaceDispo(0);
        zone8.setNbPlaceDispo(0);
        zone7.setNbPlaceDispo(0);
            if (zone8.getNbPlaceDispo()>= 1){
                expectedChoice = new Choix(4, 5);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
            }
        
        }
    
    @Test
    void placerOuvrierNourritureSuffisanteETOutilsIndispo16() { //9 choix
        inventaire.setNourriture(11);
        inventaire.setNbBois(11);
        zone1.setNbPlaceDispo(0);
        zone14.setNbPlaceDispo(0);
        zone13.setNbPlaceDispo(0);
        zone12.setNbPlaceDispo(0);
        zone10.setNbPlaceDispo(0);
        zone9.setNbPlaceDispo(0);
        zone8.setNbPlaceDispo(0);
        zone7.setNbPlaceDispo(0);
            if (zone8.getNbPlaceDispo()>= 1){
                expectedChoice = new Choix(3, 5);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
            }
        
        }
    
    @Test
    void placerOuvrierNourritureSuffisanteETOutilsIndispo17() { //10 choix
        inventaire.setNourriture(11);
        inventaire.setNbBois(11);
        zone1.setNbPlaceDispo(0);
        zone14.setNbPlaceDispo(0);
        zone13.setNbPlaceDispo(0);
        zone12.setNbPlaceDispo(0);
        zone10.setNbPlaceDispo(0);
        zone9.setNbPlaceDispo(0);
        zone8.setNbPlaceDispo(0);
        zone7.setNbPlaceDispo(0);
        zone4.setNbPlaceDispo(0);
            if (zone8.getNbPlaceDispo()>= 1){
                expectedChoice = new Choix(2, 5);
                actualChoice = joueurBot.placerOuvriers(listeZones, inventaire);
                assertEquals(expectedChoice, actualChoice);
            }
        
        }
    
}