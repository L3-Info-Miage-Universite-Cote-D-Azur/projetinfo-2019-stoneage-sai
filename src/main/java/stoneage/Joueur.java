package stoneage;

public class Joueur {
	int zone = 1;
	int nbOuvriers = 1;

	public Choix placerOuvriers(int zone, int nbOuvriers){
		return new Choix(zone, nbOuvriers);
	}
}
