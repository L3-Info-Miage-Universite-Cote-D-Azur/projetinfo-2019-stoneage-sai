package stoneage;
import java.util.ArrayList;
import java.util.Random;

public class Joueur {
	Random rand = new Random();
	public Choix placerOuvriers(ArrayList<Zone> listeZonesDispo, Inventaire inv){
		if ((listeZonesDispo != null) && (listeZonesDispo.size() > 0)&&inv.getNbOuvrier()>0){
			 	int zoneChoisie = rand.nextInt(listeZonesDispo.size()); 
				//IA simple qui choisit une zone au hazard
				int nbOuvChoisie= rand.nextInt(inv.getNbOuvrier());
				//IA simple qui choisit nombre d'ouvrier qu'elle va poser sur cette zone au hazard 
				 
	            return new Choix(listeZonesDispo.get(zoneChoisie), nbOuvChoisie);
		 }
	     else 
	    	 {
	    	 return null;
	    	 }
	}
}
