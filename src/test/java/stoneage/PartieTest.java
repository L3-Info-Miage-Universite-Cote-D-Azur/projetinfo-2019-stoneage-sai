
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
    private Joueurs joueur;
   
    @BeforeEach
    void setUp(){
    	inv=new Inventaire();
    	joueur=new Joueur();
    	nbZone=4;
    	//retirer 2 ouvrier de ouvrier dispo pour le teste de PhaseAction
    	partie =new Partie(nbZone);
    }

	@Test	
	public void testPhaseAction() {	
		assertEquals(inv.listeZonesDispo.size(),4); 
		partie.phaseAction(inv,joueur,1);
		// cette phase prend chaque zone dans la liste et l'efface apres avoir fini avec 
		assertEquals(inv.listeZonesDispo.size(),0); //verifier si cette methode a bien vider la liste 		
	}

	@Test
	public void testPhasePlacement() {
		assertEquals(inv.listeZonesDispo.size(),4); 
		assertEquals(inv.listeZonesJouer.size(),0); 

		partie.phasePlacement(inv,joueur,1);
		assertTrue(4-inv.listeZonesDispo.size()==inv.listeZonesJouer.size()); 
	}

}
