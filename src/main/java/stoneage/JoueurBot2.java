package stoneage;
import java.util.ArrayList;
import java.util.Random;
/**
 *Cette Class est les joueurs qui suivent une strategie dans le jeu ,
 * elle permet de faire des choix plus inteligent et eviter les choix
 * au hazard le plus possible en respenctant les regle du jeu.
 *
 * Pour le choix de la zone:
 *  Dans un premier temps le joueur regarde ses nouriture et si elle sont compriuse entre 5 et 10 ou inferieur a 3
 *  il choisit de placer un ou des ouvrier en zone chasse pour ne pas manquer de nouriture lors du nourrissage des ouvriers
 *  ensuite il previligie la recuperation de bois si celui ci est trop faible et ensuite si il peut il recupere un outil
 *  Apres cela il previligie les carte batiment et ensuite civilisation
 *  sinon il choisit les zones riviere,champ, ...
 *  
 *  Pour le choix de l'outil:
 *   si cela est interressant de placer un outil c'est a dire que si le placement d'un outil lui permet de prendre 
 *   plus de ressource: alors il choisit de placer un outil
 *   
 * Pour le choix de Dé cadeau de carte civilisation :
 * 	il choisit le meilleur dé possible donc Or, champ , outil, argile ...
 *   
 * Pour le choix de type de ressource pour payer ses dettes:
 *  il prend les ressources du moin vher au plus cher donc bois, argile, pierre, or.
 *  
 * Pour nourrir ses ouvrier:
 *  il utilise le meme principe que pour payer ses dettes.
 *  sinon il paye en score.
 **/
public class JoueurBot2 implements Joueurs {
	Random rand = new Random();
	private int gains;
	private String TypeGains;
    private String name;
	private int num;
	JoueurBot2(String name,int num){
            this.name=name;
            this.num=num;
	}
	public Choix placerOuvriers(ArrayList<Zone> LesZones ,Inventaire inv){
		if ( (inv.getNbZoneJouer() <10 && inv.ouvrierDispo())){
			if (!inv.listeZonesJouer.get(1) && inv.getNourriture() >= 5 && inv.getNourriture() < 10  && LesZones.get(1).getNbPlaceDispo() > 2 ){//Chasse
				return new Choix(1, 1);
			}
			else if (!inv.listeZonesJouer.get(1) && inv.getNourriture() < 3 && LesZones.get(1).getNbPlaceDispo()>3) {
				return new Choix(1, 3);
			}
			else if (!inv.listeZonesJouer.get(2) && LesZones.get(2).getNbPlaceDispo()>= 2 && inv.getNbBois()<10 && inv.getNbOuvrierDispo() >= 2) {
				return new Choix(2, 2);
			}
			else if (!inv.listeZonesJouer.get(0) && LesZones.get(0).getNbPlaceDispo()>= 1 && inv.getNbOuvrierDispo() >= 1) {//Fabrication outils
				return new Choix(0, 1);
			}
			else if (StoneAge.getNbJoueurTotal()==2){
				if (inv.getNbZoneJouer() < 10 && inv.ouvrierDispo()){
					int i=0;
					int nbOuvChoisie = 1;
					int[] tabZoneDispo={0,1,2,3,4,5,6,7,8,11,12,15}; // zone 10 et 11 indispo
					int zoneChoisie = tabZoneDispo[10];
					while (inv.listeZonesJouer.get(zoneChoisie) || LesZones.get(zoneChoisie).getNbPlaceDispo()==0|| LesZones.get(zoneChoisie).nbJoueur>=1){
						if (i == 0){ zoneChoisie = tabZoneDispo[9];}
						else if(i == 1){zoneChoisie = tabZoneDispo[0];}
						else if (i == 2){zoneChoisie = tabZoneDispo[8];}
						else if (i == 3){zoneChoisie = tabZoneDispo[7];}
						else if (i == 4){zoneChoisie = tabZoneDispo[6];}
						else if (i == 5) {
							if (inv.getNbOuvrierDispo() >= 2) {
								zoneChoisie = tabZoneDispo[3];
							}
							else {
								zoneChoisie = tabZoneDispo[2];
							}
							nbOuvChoisie = inv.getNbOuvrierDispo();
						}
						else if(i == 6){
							zoneChoisie = tabZoneDispo[2];
							nbOuvChoisie = inv.getNbOuvrierDispo();
						}
						else if(i >= 7) {
							zoneChoisie = 1;
							nbOuvChoisie = inv.getNbOuvrierDispo();
							break;
						}
						i++;
					}

					return new Choix(zoneChoisie, nbOuvChoisie);
				}
				else{
					return null;
				}
			}
			else if(StoneAge.getNbJoueurTotal()==3) {
				if ( (inv.getNbZoneJouer() < 10 &&inv.ouvrierDispo())){
					int i=0;
					int nbOuvChoisie = 1;
					int[] tabZoneDispo={0,1,2,3,4,5,6,7,8,9,11,12,13,15}; // 7,8,9 cartes 11,12,13 batiments
					int zoneChoisie = tabZoneDispo[12];
					while (inv.listeZonesJouer.get(zoneChoisie)|| LesZones.get(zoneChoisie).getNbPlaceDispo()==0|| LesZones.get(zoneChoisie).nbJoueur>=2){
						if (i == 0){ zoneChoisie = tabZoneDispo[11];}
						else if(i == 1){zoneChoisie = tabZoneDispo[10];}
						else if (i == 2){zoneChoisie = tabZoneDispo[9];}
						else if (i == 3){zoneChoisie = tabZoneDispo[8];}
						else if (i == 4){zoneChoisie = tabZoneDispo[7];}
						else if (i == 5){zoneChoisie = tabZoneDispo[6];}
						else if (i == 6) {
							if (inv.getNbOuvrierDispo() >= 2){
								zoneChoisie = tabZoneDispo[3];
							}
							else {zoneChoisie = tabZoneDispo[2];}

							if (LesZones.get(zoneChoisie).getNbPlaceDispo() >= inv.getNbOuvrierDispo()){nbOuvChoisie = inv.getNbOuvrierDispo();}
							else{nbOuvChoisie = LesZones.get(zoneChoisie).getNbPlaceDispo();}
						}
						else if(i == 7){
							zoneChoisie = tabZoneDispo[2];
							if (LesZones.get(zoneChoisie).getNbPlaceDispo() >= inv.getNbOuvrierDispo()){nbOuvChoisie = inv.getNbOuvrierDispo();}
							else{nbOuvChoisie = LesZones.get(zoneChoisie).getNbPlaceDispo();}
						}
						else if(i >= 8) {
							zoneChoisie = 1;
							nbOuvChoisie = inv.getNbOuvrierDispo();
							break;
						}
						i++;
					}
					return new Choix(zoneChoisie, nbOuvChoisie);
				}
				else{
					return null;
				}
			}

			else if ((inv.getNbZoneJouer() < 10 && inv.ouvrierDispo())){
				int i=0;
				int nbOuvChoisie = 1;
				int zoneChoisie = 14;
				while (inv.listeZonesJouer.get(zoneChoisie) || LesZones.get(zoneChoisie).getNbPlaceDispo()==0){
					if (i <= 7){ zoneChoisie = 13 - i;}
					else if(i == 8){
						if (inv.getNbOuvrierDispo() >= 2){zoneChoisie = 3;}
						else {zoneChoisie = 2;}

						if (LesZones.get(zoneChoisie).getNbPlaceDispo() >= inv.getNbOuvrierDispo()){nbOuvChoisie = inv.getNbOuvrierDispo();}
						else{nbOuvChoisie = LesZones.get(zoneChoisie).getNbPlaceDispo();}
					}
					else if(i == 9){
						zoneChoisie = 2;
						if (LesZones.get(zoneChoisie).getNbPlaceDispo() >= inv.getNbOuvrierDispo()){nbOuvChoisie = inv.getNbOuvrierDispo();}
						else{nbOuvChoisie = LesZones.get(zoneChoisie).getNbPlaceDispo();}
					}
					else if(i>=10){
						zoneChoisie = 1;
						nbOuvChoisie = inv.getNbOuvrierDispo();
						break;
					}
					i++;
				}
				return new Choix(zoneChoisie, nbOuvChoisie);
			}
			else{
				return null;
			}
		}
		return null;
	}
	public int placerOutils(int nbOutils,int nbRessources, Zone zoneChoisi) {
	    int OutilChoisie ;
		if ((zoneChoisi.niveauZone-(nbRessources % zoneChoisi.niveauZone))<=nbOutils)
    	{
			OutilChoisie =(zoneChoisi.niveauZone-(nbRessources % zoneChoisi.niveauZone));
    	}
    	else {
    	    OutilChoisie = rand.nextInt(nbOutils+1);
    	}
    	return OutilChoisie;
    }
    public int cadeauRes(ArrayList<Integer> listeDe){
		if (listeDe.contains(4)){return 4;}
		else if (listeDe.contains(6)){return 6;}
		else if (listeDe.contains(5)){return 5;}
		else if (listeDe.contains(3)){return 3;}
		else if (listeDe.contains(2)){return 2;}
		else {return 1;}
	}
	public int choixTypeRes(int cout, Inventaire inv, int...typeDispo) {
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
			if (inv.getNbBois()<cout) {
				// s'il ya pas assez de bois on enleve l'indice de cette zone de la liste
				listTypeDispo.remove(listTypeDispo.indexOf(3));
			}
			if ( inv.getNbArgile()<cout) {
				listTypeDispo.remove(listTypeDispo.indexOf(4));
			}
			if ( inv.getNbPierre()<cout) {
				listTypeDispo.remove(listTypeDispo.indexOf(5));
			}
			if (inv.getNbOr()<cout) {
				listTypeDispo.remove(listTypeDispo.indexOf(6));
			}
			if(listTypeDispo.size()>0&&rand.nextBoolean()) { //le joueur decide de prendre cette carte si il a acces de ressource et si il a envie (true/false)
				int i=rand.nextInt((listTypeDispo.size()));
				return listTypeDispo.get(i);
			}
			else {
				return (-1);
			}
	}




	public int getNum(){
		return num;
	}
	public int getGains(){
		return gains;
	}
	public String TypeGains(){
		return  TypeGains;
	}      
}
