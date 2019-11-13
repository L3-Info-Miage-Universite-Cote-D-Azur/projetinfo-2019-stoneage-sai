package stoneage;

import java.util.ArrayList;

/*
 * 	public class Inventaire contient:
 *  Un nombre d'ouvriers, 
 *  Des ressources de différents types,
 *  Methodes qui permettent d'attribuer des valeur et accéder à ces valeurs 
 *  
 */
public class Inventaire {
	public static final int NB_INITIAL_OUVRIERS = 5;
	private int nbOuvrier = 1; //Minimum 1 ouvrier pour pouvoir jouer
	private int nbRessource = 0;
	private int nbOuvrierDispo = 0;
	private int nbBois;
	private int nbArgile;
	private int nbPierre;
	private int nbOr;
	private int nbNourriture;
	private int nbOutils;
	private int score;
    public ArrayList<Zone> listeZonesDispo = new ArrayList<>();
    public ArrayList<Zone> listeZonesJouer = new ArrayList<>();

	public Inventaire() {  //Initialisation d'un Inventaire vide
		setNbOuvrier(NB_INITIAL_OUVRIERS); //Initialisation du nombre d'ouvrier 
		// Initialisation à 0 des ressources et du score
		setNbBois(0);
		setNbArgile(0);
		setNbPierre(0);
		setNbOr(0);
		setNourriture(15);
		setNbRessource(0);
		setNbOutils(0);
		setScore(0);
		resetAvailableWorkers();
        for (int i=1;i <= 6;i++ ){
        	Zone zone= new Zone(i);
        	listeZonesDispo.add(zone);
        } //remplire la liste des zones
	}

    public Inventaire(Inventaire toCopy) {
        setNbOuvrier(toCopy.getNbOuvrier());
        setNbRessource(toCopy.getNbRessource());
        resetAvailableWorkers();
    }

	public void restartListeZoneDispo() {
	    listeZonesDispo = new ArrayList<>();
        for (int i=1;i <= 6;i++ ){
        	Zone zone= new Zone(i);
        	listeZonesDispo.add(zone);
        } //remplire la liste des zones
	}
	public void addAvailableWorkers(int i) {
		nbOuvrierDispo += i;
	}
	public void removeAvailableWorkers(int i) {
		nbOuvrierDispo -= i; }
	public void updateScore(){
		//Pour l'instant le score est seulement calculé par le nombre total de ressource
		setScore(getNbRessource());
	}
	public void resetAvailableWorkers() {
		nbOuvrierDispo = getNbOuvrier();
	}
	public boolean ouvrierDispo() {
		return (nbOuvrierDispo > 0);
	}
	public int getNbOuvrierDispo() {
        return nbOuvrierDispo;
    }

    public void resetInventory(){
		setNbArgile(0);
		setNbBois(0);
		setNbOr(0);
		setNbPierre(0);
		setNbRessource(0);
		setNourriture(15);
		setNbOutils(0);
	}
	/* ****************************************
	   * Getter and setter des champs privés  * 
	   **************************************** */
	
	public int getNbOuvrier() {
		return nbOuvrier;
	}
	
	public void setNbOuvrier(int nbOuvrier) {
		this.nbOuvrier = nbOuvrier;
	}
	
	public int getNbRessource() {
		return nbRessource;
	}
	
	public void setNbRessource(int nbRessource) {
		this.nbRessource = nbRessource;
	}

	public int getNbBois() {
		return nbBois;
	}

	public void setNbBois(int nbBois) {
		this.nbBois = nbBois;
	}
	public int getNbArgile() {
		return nbArgile;
	}
	public void setNbArgile(int nbArgile) {
		this.nbArgile = nbArgile;
	}

	public int getNbPierre() {
		return nbPierre;
	}

	public void setNbPierre(int nbPierre) {
		this.nbPierre = nbPierre;
	}

	public int getNbOr() {
		return nbOr;
	}

	public void setNbOr(int nbOr) {
		this.nbOr = nbOr;
	}
	public int getNourriture() {
		return nbNourriture;
	}
	public void setNourriture(int nbNourriture) {
		this.nbNourriture = nbNourriture;
	}
	public void setNbOutils(int nbOutils){
		this.nbOutils=nbOutils;
	}
	public int getNbOutils(){
		return nbOutils;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
