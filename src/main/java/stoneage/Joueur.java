package src.main.java.stoneage;

public class Joueur {
	int zone = 1;
	int nbOuvriers = 1;

	public CoupJouer placerOuvriers(int zone, int nbOuvriers){
		return new CoupJouer(zone, nbOuvriers);
	}
}
