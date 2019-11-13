package stoneage;
import java.util.ArrayList;
import java.util.Random;

public class JoueurIA implements Joueurs {
	Random rand = new Random();
    ArrayList<Integer> indZonesDispo = new ArrayList<>();
    @Override
    public void recupeRes(Inventaire inventaireJoueur, Zone zoneChoisi) { 
    	int nbRessources= zoneChoisi.lancéDeDés(zoneChoisi.getNbOuvriersPlaces());
    	
    	/*Ici le joueur va chosir s'il peut ou pas placer des outils lorsqu'il recupere ses gains.
    	si par exemple il a une somme de des egale a 8 dans la zone foret il peut rajouter un outils pour avoir 9 et donc 3 bois au lieu de 2.*/
    	
    	if ((zoneChoisi.niveauZone-(nbRessources % zoneChoisi.niveauZone))<=inventaireJoueur.getNbOutils())
    	{
    		nbRessources+=(zoneChoisi.niveauZone-(nbRessources % zoneChoisi.niveauZone));
    		nbRessources=(nbRessources / zoneChoisi.niveauZone);
    		inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils()-(nbRessources % zoneChoisi.niveauZone));	
    	}
    		
    	//recuperer les ressources gagner
    	//ajouter les nouveau ressources a l'inventaire du joueur 
    	switch(zoneChoisi.niveauZone)
    	{
    		case 1:
    			inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils()+1);
    			
    			break;
    		case 2:
    			inventaireJoueur.setNourriture(inventaireJoueur.getNourriture()+nbRessources);
    			break;
    		case 3:
    			inventaireJoueur.setNbBois(inventaireJoueur.getNbBois()+nbRessources);
    			inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);
    	    	
    			break;
    		case 4: 
    			inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile()+nbRessources);
    			inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);
    	    	
    			break;
    		case 5:
    			inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre()+nbRessources);
    			inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);
    	    	
    			break;
    		
    		case 6:
    			inventaireJoueur.setNbOr(inventaireJoueur.getNbOr()+nbRessources);
    			inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);
    	    	
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
	public Choix placerOuvriers( Inventaire inv){
	    for (int i=0;i < inv.listeZonesDispo.size();i++ ){
	    	indZonesDispo.add((inv.listeZonesDispo.get(i)).niveauZone);
	    } //remplire la liste des zones
		if ((inv.listeZonesDispo != null) && (inv.listeZonesDispo.size() > 0))
		{
			if ((indZonesDispo.contains(2))&& inv.getNourriture()<5 &&inv.getNbOuvrierDispo()==5 ) 
				// si la zone chasse est disponnible et que le nombre de nourriture disponnible ne suffit pas pour nourrire les ouvriers
	    	{	
				int nbOuvChoisie= 5 - inv.getNourriture() ;
				// on choisit la zone de chasse si on a pas assez de nourriture 
				return new Choix(inv.listeZonesDispo.get(1), nbOuvChoisie);
			}
			/*else if (indZonesDispo.contains(1))				
			{
				return new Choix(inv.listeZonesDispo.get(0), 1);	 //choisir zone fabrication outil 
	    	}*/
			else 
			{
				int zoneChoisie = rand.nextInt(inv.listeZonesDispo.size()); 
				int nbOuvChoisie= rand.nextInt(Math.min(inv.getNbOuvrierDispo(),(inv.listeZonesDispo.get(zoneChoisie).getNbPlaceZone()-1)))+1;

	            return new Choix(inv.listeZonesDispo.get(zoneChoisie), nbOuvChoisie);
				
			}		
		}
		else 
			{
			return null;
		    }
	}
	

}
