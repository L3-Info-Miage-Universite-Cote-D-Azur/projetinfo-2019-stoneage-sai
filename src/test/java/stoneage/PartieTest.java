
package stoneage;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PartieTest {
	private  Partie partie;
	private int nbZone;

    private Inventaire  inv;
    private Joueurs joueur, joueurIA;
   
    @BeforeEach
    void setUp(){
    	inv = new Inventaire();
    	joueur = new Joueur();
    	joueurIA = new JoueurIA();
    	nbZone = 6;
    	//retirer 2 ouvrier de ouvrier dispo pour le teste de PhaseAction
    	partie = new Partie(nbZone);
    }

	@Test	
	public void testPhaseAction() {
		assertEquals(inv.listeZonesDispo.size(), 6); //il y a 6 zones
		
		for(int i = 0; i < 6 ;i++){
			inv.listeZonesJouer.add(true); //On initialise toute les zones a True
		}
		partie.phaseAction(inv, joueur, 1); //La fonction doit remettre False a toutes les zones
		for(int i = 0; i < 6; i++){
			assertFalse(inv.listeZonesJouer.get(i));  //On vérifie si toutes les zones sont a False
		}	
	}

	@Test
	public void testPhasePlacement() {
		inv.setNourriture(0);
		assertEquals(inv.listeZonesDispo.size(),6); 

		partie.phasePlacement(inv, joueurIA, 1); //Comme le JoueurIA n'a pas de nourriture il doit jouer la zone chasse
		assertFalse(inv.listeZonesJouer.get(2)); // "listeZonesJouer.get(2)" est la zone chasse
	}
	
	
	@Test
	public void testPhaseNourrir() {
		joueur = new Joueur();
		
		inv.resetInventory();
		partie.phaseNourrir(inv, joueur, 1);
		assertEquals(inv.getNourriture(), 10);
		
		
		inv.resetInventory();
		inv.setNourriture(0);
		inv.setScore(30);     //On initialise le score du joueur a 30
		partie.phaseNourrir(inv, joueur, 1);  //Comme le joueur n'a pas assez de nourriture et de ressource pour nourrire ses ouvriers, il a une pénalité de 10 points
		assertEquals(inv.getScore(), 20);     //Le score du joueur doit donc etre de 20
		
		
		inv.resetInventory();
		inv.setNourriture(0);
		inv.setNbBois(10);
		partie.phaseNourrir(inv, joueur, 1);  //Le joueur utilise sont bois pour nourrire ses ouvriers
		assertEquals(inv.getNbBois(), 5);
		
		
		inv.resetInventory();
		inv.setNourriture(0);
		inv.setNbBois(3);
		inv.setNbArgile(10);
		partie.phaseNourrir(inv, joueur, 1);  //Le joueur utilise 3 bois et 2 argiles pour nourrire ses ouvriers
		assertEquals(inv.getNbBois(), 0);
		assertEquals(inv.getNbArgile(), 8);
		
		
		inv.resetInventory();
		inv.setNourriture(0);
		inv.setNbBois(1);
		inv.setNbArgile(1);
		inv.setNbPierre(1);
		inv.setNbOr(10);
		partie.phaseNourrir(inv, joueur, 1);  //Le joueur utilise le bois, l'argile, la pierre et 2 or pour nourrire ses ouvriers
		assertEquals(inv.getNbBois(), 0);
		assertEquals(inv.getNbArgile(), 0);
		assertEquals(inv.getNbPierre(), 0);
		assertEquals(inv.getNbOr(), 8);
		
	}
	
}
