
package stoneage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PartieTest {
	private  Partie partie;
	Zone zone= new Zone();

    private Inventaire  inv,inv2;
    private Joueurs joueur, joueurIA, joueurBot2;
   
    @BeforeEach
    void setUp(){
    	inv = new Inventaire();
    	inv2 = new Inventaire();
    	joueur = new Joueur("oss",1);
    	joueurIA = new JoueurIA("oss",2);
    	joueurBot2 = new JoueurBot2("oss", 2);
    	//retirer 2 ouvrier de ouvrier dispo pour le teste de PhaseAction
    	partie = new Partie(true);
    }
     
	@Test	
	public void testPhaseAction() {
		assertEquals(inv.listeZonesDispo.size(), 16); //il y a 16 zones
		for(int i = 0; i < 16 ;i++){
			inv.listeZonesJouer.add(true); //On initialise toute les zones a True
		}
		partie.phaseAction(inv, joueur); //La fonction doit remettre False a toutes les zones
		for(int i = 0; i < 16; i++){
			assertFalse(inv.listeZonesJouer.get(i));//On vérifie si toutes les zones sont a False
			assertEquals(inv.listeOuvriersPlaces.get(i),0);//on verifie si tous les ouvriers sont bien sortie du plateau
		}
		assertEquals(inv.getNbOuvrierDispo(),5); // on verifie si le nb d'ouvrier dispo est reset
	}
	
	@Test
	public void testPhasePlacement() {
		inv.setNourriture(0);
		partie.phasePlacement(inv, joueurBot2); //Comme le JoueurBot2 n'a pas de nourriture il doit jouer la zone chasse
		assertTrue(inv.listeZonesJouer.get(1)); //"listeZonesJouer.get(1)" est la zone chasse
		
		inv2.setNourriture(20);
		partie.phasePlacement(inv2, joueurBot2); //Comme le JoueurBot2 a de la nourriture il ne va pas choisir la zone chasse
		assertFalse(inv2.listeZonesJouer.get(1));
	}
	

	@Test
	public void testPhaseNourrir() {
		joueur = new Joueur("oss",3);
		

		partie.phaseNourrir(inv, joueur);
		assertEquals(inv.getNourriture(), 10);
		
		inv.resetInventory();
		inv.setNourriture(0);
		inv.setScore(30);     //On initialise le score du joueur a 30
		partie.phaseNourrir(inv, joueur);  //Comme le joueur n'a pas assez de nourriture et de ressource pour nourrire ses ouvriers, il a une pénalité de 10 points
		assertEquals(inv.getScore(), 20);     //Le score du joueur doit donc etre de 20
		
		
		inv.resetInventory();
		inv.setNourriture(1);
		inv.setNbBois(10);
		inv.setNbRessource(10);
		partie.phaseNourrir(inv, joueur);  //Le joueur utilise sont bois pour nourrire ses ouvriers
		assertEquals(inv.getNbBois(), 6);
		assertEquals(inv.getNbRessource(), 6);
		
		inv.resetInventory();
		inv.setNourriture(0);
		inv.setNbBois(3);
		inv.setNbArgile(10);
		inv.setNbRessource(13);
		partie.phaseNourrir(inv, joueur);  //Le joueur utilise 3 bois et 2 argiles pour nourrire ses ouvriers
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
		partie.phaseNourrir(inv, joueur);  //Le joueur utilise le bois, l'argile, la pierre et 2 or pour nourrire ses ouvriers
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
	@Disabled
	public void TestDemanderCadeaux() {
		inv = new Inventaire();
		inv2 = new Inventaire();
		ArrayList<Joueurs> listeJoueurs =new ArrayList<>();
	    ArrayList<Inventaire> listeInventaires=new ArrayList<>();
	    
	    listeJoueurs.add(joueurBot2);
	    listeInventaires.add(inv);
    	
		// verifier que les joueurs n'ont pas de ressource ni outil ni nivau champ 
		assertTrue(inv2.getNbRessource()==0 &&inv2.getNbOutils()==0 &&inv2.getScoreChamp()==0 );    
		assertTrue(inv.getNbRessource()==0 &&inv.getNbOutils()==0 &&inv.getScoreChamp()==0 );

		// verifier que les joueur on ressus leur cadeau 
		Partie.demanderCadeau(zone,listeInventaires, listeJoueurs, joueurBot2, inv2);
		assertTrue(inv2.getNbRessource()==1 ||inv2.getNbOutils()==1 ||inv2.getScoreChamp()==1 );   
		assertTrue(inv.getNbRessource()==1 ||inv.getNbOutils()==1 ||inv.getScoreChamp()==1 );
	}
	
	
}
