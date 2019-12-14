package stoneage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class JoueurIATest {
    ArrayList<Zone> listeZone = new ArrayList<Zone>();
    Zone zone;
    Choix choix ,choix2;
    JoueurIA joueurIA;
    Inventaire inventaire;
    CarteCivilisation carte;
    ArrayList<CarteCivilisation> listeDesCartes;
    StoneAge stoneage2,stoneage3,stoneage4;
    ArrayList<Integer> listeDe,listeDe2,listeDe3,listeDe4,listeDe5,listeDe6;
    Map<String, Integer> m ;
            
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
        joueurIA = new JoueurIA("o",1);
        inventaire = new Inventaire();
        for (int i = 1; i <= 15; i++) {
            inventaire.listeZonesJouer.add(false);
        }
        stoneage2=new StoneAge(2,true);
        stoneage3=new StoneAge(3,true);
        stoneage4=new StoneAge(4,true);
        listeDe=new ArrayList<Integer>();
        listeDe.add(4);
        listeDe2=new ArrayList<Integer>();
        listeDe2.add(6);
        listeDe3=new ArrayList<Integer>();
        listeDe3.add(5);
        listeDe4=new ArrayList<Integer>();
        listeDe4.add(3);
        listeDe5=new ArrayList<Integer>();
        listeDe5.add(2);
        listeDe6=new ArrayList<Integer>();
        listeDe6.add(100);
        
        m = new HashMap<>();
    }

    @Test
    void placerOutilSiPasOutil() {
    	//si le joueur n'a pas d'outil on teste qu'il retourne bien 0 outil a placer dans les zone qui vont lancer des dé 
    	int nbRessource=8;
    	for (int z=1;z<8;z++) {
    		int nbOutil=joueurIA.placerOutils(0, nbRessource, listeZone.get(z));
    		assertEquals(nbOutil ,0);
    	}
    }
    
    @Test
    void placerOutilSiYaOutil() {
    	//si le joueur a un nombre de ressource modulo zone pas loin du nombre d'outil on retourne le nombre d'outil manquant a placer dans les zone qui vont lancer des dé 
    		int nbOutil=joueurIA.placerOutils(1, 8, listeZone.get(2));//zone foret n°3 : donc 8%3=2 , nbOutil doit etre egale a 1 
    		assertEquals(nbOutil ,1);
    		nbOutil=joueurIA.placerOutils(4, 8, listeZone.get(2));//zone foret n°3 : donc 8%3=2 , nbOutil doit etre egale a 2 cal le joueur a assez d'outil
    		assertEquals(nbOutil ,4);
    		nbOutil=joueurIA.placerOutils(1, 8, listeZone.get(3));//zone Glaissiere n°4 : donc 8%4=0 , nbOutil doit etre egale a 0
    		assertEquals(nbOutil ,0);
    		nbOutil=joueurIA.placerOutils(4, 8, listeZone.get(3));//zone Glaissiere n°4 : donc 8%4=0 , nbOutil doit etre egale a 1 car on a 4 outil dispo
    		assertEquals(nbOutil ,4);
    	
    }

    
   @Test
   void cadeauRes(){
       int a=joueurIA.cadeauRes(listeDe);
       int b=joueurIA.cadeauRes(listeDe2);
       int c=joueurIA.cadeauRes(listeDe3);
       int d=joueurIA.cadeauRes(listeDe4);
       int e=joueurIA.cadeauRes(listeDe5);
       int f=joueurIA.cadeauRes(listeDe6);
       assertEquals(4,a);
       assertEquals(6,b);
       assertEquals(5,c);
       assertEquals(3,d);
       assertEquals(2,e);
       assertEquals(1,f);
   }
    
   @Test
   void choixTypeRes(){
       int a,b,c,d,e;
       inventaire.lesRessources.put(3,new Ressources(3,"Bois",7));//>=5
       a=joueurIA.choixTypeRes(5,inventaire,3,4,5,6);
       assertEquals(3,a);
       
      
       inventaire.lesRessources.put(4,new Ressources(4,"Argile",7));
       b=joueurIA.choixTypeRes(5,inventaire,4,5,6);
       assertEquals(4,b);
       
       
       inventaire.lesRessources.put(5,new Ressources(5,"Pierre",7));
       c=joueurIA.choixTypeRes(5,inventaire,5,6);
       assertEquals(5,c);
       
      
       inventaire.lesRessources.put(6,new Ressources(6,"Or",7));
       d=joueurIA.choixTypeRes(5,inventaire,6);
       assertEquals(6,d);
       
       
       inventaire.lesRessources.put(3,new Ressources(3,"Bois",7));
       e=joueurIA.choixTypeRes(5,inventaire,10);
       assertEquals(-1,e);
   }
   
    @Test
    void placerOuvrier() {//si il y a 4 joueurs
        inventaire.setNourriture(4);
        choix=new Choix(1,3);
        choix2=joueurIA.placerOuvriers(listeZone,inventaire);

        inventaire.setNbOuvrier(2);
        inventaire.resetAvailableWorkers();
        assertEquals(choix,choix2);
        inventaire.listeZonesJouer.set(1,true);
        inventaire.listeZonesJouer.set(6,true);
        
        //test placerOuvrier4joueurs
        choix=joueurIA.placerOuvriers(listeZone,inventaire);
        choix2 = new Choix(choix.zoneChoisie,choix.nbOuvriersChoisie);
        assertEquals(choix, choix2); 
        for (int i=0;i<10;i++) {
            choix=joueurIA.placerOuvriers(listeZone,inventaire);
            Zone zoneChoisie=listeZone.get(choix.zoneChoisie);
            assertTrue(choix.zoneChoisie>=0); 
            assertTrue(choix.zoneChoisie<=14);
             //verifier que la zone choisie est bien dans la liste dans zone
            assertTrue(choix.nbOuvriersChoisie <=inventaire.getNbOuvrierDispo());
            assertTrue(choix.nbOuvriersChoisie <=zoneChoisie.getNbPlaceDispo());
        }  
    }
    
    @Test
    void placerOuvrier2Joueurs() {
        //tester si les joueur choisi pas les zones carte 3 ET 4 civilisation et batiment 
        choix=joueurIA.placerOuvriers(listeZone,inventaire);
        choix2 = new Choix(choix.zoneChoisie,choix.nbOuvriersChoisie);
        StoneAge stone=new StoneAge(2,true);
        assertEquals(choix, choix2); 
        for (int i=0;i<10;i++) {
            choix=joueurIA.placerOuvriers(listeZone,inventaire);
            Zone zoneChoisie=listeZone.get(choix.zoneChoisie);
            assertTrue(choix.zoneChoisie>=0); 
            assertTrue(choix.zoneChoisie<=12);
            assertFalse(choix.zoneChoisie==9||choix.zoneChoisie==10);
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
        StoneAge stone=new StoneAge(3,true);
        for (int i=0;i<10;i++) {
            choix=joueurIA.placerOuvriers(listeZone,inventaire); //on fait 10 choix differant
            Zone zoneChoisie=listeZone.get(choix.zoneChoisie);
            assertTrue(choix.zoneChoisie>=0);
            assertTrue(choix.zoneChoisie<=13);
            assertFalse(choix.zoneChoisie==10);
            //verifier que la zone choisie est bien dans la liste dans zone
            assertTrue(choix.nbOuvriersChoisie <=inventaire.getNbOuvrierDispo()); 
            assertTrue(choix.nbOuvriersChoisie <=zoneChoisie.getNbPlaceDispo()); 
            /*verifier que le nombre d'ouvrier choisie est inferieur au nombre
        * de place disponnible dans la zone  et qu'il est inferieur au nombre
        *  d'ouvrier que le joueur possede */        
        }     
    }
    
    @Test 
    void NourrirOuv(){
        //inventaire.setNbRessource(5);
        inventaire.lesRessources.put(3,new Ressources(3,"Bois",5));
        inventaire.setNourriture(4);
        Map<String, Integer> nourrir=joueurIA.NourrirOuv(inventaire,10);
        m.put("Point de Score", 10);
        assertEquals(nourrir,m);
        
        //inventaire.setNbRessource(6);
        inventaire.lesRessources.put(3,new Ressources(3,"Bois",6));
        inventaire.setNourriture(5);
        Map<String, Integer> nour=joueurIA.NourrirOuv(inventaire,10);
        m=new HashMap<>();
        m.put("Nourriture", inventaire.getNourriture());
        assertEquals(nour,m);
        
        inventaire.lesRessources.put(3,new Ressources(3,"Bois",5));
        inventaire.lesRessources.put(4,new Ressources(4,"Argile",5));
        inventaire.lesRessources.put(5,new Ressources(5,"Pierre",5));
        Map<String, Integer> nouro=joueurIA.NourrirOuv(inventaire,10);
        m.put("Bois",5);
        m.put("Argile",5);
        assertEquals(nouro,m);
    }
}