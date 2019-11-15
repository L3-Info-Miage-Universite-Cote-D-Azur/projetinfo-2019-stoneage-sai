package stoneage;
import java.util.ArrayList;
import java.util.Random;

public class Joueur implements Joueurs {
	public int gains;
	public String TypeGains;



	public int getGains(){
		return gains;
	}
	public String TypeGains(){
		return  TypeGains;
		
	}
	Random rand = new Random();


    public void recupeRes(Inventaire inventaireJoueur, Zone zoneChoisi) { 
    	int nbRessources= zoneChoisi.lancéDeDés(zoneChoisi.getNbOuvriersPlaces());
    	int OutilChoisie = rand.nextInt(inventaireJoueur.getNbOutils()+1); 
		
    	nbRessources=nbRessources +OutilChoisie;
    	nbRessources=nbRessources / zoneChoisi.niveauZone;
    	inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils()-OutilChoisie);	   	   		
    	//recuperer les ressources gagner
    	//ajouter les nouveau ressources a l'inventaire du joueur 
    	switch(zoneChoisi.niveauZone)
    	{
    		case 1:
    			inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils()+1);

    			gains=1;
    			TypeGains="Outils";
    			break;
    		case 2:
    			inventaireJoueur.setNourriture(inventaireJoueur.getNourriture()+nbRessources);

    			gains=nbRessources;
    			TypeGains="Nourriture";
    			break;
    		case 3:
    			inventaireJoueur.setNbBois(inventaireJoueur.getNbBois()+nbRessources);
    			inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);

    			gains=nbRessources;
    			TypeGains="Bois";
    	    	
    			break;
    		case 4: 
    			inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile()+nbRessources);
    			inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);

    			gains=nbRessources;
    			TypeGains="Argile";
    	    	
    			break;
    		case 5:
    			inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre()+nbRessources);
    			inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);

    			gains=nbRessources;
    			TypeGains="Pierre";
    	    	
    			break;

    		case 6:
    			inventaireJoueur.setNbOr(inventaireJoueur.getNbOr()+nbRessources);
    			inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);

    			gains=nbRessources;
    			TypeGains="Or";
    	    	
    			break;
    		default:
    			break;			
    	}
        inventaireJoueur.addAvailableWorkers(zoneChoisi.getNbOuvriersPlaces());
        //recuperer les ouvriers 
        zoneChoisi.resetNbOuvriersPlaces();
        zoneChoisi.setNbPlaceDispo(zoneChoisi.getNbPlaceZone());//quand on recupere les ouvriers,toutes les places deviennent disponibles.    
    }
    
        @Override
	public Choix placerOuvriers(Inventaire inv){
		if ((inv.listeZonesDispo != null) && (inv.listeZonesDispo.size() > 0))
			{
			 	int zoneChoisie = rand.nextInt(inv.listeZonesDispo.size()); 
				//IA simple qui choisit une zone au hazard
				int nbOuvChoisie= rand.nextInt(Math.min(inv.getNbOuvrierDispo(),(inv.listeZonesDispo.get(zoneChoisie).getNbPlaceZone()-1)))+1;
				//IA simple qui choisit nombre d'ouvrier qu'elle va poser sur cette zone au hazard 
				//le nombre doit etres inferieur au nombre de place disponnible de la zone et inferieur au nombre d'ouvrier dispo de la zone 
	            return new Choix(inv.listeZonesDispo.get(zoneChoisie), nbOuvChoisie);
			}
	     else 
	    	 {
	    	 return null;
	    }
	}
}