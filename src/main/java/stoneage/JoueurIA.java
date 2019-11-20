package stoneage;
import java.util.ArrayList;
import java.util.Random;

public class JoueurIA implements Joueurs {
	Random rand = new Random();

    public int placerOutils(int nbOutils,int nbRessources, Zone zoneChoisi) { 

    	int OutilChoisie ;
 

    	/*Ici le joueur va choisir s'il peut ou pas placer des outils lorsqu'il recupere ses gains.
    	si par exemple il a une somme de des egale a 8 dans la zone foret il peut rajouter un outils 
    	pour avoir 9 et donc 3 bois au lieu de 2.*/
    	
    	if ((zoneChoisi.niveauZone-(nbRessources % zoneChoisi.niveauZone))<=nbOutils)
    	{
    		OutilChoisie =(zoneChoisi.niveauZone-(nbRessources % zoneChoisi.niveauZone));

    	}
    	else {
    	    OutilChoisie = rand.nextInt(nbOutils+1); 
    	}
    	return OutilChoisie;
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
