package stoneage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import stoneage.*;


public class InventaireTest {

	private Inventaire inv;

	@BeforeEach
	void setUp() {
		inv = new Inventaire();
	}


	@Test public void TestAVide() {
		assertEquals(0, inv.getNbRessource());					//Au debut le nombre de ressource doit etre a 0
		assertEquals(inv.NB_INITIAL_OUVRIERS, inv.getNbOuvrier());  //AU debut le nombre d'ouvriers est initialisé a 5 (dans le constructeur)
	}
	
	
	@Test public void TestAvecValeurs() {
		inv.setNbOuvrier(6);				//On définit 6 ouvriers dans l'inventaire inv
        inv.setNbRessource(2);				//On ajoute 2 ressource
        
        
        Inventaire invCopie = new Inventaire(inv);	//On definit un nouveau invCopie (copie de inv)
        
        assertEquals(2, invCopie.getNbRessource());		//On vérifie les champs initialisé dans inv
        assertEquals(6, invCopie.getNbOuvrier());
        
        inv.setNbRessource(20);							//On definit 20 ressource dans inv
        assertEquals(2, invCopie.getNbRessource());		//Le nombre de ressource ne doit pas changer dans invCopie (rester a 2)
        
        invCopie.setNbRessource(10);					//On definit 10 ressource dans invCopie
        assertEquals(20, inv.getNbRessource());			//Le nombres de ressource doit rester a 20 dans inv
	}
}

