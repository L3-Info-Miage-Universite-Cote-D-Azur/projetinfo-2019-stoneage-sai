package stoneage;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.* ;

@ExtendWith(MockitoExtension.class)
public class ZoneTest {
    Zone zone1;     // Fabrication d'outils
    Zone zone2;     // Chasse
    Zone zone3;     // Foret
    Zone zone4;     // Glaisiere
    Zone zone5;     // Carriere
    Zone zone6;     // Riviere
    Zone[] listeZone = new Zone[6];
    Inventaire inventaire;

    @Mock
    Dé dice;

    @BeforeEach
    void setUp(){
        zone1 = new Zone(1, dice);
        zone2 = new Zone(2, dice);
        zone3 = new Zone(3, dice);
        zone4 = new Zone(4, dice);
        zone5 = new Zone(5, dice);
        zone6 = new Zone(6, dice);

        listeZone[0] = zone1;
        listeZone[1] = zone2;
        listeZone[2] = zone3;
        listeZone[3] = zone4;
        listeZone[4] = zone5;
        listeZone[5] = zone6;

        inventaire = new Inventaire();
    }

    /* TEST 1: placement des ouvriers
     * On ne doit pas pouvoir placer plus de 5 ouvriers sur les zones
     * car on a maximum 5ouvrier .
     * Et on ne doit pas pouvoir placer 0 ouvrier sur une zone.
     *
     */

    @Test
    public void placerOuvrier(){
        for(Zone zones : listeZone){
            for (int i = 1; i < 6; i++){
                zones.placerOuvrier(inventaire, i);
                if (zones == zone1 && i>1) { // zone1, une seule place donc on sépare ce case des autres
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
    public void throwDice() {
        when(dice.Lancer()).thenReturn(6);
        for(Zone zones : listeZone) {
            for (int i = 1; i < 6; i++) {
                assertEquals(6 * i, zones.lancéDeDés(i));
            }
        }
    }
}