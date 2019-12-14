 package stoneage;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarteCivilisationTest {
	
	
	private Inventaire inv;
    private CarteCivilisation carte,carte3,carte5,carte7,carte10,carte11,
    carte14,carte15,carte16,carte17,carte18,carte19,carte21,carte23,carte24,
    carte25,carte28,carte29,carte30,carte31,carte34,carte35,carte37,carte38;
    private Plateau plateau;
    private Joueurs joueur;
    private Joueurs joueurIA;
    private Joueurs joueurBOT;
    private Partie partie;
    
    
    @BeforeEach
    void setUp(){
    	plateau= new Plateau();
     	plateau.addAllZones();
        inv = new Inventaire();
        carte = new CarteCivilisation();
        Joueurs joueur=new Joueur("1",1);
        Joueurs joueurIA=new JoueurIA("2",2);
        Joueurs joueurBOT=new JoueurBot2("3",3);
        carte3=new CarteCivilisation(0,0);
        carte5=new CarteCivilisation(0,2);
        carte7=new CarteCivilisation(0,4); //Ex: vert, carte n°4-> Ecriture, pioche carteCiv.
        carte10=(new CarteCivilisation(0,7));
        carte11=(new CarteCivilisation(0,8));
        carte14=(new CarteCivilisation(0,11));
        carte15=(new CarteCivilisation(0,12));
        carte16=(new CarteCivilisation(0,13));
        carte17=(new CarteCivilisation(0,14));
        carte18=(new CarteCivilisation(0,15));
        //  FOND JAUNE
        carte19=(new CarteCivilisation(1,16)); //Ex: jaune, carte n°16-> hutte, nourriture, 1ouvrier.
        carte21=(new CarteCivilisation(1,18));
        carte23=(new CarteCivilisation(1,20));
        carte24=(new CarteCivilisation(1,21));
        carte25=(new CarteCivilisation(1,22));
        carte28=(new CarteCivilisation(1,25));
        carte29=(new CarteCivilisation(1,26));
        carte30=(new CarteCivilisation(1,27));
        carte31=(new CarteCivilisation(1,28));
        carte34=(new CarteCivilisation(1,30));
        carte35=(new CarteCivilisation(1,31));
        carte37=(new CarteCivilisation(1,32));
        carte38=(new CarteCivilisation(1,34));

    }
    
    
    @Test
    public void addCardsToList() {
        assertEquals(carte.getAllCards().size(), 36);
    }
    
    
    @Test
    public void getFondDeCarte() {
        for (int i = 0; i < carte.getAllCards().size(); i++) {
            if (i < 16) {
                assertEquals(carte.getAllCards().get(i).getFondDeCarte(), 0);
            }
            else {
                assertEquals(carte.getAllCards().get(i).getFondDeCarte(), 1);
            }
        }
    }
    
    
    @Test
    public void getNumeroCarte() {
        for (int i = 0; i < carte.getAllCards().size(); i++) {
            assertEquals(carte.getAllCards().get(i).getNumeroCarte(), i);
        }
    }
    @Test
    public void recupResCarteCiv0() {
    	inv = new Inventaire();
    	assertEquals(carte3.getNumeroCarte() , 0);
    	carte3.recupResCarteCiv(inv,plateau.lesZones.get(0) ,joueur);
    	assertEquals(inv.getNourriture(),7+12);
    	assertEquals(plateau.lesZones.get(0).getGains(),7);
    }
    @Test
    public void recupResCarteCiv2() {
    	inv = new Inventaire();
    	assertEquals(carte5.getNumeroCarte() , 2);
    	carte5.recupResCarteCiv(inv,plateau.lesZones.get(1) ,joueur);
    	assertEquals(inv.getNbOutils(),1);
    	assertEquals(plateau.lesZones.get(1).getGains(),1);
    }

	@Test
	public void recupResCarteCiv4() {
		inv = new Inventaire();
		assertEquals(carte7.getNumeroCarte() , 4);
		carte7.recupResCarteCiv(inv,plateau.lesZones.get(1) ,joueur);
		assertEquals(inv.getNbCarteVert(),1);
	}
	@Test
	public void recupResCarteCiv7() {
		inv = new Inventaire();
		assertEquals(carte10.getNumeroCarte() , 7);
		carte10.recupResCarteCiv(inv,plateau.lesZones.get(2) ,joueur);
		assertEquals(inv.getNbCarteVert(),1);
		assertEquals(inv.getNourriture(),12+5);
    	assertEquals(plateau.lesZones.get(2).getGains(),5);

	}
	@Test
	public void recupResCarteCiv8() {
		inv = new Inventaire();
		assertEquals(carte11.getNumeroCarte() , 8);
		carte11.recupResCarteCiv(inv,plateau.lesZones.get(2) ,joueur);
		assertEquals(inv.getNbCarteVert(),1);
		assertEquals(inv.getScoreChamp(),1);
    	assertEquals(plateau.lesZones.get(2).getGains(),1);

	}
	@Test
	public void recupResCarteCiv11() {
		inv = new Inventaire();
		assertEquals(carte14.getNumeroCarte() , 11);
		carte14.recupResCarteCiv(inv,plateau.lesZones.get(2) ,joueur);
		assertEquals(inv.getNbCarteVert(),1);
		assertEquals(inv.getNbPierre(),2);
    	assertEquals(plateau.lesZones.get(2).getGains(),2);

	}
	@Test
	public void recupResCarteCiv12() {
		inv = new Inventaire();
		assertEquals(carte15.getNumeroCarte() , 12);
		carte15.recupResCarteCiv(inv,plateau.lesZones.get(2) ,joueur);
		assertEquals(inv.getNbCarteVert(),1);
		assertEquals(inv.getScore(),3); 
    	assertEquals(plateau.lesZones.get(2).getGains(),3);

	}
	@Test
	public void recupResCarteCiv13() {
		inv = new Inventaire();
		assertEquals(carte16.getNumeroCarte() , 13);
		carte16.recupResCarteCiv(inv,plateau.lesZones.get(2) ,joueur);
		assertEquals(inv.getNbCarteVert(),1);
		assertEquals(inv.getScore(),3); 
    	assertEquals(plateau.lesZones.get(2).getGains(),3);

	}
	@Test
	public void recupResCarteCiv14() {
		inv = new Inventaire();
		assertEquals(carte17.getNumeroCarte() , 14);
		carte17.recupResCarteCiv(inv,plateau.lesZones.get(2) ,joueur);
		assertEquals(inv.getNbCarteVert(),1);
		assertEquals(inv.getNourriture(),3+12); 
    	assertEquals(plateau.lesZones.get(2).getGains(),3);

	}
	@Test
	public void recupResCarteCiv15() {
		inv = new Inventaire();
		assertEquals(carte18.getNumeroCarte() , 15);
		carte18.recupResCarteCiv(inv,plateau.lesZones.get(2) ,joueur);
		assertEquals(inv.getNbCarteVert(),1);
		assertEquals(inv.getNourriture(),1+12); 
    	assertEquals(plateau.lesZones.get(2).getGains(),1);

	}
	@Test
	public void recupResCarteCiv16() {
		inv = new Inventaire();
		assertEquals(carte19.getNumeroCarte() , 16);
		carte19.recupResCarteCiv(inv,plateau.lesZones.get(2) ,joueur);
		assertEquals(inv.getNbConstructeur(),1);
		assertEquals(inv.getNourriture(),4+12); 
    	assertEquals(plateau.lesZones.get(2).getGains(),4);

	}
	@Test
	public void recupResCarteCiv18() {
		inv = new Inventaire();
		assertEquals(carte21.getNumeroCarte() , 18);
		carte21.recupResCarteCiv(inv,plateau.lesZones.get(2) ,joueur);
		assertEquals(inv.getNbConstructeur(),2);
		assertEquals(inv.getNourriture(),2+12); 
    	assertEquals(plateau.lesZones.get(2).getGains(),2);

	}
	@Test
	public void recupResCarteCiv20() {
		inv = new Inventaire();
		assertEquals(carte23.getNumeroCarte() , 20);
		carte23.recupResCarteCiv(inv,plateau.lesZones.get(2) ,joueur);
		assertEquals(inv.getNbConstructeur(),3);
		assertEquals(inv.getScore(),3); 
    	assertEquals(plateau.lesZones.get(2).getGains(),3);

	}
	@Test
	public void recupResCarteCiv21() {
		inv = new Inventaire();
		assertEquals(carte24.getNumeroCarte() , 21);
		carte24.recupResCarteCiv(inv,plateau.lesZones.get(2) ,joueur);
		assertEquals(inv.getNbPierre(),	1);
		assertEquals(inv.getNbPaysan(),1); 
    	assertEquals(plateau.lesZones.get(2).getGains(),1);

	}
	@Test
	public void recupResCarteCiv22() {
		inv = new Inventaire();
		assertEquals(carte25.getNumeroCarte() , 22);
		carte25.recupResCarteCiv(inv,plateau.lesZones.get(2) ,joueur);
		assertEquals(inv.getNbPaysan(),1); 
		assertEquals(inv.getScoreChamp(),1); 
    	assertEquals(plateau.lesZones.get(2).getGains(),1);

	}
	@Test
	public void recupResCarteCiv25() {
		inv = new Inventaire();
		assertEquals(carte28.getNumeroCarte() , 25);
		carte28.recupResCarteCiv(inv,plateau.lesZones.get(2) ,joueur);
		assertEquals(inv.getNbPaysan(),2); 
		assertEquals(inv.getNourriture(),3+12); 
    	assertEquals(plateau.lesZones.get(2).getGains(),3);

	}
	@Test
	public void recupResCarteCiv26() {
		inv = new Inventaire();
		assertEquals(carte29.getNumeroCarte() , 26);
		carte29.recupResCarteCiv(inv,plateau.lesZones.get(2) ,joueur);
		assertEquals(inv.getNbFabricant(),1); 
		assertEquals(inv.getNbOutils(),3); 
    	assertEquals(plateau.lesZones.get(2).getGains(),3);

	}
	
	@Test
	public void recupResCarteCiv27() {
		inv = new Inventaire();
		assertEquals(carte30.getNumeroCarte() , 27);
		carte30.recupResCarteCiv(inv,plateau.lesZones.get(2) ,joueur);
		assertEquals(inv.getNbFabricant(),1); 
		assertEquals(inv.getNbOutils(),4); 
    	assertEquals(plateau.lesZones.get(2).getGains(),4);

	}
	@Test
	public void recupResCarteCiv28() {
		inv = new Inventaire();
		assertEquals(carte31.getNumeroCarte() , 28);
		carte31.recupResCarteCiv(inv,plateau.lesZones.get(2) ,joueur);
		assertEquals(inv.getNbFabricant(),2); 
		assertEquals(inv.getNbOutils(),2); 
    	assertEquals(plateau.lesZones.get(2).getGains(),2);

	}
	@Test
	public void recupResCarteCiv31() {
		inv = new Inventaire();
		assertEquals(carte35.getNumeroCarte() , 31);
		carte35.recupResCarteCiv(inv,plateau.lesZones.get(2) ,joueur);
		assertEquals(inv.getNbChamane(),1); 
		assertEquals(inv.getNbPierre(),1); 
    	assertEquals(plateau.lesZones.get(2).getGains(),1);

	}
	@Test
	public void recupResCarteCiv32() {
		inv = new Inventaire();
		assertEquals(carte37.getNumeroCarte() , 32);
		carte37.recupResCarteCiv(inv,plateau.lesZones.get(2) ,joueur);
		assertEquals(inv.getNbChamane(),1); 
		assertEquals(inv.getNbOr(),1); 
    	assertEquals(plateau.lesZones.get(2).getGains(),1);

	}
	
	@Test
	public void recupResCarteCiv34() {
		inv = new Inventaire();
		assertEquals(carte38.getNumeroCarte() , 34);
		carte38.recupResCarteCiv(inv,plateau.lesZones.get(2) ,joueur);
		assertEquals(inv.getNbChamane(),2); 
		assertEquals(inv.getNbArgile(),1); 
    	assertEquals(plateau.lesZones.get(2).getGains(),1);

	}

}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

