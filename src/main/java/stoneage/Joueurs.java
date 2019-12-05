/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stoneage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Ossama
 */
public interface Joueurs {
	Random rand = new Random();
	 int cadeauRes(ArrayList<Integer> listeDe );
	 int choixTypeRes(int cout,Inventaire inv, int...typeDispo) ;
	int placerOutils(int nbOutils,int nbRessources, Zone zoneChoisi) ;
	 Choix placerOuvriers(ArrayList<Zone> LesZones ,Inventaire inv);
	 int getNum();
	 ArrayList<Integer> payBuildingWith(Inventaire inv,int cout,int types);


	/** La methode NourrirOuv va permettre au joueur de nourrir ses ouvriers,
	 *  il va d'abbord payer avec ses nourriture sinon bois , argile , pierre ..
	 *  mais s'il a pas aassez de nourriture ou de ressouce son score sera diminuer,
	 *  le joueur peut choisir entre donner des ressouces ou avoir -10 dans son score**/
	default  Map<String, Integer> NourrirOuv(Inventaire inv,  int nm) {
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


	default public boolean payerBatiment() {//le joueur choisi au hasard s'il prend la carte ou pas
		int a = rand.nextInt(2);
		if (a == 0) {
			return true;
		} else {
			return false;
		}
	}
	default public ArrayList<Integer> pouvoirZone(ArrayList<Zone> LesZones, Inventaire inv ){
			ArrayList<Integer> listZoneDispo = new ArrayList<>();
			//*****************Si Jeu entre 2 joueurs******************//
			if (StoneAge.getNbJoueurTotal() == 2) {
				for (int i = 0; i < LesZones.size(); i++) {
					if (LesZones.get(i).getNbPlaceDispo() != 0 && inv.listeZonesJouer.get(i) != true && LesZones.get(i).nbJoueur == 0 && i != 15 && i != 1 && i != 9 && i != 10 && i != 13 && i != 14) {
						listZoneDispo.add(i);
					}
					if (i == 1 && inv.listeZonesJouer.get(i) != true) {
						listZoneDispo.add(i);
					}
					if (i == 15 && inv.getNbOuvrierDispo() >= 2 && inv.getNbOuvrier() < 10 && inv.listeZonesJouer.get(i) != true && LesZones.get(i).getNbPlaceDispo() != 0) {
						listZoneDispo.add(i);
					}
				}
			}
			//*****************Si Jeu entre 3 joueurs******************//
			else if (StoneAge.getNbJoueurTotal() == 3) {
				for (int i = 0; i < LesZones.size(); i++) {
					if (LesZones.get(i).getNbPlaceDispo() != 0 && inv.listeZonesJouer.get(i) != true && LesZones.get(i).nbJoueur <= 3 && i != 1 && i != 10 && i != 14) {
						listZoneDispo.add(i);
					}
					if (i == 1 && inv.listeZonesJouer.get(i) != true) {
						listZoneDispo.add(i);
					}
					if (i == 15 && inv.getNbOuvrierDispo() >= 2 && inv.listeZonesJouer.get(i) != true && LesZones.get(i).getNbPlaceDispo() != 0) {
						listZoneDispo.add(i);
					}
				}
			}
			else if (StoneAge.getNbJoueurTotal() == 4) {
				for (int i = 0; i < LesZones.size(); i++) {
					if (LesZones.get(i).getNbPlaceDispo() != 0 && inv.listeZonesJouer.get(i) != true && i != 15 && LesZones.get(i).nbJoueur <= 2 && i != 1) {
						listZoneDispo.add(i);
					}
					if (i == 1 && inv.listeZonesJouer.get(i) != true) {
						listZoneDispo.add(i);
					}
					if (i == 15 && inv.getNbOuvrierDispo() >= 2 && inv.listeZonesJouer.get(i) != true && LesZones.get(i).getNbPlaceDispo() != 0) {
						listZoneDispo.add(i);
					}
				}
			}
			return listZoneDispo;

		}
	default void resolution(Inventaire inv,ArrayList<Integer> res,int cout){
		inv.setScore(inv.getScore()+res.get(0)*6+res.get(1)*5+res.get(2)*4+res.get(3)*3);
		inv.setNbOr(inv.getNbOr()-res.get(0));
		inv.setNbPierre(inv.getNbPierre()-res.get(1));
		inv.setNbArgile(inv.getNbArgile()-res.get(2));
		inv.setNbBois(inv.getNbBois()-res.get(3));
		inv.setNbRessource(inv.getNbRessource() - cout);
	}

}
