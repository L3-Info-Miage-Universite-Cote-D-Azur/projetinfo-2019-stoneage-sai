package stoneage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
		assertEquals(tiles.getCards().size(), 17);
	}

	
	@Test
    public void shuffle() {
		tiles.shuffle(tiles.getCards());
		assertEquals(tiles.getCards().size(), 17);
	}
	
	@Test
	void ajouterDansInventaire() {
		assertEquals(inv.getCarteBat().size(), 0);
		for (int i = 0; i < tiles.getCards().size(); i++) {
			tiles.getCards().get(i).ajouterDansInventaire(inv);
		}
		assertEquals(inv.getCarteBat().size(), 17);
	}
	
	
	@Test
	void getCardScore() {
		for (int i = 0; i < tiles.getCards().size(); i++) {
			assertTrue(tiles.getCards().get(i).getCardScore() >= 0 && tiles.getCards().get(i).getCardScore() <= 6);
		}
	}
	
	
	@Test
	void getBuildingCost() {
		for (int i = 0; i < tiles.getCards().size(); i++) {
			assertTrue(tiles.getCards().get(i).getBuildingCost() >= 0 && tiles.getCards().get(i).getBuildingCost() <= 12);
		}
	}
}
