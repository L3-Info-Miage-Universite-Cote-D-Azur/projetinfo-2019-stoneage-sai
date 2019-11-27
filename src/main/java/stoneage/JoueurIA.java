package stoneage;
import java.util.ArrayList;
import java.util.Random;

public class JoueurIA implements Joueurs {
	Random rand = new Random();
	String name;
	private int num;
	JoueurIA(String name,int num){
            this.name=name;
            this.num=num;
        }
        @Override
    public int getNum(){
            return num;
        }
    public int placerOutils(int nbOutils,int nbRessources, Zone zoneChoisi) {
    	int OutilChoisie ;
    	/*Ici le joueur va choisir s'il peut ou pas placer des outils lorsqu'il recupere ses gains.*/
		/*Si le joueur a un nombre d'outils suffisant pour avoir i+1 resource de plus, alors il les utilise*/
		for (int i=5;i>=0;i--){
			if ((zoneChoisi.niveauZone-(nbRessources % zoneChoisi.niveauZone))+(zoneChoisi.niveauZone*i)<=nbOutils)
			{
				OutilChoisie =(zoneChoisi.niveauZone-(nbRessources % zoneChoisi.niveauZone))+(zoneChoisi.niveauZone*i);
				return OutilChoisie;
			}
    	/*si par exemple il a une somme de des egale a 8 dans la zone foret il peut rajouter un outils
    	pour avoir 9 et donc 3 bois au lieu de 2.*/
		}
		//  il retoune 0 OUTILS par ce que ça ne servira a rien  de perdre ses outil alors qu'il ne va rien gagner au retour
		return 0;
	}

	public int cadeauRes(ArrayList<Integer> listeDe ){
		//cette methode va permettre au joueur de choisir la resouce cadeau de la carte civilisation
		if (listeDe.contains(4)){ //s'il il y a un dé Or parmis les dé il le choisi en 1er
			return 4;
		}
		else if (listeDe.contains(6)){//s'il il y a un dé niveau champ parmis les dé il le choisi en second
			return 6;
		}
		else if (listeDe.contains(5)){//s'il il y a un dé Outil parmis les dé il le choisi en 3eme
			return 5;
		}
		else if (listeDe.contains(3)){//s'il il y a un dé Argile parmis les dé il le choisi en 4eme...
			return 3;
		}
		else if (listeDe.contains(2)){
			return 2;
		}
		else {
			return 1;
		}
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
		if (inv.getNbBois()>cout && listTypeDispo.contains(3)) {
			// s'il a assez de bois il paye avec bois car c la resource la moin chere
			return 3;
		}
		if ( inv.getNbArgile()>cout && listTypeDispo.contains(4)) {
			// s'il a assez d'argile  il paye avec argile car c la resource la moin chere apres le bois...
			return 4;
		}
		if ( inv.getNbPierre()>cout && listTypeDispo.contains(5)) {
			return 5;
		}
		if ( inv.getNbOr()>cout&&listTypeDispo.contains(6)) {

			return 6 ;
		}
		else {
			return (-1);
		}
    }

    public Choix placerOuvriers(ArrayList<Zone> LesZones ,Inventaire inv){
	    if ( (inv.getNbZoneJouer() < 6 &&inv.ouvrierDispo())){
			if (inv.listeZonesJouer.get(1)==false&& inv.getNourriture()<5 &&inv.getNbOuvrierDispo()==5 )
			// si la zone chasse est disponnible et que le nombre de nourriture disponnible ne suffit pas pour nourrire les ouvriers
			{
				// on choisit la zone de chasse si on a pas assez de nourriture
				return new Choix(1, 5);
			}
			else if (inv.listeZonesJouer.get(6)==false&& inv.getNourriture()<5 &&inv.getNbOuvrierDispo()>1 )
			// si le champ est disponnible et que le nombre de nourriture disponnible ne suffit pas pour nourrire les ouvriers
			{
				// on choisit le champ si on a pas assez de nourriture
				return new Choix(6, 1);
			}
			else if (StoneAge.getNbJoueurTotal()==2){
				if ( (inv.getNbZoneJouer() < 6&&inv.ouvrierDispo())){
					int i=0;
					int[] tabZoneDispo={0,1,2,3,4,5,6,7,8,9,12,13};
					int zoneChoisie = tabZoneDispo[rand.nextInt(12)];
				 	while ( inv.listeZonesJouer.get(zoneChoisie)==true || LesZones.get(zoneChoisie).getNbPlaceDispo()==0|| LesZones.get(zoneChoisie).nbJoueur>=1){
						zoneChoisie = tabZoneDispo[rand.nextInt(12)];
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
					int[] tabZoneDispo={0,1,2,3,4,5,6,7,8,9,10,12,13,14};
					int zoneChoisie = tabZoneDispo[rand.nextInt(14)];
				 	while ( inv.listeZonesJouer.get(zoneChoisie)==true || LesZones.get(zoneChoisie).getNbPlaceDispo()==0|| LesZones.get(zoneChoisie).nbJoueur>=2){
						zoneChoisie = tabZoneDispo[rand.nextInt(14)];
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
			else if ( (inv.getNbZoneJouer() < 6 &&inv.ouvrierDispo())){
				
				 	int zoneChoisie = rand.nextInt(15);
				 	while ( inv.listeZonesJouer.get(zoneChoisie)==true || LesZones.get(zoneChoisie).getNbPlaceDispo()==0){
				 		zoneChoisie = rand.nextInt(15);
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
	return null;
    }

}
