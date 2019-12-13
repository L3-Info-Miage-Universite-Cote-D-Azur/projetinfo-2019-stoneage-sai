package stoneage;


import java.util.*;

/*
 * 	public class Inventaire contient:
 *  Un nombre d'ouvriers,
 *  Des ressources de différents types,
 *  Methodes qui permettent d'attribuer des valeur et accéder à ces valeurs
 *
 */
public class Inventaire {
	public static final int NB_INITIAL_OUVRIERS = 5;
	public Map<Integer, Ressources> lesRessources = new HashMap<>();
	private int nbOuvrier = 5; //Minimum 1 ouvrier pour pouvoir jouer
	private int nbOuvrierDispo = 0;
	private int nbOutilsDutour;  //nombre d'outil qui va etre minorer dans le tour et revient au nombre d'outil initial en fin de tour
	private int scoreChamp;
	private int nbPaysan;// va etre multiplier par le scoreChamp pour le score final
	private int nbFabricant;// va etre multiplier par le nombre d'outil pour le score final
	private int nbConstructeur;// va etre multiplier par le nombre de tuile batiment  pour le score final
	private int nbChamane; // va etre multiplier par le nombre de figurine pour le score final
	private int nbCarteVert;
	private int nbCarteCiv; //nb =0/1 carte existe 1 seul dans la partie
	private Set<Integer> setTypeCarteCivVerte ; //cette ensemble va contenir les differant type de carte civilisation verte que le joueur va prendre
	private int score;
	public ArrayList<CarteCivilisation> listeDesCarteCivilisation;
	public ArrayList<BuildingTiles> listeDesCarteBatiments;
	public ArrayList<Integer> listeZonesDispo;
	public ArrayList<Boolean> listeZonesJouer;
	public ArrayList<Integer> listeOuvriersPlaces;
	public Inventaire() {  //Initialisation d'un Inventaire vide

		this.lesRessources.put(1,new Ressources(1,"Outil",0));
		this.lesRessources.put(2,new Ressources(2,"Nourriture",12));
		this.lesRessources.put(3,new Ressources(3,"Bois",0));
		this.lesRessources.put(4,new Ressources(4,"Argile",0));
		this.lesRessources.put(5,new Ressources(5,"Pierre",0));
		this.lesRessources.put(6,new Ressources(6,"Or",0));
		setNbOuvrier(NB_INITIAL_OUVRIERS); //Initialisation du nombre d'ouvrier

		// Initialisation à 0 des ressources et du score
		setScore(0);
		setScoreChamp(0);
		resetAvailableWorkers();
		listeDesCarteCivilisation = new ArrayList<>();
		listeDesCarteBatiments=new ArrayList<>();
		listeOuvriersPlaces=new ArrayList<>();
		setTypeCarteCivVerte = new LinkedHashSet<>();
		listeZonesJouer  = new ArrayList<>();
		listeZonesDispo = new ArrayList<>();
		for (int i=1;i < 17;i++ ){
			listeZonesDispo.add(i);
			listeZonesJouer.add(false);
			listeOuvriersPlaces.add(0);
		} //remplire la liste des zones
	}

	public Inventaire(Inventaire toCopy) {
		setNbOuvrier(toCopy.getNbOuvrier());
		resetAvailableWorkers();
	}
	public boolean ouvrierDispo() {
		return (nbOuvrierDispo > 0);
	}

	public void resetInventory(){ //sert pour le teste
		setScore(0);
		setScoreChamp(0);
		resetAvailableWorkers();
	}
	/* ****************************************
	 * Getter and setter and reset  des champs privés  *
	 **************************************** */

	// La methode retourne le nombre de zone que le joueur a choisi durant le tour, elle est renisialiser en fin de tour
	public int getNbZoneJouer(){
		int nb=0;
		for (int i=0;i<16; i++){
			if(listeZonesJouer.get(i)==true)
				nb++;
		}
		return nb;
	}

	public int getNbOuvrierDispo() {
		return nbOuvrierDispo;
	}

	public void resetAvailableWorkers() {
		nbOuvrierDispo = getNbOuvrier();
	}

	public int getNbOuvrier() {
		return nbOuvrier;
	}

	public void setNbOuvrier(int nbOuvrier) {
		this.nbOuvrier = nbOuvrier;
	}

	public int getNbRessource() {
		return getNbBois()+getNbArgile()+getNbPierre()+getNbOr();
	}

	public int getNbBois() {
		return lesRessources.get(3).getValeur();
	}

	public int getNbArgile() {
		return lesRessources.get(4).getValeur();
	}

	public int getNbPierre() {
		return lesRessources.get(5).getValeur();
	}

	public int getNbOr() {
		return lesRessources.get(6).getValeur();
	}

	public int getNourriture()  {
		return lesRessources.get(2).getValeur();
	}

	public int getNbOutils(){
		return lesRessources.get(1).getValeur();
	}

	public void setNourriture(int nb){
		lesRessources.get(2).addvaleur(nb);
	}

	public int getNbOutilsDuTour(){
		return nbOutilsDutour;
	}

	public void setNbOutilsDuTour(int nbOutils){
		this.nbOutilsDutour=nbOutils;
	}

	public int getScoreChamp() {
		return scoreChamp;
	}

	public void resetNbOutilsDuTour(){
		this.nbOutilsDutour= lesRessources.get(1).getValeur();
	}

	public void setScoreChamp(int niveauChamp) {
		scoreChamp=niveauChamp;
	}
	public void addCarteBat(BuildingTiles building){
		listeDesCarteBatiments.add(building);
	}

	public ArrayList<BuildingTiles> getCarteBat() {
		return listeDesCarteBatiments;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	//La methode calcScore calcule et retoune le score finale du joueur
	public int calcScore() {
		return score+getNbRessource()+(nbOuvrier*nbChamane+nbFabricant*getNbOutils()+nbConstructeur*getNbCarteBat()+nbPaysan*scoreChamp)+((int)Math.pow(setTypeCarteCivVerte.size(),2))+(nbCarteVert-setTypeCarteCivVerte.size());
		/* Le score finale contient :
		 * Le niveau de score
		 * le nombre des ressource
		 * Le nombre d'ouvrier * le nombre carte Chamane
		 * Le nombre d'outil * le nombre de carte Fabricant
		 * Le nombre de batiment * le nombre de carte constructeur
			//nb batiment = 1 pour l'instant carte pas de carte batiment a l'iteration 4
		 * Le nombre de carte payson * le niveau de champ
		 * Le nombre de type de carte verte **2
		 * le nombre du reste des carte verte
		 */
	}

	/******************************Les add et remove ************************************/

	public void addAvailableWorkers(int i) {
		nbOuvrierDispo += i;
	}
	public void removeAvailableWorkers(int i) {
		nbOuvrierDispo -= i;
	}
	public void addNbPaysan(int nb) {
		nbPaysan+=nb;
	}
	public void addNbFabricant(int nb) {
		nbFabricant+=nb;
	}
	public void addNbConstructeur(int nb) {
		nbConstructeur+=nb;
	}
	public void addNbChamane(int nb) {
		nbChamane+=nb;
	}
	public int getNbPaysan() {
		return nbPaysan;
	}
	public int  getNbFabricant() {
		return nbFabricant;
	}
	public int  getNbConstructeur() {
		return nbConstructeur;
	}
	public int  getNbChamane() {
		return nbChamane;
	}
	public void addNbCarteVert() {
		nbCarteVert++;
	}
	public int getNbCarteVert() {
		return nbCarteVert;
	}
	public void addNbcarteCiv() {
		//cette carte est la carte joker, il existe qu'une seul durent la partie et elle sert seuelemnt dans le score final
		nbCarteCiv++;
	}
	public int getNbCarteBat(){
		return listeDesCarteBatiments.size();
	}
	public void addTypeCarteCivVerte(int type) {
		setTypeCarteCivVerte.add(type);
	}
}