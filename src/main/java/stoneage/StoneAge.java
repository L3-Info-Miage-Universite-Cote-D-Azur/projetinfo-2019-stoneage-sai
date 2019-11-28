package stoneage;

import java.util.ArrayList;
import java.util.Collections;
/*
		*Cette Class est le moteur du jeu,
		* Elle contient les liste des joueur qui vont participer a la partie ( entre 2 et 4 joueurs),
		* Elle contient les inventaire de chaque joueur,
		* elle contient aussi les carte batiment et les carte civilisation qui vont etre melanger au debut de chaque tour
		* Cette class gére les tours, l'ordre des  joueures et termine le jeu lorsque les condition sont atteinte
		* elles decide enfin de qui gagne la partie ou affiche s'il y a egalité.
		*
*/
public class StoneAge {
	Partie partie = new Partie(); //nombre de joueur choisie est 4 le nombre de joueur minimal est 1 
	private final JoueurIA joueurIA = new JoueurIA("O",1);
	private final JoueurBot2 joueurBot = new JoueurBot2("S",2);
	final static  ArrayList<Joueurs> listeDesJoueurs =new ArrayList<>(); //une liste qui va contenir tous les joueurs de la partie
	final static  ArrayList<Inventaire> listeDesInventaires=new ArrayList<>() ; //une liste qui va contenir d=toues les inventaire de la partie
 	private int nbJoueurs;  // A part le joueur IA
	private static int nbJoueurTotal;
 	public Zone zone;
        public BuildingTiles building=new BuildingTiles();
        public CarteCivilisation carte=new CarteCivilisation();
 	
 	/****** Choisir un Nombre de Joueure Pour commencer une Partie ( entre 2 et 4 )******/
	public static final void main(String [] args) {
		StoneAge stoneAge = new StoneAge(4);
		stoneAge.jouer();
	}
	 public StoneAge(int nbJ){
			nbJoueurTotal = nbJ;
	    	//On choisit le nombre des joueures dans cette partie
	    	this.nbJoueurs=nbJ-1;
	    	listeDesJoueurs.add(joueurIA); 
	       	listeDesJoueurs.add(joueurBot);
	    	listeDesInventaires.add(new Inventaire());
	    	listeDesInventaires.add(new Inventaire());
	    	// ajoute en premier le joueurIA et Bot2
	    	//puis les autres joueurs normaux
	    	for (int i=0;i < nbJoueurs-1;i++){
				listeDesJoueurs.add(new Joueur("J"+i,i+3));
	    		listeDesInventaires.add(new Inventaire());
	    	}

	    }
	    
	protected  void jouer(){

        System.out.println("***************** Debut de la Partie *****************\n");
        System.out.println("** Debut de la Partie **\n");
        System.out.println("Joueur 1 : JoueurIA");
        System.out.println("Joueur 2 : JoueurBot2");
        for (int i=3 ; i<=nbJoueurs+1;i++){
            System.out.println("Joueur "+(i)+" : JoueurNormal");
        }
        int nbDeTour=1;
        while ( partie.getNbCarteDispo()>(nbJoueurTotal+5) && nbDeTour<50 && partie.getNbBatiments()>(nbJoueurTotal+5) ){
        	// Nombre de partie ay max est 50 sinon le jeu s'arrete lorsqu'il ya plus de carte Civ        
            System.out.println("\n**** Debut du Tour N° "+ (nbDeTour) +" ****");
            unTour();
            System.out.println("**** Fin du Tour N° "+ (nbDeTour) +" ****\n");
            nbDeTour++;
        }
        System.out.println("***************** fin de la Partie *****************");	
        gagner();
	}	
    protected void unTour(){
    	for (int i=0 ; i<=nbJoueurs;i++){
    		listeDesInventaires.get(i).resetAvailableWorkers(); //remettre a jour le nombre d'ouvrier disponnible
    	}
		System.out.println("**** Phase de placement ****");
		boolean placer=false;
    	for (int i=0 ; i<=nbJoueurs;i++)
    	{
    		if ( listeDesInventaires.get(i).ouvrierDispo()==true) {
    			placer=true;
    		}
    	}
		while (placer) {	
    		for (int i=0 ; i<=nbJoueurs;i++){
    			if (listeDesInventaires.get(i).getNbOuvrierDispo() != 0){
    				partie.phasePlacement( listeDesInventaires.get(i), listeDesJoueurs.get(i));                               
    			}
    			else {
    	    		for (int j=0 ; j<=nbJoueurs;j++){
    	    			if (listeDesInventaires.get(j).getNbOuvrierDispo() != 0 &&listeDesInventaires.get(i).getNbOuvrierDispo() != 0){
    	    				partie.phasePlacement( listeDesInventaires.get(i), listeDesJoueurs.get(i));
    	    				partie.phasePlacement( listeDesInventaires.get(j), listeDesJoueurs.get(j));
    	    			}
    	    			else {
    	    	    		for (int k=0 ; k<=nbJoueurs;k++){
    	    	    			if (listeDesInventaires.get(k).getNbOuvrierDispo() != 0 && listeDesInventaires.get(j).getNbOuvrierDispo() != 0 &&listeDesInventaires.get(i).getNbOuvrierDispo() != 0){
    	    	    				partie.phasePlacement( listeDesInventaires.get(i), listeDesJoueurs.get(i));
    	    	    				partie.phasePlacement( listeDesInventaires.get(j), listeDesJoueurs.get(j));
    	    	    				partie.phasePlacement( listeDesInventaires.get(k), listeDesJoueurs.get(k));
    	    	    			}
    	    	    			else {
    	    	    	    		for (int l=0 ; l<=nbJoueurs;l++){
    	    	    	    			if (listeDesInventaires.get(l).getNbOuvrierDispo() != 0 && listeDesInventaires.get(k).getNbOuvrierDispo() != 0 && listeDesInventaires.get(j).getNbOuvrierDispo() != 0 &&listeDesInventaires.get(i).getNbOuvrierDispo() != 0){
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
        		if ( listeDesInventaires.get(i).ouvrierDispo()==true) {
        			placer=true;
        		}
        	}
    	}
		System.out.println("**** Phase de résolution des ouvriers **** \n");		
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

		System.out.println("**** Phase Nourrir les ouvriers ****"+ "\n");
		for (int i=0 ; i<=nbJoueurs;i++){
			partie.phaseNourrir( listeDesInventaires.get(i), listeDesJoueurs.get(i));

		}
                
                for (int i=0 ; i<=nbJoueurs;i++) {
			int numJ=listeDesJoueurs.get(i).getNum();
			System.out.println("**** Inventaire du joueur "+numJ+" ****" );
			System.out.println("Bois  : " + listeDesInventaires.get(i).getNbBois());
			System.out.println("Argile : " + listeDesInventaires.get(i).getNbArgile());
			System.out.println("Pierre : " + listeDesInventaires.get(i).getNbPierre());
			System.out.println("Or : " + listeDesInventaires.get(i).getNbOr());
			System.out.println("Nombre d'ouvriers : " + listeDesInventaires.get(i).getNbOuvrier());
			System.out.println("Niveau agriculture : " + listeDesInventaires.get(i).getScoreChamp());
			System.out.println("Nombre de nourriture : " +listeDesInventaires.get(i).getNourriture());
			System.out.println("Nombre de ressources : " +listeDesInventaires.get(i).getNbRessource());
			System.out.println("Nombre d'outil  : " + listeDesInventaires.get(i).getNbOutils()+ "\n");
		}
		//changer l'ordre des joueurs et des inventaire 1234 ==> 2341 ==> 3412....
		for (int i=0 ; i<=nbJoueurs-1;i++) {
				Collections.swap(listeDesInventaires,i,i+1);
				Collections.swap(listeDesJoueurs,i,i+1);
		}
                Collections.shuffle(carte.getAllCards());//melange des cartes civilisations a la fin de chaque tour 
                Collections.shuffle(building.getCards());//melange des cartes batiments a la fin de chaque tour 
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
	    		System.out.println("Resources en Bois du joueur ** "+ (numJ) +" ** : " +listeDesInventaires.get(i).getNbBois());
	    		System.out.println("Resources en Argile du joueur ** "+ (numJ) +" ** : " +listeDesInventaires.get(i).getNbArgile());
	    		System.out.println("Resources en Pierre du joueur ** "+ (numJ) +" ** : " +listeDesInventaires.get(i).getNbPierre());
	    		System.out.println("Resources en Or du joueur ** "+ (numJ) +" ** : " +listeDesInventaires.get(i).getNbOr() );
				System.out.println("Niveau agriculture du joueur ** "+ (numJ) +" ** : " +listeDesInventaires.get(i).getScoreChamp() );
				System.out.println("Nombre d'outil du joueur ** "+ (numJ) +" ** : " +listeDesInventaires.get(i).getNbOutils());
				System.out.println("Nombre de nourriture du joueur ** "+ (numJ) +" ** : " +listeDesInventaires.get(i).getNourriture());
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
	public static int getNbJoueurTotal() {
		return nbJoueurTotal;
	}
}
