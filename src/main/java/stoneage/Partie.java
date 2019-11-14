package stoneage;
import java.util.ArrayList;
public class Partie {
    private final Joueur joueurIA = new Joueur();
    private final ArrayList<Joueurs> listeDesJoueurs ; //une liste qui va contenir tous les joueurs de la partie
    private final ArrayList<Inventaire> listeDesInventaires ; //une liste qui va contenir d=toues les inventaire de la partie
    private int nbJoueurs;  // A part le joueur IA 

    public Partie(int nbJ){
    	//On choisie le nombre des joueures dans cette partie
    	this.nbJoueurs=nbJ-1;
    	listeDesJoueurs = new ArrayList<>();
    	listeDesInventaires = new ArrayList<>();
    	listeDesJoueurs.add(joueurIA); 
    	listeDesInventaires.add(new Inventaire());
    	
    	// ajoute en premier le joueurIA 
    	//puis les autres joueurs normaux
    	for (int i=0;i < nbJoueurs;i++){
    		listeDesJoueurs.add(new Joueur());
    		listeDesInventaires.add(new Inventaire());
    	}
 }
    protected void jouer(){

        System.out.println("***************** Debut de la Partie *****************"+ "\n");
        System.out.println("Joueur **1** : JoueurIA");
        System.out.println("Joueur **2** : JoueurNormal"+ "\n");
        System.out.println("Joueur **3** : JoueurNormal"+ "\n");
        System.out.println("Joueur **4** : JoueurNormal"+ "\n");
        for (int z=0 ; z < 5;z++){
            System.out.println("**** Debut du Tour N° "+ (z+1) +" ****");
            unTour();
	    System.out.println("**** Fin du Tour N° "+ (z+1) +" ****"+ "\n");
        }
        System.out.println("***************** fin de la Partie *****************");		
        gagner();
	}
    
    public void gagner() {
    	boolean Egalite = false;
    	//Egalite.add(2);
    	int Gagnant=0;
    	int ScoreGagnant=0;
    	for (int i=0 ; i<=nbJoueurs;i++)
    	{
    		System.out.println("Resources en Bois du joueur ** "+ (i+1) +" ** : " +listeDesInventaires.get(i).getNbBois());
    		System.out.println("Resources en Argile du joueur ** "+ (i+1) +" ** : " +listeDesInventaires.get(i).getNbArgile());
    		System.out.println("Resources en Pierre du joueur ** "+ (i+1) +" ** : " +listeDesInventaires.get(i).getNbPierre());
    		System.out.println("Resources en Or du joueur ** "+ (i+1) +" ** : " +listeDesInventaires.get(i).getNbOr() );
    		System.out.println("Le Score final  du joueur ** "+ (i+1) +" ** : " +listeDesInventaires.get(i).calcScore() + "\n");
    		if ( listeDesInventaires.get(i).calcScore()>ScoreGagnant){
    			Gagnant=i+1; //si le score du joueur corant est le plus elever alors on change les deux variables.
    			ScoreGagnant=listeDesInventaires.get(i).calcScore();
    		}
    		else if (listeDesInventaires.get(i).calcScore()== ScoreGagnant)
    		{
    			Egalite=!Egalite; // nous permet de savoir si les 4 joueurs ont des score egaux
    		}

    	}
 
    		if (Egalite == true) {
    			System.out.println("Les Joueurs ont des scores egaux! Il n'y a pas de gagagnt");
    		}
    		else {
    			System.out.println("Le joueur ** "+Gagnant+" ** emporte la partie avec : " + ScoreGagnant + " Points");
    		}
    }

    protected void unTour(){
    	for (int i=0 ; i<nbJoueurs;i++){
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
    	

		System.out.println("**** Phase de résolution des ouvriers ****");
    	for(Boolean bool : Zone.getListeZoneJouee()) {
			if (bool) {
				for(int i=0 ; i< nbJoueurs ; i++){
					phaseAction(listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);// faire la phase action pour chaque joueur
				}
			}
		}

		System.out.println("**** Phase Nourrir les ouvriers ****"+ "\n");              
		for (int i=0 ; i<nbJoueurs;i++){
			phaseNourrir( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
		}
    }

   protected void phaseAction(Inventaire inventaire, Joueurs joueur, int joueurCourant){

	   for(Boolean bool : Zone.getListeZoneJouee()){
		   if (bool){
			   int x = Zone.getListeZoneJouee().indexOf(bool);
			   joueur.recupeRes(inventaire, Zone.getListeZone().get(x));
			   Zone.getListeZoneJouee().set(x, false);
			   Zone.getListeZoneDispo().set(x, true);
			   System.out.println("Le joueur " + joueurCourant + " reprend ses ouvriers de la zone "+ Zone.getListeZone().get(x).NomZone()); //Zone.getListeZone().get(x)
		   }
	   }
    }

	protected void phasePlacement(Inventaire inventaire, Joueurs joueur, int joueurCourant){
		Choix choix = joueur.placerOuvriers(inventaire);
		Zone zoneChoisie = choix.getZoneChoisie();
		int index = Zone.getListeZone().indexOf(zoneChoisie);
		int nbOuvriersDispo = Zone.getPlaceDispoParZone().get(index);

		if (!Zone.getListeZoneJouee().get(index)) { //implique que la zone est disponible
			Zone.getListeZoneJouee().set(index, true);
			Zone.getPlaceDispoParZone().set(index, nbOuvriersDispo - choix.getNbOuvriersChoisis());
		}
		else{
			if (Zone.getListeZoneDispo().get(index)) {
				if (!inventaire.getListeZonesJouees().get(index)) {
					Zone.getPlaceDispoParZone().set(index, nbOuvriersDispo - choix.getNbOuvriersChoisis());
				}
			}
		}
		if (Zone.getPlaceDispoParZone().get(index) <= 0) {
			Zone.getListeZoneDispo().set(index, false);
		}
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
                    nm=nm-nm;
                    inv.setNbBois(inv.getNbBois()-nm);
                }
            }
            if (nm!=0) {
                if (nm-inv.getNbArgile()>0) {
                    nm=nm-inv.getNbArgile();
                    inv.setNbArgile(inv.getNbArgile()-inv.getNbArgile());      
                }     
                else{
                    nm=nm-nm;
                    inv.setNbArgile(inv.getNbArgile()-nm);  
                }
            }
            if (nm!=0) {
                if (nm-inv.getNbPierre()>0) {
                    nm=nm-inv.getNbPierre();
                    inv.setNbPierre(inv.getNbPierre()-inv.getNbPierre());      
                }     
                else{
                    nm=nm-nm;
                    inv.setNbPierre(inv.getNbPierre()-nm);  
                }
            }
            if (nm!=0) {
                if (nm-inv.getNbOr()>0) {
                    nm=nm-inv.getNbOr();
                    inv.setNbOr(inv.getNbOr()-inv.getNbOr());      
                }     
                else{
                    nm=nm-nm;
                    inv.setNbPierre(inv.getNbPierre()-nm);  
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