package stoneage;
import java.util.ArrayList;

/**
 *Cette Classe représente les joueursIA qui suivent une strategie dans le jeu.
 * elle permet de faire des choix plus intelligent et éviter les choix
 * au hazard le plus possible en respenctant les regle du jeu.
 *
 * Pour le choix de Zone :
 * stratégie du joueurIA est tout d'abord de surveiller le nombre de nourriture qu'il possède pour ne pas avoir de score négatif
 * ensuite il essaie d'avoir le nombre  maximal d'ouvriers
 * apres il va choisir les carte civilsation 1 /2 (les moins chere )
 * sinon il choisit la zone fabrication d'outil, le champ, foret, carriere...
 *
 *Pour le choix de Dé cadeau de carte civilisation :
 * il choisit le meilleur dé present donc Or> champ > outil > argile ...
 *
 * Pour le choix de ressources pour payer la carte civ :
 *le joueur regarde d'abord les ressources les moin chère: Bois, Argile, Pierre..
 *
 * @see Joueurs
 * 	Pour les méthodes
**/
public class JoueurIA implements Joueurs {
	private String name;
	private int num;
	JoueurIA(String name,int num){
            this.name=name;
            this.num=num;
        }
        @Override
    public int getNum(){
            return num;
        }
	/**La methode placerOutils va permettre au joueur de choisir s'il peut ou pas placer des outils lorsqu'il recupere ses gains,
	* Ainsi que le nombre d'outil qu'il va utiliser avec une maniere plus reflechis**/
    public int placerOutils(int nbOutils,int nbRessources, Zone zoneChoisi) {
    	int OutilChoisie ;
		/*Si le joueur a un nombre d'outils suffisant pour avoir i+1 resource de plus, alors il les utilise*/
		for (int i=5;i>=0;i--){
			if ((zoneChoisi.getNiveauZone()-(nbRessources % zoneChoisi.getNiveauZone()))+(zoneChoisi.getNiveauZone()*i)<=nbOutils)
			{
				OutilChoisie =(zoneChoisi.getNiveauZone()-(nbRessources % zoneChoisi.getNiveauZone()))+(zoneChoisi.getNiveauZone()*i);
				return OutilChoisie;
			}
    	/*si par exemple il a une somme de des egale a 8 dans la zone foret il peut rajouter un outils
    	pour avoir 9 et donc 3 bois au lieu de 2.*/
		}
		//  il retoune 0 OUTILS par ce que ça ne servira a rien  de perdre ses outil alors qu'il ne va rien gagner au retour
		return 0;
	}
	/**
	La methode cadeauRes va permettre au joueur de choisir la resouce cadeau de la carte civilisation */
	public int cadeauRes(ArrayList<Integer> listeDe ){
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
	/**
	 * La  methode choixTypesRes permet au joueure de choisir la resource qu'il va utiliser pour payer ses dettes **/
	public int choixTypeRes(int cout,Inventaire inv, int...typeDispo) {
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
    /**Cette metode va permettre au joueur de choisir la zone et le nombre d'ouvrier qu'il va posé dans celle ci
    avec une facon plus refelchie et en suivant une strategie plus iteligente que d faire un choix au hazard **/
    public Choix placerOuvriers(ArrayList<Zone> lesZones ,Inventaire inv) {
    	//*****************Si Jeu entre 2 joueurs******************//
		if (StoneAge.getNbJoueurTotal() == 2) {
			ArrayList<Integer> listZoneDispo = new ArrayList<>();
			for (int i = 0; i < lesZones.size(); i++) {
				if (lesZones.get(i).getNbPlaceDispo() != 0 && inv.listeZonesJouer.get(i) != true && lesZones.get(i).getNbJoueur() == 0 && i != 1 && i != 9 && i != 10 && i != 13 && i != 14) {
					listZoneDispo.add(i);
				}
				if (i == 1 && inv.listeZonesJouer.get(i) != true) {
					listZoneDispo.add(i);
				}
			}
			// le joueur choisi une zone parmis les zones dispo
			int[] tabChoix=choixInteligent(listZoneDispo, lesZones, inv);
			//IA simple qui choisit nombre d'ouvrier qu'elle va poser sur cette zone au hazard
			//le nombre doit etres inferieur au nombre de place disponnible de la zone et inferieur au nombre d'ouvrier dispo de la zone
			return new Choix(tabChoix[0], tabChoix[1]);
		}
		//*****************Si Jeu entre 3 joueurs******************//
		else if (StoneAge.getNbJoueurTotal() == 3) {
			ArrayList<Integer> listZoneDispo = new ArrayList<>();
			for (int i = 0; i < lesZones.size(); i++) {
				if (lesZones.get(i).getNbPlaceDispo() != 0 && inv.listeZonesJouer.get(i) != true && lesZones.get(i).getNbJoueur() <=1 && i != 1 &&  i != 10  && i != 14) {
					listZoneDispo.add(i);
				}
				if (i == 1 && inv.listeZonesJouer.get(i) != true) {
					listZoneDispo.add(i);
				}
			}
			// le joueur choisi une zone parmis les zones dispo
			int[] tabChoix=choixInteligent(listZoneDispo, lesZones, inv);
			//IA simple qui choisit nombre d'ouvrier qu'elle va poser sur cette zone au hazard
			//le nombre doit etres inferieur au nombre de place disponnible de la zone et inferieur au nombre d'ouvrier dispo de la zone
			return new Choix(tabChoix[0], tabChoix[1]);
		}
		//*****************Si Jeu entre 4 joueurs******************//
		else if (StoneAge.getNbJoueurTotal() == 4) {
			ArrayList<Integer> listZoneDispo = new ArrayList<>();
			for (int i = 0; i < lesZones.size(); i++) {
				if (lesZones.get(i).getNbPlaceDispo() != 0 && inv.listeZonesJouer.get(i) != true && lesZones.get(i).getNbJoueur() <=2 && i != 1 ) {
					listZoneDispo.add(i);
				}
				if (i == 1 && inv.listeZonesJouer.get(i) != true) {
					listZoneDispo.add(i);
				}
			}
			// le joueur choisi une zone parmis les zones dispo
			int[] tabChoix=choixInteligent(listZoneDispo, lesZones, inv);
			//IA simple qui choisit nombre d'ouvrier qu'elle va poser sur cette zone au hazard
			//le nombre doit etres inferieur au nombre de place disponnible de la zone et inferieur au nombre d'ouvrier dispo de la zone
			return new Choix(tabChoix[0], tabChoix[1]);
		}
		else{
			return null;
		}
	}
	/**
	 * La strategie du joueurIA est tout d'abord de surveiller le nombre de nourriture qu'il possede pour pas avoir de score negatif
	 * ensuite il essaie d'avoir le nombre  maximal d'ouvriers
	 * apres il va choisire les carte civilsation 1 /2 (les moins chere )
	 * sinon  il choisi la zone fabrication d'outil, le champ, foret, carriere...
	 * Cette methode retourne un tableau qui contient une zone et un nombre d'ouvrier que le joeuur va placer sur cette Zone*/
    private int[] choixInteligent( ArrayList<Integer> listZoneDispo,ArrayList<Zone> lesZones, Inventaire inv ){
    	int choixInt[]={0,0};
		if (listZoneDispo.contains(1) && listZoneDispo.contains(6)&&inv.getNourriture()<inv.getNbOuvrier()  ) {
			// si la zone chasse est disponnible et que le nombre de nourriture disponnible ne suffit pas pour nourrire les ouvriers
			if (listZoneDispo.contains(1) && inv.getNbOuvrierDispo() >= 3) {
				// on choisit la zone de chasse si on a pas assez de nourriture
				choixInt[0] = 1;
				choixInt[1] = 3;
			} else if (listZoneDispo.contains(6)&&(listZoneDispo.contains(0)|| listZoneDispo.contains(15))) {
				choixInt[0]  = 6;
				choixInt[1] = 1;
				// si le champ est disponnible et que le nombre de nourriture disponnible ne suffit pas pour nourrire les ouvriers, on le choisi
			}
		} else if (listZoneDispo.contains(15)&&(listZoneDispo.contains(0)|| listZoneDispo.contains(6))&& inv.getNbOuvrier()<10&& inv.getNbOuvrierDispo()>=2) { // zone Hutte
		choixInt[0]= 15; //Hutte
		choixInt[1]=2;
		}
		else if(listZoneDispo.contains(7)&& inv.getNbRessource()>1) {
			// pour ne pas prendre une carte civilisation pour rien
			choixInt[0]= 7;
			choixInt[1] = 1; //on choisi la carte civ 1
		}else if(listZoneDispo.contains(8)&& inv.getNbRessource()>2) {
			choixInt[0] =8;
			choixInt[1]=1;//on choisit la carte civ 2
		}else if (listZoneDispo.contains(0) && inv.getNbOutils()<5 && (listZoneDispo.contains(6)|| listZoneDispo.contains(15))){ // zone fab Outil
			choixInt[0]= 0;
			choixInt[1]=1;
		}else if (listZoneDispo.contains(6)&& (listZoneDispo.contains(0)|| listZoneDispo.contains(15))) { // CHAMP
			choixInt[0] = 6;
			choixInt[1] = 1;
		}else if (listZoneDispo.contains(11)){ // CHAMP
			choixInt[0]= 11;
			choixInt[1]=1;
		}else if (listZoneDispo.contains(2)){ // bois
			choixInt[0]= 2;
			choixInt[1]= rand.nextInt(Math.min(inv.getNbOuvrierDispo(),lesZones.get(2).getNbPlaceDispo()))+1;
		}else if (listZoneDispo.contains(3)){ // argile
			choixInt[0]= 3;
			choixInt[1]= rand.nextInt(Math.min(inv.getNbOuvrierDispo(),lesZones.get(3).getNbPlaceDispo()))+1;
		}else if (listZoneDispo.contains(4)){ // PIERRE
			choixInt[0]= 4;
			choixInt[1]= rand.nextInt(Math.min(inv.getNbOuvrierDispo(),lesZones.get(4).getNbPlaceDispo()))+1;
		}else if (listZoneDispo.contains(5)){  //OR
			//le joueur la choisi que s'il a plus de 3 ouvrier dispo pour avoir un max d'Or
			choixInt[0]= 5;
			choixInt[1]= rand.nextInt(Math.min(inv.getNbOuvrierDispo(),lesZones.get(5).getNbPlaceDispo()))+1;
		}
		else{ // au pire des cas si le joueur ne trouve pas de choix
			choixInt[0]=1;
			choixInt[1]= rand.nextInt(Math.min(inv.getNbOuvrierDispo(),lesZones.get(1).getNbPlaceDispo()))+1;
		}
		return choixInt;
	}
}
