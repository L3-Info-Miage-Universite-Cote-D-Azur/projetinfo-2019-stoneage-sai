package stoneage;



import java.util.ArrayList;
import java.util.Collections;
/**
 *Cette Class est le moteur du jeu,
 * Elle contient les liste des joueur qui vont participer a la partie ( entre 2 et 4 joueurs),
 * Elle contient les inventaire de chaque joueur,
 * elle contient aussi les carte batiment et les carte civilisation qui vont etre melanger au debut de chaque tour
 * Cette class gére les tours, l'ordre des  joueures et termine le jeu lorsque les condition sont atteinte
 * elles decide enfin de qui gagne la partie ou affiche s'il y a egalité.
 *
 **/
public class StoneAge {
	Partie partie = new Partie(true); //nombre de joueur choisie est 4 le nombre de joueur minimal est 1
	private final JoueurIA joueurIA = new JoueurIA("O",1);
	private final JoueurBot2 joueurBot = new JoueurBot2("S",2);
	static  ArrayList<Joueurs> listeDesJoueurs =new ArrayList<>(); //une liste qui va contenir tous les joueurs de la partie
	static  ArrayList<Inventaire> listeDesInventaires=new ArrayList<>() ; //une liste qui va contenir d=toues les inventaire de la partie
	private int nbJoueurs;  // A part le joueur IA
	private static int nbJoueurTotal;
	public Zone zone;
	boolean stat;


	/****** Choisir un Nombre de Joueure Pour commencer une Partie ( entre 2 et 4 )******/
	/*Pour les statistique il se peut que x joueur arrive en tete avec le meme score, alors les x joueur gagne la partie.
	 * C'est pour cela que le total des victoire des joueur dans les statistiques peut depasser 500.
	 * Mais il y a bien 500 parties joués.
	 */


	public static final void main(String [] args) {

		/****** Choisir "unePartie(nbJoueur)" pour lancer une seule partie.
		 * **** Choisir "partie500Stat(nbJoueur)" pour lancer 500 partie avec les statistique.
		 * **** nbJoueur = Le nombre de joueur compris entre 2 et 4. ******/

		//unePartie(4);
		partie500Stat(4);
	}
	
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
	    
	public void jouer(int p){
		if (stat == false || p == 0) {
	        System.out.println("***************** Debut de la Partie *****************\n");
	        System.out.println("Joueur 1 : JoueurIA, un joueur inteligent qui utilise une strategie de jeu gagante.");
	        System.out.println("Joueur 2 : JoueurBot2, un joueur inteligent qui utilise une  seconde strategie de jeu gagante. Elle est differante du celle du JouaurIA.");
	        for (int i=3 ; i<=nbJoueurs+1;i++){
	            System.out.println("Joueur "+(i)+" : JoueurNormal, un joueur normal qui fait des choix au hazard pour toute les methodes.");
	        } 
		}

        int nbDeTour=1;
        while ( partie.getNbCarteDispo()>=(nbJoueurTotal) && nbDeTour<50 && partie.getNbBatiments()>=(nbJoueurTotal) ){
        	// Nombre de partie ay max est 50 sinon le jeu s'arrete lorsqu'il ya plus de carte Civ        
            if (!stat) System.out.println("\n**** Debut du Tour N° "+ (nbDeTour) +" ****");
            unTour();
            if (!stat) System.out.println("**** Fin du Tour N° "+ (nbDeTour) +" ****\n");
            nbDeTour++;
        }
        if (!stat) System.out.println("***************** fin de la Partie *****************");	
        if (stat == false) {
        	gagner();
        }
	}	
	
	
	
    private void unTour(){
    	for (int i=0 ; i<=nbJoueurs;i++){
    		listeDesInventaires.get(i).resetAvailableWorkers(); //remettre a jour le nombre d'ouvrier disponnible
    	}
    	if (!stat) System.out.println("**** Phase de placement ****");
		boolean placer=false;
    	for (int i=0 ; i<=nbJoueurs;i++)
    	{
    		if ( listeDesInventaires.get(i).ouvrierDispo()==true &&  listeDesJoueurs.get(i).pouvoirZone(partie.getLesZones(),listeDesInventaires.get(i)).size() >0) {
    			placer=true;
    		}
    	}
		while (placer) {	
    		for (int i=0 ; i<=nbJoueurs;i++){
    			if (listeDesInventaires.get(i).getNbOuvrierDispo() != 0&&  listeDesJoueurs.get(i).pouvoirZone(partie.getLesZones(),listeDesInventaires.get(i)).size() >0){
    				partie.phasePlacement( listeDesInventaires.get(i), listeDesJoueurs.get(i));                               
    			}
    			else {
    	    		for (int j=0 ; j<=nbJoueurs;j++){
    	    			if (listeDesInventaires.get(j).getNbOuvrierDispo() != 0 &&listeDesInventaires.get(i).getNbOuvrierDispo() != 0&&  listeDesJoueurs.get(i).pouvoirZone(partie.getLesZones(),listeDesInventaires.get(i)).size() >0){
    	    				partie.phasePlacement( listeDesInventaires.get(i), listeDesJoueurs.get(i));
    	    				partie.phasePlacement( listeDesInventaires.get(j), listeDesJoueurs.get(j));
    	    			}
    	    			else {
    	    	    		for (int k=0 ; k<=nbJoueurs;k++){
    	    	    			if (listeDesInventaires.get(k).getNbOuvrierDispo() != 0 && listeDesInventaires.get(j).getNbOuvrierDispo() != 0 &&listeDesInventaires.get(i).getNbOuvrierDispo() != 0&&  listeDesJoueurs.get(i).pouvoirZone(partie.getLesZones(),listeDesInventaires.get(i)).size() >0){
    	    	    				partie.phasePlacement( listeDesInventaires.get(i), listeDesJoueurs.get(i));
    	    	    				partie.phasePlacement( listeDesInventaires.get(j), listeDesJoueurs.get(j));
    	    	    				partie.phasePlacement( listeDesInventaires.get(k), listeDesJoueurs.get(k));
    	    	    			}
    	    	    			else {
    	    	    	    		for (int l=0 ; l<=nbJoueurs;l++){
    	    	    	    			if (listeDesInventaires.get(l).getNbOuvrierDispo() != 0 &&  listeDesJoueurs.get(i).pouvoirZone(partie.getLesZones(),listeDesInventaires.get(i)).size() >0&& listeDesInventaires.get(k).getNbOuvrierDispo() != 0 && listeDesInventaires.get(j).getNbOuvrierDispo() != 0 &&listeDesInventaires.get(i).getNbOuvrierDispo() != 0){
    	    	    	    				partie.phasePlacement( listeDesInventaires.get(i), listeDesJoueurs.get(i));
    	    	    	    				partie.phasePlacement( listeDesInventaires.get(j), listeDesJoueurs.get(j));
    	    	    	    				partie.phasePlacement( listeDesInventaires.get(k), listeDesJoueurs.get(k));
    	    	    	    				partie.phasePlacement( listeDesInventaires.get(l), listeDesJoueurs.get(l));
                                                }
    	    	    	    		}
    	    	    			}
    	    	    		}
    	    			}
    	    		}
	
    			}
    		}
    		placer=false;
        	for (int i=0 ; i<=nbJoueurs;i++)
        	{
				if ( listeDesInventaires.get(i).ouvrierDispo()==true &&  listeDesJoueurs.get(i).pouvoirZone(partie.getLesZones(),listeDesInventaires.get(i)).size() >0) {
        			placer=true;
        		}
        	}
    	}
		if (!stat) System.out.println("**** Phase de résolution des ouvriers **** \n");		
		boolean recuperer=false;
    	for (int i=0 ; i<=nbJoueurs;i++)
    	{
    		if ( (listeDesInventaires.get(i).getNbZoneJouer() > 0) ) {
    			recuperer=true;
    		}
    	}
		while (recuperer) {			
			for (int i=0 ; i<=nbJoueurs;i++){
    			if (listeDesInventaires.get(i).getNbZoneJouer()  != 0){
    				partie.phaseAction( listeDesInventaires.get(i), listeDesJoueurs.get(i));
    			}
    			else {
    	    		for (int j=0 ; j<=nbJoueurs;j++){
    	    			if (listeDesInventaires.get(j).getNbZoneJouer()!= 0 &&listeDesInventaires.get(i).getNbZoneJouer() != 0){
    	    				partie.phaseAction( listeDesInventaires.get(i), listeDesJoueurs.get(i));
    	    				partie.phaseAction( listeDesInventaires.get(j), listeDesJoueurs.get(j));
    	    			}
	
    	    			else {
    	    	    		for (int k=0 ; k<=nbJoueurs;k++){
    	    	    			if (listeDesInventaires.get(k).getNbZoneJouer() != 0 && listeDesInventaires.get(j).getNbZoneJouer()!= 0 &&listeDesInventaires.get(i).getNbZoneJouer() != 0){
    	    	    				partie.phaseAction( listeDesInventaires.get(i), listeDesJoueurs.get(i));
    	    	    				partie.phaseAction( listeDesInventaires.get(j), listeDesJoueurs.get(j));
    	    	    				partie.phaseAction( listeDesInventaires.get(k), listeDesJoueurs.get(k));
    	    	    			}
    	    	    			else {
    	    	    	    		for (int l=0 ; l<=nbJoueurs;l++){
    	    	    	    			if (listeDesInventaires.get(l).getNbZoneJouer() != 0 && listeDesInventaires.get(k).getNbZoneJouer() != 0 && listeDesInventaires.get(j).getNbZoneJouer() != 0 &&listeDesInventaires.get(i).getNbOuvrierDispo() != 0){
    	    	    	    				partie.phaseAction( listeDesInventaires.get(i), listeDesJoueurs.get(i));
    	    	    	    				partie.phaseAction( listeDesInventaires.get(j), listeDesJoueurs.get(j));
    	    	    	    				partie.phaseAction( listeDesInventaires.get(k), listeDesJoueurs.get(k));
    	    	    	    				partie.phaseAction( listeDesInventaires.get(l), listeDesJoueurs.get(l));
    	    	    	    			}
    	    	    	    		}
    	    	    			}
    	    	    		}
    	    			}
    	    		}
    			}
			}
			recuperer=false;
	    	for (int i=0 ; i<=nbJoueurs;i++)
	    	{
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
				System.out.println("Nombre de carte Civilisation Constructeur : " + listeDesInventaires.get(i).getNbConstructeur() +
						" |  Fabricant  : " + listeDesInventaires.get(i).getNbFabricant() +
						" | Paysan  : " + listeDesInventaires.get(i).getNbPaysan() +
						" |  Chamane  : " + listeDesInventaires.get(i).getNbChamane());
				System.out.println("Niveau agriculture : " +listeDesInventaires.get(i).getScoreChamp() );
				System.out.println("Nombre de carte Civilisation Verte  : " + listeDesInventaires.get(i).getNbCarteVert());
				System.out.println("Nombre de carte Civilisation Batiment   : " + listeDesInventaires.get(i).getNbCarteBat());
	    		System.out.println("Le Score final  du joueur ** "+ (numJ) +" ** : " +listeDesInventaires.get(i).calcScore() + "\n");
	                
	    		if ( listeDesInventaires.get(i).calcScore()>ScoreGagnant){
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
				System.out.println("Les Joueurs ont des scores egaux! Il n'y a pas de gagnant.");// nous permet de savoir si les 4 joueurs ont des score egaux
	    	} 
	    	else if (nbScoreEgaux==3) {
                 System.out.println("Les joueurs ** " +(joueurGagnant.get(0))+" ** et ** "+(joueurGagnant.get(1))+" ** et ** "+(joueurGagnant.get(2))+" ** gagnent.");
            } 
	    	else if (nbScoreEgaux==2) {
                System.out.println("Les joueurs ** " +(joueurGagnant.get(0))+" ** et ** "+(joueurGagnant.get(1))+" ** gagnent.");
           } 
	    	else if  (nbScoreEgaux==1){
	    		System.out.println("Le joueur *** "+Gagnant+" *** emporte la partie avec : " + ScoreGagnant + " Points");
	    	}  
	    	else {
	    		System.out.println("Le score des joueurs est negative! Ils ont tous perdu. ");

	    	}
	 }
	 
	 
	 
	 public void calculStat(int[][] tabStatistique) {
		 ArrayList<Integer> listScore= new ArrayList<>();
		 for (int i=0 ; i<=nbJoueurs;i++){
		 	listScore.add(listeDesInventaires.get(i).calcScore());
		 }
		 int Gagnant=0;
	     int ScoreGagnant=0;
		 for (int i = 0; i <= nbJoueurs; i++) {
			    int numJ=listeDesJoueurs.get(i).getNum();
			    tabStatistique[numJ-1][1] += listeDesInventaires.get(i).calcScore();
			    tabStatistique[numJ-1][2] += listeDesInventaires.get(i).getNbOuvrier();
			    tabStatistique[numJ-1][3] += listeDesInventaires.get(i).getNbBois();
				tabStatistique[numJ-1][4] += listeDesInventaires.get(i).getNbArgile();
				tabStatistique[numJ-1][5] += listeDesInventaires.get(i).getNbPierre();
				tabStatistique[numJ-1][6] += listeDesInventaires.get(i).getNbOr();
				tabStatistique[numJ-1][7] += listeDesInventaires.get(i).getNourriture();
				tabStatistique[numJ-1][8] += listeDesInventaires.get(i).getScoreChamp();
				tabStatistique[numJ-1][9] += listeDesInventaires.get(i).getNbConstructeur();
			    tabStatistique[numJ-1][10] += listeDesInventaires.get(i).getNbFabricant();
				tabStatistique[numJ-1][11] += listeDesInventaires.get(i).getNbPaysan();
				tabStatistique[numJ-1][12] += listeDesInventaires.get(i).getNbChamane();
				tabStatistique[numJ-1][13] += listeDesInventaires.get(i).getNbCarteVert();
				tabStatistique[numJ-1][14] += listeDesInventaires.get(i).getNbCarteBat();
	    		if ( listeDesInventaires.get(i).calcScore()>ScoreGagnant){
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
			 System.out.println("Nombre de carte Civilisation Verte " + (tabStatistique[i][13]) / 500);
			 System.out.println("Nombre de carte Civilisation Batiment " + (tabStatistique[i][14]) / 500);
			 System.out.println("Le Score moyen  du joueur ** " + (i+1) + " ** : " + (tabStatistique[i][1]) / 500 + " points");
			 System.out.println("\nAu total le joueur ** " + (i+1) + " ** a remporter : " + tabStatistique[i][0] + " parties sur 500\n");
		 }
	 }
	 
	 public static void unePartie(int nbJoueur) {
		 StoneAge stoneAge = new StoneAge(nbJoueur, false);
		 stoneAge.jouer(0);
	 }
	 
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
	 
	 
	public static int getNbJoueurTotal() {
		return nbJoueurTotal;
	}
	
	public boolean getStat() {
		return stat;
	}
}
