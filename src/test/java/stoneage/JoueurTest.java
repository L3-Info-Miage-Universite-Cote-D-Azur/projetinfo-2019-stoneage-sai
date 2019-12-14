package stoneage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class JoueurTest {
    ArrayList<Zone> listeZone;
    Zone zone;
    Choix choix ,choix2;
    Joueur j;
    Dé de;
    ArrayList<Integer> listeDe;
    Inventaire inventaire;
    CarteCivilisation carteCiv=new CarteCivilisation();
    BuildingTiles carteBat=new BuildingTiles();
    ArrayList<CarteCivilisation> listeDesCivilisation=new ArrayList<>();
    ArrayList<BuildingTiles> listeDesBatiments=new ArrayList<>();
    @BeforeEach
    void setUp(){
        listeZone=new ArrayList<>() ;
        zone=new Zone(1,1,"fabrication d'outils");
        listeZone.add(zone);
        zone=new Zone(2,100,"chasse");
        listeZone.add(zone);
        zone=new Zone(3,7,"foret");
        listeZone.add(zone);
        zone=new Zone(4,7,"glaisiere");
        listeZone.add(zone);
        zone=new Zone(5,7,"carriere");
        listeZone.add(zone);
        zone=new Zone(6,7,"riviere");
        listeZone.add(zone);
        for (int i = 8; i < 12; i++) {
            zone=new Zone(i,1,"carte civilisation");
            listeZone.add(zone);
        }
        for (int i = 12; i < 16; i++) {
            zone=new Zone(i,1,"carte batiments");
            listeZone.add(zone);
        }
        zone=new Zone(16,2,"hutte");
        listeZone.add(zone);
        j = new Joueur("oss",1);
        inventaire = new Inventaire(); 
        listeDesCivilisation=carteCiv.getAllCards(); 
        listeDesBatiments=carteBat.getCards();
        /*je crée un objet avec les valeurs zoneChoisie et nbOuvriersChoisie
         *  renvoyé par j.placerOuvriers(listeZone, inventaire)par ce que 
         * leur création se fait avec un random et c'est le seul moyen de les recupérées . */
        //zone.recupeRes(listeDesCivilisation,listeDesBatiments,inventaire,j);
   
    }
    @Test
    void placerOuvrier2Joueurs() {
    	StoneAge stone=new StoneAge(2,false);
    	//tester si les joueur choisi pas les zones carte 3 ET 4 civilisation et batiment 
    	choix=j.placerOuvriers(listeZone,inventaire);
        choix2 = new Choix(choix.zoneChoisie,choix.nbOuvriersChoisie);
    	assertEquals(choix, choix2); 
	    for (int i=0;i<10;i++) {
	        choix=j.placerOuvriers(listeZone,inventaire);
			Zone zoneChoisie=listeZone.get(choix.zoneChoisie);
	        assertTrue(choix.zoneChoisie>=0); 
	        assertTrue(choix.zoneChoisie<=15); 
	        assertFalse(choix.zoneChoisie==9||choix.zoneChoisie==10);
	        assertFalse(choix.zoneChoisie==13||choix.zoneChoisie==14);
	         //verifier que la zone choisie est bien dans la liste dans zone
	        assertTrue(choix.nbOuvriersChoisie <=inventaire.getNbOuvrierDispo());  
	        assertTrue(choix.nbOuvriersChoisie <=zoneChoisie.getNbPlaceDispo());  
	        /*verifier que le nombre d'ouvrier choisie est inferieur au nombre 
	             * de place disponnible dans la zone  et qu'il est inferieur au nombre
	             *  d'ouvrier que le joueur possede */
    	}
    }

    @Test
    void placerOuvrier3Joueurs() {
    	StoneAge stone=new StoneAge(3, false);
	    for (int i=0;i<10;i++) {
	        choix=j.placerOuvriers(listeZone,inventaire); //on fait 10 choix differant 
			Zone zoneChoisie=listeZone.get(choix.zoneChoisie);
            assertTrue(choix.zoneChoisie>=0); 
            assertTrue(choix.zoneChoisie<=15);
	        assertFalse(choix.zoneChoisie==14);
            //verifier que la zone choisie est bien dans la liste dans zone
            assertTrue(choix.nbOuvriersChoisie <=inventaire.getNbOuvrierDispo());  
            assertTrue(choix.nbOuvriersChoisie <=zoneChoisie.getNbPlaceDispo());  
            /*verifier que le nombre d'ouvrier choisie est inferieur au nombre 
             * de place disponnible dans la zone  et qu'il est inferieur au nombre
             *  d'ouvrier que le joueur possede */
	    }
    }
	@Test
    void placerOuvrier4Joueurs() { // verification pour 4joueures 
		StoneAge stone=new StoneAge(4,false);
	    for (int i=0;i<10;i++) {
	        choix=j.placerOuvriers(listeZone,inventaire); //on fait 10 choix differant 
			Zone zoneChoisie=listeZone.get(choix.zoneChoisie);
            assertTrue(choix.zoneChoisie>=0); 
            assertTrue(choix.zoneChoisie<=15);
            //verifier que la zone choisie est bien dans la liste dans zone
            assertTrue(choix.nbOuvriersChoisie <=inventaire.getNbOuvrierDispo());  
            assertTrue(choix.nbOuvriersChoisie <=zoneChoisie.getNbPlaceDispo());  
            /*verifier que le nombre d'ouvrier choisie est inferieur au nombre 
             * de place disponnible dans la zone  et qu'il est inferieur au nombre
             *  d'ouvrier que le joueur possede */
	    }
    }
    @Test
    void placerOutil() {
    	//si le joueur n'a pas d'outil on teste qu'il retourne bien 0 outil a placer dans les zone qui vont lancer des dé 
    	int nbRessource=8;
    	// ici le nombre de resource n'est pas important car le joueur va faire un choix au hazard 
    	for (int z=1;z<8;z++) { 
    		int nbOutil=j.placerOutils(0, nbRessource, listeZone.get(z));
    		assertEquals(nbOutil ,0);// verifier qu'il retoune 0 car il n'a pas d'outil
    	}
    	for (int z=1;z<8;z++) {
    		int nbOutil=j.placerOutils(10, nbRessource, listeZone.get(z));
    		assertTrue(nbOutil>=0 && nbOutil<=10); // verifier qu'il retoune un nombre inferieur ou egale a 10 car il a 10 outils
    	}
    }

    @Test
    void cadeauRes() {
    	int cadeau;
    	for(int i=0 ;i<10;i++) {
    		listeDe=listeZone.get(0).lancerNbDé(4);
    		cadeau=j.cadeauRes(listeDe);
    		assertTrue(listeDe.contains(cadeau)); // verifier qu'il choisit un dé dans la liste 
    		listeDe=listeZone.get(0).lancerNbDé(3);
    		cadeau=j.cadeauRes(listeDe);
    		assertTrue(listeDe.contains(cadeau));
    		listeDe=listeZone.get(0).lancerNbDé(2);
    		cadeau=j.cadeauRes(listeDe);
    		assertTrue(listeDe.contains(cadeau));
    		listeDe=listeZone.get(0).lancerNbDé(1);
    		cadeau=j.cadeauRes(listeDe);
    		assertTrue(listeDe.contains(cadeau));
    	}
    }
    @Test
    void choixTypeResSiPasDeRes() { //verifier qu'il ne prend pas la carte s'il a pas de ressource suffisante
    	int payement = j.choixTypeRes(1,inventaire,3,4,5,6);
    	assertEquals(payement, -1);
        inventaire.lesRessources.put(3,new Ressources(3,"Bois",3));
    	payement = j.choixTypeRes(4,inventaire,3,4,5,6);
    	assertEquals(payement, -1);
    }
    @Test
    void choixTypeResDispo() { //verifier qu'ilprend la carte s'il a  des ressource suffisante et s'il a envie
        inventaire.lesRessources.put(3,new Ressources(3,"Bois",4));
    	int payement = j.choixTypeRes(3,inventaire,3,4,5,6);   	
    	assertTrue((payement>2&& payement<7)||payement==-1);
    }
    
    @Test
    void NourrirOuvSiResSuffisante() {
    	// Si on nourri avec nourriture 
		inventaire.setNourriture(5);
		Map<String, Integer> choixNourriture = j.NourrirOuv(inventaire,5);
		Set<String> keys=choixNourriture.keySet();
		assertFalse(keys.contains("Point de Score") );
		assertTrue(keys.contains("Nourriture") );
		assertEquals(choixNourriture.get("Nourriture") ,5);
    	
    	// Si on nourri ressource
		inventaire.setNourriture(0);
                inventaire.lesRessources.put(6,new Ressources(6,"Or",2));
                inventaire.lesRessources.put(3,new Ressources(3,"Bois",2));
                inventaire.lesRessources.put(4,new Ressources(4,"Argile",1));
                inventaire.lesRessources.put(5,new Ressources(5,"Pierre",1));
		choixNourriture = j.NourrirOuv(inventaire,5); // si assez de nourriture 
		keys=choixNourriture.keySet();
		assertFalse(keys.contains("Point de Score") );
		assertTrue(keys.contains("Or") );
		assertTrue(keys.contains("Bois") );
		assertTrue(keys.contains("Pierre") );
		assertTrue(keys.contains("Argile") );
    	
    }
    @Test
    void NourrirOuvSiResInsuffisante() {
                inventaire.lesRessources.put(5,new Ressources(5,"Pierre",1));
		inventaire.setNourriture(0);
		Map<String, Integer> choixNourriture = j.NourrirOuv(inventaire,5);
		Set<String> keys=choixNourriture.keySet();
		assertTrue(keys.contains("Point de Score"));
		assertFalse(keys.contains("Pierre"));
		assertEquals(choixNourriture.get("Point de Score") ,10);
    }
}
