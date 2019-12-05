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
	Zone area1,area2,area3,area4,area5,area6,area7,area8,area9,area10,area11,area12,area13,area14,area15,area16;
    Zone zone1,zone2,zone3,zone4,zone5,zone6,zone7;
    Zone[] areaList = new Zone[16];
    Zone[] listeZone = new Zone[6];
    Choix[] listeChoix = new Choix[16];
    Inventaire inventaire;
    private  ArrayList<CarteCivilisation> listeDesCartes;
    private ArrayList<BuildingTiles> listeDesBat;
    
    public CarteCivilisation carte=new CarteCivilisation();
    public BuildingTiles bat = new BuildingTiles();
    private Joueurs joueur;
    private Choix choix1, choix2, choix3, choix4, choix5, choix6, choix7, choix8,choix9,choix10,choix11,choix12,choix13,choix14,choix15,choix16;

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
        area8 = new Zone(8);
        area9 = new Zone(9);
        area10 = new Zone(10);
        area11 = new Zone(11);
        area12 = new Zone(12);
        area13 = new Zone(13);
        area14 = new Zone(14);
        area15 = new Zone(15);
        area16 = new Zone(16);

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
        areaList[6] = area7;
        areaList[7] = area8;
        areaList[8] = area9;
        areaList[9] = area10;
        areaList[10] = area11;
        areaList[11] = area12;
        areaList[12] = area13;
        areaList[13] = area14;
        areaList[14] = area15;
        areaList[15] = area16;

        listeZone[0] = zone1;
        listeZone[1] = zone2;
        listeZone[2] = zone3;
        listeZone[3] = zone4;
        listeZone[4] = zone5;
        listeZone[5] = zone6;

        inventaire = new Inventaire();
        
        listeDesCartes=new ArrayList<CarteCivilisation>();
        listeDesCartes=carte.getAllCards();
        
        listeDesBat = new ArrayList<BuildingTiles>();
        listeDesBat = bat.getCards();
        
        joueur = new Joueur("oss",1);
        
        choix1 = new Choix(1, 1);
        choix2 = new Choix(2, 2);
        choix3 = new Choix(3, 3);
        choix4 = new Choix(4, 4);
        choix5 = new Choix(5, 5);
        choix6 = new Choix(6, 5);
        choix7 = new Choix(7, 1);
        choix8 = new Choix(8, 1);
        choix9 = new Choix(9, 1);
        choix10 = new Choix(10, 1);
        choix11 = new Choix(11, 1);
        choix12 = new Choix(12, 1);
        choix13 = new Choix(13, 1);
        choix14 = new Choix(14, 1);
        choix15 = new Choix(15, 1);
        choix16 = new Choix(16, 1);
        
        listeChoix[0] = choix1;
        listeChoix[1] = choix2;
        listeChoix[2] = choix3;
        listeChoix[3] = choix4;
        listeChoix[4] = choix5;
        listeChoix[5] = choix6;
        listeChoix[6] = choix7;
        listeChoix[7] = choix8;
        listeChoix[8] = choix9;
        listeChoix[9] = choix10;
        listeChoix[10] = choix11;
        listeChoix[11] = choix12;
        listeChoix[12] = choix13;
        listeChoix[13] = choix14;
        listeChoix[14] = choix15;
        listeChoix[15] = choix16;
        
    }

    /** TEST 1: placement des ouvriers
     * On ne doit pas pouvoir placer plus de 5 ouvriers sur les zones
     * car on a maximum 5ouvrier .
     * Et on ne doit pas pouvoir placer 0 ouvrier sur une zone.
     *
     * TEST2: test de lancéDeDés lancer n des et  verifier que la somme des valeur est juste .
     **/

    @Test
    public void placerOuvrier(){
        for (int zone = 0; zone <= 5; zone++) {
              if (zone == 0) {                                  //On separe le cas de la premiere qui peut recevoir un seul ouvriers
                  areaList[zone].placerOuvrier(inventaire, 1);
                  assertEquals(1, areaList[zone].getNbOuvriersPlaces());
                  areaList[zone].placerOuvrier(inventaire, 3);
                  assertEquals(1, areaList[zone].getNbOuvriersPlaces());
                  inventaire.resetAvailableWorkers();
                  continue;
              }
           for (int i = 1; i < 6; i++){
               areaList[zone].placerOuvrier(inventaire, i);
               if (i == 1) {
                   assertEquals(i, areaList[zone].getNbOuvriersPlaces());
               }
               else if (i == 2) {
                   assertEquals(i + 1, areaList[zone].getNbOuvriersPlaces());
               }
               else if (i == 3) {
                   assertEquals(3, areaList[zone].getNbOuvriersPlaces());
               }
               else if (zone ==15) {
            	   assertEquals(2, areaList[zone].getNbOuvriersPlaces());
               }
           }

           inventaire.resetAvailableWorkers();
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
        assertEquals(area8.NomZone(), "Civilisation 1");
        assertEquals(area9.NomZone(), "Civilisation 2");
        assertEquals(area10.NomZone(), "Civilisation 3");
        assertEquals(area11.NomZone(), "Civilisation 4");
        assertEquals(area12.NomZone(), "Batiment 1");
        assertEquals(area13.NomZone(), "Batiment 2");
        assertEquals(area14.NomZone(), "Batiment 3");
        assertEquals(area15.NomZone(), "Batiment 4");
        assertEquals(area16.NomZone(), "Hutte");
        

        area1.placerOuvrier(inventaire, choix1.nbOuvriersChoisie);
        assertEquals(inventaire.getNbOuvrierDispo(), 4);
        area1.recupeRes(listeDesCartes, listeDesBat, inventaire, joueur);
        assertEquals(inventaire.getNbOuvrierDispo(), 5);
        
        area2.placerOuvrier(inventaire, choix2.nbOuvriersChoisie);
        assertEquals(inventaire.getNbOuvrierDispo(), 3);
        area2.recupeRes(listeDesCartes, listeDesBat, inventaire, joueur);
        assertEquals(inventaire.getNbOuvrierDispo(), 5);
        
        area3.placerOuvrier(inventaire, choix3.nbOuvriersChoisie);
        assertEquals(inventaire.getNbOuvrierDispo(), 2);
        area3.recupeRes(listeDesCartes, listeDesBat, inventaire, joueur);
        assertEquals(inventaire.getNbOuvrierDispo(), 5);
        
        area4.placerOuvrier(inventaire, choix4.nbOuvriersChoisie);
        assertEquals(inventaire.getNbOuvrierDispo(), 1);
        area4.recupeRes(listeDesCartes, listeDesBat, inventaire, joueur);
        assertEquals(inventaire.getNbOuvrierDispo(), 5);
        
        area5.placerOuvrier(inventaire, choix5.nbOuvriersChoisie);
        assertEquals(inventaire.getNbOuvrierDispo(), 0);
        area5.recupeRes(listeDesCartes, listeDesBat, inventaire, joueur);
        assertEquals(inventaire.getNbOuvrierDispo(), 5);
        
        area6.placerOuvrier(inventaire, choix6.nbOuvriersChoisie);
        assertEquals(inventaire.getNbOuvrierDispo(), 0);
        area6.recupeRes(listeDesCartes, listeDesBat, inventaire, joueur);
        assertEquals(inventaire.getNbOuvrierDispo(), 5);
        
        for (int i = 6; i <= 14; i++) {
        	areaList[i].placerOuvrier(inventaire, listeChoix[i].nbOuvriersChoisie);
        	assertEquals(inventaire.getNbOuvrierDispo(), 4);
        	areaList[i].recupeRes(listeDesCartes, listeDesBat, inventaire, joueur);
        	assertEquals(inventaire.getNbOuvrierDispo(), 5);
        }
    }
    
    
    
    @Test
    public void throwDice() {

    	for (int zone = 0; zone <= 5; zone++) {
    		for (int i = 1; i < 6; i++) {
    			assertTrue((6 * i)>= areaList[zone].lancéDeDés(i));
    			assertTrue((1 * i)<= areaList[zone].lancéDeDés(i));
    		}
    	}
    }
    
}