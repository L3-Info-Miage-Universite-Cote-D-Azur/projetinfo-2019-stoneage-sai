package stoneage;



public class Main1 {

	public static final void main(String [] args) {

		/****** Choisir "unePartie(nbJoueur)" pour lancer une seule partie.
		 * **** Choisir "partie500Stat(nbJoueur)" pour lancer 500 partie avec les statistiques.
		 * **** nbJoueur = Le nombre de joueur compris entre 2 et 4. ******/

		StoneAge stoneage = new StoneAge(4, false);
		stoneage.unePartie(4);
	}
}
