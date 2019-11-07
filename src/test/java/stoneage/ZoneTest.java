package stoneage;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.* ;

@ExtendWith(MockitoExtension.class)
public class ZoneTest {
    private Zone zone3; // zone Foret (7 places)
    private Zone zone4; // zone Glaisière (7 places)
    private Zone zone5; // zone Carrière (7 places)
    private Zone zone6; // zone Rivière (7 places)
    private Zone[] listZone;
    Inventaire inventaire = new Inventaire();

    @Mock
    Dé dice = new Dé();

    @BeforeEach
    void setUp(){
        zone3 = new Zone(3, dice);
        zone4 = new Zone(4, dice);
        zone5 = new Zone(5, dice);
        zone6 = new Zone(6, dice);
        listZone = new Zone[]{zone3, zone4, zone5, zone6};
    }

    /* TEST 1: placement des ouvriers
     * On doit pouvoir placer entre 1 et 5 ouvriers sur les zones.
     *
     * TEST 2: resoudre:
     * Avoir un nombre de ressources correspondant à la zone et au nombre d'ouvriers
     * */

    @Test
    public void placerOuvrier() {
        for(Zone zone : listZone){
            for(int j=1; j<6;j++){
                zone.placerOuvrier(inventaire, j);
                assertTrue(zone.ouvrierPlace(j));
            }
        }
    }

    @Test
    public void resoudre(){
        when(dice.Lancer()).thenReturn(6);
        for(int i=1 ; i<6; i++) {
            zone3.placerOuvrier(inventaire, i);
            zone3.resoudre(inventaire);
            assertEquals((i*6)/3, inventaire.getNbBois());

            inventaire.resetInventory();

            zone4.placerOuvrier(inventaire, i);
            zone4.resoudre(inventaire);
            assertEquals((i*6)/4, inventaire.getNbArgile());

            inventaire.resetInventory();

            zone5.placerOuvrier(inventaire, i);
            zone5.resoudre(inventaire);
            assertEquals((i*6)/5, inventaire.getNbPierre());

            inventaire.resetInventory();

            zone6.placerOuvrier(inventaire, i);
            zone6.resoudre(inventaire);
            assertEquals((i*6)/6, inventaire.getNbOr());
        }
    }
}