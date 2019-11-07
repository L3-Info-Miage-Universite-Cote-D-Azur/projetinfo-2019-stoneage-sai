
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
    private  ArrayList<Zone> listeZonesDispo = new ArrayList<>();   
    private  ArrayList<Zone> listeZonesJouer = new ArrayList<>(); 
   
    @BeforeEach
    void setUp(){
    	//retirer 2 ouvrier de ouvrier dispo pour le teste de PhaseAction
    	partie =new Partie();
    	nbZone=4;
        for (int i=3;i <= nbZone+2;i++ ){
        	Zone zone= new Zone(i);
        	listeZonesDispo.add(zone);
        } //remplir la liste des zones Disponnibre pris comme exemple pour ce teste
    }
	@Test	
	public void testPhaseAction() {	
		assertEquals(listeZonesDispo.size(),4); 
		partie.phaseAction(listeZonesDispo);
		// cette phase prend chaque zone dans la liste et l'efface apres avoir fini avec 
		assertEquals(listeZonesDispo.size(),0); //verifier si cette methode a bien vider la liste 		
	}

	@Test
	public void testPhasePlacement() {
		assertEquals(listeZonesDispo.size(),4); 
		assertEquals(listeZonesJouer.size(),0); 
		partie.phasePlacement(listeZonesDispo,listeZonesJouer);
		assertTrue(4-listeZonesDispo.size()==listeZonesJouer.size()); 
	}

}
