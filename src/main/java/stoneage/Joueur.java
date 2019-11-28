package stoneage;           //**************ajout de 2 methode choix entre 4 ressource ou entre 2 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/*
 *Cette Class est les joueurs qui Normal qui va jouer au hazard ,
 * elle permet de faire des choix au hazard en respectant les regles du jeux.
 *
 */
public class Joueur implements Joueurs {
	Random rand = new Random();
	String name;
	private int num;

	Joueur(String name, int num) {
		this.name = name;
		this.num = num;
	}

	@Override
	public int getNum() {
		return num;
	}

	/*La methode placerOutils va permettre au joueur de choisir s'il peut ou pas placer des outils lorsqu'il recupere ses gains,
	 * Ainsi que le nombre d'outil qu'il va utiliser  au hazard */
	public int placerOutils(int nbOutils, int nbRessources, Zone zoneChoisi) {
		/* cette methode va permettre au joueur de choisir le nombre d'outil d'il va utilisier*/
		int OutilChoisie = rand.nextInt(nbOutils + 1);
		return OutilChoisie;
	}

	/*La methode cadeauRes va permettre au joueur de choisir la resouce cadeau de la carte civilisation au hazard*/
	public int cadeauRes(ArrayList<Integer> listeDe) {
		return listeDe.get(rand.nextInt(listeDe.size()));
	}

	/*La  methode choixTypesRes permet au joueure de choisir la resource qu'il va utiliser pour payer ses dettes */
	public int choixTypeRes(int cout, Inventaire inv, int... typeDispo) {
		ArrayList<Integer> listTypeDispo = new ArrayList(); //transforme le tableau en liste
		for (int i = 0; i < typeDispo.length; i++) {
			listTypeDispo.add(typeDispo[i]);
		}
		/* le joueur retourne :
		 * -1: s'il ne veut pas prendre la carte
		 * 3: s'il choisi de la payer avec bois
		 * 4:  Argile
		 * 5: Pierre
		 * 6: Or
		 */

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

	/*Cette metode va permettre au joueur de choisir la zone et le nombre d'ouvrier qu'il va posé dans celle ci au hazard */
	public Choix placerOuvriers(ArrayList<Zone> LesZones, Inventaire inv) {
		if (StoneAge.getNbJoueurTotal() == 2) {
			if ((inv.getNbZoneJouer() < 6 && inv.ouvrierDispo())) {
				int i = 0;
				int[] tabZoneDispo = {0, 1, 2, 3, 4, 5, 6, 7, 8, 11, 12};
				int zoneChoisie = tabZoneDispo[rand.nextInt(11)];
				while (inv.listeZonesJouer.get(zoneChoisie) == true || LesZones.get(zoneChoisie).getNbPlaceDispo() == 0 || LesZones.get(zoneChoisie).nbJoueur >= 1) {
					zoneChoisie = tabZoneDispo[rand.nextInt(11)];
					i++;
					if (i == 10) {
						zoneChoisie = 1;
						break;
					}
				}

				//IA simple qui choisit une zone au hazard
				int nbOuvChoisie = rand.nextInt(Math.min(inv.getNbOuvrierDispo(), LesZones.get(zoneChoisie).getNbPlaceDispo())) + 1;
				//IA simple qui choisit nombre d'ouvrier qu'elle va poser sur cette zone au hazard 
				//le nombre doit etres inferieur au nombre de place disponnible de la zone et inferieur au nombre d'ouvrier dispo de la zone 
				return new Choix(zoneChoisie, nbOuvChoisie);
			} else {
				return null;
			}
		} else if (StoneAge.getNbJoueurTotal() == 3) {
			if ((inv.getNbZoneJouer() < 6 && inv.ouvrierDispo())) {
				int i = 0;
				int[] tabZoneDispo = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13};
				int zoneChoisie = tabZoneDispo[rand.nextInt(13)];
				while (inv.listeZonesJouer.get(zoneChoisie) || LesZones.get(zoneChoisie).getNbPlaceDispo() == 0 || LesZones.get(zoneChoisie).nbJoueur >= 2) {
					zoneChoisie = tabZoneDispo[rand.nextInt(13)];
					i++;
					if (i == 10) {
						zoneChoisie = 1;
						break;
					}
				}
				//IA simple qui choisit une zone au hazard
				int nbOuvChoisie = rand.nextInt(Math.min(inv.getNbOuvrierDispo(), LesZones.get(zoneChoisie).getNbPlaceDispo())) + 1;
				//IA simple qui choisit nombre d'ouvrier qu'elle va poser sur cette zone au hazard 
				//le nombre doit etres inferieur au nombre de place disponnible de la zone et inferieur au nombre d'ouvrier dispo de la zone 
				return new Choix(zoneChoisie, nbOuvChoisie);
			} else {
				return null;
			}
		} else {
			if ((inv.getNbZoneJouer() < 6 && inv.ouvrierDispo())) {

				int zoneChoisie = rand.nextInt(15);
				while (inv.listeZonesJouer.get(zoneChoisie) == true || LesZones.get(zoneChoisie).getNbPlaceDispo() == 0) {
					zoneChoisie = rand.nextInt(15);
				}
				//IA simple qui choisit une zone au hazard
				int nbOuvChoisie = rand.nextInt(Math.min(inv.getNbOuvrierDispo(), LesZones.get(zoneChoisie).getNbPlaceDispo())) + 1;
				//IA simple qui choisit nombre d'ouvrier qu'elle va poser sur cette zone au hazard 
				//le nombre doit etres inferieur au nombre de place disponnible de la zone et inferieur au nombre d'ouvrier dispo de la zone 
				return new Choix(zoneChoisie, nbOuvChoisie);
			} else {
				return null;
			}
		}
	}

	public boolean payerBatiment() {//le joueur choisi au hasard s'il prend la carte ou pas
		int a = rand.nextInt(2);
		if (a == 0) {
			return true;
		} else {
			return false;
		}
	}


	public Map<String, Integer> NourrirOuv(Inventaire inv,  int nm) {
		Map<String, Integer> choixNourriture = new HashMap<>();
		int choixJ = rand.nextInt(2); // choix du joueur si oui paye avec ressource sinon paye avec score
		if (inv.getNbRessource()+ inv.getNourriture() < nm){
			choixNourriture.put("Point de Score", 10); //si on a pas assez de ressource le score diminue de 10 pts
			return choixNourriture;
		}
		if (inv.getNourriture()>0){
			choixNourriture.put("Nourriture", inv.getNourriture());
		}
		if (inv.getNbRessource() >= nm) {
			if (inv.getNbBois() > 0 && inv.getNbBois() < nm) { // si la quantite de bois est superieur a 0 et moin que la nourriture manquante
				choixNourriture.put("Bois", inv.getNbBois());
				nm -= inv.getNbBois();
			} else if (inv.getNbBois() >= nm) {
				choixNourriture.put("Bois", nm);
				return choixNourriture;
			}
			if (inv.getNbArgile() > 0 && inv.getNbArgile() < nm) {
				choixNourriture.put("Argile", inv.getNbArgile());
				nm -= inv.getNbArgile();
			} else if (inv.getNbArgile() >= nm) {
				choixNourriture.put("Argile", nm);
				return choixNourriture;
			}
			if (inv.getNbPierre() > 0 && inv.getNbPierre() < nm) {
				choixNourriture.put("Pierre", inv.getNbPierre());
				nm -= inv.getNbPierre();
			} else if (inv.getNbPierre() > nm) {
				choixNourriture.put("Pierre", nm);
				return choixNourriture;
			}

			if (inv.getNbOr() > nm) {
				choixNourriture.put("Or", nm);
				return choixNourriture;
			}
		}
		return choixNourriture;
	}




        @Override
        public String toString(){
            return name;
        }	
}