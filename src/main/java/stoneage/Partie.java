package stoneage;
import java.util.ArrayList;
public class Partie {
    private final Joueur joueur;
    private final Inventaire inventaireDuJoueur;
    private final int NbZone;

    public Partie(){
    	joueur = new Joueur();
    	inventaireDuJoueur = new Inventaire();
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
        System.out.println("Resources en Bois: "+inventaireDuJoueur.getNbBois());
        System.out.println("Resources en Argile: "+inventaireDuJoueur.getNbArgile());
        System.out.println("Resources en Pierre: "+inventaireDuJoueur.getNbPierre());
        System.out.println("Resources en Or: "+inventaireDuJoueur.getNbOr());    
        System.out.println("Le joueur gagne avec "+inventaireDuJoueur.getNbRessource()+ " ressource(s).");

	}
    protected void unTour(){
        ArrayList<Zone> listeZonesDispo = new ArrayList<>();
        ArrayList<Zone> listeZonesJouer = new ArrayList<>();
        for (int i=3;i <= NbZone+2;i++ ){
        	Zone zone= new Zone(i);
        	listeZonesDispo.add(zone);
        } //remplire la liste des zones
	        
		inventaireDuJoueur.resetAvailableWorkers(); //remettre a jour le nombre d'ouvrier disponnible
		System.out.println("**** Phase de placement ****");
		phasePlacement(listeZonesDispo, listeZonesJouer);
		System.out.println("**** Phase de résolution des ouvriers ****");
		phaseAction(listeZonesJouer);
    }
	
    protected void phaseAction(ArrayList<Zone> listeZonesJouées) {
        while (listeZonesJouées.size() > 0 )
        {
            Zone choix = listeZonesJouées.get(0);
            listeZonesJouées.remove(choix);
            choix.resoudre(inventaireDuJoueur);
            System.out.println("Le joueur reprend ses ouvriers de la zone "+choix.NomZone());
        }
    }
    protected void phasePlacement(ArrayList<Zone> listeZonesDispo, ArrayList<Zone> listeZonesJouées){
    	while (inventaireDuJoueur.ouvrierDispo()){
    		Choix choix = joueur.placerOuvriers(listeZonesDispo, inventaireDuJoueur);
    		listeZonesDispo.remove(choix.zoneChoisie);
    		listeZonesJouées.add(choix.zoneChoisie);
    		choix.zoneChoisie.placerOuvrier(inventaireDuJoueur, choix.nbOuvriersChoisie);
    		System.out.println("Le joueur a choisi la zone "+(choix.zoneChoisie).NomZone()+" pour y placer "+choix.nbOuvriersChoisie+" ouvrier(s)");
    	}
    }
        
	public static final void main(String [] args) {
		Partie partie = new Partie();
		partie.jouer(); 
	}
}
