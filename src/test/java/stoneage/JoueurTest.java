package stoneage;

import stoneage.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.*;

public class JoueurTest {
    private Choix choix;
    private Joueur j;
    @BeforeEach
    void setUp(){
        choix = new Choix(3,1);
        j = new Joueur();
    }
    @Test
    public void placerOuvrier() {
        assertEquals(choix, j.placerOuvriers(3, 1));
    }
}