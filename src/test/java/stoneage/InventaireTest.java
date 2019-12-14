package stoneage;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import stoneage.*;

public class InventaireTest {
	private Inventaire inv;
        public ArrayList<Boolean> listeZonesJouer ;
	@BeforeEach
	void setUp() {
            inv = new Inventaire();
            listeZonesJouer = new ArrayList<>();
	}

	@Test 
        public void TestAVide(){
            assertEquals(6,inv.lesRessources.size());
            assertEquals(0, inv.getNbRessource());
            assertEquals(Inventaire.NB_INITIAL_OUVRIERS, inv.getNbOuvrier());
            assertEquals(0,inv.getNbBois());
            assertEquals(0,inv.getNbArgile());
            assertEquals(0,inv.getNbPierre());
            assertEquals(0,inv.getNbOr());
            assertEquals(12,inv.getNourriture());
            assertEquals(0,inv.getNbOutils());
            assertEquals(0,inv.getScore());
            assertEquals(0,inv.getScoreChamp());
            assertEquals(16,inv.listeZonesDispo.size());
            assertEquals(16,inv.listeZonesJouer.size());       
            assertEquals(0,inv.getNbZoneJouer());
	}	
	
	@Test 
        public void TestAvecValeurs() {
            inv.setNbOuvrier(4);
            inv.lesRessources.put(3,new Ressources(3,"Bois",3));
            assertEquals(3, inv.getNbRessource());
            assertEquals(4, inv.getNbOuvrier());     
            
            inv.lesRessources.put(6,new Ressources(6,"Or",7));
            assertEquals(10, inv.getNbRessource());//3 bois et 7 or 
            
            for (int i=0;i < 6;i++ ){
                    inv.listeZonesJouer.set(i,true);
            }
            assertEquals(6,inv.getNbZoneJouer());
	}
        
        @Test
        public void  resetAvailableWorkers(){
            inv. resetAvailableWorkers();
            assertEquals(inv.getNbOuvrierDispo(),inv.getNbOuvrier());
        }     
}

