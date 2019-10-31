package stoneage;

/*
 * 	public class Inventaire contient:
 *  Un nombre d'ouvriers, 
 *  Des ressources de différents types,
 *  Methodes qui permettent d'attribuer des valeur et accéder à ces valeurs 
 *  
 */

public class Inventaire {
	public static final int NB_INITIAL_OUVRIERS = 5;
	
	private int nbOuvrier = 1;
	private int nbRessource = 0;
	private int nbOuvrierDispo = 0;
	
	private int nbBois = 0;
	private int nbArgile = 0;
	private int nbPierre = 0;
	private int nbOr = 0;
	
	
	public Inventaire() {  //Initialisation d'un Inventaire vide
		
		setNbOuvrier(NB_INITIAL_OUVRIERS); //Initialisation du nombre d'ouvrier 
		
		setNbBois(0);		// Initialisation à 0 du nombre de bois 
		setNbArgile(0);		// Initialisation à 0 du nombre d'argile
		setNbPierre(0);		// Initialisation à 0 du nombre de pierre
		setNbOr(0);			// Initialisation à 0 du nombre d'or
		
		// Initialisation du nombre total de ressources en ajoutant les différentes ressources ensemble
		setNbRessource(getNbBois() + getNbArgile() + getNbOr() + getNbPierre());
	}
	
	
	public void addWorkers(int i) {
		nbOuvrierDispo += i;
	}
	
	public void removeWorkers(int i) {
		nbOuvrierDispo -= i;
	}
	
	public boolean ouvrierDispo(int nbOuvrierDispo) {
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
}
