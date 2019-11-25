package stoneage;

import java.util.ArrayList;
import java.util.Collections;

public class StoneAge {
	Partie partie = new Partie(); //nombre de joueur choisie est 4 le nombre de joueur minimal est 1 
	private final JoueurIA joueurIA = new JoueurIA("O",1);
	private final JoueurBot2 joueurBot = new JoueurBot2("S",2);
	final static  ArrayList<Joueurs> listeDesJoueurs =new ArrayList<>(); //une liste qui va contenir tous les joueurs de la partie
	final static  ArrayList<Inventaire> listeDesInventaires=new ArrayList<>() ; //une liste qui va contenir d=toues les inventaire de la partie
 	private int nbJoueurs;  // A part le joueur IA
	private static int nbJoueurTotal;
 	public Zone zone;


 	
 	/****** Choisir un Nombre de Joueure Pour commencer une Partie ( entre 2 et 4 )******/
	public static final void main(String [] args) {
		StoneAge stoneAge = new StoneAge(2);
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
	    		//listeDesJoueurs.add(new Joueur());
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
        while ( partie.getNbCarteDispo()>nbJoueurTotal && nbDeTour<50 && partie.getNbBatiments()>nbJoueurTotal) {
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
	    		if ( (listeDesInventaires.get(i).getNbZoneJouer() > 0) ) {
	    			recuperer=true;
	    		}
	    	}
		}
    			
    	    			
		System.out.println("**** Phase Nourrir les ouvriers ****"+ "\n");
		for (int i=0 ; i<=nbJoueurs;i++){
			partie.phaseNourrir( listeDesInventaires.get(i), listeDesJoueurs.get(i));
                        System.out.println();
		}
                
                
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
	    		System.out.println("Resources en Bois du joueur ** "+ (i+1) +" ** : " +listeDesInventaires.get(i).getNbBois());
	    		System.out.println("Resources en Argile du joueur ** "+ (i+1) +" ** : " +listeDesInventaires.get(i).getNbArgile());
	    		System.out.println("Resources en Pierre du joueur ** "+ (i+1) +" ** : " +listeDesInventaires.get(i).getNbPierre());
	    		System.out.println("Resources en Or du joueur ** "+ (i+1) +" ** : " +listeDesInventaires.get(i).getNbOr() );
	    		System.out.println("Le Score final  du joueur ** "+ (i+1) +" ** : " +listeDesInventaires.get(i).calcScore() + "\n");
	                
	    		if ( listeDesInventaires.get(i).calcScore()>ScoreGagnant){
	    			Gagnant=i+1; //si le score du joueur corant est le plus elever alors on change les deux variables.
	    			ScoreGagnant=listeDesInventaires.get(i).calcScore();
	    		}       
	        }        
    		int nbScoreEgaux=0;
    	    ArrayList<Integer>  joueurGagnant=new ArrayList();
	    	for (int i=0 ; i<=nbJoueurs;i++){
	    		if (listeDesInventaires.get(i).calcScore()== ScoreGagnant) {
	    			nbScoreEgaux++;
	    			joueurGagnant.add(i+1);
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
