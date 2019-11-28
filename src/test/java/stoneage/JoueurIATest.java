package stoneage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import java.util.ArrayList;
import java.util.Collections;


public class JoueurIATest {
    ArrayList<Zone> listeZones = new ArrayList<Zone>();
    Zone zone1, zone2, zone3, zone4, zone5, zone6,zone7,zone8, zone9, zone10, zone11, zone12, zone13,zone14;
    Choix actualChoice ,expectedChoice;
    JoueurIA joueurIA;
    Inventaire inventaire;
    CarteCivilisation carte;
    ArrayList<CarteCivilisation> listeDesCartes;
    
    @BeforeEach
    void setUp(){
        zone1 = new Zone(1);
        zone2 = new Zone(2);
        zone3 = new Zone(3);
        zone4 = new Zone(4);
        zone5 = new Zone(5);
        zone6 = new Zone(6);
        zone7 = new Zone(7);
        zone8 = new Zone(8);
        zone9 = new Zone(9);
        zone10 =new Zone(10);
        zone11= new Zone(11);
        zone12= new Zone(12);
        zone13= new Zone(13);
        zone14= new Zone(14);

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

        joueurIA = new JoueurIA("o",1);
        inventaire = new Inventaire();
        
        //Collections.copy(carte.getAllCards(),listeDesCartes);
    }

    @Test
    void placerOutilSiPasOutil() {
    	//si le joueur n'a pas d'outil on teste qu'il retourne bien 0 outil a placer dans les zone qui vont lancer des dé 
    	int nbRessource=8;
    	for (int z=1;z<8;z++) {
    		int nbOutil=joueurIA.placerOutils(0, nbRessource, listeZones.get(z));
    		assertEquals(nbOutil ,0);
    	}
    }
    
    @Test
    void placerOutilSiYaOutil() {
    	//si le joueur a un nombre de ressource modulo zone pas loin du nombre d'outil on retourne le nombre d'outil manquant a placer dans les zone qui vont lancer des dé 
    		int nbOutil=joueurIA.placerOutils(1, 8, listeZones.get(2));//zone foret n°3 : donc 8%3=2 , nbOutil doit etre egale a 1 
    		assertEquals(nbOutil ,1);
    		nbOutil=joueurIA.placerOutils(4, 8, listeZones.get(2));//zone foret n°3 : donc 8%3=2 , nbOutil doit etre egale a 2 cal le joueur a assez d'outil
    		assertEquals(nbOutil ,4);
    		nbOutil=joueurIA.placerOutils(1, 8, listeZones.get(3));//zone Glaissiere n°4 : donc 8%4=0 , nbOutil doit etre egale a 0
    		assertEquals(nbOutil ,0);
    		nbOutil=joueurIA.placerOutils(4, 8, listeZones.get(3));//zone Glaissiere n°4 : donc 8%4=0 , nbOutil doit etre egale a 1 car on a 4 outil dispo
    		assertEquals(nbOutil ,4);
    	
    }

    
   
    
    @Disabled("pas pret")
    @Test
    void placerOuvrierNourritureInsuffisante() {
        for(int i=0; i<5 ; i++){
            inventaire.setNourriture(i);
            expectedChoice = new Choix(1, 5-i);
            actualChoice = joueurIA.placerOuvriers(listeZones, inventaire);
            assertEquals(expectedChoice, actualChoice);
        }
    }
    @Disabled("pas pret")
    @Test
    void placerOuvrierNourritureSuffisante() {
        inventaire.setNourriture(5);
        actualChoice = joueurIA.placerOuvriers(listeZones, inventaire);
        assertTrue(actualChoice.nbOuvriersChoisie >=0);
        assertTrue(actualChoice.nbOuvriersChoisie <=5);
        assertTrue(actualChoice.nbOuvriersChoisie <=inventaire.getNbOuvrierDispo());
        assertTrue(actualChoice.nbOuvriersChoisie <= listeZones.get(actualChoice.zoneChoisie).getNbPlaceDispo());

    }


}

