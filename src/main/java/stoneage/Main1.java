package stoneage;

/**
 * Lancement d'une unique partie avec le choix du nombre de joueurs (entre 2 et 4).
 */
public class Main1 {
	public static final void main(String [] args) {
		if (Integer.parseInt(args[0]) == 2 || Integer.parseInt(args[0]) == 3|| Integer.parseInt(args[0]) == 4) {
			StoneAge stoneage = new StoneAge(Integer.parseInt(args[0]), false);
			stoneage.unePartie(Integer.parseInt(args[0]));
		}
		else {
			// Execution par defaut
			StoneAge stoneage = new StoneAge(4, false);
			stoneage.unePartie(4);
		}
	}
}
