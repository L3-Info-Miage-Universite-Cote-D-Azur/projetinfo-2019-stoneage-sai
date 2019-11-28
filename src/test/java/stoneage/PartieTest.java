
package stoneage;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;

public class PartieTest {
	private  Partie partie;

    private Inventaire  inv,inv2;
    private Joueurs joueur, joueurIA;
   
    @BeforeEach
    void setUp(){
    	inv = new Inventaire();
    	inv2 = new Inventaire();
    	joueur = new Joueur("oss",1);
    	joueurIA = new JoueurIA("oss",2);
    	//retirer 2 ouvrier de ouvrier dispo pour le teste de PhaseAction
    	partie = new Partie();
    }
     
	@Test	
	public void testPhaseAction() {
		assertEquals(inv.listeZonesDispo.size(), 15); //il y a 6 zones
		
		for(int i = 0; i < 15 ;i++){
			inv.listeZonesJouer.add(true); //On initialise toute les zones a True
		}
		partie.phaseAction(inv, joueur); //La fonction doit remettre False a toutes les zones
		for(int i = 0; i < 15; i++){
			assertFalse(inv.listeZonesJouer.get(i));//On vérifie si toutes les zones sont a False
			assertEquals(inv.listeOuvriersPlaces.get(i),0);//on verifie si tous les ouvriers sont bien sortie du plateau
		}
		
		assertEquals(inv.getNbOuvrierDispo(),5); // on verifie si le nb d'ouvrier dispo est reset
	}
	@Test
	public void testPhasePlacement() {
		inv.setNourriture(0);
		assertEquals(inv.listeZonesDispo.size(),15); 

		partie.phasePlacement(inv, joueurIA); //Comme le JoueurIA n'a pas de nourriture il doit jouer la zone chasse
		assertFalse(inv.listeZonesJouer.get(2));// "listeZonesJouer.get(2)" est la zone chasse
		assertEquals(inv.listeZonesDispo.size(),15); // il reste encore de la place dans la zone chasse donc toujours 15 zone dispo
	}
	
	@Disabled("pas pret")
	@Test
	public void testPhaseNourrir() {
		joueur = new Joueur("oss",3);
		
		inv.resetInventory();
		partie.phaseNourrir(inv, joueur, 1);
		assertEquals(inv.getNourriture(), 10);
		
		
		inv.resetInventory();
		inv.setNourriture(0);
		inv.setScore(30);     //On initialise le score du joueur a 30
		partie.phaseNourrir(inv, joueur, 1);  //Comme le joueur n'a pas assez de nourriture et de ressource pour nourrire ses ouvriers, il a une pénalité de 10 points
		assertEquals(inv.getScore(), 20);     //Le score du joueur doit donc etre de 20
		
		
		inv.resetInventory();
		inv.setNourriture(1);
		inv.setNbBois(10);
		inv.setNbRessource(10);
		partie.phaseNourrir(inv, joueur, 1);  //Le joueur utilise sont bois pour nourrire ses ouvriers
		assertEquals(inv.getNbBois(), 6);
		assertEquals(inv.getNbRessource(), 6);
		
		
		inv.resetInventory();
		inv.setNourriture(0);
		inv.setNbBois(3);
		inv.setNbArgile(10);
		inv.setNbRessource(13);
		partie.phaseNourrir(inv, joueur, 1);  //Le joueur utilise 3 bois et 2 argiles pour nourrire ses ouvriers
		assertEquals(inv.getNbBois(), 0);
		assertEquals(inv.getNbArgile(), 8);
		assertEquals(inv.getNbRessource(), 8);
		
		
		inv.resetInventory();
		inv.setNourriture(0);
		inv.setNbBois(1);
		inv.setNbArgile(1);
		inv.setNbPierre(1);
		inv.setNbOr(10);
		inv.setNbRessource(13);
		partie.phaseNourrir(inv, joueur, 1);  //Le joueur utilise le bois, l'argile, la pierre et 2 or pour nourrire ses ouvriers
		assertEquals(inv.getNbBois(), 0);
		assertEquals(inv.getNbArgile(), 0);
		assertEquals(inv.getNbPierre(), 0);
		assertEquals(inv.getNbOr(), 8);
		
		
		
		inv.resetInventory();
		inv.setNourriture(1);
		inv.setNbBois(0);
		inv.setNbArgile(1);
		inv.setNbPierre(4);
		inv.setNbOr(10);
		inv.setNbRessource(15);
		partie.phaseNourrir(inv, joueur);
		assertEquals(inv.getNbBois(), 0);
		assertEquals(inv.getNbArgile(), 0);
		assertEquals(inv.getNbPierre(), 1);
		assertEquals(inv.getNbOr(), 10);
		assertEquals(inv.getNbRessource(), 11);
		
	}
	@Test
	public void TestDemanderCadeaux() {
		ArrayList<Joueurs> listeJoueurs =new ArrayList<>();
	    ArrayList<Inventaire> listeInventaires=new ArrayList<>() ;
		listeJoueurs.add(joueurIA); 
       	listeJoueurs.add(joueur);
    	listeInventaires.add(inv);
    	listeInventaires.add(inv2);
		inv2.setNbRessource(0);
		partie.demanderCadeau(listeInventaires, listeJoueurs, joueur, inv2);
		assertEquals(inv2.getNbRessource(),1);    //l'inventaire 2 recois une ressource en cadeaux 
		
	}
	
}
