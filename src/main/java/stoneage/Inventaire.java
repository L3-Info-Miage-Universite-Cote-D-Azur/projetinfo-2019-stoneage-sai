package stoneage;

/*
 * 	public class Inventaire contient:
 *  Un nombre d'ouvriers, 
 *  Des ressources de différents types,
 *  Methodes qui permettent d'attribuer des valeur et accéder à ces valeurs 
 *  
 */

public class Inventaire {
	private static final int NB_INITIAL_OUVRIERS = 5;
	
	private int nbOuvrier = 1; //Minimum 1 ouvrier pour pouvoir jouer
	private int nbRessource = 0;
	private int nbOuvrierDispo = 0;
	
	private int nbBois;
	private int nbArgile;
	private int nbPierre;
	private int nbOr;
	private int score;

	public Inventaire() {  //Initialisation d'un Inventaire vide

		setNbOuvrier(NB_INITIAL_OUVRIERS); //Initialisation du nombre d'ouvrier 

		// Initialisation à 0 des ressources et du score
		setNbBois(0);
		setNbArgile(0);
		setNbPierre(0);
		setNbOr(0);
		setNbRessource(0);
		setScore(0);

		resetAvailableWorkers();
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}