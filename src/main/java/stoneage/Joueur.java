package stoneage;
import java.util.ArrayList;
import java.util.Random;

/**
 * Cette classe correspond au joueur qui n'a pas de stratégie de jeu.
 * Tous ces choix sont aléatoires.
 * @see #rand
 *		Il joue de manière aléatoire pour ce qui est du placement d'un outil ou non.
 *		Il place ses ouvrier aléatoirement (choix de zone et nombre d'ouvriers).
 *		Prend les cartes de manière aléatoire.
 * @see #name
 * 		Le joueur a un nom.
 * @see int#num
 * 		Le joueur a un numéro (1, 2, 3 ou 4).
 **/
public class Joueur implements Joueurs {
	Random rand = new Random();
	String name;
	private int num;

	/**
	 * Constructeur de la classe Joueur
	 * @param name:
	 *            Nom du joueur.
	 * @param num :
	 *            Numéro du joueur.
	 */
	Joueur(String name, int num) {
		this.name = name;
		this.num = num;
	}

	/**
	 * Méthode qui permet au joueur de choisir s'il peut placer des outils ou pas lorsqu'il récupère ses gains.
	 * Le nombre d'outils utilisés est aléatoire.
	 * @param nbOutils:
	 *                Nombre d'outils du joueur.
	 *
	 * @param nbRessources:
	 *                    Nombre de ressources du joueur
	 * @param zoneChoisi:
	 *                  Zone sur laquelle est le joueur
	 * @return le nombre d'outils que le joueur va utiliser.
	 */
	public int placerOutils(int nbOutils, int nbRessources, Zone zoneChoisi) {
		int OutilChoisie = rand.nextInt(nbOutils + 1);
		return OutilChoisie;
	}

	/**
	 *  Méthode qui permet au joueur de choisir la ressource cadeau de la carte civilisation au hazard
	 * @param listeDe:
	 *               Liste des dés que le joueur a lancé.
	 * @return un entier qui correspond à un numéro de dé (de 1 à 6).
	 */
	public int cadeauRes(ArrayList<Integer> listeDe) {
		return listeDe.get(rand.nextInt(listeDe.size()));
	}

	/**
	 * Méthode qui permet au joueur de choisir la ressource pour payer ses dettes.
	 * @param cout:
	 *            Coût de la carte.
	 * @param inv:
	 *           Inventaire du joueur.
	 * @param typeDispo:
	 *                 Type de ressource que le joueur possède.
	 *                 3:bois
	 *                 4:Argile
	 *                 5:Pierre
	 *                 6:Or
	 *
	 * @return  entier qui correspond à la ressource utilisé pour payer:
	 *		-1: S'il ne veut pas prendre la carte
	 * 		3: Bois
	 * 		4: Argile
	 * 		5: Pierre
	 * 		6: Or
	 */
	public int choixTypeRes(int cout, Inventaire inv, int... typeDispo) {
		ArrayList<Integer> listTypeDispo = new ArrayList(); //transforme le tableau en liste
		for (int i = 0; i < typeDispo.length; i++) {
			listTypeDispo.add(typeDispo[i]);
		}
		if (inv.getNbBois() < cout) {
			// s'il ya pas assez de bois on enleve l'indice de cette zone de la liste
			listTypeDispo.remove(listTypeDispo.indexOf(3));
		}
		if (inv.getNbArgile() < cout) {
			listTypeDispo.remove(listTypeDispo.indexOf(4));
		}
		if (inv.getNbPierre() < cout) {
			listTypeDispo.remove(listTypeDispo.indexOf(5));
		}
		if (inv.getNbOr() < cout) {
			listTypeDispo.remove(listTypeDispo.indexOf(6));
		}
		if (listTypeDispo.size() > 0 && rand.nextBoolean()) { //le joueur decide de prendre cette carte si il a acces de ressource et si il a envie (true/false)
			int i = rand.nextInt((listTypeDispo.size()));
			return listTypeDispo.get(i);
		} else {
			return (-1);
		}
	}

	/**
	 *Méthode qui permet à un joueur de choisir une zone et placer un nombre aléatoire d'ouvriers dessus.
	 * @param LesZones:
	 *                Liste de toutes les zones.
	 * @param inv:
	 *           Inventaire du joueur.
	 * @return un Choix
	 * 		@see Choix
	 */
	public Choix placerOuvriers(ArrayList<Zone> LesZones, Inventaire inv) {
		//*****************Si Jeu entre 2 joueurs******************//
		if (StoneAge.getNbJoueurTotal() == 2) {
			ArrayList<Integer> listZoneDispo = pouvoirZone( LesZones,inv );
			int zoneChoisie = listZoneDispo.get(rand.nextInt(listZoneDispo.size()));
			if (zoneChoisie == 15) {
				return new Choix(zoneChoisie, 2);
			}
			// le joueur peut choisir la zone "Hutte" si est seulement si il a 2 ouvrier disponible,
			// la zone n'est prise et que au moin une des zonnes "Champ" et "fabrication d'outil" est disponnible
			//La zonne Hutte accepte obligatoirement 2 ouvrier
			int nbOuvChoisie = rand.nextInt(Math.min(inv.getNbOuvrierDispo(), LesZones.get(zoneChoisie).getNbPlaceDispo())) + 1;
			//IA simple qui choisit nombre d'ouvrier qu'elle va poser sur cette zone au hazard
			//le nombre doit etres inferieur au nombre de place disponnible de la zone et inferieur au nombre d'ouvrier dispo de la zone
			return new Choix(zoneChoisie, nbOuvChoisie);
		}
			//*****************Si Jeu entre 3 joueurs******************//
		else if (StoneAge.getNbJoueurTotal() == 3) {
			ArrayList<Integer> listZoneDispo  =pouvoirZone( LesZones,inv );
			if (listZoneDispo.size() == 0) {
				return null;
			}
			int zoneChoisie = listZoneDispo.get(rand.nextInt(listZoneDispo.size()));
			if (listZoneDispo.size() < 3) {
				return new Choix(zoneChoisie,Math.min(inv.getNbOuvrierDispo(), LesZones.get(zoneChoisie).getNbPlaceDispo()));
			}
			// le joueur peut choisir la zone "Hutte" si est seulement si il a 2 ouvrier disponnible,
			// la zone n'est prise et que au moin une des zonnes "Champ" et "fabrication d'outil" est disponnible
			//La zonne Hutte accepte obligatoirement 2 ouvrier
			int nbOuvChoisie = rand.nextInt(Math.min(inv.getNbOuvrierDispo(), LesZones.get(zoneChoisie).getNbPlaceDispo())) + 1;
			//IA simple qui choisit nombre d'ouvrier qu'elle va poser sur cette zone au hazard
			//le nombre doit etres inferieur au nombre de place disponnible de la zone et inferieur au nombre d'ouvrier dispo de la zone
			return new Choix(zoneChoisie, nbOuvChoisie);
		}
		//*****************Si Jeu entre 4 joueurs******************//
		else if (StoneAge.getNbJoueurTotal() == 4) {
			ArrayList<Integer> listZoneDispo =pouvoirZone( LesZones,inv );
			int zoneChoisie = listZoneDispo.get(rand.nextInt(listZoneDispo.size()));
			if (zoneChoisie == 15) {
				return new Choix(zoneChoisie, 2);
			}

			// le joueur peut choisir la zone "Hutte" si est seulement si il a 2 ouvrier disponnible,
			// la zone n'est prise et que au moin une des zonnes "Champ" et "fabrication d'outil" est disponnible
			//La zonne Hutte accepte obligatoirement 2 ouvrier
			int nbOuvChoisie = rand.nextInt(Math.min(inv.getNbOuvrierDispo(), LesZones.get(zoneChoisie).getNbPlaceDispo())) + 1;
			//IA simple qui choisit nombre d'ouvrier qu'elle va poser sur cette zone au hazard
			//le nombre doit etres inferieur au nombre de place disponnible de la zone et inferieur au nombre d'ouvrier dispo de la zone
			return new Choix(zoneChoisie, nbOuvChoisie);
		}else {
				return null;

		}
	}

	@Override
	public String toString(){
		return name;
	}
	@Override
	public int getNum() {
		return num;
	}
}