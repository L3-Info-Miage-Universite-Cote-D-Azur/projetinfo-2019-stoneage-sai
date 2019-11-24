package stoneage;           //**************ajout de 2 methode choix entre 4 ressource ou entre 2 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.LinkedHashSet;
import java.util.Set;

public class Joueur implements Joueurs {
	Random rand = new Random();
        String name;
	int num;
	Joueur(String name,int num){
            this.name=name;
            this.num=num;
        }
        @Override
        public int getNum(){
            return num;
        }
    public int placerOutils(int nbOutils,int nbRessources, Zone zoneChoisi) { 
    /* cette methode va permettre au joueur de choisir le nombre d'outil d'il va utilisier*/
	    int OutilChoisie = rand.nextInt(nbOutils+1);
    	return OutilChoisie;
    }
    public int cadeauRes(ArrayList<Integer> listeDe ){
		//cette methode va permettre au joueur de choisir la resouce cadeau de la carte civilisation
		return listeDe.get(rand.nextInt(listeDe.size()));
	}


    public int choixTypeRes(int cout,Inventaire inv, int...typeDispo) { 
    /* cette methode permet au joueure de choisir la resource qu'il va utiliser pour payer ses dettes */
    	ArrayList<Integer> listTypeDispo = new ArrayList(); //transforme le tableau en liste
    	for (int i=0 ; i<typeDispo.length; i++) {
    		listTypeDispo.add(typeDispo[i]);
    	}
    	/* le joueur retourne :
    	 * -1: s'il ne veut pas prendre la carte 
    	 * 3: s'il choisi de la payer avec bois 
    	 * 4:  Argile 
    	 * 5: Pierre 
    	 * 6: Or 
    	 */
    	if ( inv.getNbBois()<cout) {
    		// s'il ya pas assez de bois on enleve l'indice de cette zone de la liste
    		listTypeDispo.remove(0);
		}
		else if ( inv.getNbArgile()<cout) {
			listTypeDispo.remove(1);
		}
		else if ( inv.getNbPierre()>cout) {
			listTypeDispo.remove(2);
		}
		else if (inv.getNbOr()>cout) {
			listTypeDispo.remove(3);
		}    	
    	if(listTypeDispo.size()>0) {
    		int i=rand.nextInt((listTypeDispo.size()));
    		return listTypeDispo.get(i);
    	}
    	else {
    		return (-1);
    	}
    }

	public Choix placerOuvriers(ArrayList<Zone> LesZones ,Inventaire inv){
		if (StoneAge.getNbJoueurTotal()==2){
			if ( (inv.getNbZoneJouer() < 6&&inv.ouvrierDispo())){
				int i=0;
				int zoneChoisie = rand.nextInt(9);
			 	while ( inv.listeZonesJouer.get(zoneChoisie)==true || LesZones.get(zoneChoisie).getNbPlaceDispo()==0|| LesZones.get(zoneChoisie).nbJoueur>=1){				 	
			 		zoneChoisie = rand.nextInt(9);
			 		i++;
			 		if(i==10) {
			 			zoneChoisie = 1;
			 			break;
			 		}
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
		else if(StoneAge.getNbJoueurTotal()==3) {
			if ( (inv.getNbZoneJouer() < 6 &&inv.ouvrierDispo())){
				int i=0;
			 	int zoneChoisie = rand.nextInt(10);
			 	while ( inv.listeZonesJouer.get(zoneChoisie)==true || LesZones.get(zoneChoisie).getNbPlaceDispo()==0|| LesZones.get(zoneChoisie).nbJoueur>=2){
			 		zoneChoisie = rand.nextInt(10);
			 		i++;
			 		if(i==10) {
			 			zoneChoisie = 1;
			 			break;
			 		}
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
		else {
			if ( (inv.getNbZoneJouer() < 6 &&inv.ouvrierDispo())){

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
        @Override
        public String toString(){
            return name;
        }	
}