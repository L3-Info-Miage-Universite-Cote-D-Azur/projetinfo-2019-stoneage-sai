package stoneage;

import java.util.ArrayList;
/**
	*Une public classe Zone qui contient toute les zone du plateau de jeu
 	*parmit eu les carte civilsation et les carte batimet.
 	* Cette class gére les placement des ouvrier dans les zone ainsi que les recuperation des ouvrier avec les gains associé aux Zones
 	*
 **/
public class Zone {
	private String nomZone;
	private int nbOuvriersPlacés = 0;
	public int niveauZone ;
	private ArrayList<Integer> listeDesDe;
	public  Dé dé= new Dé();
	private int nbPlaceZone ;
	private int nbPlaceDispo;
	public int gains; //le nombre des gains du joueur
	public String TypeGains; //le nom du gain par exemple bois...
	public String[] TypesGains;
	public int nbJoueur;




	public Zone(int niveau, int nbPlace, String nom) {
		this.niveauZone = niveau;
		this.nomZone=nom;
		this.nbPlaceZone=nbPlace;
		nbPlaceDispo=nbPlaceZone;//au début le nombre de place disponible = au nombre place max de la zone
	}

	public String toString(){
		return nomZone;
	}

	/*La methode placerOuvrier va permettre de poser un nombre de figurine sur une zone choisi, ainsi le nombre de place
    dans la zone sera crediter et le nombre d'ouvrier poser sera enregistrer  */
	public void placerOuvrier(Inventaire inventaireJoueur,int nbOuvriers){
		if (nbOuvriers>=1 && nbOuvriers <=nbPlaceDispo && nbOuvriers<=inventaireJoueur.getNbOuvrierDispo() ){
			inventaireJoueur.removeAvailableWorkers(nbOuvriers);//pour placer un nbOuvrier il faut les retirer d'abord de l'inventaire du joueur
			nbOuvriersPlacés+=nbOuvriers;
			nbJoueur++;
			nbPlaceDispo=nbPlaceDispo-nbOuvriers; //le nombre de place disponnible dans la zone diminue
			//le nombre d'ouvrier placer dans la zone augmente
		}
	}
	public void setTypeGains(String t){
		this.TypeGains=t;
	}
	public void setGains(int t){
		this.gains=t;
	}

	public int getNbPlaceZone(){
		return nbPlaceZone;
	}
	public int getNbPlaceDispo(){
		return nbPlaceDispo;
	}
	public int getNbOuvriersPlaces(){
		return nbOuvriersPlacés;
	}
	public void setNbPlaceDispo(int nbPlaceDispo) {
		this.nbPlaceDispo=nbPlaceDispo;
	}// le nombre de place disponible par zone

	public int getGains(){
		return gains;
	}
	public String getTypeGains(){
		return  TypeGains;
	}
	public ArrayList<Integer> lancerNbDé(int nbDe){
		//cette methode retourne une liste de de taille donné qui contient des nombre entre 1 et 6
		ArrayList<Integer> lancement4De=new ArrayList<>();
		for (int i =0; i<nbDe; i++){
			lancement4De.add(dé.Lancer());
		}
		return lancement4De;
	}
	public int lancéDeDés(int nbOuvriersPlacés){
		int sommeDés=0;
		int valeurde;
		for (int i = 0; i < nbOuvriersPlacés; i++) {
			valeurde=dé.Lancer();
			listeDesDe.add(valeurde);
			sommeDés+=valeurde;
		}
		return sommeDés ;
	}
	public ArrayList<Integer> getListeDe(){
		return listeDesDe;
	} //Methode qui retourne une liste qui contient les dés lancer a chaque lance de Dé

	public void resetListDesDe(){
		listeDesDe=new ArrayList<>();
	}


	public int gestionRessources(Inventaire inventaireJoueur, Joueurs J){
		inventaireJoueur.resetNbOutilsDuTour();
		int nbRessources= this.lancéDeDés(inventaireJoueur.listeOuvriersPlaces.get(this.niveauZone-1));
		int nbOutilsDuJoueur=inventaireJoueur.getNbOutilsDuTour();
		int outilChoisie;
		// le nombre d'outil que le joueur a choisi d'utiliser
		if (inventaireJoueur.getNbOutilsDuTour()>0){ // s'il a des outils on le laisse choisir le nombre qu'il veut sinon en retourne 0
			outilChoisie=J.placerOutils(nbOutilsDuJoueur,nbRessources,this);
		}
		else {
			outilChoisie=0;
		}
		nbRessources=nbRessources +outilChoisie;
		nbRessources=nbRessources / this.niveauZone;
		inventaireJoueur.setNbOutilsDuTour(inventaireJoueur.getNbOutilsDuTour()-outilChoisie);
		return nbRessources;
	}




	public void recupeRes(ArrayList<CarteCivilisation> listeDesCartes,ArrayList<BuildingTiles> listeDesBatiments,Inventaire inventaireJoueur, Joueurs J) {
		resetListDesDe(); // vider la liste qui contient les dés du joueur precedent ou bien du meme joueur avec une zone precedente
		int nbRessources = gestionRessources(inventaireJoueur, J);
		//recuperer les ressources gagner
		//ajouter les nouveau ressources a l'inventaire du joueur

		if (this.niveauZone == 1) {
			inventaireJoueur.lesRessources.get(this.niveauZone).addvaleur(1);
			gains = 1;
			TypeGains = inventaireJoueur.lesRessources.get(this.niveauZone).getNom();
			//Le nombre maximal d'OUTILS pour chaque joueur est : 12
			//Les joueurs peuvent tous de meme choisir la zone fabrication et ne pas avoir d'outil ensuite

		} else if (this.niveauZone == 7) {
			inventaireJoueur.setScoreChamp(inventaireJoueur.getScoreChamp() + 1);
			gains = 1;
			TypeGains = "Niveau Champ ";
		} else if (this.niveauZone < 7 && this.niveauZone > 1) {
			inventaireJoueur.lesRessources.get(this.niveauZone).addvaleur(nbRessources);
			gains = nbRessources;
			TypeGains = inventaireJoueur.lesRessources.get(this.niveauZone).getNom();
		} else if (this.niveauZone == 16) {
			inventaireJoueur.setNbOuvrier(Math.min(inventaireJoueur.getNbOuvrier() + 1, 10));
			//Le nombre maximal d'ouvrier pour chaque joueur est : 10
			//Les joueurs peuvent tous de meme choisir la Hutte et ne pas avoir d'ouvriers ensuite
			gains = 1;
			TypeGains = "Ouvrier";

		} else if (this.niveauZone > 7 && this.niveauZone < 12) { //POUR LES CARTE CIVILISATION
			int coutCarte = this.niveauZone - 7; // carte 1 vaut 1 / carte 2 vaut 2/ carte 3 vaut 3...
			boolean payer = false;
			CarteCivilisation carte;
			if (this.niveauZone - 8 >= listeDesCartes.size() && listeDesCartes.size() > 0)  {
				carte = listeDesCartes.get(listeDesCartes.size() - 1);

			} else {
				carte = listeDesCartes.get(this.niveauZone - 8);
			}
			// si niveau de zone = 8 alors carte 1 sinon carte 2 sinon...
			/* Paiement de la carte civilisation  */
			int typeCout = J.choixTypeRes(coutCarte, inventaireJoueur, 3, 4, 5, 6);
			if (typeCout != -1) {
				inventaireJoueur.lesRessources.get(typeCout).subvaleur(coutCarte);
				payer = true;
			}
			/* Si le joueur paye la carte (il a assez de resource pour la payer et choisi de la prendre ) elle s'eneleve de a liste
			 *  sinon elle sera rendu a la liste  */
			if (payer == true) {
				carte.recupResCarteCiv(inventaireJoueur, this, J);
				listeDesCartes.remove(carte);
				TypeGains += " avec la carte Civilisation. ";
			} else {
				gains = -1;
			}
		} else if (this.niveauZone > 11 && this.niveauZone < 16) { //POUR LES CARTE Batiment
			BuildingTiles building;
			if (this.niveauZone - 12 >= listeDesBatiments.size()) {
				building = listeDesBatiments.get(listeDesBatiments.size() - 1);
			} else {
				building = listeDesBatiments.get(this.niveauZone - 12);
			}
			boolean pay = J.payerBatiment();
			//le joueur choisi s'il prend la carte ou pas
			/* Si le joueur paye la carte (il a assez de resource pour la payer et choisi de la prendre ) elle demanderCadeau'eneleve de la liste
			 *  sinon elle sera rendu a la liste  */

			if (pay == true) { //le joueur paye la carte(et le decide),on la retire donc de la liste des cartes
				gains = -5;
				building.recupResCarteBat(inventaireJoueur, this, J);
				inventaireJoueur.addCarteBat(building);
				listeDesBatiments.remove(building);
			} else {
				gains = -3;
			}
		}
		inventaireJoueur.addAvailableWorkers(this.getNbOuvriersPlaces());
		//recuperer les ouvriers
		nbJoueur--;
		this.nbOuvriersPlacés -= inventaireJoueur.listeOuvriersPlaces.get(this.niveauZone - 1); //on enleve les figurines du joueur DE LA ZONE
		this.setNbPlaceDispo(this.getNbPlaceZone());//quand on recupere les ouvriers,toutes les places deviennent disponibles.
	}
}
