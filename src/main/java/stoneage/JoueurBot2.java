package stoneage;
import java.util.ArrayList;
import java.util.Random;
/** 
 * JoueurBot2 est un joueur qui suit une strategie de jeu gagnante.
 * Cette derniere lui permet de faire des choix plus intelligent et éviter les choix
 * au hazard le plus possible en respectant les règles du jeu.
 *
 * Pour le choix de la zone:
 * le joueur regarde s'il a assez de nourriture pour nourrir ses ouvriers à la fin du tour:
 * Si oui:il choisit les zones suivantes dans cette ordre si elles sont disponibles:
 * il  va choisir les cartes civilsation 1 et 2 car elles sont les moins chères
 * puis la zone forêt (car la ressource bois est la moins cher)
 * puis la zone fabrication d'Outils(pour avoir plus de ressources lors de la récupération des ouvriers)
 * puis les batiments 1 2 3 et 4
 * puis lez zones glaisieres carriere et rivieres dans cet ordre(de la ressource la moins chere a la ressource la plus cher)
 * Sinon:il place des figurines dans la zone de chasse pour avoir de la nourriture
 *  
 *  Pour le choix de l'outil:
 *   si cela est interressant de placer un outil c'est-à-dire que si le placement d'un outil lui permet de prendre
 *   plus de ressource: alors il choisit de placer un outil
 * 
 * Pour le choix de Dé cadeau de carte civilisation :
 * il choisit le meilleur dé présent donc Or> champ > outil > argile ...
 *
 * Pour le choix de ressource pour payer la carte civ :
 * le joueur regarde d'abord les ressources les moins chères: Bois, Argile, Pierre..
 *   
 * @see Joueurs
 * 	Pour les méthodes
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
        
	public int cadeauRes(ArrayList<Integer> listeDe){
		if (listeDe.contains(4)){return 4;}
		else if (listeDe.contains(6)){return 6;}
		else if (listeDe.contains(5)){return 5;}
		else if (listeDe.contains(3)){return 3;}
		else if (listeDe.contains(2)){return 2;}
		else {return 1;}
	}
	public int choixTypeRes(int cout,Inventaire inv, int...typeDispo) {
    	ArrayList<Integer> listTypeDispo = new ArrayList();
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
			return 3;
		}
		if ( inv.getNbArgile()>cout && listTypeDispo.contains(4)) {
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
			ArrayList<Integer> tabChoix=ouPlacer(listZoneDispo, lesZones, inv);
			//IA simple qui choisit nombre d'ouvrier qu'elle va poser sur cette zone au hazard
			//le nombre doit etres inferieur au nombre de place disponnible de la zone et inferieur au nombre d'ouvrier dispo de la zone
			return new Choix(tabChoix.get(0), tabChoix.get(1));
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
			ArrayList<Integer> tabChoix=ouPlacer(listZoneDispo, lesZones, inv);
			//IA simple qui choisit nombre d'ouvrier qu'elle va poser sur cette zone au hazard
			//le nombre doit etres inferieur au nombre de place disponnible de la zone et inferieur au nombre d'ouvrier dispo de la zone
			return new Choix(tabChoix.get(0), tabChoix.get(1));
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
			ArrayList<Integer> tabChoix=ouPlacer(listZoneDispo, lesZones, inv);
			//IA simple qui choisit nombre d'ouvrier qu'elle va poser sur cette zone au hazard
			//le nombre doit etres inferieur au nombre de place disponnible de la zone et inferieur au nombre d'ouvrier dispo de la zone
			return new Choix(tabChoix.get(0), tabChoix.get(1));
		}
		else{
			return null;
		}
	}
	public ArrayList<Integer> ouPlacer(ArrayList<Integer> listZoneDispo,ArrayList<Zone> lesZones, Inventaire inv ){
		ArrayList<Integer> placer=new ArrayList<Integer>();
		if (inv.getNourriture()>=inv.getNbOuvrier()) {
			if (listZoneDispo.contains(7) && inv.getNbRessource()>=1 ) {//carte civ qui coute 1
				placer.add(7);
				placer.add(1);
			}
			else if (listZoneDispo.contains(8) && inv.getNbRessource()>=2) {//carte civ qui coute 2
				placer.add(8);
				placer.add(1);
			}
			else if (listZoneDispo.contains(2) && inv.getNbBois()<=4) {//bois
                	// placer.add(2);
                    placer.add(rand.nextInt(Math.min(inv.getNbOuvrierDispo(),lesZones.get(2).getNbPlaceDispo()))+1);
            }
			else if (listZoneDispo.contains(6)) {//champ
				placer.add(6);
            	placer.add(1);
            }
			else if (listZoneDispo.contains(0)) {
               placer.add(0);
               placer.add(1);
			}
			else if (listZoneDispo.contains(11)) {//batiment 1
				placer.add(11);
				placer.add(1);
			}
			else if (listZoneDispo.contains(12)) {//batiment 2
				placer.add(12);
				placer.add(1);
			}
			else if (listZoneDispo.contains(13)) {//batiment 3
                    placer.add(13);
                    placer.add(1);
			}
			else if (listZoneDispo.contains(14)) {//batiment 4
				placer.add(14);
				placer.add(1);
			}
			else if (listZoneDispo.contains(3) ){ // argile
				placer.add(3);
				placer.add(rand.nextInt(Math.min(inv.getNbOuvrierDispo(),lesZones.get(3).getNbPlaceDispo()))+1);
			}
			else if (listZoneDispo.contains(4)){ // PIERRE
				placer.add(4);
				placer.add(rand.nextInt(Math.min(inv.getNbOuvrierDispo(),lesZones.get(4).getNbPlaceDispo()))+1);
			}
			else if (listZoneDispo.contains(5)){  //OR
				//le joueur la choisi que s'il a plus de 3 ouvrier dispo pour avoir un max d'Or
				placer.add(5);
				placer.add(rand.nextInt(Math.min(inv.getNbOuvrierDispo(),lesZones.get(5).getNbPlaceDispo()))+1);
			}
			else{ // au pire des cas si le joueur ne trouve pas de choix
				placer.add(1);
				placer.add(rand.nextInt(Math.min(inv.getNbOuvrierDispo(),lesZones.get(1).getNbPlaceDispo()))+1);
			}
		}
		else{
			placer.add(1);
			placer.add(rand.nextInt(Math.min(inv.getNbOuvrierDispo(),lesZones.get(1).getNbPlaceDispo()))+1);		
		}
		return placer;
	}
	public int placerOutils(int nbOutils,int nbRessources, Zone zoneChoisi) {
                int OutilChoisie ;
		for (int i=5;i>=0;i--){
			if ((zoneChoisi.getNiveauZone()-(nbRessources % zoneChoisi.getNiveauZone()))+(zoneChoisi.getNiveauZone()*i)<=nbOutils)
			{
				OutilChoisie =(zoneChoisi.getNiveauZone()-(nbRessources % zoneChoisi.getNiveauZone()))+(zoneChoisi.getNiveauZone()*i);
				return OutilChoisie;
			}
		}
		return 0;
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
