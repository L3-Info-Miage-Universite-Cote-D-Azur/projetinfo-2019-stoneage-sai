package stoneage;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.* ;

@ExtendWith(MockitoExtension.class)
public class ZoneTest {
    Zone area1,area2,area3,area4,area5,area6,area7;
    Zone zone1,zone2,zone3,zone4,zone5,zone6,zone7;
    Zone[] areaList = new Zone[6];
    Zone[] listeZone = new Zone[6];
    Inventaire inventaire;
    private  ArrayList<CarteCivilisation> listeDesCartes;
    public CarteCivilisation carte=new CarteCivilisation();
    private Joueurs joueur;
    private Choix choix1, choix2, choix3, choix4, choix5, choix6, choix7;

    @Mock
    Dé dice;

    @BeforeEach
    void setUp(){
        area1 = new Zone(1);
        area2 = new Zone(2);
        area3 = new Zone(3);
        area4 = new Zone(4);
        area5 = new Zone(5);
        area6 = new Zone(6);
        area7 = new Zone(7);

        zone1 = new Zone(1, dice);
        zone2 = new Zone(2, dice);
        zone3 = new Zone(3, dice);
        zone4 = new Zone(4, dice);
        zone5 = new Zone(5, dice);
        zone6 = new Zone(6, dice);
        zone7 = new Zone(7, dice);

        areaList[0] = area1;
        areaList[1] = area2;
        areaList[2] = area3;
        areaList[3] = area4;
        areaList[4] = area5;
        areaList[5] = area6;

        listeZone[0] = zone1;
        listeZone[1] = zone2;
        listeZone[2] = zone3;
        listeZone[3] = zone4;
        listeZone[4] = zone5;
        listeZone[5] = zone6;

        inventaire = new Inventaire();
        
        listeDesCartes=new ArrayList<CarteCivilisation>();
    	listeDesCartes=carte.getAllCards();
    	
    	joueur = new Joueur("oss",1);
    	
    	choix1 = new Choix(1, 1);
    	choix2 = new Choix(2, 2);
    	choix3 = new Choix(3, 3);
    	choix4 = new Choix(4, 4);
    	choix5 = new Choix(5, 5);
    	choix6 = new Choix(6, 5);
    	choix7 = new Choix(7, 1);
    }

    /* TEST 1: placement des ouvriers
     * On ne doit pas pouvoir placer plus de 5 ouvriers sur les zones
     * car on a maximum 5ouvrier .
     * Et on ne doit pas pouvoir placer 0 ouvrier sur une zone.
     *
     * TEST2: test de lancéDeDés avec un dé truqué pour éliminer l'aléatoire.
     */

    @Test
    public void placerOuvrier(){
        for(Zone zones : areaList){
            for (int i = 1; i < 6; i++){
                zones.placerOuvrier(inventaire, i);
                if (zones == area1 && i>1) { // zone1, une seule place donc on sépare ce case des autres
                    assertNotEquals(i, zones.getNbOuvriersPlaces());
                }else{
                    assertEquals(i, zones.getNbOuvriersPlaces());
                }
                //reset des zones et de l'inventaire
                zones.resetNbOuvriersPlaces();
                zones.setNbPlaceDispo(zones.getNbPlaceZone());
                inventaire.resetAvailableWorkers();
            }
        }
    }
    
    
    
    @Test
    public void recupeRes() {
    	assertEquals(area1.NomZone(), "Fabrication d'Outils");
    	assertEquals(area2.NomZone(), "Chasse");
    	assertEquals(area3.NomZone(), "foret");
    	assertEquals(area4.NomZone(), "glaisière");
    	assertEquals(area5.NomZone(), "carrière");
    	assertEquals(area6.NomZone(), "rivière");
    	assertEquals(area7.NomZone(), "champ");
    	

    	area1.placerOuvrier(inventaire, choix1.nbOuvriersChoisie);
    	assertEquals(inventaire.getNbOuvrierDispo(), 4);
    	area1.recupeRes(listeDesCartes, inventaire, joueur);
    	assertEquals(inventaire.getNbOuvrierDispo(), 5);
    	
    	area2.placerOuvrier(inventaire, choix2.nbOuvriersChoisie);
    	assertEquals(inventaire.getNbOuvrierDispo(), 3);
    	area2.recupeRes(listeDesCartes, inventaire, joueur);
    	assertEquals(inventaire.getNbOuvrierDispo(), 5);
    	
    	area3.placerOuvrier(inventaire, choix3.nbOuvriersChoisie);
    	assertEquals(inventaire.getNbOuvrierDispo(), 2);
    	area3.recupeRes(listeDesCartes, inventaire, joueur);
    	assertEquals(inventaire.getNbOuvrierDispo(), 5);
    	
    	area4.placerOuvrier(inventaire, choix4.nbOuvriersChoisie);
    	assertEquals(inventaire.getNbOuvrierDispo(), 1);
    	area4.recupeRes(listeDesCartes, inventaire, joueur);
    	assertEquals(inventaire.getNbOuvrierDispo(), 5);
    	
    	area5.placerOuvrier(inventaire, choix5.nbOuvriersChoisie);
    	assertEquals(inventaire.getNbOuvrierDispo(), 0);
    	area5.recupeRes(listeDesCartes, inventaire, joueur);
    	assertEquals(inventaire.getNbOuvrierDispo(), 5);
    	
    	area6.placerOuvrier(inventaire, choix6.nbOuvriersChoisie);
    	assertEquals(inventaire.getNbOuvrierDispo(), 0);
    	area6.recupeRes(listeDesCartes, inventaire, joueur);
    	assertEquals(inventaire.getNbOuvrierDispo(), 5);
    	
    	area7.placerOuvrier(inventaire, choix7.nbOuvriersChoisie);
    	assertEquals(inventaire.getNbOuvrierDispo(), 4);
    	area7.recupeRes(listeDesCartes, inventaire, joueur);
    	assertEquals(inventaire.getNbOuvrierDispo(), 5);
    }
    
    
    @Test
    public void throwDice() {
        when(dice.Lancer()).thenReturn(6);
        for(Zone zones : listeZone) {
            for (int i = 1; i < 6; i++) {
                assertEquals(6 * i, zones.lancéDeDés(i));
            }
        }
    }
}