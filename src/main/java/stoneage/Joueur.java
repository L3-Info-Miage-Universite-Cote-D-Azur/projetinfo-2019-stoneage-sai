package stoneage;           //**************ajout de 2 methode choix entre 4 ressource ou entre 2 
import java.util.ArrayList;
import java.util.Random;

public class Joueur implements Joueurs {
	Random rand = new Random();
	
	/* cette methode va permettre au joueur de choisir le nombre d'outil d'il va utilisier*/
    public int placerOutils(int nbOutils,int nbRessources, Zone zoneChoisi) { 
	    int OutilChoisie = rand.nextInt(nbOutils+1); 
    	return OutilChoisie;
    }
    public int choixTypeRes(int...typeDispo) {
    	int i=rand.nextInt((typeDispo.length));
    	return typeDispo[i];
    	
    }

	public Choix placerOuvriers(ArrayList<Zone> LesZones ,Inventaire inv){
		if ( (inv.getNbZoneJouer() < 6&&inv.ouvrierDispo())){
			 	int zoneChoisie = rand.nextInt(11);
			 	while ( inv.listeZonesJouer.get(zoneChoisie)==true || LesZones.get(zoneChoisie).getNbPlaceDispo()==0){
			 		zoneChoisie = rand.nextInt(11);
			 	}
				//IA simple qui choisit une zone au hazard
				int nbOuvChoisie= rand.nextInt(Math.min(inv.getNbOuvrierDispo(),LesZones.get(zoneChoisie).getNbPlaceDispo()))+1;
				//IA simple qui choisit nombre d'ouvrier qu'elle va poser sur cette zone au hazard 
				//le nombre doit etres inferieur au nombre de place disponnible de la zone et inferieur au nombre d'ouvrier dispo de la zone 
	            return new Choix(zoneChoisie, nbOuvChoisie);
			}
	     else{
	    	 return null;
	    }
	}
	
}