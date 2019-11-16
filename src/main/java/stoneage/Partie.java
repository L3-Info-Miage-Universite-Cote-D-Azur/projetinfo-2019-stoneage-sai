package stoneage;
import java.util.ArrayList;
import java.util.Collections ;
public class Partie {
	
    private final JoueurIA joueurIA = new JoueurIA();
    private final JoueurBot2 joueurBot = new JoueurBot2();
    //private final Joueur joueurBot = new Joueur();
    private final ArrayList<Joueurs> listeDesJoueurs ; //une liste qui va contenir tous les joueurs de la partie
    private final ArrayList<Inventaire> listeDesInventaires ; //une liste qui va contenir d=toues les inventaire de la partie
    private int nbJoueurs;  // A part le joueur IA 
    public Zone zone;
    private final ArrayList<Zone> LesZones ;
    
    public Partie(int nbJ){
    	//On choisie le nombre des joueures dans cette partie
    	this.nbJoueurs=nbJ-1;
    	listeDesJoueurs = new ArrayList<>();
    	listeDesInventaires = new ArrayList<>();
    	listeDesJoueurs.add(joueurIA); 
       	listeDesJoueurs.add(joueurBot);
    	listeDesInventaires.add(new Inventaire());
    	listeDesInventaires.add(new Inventaire());
    	LesZones=new ArrayList<>();
    	  // c'est la liste general des zone pour le jeu 
    	    for(int i=1; i<7;i++){
    	    	Zone zone = new Zone(i);
    	    	LesZones.add(zone);
    	    }

    	// ajoute en premier le joueurIA 
    	//puis les autres joueurs normaux
    	for (int i=0;i < nbJoueurs-1;i++){
    		listeDesJoueurs.add(new Joueur());
    		listeDesInventaires.add(new Inventaire());
    	}

    }
    protected void jouer(){

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
    
    public void gagner() {
        ArrayList<Integer> listScore= new ArrayList<>();
        listScore.add(listeDesInventaires.get(0).calcScore());
        listScore.add(listeDesInventaires.get(1).calcScore());
        listScore.add(listeDesInventaires.get(2).calcScore());
        listScore.add(listeDesInventaires.get(3).calcScore());
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
                int a=0;
                int j=1;
                if (listeDesInventaires.get(a).calcScore()== listeDesInventaires.get(a+1).calcScore() && listeDesInventaires.get(a+1).calcScore()==listeDesInventaires.get(a+2).calcScore() && listeDesInventaires.get(a+2).calcScore()==listeDesInventaires.get(a+3).calcScore()){
                    System.out.println("Les Joueurs ont des scores egaux! Il n'y a pas de gagnant");// nous permet de savoir si les 4 joueurs ont des score egaux
    		}  	
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
    }
    protected void unTour(){
        /*ArrayList<Zone> listeZonesDispo = new ArrayList<>();

        
        for (int i=1;i <= 6;i++ ){
        	Zone zone= new Zone(i);
        	listeZonesDispo.add(zone);

        } //remplire la liste des zones
        */
    	for (int i=0 ; i<=nbJoueurs;i++)
    	{
    		listeDesInventaires.get(i).resetAvailableWorkers(); //remettre a jour le nombre d'ouvrier disponnible
   

    	}
		System.out.println("**** Phase de placement ****");
		while (listeDesInventaires.get(0).ouvrierDispo() || listeDesInventaires.get(1).ouvrierDispo()|| listeDesInventaires.get(2).ouvrierDispo()|| listeDesInventaires.get(3).ouvrierDispo()){
    			
    		for (int i=0 ; i<=nbJoueurs;i++){
    			if (listeDesInventaires.get(i).getNbOuvrierDispo() != 0)
    			{
    				phasePlacement( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
    			}
    			else {
    	    		for (int j=0 ; j<=nbJoueurs;j++){
    	    			if (listeDesInventaires.get(j).getNbOuvrierDispo() != 0 &&listeDesInventaires.get(i).getNbOuvrierDispo() != 0)
    	    			{
    	    				phasePlacement( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
    	    				phasePlacement( listeDesInventaires.get(j), listeDesJoueurs.get(j), j+1);
    	    			}
    	    			else {
    	    	    		for (int k=0 ; k<=nbJoueurs;k++){
    	    	    			if (listeDesInventaires.get(k).getNbOuvrierDispo() != 0 && listeDesInventaires.get(j).getNbOuvrierDispo() != 0 &&listeDesInventaires.get(i).getNbOuvrierDispo() != 0)
    	    	    			{
    	    	    				phasePlacement( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
    	    	    				phasePlacement( listeDesInventaires.get(j), listeDesJoueurs.get(j), j+1);
    	    	    				phasePlacement( listeDesInventaires.get(k), listeDesJoueurs.get(k), k+1);
    	    	    			}
    	    	    			else {
    	    	    	    		for (int l=0 ; l<=nbJoueurs;l++){
    	    	    	    			if (listeDesInventaires.get(l).getNbOuvrierDispo() != 0 && listeDesInventaires.get(k).getNbOuvrierDispo() != 0 && listeDesInventaires.get(j).getNbOuvrierDispo() != 0 &&listeDesInventaires.get(i).getNbOuvrierDispo() != 0)
    	    	    	    				 
    	    	    	    			{
    	    	    	    				phasePlacement( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
    	    	    	    				phasePlacement( listeDesInventaires.get(j), listeDesJoueurs.get(j), j+1);
    	    	    	    				phasePlacement( listeDesInventaires.get(k), listeDesJoueurs.get(k), k+1);
    	    	    	    				phasePlacement( listeDesInventaires.get(l), listeDesJoueurs.get(l), l+1);
    	    	    	    			}
    	    	    	    		}
    	    	    			}
    	    	    		}
    	    			}
    	    		}
	
    			}
    		}
    	}
    	

		System.out.println("**** Phase de résolution des ouvriers **** \n");		

		while ((listeDesInventaires.get(0).getNbZoneJouer() > 0) || (listeDesInventaires.get(1).getNbZoneJouer() > 0)||(listeDesInventaires.get(2).getNbZoneJouer() > 0) || (listeDesInventaires.get(3).getNbZoneJouer() > 0)) {
			
			for (int i=0 ; i<=nbJoueurs;i++){
    			if (listeDesInventaires.get(i).getNbZoneJouer()  != 0)
    			{
    				phaseAction( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
    			}
    			else {
    	    		for (int j=0 ; j<=nbJoueurs;j++){
    	    			if (listeDesInventaires.get(j).getNbZoneJouer()!= 0 &&listeDesInventaires.get(i).getNbZoneJouer() != 0)
    	    			{
    	    				phaseAction( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
    	    				phaseAction( listeDesInventaires.get(j), listeDesJoueurs.get(j), j+1);
    	    			}
	
    	    			else {
    	    	    		for (int k=0 ; k<=nbJoueurs;k++){
    	    	    			if (listeDesInventaires.get(k).getNbZoneJouer() != 0 && listeDesInventaires.get(j).getNbZoneJouer()!= 0 &&listeDesInventaires.get(i).getNbZoneJouer() != 0)
    	    	    			{
    	    	    				phaseAction( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
    	    	    				phaseAction( listeDesInventaires.get(j), listeDesJoueurs.get(j), j+1);
    	    	    				phaseAction( listeDesInventaires.get(k), listeDesJoueurs.get(k), k+1);
    	    	    			}
    	    	    			else {
    	    	    	    		for (int l=0 ; l<=nbJoueurs;l++){
    	    	    	    			if (listeDesInventaires.get(l).getNbZoneJouer() != 0 && listeDesInventaires.get(k).getNbZoneJouer() != 0 && listeDesInventaires.get(j).getNbZoneJouer() != 0 &&listeDesInventaires.get(i).getNbOuvrierDispo() != 0)
    	    	    	    				 
    	    	    	    			{
    	    	    	    				phaseAction( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
    	    	    	    				phaseAction( listeDesInventaires.get(j), listeDesJoueurs.get(j), j+1);
    	    	    	    				phaseAction( listeDesInventaires.get(k), listeDesJoueurs.get(k), k+1);
    	    	    	    				phaseAction( listeDesInventaires.get(l), listeDesJoueurs.get(l), l+1);
    	    	    	    			}
    	    	    	    		}
    	    	    			}
    	    	    		}
    	    			}
    	    		}
    			}
			}
		}
    			
    	    			
		System.out.println("**** Phase Nourrir les ouvriers ****"+ "\n");
		for (int i=0 ; i<=nbJoueurs;i++){
			phaseNourrir( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
		}
		
    }

    protected void phaseAction(  Inventaire  inv,Joueurs joueur,int joueurCourant) {
        for(int i =0;i<6;i++){
        	if (inv.listeZonesJouer.get(i)==true){
        		Zone choix = LesZones.get(i);
        		joueur.recupeRes(inv,choix);
        		inv.listeZonesJouer.set(i,false); //la zone n'es pluas etuliser donc elle devient false pour le joueur (disponnible a nouveau)
        		System.out.println("Le joueur " + joueurCourant + " reprend ses ouvriers de la zone "+choix.NomZone());
        		System.out.println("Il gagne : "+joueur.getGains() +" " +joueur.TypeGains()  + ". \n");
        	}            
        }
    }
   
    protected void phasePlacement( Inventaire  inv, Joueurs joueur, int joueurCourant){
            Choix choix = joueur.placerOuvriers( LesZones,inv);
            inv.listeZonesJouer.set(choix.zoneChoisie,true); //la zone choisie est utliser donc devient true dans l'inventaire du joueur 
            LesZones.get(choix.zoneChoisie).placerOuvrier(inv, choix.nbOuvriersChoisie);    		
            System.out.println("Le joueur " + joueurCourant + " a choisi la zone "+(LesZones.get(choix.zoneChoisie)).NomZone()+" pour y placer "+choix.nbOuvriersChoisie+" ouvrier(s)");  
    }
    
    protected void phaseNourrir(Inventaire  inv, Joueurs joueur, int joueurCourant){
        int nm=inv.getNbOuvrierDispo()-inv.getNourriture();//nourriture qui manque
    	if (inv.getNourriture()>=inv.getNbOuvrierDispo()){
            inv.setNourriture(inv.getNourriture()-inv.getNbOuvrierDispo());
            System.out.println("Le joueur " + joueurCourant + " va nourrir ses ouvriers avec la nourritue qu'il possede. ");

    	}
        else if(inv.getNourriture()<inv.getNbOuvrierDispo()  && Integer.sum(Integer.sum(Integer.sum(inv.getNbBois(),inv.getNbArgile()),inv.getNbPierre()),inv.getNbOr())>=nm ){
            inv.setNourriture(inv.getNourriture()-inv.getNourriture());
            inv.setNbRessource(inv.getNbRessource()-nm);
            if (nm!=0) {
                if (nm-inv.getNbBois()>0) {
                    nm=nm-inv.getNbBois();
                    inv.setNbBois(inv.getNbBois()-inv.getNbBois());      
                }     
                else{
                    inv.setNbBois(inv.getNbBois()-nm);
                    nm=nm-nm;
                }
            }
            if (nm!=0) {
                if (nm-inv.getNbArgile()>0) {
                    nm=nm-inv.getNbArgile();
                    inv.setNbArgile(inv.getNbArgile()-inv.getNbArgile());      
                }     
                else{
                    inv.setNbArgile(inv.getNbArgile()-nm);  
                    nm=nm-nm;
                }
            }
            if (nm!=0) {
                if (nm-inv.getNbPierre()>0) {
                    nm=nm-inv.getNbPierre();
                    inv.setNbPierre(inv.getNbPierre()-inv.getNbPierre());      
                }     
                else{
                    inv.setNbPierre(inv.getNbPierre()-nm);  
                    nm=nm-nm;
                }
            }
            if (nm!=0) {
                if (nm-inv.getNbOr()>0) {
                    nm=nm-inv.getNbOr();
                    inv.setNbOr(inv.getNbOr()-inv.getNbOr());      
                }     
                else{
                    inv.setNbOr(inv.getNbOr()-nm);  
                    nm=nm-nm;
                }
            }
            System.out.println("Le joueur " + joueurCourant + " n'a pas assez de nourriture ,il utilise donc la nourriture qu'il possede et ses ressources" );
        }
    	else {
            inv.setScore(inv.getScore()-10);
            System.out.println("Le joueur " + joueurCourant + " n'a pas assez de nourriture, son score est diminuer de 10 points. ");
    	}	
    }
}