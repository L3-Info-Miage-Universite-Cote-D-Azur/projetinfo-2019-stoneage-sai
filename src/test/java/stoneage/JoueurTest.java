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
        for (int i = 1; i <=6; i++) {
            zone = new Zone(i);
            listeZone.add(zone);
        }
        j = new Joueur();
        inventaire = new Inventaire(); 
        
        choix=j.placerOuvriers(listeZone,inventaire);
        choix2 = new Choix(choix.zoneChoisie,choix.nbOuvriersChoisie);
        /*je crée un objet avec les valeurs zoneChoisie et nbOuvriersChoisie
         *  renvoyé par j.placerOuvriers(listeZone, inventaire)par ce que 
         * leur création se fait avec un random et c'est le seul moyen de les recupérées . */
        zone.recupeRes( inventaire,j);
   
    }
	@Test
    void placerOuvrier() {
			Zone zoneChoisie=listeZone.get(choix.zoneChoisie);
        	assertEquals(choix, choix2); 
            assertTrue(choix.zoneChoisie>=0); 
            assertTrue(choix.zoneChoisie<=5);
            //verifier que la zone choisie est bien dans la liste dans zone
            assertTrue(choix.nbOuvriersChoisie <=inventaire.getNbOuvrierDispo());  
            assertTrue(choix.nbOuvriersChoisie <=zoneChoisie.getNbPlaceDispo());  
            /*verifier que le nombre d'ouvrier choisie est inferieur au nombre 
             * de place disponnible dans la zone  et qu'il est inferieur au nombre
             *  d'ouvrier que le joueur possede */
    }
	@Test
	void recupeRes(){

		Zone zoneChoisie=listeZone.get(choix.zoneChoisie);
		assertEquals(inventaire.getNbOuvrierDispo(),5);
		assertEquals(zoneChoisie.getNbPlaceDispo(),zoneChoisie.getNbPlaceZone());
		//verifier si le nombre de place disponnible dans la zone choisie a bien etait remis a jour (= initial)
		assertEquals(zoneChoisie.getNbOuvriersPlaces(),0);
		//verifier si les ouvrier on bien etait recuperer par le joueur 
		assertTrue((zoneChoisie.lancéDeDés(choix.nbOuvriersChoisie)/choix.nbOuvriersChoisie) >=1 
				&&(zoneChoisie.lancéDeDés(choix.nbOuvriersChoisie)/choix.nbOuvriersChoisie ) <=6  );
		//verifier si le dé lancé est bien etre 1 et 6 
	}
}
