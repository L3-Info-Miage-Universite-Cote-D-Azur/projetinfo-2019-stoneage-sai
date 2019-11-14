package stoneage;
import java.util.ArrayList;
public class Partie {
    private final JoueurIA joueurIA = new JoueurIA();
    private final ArrayList<Joueurs> listeDesJoueurs ; //une liste qui va contenir tous les joueurs de la partie
    private final ArrayList<Inventaire> listeDesInventaires ; //une liste qui va contenir d=toues les inventaire de la partie
    private int nbJoueurs=3;  // A part le joueur IA 
    
	

    public Partie(){
    	
    	//On choisie le nombre des joueures dans cette partie
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
    			System.out.println("Les Joueurs ont des scores egaux! Il n'ya pas de gagagnt");
    		}
    		else {
    			System.out.println("Le joueur ** "+Gagnant+" ** emporte la partie avec : " + ScoreGagnant + " Points");
    		}
    }
    protected void unTour(){
        ArrayList<Zone> listeZonesDispo = new ArrayList<>();
        ArrayList<Zone> listeZonesJouer = new ArrayList<>();
        
        for (int i=1;i <= 6;i++ ){
        	Zone zone= new Zone(i);
        	listeZonesDispo.add(zone);

        } //remplire la liste des zones
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
    	

		System.out.println("**** Phase de résolution des ouvriers ****");
		
		while ((listeDesInventaires.get(2).listeZonesJouer.size() > 0) && (listeDesInventaires.get(3).listeZonesJouer.size() > 0)&&(listeDesInventaires.get(0).listeZonesJouer.size() > 0) && (listeDesInventaires.get(1).listeZonesJouer.size() > 0)) {
			for (int i=0 ; i<=nbJoueurs;i++){
    			if (listeDesInventaires.get(i).listeZonesJouer.size()  != 0)
    			{
    				phaseAction( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
    			}
    			else {
    	    		for (int j=0 ; j<=nbJoueurs;j++){
    	    			if (listeDesInventaires.get(j).listeZonesJouer.size()!= 0 &&listeDesInventaires.get(i).listeZonesJouer.size() != 0)
    	    			{
    	    				phaseAction( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
    	    				phaseAction( listeDesInventaires.get(j), listeDesJoueurs.get(j), j+1);
    	    			}
	
    	    			else {
    	    	    		for (int k=0 ; k<=nbJoueurs;k++){
    	    	    			if (listeDesInventaires.get(k).getNbOuvrierDispo() != 0 && listeDesInventaires.get(j).getNbOuvrierDispo() != 0 &&listeDesInventaires.get(i).getNbOuvrierDispo() != 0)
    	    	    			{
    	    	    				phaseAction( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
    	    	    				phaseAction( listeDesInventaires.get(j), listeDesJoueurs.get(j), j+1);
    	    	    				phaseAction( listeDesInventaires.get(k), listeDesJoueurs.get(k), k+1);
    	    	    			}
    	    	    			else {
    	    	    	    		for (int l=0 ; l<=nbJoueurs;l++){
    	    	    	    			if (listeDesInventaires.get(l).getNbOuvrierDispo() != 0 && listeDesInventaires.get(k).getNbOuvrierDispo() != 0 && listeDesInventaires.get(j).getNbOuvrierDispo() != 0 &&listeDesInventaires.get(i).getNbOuvrierDispo() != 0)
    	    	    	    				 
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
			phaseNourrire( listeDesInventaires.get(i), listeDesJoueurs.get(i), i+1);
		}
		
    }

    protected void phaseAction( Inventaire  inv,Joueurs joueur,int joueurCourant) {
        while (inv.listeZonesJouer.size() > 0 ){
            Zone choix = inv.listeZonesJouer.get(0);
            joueur.recupeRes(inv,choix);
            inv.listeZonesJouer.remove(choix);
            inv.listeZonesDispo.add(choix);
            System.out.println("Le joueur " + joueurCourant + " reprend ses ouvriers de la zone "+choix.NomZone());
        }
    }
    
    
    
    
    protected void phasePlacement( Inventaire  inv, Joueurs joueur, int joueurCourant){
            Choix choix = joueur.placerOuvriers( inv);
            inv.listeZonesDispo.remove(choix.zoneChoisie);   		
            inv.listeZonesJouer.add(choix.zoneChoisie);
            choix.zoneChoisie.placerOuvrier(inv, choix.nbOuvriersChoisie);    		
            System.out.println("Le joueur " + joueurCourant + " a choisi la zone "+(choix.zoneChoisie).NomZone()+" pour y placer "+choix.nbOuvriersChoisie+" ouvrier(s)");
    }
        

    protected void phaseNourrire( Inventaire  inv, Joueurs joueur, int joueurCourant){
    	if (inv.getNourriture()>=inv.getNbOuvrierDispo())
    	{
    		inv.setNourriture(inv.getNourriture()-inv.getNbOuvrierDispo());
            System.out.println("Le joueur " + joueurCourant + " va nourrir ses ouvriers avec la nourritue qu'il possede. ");

    	}
    	else 
    	{
    		inv.setScore(inv.getScore()-10);
            System.out.println("Le joueur " + joueurCourant + " n'a pas assez de nourriture, son score est diminuer de 10 points. ");
    	}
    	
    }
      

}
