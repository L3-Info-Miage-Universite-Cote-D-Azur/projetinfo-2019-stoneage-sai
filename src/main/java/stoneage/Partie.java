package stoneage;
import java.util.ArrayList;
public class Partie {
    private final Joueur joueur1;
    JoueurIA joueur2;
    
    private final Inventaire inventaireDuJoueur1, inventaireDuJoueur2;
    
	

    public Partie(){
    	joueur1 = new Joueur();
    	joueur2 = new JoueurIA();
    	
    	inventaireDuJoueur1 = new Inventaire();
    	inventaireDuJoueur2 = new Inventaire();

    	

    }
    protected void jouer(){
        String newLine = System.getProperty("line.separator");
        System.out.println("***************** Debut de la Partie *****************"+newLine);
        for (int z=0 ; z < 5;z++){
            System.out.println("**** Debut du Tour N° "+ (z+1) +" ****");
            unTour();
	    System.out.println("**** Fin du Tour N° "+ (z+1) +" ****"+newLine);
        }
        System.out.println("***************** fin de la Partie *****************");		
        gagner();
	}
    
    
    protected void unTour(){
        /*ArrayList<Zone> listeZonesDispo1 = new ArrayList<>();
        ArrayList<Zone> listeZonesJouer1 = new ArrayList<>();
        ArrayList<Zone> listeZonesDispo2 = new ArrayList<>();
        ArrayList<Zone> listeZonesJouer2 = new ArrayList<>();
        
        for (int i=3;i <= NbZone+2;i++ ){
        	Zone zone= new Zone(i);
        	listeZonesDispo1.add(zone);
        	listeZonesDispo2.add(zone);
        } //remplire la liste des zones
	        */
		inventaireDuJoueur1.resetAvailableWorkers(); //remettre a jour le nombre d'ouvrier disponnible
		inventaireDuJoueur2.resetAvailableWorkers();
		System.out.println("**** Phase de placement ****");
		
		
		while (inventaireDuJoueur1.ouvrierDispo() || inventaireDuJoueur2.ouvrierDispo()){
			if (inventaireDuJoueur1.getNbOuvrierDispo() == 0) {
				phasePlacement( inventaireDuJoueur2, joueur2, 2);
			}
			else if (inventaireDuJoueur2.getNbOuvrierDispo() == 0) {
				phasePlacement( inventaireDuJoueur1, joueur1, 1);
			}
			else {
				phasePlacement(inventaireDuJoueur1, joueur1, 1);
				phasePlacement( inventaireDuJoueur2, joueur2, 2);
				
			}
			System.out.println();
		}
		System.out.println("**** Phase de résolution des ouvriers ****");
		
		while ((inventaireDuJoueur1.listeZonesJouer.size() > 0) && (inventaireDuJoueur2.listeZonesJouer.size() > 0)) {
			if (inventaireDuJoueur1.listeZonesJouer.size() == 0) {
				phaseAction(inventaireDuJoueur2,joueur2,2);
			}
			else if (inventaireDuJoueur2.listeZonesJouer.size() == 0) {
				phaseAction(inventaireDuJoueur1,joueur1,1);
			}
			else {
				phaseAction(inventaireDuJoueur1,joueur1,1);
				phaseAction(inventaireDuJoueur2,joueur2,2);
			}
		}
		inventaireDuJoueur1.restartListeZoneDispo();
		inventaireDuJoueur2.restartListeZoneDispo();
		System.out.println("**** Phase Nourrir les ouvriers ****");
		phaseNourrire( inventaireDuJoueur1, joueur1, 1);
		phaseNourrire( inventaireDuJoueur2, joueur2, 2);
		
    }
    
    
    
    
	
    protected void phaseAction( Inventaire  inv,Joueurs joueur,int joueurCourant) {
        while (inv.listeZonesJouer.size() > 0 ){
            Zone choix = inv.listeZonesJouer.get(0);
            joueur.recupeRes(inv,choix);
            inv.listeZonesJouer.remove(choix);
            System.out.println("Le joueur " + joueurCourant + " reprend ses ouvriers de la zone "+choix.NomZone());
        }
    }
    
    
    
    
    protected void phasePlacement( Inventaire  inv, Joueurs joueur, int joueurCourant){
       // while (inventaireDuJoueur.ouvrierDispo()){
            Choix choix = joueur.placerOuvriers( inv);
            inv.listeZonesDispo.remove(choix.zoneChoisie);   		
            inv.listeZonesJouer.add(choix.zoneChoisie);
            choix.zoneChoisie.placerOuvrier(inv, choix.nbOuvriersChoisie);    		
            System.out.println("Le joueur " + joueurCourant + " a choisi la zone "+(choix.zoneChoisie).NomZone()+" pour y placer "+choix.nbOuvriersChoisie+" ouvrier(s)");
       // }
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
    
    public void gagner() {
        System.out.println("Resources en Bois du joueur 1 : "+inventaireDuJoueur1.getNbBois());
        System.out.println("Resources en Argile du joueur 1 : "+inventaireDuJoueur1.getNbArgile());
        System.out.println("Resources en Pierre du joueur 1 : "+inventaireDuJoueur1.getNbPierre());
        System.out.println("Resources en Or du joueur 1 : "+inventaireDuJoueur1.getNbOr() + "\n");
        
        System.out.println("Resources en Bois du joueur 2 : "+inventaireDuJoueur2.getNbBois());
        System.out.println("Resources en Argile du joueur 2 : "+inventaireDuJoueur2.getNbArgile());
        System.out.println("Resources en Pierre du joueur 2 : "+inventaireDuJoueur2.getNbPierre());
        System.out.println("Resources en Or du joueur 2 : "+inventaireDuJoueur2.getNbOr() + "\n");
        
    	if (inventaireDuJoueur1.calcScore() == inventaireDuJoueur2.calcScore()) {
    		System.out.println("Egalité !!! Personne ne gagne");
    	}
    	else if (inventaireDuJoueur1.calcScore() > inventaireDuJoueur2.calcScore()) {
    		System.out.println("Le joueur 1 l'emporte avec : " + inventaireDuJoueur1.calcScore() + " Points");
    	}
    	else {
    		System.out.println("Le joueur 2 l'emporte avec : " + inventaireDuJoueur2.calcScore() + " Points");
    	}
    }
    
 
    
	public static final void main(String [] args) {
		Partie partie = new Partie();
		partie.jouer(); 
	}
}
