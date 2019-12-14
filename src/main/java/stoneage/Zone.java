package stoneage;
import java.util.ArrayList;
/**
 *Cette classe contient toutes les zones du plateau de jeu.
 *
 * @see int#nbOuvriersPlacés
 *
 * @see int#niveauZone
 *
 * @see #listeDesDe
 *
 * @see int#nbPlaceZone
 *
 * @see int#nbPlaceDispo
 *
 *
 * Cette class gére les placement des ouvrier dans les zone ainsi que les recuperation des ouvrier avec les gains associé aux Zones
 *
 **/
public class Zone {
	private String nomZone;
	private int nbOuvriersPlacés = 0;
	private int niveauZone ;
	private ArrayList<Integer> listeDesDe;
	private  Dé dé= new Dé();
	private int nbPlaceZone ;
	private int nbPlaceDispo;
	private int gains; //le nombre des gains du joueur
	private String TypeGains; //le nom du gain par exemple bois...
	private String[] TypesGains;
	private int nbJoueur;

	/**
	 * Constructeur de la classe Zone
	 * @param niveau:
	 *              Entier entre 1 et 16
	 *              Correspond au niveau de la Zone
	 * @param nbPlace:
	 *               Correspond au nombre de places disponible de la zone
	 * @param nom:
	 *           Nom de la zone
	 */
	public Zone(int niveau, int nbPlace, String nom) {
		this.niveauZone = niveau;
		this.nomZone=nom;
		this.nbPlaceZone=nbPlace;
		nbPlaceDispo=nbPlaceZone;//au début le nombre de place disponible est égal au nombre place maximum de la zone
	}

	@Override
	public String toString(){
		return nomZone;
	}

	/**
	 * Méthode qui permet de poser des ouvriers sur la zone
	 * @param inventaireJoueur:
	 *                        Inventaire du joueur
	 * @param nbOuvriers:
	 *                  Entier
	 *                  Nombre d'ouvrier
	 *
	 * Quand on place des ouvriers, on les retire d'abord l'inventaire du joueur.
	 * Le nombre de place disponible dans la zone est ensuite diminué de nbOuvriers.
	 * Le nombre de joueur dans la zone augmente aussi de 1.
	 */
	public void placerOuvrier(Inventaire inventaireJoueur,int nbOuvriers){
		if (nbOuvriers>=1 && nbOuvriers <=nbPlaceDispo && nbOuvriers<=inventaireJoueur.getNbOuvrierDispo() ){
			inventaireJoueur.removeAvailableWorkers(nbOuvriers);
			nbOuvriersPlacés+=nbOuvriers;
			nbJoueur++;
			nbPlaceDispo=nbPlaceDispo-nbOuvriers;
		}
	}

	/**
	 *	Lancement de plusieurs dés
	 * @param nbDe:
	 *            Entier
	 *            Nombre de dés que l'on va lancer.
	 * @return : Une liste de nbDe dé.
	 * 			Chaque dé a une valeur entière comprise entre 1 et 6.
	 *
	 */
	public ArrayList<Integer> lancerNbDé(int nbDe){
		ArrayList<Integer> lancement4De=new ArrayList<>();
		for (int i =0; i<nbDe; i++){
			lancement4De.add(dé.Lancer());
		}
		return lancement4De;
	}

	/**
	 * Valeur de tous les dés lancés.
	 * @param nbOuvriersPlacés :
	 *                         Entier
	 *                         Nombre d'ouvriers placés sur la zone
	 * @return Un entier qui correspond à la somme des valeurs de tous les dés lancés.
	 */
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
	
	public void setTabTypeGains(int index,String val){
		this.TypesGains[index]=val;
	}
	public String[] getTabTypeGains(){
		return TypesGains;
	}
	public int getNiveauZone() {
		return niveauZone;
	}
	public int getNbJoueur() {
		return nbJoueur;
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
	}
	public int getGains(){
		return gains;
	}
	public String getTypeGains(){
		return  TypeGains;
	}
	public ArrayList<Integer> getListeDe(){
		return listeDesDe;
	}
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