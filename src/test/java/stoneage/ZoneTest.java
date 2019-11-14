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
    Inventaire inventaire;

    @Mock
    Dé dice = new Dé();

    @BeforeEach
    void setUp() {
        inventaire = new Inventaire();
    }

    /* TEST 1: placement des ouvriers
     * On doit pouvoir placer entre 1 et 5 ouvriers sur les zones.
     *
     * TEST 2: resoudre:
     * Avoir un nombre de ressources correspondant à la zone et au nombre d'ouvriers
     * */

    @Test
    public void placerOuvrier() {
        for (Zone zone : Zone.getListeZone()) {
            for (int j = 1; j < 6; j++) {
                zone.placerOuvrier(inventaire, j);
                assertTrue(zone.ouvrierPlace(j));
                assertEquals(7-j, Zone.getPlaceDispoParZone().get(0));
            }
        }
    }

    @Test
    public void throwDice() {
        when(dice.Lancer()).thenReturn(6);
        for (Zone zone: Zone.getListeZone()) {
            for(int i=1 ; i<6; i++) {
                assertEquals(6*i, zone.lancéDeDés(i));
                }
             }
    }
}