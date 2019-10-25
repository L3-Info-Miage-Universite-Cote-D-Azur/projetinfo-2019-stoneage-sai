package stoneage;

import stoneage.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.*;

public class ZoneTest {
    private Zone zone3; // zone Foret (7 places)
    @BeforeEach
    void setUp(){
        zone3 = new Zone(3);
    }

    @Test
    public void placerOuvrier() {
        zone3.placerOuvrier(1);
        assertTrue(zone3.ouvrierPlace(1));
    }
}