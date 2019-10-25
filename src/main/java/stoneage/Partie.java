package stoneage;
/**
*
* @author Yasmine 
*/
public class Partie {
    private final Joueur joueur;
    private final Zone zone;

	public Partie(){
		joueur = new Joueur();
		zone= new Zone(3); //zone foret
		

	}
	protected void jouer(int constante){
		Choix choix = joueur.placerOuvriers(1,1);
		int gains = zone.resoudre(constante);
		System.out.println("Le joueur a placé "+choix.nbOuvriersJouer+" ouvrier(s) dans la zone "+ zone.NomZone() +".");
		System.out.println("Le joueur gagne avec "+gains + " bois.");
	}

	
	
	public static final void main(String [] args) {
		System.out.println("**** Debut de la Partie ****");
		Partie partie = new Partie();
		partie.jouer(6); //Choix d'une constante qui represente le dé.
		System.out.println("**** fin de la Partie ****");
		



}
}
