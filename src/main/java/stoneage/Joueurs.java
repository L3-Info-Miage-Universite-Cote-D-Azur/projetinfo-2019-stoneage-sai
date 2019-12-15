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
 * Interface Joueurs
 * Définit les méthodes par défaut que les joueurs vont utiliser.
 * Définit ce que chaque joueur a comme méthode:
 *      @see #cadeauRes(ArrayList)
 *      @see #choixTypeRes(int, Inventaire, int...)
 *      @see #placerOutils(int, int, Zone)
 *      @see #placerOuvriers(ArrayList, Inventaire)
 *      @see #getNum()
 */
public interface Joueurs {
	Random rand = new Random();
	int cadeauRes(ArrayList<Integer> listeDe );
	int choixTypeRes(int cout,Inventaire inv, int...typeDispo) ;
	int placerOutils(int nbOutils,int nbRessources, Zone zoneChoisi) ;
	Choix placerOuvriers(ArrayList<Zone> LesZones ,Inventaire inv);
	int getNum();
	Map<String ,Integer> lesResCouter = new HashMap<>();

    /**
     * Méthode pour payer un bâtiment avec des ressources.
     * @param inv:
     *           Inventaire du joueur
     * @param cout:
     *            Coût du bâtiment
     * @param types:
     *             Types de ressources pour payer le bâtiment
     * @return La liste des ressources utilisées en quantité (int)
     */
	default ArrayList<Integer> payBuildingWith(Inventaire inv,int cout,int types){
        ArrayList<Integer> res= new ArrayList();//res=[Or,Pierre,Argile,Bois]
        if (cout==5) {
            if (types==4) {//le joueur choisit de payer par Or,Pierre,Argile,Bois par ce que ca rapporte plus de points de score comme ca 
                if (inv.getNbRessource()>=cout && inv.getNbBois()>0 && inv.getNbArgile()>0 && inv.getNbPierre()>0 && inv.getNbOr()>0) {
                    if (inv.getNbOr()>=2) {
                        res.add(2);
                        res.add(1);
                        res.add(1);
                        res.add(1);
                    }
                    else{
                        if (inv.getNbPierre()>=2) {
                            res.add(1);
                            res.add(2);
                            res.add(1);
                            res.add(1);
                        }
                        else{

                            if (inv.getNbArgile()>=2) {
                                res.add(1);
                                res.add(1);
                                res.add(2);
                                res.add(1);
                            }
                            else if (inv.getNbBois()>=2) {
                                res.add(1);
                                res.add(1);
                                res.add(1);
                                res.add(2);
                            }
                        }
                    }
                }
            }
            if (types==2) {
                if (inv.getNbOr()+inv.getNbPierre()>=cout){
                    if (inv.getNbOr()>=3 && inv.getNbPierre()>=2) {
                        res.add(3);
                        res.add(2);
                        res.add(0);
                        res.add(0);
                    }
                    else if (inv.getNbPierre()>=3 && inv.getNbOr()>=2) {
                        res.add(2);
                        res.add(3);
                        res.add(0);
                        res.add(0);
                    }
                }
                if (inv.getNbOr()+inv.getNbArgile()>=cout && res.size()==0 ) {
                    if (inv.getNbOr()>=3 && inv.getNbArgile()>=2) {
                        res.add(3);
                        res.add(0);
                        res.add(2);
                        res.add(0);
                    }
                    else if (inv.getNbArgile()>=3 && inv.getNbOr()>=2) {
                        res.add(2);
                        res.add(0);
                        res.add(3);
                        res.add(0);
                    }
                }
                if (inv.getNbOr()+inv.getNbBois()>=cout && res.size()==0) {
                    if (inv.getNbOr()>=3 && inv.getNbBois()>=2) {
                        res.add(3);
                        res.add(0);
                        res.add(0);
                        res.add(2);
                    }
                    else if (inv.getNbBois()>=3 && inv.getNbOr()>=2) {
                        res.add(2);
                        res.add(0);
                        res.add(0);
                        res.add(3);
                    }
                }
                if (inv.getNbPierre()+inv.getNbArgile()>=cout && res.size()==0) {
                    if (inv.getNbPierre()>=3 && inv.getNbArgile()>=2) {
                        res.add(0);
                        res.add(3);
                        res.add(2);
                        res.add(0);
                    }
                    else if (inv.getNbArgile()>=3 && inv.getNbPierre()>=2) {
                        res.add(0);
                        res.add(2);
                        res.add(3);
                        res.add(0);
                    }
                }
                if (inv.getNbPierre()+inv.getNbBois()>=cout && res.size()==0) {
                    if (inv.getNbPierre()>=3 && inv.getNbBois()>=2) {
                        res.add(0);
                        res.add(3);
                        res.add(0);
                        res.add(2);
                    }
                    else if (inv.getNbBois()>=3 && inv.getNbPierre()>=2) {
                        res.add(0);
                        res.add(2);
                        res.add(0);
                        res.add(3);
                    }
                }
                if (inv.getNbArgile()+inv.getNbBois()>=cout && res.size()==0) {
                    if (inv.getNbArgile()>=3&& inv.getNbBois()>=2) {
                        res.add(0);
                        res.add(0);
                        res.add(3);
                        res.add(2);
                    }
                    else if (inv.getNbBois()>=3&& inv.getNbArgile()>=2) {
                        res.add(0);
                        res.add(0);
                        res.add(2);
                        res.add(3);
                    }
                }
            }
        }
        if (cout==4) {
            if (types==4) {
                if (inv.getNbRessource()>=cout && inv.getNbBois()>0 && inv.getNbArgile()>0 && inv.getNbPierre()>0 && inv.getNbOr()>0) {
                    res.add(1);
                    res.add(1);
                    res.add(1);
                    res.add(1);
                }
            }
            if (types==2) {
                if (inv.getNbOr()+inv.getNbPierre()>=cout){
                    if (inv.getNbOr()>=2 && inv.getNbPierre()>=2) {
                        res.add(2);
                        res.add(2);
                        res.add(0);
                        res.add(0);
                    }
                    else if (inv.getNbOr()>=3) {
                        res.add(3);
                        res.add(1);
                        res.add(0);
                        res.add(0);
                    }
                    else if (inv.getNbPierre()>=3) {
                        res.add(1);
                        res.add(3);
                        res.add(0);
                        res.add(0);
                    }
                }
                if (inv.getNbOr()+inv.getNbArgile()>=cout && res.size()==0 ) {
                    if (inv.getNbOr()>=2 && inv.getNbArgile()>=2  ) {
                        res.add(2);
                        res.add(0);
                        res.add(2);
                        res.add(0);
                    }
                    else if (inv.getNbOr()>=3 ) {
                        res.add(3);
                        res.add(0);
                        res.add(1);
                        res.add(0);
                    }
                    else if (inv.getNbArgile()>=3 ) {
                        res.add(1);
                        res.add(0);
                        res.add(3);
                        res.add(0);
                    }
                }
                if (inv.getNbOr()+inv.getNbBois()>=cout && res.size()==0) {
                    if (inv.getNbOr()>=2 &&  inv.getNbBois()>=2) {
                        res.add(2);
                        res.add(0);
                        res.add(0);
                        res.add(2);
                    }
                    else if (inv.getNbOr()>=3 ) {
                        res.add(3);
                        res.add(0);
                        res.add(0);
                        res.add(1);
                    }
                    else if (inv.getNbBois()>=3 ) {
                        res.add(1);
                        res.add(0);
                        res.add(0);
                        res.add(3);
                    }
                }
                if (inv.getNbPierre()+inv.getNbArgile()>=cout && res.size()==0) {
                    if (inv.getNbPierre()>=2&& inv.getNbArgile()>=2) {
                        res.add(0);
                        res.add(2);
                        res.add(2);
                        res.add(0);
                    }
                    else if (inv.getNbPierre()>=3){
                        res.add(0);
                        res.add(3);
                        res.add(1);
                        res.add(0);
                    }
                    else if (inv.getNbArgile()>=3){
                        res.add(0);
                        res.add(1);
                        res.add(3);
                        res.add(0);
                    }
                }
                if (inv.getNbPierre()+inv.getNbBois()>=cout && res.size()==0) {
                    if (inv.getNbPierre()>=2 && inv.getNbBois()>=2 ) {
                        res.add(0);
                        res.add(2);
                        res.add(0);
                        res.add(2);
                    }
                    else if (inv.getNbPierre()>=3){
                        res.add(0);
                        res.add(3);
                        res.add(0);
                        res.add(1);
                    }
                    else if (inv.getNbBois()>=3){
                        res.add(0);
                        res.add(1);
                        res.add(0);
                        res.add(3);
                    }
                }
                if (inv.getNbArgile()+inv.getNbBois()>=cout && res.size()==0) {
                    if (inv.getNbArgile()>=2 && inv.getNbBois()>=2) {
                        res.add(0);
                        res.add(0);
                        res.add(2);
                        res.add(2);
                    }
                    else if (inv.getNbArgile()>=3){
                        res.add(0);
                        res.add(0);
                        res.add(3);
                        res.add(1);
                    }
                    else if (inv.getNbBois()>=3){
                        res.add(0);
                        res.add(0);
                        res.add(1);
                        res.add(3);
                    }
                }
            }
        }
        return res;
    }

    /**
     * Méthode pour payer les 3 bâtiments où le nomre de ressources est minimum 1 et maximum 7
     * @param inv:
     *           Inventaire du joueur
     * @return La liste des ressources utilisées en quantité (int)
     */
    default ArrayList<Integer> payBuilding17(Inventaire inv){
            ArrayList<Integer> res=new ArrayList();
            int nbRes=7;
            if (inv.getNbRessource()>=nbRes) {
                if (inv.getNbOr()>=nbRes) {
                    res.add(nbRes);
                    res.add(0);
                    res.add(0);
                    res.add(0);
                    nbRes=0;
                }
                else{
                    res.add(inv.getNbOr());
                    nbRes=nbRes-inv.getNbOr();
                }
                if (nbRes!=0) {
                    if (inv.getNbPierre()>=nbRes) {
                        res.add(nbRes);
                        res.add(0);
                        res.add(0);
                        nbRes=0;
                    }
                    else{
                        res.add(inv.getNbPierre());
                        nbRes=nbRes-inv.getNbPierre();
                    }
                }
                if (nbRes!=0) {
                    if (inv.getNbArgile()>=nbRes) {
                        res.add(nbRes);
                        res.add(0);
                        nbRes=nbRes-nbRes;
                    }
                    else{
                        res.add(inv.getNbArgile());
                        nbRes=nbRes-inv.getNbArgile();
                    }
                }
                if (nbRes!=0) {
                    if (inv.getNbBois()>=nbRes) {
                        res.add(nbRes);
                        nbRes=nbRes-nbRes;
                    }
                    else{
                        res.add(inv.getNbBois());
                        nbRes=nbRes-inv.getNbBois();
                    }
                }
            }
            else{
                res.add(inv.getNbOr());
                res.add(inv.getNbPierre());
                res.add(inv.getNbArgile());
                res.add(inv.getNbBois());
            }
            return res;
        }

	/** Méthode qui permet au joueur de nourrir ses ouvriers.
	 *  Il va d'abord payer avec ses nourritures, sinon en bois, en argile, en pierre ou en or.
	 *  S'il n'a pas assez de nourritures et de ressources son score diminue.
	 *  le joueur peut choisir entre donner des ressouces ou perdre 10 point dans son score**/
	default Map<String, Integer> NourrirOuv(Inventaire inv,  int nm) {
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

    /**
     * Méthode pour savoir si un joueur prend la carte bâtiment ou non.
     * @return boolean true s'il la prend, false sinon
     */
	default boolean payerBatiment() {//le joueur choisi au hasard s'il prend la carte ou pas
		int a = rand.nextInt(2);
		if (a == 0) {
			return true;
		} else {
			return false;
		}
	}

    /**
     * Méthode qui permet de savoir quelles zones sont encore disponibles à jouer.
     * @param LesZones:
     *                Liste des toutes les zones
     * @param inv:
     *           Inventaire du joueur
     * @return Liste des zones encore disponibles pour jouer dessus.
     */
	default ArrayList<Integer> pouvoirZone(ArrayList<Zone> LesZones, Inventaire inv ){
	    ArrayList<Integer> listZoneDispo = new ArrayList<>();
	    //*****************Si Jeu entre 2 joueurs******************//
        if (StoneAge.getNbJoueurTotal() == 2) {
            for (int i = 0; i < LesZones.size(); i++) {
                if (LesZones.get(i).getNbPlaceDispo() != 0 && !inv.listeZonesJouer.get(i) && LesZones.get(i).getNbJoueur() == 0 && i != 15 && i != 1 && i != 9 && i != 10 && i != 13 && i != 14) {
                    listZoneDispo.add(i);
                }
                if (i == 1 && !inv.listeZonesJouer.get(i)) {
                    listZoneDispo.add(i);
                }
                if (i == 15 && inv.getNbOuvrierDispo() >= 2 && inv.getNbOuvrier() < 10 && !inv.listeZonesJouer.get(i) && LesZones.get(i).getNbPlaceDispo() != 0) {
                    listZoneDispo.add(i);
                }
            }
        }
        //*****************Si Jeu entre 3 joueurs******************//
        else if (StoneAge.getNbJoueurTotal() == 3) {
            for (int i = 0; i < LesZones.size(); i++) {
                if (LesZones.get(i).getNbPlaceDispo() != 0 && !inv.listeZonesJouer.get(i) && LesZones.get(i).getNbJoueur() <= 3 && i != 1 && i != 10 && i != 14) {
                    listZoneDispo.add(i);
                }
                if (i == 1 && !inv.listeZonesJouer.get(i)) {
                    listZoneDispo.add(i);
                }
                if (i == 15 && inv.getNbOuvrierDispo() >= 2 && !inv.listeZonesJouer.get(i) && LesZones.get(i).getNbPlaceDispo() != 0) {
                    listZoneDispo.add(i);
                }
            }
        }
        else if (StoneAge.getNbJoueurTotal() == 4) {
            for (int i = 0; i < LesZones.size(); i++) {
                if (LesZones.get(i).getNbPlaceDispo() != 0 && !inv.listeZonesJouer.get(i) && i != 15 && LesZones.get(i).getNbJoueur() <= 2 && i != 1) {
                    listZoneDispo.add(i);
                }
                if (i == 1 && !inv.listeZonesJouer.get(i)) {
                    listZoneDispo.add(i);
                }
                if (i == 15 && inv.getNbOuvrierDispo() >= 2 && !inv.listeZonesJouer.get(i) && LesZones.get(i).getNbPlaceDispo() != 0) {
                    listZoneDispo.add(i);
                }
            }
        }
        return listZoneDispo;
	}

    /**
     * Méthode pour payer 2 cartes bâtiment
     * @param inv:
     *           Inventaire du joueur
     * @param nbRes:
     *             Nombre de ressources
     * @return une liste de 4 entiers correspondant au nombre de ressources mise pour payer les cartes.
     */
    default ArrayList<Integer>payBuilding14and18(Inventaire inv, int nbRes){
        ArrayList<Integer> res = new ArrayList();
        if (inv.getNbOr()+ inv.getNbPierre()  + inv.getNbArgile()>= nbRes) {
            int nbResMise1 = Math.min(nbRes, inv.getNbOr());
            int nbResMise2 = Math.min(nbRes - nbResMise1, inv.getNbPierre());
            int nbResMise3 = Math.min(nbRes - nbResMise2, inv.getNbArgile());
            res.add(nbResMise1);
            res.add(nbResMise2);
            res.add(nbResMise3);
            res.add(0);
            nbRes = 0;
        }
        else if (inv.getNbOr()+ inv.getNbPierre()  + inv.getNbBois()>= nbRes) {
            int nbResMise1 = Math.min(nbRes, inv.getNbOr());
            int nbResMise2 = Math.min(nbRes - nbResMise1, inv.getNbPierre());
            int nbResMise3 = Math.min(nbRes - nbResMise2, inv.getNbBois());
            res.add(nbResMise1);
            res.add(nbResMise2);
            res.add(0);
            res.add(nbResMise3);
            nbRes = 0;
        }
        else if (inv.getNbOr()+ inv.getNbArgile()  + inv.getNbBois()>= nbRes) {
            int nbResMise1 = Math.min(nbRes, inv.getNbOr());
            int nbResMise2 = Math.min(nbRes - nbResMise1, inv.getNbArgile());
            int nbResMise3 = Math.min(nbRes - nbResMise2, inv.getNbBois());
            res.add(nbResMise1);
            res.add(0);
            res.add(nbResMise2);
            res.add(nbResMise3);
            nbRes = 0;
        }
        else if (inv.getNbPierre() + inv.getNbArgile()+ inv.getNbBois()>= nbRes) {
            int nbResMise1 = Math.min(nbRes, inv.getNbPierre());
            int nbResMise2 = Math.min(nbRes - nbResMise1, inv.getNbArgile());
            int nbResMise3 = Math.min(nbRes - nbResMise2, inv.getNbBois());
            res.add(0);
            res.add(nbResMise1);
            res.add(nbResMise2);
            res.add(nbResMise3);
            nbRes = 0;
        }
        return res;
    }

    /**
     * Méthode qui permet d'enlever les ressources de l'inventaire du joueur.
     * @param inv:
     *           Inventaire du joueur.
     * @param res:
     *           Liste des ressources disponibles (quantité)
     */
    default void resolution(Inventaire inv,ArrayList<Integer> res){
        lesResCouter.clear();
        inv.setScore(inv.getScore()+res.get(0)*6+res.get(1)*5+res.get(2)*4+res.get(3)*3);
        inv.lesRessources.get(6).subvaleur(res.get(0)) ;
        inv.lesRessources.get(5).subvaleur(res.get(1)) ;
        inv.lesRessources.get(3).subvaleur(res.get(3)) ;
        inv.lesRessources.get(4).subvaleur(res.get(2)) ;
        for (int i=0 ;i<4; i++){
            if (res.get(i)>0){
                lesResCouter.put(inv.lesRessources.get(6-i).getNom() ,res.get(i)); // pour afficher le cout de la carte batiment
            }
        }
    }

}
