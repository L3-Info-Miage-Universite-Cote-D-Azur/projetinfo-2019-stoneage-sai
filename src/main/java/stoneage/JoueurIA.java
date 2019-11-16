package stoneage;
import java.util.ArrayList;
import java.util.Random;

public class JoueurIA implements Joueurs {
	Random rand = new Random();
	public int gains;
	public String TypeGains;

	public int getGains(){
		return gains;
	}
	public String TypeGains(){
		return  TypeGains;
		
	}
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
    
    public Choix placerOuvriers(ArrayList<Zone> LesZones ,Inventaire inv){

	    if ( (inv.getNbZoneJouer() < 6 &&inv.ouvrierDispo())){
			if (inv.listeZonesJouer.get(2)==false&& inv.getNourriture()<5 &&inv.getNbOuvrierDispo()==5 ) 
				// si la zone chasse est disponnible et que le nombre de nourriture disponnible ne suffit pas pour nourrire les ouvriers
	    	{	
				int nbOuvChoisie= 5 - inv.getNourriture() ;
				// on choisit la zone de chasse si on a pas assez de nourriture 
				return new Choix(1, nbOuvChoisie);
			}
			else 
			{
				int zoneChoisie = rand.nextInt(6);
			 	while ( inv.listeZonesJouer.get(zoneChoisie)==true || LesZones.get(zoneChoisie).getNbPlaceDispo()==0){
			 		zoneChoisie = rand.nextInt(6);
			 	}
				//IA simple qui choisit une zone au hazard
				int nbOuvChoisie= rand.nextInt(Math.min(inv.getNbOuvrierDispo(),LesZones.get(zoneChoisie).getNbPlaceDispo()))+1;
				//IA simple qui choisit nombre d'ouvrier qu'elle va poser sur cette zone au hazard 
				//le nombre doit etres inferieur au nombre de place disponnible de la zone et inferieur au nombre d'ouvrier dispo de la zone 
	            return new Choix(zoneChoisie, nbOuvChoisie);
			}
		}
		else{
	    	 return null;
		    }
	}
	
  
}
