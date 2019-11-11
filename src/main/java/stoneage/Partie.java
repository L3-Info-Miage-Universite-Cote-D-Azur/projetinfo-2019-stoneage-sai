package stoneage;
import java.util.ArrayList;
public class Partie {
    private final Joueur joueur1, joueur2;
    
    private final Inventaire inventaireDuJoueur1, inventaireDuJoueur2;
    private final int NbZone;
    
	

    public Partie(){
    	joueur1 = new Joueur();
    	joueur2 = new Joueur();
    	
    	inventaireDuJoueur1 = new Inventaire();
    	inventaireDuJoueur2 = new Inventaire();
    	
    	NbZone=4;
    	

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
        ArrayList<Zone> listeZonesDispo1 = new ArrayList<>();
        ArrayList<Zone> listeZonesJouer1 = new ArrayList<>();
        ArrayList<Zone> listeZonesDispo2 = new ArrayList<>();
        ArrayList<Zone> listeZonesJouer2 = new ArrayList<>();
        
        for (int i=3;i <= NbZone+2;i++ ){
        	Zone zone= new Zone(i);
        	listeZonesDispo1.add(zone);
        	listeZonesDispo2.add(zone);
            } //remplire la liste des zones
	        
		inventaireDuJoueur1.resetAvailableWorkers(); //remettre a jour le nombre d'ouvrier disponnible
		inventaireDuJoueur2.resetAvailableWorkers();
		System.out.println("**** Phase de placement ****");		
		
		phasePlacement(listeZonesDispo1, listeZonesJouer1, inventaireDuJoueur1, joueur1, 1);
		phasePlacement(listeZonesDispo2, listeZonesJouer2, inventaireDuJoueur2, joueur2, 2);
						
		System.out.println("**** Phase de résolution des ouvriers ****");
                
		phaseAction(listeZonesJouer1,inventaireDuJoueur1, 1);
		phaseAction(listeZonesJouer2,inventaireDuJoueur2, 2);			
    }
    
    
    
    
	
    protected void phaseAction(ArrayList<Zone> listeZonesJouées, Inventaire  inventaireDuJoueur, int joueurCourant) {
            Zone choix = listeZonesJouées.get(0);
            listeZonesJouées.remove(choix);
            choix.resoudre(inventaireDuJoueur);
            System.out.println("Le joueur " + joueurCourant + " reprend ses ouvriers de la zone "+choix.NomZone());
    }
    
    
    
    
    protected void phasePlacement(ArrayList<Zone> listeZonesDispo, ArrayList<Zone> listeZonesJouées, Inventaire  inventaireDuJoueur, Joueur joueur, int joueurCourant) {
    	Choix choix = joueur.placerOuvriers(listeZonesDispo, inventaireDuJoueur);

    	listeZonesDispo.remove(choix.zoneChoisie);
    		
    	listeZonesJouées.add(choix.zoneChoisie);
    	choix.zoneChoisie.placerOuvrier(inventaireDuJoueur, choix.nbOuvriersChoisie);
    		
    	System.out.println("Le joueur " + joueurCourant + " a choisi la zone "+(choix.zoneChoisie).NomZone()+" pour y placer "+choix.nbOuvriersChoisie+" ouvrier(s)");
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
        
    	if (inventaireDuJoueur1.getNbRessource() == inventaireDuJoueur2.getNbRessource()) {
    		System.out.println("Egalité !!! Personne ne gagne");
    	}
    	else if (inventaireDuJoueur1.getNbRessource() > inventaireDuJoueur2.getNbRessource()) {
    		System.out.println("Le joueur 1 l'emporte avec : " + inventaireDuJoueur1.getNbRessource() + " ressources");
    	}
    	else {
    		System.out.println("Le joueur 2 l'emporte avec : " + inventaireDuJoueur2.getNbRessource() + " ressources");
    	}
    }
    
 
    
	public static final void main(String [] args) {
		Partie partie = new Partie();
		partie.jouer(); 
	}
}
