package stoneage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class PlateauTest {
	Plateau plateau; 
	public ArrayList<Zone> lesZones;
	
	@BeforeEach
	void setup() {
		plateau = new Plateau();
	}
	
	@Test
	void addAllTest() { // on test si toutes les zones se sont bien ajoutées dans le plateau
		plateau.addAllZones();
		assertEquals(plateau.lesZones.get(0).toString(),"Fabrication d'Outils");
		assertEquals(plateau.lesZones.get(2).toString(),"foret");
		assertEquals(plateau.lesZones.get(3).toString(),"glaisière");
		assertEquals(plateau.lesZones.get(4).toString(),"carrière");
		assertEquals(plateau.lesZones.get(6).toString(),"champ");
		assertEquals(plateau.lesZones.get(7).toString(),"Civilisation 1");
		assertEquals(plateau.lesZones.get(8).toString(),"Civilisation 2");
		assertEquals(plateau.lesZones.get(9).toString(),"Civilisation 3");
		assertEquals(plateau.lesZones.get(10).toString(),"Civilisation 4");
		assertEquals(plateau.lesZones.get(11).toString(),"Batiment 1");
		assertEquals(plateau.lesZones.get(12).toString(),"Batiment 2");
		assertEquals(plateau.lesZones.get(13).toString(),"Batiment 3");
		assertEquals(plateau.lesZones.get(14).toString(),"Batiment 4");
		assertEquals(plateau.lesZones.get(15).toString(),"Hutte");
		
	}
}
