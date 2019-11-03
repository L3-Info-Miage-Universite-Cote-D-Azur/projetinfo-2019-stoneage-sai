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
        ArrayList<Zone> listeZonesDispo = new ArrayList<>();
        ArrayList<Zone> listeZonesJouer = new ArrayList<>();
        for (int i=3;i <= NbZone+2;i++ ){
        	Zone zone= new Zone(i);
        	listeZonesDispo.add(zone);
        } //remplire la liste des zones
        
        inventaireDuJoueur.resetAvailableWorkers(); //remettre a jour le nombre d'ouvrier disponnible
        System.out.println("**** phase de placement ****");
        phasePlacement(listeZonesDispo, listeZonesJouer);

        System.out.println("**** phase de résolution des ouvriers ****");
        phaseAction(listeZonesJouer);

        System.out.println("**** fin du jeu ****");
        System.out.println("Le joueur gagne avec "+inventaireDuJoueur.getNbRessource()+ " ressource(s).");

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
		System.out.println("**** Debut de la Partie ****");
		Partie partie = new Partie();
		partie.jouer(); 
		System.out.println("**** fin de la Partie ****");		
	}
}
