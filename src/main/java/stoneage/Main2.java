package stoneage;

/**
 * Lancement de 500 parties avec le choix du nombre de joueurs (entre 2 et 4).
 */
public class Main2 {
	public static final void main(String [] args) {
		try {
			if (Integer.parseInt(args[0]) == 2 || Integer.parseInt(args[0]) == 3 || Integer.parseInt(args[0]) == 4) {
				StoneAge stoneage = new StoneAge(Integer.parseInt(args[0]), true);
				stoneage.partie500Stat(Integer.parseInt(args[0]));
			}
			else {
				StoneAge stoneage = new StoneAge(4, true);
				stoneage.partie500Stat(4);
			}
		}
		catch (Exception e) {
			// Execution par defaut
			StoneAge stoneage = new StoneAge(4, true);
			stoneage.partie500Stat(4);
		}
	}
}
