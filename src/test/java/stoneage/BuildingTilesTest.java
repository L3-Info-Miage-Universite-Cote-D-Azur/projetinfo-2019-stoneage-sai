package stoneage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BuildingTilesTest {
	
	private BuildingTiles tiles;
	private Inventaire inv;
	
	
	@BeforeEach
    void setUp(){
		tiles =  new BuildingTiles();
		inv = new Inventaire();
	}
	
	
	@Test
    public void addCards() {
		assertEquals(tiles.getCards().size(), 28);
	}
	
	@Test
	void ajouterDansInventaire() {
		assertEquals(inv.getCarteBat().size(), 0);
		for (int i = 0; i < tiles.getCards().size(); i++) {
			tiles.getCards().get(i).ajouterDansInventaire(inv);
		}
		assertEquals(inv.getCarteBat().size(), 28);
	}
	
	
	@Test
	void getCardScore() {
		for (int i = 0; i < tiles.getCards().size(); i++) {
			assertTrue(tiles.getCards().get(i).getCardScore() >= 0 && tiles.getCards().get(i).getCardScore() <= 7);
		}
	}
	
	
	@Test
	void getBuildingCost() {
		for (int i = 0; i < tiles.getCards().size(); i++) {
			assertTrue(tiles.getCards().get(i).getBuildingCost() >= 0 && tiles.getCards().get(i).getBuildingCost() <= 21);
		}
	}
}
