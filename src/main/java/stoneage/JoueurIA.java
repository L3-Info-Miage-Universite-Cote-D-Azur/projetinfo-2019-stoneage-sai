package stoneage;
import java.util.ArrayList;
import java.util.Random;

public class JoueurIA {

	Random rand = new Random();
	//Zone zone ;
    ArrayList<Integer> indZonesDispo = new ArrayList<>();
    public void recupeRes(Inventaire inventaireJoueur, Zone zoneChoisi) { 
    	int nbRessources= zoneChoisi.lancéDeDés(zoneChoisi.getNbOuvriersPlaces());
    	
    	/*Ici le joueur va chosir s'il peut ou pas placer des outils lorsqu'il recupere ses gains.
    	si par exemple il a une somme de des egale a 8 dans la zone foret il peut rajouter un outils pour avoir 9 et donc 3 bois au lieu de 2.*/
    	
    	if ((nbRessources % zoneChoisi.niveauZone)<inventaireJoueur.getNbOutils())
    	{
    		nbRessources+=nbRessources % zoneChoisi.niveauZone;
    		inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils()-(nbRessources % zoneChoisi.niveauZone));	
    	}
    		
    	//recuperer les ressources gagner
    	inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);
    	//ajouter les nouveau ressources a l'inventaire du joueur 
    	switch(zoneChoisi.niveauZone)
    	{
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
        
    }
    
	public Choix placerOuvriers(ArrayList<Zone> listeZonesDispo, Inventaire inv){
	    for (int i=0;i < listeZonesDispo.size();i++ ){
	    	indZonesDispo.add((listeZonesDispo.get(i)).niveauZone);
	    } //remplire la liste des zones
		if ((listeZonesDispo != null) && (listeZonesDispo.size() > 0))
		{
			if ((indZonesDispo.contains(2))&& inv.getNourriture()<5 ) 
				// si la zone chasse est disponnible et que le nombre de nourriture disponnible ne suffit pas pour nourrire les ouvriers
	    	{	
				int nbOuvChoisie= 5 - inv.getNourriture() ;
				// on choisit la zone de chasse si on a pas assez de nourriture 
				return new Choix(listeZonesDispo.get(1), nbOuvChoisie);
			}
			if (indZonesDispo.contains(1))				
			{
				return new Choix(listeZonesDispo.get(0), 1);	 //choisir zone fabrication outil 
	    	}
			else 
			{
				return new Choix(listeZonesDispo.get(0),inv.getNbOuvrierDispo());
			}		
		}
		else 
			{
			return null;
		    }
	}
	

}