package stoneage;

import stoneage.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class JoueurTest {
    ArrayList<Zone> listeZone ;
    Zone zone;
    Choix choix ,choix2;
    Joueur j;
    Inventaire inventaire;
    
    @BeforeEach
    void setUp(){
        listeZone = new ArrayList<>();
        for (int i = 3; i <=6; i++) {
            zone = new Zone(i);
            listeZone.add(zone);
        }
        j = new Joueur();
	inventaire = new Inventaire();      
        choix2=j.placerOuvriers(inventaire);
        choix = new Choix(choix2.zoneChoisie,choix2.nbOuvriersChoisie);//je crée un objet avec les valeurs 
        //zoneChoisie et nbOuvriersChoisie renvoyé par j.placerOuvriers(listeZone, inventaire)par ce que 
        //leur création se fait avec un random et c'est le seul moyen de les recupérées .
    }
	@Test
    void placerOuvrier() {
            assertEquals(choix, choix2);   
    }
}
