package stoneage;
import java.util.*;
/**
 * 	Cette classe représente l'inventaire d'un joueur.
 * @see #lesRessources
 * 		Liste de toutes les ressources que le joueur possède ainsi que la quantité.
 * @see int#nbOuvrier
 *  	Le nombre d'ouvriers total.
 * @see int#nbOuvrierDispo
 * 		Le nombre d'ouvriers disponibles.
 * @see int#nbOutilsDutour
 * 		Le nombre d'outils qui va être minoré dans un tour.
 * 		Se réinitialise à la fin d'un tour.
 * @see int#scoreChamp
 * 		Le score de champs que le joueur possède.
 * @see int#nbPaysan
 * 		Le nombre de paysan que le joueur possède
 * 		Va être multiplié par le scoreChamp pour le score final
 * @see int#nbFabricant
 * 		Le nombre de fabricant que le joueur possède
 * 		Va etre multiplié par le nombre d'outil pour le score final
 * @see int#nbConstructeur
 * 		Le nombre de constructeur que le joueur possède
 * 		Va être multiplié par le nombre de tuile bâtiment  pour le score final
 * @see int#nbChamane
 * 		Le nombre de chamane que le joueur possède
 * 		Va être multiplié par le nombre de figurine pour le score final
 * @see int#nbCarteVert
 *		Le nombre de carte verte que le joueur possède
 * @see #setTypeCarteCivVerte
 * 		Ensemble contenant les différents types de carte civilisation verte que le joueur va prendre
 * @see int#score
 * 		Score du joueur
 * @see #listeDesCarteCivilisation
 * 		Liste de toutes les cartes civilisations
 * @see #listeDesCarteBatiments
 * 		Liste de toutes les cartes bâtiments
 * @see #listeZonesDispo
 *		Liste des zones disponibles pour pouvoir jouer dessus
 * @see #listeZonesJouer
 * 		Liste des zones jouées:
 * 		true si elle est jouée, false sinon
 * @see #listeOuvriersPlaces
 *		Liste des ouvriers placés sur une zone
 */
public class Inventaire {
	public static final int NB_INITIAL_OUVRIERS = 5;
	public Map<Integer, Ressources> lesRessources = new HashMap<>();
	private int nbOuvrier = 5;
	private int nbOuvrierDispo = 0;
	private int nbOutilsDutour;
	private int scoreChamp;
	private int nbPaysan;
	private int nbFabricant;
	private int nbConstructeur;
	private int nbChamane;
	private int nbCarteVert;
	private int nbCarteCiv; //nb = 0/1 carte existe 1 seul dans la partie
	private Set<Integer> setTypeCarteCivVerte ;
	private int score;
	public ArrayList<CarteCivilisation> listeDesCarteCivilisation;
	public ArrayList<BuildingTiles> listeDesCarteBatiments;
	public ArrayList<Integer> listeZonesDispo;
	public ArrayList<Boolean> listeZonesJouer;
	public ArrayList<Integer> listeOuvriersPlaces;

	/**
	 * Initialisation d'un inventaire vide:
	 * Outils = 0
	 * Nourriture = 12
	 * Bois = 0
	 * Argile = 0
	 * Pierre = 0
	 * Or = 0
	 * Score = 0
	 * ScoreChamp = 0
	 * nbOuvrier = 5
	 * nbOuvrierDispo = 5
	 * listeZoneDispo[i] = i pour i allant de 1 à 17.
	 * listeZonesJouer[i] = false pour i allant de 1 à 17.
	 * 	listeOuvriersPlaces[i] = 0 pour i allant de 1 à 17.
	 */
	public Inventaire() {
		this.lesRessources.put(1,new Ressources(1,"Outil",0));
		this.lesRessources.put(2,new Ressources(2,"Nourriture",12));
		this.lesRessources.put(3,new Ressources(3,"Bois",0));
		this.lesRessources.put(4,new Ressources(4,"Argile",0));
		this.lesRessources.put(5,new Ressources(5,"Pierre",0));
		this.lesRessources.put(6,new Ressources(6,"Or",0));
		setNbOuvrier(NB_INITIAL_OUVRIERS);
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
		}
	}

	/**
	 * Constructeur de la classe Inventaire.
	 * @param toCopy:
	 *              Inventaire à copier.
	 */
	public Inventaire(Inventaire toCopy) {
		setNbOuvrier(toCopy.getNbOuvrier());
		resetAvailableWorkers();
	}

	/**
	 * Retourne si au moin un ouvrier est dispo ou non.
	 * @return boolean:
	 * 		true s'il y a au moins un ouvrier dispo
	 * 		false sinon
	 */
	public boolean ouvrierDispo() {
		return (nbOuvrierDispo > 0);
	}

	/**
	 * Réinitialise le score, le score champs et les ouvriers disponibles
	 */
	public void resetInventory(){
		setScore(0);
		setScoreChamp(0);
		resetAvailableWorkers();
	}

	/**
	 * Nombre de zones choisies par un joueur
	 * @return int:
	 * 		Nombre de zones que le joueur a choisies durant le tour.
	 */
	public int getNbZoneJouer(){
		int nb=0;
		for (int i=0;i<16; i++){
			if(listeZonesJouer.get(i)==true)
				nb++;
		}
		return nb;
	}

	/**
	 * Réinitialise le nombre d'ouvrier disponible
	 */
	public void resetAvailableWorkers() {
		nbOuvrierDispo = getNbOuvrier();
	}

	/**
	 * Retourne le nombre total de ressources.
	 * @return int:
	 * 		Le nombre de ressources en fonctions du nombre de bois, argile, argent et or.
	 */
	public int getNbRessource() {
		return getNbBois()+getNbArgile()+getNbPierre()+getNbOr();
	}

	/**
	 * Calcule le score final.
	 *
	 * @return int:
	 * 	Somme de :
	 * 		Le niveau de score
	 *		le nombre des ressource
	 * 		Le nombre d'ouvrier * le nombre carte Chamane
	 * 		Le nombre d'outil * le nombre de carte Fabricant
	 * 		Le nombre de batiment * le nombre de carte constructeur
	 * 		Le nombre de carte payson * le niveau de champ
	 * 		Le nombre de type de carte verte **2
	 * 		le nombre du reste des carte verte
	 */
	public int calcScore() {
		return score+getNbRessource()+(nbOuvrier*nbChamane+nbFabricant*getNbOutils()+nbConstructeur*getNbCarteBat()+nbPaysan*scoreChamp)+((int)Math.pow(setTypeCarteCivVerte.size(),2))+(nbCarteVert-setTypeCarteCivVerte.size());

	}

	public int getNbOuvrierDispo() {
		return nbOuvrierDispo;
	}
	public int getNbOuvrier() {
		return nbOuvrier;
	}
	public void setNbOuvrier(int nbOuvrier) {
		this.nbOuvrier = nbOuvrier;
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
		lesRessources.put(2,new Ressources(2,"Nourriture",nb));
	}
	public void addNourriture(int nb){
		lesRessources.get(2).addvaleur(nb);;
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