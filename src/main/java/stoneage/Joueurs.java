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

public interface Joueurs {
	Random rand = new Random();
	int cadeauRes(ArrayList<Integer> listeDe );
	int choixTypeRes(int cout,Inventaire inv, int...typeDispo) ;
	int placerOutils(int nbOutils,int nbRessources, Zone zoneChoisi) ;
	Choix placerOuvriers(ArrayList<Zone> LesZones ,Inventaire inv);
	int getNum();
	
	 default  ArrayList<Integer> payBuildingWith(Inventaire inv,int cout,int types){
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
        
        default public ArrayList<Integer>payBuilding17(Inventaire inv){
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


	default  boolean payerBatiment() {//le joueur choisi au hasard s'il prend la carte ou pas
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
					if (LesZones.get(i).getNbPlaceDispo() != 0 && inv.listeZonesJouer.get(i) != true && LesZones.get(i).getNbJoueur() == 0 && i != 15 && i != 1 && i != 9 && i != 10 && i != 13 && i != 14) {
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
					if (LesZones.get(i).getNbPlaceDispo() != 0 && inv.listeZonesJouer.get(i) != true && LesZones.get(i).getNbJoueur() <= 3 && i != 1 && i != 10 && i != 14) {
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
					if (LesZones.get(i).getNbPlaceDispo() != 0 && inv.listeZonesJouer.get(i) != true && i != 15 && LesZones.get(i).getNbJoueur() <= 2 && i != 1) {
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
    default  ArrayList<Integer>payBuilding14and18(Inventaire inv, int nbRes){
	    //methode qui permet au joueur de payer 2 carte batiment
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
    default void resolution(Inventaire inv,ArrayList<Integer> res){
        inv.setScore(inv.getScore()+res.get(0)*6+res.get(1)*5+res.get(2)*4+res.get(3)*3);
        inv.lesRessources.get(6).subvaleur(res.get(0)) ;
        inv.lesRessources.get(5).subvaleur(res.get(1)) ;
        inv.lesRessources.get(4).subvaleur(res.get(2)) ;
        inv.lesRessources.get(3).subvaleur(res.get(3)) ;
    }

}
