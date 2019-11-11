package stoneage;
import java.util.ArrayList;
import java.util.Random;

public class Joueur implements Joueurs {
	Random rand = new Random();
        @Override
    public void recupeRes(Inventaire inventaireJoueur, Zone zoneChoisi) { 
    	int nbRessources= zoneChoisi.lancéDeDés(zoneChoisi.getNbOuvriersPlaces());

    	if ((nbRessources % zoneChoisi.niveauZone)<inventaireJoueur.getNbOutils())
    	{
    		nbRessources+=nbRessources % zoneChoisi.niveauZone;
    		nbRessources=nbRessources / zoneChoisi.niveauZone;
    		inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils()-(nbRessources % zoneChoisi.niveauZone));	   	
    	}    		
    	//recuperer les ressources gagner
    	inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);
    	//ajouter les nouveau ressources a l'inventaire du joueur 
    	switch(zoneChoisi.niveauZone)
    	{
    		case 1:
    			inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils()+1);
    		case 2:
    			inventaireJoueur.setNourriture(inventaireJoueur.getNourriture()+nbRessources);
    		case 3:
    			inventaireJoueur.setNbBois(inventaireJoueur.getNbBois()+nbRessources);
    			break;
    		case 4: 
    			inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile()+nbRessources);
    			break;
    		case 5:
    			inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre()+nbRessources);
    			break;

    		case 6:
    			inventaireJoueur.setNbOr(inventaireJoueur.getNbOr()+nbRessources);
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
	public Choix placerOuvriers(ArrayList<Zone> listeZonesDispo, Inventaire inv){
		if ((listeZonesDispo != null) && (listeZonesDispo.size() > 0))
			{
			 	int zoneChoisie = rand.nextInt(listeZonesDispo.size()); 
				//IA simple qui choisit une zone au hazard
				int nbOuvChoisie= rand.nextInt(inv.getNbOuvrierDispo())+1;
				//IA simple qui choisit nombre d'ouvrier qu'elle va poser sur cette zone au hazard 
	            return new Choix(listeZonesDispo.get(zoneChoisie), nbOuvChoisie);
			}
	     else 
	    	 {
	    	 return null;
	    	 }
	}
}
