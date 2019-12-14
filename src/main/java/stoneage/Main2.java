package stoneage;



public class Main2 {

	public static final void main(String [] args) {

		/**** Choisir "unePartie(nbJoueur)" pour lancer une seule partie.
		 * Choisir "partie500Stat(nbJoueur)" pour lancer 500 partie avec les statistiques.
		 * nbJoueur = Le nombre de joueur compris entre 2 et 4. 
		 * Pour les statistique il se peut que x joueur arrive en tete avec le meme score, alors les x joueur gagne la partie.
		 * C'est pour cela que le total des victoire des joueur dans les statistiques peut depasser 500.
		 * Mais il y a bien 500 parties jouées. ******/
		
		if (Integer.parseInt(args[0]) == 2 || Integer.parseInt(args[0]) == 3|| Integer.parseInt(args[0]) == 4) {
			StoneAge stoneage = new StoneAge(Integer.parseInt(args[0]), true);
			stoneage.partie500Stat(Integer.parseInt(args[0]));
		}
		else {
			// Execution par defaut
			StoneAge stoneage = new StoneAge(4, true);
			stoneage.partie500Stat(4);
		}
	}
}
