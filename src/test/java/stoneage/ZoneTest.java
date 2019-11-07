package stoneage;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
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
    Dé unDéTruqué;


    @BeforeEach
    void setUp(){
        zone3 = new Zone(3);
        zone4 = new Zone(4);
        zone5 = new Zone(5);
        zone6 = new Zone(6);
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
}