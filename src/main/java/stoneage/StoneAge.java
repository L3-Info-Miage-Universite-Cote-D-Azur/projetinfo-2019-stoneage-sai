package stoneage;

import java.util.ArrayList;
import java.util.Collections;

public class StoneAge {
	Partie partie = new Partie(); //nombre de joueur choisie est 4 le nombre de joueur minimal est 1 
	private final JoueurIA joueurIA = new JoueurIA();
	private final JoueurBot2 joueurBot = new JoueurBot2();
	private final ArrayList<Joueurs> listeDesJoueurs ; //une liste qui va contenir tous les joueurs de la partie
	private final ArrayList<Inventaire> listeDesInventaires ; //une liste qui va contenir d=toues les inventaire de la partie
 	private int nbJoueurs;  // A part le joueur IA 
 	public Zone zone;

	public static final void main(String [] args) {
		StoneAge stoneAge = new StoneAge(3);
		stoneAge.jouer();
		 
	}
	 public StoneAge(int nbJ){
	    	//On choisie le nombre des joueures dans cette partie
	    	this.nbJoueurs=nbJ-1;
	    	listeDesJoueurs = new ArrayList<>();
	    	listeDesInventaires = new ArrayList<>();
	    	listeDesJoueurs.add(joueurIA); 
	       	listeDesJoueurs.add(joueurBot);
	    	listeDesInventaires.add(new Inventaire());
	    	listeDesInventaires.add(new Inventaire());

	    	// ajoute en premier le joueurIA 
	    	//puis les autres joueurs normaux
	    	for (int i=0;i < nbJoueurs-1;i++){
	    		listeDesJoueurs.add(new Joueur());
	    		listeDesInventaires.add(new Inventaire());
	    	}

	    }
	    
	protected  void jouer(){

        System.out.println("***************** Debut de la Partie *****************\n");
        System.out.println("Joueur **1** : JoueurIA");
        System.out.println("Joueur **2** : JoueurBot2");
        System.out.println("Joueur **3** : JoueurNormal");
        System.out.println("Joueur **4** : JoueurNormal\n");
        for (int z=0 ; z < 5;z++){
            System.out.println("**** Debut du Tour N° "+ (z+1) +" ****");
            unTour();
	    System.out.println("**** Fin du Tour N° "+ (z+1) +" ****\n");
        }
        System.out.println("***************** fin de la Partie *****************");	
        gagner();
	}
	
    protected void unTour(){
    	for (int i=0 ; i<=nbJoueurs;i++)
    	{
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
    			if (listeDesInventaires.get(i).getNbOuvrierDispo() != 0)
    			{
    				partie.phasePlacement( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
    			}
    			else {
    	    		for (int j=0 ; j<=nbJoueurs;j++){
    	    			if (listeDesInventaires.get(j).getNbOuvrierDispo() != 0 &&listeDesInventaires.get(i).getNbOuvrierDispo() != 0)
    	    			{
    	    				partie.phasePlacement( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
    	    				partie.phasePlacement( listeDesInventaires.get(j), listeDesJoueurs.get(j), j+1);
    	    			}
    	    			else {
    	    	    		for (int k=0 ; k<=nbJoueurs;k++){
    	    	    			if (listeDesInventaires.get(k).getNbOuvrierDispo() != 0 && listeDesInventaires.get(j).getNbOuvrierDispo() != 0 &&listeDesInventaires.get(i).getNbOuvrierDispo() != 0)
    	    	    			{
    	    	    				partie.phasePlacement( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
    	    	    				partie.phasePlacement( listeDesInventaires.get(j), listeDesJoueurs.get(j), j+1);
    	    	    				partie.phasePlacement( listeDesInventaires.get(k), listeDesJoueurs.get(k), k+1);
    	    	    			}
    	    	    			else {
    	    	    	    		for (int l=0 ; l<=nbJoueurs;l++){
    	    	    	    			if (listeDesInventaires.get(l).getNbOuvrierDispo() != 0 && listeDesInventaires.get(k).getNbOuvrierDispo() != 0 && listeDesInventaires.get(j).getNbOuvrierDispo() != 0 &&listeDesInventaires.get(i).getNbOuvrierDispo() != 0)
    	    	    	    				 
    	    	    	    			{
    	    	    	    				partie.phasePlacement( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
    	    	    	    				partie.phasePlacement( listeDesInventaires.get(j), listeDesJoueurs.get(j), j+1);
    	    	    	    				partie.phasePlacement( listeDesInventaires.get(k), listeDesJoueurs.get(k), k+1);
    	    	    	    				partie.phasePlacement( listeDesInventaires.get(l), listeDesJoueurs.get(l), l+1);
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
    			if (listeDesInventaires.get(i).getNbZoneJouer()  != 0)
    			{
    				partie.phaseAction( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
    			}
    			else {
    	    		for (int j=0 ; j<=nbJoueurs;j++){
    	    			if (listeDesInventaires.get(j).getNbZoneJouer()!= 0 &&listeDesInventaires.get(i).getNbZoneJouer() != 0)
    	    			{
    	    				partie.phaseAction( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
    	    				partie.phaseAction( listeDesInventaires.get(j), listeDesJoueurs.get(j), j+1);
    	    			}
	
    	    			else {
    	    	    		for (int k=0 ; k<=nbJoueurs;k++){
    	    	    			if (listeDesInventaires.get(k).getNbZoneJouer() != 0 && listeDesInventaires.get(j).getNbZoneJouer()!= 0 &&listeDesInventaires.get(i).getNbZoneJouer() != 0)
    	    	    			{
    	    	    				partie.phaseAction( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
    	    	    				partie.phaseAction( listeDesInventaires.get(j), listeDesJoueurs.get(j), j+1);
    	    	    				partie.phaseAction( listeDesInventaires.get(k), listeDesJoueurs.get(k), k+1);
    	    	    			}
    	    	    			else {
    	    	    	    		for (int l=0 ; l<=nbJoueurs;l++){
    	    	    	    			if (listeDesInventaires.get(l).getNbZoneJouer() != 0 && listeDesInventaires.get(k).getNbZoneJouer() != 0 && listeDesInventaires.get(j).getNbZoneJouer() != 0 &&listeDesInventaires.get(i).getNbOuvrierDispo() != 0)
    	    	    	    				 
    	    	    	    			{
    	    	    	    				partie.phaseAction( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
    	    	    	    				partie.phaseAction( listeDesInventaires.get(j), listeDesJoueurs.get(j), j+1);
    	    	    	    				partie.phaseAction( listeDesInventaires.get(k), listeDesJoueurs.get(k), k+1);
    	    	    	    				partie.phaseAction( listeDesInventaires.get(l), listeDesJoueurs.get(l), l+1);
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
			partie.phaseNourrir( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
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
	    	else if (nbScoreEgaux==2) {
                 System.out.println("Les joueurs ** " +(joueurGagnant.get(0))+" ** et ** "+(joueurGagnant.get(1))+" ** gagnent.");
            } 
	    	else if  (nbScoreEgaux==1){
	    		System.out.println("Le joueur *** "+Gagnant+" *** emporte la partie avec : " + ScoreGagnant + " Points");
	    	}        
	               /*  	
	                else if (listeDesInventaires.get(a).calcScore()== listeDesInventaires.get(a+1).calcScore() &&listeDesInventaires.get(a).calcScore()>=Collections.max(listScore)){//scorejoueur1==scorejoueur2
                        System.out.println("Les joueurs" +(j)+" et"+(j+1)+"gagnent");
                    } 
	                else if (listeDesInventaires.get(a).calcScore()== listeDesInventaires.get(a+2).calcScore() &&listeDesInventaires.get(a).calcScore()>=Collections.max(listScore)){//scorejoueur1==scorejoueur3
	                        System.out.println("Les joueurs" +j+" et"+(j+2)+"gagnent");
	                    } 
	                else if (listeDesInventaires.get(a).calcScore()== listeDesInventaires.get(a+3).calcScore() &&listeDesInventaires.get(a).calcScore()>=Collections.max(listScore)){//scorejoueur1==scorejoueur3
	                        System.out.println("Les joueurs" +j+" et"+(j+3)+"gagnent");
	                    } 
	                else if (listeDesInventaires.get(a+1).calcScore()== listeDesInventaires.get(a+2).calcScore() &&listeDesInventaires.get(a+1).calcScore()>=Collections.max(listScore)){//scorejoueur2==scorejoueur3
	                        System.out.println("Les joueurs" +(j+1)+" et"+(j+2)+"gagnent");
	                    } 
	                else if (listeDesInventaires.get(a+1).calcScore()== listeDesInventaires.get(a+3).calcScore() &&listeDesInventaires.get(a+1).calcScore()>=Collections.max(listScore)){//scorejoueur2==scorejoueur4
	                        System.out.println("Les joueurs" +(j+1)+" et"+(j+3)+"gagnent");
	                    } 
	                else if (listeDesInventaires.get(a+2).calcScore()== listeDesInventaires.get(a+3).calcScore() &&listeDesInventaires.get(a+2).calcScore()>=Collections.max(listScore)){//scorejoueur2==scorejoueur4
	                        System.out.println("Les joueurs" +(j+2)+" et"+(j+3)+"gagnent");
	                    } 
	                                      		
	    		else {
	    			System.out.println("Le joueur ** "+Gagnant+" ** emporte la partie avec : " + ScoreGagnant + " Points");
	    		} 
	    		*/             	    
	 }
}
