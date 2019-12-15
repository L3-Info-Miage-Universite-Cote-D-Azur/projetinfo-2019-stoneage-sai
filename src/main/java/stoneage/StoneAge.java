package stoneage;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Cette classe est le moteur du jeu.
 * Elle gère:
 * @see #unTour():
 * 		- L'ordre des joueurs.
 * 		- Les différentes phases du jeu
 * @see #gagner() :
 * 		- Le gagnant de la partie ou s'il y a égalité entre des joueurs.
 *		- La fin de partie : conditions de fin de partie atteintes ou non.
 * 		- L'affichage des scores.

 * Elle contient:
 * @see #listeDesJoueurs :
 * 		- Les liste des joueur qui vont participer a la partie (entre 2 et 4 joueurs)
 * @see #listeDesInventaires :
 * 		- Les inventaire de chaque joueur
 *
 */
public class StoneAge {
	Partie partie = new Partie(true); // Le nombre de joueur choisit est 4. Le nombre de joueur minimal est 1
	private final JoueurIA joueurIA = new JoueurIA("O",1);
	private final JoueurBot2 joueurBot = new JoueurBot2("S",2);
	static ArrayList<Joueurs> listeDesJoueurs =new ArrayList<>(); // Une liste qui va contenir tous les joueurs de la partie.
	static ArrayList<Inventaire> listeDesInventaires=new ArrayList<>() ; // Une liste qui va contenir tous les inventaires de la partie.
	private int nbJoueurs;  // A part le joueur IA
	private static int nbJoueurTotal;
	public Zone zone;
	boolean stat;


	/**
	 * Constructeur de la classe StoneAge
	 * @param nbJ :
	 *            Nombre de joueurs dans la partie
	 *            De 2 à 4 joueurs
	 * @param statistique :
	 *                       Si on veut les statistiques de la partie
	 *                       on initialise le paramètre à true sinon false
	 */
	public StoneAge(int nbJ, boolean statistique){
		 Partie partie = new Partie(statistique);
		 stat = statistique;
		 nbJoueurTotal = nbJ;
		 listeDesInventaires=new ArrayList<>() ;
		 listeDesJoueurs =new ArrayList<>();
		 //On choisit le nombre des joueures dans cette partie
		 this.nbJoueurs=nbJ-1;
		 listeDesJoueurs.add(joueurIA);
		 listeDesJoueurs.add(joueurBot);
		 listeDesInventaires.add(new Inventaire());
		 listeDesInventaires.add(new Inventaire());
		 // ajoute en premier le joueurIA et Bot2
		 // puis les autres joueurs normaux
		 for (int i=0;i < nbJoueurs-1;i++){
		 	listeDesJoueurs.add(new Joueur("J"+i,i+3));
		 	listeDesInventaires.add(new Inventaire());
		 }
	}

	/**
	 * Lance une partie.
	 * @param p:
	 *         Numero de la partie jouer
	 *         Si p == 0 : Affiche le début de partie avec les joueurs.
	 */
	public void jouer(int p){
		if (!stat || p == 0) {
	        System.out.println("***************** Debut de la Partie *****************\n");
	        System.out.println("Joueur 1 : JoueurIA, un joueur intelligent qui utilise une stratégie de jeu gagante.");
	        System.out.println("Joueur 2 : JoueurBot2, un joueur intelligent qui utilise une seconde stratégie de jeu gagante. Elle est différente du celle du JoueurIA.");
	        for (int i=3 ; i<=nbJoueurs+1;i++){
	            System.out.println("Joueur "+(i)+" : JoueurNormal, un joueur normal qui fait des choix au hazard pour toutes les méthodes.");
	        } 
		}

        int nbDeTour=1;
        while (partie.getNbCarteDispo()>=(nbJoueurTotal) && nbDeTour<50 && partie.getNbBatiments()>=(nbJoueurTotal) ){
        	// Nombre de partie au max est 50 sinon le jeu s'arrete lorsqu'il ya plus de carte Civ
            if (!stat) System.out.println("\n**** Début du Tour N° "+ (nbDeTour) +" ****");
            unTour();
            if (!stat) System.out.println("**** Fin du Tour N° "+ (nbDeTour) +" ****\n");
            nbDeTour++;
        }
        if (!stat) System.out.println("***************** Fin de la Partie *****************");
        if (!stat) {
        	gagner();
        }
	}

	/**
	 * Placement des ouvriers.
	 * @see Partie#phasePlacement(Inventaire, Joueurs);
	 *
	 * Action des ouvriers.
	 * @see Partie#phaseAction(Inventaire, Joueurs);
	 *
	 * Nourrir les ouvriers.
	 * @see Partie#phaseNourrir(Inventaire, Joueurs);
	 *
	 * Changement de l'ordre des joueurs
	 */
	public void unTour(){
		boolean placer=false;
		boolean recuperer=false;

		for (int i=0 ; i<=nbJoueurs;i++){
			listeDesInventaires.get(i).resetAvailableWorkers(); //remettre a jour le nombre d'ouvrier disponible
		}
		if (!stat) System.out.println("**** Phase de placement ****");
		for (int i=0 ; i<=nbJoueurs;i++) {
			if (listeDesInventaires.get(i).ouvrierDispo() &&  listeDesJoueurs.get(i).pouvoirZone(partie.getLesZones(),listeDesInventaires.get(i)).size() >0) {
				placer=true;
			}
		}

		while (placer) {
			for(Joueurs joueur : listeDesJoueurs){
				int index = listeDesJoueurs.indexOf(joueur);
				if (listeDesInventaires.get(index).ouvrierDispo() && joueur.pouvoirZone(partie.getLesZones(),listeDesInventaires.get(index)).size() >0){
					partie.phasePlacement(listeDesInventaires.get(index), joueur);
				}
			}
			placer=false;
			for (int i=0 ; i<=nbJoueurs;i++) {
				if (listeDesInventaires.get(i).ouvrierDispo() &&  listeDesJoueurs.get(i).pouvoirZone(partie.getLesZones(),listeDesInventaires.get(i)).size() >0) {
					placer=true;
				}
			}
		}

		if (!stat) System.out.println("**** Phase de résolution des ouvriers **** \n");
    	for (int i=0 ; i<=nbJoueurs;i++) {
    		if ( (listeDesInventaires.get(i).getNbZoneJouer() > 0) ) {
    			recuperer=true;
    		}
    	}

		while (recuperer) {
			for(Joueurs joueur : listeDesJoueurs){
				int index = listeDesJoueurs.indexOf(joueur);
				if (listeDesInventaires.get(index).getNbZoneJouer()!= 0){
					partie.phaseAction(listeDesInventaires.get(index), joueur);
				}
			}
			recuperer=false;
	    	for (int i=0 ; i<=nbJoueurs;i++) {
				listeDesInventaires.get(i).resetNbOutilsDuTour(); // chaque joeuur recupere le nombre d'outil qu'il avait
	    		if ( (listeDesInventaires.get(i).getNbZoneJouer() > 0) ) {
	    			recuperer=true;
	    		}
	    	}
		}

		if (!stat) System.out.println("**** Phase Nourrir les ouvriers ****"+ "\n");
		for (int i=0 ; i<=nbJoueurs;i++){
			partie.phaseNourrir( listeDesInventaires.get(i), listeDesJoueurs.get(i));

		}
        
		if (!stat) {
	        for (int i=0 ; i<=nbJoueurs;i++) {
				int numJ=listeDesJoueurs.get(i).getNum();
				System.out.println("**** Inventaire du joueur "+numJ+" ****" );
				System.out.println("Bois  : " + listeDesInventaires.get(i).getNbBois()+
						" | Argile : " +listeDesInventaires.get(i).getNbArgile() +
						" | Pierre : " + listeDesInventaires.get(i).getNbPierre() +
						" | Or : " + listeDesInventaires.get(i).getNbOr() +
						" | Nombre d'ouvriers : " + listeDesInventaires.get(i).getNbOuvrier()+
						" | Niveau agriculture : " + listeDesInventaires.get(i).getScoreChamp());
				System.out.println("Nombre de nourriture : " +listeDesInventaires.get(i).getNourriture() +
						" | Nombre de ressources : " +listeDesInventaires.get(i).getNbRessource() +
						" | Nombre d'outil  : " + listeDesInventaires.get(i).getNbOutils()+ "\n");
	
			}
		}
		//changer l'ordre des joueurs et des inventaire 1234 ==> 2341 ==> 3412....
		for (int i=0 ; i<=nbJoueurs-1;i++) {
				Collections.swap(listeDesInventaires,i,i+1);
				Collections.swap(listeDesJoueurs,i,i+1);
		}
    }

	/**
	 * Affiche l'inventaire des joueurs.
	 * Affiche le score de chaque joueur.
	 * Affiche le gagnant et son score
	 * Affiche si plusieurs joueurs sont gagnants (égalité)
	 *
	 */
	public void gagner() {
	        ArrayList<Integer> listScore= new ArrayList<>();
			for (int i=0 ; i<=nbJoueurs;i++){
				listScore.add(listeDesInventaires.get(i).calcScore());
			}
	    	int Gagnant=0;
	    	int ScoreGagnant=0;
	    	for (int i=0 ; i<=nbJoueurs;i++){ 
				int numJ=listeDesJoueurs.get(i).getNum();
				System.out.println("********* Le  joueur "+ (numJ) +" *********");
				System.out.println("Nombre d'ouvriers  : " +listeDesInventaires.get(i).getNbOuvrier()+
						" | Bois : " +listeDesInventaires.get(i).getNbBois() +
						" | Argile  : " +listeDesInventaires.get(i).getNbArgile() +
						" | Pierre  : " +listeDesInventaires.get(i).getNbPierre() +
						" | Or  : " +listeDesInventaires.get(i).getNbOr() +
						" | Outil  : " +listeDesInventaires.get(i).getNbOutils() +
						" | Nourriture  : " +listeDesInventaires.get(i).getNourriture());
				System.out.println("Nombre de cartes Civilisation Constructeur : " + listeDesInventaires.get(i).getNbConstructeur() +
						" | Fabricant  : " + listeDesInventaires.get(i).getNbFabricant() +
						" | Paysan  : " + listeDesInventaires.get(i).getNbPaysan() +
						" | Chamane  : " + listeDesInventaires.get(i).getNbChamane());
				System.out.println("Niveau agriculture : " +listeDesInventaires.get(i).getScoreChamp() );
				System.out.println("Nombre de cartes Civilisation Verte  : " + listeDesInventaires.get(i).getNbCarteVert());
				System.out.println("Nombre de cartes Batiment   : " + listeDesInventaires.get(i).getNbCarteBat());
	    		System.out.println("Le Score final du joueur ** "+ (numJ) +" ** : " +listeDesInventaires.get(i).calcScore() + "\n");
	                
	    		if (listeDesInventaires.get(i).calcScore()>ScoreGagnant){
	    			Gagnant=numJ; //si le score du joueur corant est le plus elever alors on change les deux variables.
	    			ScoreGagnant=listeDesInventaires.get(i).calcScore();
	    		}       
	        }        
    		int nbScoreEgaux=0;
    	    ArrayList<Integer>  joueurGagnant=new ArrayList();
	    	for (int i=0 ; i<=nbJoueurs;i++){
	    		if (listeDesInventaires.get(i).calcScore()== ScoreGagnant) {
	    			nbScoreEgaux++;
	    			joueurGagnant.add(listeDesJoueurs.get(i).getNum());
	    		}
	    	}
	    	if (nbScoreEgaux==nbJoueurs+1) {
				System.out.println("Les joueurs ont des scores égaux! Il n'y a pas de gagnant.");// nous permet de savoir si les 4 joueurs ont des score egaux
	    	} 
	    	else if (nbScoreEgaux==3) {
                 System.out.println("Les joueurs ** " +(joueurGagnant.get(0))+" ** et ** "+(joueurGagnant.get(1))+" ** et ** "+(joueurGagnant.get(2))+" ** gagnent.");
            } 
	    	else if (nbScoreEgaux==2) {
                System.out.println("Les joueurs ** " +(joueurGagnant.get(0))+" ** et ** "+(joueurGagnant.get(1))+" ** gagnent.");
           } 
	    	else if  (nbScoreEgaux==1){
	    		System.out.println("Le joueur *** "+Gagnant+" *** remporte la partie avec : " + ScoreGagnant + " Points");
	    	}  
	    	else {
	    		System.out.println("Le score des joueurs est négatif! Ils ont tous perdu. ");

	    	}
	 }

	/**
	 * Calcule les statistiques de partie.
	 * @param tabStatistique :
	 *                       Tableau d'entiers à 2 dimensions : [x][y]
	 *                       x correspond au joueurs
	 *                       y == 0:
	 *                       	- Le nombre de fois que le joueur a gagné.
	 *                       y allant de 1 à 14 correspond aux items dans l'inventaire:
	 *                       	- Score (Score total, scoreChamp)
	 *                       	- Ouvriers, Constructeurs, Fabricant, Paysan, Chamane
	 *                       	- Ressources
	 *                       	- Cartes
	 */
	public void calculStat(int[][] tabStatistique) {
		 ArrayList<Integer> listScore= new ArrayList<>();
		 for (int i=0 ; i<=nbJoueurs;i++){
		 	listScore.add(listeDesInventaires.get(i).calcScore());
		 }
		 int Gagnant=0;
	     int ScoreGagnant=0;
		 for (int i = 0; i <= nbJoueurs; i++) {
			 int numJ = listeDesJoueurs.get(i).getNum();
			 tabStatistique[numJ - 1][1] += listeDesInventaires.get(i).calcScore();
			 tabStatistique[numJ - 1][2] += listeDesInventaires.get(i).getNbOuvrier();
			 tabStatistique[numJ - 1][3] += listeDesInventaires.get(i).getNbBois();
			 tabStatistique[numJ - 1][4] += listeDesInventaires.get(i).getNbArgile();
			 tabStatistique[numJ - 1][5] += listeDesInventaires.get(i).getNbPierre();
			 tabStatistique[numJ - 1][6] += listeDesInventaires.get(i).getNbOr();
			 tabStatistique[numJ - 1][7] += listeDesInventaires.get(i).getNourriture();
			 tabStatistique[numJ - 1][8] += listeDesInventaires.get(i).getScoreChamp();
			 tabStatistique[numJ - 1][9] += listeDesInventaires.get(i).getNbConstructeur();
			 tabStatistique[numJ - 1][10] += listeDesInventaires.get(i).getNbFabricant();
			 tabStatistique[numJ - 1][11] += listeDesInventaires.get(i).getNbPaysan();
			 tabStatistique[numJ - 1][12] += listeDesInventaires.get(i).getNbChamane();
			 tabStatistique[numJ - 1][13] += listeDesInventaires.get(i).getNbCarteVert();
			 tabStatistique[numJ - 1][14] += listeDesInventaires.get(i).getNbCarteBat();
			 if (listeDesInventaires.get(i).calcScore() > ScoreGagnant) {
				 Gagnant = numJ; //si le score du joueur courant est le plus eleve alors on change les deux variables.
				 ScoreGagnant = listeDesInventaires.get(i).calcScore();
			 }
		 }
		 int nbScoreEgaux=0;
    	 ArrayList<Integer>  joueurGagnant=new ArrayList();
    	 for (int i=0 ; i<=nbJoueurs;i++){
	    	if (listeDesInventaires.get(i).calcScore()== ScoreGagnant) {
	    		nbScoreEgaux++;
	    		joueurGagnant.add(listeDesJoueurs.get(i).getNum());
	    	}
    	 }
    	 switch (nbScoreEgaux) {
    	 	case 1 :
    	 		tabStatistique[Gagnant - 1][0] += 1;
    	 		break;
    	 	case 2 :
			 	tabStatistique[joueurGagnant.get(0)-1][0] += 1;
			 	tabStatistique[joueurGagnant.get(1)-1][0] += 1;
			 	break;

			case 3 :
				tabStatistique[joueurGagnant.get(0)-1][0] += 1;
				tabStatistique[joueurGagnant.get(1)-1][0] += 1;
				tabStatistique[joueurGagnant.get(2)-1][0] += 1;
				break;
			}
	 }

	/**
	 * Affiche les statistiques des joueurs.
	 * @param tabStatistique :
	 *                       Tableau d'entiers à 2 dimensions : [x][y]
	 *                       x correspond au joueurs
	 *                       y == 0:
	 *                       	- Le nombre de fois que le joueur a gagné.
	 *                       y allant de 1 à 14 correspond aux items dans l'inventaire:
	 *                       	- Score (Score total, scoreChamp)
	 *                       	- Ouvriers, Constructeurs, Fabricant, Paysan, Chamane
	 *                       	- Ressources
	 *                       	- Cartes
	 */
	public void afficheStat(int[][] tabStatistique) {
		for (int i = 0; i <= nbJoueurs; i++) {
			System.out.println("\n**** Statistique du joueur " + (i+1) + " ****");
			System.out.println("\nMoyenne sur 500 parties du joueur " + (i+1) + " : ");
			System.out.print("Nombre d'ouvriers : " + (tabStatistique[i][2]) / 500);
			System.out.print(" | Bois : " + (tabStatistique[i][3]) / 500);
			System.out.print(" | Argiles : " + (tabStatistique[i][4]) / 500);
			System.out.print(" | Pierres : " + (tabStatistique[i][5]) / 500);
			System.out.print(" | Or : " + (tabStatistique[i][6]) / 500);
			System.out.println(" | Nourriture : " + (tabStatistique[i][7]) / 500);
			System.out.print("Nombre de carte Civilisation Constructeur : " + (tabStatistique[i][9]) / 500);
			System.out.print(" | Fabricant : " + (tabStatistique[i][10]) / 500);
			System.out.print(" | Paysan : " + (tabStatistique[i][11]) / 500);
			System.out.println(" | Chamane : " + (tabStatistique[i][12]) / 500);
			System.out.println("Une agriculture de niveau : " + (tabStatistique[i][8]) / 500);
			System.out.println("Nombre de cartes Civilisation Verte " + (tabStatistique[i][13]) / 500);
			System.out.println("Nombre de cartes Batiment " + (tabStatistique[i][14]) / 500);
			System.out.println("Le Score moyen du joueur ** " + (i+1) + " ** : " + (tabStatistique[i][1]) / 500 + " points");
			System.out.println("\nAu total le joueur ** " + (i+1) + " ** a remporté : " + tabStatistique[i][0] + " parties sur 500\n");
		}
	}

	/**
	 * Lance une unique partie sans statistiques
	 * @param nbJoueur:
	 *                   Nombre de joueurs dans la partie.
	 */
	public static void unePartie(int nbJoueur) {
		StoneAge stoneAge = new StoneAge(nbJoueur, false);
		stoneAge.jouer(0);
	}

	/**
	 * Lance 500 parties avec statistiques.
	 * @param nbJoueur:
	 *                  Nombre de joueurs dans la partie.
	 */
	public static void partie500Stat(int nbJoueur) {
		int[][] tab = new int[4][15];
		StoneAge stoneAge;
		stoneAge = new StoneAge(nbJoueur, true);
		for (int p = 0; p < 500; p++) {
			stoneAge = new StoneAge(nbJoueur, true);
			stoneAge.jouer(p);
			stoneAge.calculStat(tab);
		}
		stoneAge.afficheStat(tab);
	}

	/**
	 * Accès au nombre total de joueurs.
	 * @return int : de 1 à 4
	 */
	public static int getNbJoueurTotal() {
		return nbJoueurTotal;
	}

	/**
	 * Savoir si on utilise veut des stats ou non.
	 * @return boolean
	 */
	public boolean getStat() {
		return stat;
	}
	
	/**
	 * Accès au nombre de cartes civilisation restantes.
	 * @return int
	 */
	public int getNbCarteDispo() {
		return partie.getNbCarteDispo();
	}
	
	/**
	 * Accès au nombre de cartes batiment restantes.
	 * @return int
	 */
	public int getNbBatiments() {
		return partie.getNbBatiments();
	}
	
	/**
	 * Accès a la liste des inventaires.
	 * @return ArrayList<Inventaire
	 */
	public static ArrayList<Inventaire> getListeDesInventaires() {
		return listeDesInventaires;
	}
	
	/**
	 * Accès a la liste des joueurs
	 * @return ArrayList<Joueurs>
	 */
	public static ArrayList<Joueurs> getListeDesJoueurs() {
		return listeDesJoueurs;
	}
}
