package stoneage;
/**
*
* @author Yasmine 
* @author Ossama
*/
import java.util.ArrayList;
public class Partie {
    private final Joueur joueur;
    private final Inventaire inventaireDuJoueur;
    private final Zone zone;

	public Partie(){
		joueur = new Joueur();
		zone= new Zone(3); //zone foret
		inventaireDuJoueur=new Inventaire();

	}
	protected void jouer(int constante){
		Choix choix = joueur.placerOuvriers(1,1);
		int gains = zone.resoudre(constante);
		System.out.println("Le joueur a placé "+choix.nbOuvriersChoisie+" ouvrier(s) dans la zone "+ zone.NomZone() +".");
		System.out.println("Le joueur gagne avec "+gains + " bois.");
	}
        
        protected void phasePlacement(ArrayList<Zone> listeZonesDispo, ArrayList<Zone> listeZonesJouées){
            while (inventaireDuJoueur.ouvrierDispo()){
                Inventaire inventaireTemp = new Inventaire(inventaireDuJoueur);
                Choix choix = joueur.placerOuvriers(listeZonesDispo, inventaireTemp);
                listeZonesDispo.remove(choix.zoneChoisie);
                listeZonesJouées.add(choix.zoneChoisie);
                choix.zoneChoisie.placerOuvrier(inventaireDuJoueur, choix.nbOuvriersChoisie);
                System.out.println("Le joueur a choisi la zone "+choix.zoneChoisie+" pour y placer "+choix.nbOuvriersChoisie+" ouvrier(s)");
            }
        }
        
        
        

	
	
	public static final void main(String [] args) {
		System.out.println("**** Debut de la Partie ****");
		Partie partie = new Partie();
		partie.jouer(6); //Choix d'une constante qui represente le dé.
		System.out.println("**** fin de la Partie ****");		
            }
}
