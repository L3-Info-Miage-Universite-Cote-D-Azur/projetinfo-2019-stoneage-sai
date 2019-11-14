package stoneage;
import java.util.ArrayList;
import java.util.Random;

public class Joueur implements Joueurs {
	Random rand = new Random();

	@Override
	public void recupeRes(Inventaire inventaireJoueur, Zone zoneChoisi) {
		int nbRessources = zoneChoisi.lancéDeDés(zoneChoisi.getNbOuvriersPlaces());
		int OutilChoisie = rand.nextInt(inventaireJoueur.getNbOutils() + 1);

		nbRessources = nbRessources + OutilChoisie;
		nbRessources = nbRessources / zoneChoisi.niveauZone;
		inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils() - OutilChoisie);
		//recuperer les ressources gagner
		//ajouter les nouveau ressources a l'inventaire du joueur
		switch (zoneChoisi.niveauZone) {
			case 1:
				inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils() + 1);
			case 2:
				inventaireJoueur.setNourriture(inventaireJoueur.getNourriture() + nbRessources);
			case 3:
				inventaireJoueur.setNbBois(inventaireJoueur.getNbBois() + nbRessources);
				inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() + nbRessources);

				break;
			case 4:
				inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile() + nbRessources);
				inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() + nbRessources);

				break;
			case 5:
				inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre() + nbRessources);
				inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() + nbRessources);

				break;

			case 6:
				inventaireJoueur.setNbOr(inventaireJoueur.getNbOr() + nbRessources);
				inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() + nbRessources);

				break;
			default:
				break;
		}
		inventaireJoueur.addAvailableWorkers(zoneChoisi.getNbOuvriersPlaces());
		//recuperer les ouvriers
		zoneChoisi.resetNbOuvriersPlaces();
		//  zoneChoisi.setNbPlaceDispo(zoneChoisi.getNbPlaceZone());//quand on recupere les ouvriers,toutes les places deviennent disponibles.
	}

	@Override
	public Choix placerOuvriers(Inventaire inv) {
		if ((Zone.getListeZone() != null) && (Zone.getListeZoneDispo().size() > 0)) {
			int zoneChoisie = rand.nextInt(Zone.getListeZoneDispo().size());
			//IA simple qui choisit une zone au hazard
			int nbOuvChoisi = rand.nextInt(Math.min(inv.getNbOuvrierDispo(), (Zone.getPlaceDispoParZone().get(zoneChoisie) - 1))) + 1;
			//IA simple qui choisit nombre d'ouvrier qu'elle va poser sur cette zone au hazard
			//le nombre doit etres inferieur au nombre de place disponnible de la zone et inferieur au nombre d'ouvrier dispo de la zone
			if (Zone.getListeZoneDispo().get(zoneChoisie)) {
				return new Choix(Zone.getListeZone().get(zoneChoisie), nbOuvChoisi);
			}
		}
		return null;
	}
}

