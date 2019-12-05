package stoneage;           //**************ajout de 2 methode choix entre 4 ressource ou entre 2 
import java.util.ArrayList;
import java.util.Random;
/**
 *Cette Classe est le joueur qui ne possede pas de strategie de jeu. 
 *C'est un joueur au choix entierement aléatoire.
 *Tout en respectant les regles du jeu a 2,3 et 4 joueur,
 *il joue de maniere aléatoire pour ce qui est de du choix ou non de placer un outil,
 *du choix de où placer ses ouvrirer , de prendre une carte batiment etc..
 *
 *
 **/
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

	/**La methode placerOutils va permettre au joueur de choisir s'il peut ou pas placer des outils lorsqu'il recupere ses gains,
	 * Ainsi que le nombre d'outil qu'il va utiliser  au hazard **/
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

	/**Cette metode  placerOuvriers va permettre au joueur de choisir la zone et le nombre d'ouvrier qu'il va posé dans celle ci au hazard **/
	public Choix placerOuvriers(ArrayList<Zone> LesZones, Inventaire inv) {
		//*****************Si Jeu entre 2 joueurs******************//
		if (StoneAge.getNbJoueurTotal() == 2) {
			ArrayList<Integer> listZoneDispo = pouvoirZone( LesZones,inv );
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


        public ArrayList<Integer> payBuildingWith(Inventaire inv,int cout,int types){
        ArrayList<Integer> res= new ArrayList<Integer>();//res=[Or,Pierre,Argile,Bois]
        if (cout==5) {
            if (types==4) {//le joueur choisit de payer par Or,Pierre,Argile,Bois par ce que ca rapporte plus de points de score comme ca 
                if (inv.getNbRessource()>=cout && inv.getNbBois()!=0 && inv.getNbArgile()!=0 && inv.getNbPierre()!=0 && inv.getNbOr()!=0) {
                    if (inv.getNbOr()>=2) {
                        res.add(2);
                        res.add(1);
                        res.add(1);
                        res.add(1);
                    }
                    else{
                        res.add(1);
                        if (inv.getNbPierre()>=2) {
                            res.add(2);
                            res.add(1);
                            res.add(1);
                        }
                        else{
                            res.add(1);
                            if (inv.getNbArgile()>=2) {
                                res.add(2);
                                res.add(1);
                            }
                            else{
                                res.add(1);
                                res.add(2);
                            }
                        }
                    }
                }
            }
            if (types==2) {
                if (inv.getNbOr()+inv.getNbPierre()>=cout){
                    if (inv.getNbOr()>=3) {
                        res.add(3);
                        res.add(2);
                        res.add(0);
                        res.add(0);
                    }
                    else{
                        res.add(2);
                        res.add(3);
                        res.add(0);
                        res.add(0);
                    }
                }
                if (inv.getNbOr()+inv.getNbArgile()>=cout && res.size()==0 ) {
                    if (inv.getNbOr()>=3) {
                        res.add(3);
                        res.add(0);
                        res.add(2);
                        res.add(0);
                    }
                    else{
                        res.add(2);
                        res.add(0);
                        res.add(3);
                        res.add(0);
                    }
                }
                if (inv.getNbOr()+inv.getNbBois()>=cout && res.size()==0) {
                    if (inv.getNbOr()>=3) {
                        res.add(3);
                        res.add(0);
                        res.add(0);
                        res.add(2);
                    }
                    else{
                        res.add(2);
                        res.add(0);
                        res.add(0);
                        res.add(3);
                    }
                }
                if (inv.getNbPierre()+inv.getNbArgile()>=cout && res.size()==0) {
                    if (inv.getNbPierre()>=3) {
                        res.add(0);
                        res.add(3);
                        res.add(2);
                        res.add(0);
                    }
                    else{
                        res.add(0);
                        res.add(2);
                        res.add(3);
                        res.add(0);
                    }
                }
                if (inv.getNbPierre()+inv.getNbBois()>=cout && res.size()==0) {
                    if (inv.getNbPierre()>=3) {
                        res.add(0);
                        res.add(3);
                        res.add(0);
                        res.add(2);
                    }
                    else{
                        res.add(0);
                        res.add(2);
                        res.add(0);
                        res.add(3);
                    }
                }
                if (inv.getNbArgile()+inv.getNbBois()>=cout && res.size()==0) {
                    if (inv.getNbArgile()>=3) {
                        res.add(0);
                        res.add(0);
                        res.add(3);
                        res.add(2);
                    }
                    else{
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
                if (inv.getNbRessource()>=cout && inv.getNbBois()!=0 && inv.getNbArgile()!=0 && inv.getNbPierre()!=0 && inv.getNbOr()!=0) {
                    res.add(1);
                    res.add(1);
                    res.add(1);
                    res.add(1);
                }
            }
            if (types==2) {
                if (inv.getNbOr()+inv.getNbPierre()>=cout){
                    if (inv.getNbOr()>=2) {
                        res.add(2);
                        res.add(2);
                        res.add(0);
                        res.add(0);
                    }
                    else{
                        res.add(1);
                        res.add(3);
                        res.add(0);
                        res.add(0);
                    }
                }
                if (inv.getNbOr()+inv.getNbArgile()>=cout && res.size()==0 ) {
                    if (inv.getNbOr()>=2) {
                        res.add(2);
                        res.add(0);
                        res.add(2);
                        res.add(0);
                    }
                    else{
                        res.add(1);
                        res.add(0);
                        res.add(3);
                        res.add(0);
                    }
                }
                if (inv.getNbOr()+inv.getNbBois()>=cout && res.size()==0) {
                    if (inv.getNbOr()>=2) {
                        res.add(2);
                        res.add(0);
                        res.add(0);
                        res.add(2);
                    }
                    else{
                        res.add(1);
                        res.add(0);
                        res.add(0);
                        res.add(3);
                    }
                }
                if (inv.getNbPierre()+inv.getNbArgile()>=cout && res.size()==0) {
                    if (inv.getNbPierre()>=2) {
                        res.add(0);
                        res.add(2);
                        res.add(2);
                        res.add(0);
                    }
                    else{
                        res.add(0);
                        res.add(1);
                        res.add(3);
                        res.add(0);
                    }
                }
                if (inv.getNbPierre()+inv.getNbBois()>=cout && res.size()==0) {
                    if (inv.getNbPierre()>=2) {
                        res.add(0);
                        res.add(2);
                        res.add(0);
                        res.add(2);
                    }
                    else{
                        res.add(0);
                        res.add(1);
                        res.add(0);
                        res.add(3);
                    }
                }
                if (inv.getNbArgile()+inv.getNbBois()>=cout && res.size()==0) {
                    if (inv.getNbArgile()>=2) {
                        res.add(0);
                        res.add(0);
                        res.add(2);
                        res.add(2);
                    }
                    else{
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

	@Override
	public String toString(){
		return name;
	}
}