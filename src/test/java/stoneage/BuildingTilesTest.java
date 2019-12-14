package stoneage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BuildingTilesTest {
	
	private BuildingTiles tiles,tiles2,tiles3,tiles4,tiles5,tiles6,tiles7,tiles8,tiles9;
	private Inventaire inv;
	Zone zone;
        Joueurs J;
	
	@BeforeEach
    void setUp(){
		tiles =  new BuildingTiles();
                tiles2 =  new BuildingTiles(0,0);
                tiles3 =  new BuildingTiles(1,1);
                tiles4 =  new BuildingTiles(2,3);
                tiles5 =  new BuildingTiles(3,5);
                tiles6 =  new BuildingTiles(4,8);
                tiles7 =  new BuildingTiles(5,11);
                tiles8 =  new BuildingTiles(6,12);
                tiles9 =  new BuildingTiles(7,13);
                zone=new Zone(1,1,"fabrication d'outils");
                J=new JoueurIA("O",1);
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
        
        @Test
        void recupResCarteBatCardScore0(){
            int s =inv.getScore();
            inv.lesRessources.put(3,new Ressources(3,"Bois",2));
            inv.lesRessources.put(4,new Ressources(4,"Argile",1));
            int b=inv.getNbBois();
            int a=inv.getNbArgile();
            int g=zone.getGains();
            tiles2.recupResCarteBat(inv,zone,J);
            assertEquals(inv.getScore(),s+10);
            assertEquals(inv.getNbBois(),b-2);
            assertEquals(inv.getNbArgile(),a-1);
            assertEquals(zone.getGains(),g+10);
            assertEquals(zone.getTypeGains()," points sur la piste score avec la carte Batiment ");
        }
        
        @Test
        void recupResCarteBatCardScore1(){
            int s =inv.getScore();
            inv.lesRessources.put(3,new Ressources(3,"Bois",1));
            inv.lesRessources.put(4,new Ressources(4,"Argile",2));
            int b=inv.getNbBois();
            int a=inv.getNbArgile();
            int g=zone.getGains();
            tiles3.recupResCarteBat(inv,zone,J);
            assertEquals(inv.getScore(),s+11);
            assertEquals(inv.getNbBois(),b-1);
            assertEquals(inv.getNbArgile(),a-2);
            assertEquals(zone.getGains(),g+11);
            assertEquals(zone.getTypeGains()," points sur la piste score avec la carte Batiment ");
        }
        
        @Test
        void recupResCarteBatCardScore2(){
            int s =inv.getScore();
            inv.lesRessources.put(3,new Ressources(3,"Bois",1));
            inv.lesRessources.put(4,new Ressources(4,"Argile",1));
            inv.lesRessources.put(5,new Ressources(5,"Pierre",1));
            int b=inv.getNbBois();
            int a=inv.getNbArgile();
            int p=inv.getNbPierre();
            int g=zone.getGains();
            tiles4.recupResCarteBat(inv,zone,J);
            assertEquals(inv.getScore(),s+12);
            assertEquals(inv.getNbBois(),b-1);
            assertEquals(inv.getNbArgile(),a-1);
            assertEquals(inv.getNbPierre(),p-1);
            assertEquals(zone.getGains(),g+12);
            assertEquals(zone.getTypeGains()," points sur la piste score avec la carte Batiment ");
        }
        
        @Test
        void recupResCarteBatCardScore3(){
            int s =inv.getScore();
            inv.lesRessources.put(3,new Ressources(3,"Bois",1));
            inv.lesRessources.put(4,new Ressources(4,"Argile",1));
            inv.lesRessources.put(6,new Ressources(5,"Or",1));
            int b=inv.getNbBois();
            int a=inv.getNbArgile();
            int o=inv.getNbOr();
            int g=zone.getGains();
            tiles5.recupResCarteBat(inv,zone,J);
            assertEquals(inv.getScore(),s+13);
            assertEquals(inv.getNbBois(),b-1);
            assertEquals(inv.getNbArgile(),a-1);
            assertEquals(inv.getNbOr(),o-1);
            assertEquals(zone.getGains(),g+13);
            assertEquals(zone.getTypeGains()," points sur la piste score avec la carte Batiment ");
        }
        
        @Test
        void recupResCarteBatCardScore4(){
            int s =inv.getScore();
            inv.lesRessources.put(3,new Ressources(3,"Bois",1));
            inv.lesRessources.put(5,new Ressources(5,"Pierre",1));
            inv.lesRessources.put(6,new Ressources(6,"Or",1));
            int b=inv.getNbBois();
            int p=inv.getNbPierre();
            int o=inv.getNbOr();
            int g=zone.getGains();
            tiles6.recupResCarteBat(inv,zone,J);
            assertEquals(inv.getScore(),s+14);
            assertEquals(inv.getNbBois(),b-1);
            assertEquals(inv.getNbPierre(),p-1);
            assertEquals(inv.getNbOr(),o-1);
            assertEquals(zone.getGains(),g+14);
            assertEquals(zone.getTypeGains()," points sur la piste score avec la carte Batiment ");
        }
        
        @Test
        void recupResCarteBatCardScore5(){
            int s =inv.getScore();
            inv.lesRessources.put(4,new Ressources(3,"Argile",1));
            inv.lesRessources.put(5,new Ressources(5,"Pierre",1));
            inv.lesRessources.put(6,new Ressources(6,"Or",1));
            int a=inv.getNbArgile();
            int p=inv.getNbPierre();
            int o=inv.getNbOr();
            int g=zone.getGains();
            tiles7.recupResCarteBat(inv,zone,J);
            assertEquals(inv.getScore(),s+15);
            assertEquals(inv.getNbArgile(),a-1);
            assertEquals(inv.getNbPierre(),p-1);
            assertEquals(inv.getNbOr(),o-1);
            assertEquals(zone.getGains(),g+15);
            assertEquals(zone.getTypeGains()," points sur la piste score avec la carte Batiment ");
        }
        
        @Test
        void recupResCarteBatCardScore6(){
            inv.lesRessources.put(5,new Ressources(5,"Pierre",2));
            inv.lesRessources.put(6,new Ressources(6,"Or",1));
            int p=inv.getNbPierre();
            int o=inv.getNbOr();
            int s =inv.getScore();
            int g=zone.getGains();
            tiles8.recupResCarteBat(inv,zone,J);
            assertEquals(inv.getScore(),s+16);
            assertEquals(inv.getNbPierre(),p-2);
            assertEquals(inv.getNbOr(),o-1);
            assertEquals(zone.getGains(),g+16);
            assertEquals(zone.getTypeGains()," points sur la piste score avec la carte Batiment ");
        }
        
        @Test
        void recupResCarteBatCardScore7(){
            inv.lesRessources.put(6,new Ressources(6,"Or",2));
            inv.lesRessources.put(5,new Ressources(5,"Pierre",1));
            inv.lesRessources.put(4,new Ressources(4,"Argile",1));
            inv.lesRessources.put(3,new Ressources(3,"Bois",1));
            int o=inv.getNbOr();
            int p=inv.getNbPierre();
            int a=inv.getNbArgile();
            int b=inv.getNbBois();
            int s =inv.getScore();
            int g=zone.getGains();
            tiles9.recupResCarteBat(inv,zone,J);
            assertEquals(inv.getScore(),s+o*6 + p*5 + a*4 + b*3);
            assertEquals(inv.getNbOr(),o-2);
            assertEquals(inv.getNbPierre(),p-1);
            assertEquals(inv.getNbArgile(),a-1);
            assertEquals(inv.getNbBois(),b-1);
            assertEquals(zone.getGains(),g + o*6 + p*5 + a*4 + b*3);
        }
}
