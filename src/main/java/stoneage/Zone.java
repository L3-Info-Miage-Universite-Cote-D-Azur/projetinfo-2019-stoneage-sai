package stoneage;

import java.util.ArrayList;
/**
	*Une public classe Zone qui contient toute les zone du plateau de jeu
 	*parmit eu les carte civilsation et les carte batimet.
 	* Cette class gére les placement des ouvrier dans les zone ainsi que les recuperation des ouvrier avec les gains associé aux Zones
 	*
 **/
public class Zone {
	private String nomZone;
	private int nbOuvriersPlacés = 0;
	private int niveauZone ;
	private ArrayList<Integer> listeDesDe;
	private  Dé dé= new Dé();
	private int nbPlaceZone ;
	private int nbPlaceDispo;
	private int gains; //le nombre des gains du joueur
	private String TypeGains; //le nom du gain par exemple bois...
	private String[] TypesGains;
	private int nbJoueur;

	public Zone(int niveau) {
		listeDesDe=new ArrayList<>();
        this.niveauZone = niveau;
        dé=new Dé();
        if(niveau==1||niveau==7||niveau==8||niveau==9||niveau==10||niveau==11||niveau==12||niveau==13||niveau==14||niveau==15){
            nbPlaceZone=1;
        }
        else if (niveau== 2){
            nbPlaceZone=100;//le nombre de place dans la zone chasse est illimité
        }
        else if (niveau== 3||(niveau== 4)||(niveau== 5)||(niveau== 6)){        
            nbPlaceZone=7;  
        }
        else if (niveau==16){ // la Hutte
        	nbPlaceZone=2;
		}
        nbPlaceDispo=nbPlaceZone;//au début le nombre de place disponible = au nombre place max de la zone
    }
	public Zone() {
        addAllZones();
	}
	
	
	
    /*La methode placerOuvrier va permettre de poser un nombre de figurine sur une zone choisi, ainsi le nombre de place
    dans la zone sera crediter et le nombre d'ouvrier poser sera enregistrer  */
    public void placerOuvrier(Inventaire inventaireJoueur,int nbOuvriers){
    	if (nbOuvriers>=1 && nbOuvriers <=nbPlaceDispo && nbOuvriers<=inventaireJoueur.getNbOuvrierDispo() ){
    		inventaireJoueur.removeAvailableWorkers(nbOuvriers);//pour placer un nbOuvrier il faut les retirer d'abord de l'inventaire du joueur
    		nbOuvriersPlacés+=nbOuvriers;
    		nbJoueur++;
    		nbPlaceDispo=nbPlaceDispo-nbOuvriers; //le nombre de place disponnible dans la zone diminue
			//le nombre d'ouvrier placer dans la zone augmente
    	}
    }

	public String toString(){
		return nomZone;
	}

    /* La methode lancéDeDés va permettre de lancer autant de Dés que des nbOuvriersPlacés
    et de retourner la somme des Dés jetés */
    public int lancéDeDés(int nbOuvriersPlacés){
        int sommeDés=0;
        int valeurde;
        for (int i = 0; i < nbOuvriersPlacés; i++) {
            valeurde=dé.Lancer();
			listeDesDe.add(valeurde);
            sommeDés+=valeurde;
        }
        return sommeDés ;
    }
    
    /* La  methode  recupeRes va nous permettre de distribuer
     *  les resources gagner a chaque joueur , ainsi son inventaire va etre modifier
     *   et la zone sera liberer quand il recupere ses ouvrier */
    public void recupeRes(ArrayList<CarteCivilisation> listeDesCartes,ArrayList<BuildingTiles> listeDesBatiments,Inventaire inventaireJoueur, Joueurs J) {

		resetListDesDe(); // vider la liste qui contient les dés du joueur precedent ou bien du meme joueur avec une zone precedente
    	int nbRessources= this.lancéDeDés(inventaireJoueur.listeOuvriersPlaces.get(this.niveauZone-1));
    	int nbOutilsDuJoueur=inventaireJoueur.getNbOutilsDuTour();
		int outilChoisie;
    	// le nombre d'outil que le joueur a choisi d'utiliser
		if (inventaireJoueur.getNbOutilsDuTour()>0){ // s'il a des outils on le laisse choisir le nombre qu'il veut sinon en retourne 0
			outilChoisie=J.placerOutils(nbOutilsDuJoueur,nbRessources,this);
		}
		else {
			outilChoisie=0;
		}
    	nbRessources=nbRessources +outilChoisie;
    	nbRessources=nbRessources / this.niveauZone;
    	inventaireJoueur.setNbOutilsDuTour(inventaireJoueur.getNbOutilsDuTour()-outilChoisie);
    	//recuperer les ressources gagner
    	//ajouter les nouveau ressources a l'inventaire du joueur 
    	switch(this.niveauZone){
    		case 1:
    			inventaireJoueur.setNbOutils(Math.min(inventaireJoueur.getNbOutils()+1,12));
				//Le nombre maximal d'OUTILS pour chaque joueur est : 12
				//Les joueurs peuvent tous de meme choisir la zone fabrication et ne pas avoir d'outil ensuite
    			gains=1;
    			TypeGains="Outils";
    			break;
    		case 2:
    			inventaireJoueur.setNourriture(inventaireJoueur.getNourriture()+nbRessources);
    			gains=nbRessources;
    			TypeGains="Nourriture";
    			break;
    		case 3:
    			inventaireJoueur.setNbBois(inventaireJoueur.getNbBois()+nbRessources);
    			inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);
    			gains=nbRessources;
    			TypeGains="Bois";
    			break;
    		case 4: 
    			inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile()+nbRessources);
    			inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);
    			gains=nbRessources;
    			TypeGains="Argile";	    	
    			break;
    		case 5:
    			inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre()+nbRessources);
    			inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);
    			gains=nbRessources;
    			TypeGains="Pierre";	    	
    			break;

    		case 6:
    			inventaireJoueur.setNbOr(inventaireJoueur.getNbOr()+nbRessources);
    			inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);
    			gains=nbRessources;
    			TypeGains="Or";	    	
    			break;
    		case 7:
    			inventaireJoueur.setScoreChamp(inventaireJoueur.getScoreChamp()+1);
				// le nombre de nourriture du joueur augmente avec le niveau de score de son champ
				gains=1;
				TypeGains="Niveau champ";
				break;
			case 16:
				inventaireJoueur.setNbOuvrier(Math.min(inventaireJoueur.getNbOuvrier()+1,10));
				//Le nombre maximal d'ouvrier pour chaque joueur est : 10
				//Les joueurs peuvent tous de meme choisir la Hutte et ne pas avoir d'ouvriers ensuite
				gains=1;
				TypeGains="Ouvrier";
				break;
    		case 8:case 9: case 10: case 11:
    			int coutCarte=this.niveauZone-7; // carte 1 vaut 1 / carte 2 vaut 2/ carte 3 vaut 3...
    			boolean payer=false;
				CarteCivilisation carte;
    			if (this.niveauZone-8 >= listeDesCartes.size()){
					carte=listeDesCartes.get(listeDesCartes.size()-1);
				}
    			else {
					carte = listeDesCartes.get(this.niveauZone - 8);
				}
				// si niveau de zone = 8 alors carte 1 sinon carte 2 sinon...
    			/* Paiement de la carte civilisation  */
    			int typeCout= J.choixTypeRes(coutCarte,inventaireJoueur,3,4,5,6);
    			if (typeCout==3 ) {
    				inventaireJoueur.setNbBois(inventaireJoueur.getNbBois()-coutCarte);
    				inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()-coutCarte);
    				payer=true;
    			}
    			else if (typeCout==4) {
    				inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile()-coutCarte);
    				inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()-coutCarte);
    				payer=true;
    				
    			}
    			else if (typeCout==5) {
    				inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre()-coutCarte);
    				inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()-coutCarte);
    				payer=true;
    			}
    			else if (typeCout==6) {
    				inventaireJoueur.setNbOr(inventaireJoueur.getNbOr()-coutCarte);
    				inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()-coutCarte);
    				payer=true;
    			}
				/* Si le joueur paye la carte (il a assez de resource pour la payer et choisi de la prendre ) elle s'eneleve de a liste
				 *  sinon elle sera rendu a la liste  */
    			if (payer==true) {
					if (carte.getFondDeCarte() == 0) {
						//cartes vertes
						inventaireJoueur.addNbCarteVert();
						if (carte.getNumeroCarte() < 2) {
							//potery
							inventaireJoueur.addTypeCarteCivVerte(1); //type de la carte verte
							if (carte.getNumeroCarte() == 0) {
								//carte potery 0
								inventaireJoueur.setNourriture(inventaireJoueur.getNourriture() + 7);
								gains = 7;
								TypeGains = "Nourriture";
								//la carte 0 permet au joueur de gagner 7 nourriture
							} else {
								// Carte poterie 1
								//lancement de 4 dé avec une methode dans joeur pour que les joueur choisi quelle ressource il veut
								Partie.demanderCadeau(this,StoneAge.listeDesInventaires, StoneAge.listeDesJoueurs, J, inventaireJoueur);
								gains = -2;
							}
						}
						if (carte.getNumeroCarte() > 1 && carte.getNumeroCarte() < 4) {
							//Art
							inventaireJoueur.addTypeCarteCivVerte(2);
							if (carte.getNumeroCarte() == 2) {
								// carte Art 2
								inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils() + 1);
								gains = 1;
								TypeGains = "Outil";
							} else {
								// carte Art 3
								//LANCEMENT 2 DEE OR
								int gainsOr = this.lancéDeDés(2) / 6;
								inventaireJoueur.setNbOr(inventaireJoueur.getNbOr() + gainsOr);
								inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() + gainsOr);
								gains = gainsOr;
								TypeGains = "Or";
							}
						}
						if (carte.getNumeroCarte() > 3 && carte.getNumeroCarte() < 6) {
							//Ecriture
							inventaireJoueur.addTypeCarteCivVerte(3);
							if (carte.getNumeroCarte() == 4) {
								// carte Ecriture 4 : c'est une carte civilisation qui sert que pour calcile Score final
								inventaireJoueur.addNbcarteCiv();
								gains = 1;
								TypeGains = " Carte Civilisation";
							} else {
								// carte Ecriture 5
								Partie.demanderCadeau(this,StoneAge.listeDesInventaires, StoneAge.listeDesJoueurs, J, inventaireJoueur);
								gains = -2;
								//lancement de 4 dé avec une methode dans joueur pour que les joueures choisient quelle ressouces il veut
							}
						}
						if (carte.getNumeroCarte() > 5 && carte.getNumeroCarte() < 8) {
							//Medecine
							inventaireJoueur.addTypeCarteCivVerte(4);
							if (carte.getNumeroCarte() == 6) {
								//carte Medecine 6
								//permet au joueur de choisir 2 resource
								//methode choix dans jouer
								int res;
								ArrayList<Integer> listeDe = new ArrayList<>();
								listeDe.add(1);
								listeDe.add(2);
								listeDe.add(3);
								listeDe.add(4);
								//to do: afficher les 2 gains separaiment
								for (int j = 0; j > 2; j++) {
									res = J.cadeauRes(listeDe); // demander au joueur de choisir deux ressouce
									if (res == 1) {
										inventaireJoueur.setNbBois(inventaireJoueur.getNbBois() + 1);
										inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() + 1);
										gains = -4;
										TypesGains[TypesGains.length] = " Bois ";
									} else if (res == 2) {
										inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile() + 1);
										inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() + 1);
										gains = -4;
										TypesGains[TypesGains.length] = " Argile ";
									} else if (res == 3) {
										inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre() + 1);
										inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() + 1);
										gains = -4;
										TypesGains[TypesGains.length] = " Pierre ";
									} else if (res == 4) {
										inventaireJoueur.setNbOr(inventaireJoueur.getNbOr() + 1);
										inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() + 1);
										gains = -4;
										TypesGains[TypesGains.length] = " Or ";
									}
								}
							}
							else {
								//carte Medecine 7: ajoute 5 nourriture au joueur
								inventaireJoueur.setNourriture(inventaireJoueur.getNourriture() + 5);
								gains = 5;
								TypeGains = "Nourriture";
							}
						}
						if (carte.getNumeroCarte() > 7 && carte.getNumeroCarte() < 10) {
							// carte cadran solaire
							inventaireJoueur.addTypeCarteCivVerte(5);
							if (carte.getNumeroCarte() == 8) {
								// carte cadran solaire 8:  gagner 1 score champ donc niveau champ +1
								inventaireJoueur.setScoreChamp(inventaireJoueur.getScoreChamp() + 1);
								gains = 1;
								TypeGains = "Niveau Champ ";
							} else {
								// carte cadan solaire 9: lancer 4 dés
								//chois de ressouces entre les 4
								Partie.demanderCadeau(this,StoneAge.listeDesInventaires, StoneAge.listeDesJoueurs, J, inventaireJoueur);
								gains = -2;
							}
						}
						if (carte.getNumeroCarte() > 9 && carte.getNumeroCarte() < 12) {
							//transport
							inventaireJoueur.addTypeCarteCivVerte(6);
							if (carte.getNumeroCarte() == 10) {
								// carte transport 10:
								//lancer 4 dé
								//chois de ressouces entre les 4
								Partie.demanderCadeau(this,StoneAge.listeDesInventaires, StoneAge.listeDesJoueurs, J, inventaireJoueur);
								gains = -2;
							} else {
								// carte transport 11:  gagner 2 pierres
								inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre() + 2);
								inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() + 2);
								gains = 2;
								TypeGains = "Pierre ";
							}
						}
						if (carte.getNumeroCarte() > 11 && carte.getNumeroCarte() < 14) {
							//musique
							inventaireJoueur.addTypeCarteCivVerte(7);
							//carte musique 12 : gagner 3 pts dans le score
							//carte musique 13 : gagner 3 pts dans le score
							inventaireJoueur.setScore(inventaireJoueur.getScore() + 3);
							gains = 3;
							TypeGains = " pts de score ";
						}
						if (carte.getNumeroCarte() > 13 && carte.getNumeroCarte() < 16) {
							//tissage
							inventaireJoueur.addTypeCarteCivVerte(8);
							if (carte.getNumeroCarte() == 14) {
								//carte Tissage 14 : gagner 3 nourriture
								inventaireJoueur.setNourriture(inventaireJoueur.getNourriture() + 3);
								gains = 3;
								TypeGains = " Nourriture ";
							} else {
								//carte TISSAGE 15 : gagner 1 nourriture
								inventaireJoueur.setNourriture(inventaireJoueur.getNourriture() + 1);
								gains = 1;
								TypeGains = " Nourriture ";
							}
						}
					}
					else {//Carte Jaune
						if (carte.getNumeroCarte() > 15 && carte.getNumeroCarte() < 21) {
							// Cartes Constructeur
							if (carte.getNumeroCarte() == 16) {
								//carte constructeur 16 : gagner 1 constructeur et  4 nourriture
								inventaireJoueur.addNbConstructeur(1);
								inventaireJoueur.setNourriture(inventaireJoueur.getNourriture() + 4);
								gains = 4;
								TypeGains = " Nourriture ";
							} else if (carte.getNumeroCarte() == 17) {
								//carte constructeur 17 : gagner 1 constructeur  et  1 type de ressource
								inventaireJoueur.addNbConstructeur(1);
								//lancememnt de 4 dé et choix de ressource avec methode choix de joueur
								Partie.demanderCadeau(this,StoneAge.listeDesInventaires, StoneAge.listeDesJoueurs, J, inventaireJoueur);
								gains = -2;
							} else if (carte.getNumeroCarte() == 18) {
								//carte constructeur 18 : gagner 2 constructeur et 2 nourriture
								inventaireJoueur.addNbConstructeur(2);
								inventaireJoueur.setNourriture(inventaireJoueur.getNourriture() + 2);
								gains = 2;
								TypeGains = " Nourriture ";
							} else if (carte.getNumeroCarte() == 19) {
								//carte constructeur 19 : gagner 2 constructeur et  1 type de ressource
								inventaireJoueur.addNbConstructeur(2);
								//lancememnt de 4 dé et choix de ressource avec methode choix de joueur
								Partie.demanderCadeau(this,StoneAge.listeDesInventaires, StoneAge.listeDesJoueurs, J, inventaireJoueur);
								gains = -2;
							} else if (carte.getNumeroCarte() == 20) {
								//carte constructeur 20 : gagner 3 constructeur et 3 pts dans score final
								inventaireJoueur.addNbConstructeur(3);
								inventaireJoueur.setScore(inventaireJoueur.getScore() + 3);
								gains = 3;
								TypeGains = " point dans le score ";

							}
						}
						if (carte.getNumeroCarte() > 20 && carte.getNumeroCarte() < 26) {
							//Paysan
							if (carte.getNumeroCarte() == 21) {
								//carte Paysan 21 : gagner 1 Paysan et 1 pierre
								inventaireJoueur.addNbPaysan(1);
								inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre() + 1);
								inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() + 1);
								gains = 1;
								TypeGains = " Pierre ";
							} else if (carte.getNumeroCarte() == 22) {
								//carte Paysan 22 : gagner 1 Paysan et 1 pts dans Niveau champ
								inventaireJoueur.addNbPaysan(1);
								inventaireJoueur.setScoreChamp(inventaireJoueur.getScoreChamp() + 1);
								gains = 1;
								TypeGains = " Niveau Champ ";

							} else if (carte.getNumeroCarte() == 23) {
								//carte Paysan 23 : gagner 1 Paysan et 1 TYPE DES resources parmie les 4 dé
								inventaireJoueur.addNbPaysan(1);
								//lancer les 4 dé et choisir une ressource avec methode chois
								Partie.demanderCadeau(this,StoneAge.listeDesInventaires, StoneAge.listeDesJoueurs, J, inventaireJoueur);
								gains = -2;
							} else if (carte.getNumeroCarte() == 24) {
								//carte Paysan 24 : gagner 2 Paysan et 1 TYPE DES resources parmie les 4 dé
								inventaireJoueur.addNbPaysan(2);
								//lancer les 4 dé et choisir une ressource avec methode chois
								Partie.demanderCadeau(this,StoneAge.listeDesInventaires, StoneAge.listeDesJoueurs, J, inventaireJoueur);
								gains = -2;
							} else if (carte.getNumeroCarte() == 25) {
								//carte Paysan 25 : gagner 2 Paysan et 3 nourriture
								inventaireJoueur.addNbPaysan(2);
								inventaireJoueur.setNourriture(inventaireJoueur.getNourriture() + 3);
								gains = 3;
								TypeGains = " Nourriture ";
							}
						}
						if (carte.getNumeroCarte() > 25 && carte.getNumeroCarte() < 31) {
							// FABRICANT D'outils
							if (carte.getNumeroCarte() == 26) {
								//carte Fabricant 26 : gagner 1 Fabricant et 3 outil
								inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils() + 3);
								inventaireJoueur.addNbFabricant(1);
								gains = 3;
								TypeGains = " Outils ";
							} else if (carte.getNumeroCarte() == 27) {
								//carte Fabricant 27 : gagner 1 Fabricant et 4 outil
								inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils() + 4);
								inventaireJoueur.addNbFabricant(1);
								gains = 4;
								TypeGains = " Outils ";
							} else if (carte.getNumeroCarte() == 28) {
								//carte Fabricant 28 : gagner 2 Fabricant et 2 OUTIL
								inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils() + 2);
								inventaireJoueur.addNbFabricant(2);
								gains = 2;
								TypeGains = " Outils ";
							} else if (carte.getNumeroCarte() == 29) {
								//carte Fabricant 29 : gagner 2 Fabricant et 1 type de ressouce au choix
								inventaireJoueur.addNbFabricant(2);
								//lancer les 4 dé et choisir une ressource avec methode chois
								Partie.demanderCadeau(this,StoneAge.listeDesInventaires, StoneAge.listeDesJoueurs, J, inventaireJoueur);
								gains = -2;
							} else if (carte.getNumeroCarte() == 30) {
								//carte Fabricant 30 : gagner 2 Fabricant et 1 type de ressource au choix
								//lancer les 4 dé et choisir une ressource avec methode chois
								Partie.demanderCadeau(this,StoneAge.listeDesInventaires, StoneAge.listeDesJoueurs, J, inventaireJoueur);
								inventaireJoueur.addNbFabricant(2);
								gains = -2;
							}
						}
						//if (carte.getNumeroCarte()>30 && carte.getNumeroCarte()<36) {
						else {
							//Chamane
							if (carte.getNumeroCarte() == 31) {
								//carte chamane 31 : gagner 1 Chamane  et 1 PIERRE
								inventaireJoueur.addNbChamane(1);
								inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre() + 1);
								inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() + 1);
								gains = 1;
								TypeGains = " Pierre ";
							} else if (carte.getNumeroCarte() == 32) {
								//carte chamane 32 : gagner 1 Chamane  et 1 OR
								inventaireJoueur.addNbChamane(1);
								inventaireJoueur.setNbOr(inventaireJoueur.getNbOr() + 1);
								inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() +1);
								gains = 1;
								TypeGains = " Or ";
							} else if (carte.getNumeroCarte() == 33) {
								//carte chamane 33 : gagner 1 Chamane  et lancer 2 dé pierre
								int gain = this.lancéDeDés(2) / 5;
								inventaireJoueur.setNbPierre((gain) + inventaireJoueur.getNbPierre());
								inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() + (gain));
								inventaireJoueur.addNbChamane(1);
								gains = gain;
								TypeGains = " Pierre ";
							}
							else if (carte.getNumeroCarte() == 34) {
								//carte chamane 34 : gagner 2 Chamane  et 1 argile
								inventaireJoueur.addNbChamane(2);
								inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile() + 1);
								inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() + 1);
								gains = 2;
								TypeGains = " Argile ";

							}
							else if (carte.getNumeroCarte() == 35) {
								//carte chamane 35 : gagner 2 Chamane  et 1 lancer 2 dé bois
								inventaireJoueur.addNbChamane(2);
								int gain = this.lancéDeDés(2) / 3;
								inventaireJoueur.setNbBois((gain) + inventaireJoueur.getNbBois());
								inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() +(gain));
								gains = gain;
								TypeGains = " Bois ";
							}
						}
					}
					listeDesCartes.remove(carte);
					TypeGains += " avec la carte Civilisation. ";
					break;
    			}
    			else {
    				gains=-1;
    				break;
    			}
    		case 12:case 13:case 14:case 15:
				BuildingTiles building;
				if (this.niveauZone-12 >= listeDesBatiments.size()){
					building=listeDesBatiments.get(listeDesBatiments.size()-1);
				}
				else {
					building = listeDesBatiments.get(this.niveauZone-12);
				}
                  boolean pay=J.payerBatiment();
                  //le joueur choisi s'il prend la carte ou pas
                  /* Si le joueur paye la carte (il a assez de resource pour la payer et choisi de la prendre ) elle demanderCadeau'eneleve de la liste
    			 *  sinon elle sera rendu a la liste  */
                  if (pay==true) { //le joueur paye la carte(et le decide),on la retire donc de la liste des cartes
					 gains=-5;
                 	 if (building.getCardScore()==0) {   //une seule carte +10 points et coute 2b+1a
                  		if (inventaireJoueur.getNbBois()>=2 && inventaireJoueur.getNbArgile()>=1 && pay==true) {
                            inventaireJoueur.setScore(inventaireJoueur.getScore()+10);
                            inventaireJoueur.setNbBois(inventaireJoueur.getNbBois()-2);
                            inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile()-1);
                            inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()-3);
                            gains=10;
                        }                       
                  }
                  else if (building.getCardScore()==1) { //cartes +11 points
                        if (building.getBuildingCost()==1) { //carte +11 points et coute 1b+2a
                            if (inventaireJoueur.getNbBois()>=1 && inventaireJoueur.getNbArgile()>=2 && pay==true) {
								inventaireJoueur.setScore(inventaireJoueur.getScore()+11);
                                inventaireJoueur.setNbBois(inventaireJoueur.getNbBois()-1);
                                inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile()-2);
                                inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()-3);
                                gains=11;
                            }
                        }
                        else{ if (inventaireJoueur.getNbBois()>=2 && inventaireJoueur.getNbPierre()>=1 && pay==true) {//carte +11 points et coute 2b+1p
								inventaireJoueur.setScore(inventaireJoueur.getScore()+11);
                                inventaireJoueur.setNbBois(inventaireJoueur.getNbBois()-2);
                                inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre()-1);
                                inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()-3);
                                gains=11;
                            }
                        }
                  }
                  else if (building.getCardScore()==2) { //cartes +12 points
                        if (building.getBuildingCost()==3) {  //cartes +12 points et coute 1b+1a+1p (existe en 2 exemplaires)
                            if (inventaireJoueur.getNbBois()>=1 && inventaireJoueur.getNbArgile()>=1 && inventaireJoueur.getNbPierre()>=1 && pay==true) {
								inventaireJoueur.setScore(inventaireJoueur.getScore()+12);
                                inventaireJoueur.setNbBois(inventaireJoueur.getNbBois()-1);
                                inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile()-1);
                                inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre()-1);
                                inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()-3);
                                gains=12;
                            }
                        }
                        if (building.getBuildingCost()==4) { //carte +12 points et coute 2b+1o
                            if (inventaireJoueur.getNbBois()>=2 && inventaireJoueur.getNbOr()>=1 && pay==true) {
								inventaireJoueur.setScore(inventaireJoueur.getScore()+12);
                                inventaireJoueur.setNbBois(inventaireJoueur.getNbBois()-2);
                                inventaireJoueur.setNbOr(inventaireJoueur.getNbOr()-1);
                                inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()-3);
                                gains=12;
                            }
                        }
                  }
                  else if (building.getCardScore()==3) { //cartes +13 points
                        if (building.getBuildingCost()==5) { //cartes +13 points et coute 1b+1a+1o (existe en 2 exemplaires)
                            if (inventaireJoueur.getNbBois()>=1 && inventaireJoueur.getNbArgile()>=1 && inventaireJoueur.getNbOr()>=1 && pay==true) {
								inventaireJoueur.setScore(inventaireJoueur.getScore()+13);
                                inventaireJoueur.setNbBois(inventaireJoueur.getNbBois()-1);
                                inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile()-1);
                                inventaireJoueur.setNbOr(inventaireJoueur.getNbOr()-1);
                                inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()-3);
                                gains=13;
                            }
                        }
                        if (building.getBuildingCost()==6) { //carte +13 points et coute 2a+1p
                            if (inventaireJoueur.getNbArgile()>=2 && inventaireJoueur.getNbPierre()>=1 && pay==true) {
								inventaireJoueur.setScore(inventaireJoueur.getScore()+13);
                                inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile()-2);
                                inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre()-1);
                                inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()-3);
                                gains=13;
                            }
                        }
                        if (building.getBuildingCost()==7) { //carte +13 points et coute 1b+2p
                            if (inventaireJoueur.getNbBois()>=1 && inventaireJoueur.getNbPierre()>=2 && pay==true) {
								inventaireJoueur.setScore(inventaireJoueur.getScore()+13);
                                inventaireJoueur.setNbBois(inventaireJoueur.getNbBois()-1);
                                inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre()-2);
                                inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()-3);
                                gains=13;
                            }
                        }
                  }
                  else if (building.getCardScore()==4) { //cartes +14 points
                        if (building.getBuildingCost()==8) { //cartes +14 points et coute 1b+1p+1o (existe en 2 exemplaires)
                            if (inventaireJoueur.getNbBois()>=1 && inventaireJoueur.getNbPierre()>=1 && inventaireJoueur.getNbOr()>=1 && pay==true) {
								inventaireJoueur.setScore(inventaireJoueur.getScore()+14);
                                inventaireJoueur.setNbBois(inventaireJoueur.getNbBois()-1);
                                inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre()-1);
                                inventaireJoueur.setNbOr(inventaireJoueur.getNbOr()-1);
                                inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()-3);
                                gains=14;
                            }
                        }
                        if (building.getBuildingCost()==9) { //carte +14 points et coute 1a+2p
                            if (inventaireJoueur.getNbArgile()>=1 && inventaireJoueur.getNbPierre()>=2 && pay==true) {
								inventaireJoueur.setScore(inventaireJoueur.getScore()+14);
                                inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile()-1);
                                inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre()-2);
                                inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()-3);
                                gains=14;
                            }
                        }
                        if (building.getBuildingCost()==10) { //carte +14 points et coute 2a+1o
                            if (inventaireJoueur.getNbArgile()>=2 && inventaireJoueur.getNbOr()>=1 && pay==true) {
								inventaireJoueur.setScore(inventaireJoueur.getScore()+14);
                                inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile()-2);
                                inventaireJoueur.setNbOr(inventaireJoueur.getNbOr()-1);
                                inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()-3);
                                gains=14;
                            }
                        }
                  }
                  else if (building.getCardScore()==5) { //cartes +15 points
                        if (building.getBuildingCost()==11) { //cartes +15 points et coute 1a+1p+1o (existe en 2 exemplaires)
                            if (inventaireJoueur.getNbArgile()>=1 && inventaireJoueur.getNbPierre()>=1 && inventaireJoueur.getNbOr()>=1 && pay==true) {
								inventaireJoueur.setScore(inventaireJoueur.getScore()+15);
                                inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile()-1);
                                inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre()-1);
                                inventaireJoueur.setNbOr(inventaireJoueur.getNbOr()-1);
                                inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()-3);
                                gains=15;
                            }
                        }
                  }
                  else if (building.getCardScore()==6) { //une seule carte +16 points et coute 2p+1o
					  if (inventaireJoueur.getNbPierre() >= 2 && inventaireJoueur.getNbOr() >= 1 && pay==true) {
						  inventaireJoueur.setScore(inventaireJoueur.getScore()+16);
						  inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre() - 2);
						  inventaireJoueur.setNbOr(inventaireJoueur.getNbOr() - 1);
						  inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() - 3);
						  gains = 16;
					  }
				  }
                    else if (building.getCardScore()==7) {//cartes dont les points gagnes dependent de la valeur des matieres premieres employees(cartes avec ? en haut a gauche)
                        if (building.getBuildingCost()==13) {//carte +? points et coute 5 ressources de 4 types
                            res=J.payBuildingWith(inventaireJoueur,5,4);
                            if (res.size()==4) {
                                J.resolution(inventaireJoueur,res,5);
                                gains=res.get(0)*6+res.get(1)*5+res.get(2)*4+res.get(3)*3;
                            }
                        }
                        if (building.getBuildingCost()==15) {//5 ressources de 2 types
                            res=J.payBuildingWith(inventaireJoueur,5,2);
                            if (res.size()==4) {
                                J.resolution(inventaireJoueur,res,5);
                                gains=res.get(0)*6+res.get(1)*5+res.get(2)*4+res.get(3)*3;
                            }
                        }
                        if (building.getBuildingCost()==16) {//5 ressources de 1 type
                            if (inventaireJoueur.getNbOr()>=5) {
                                inventaireJoueur.setScore(inventaireJoueur.getScore()+5*6);
                                inventaireJoueur.setNbOr(inventaireJoueur.getNbOr()-5);
                                inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() - 5);
                                gains=35;
                                TypeGains="Or";
                            }
                            else if(inventaireJoueur.getNbPierre()>=5){
                                inventaireJoueur.setScore(inventaireJoueur.getScore()+5*5);
                                inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre()-5);
                                inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() - 5);
                                gains=25;
                                TypeGains="Pierre";
                            }
                            else if (inventaireJoueur.getNbArgile()>=5) {
                                inventaireJoueur.setScore(inventaireJoueur.getScore()+5*4);
                                inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile()-5);
                                inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() - 5);
                                gains=20;
                                TypeGains="Argile";
                            }
                            else if (inventaireJoueur.getNbBois()>=5) {
                                inventaireJoueur.setScore(inventaireJoueur.getScore()+5*3);
                                inventaireJoueur.setNbBois(inventaireJoueur.getNbBois()-5);
                                inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() - 5);
                                gains=15;
                                TypeGains="Bois";
                            }
                        }
                             if (building.getBuildingCost()==17) {//carte +? points et coute 4 ressources de 4 types
                                res=J.payBuildingWith(inventaireJoueur,4,4);
                                if (res.size()==4) {
                                    J.resolution(inventaireJoueur,res,4);
                                    gains=res.get(0)*6+res.get(1)*5+res.get(2)*4+res.get(3)*3;
                                }
                            }
                            if (building.getBuildingCost()==19) {//carte +? points et coute 4 ressources de 2 types
                                res=J.payBuildingWith(inventaireJoueur,4,2);
                                if (res.size()==4) {
                                    J.resolution(inventaireJoueur,res,4);
                                    gains=res.get(0)*6+res.get(1)*5+res.get(2)*4+res.get(3)*3;
                                }
                            }
                            if (building.getBuildingCost()==20) {//carte +? points et coute 4 ressources de 1 types
                                if (inventaireJoueur.getNbOr()>=4) {
                                inventaireJoueur.setScore(inventaireJoueur.getScore()+4*6);
                                inventaireJoueur.setNbOr(inventaireJoueur.getNbOr()-4);
                                inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() - 4);
                                gains=24;
                                TypeGains="Or";
                                }
                                else if(inventaireJoueur.getNbPierre()>=4){
                                    inventaireJoueur.setScore(inventaireJoueur.getScore()+4*5);
                                    inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre()-4);
                                    inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() - 4);
                                    gains=20;
                                    TypeGains="Pierre";
                                }
                                else if (inventaireJoueur.getNbArgile()>=4) {
                                    inventaireJoueur.setScore(inventaireJoueur.getScore()+4*4);
                                    inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile()-4);
                                    inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() - 4);
                                    gains=16;
                                    TypeGains="Argile";
                                }
                                else if (inventaireJoueur.getNbBois()>=4) {
                                    inventaireJoueur.setScore(inventaireJoueur.getScore()+4*3);
                                    inventaireJoueur.setNbBois(inventaireJoueur.getNbBois()-4);
                                    inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource() - 4);
                                    gains=12;
                                    TypeGains="Bois";
                                }
                            }
                             if (building.getBuildingCost()==21) {//carte +? points et coute au moins une matiere premiere et au plus 7(existe en 3 exemplaires)
                                res=J.payBuilding17(inventaireJoueur);
                                int sum=0;
                                for(int a:res){
                                    sum+=a;
                                }
                                J.resolution(inventaireJoueur,res,sum);
                                gains=res.get(0)*6+res.get(1)*5+res.get(2)*4+res.get(3)*3;
                             }
                    }
                  inventaireJoueur.addCarteBat(building);
                  listeDesBatiments.remove(building);
                  TypeGains=" points sur la piste score avec la carte Batiment ";
                  }
                  else{
                  		gains=-3;
                  }
    		default:   			
    			break;			
    	}
        inventaireJoueur.addAvailableWorkers(this.getNbOuvriersPlaces());
        //recuperer les ouvriers 
        nbJoueur--;
        this.nbOuvriersPlacés-=inventaireJoueur.listeOuvriersPlaces.get(this.niveauZone-1); //on enleve les figurines du joueur DE LA ZONE
        this.setNbPlaceDispo(this.getNbPlaceZone());//quand on recupere les ouvriers,toutes les places deviennent disponibles.
    }  


    public boolean ouvrierPlace(int nbOuvriers){
        return (nbOuvriersPlacés == nbOuvriers);
    }//retourne une valeur booléenne pour vérifier si tous les ouvrier ont été placés ou non.


	public ArrayList<Integer> lancerNbDé(int nbDe){
		//cette methode retourne une liste de de taille donné qui contient des nombre entre 1 et 6
		ArrayList<Integer> lancement4De=new ArrayList<>();
		for (int i =0; i<nbDe; i++){
			lancement4De.add(dé.Lancer());
		}
		return lancement4De;
	}
	public int lancéDeDés(int nbOuvriersPlacés){
		int sommeDés=0;
		int valeurde;
		for (int i = 0; i < nbOuvriersPlacés; i++) {
			valeurde=dé.Lancer();
			listeDesDe.add(valeurde);
			sommeDés+=valeurde;
		}
		return sommeDés ;
	}
	public ArrayList<Integer> getListeDe(){
		return listeDesDe;
	} //Methode qui retourne une liste qui contient les dés lancer a chaque lance de Dé

	public void resetListDesDe(){
		listeDesDe=new ArrayList<>();
	}


	public int gestionRessources(Inventaire inventaireJoueur, Joueurs J){
		inventaireJoueur.resetNbOutilsDuTour();
		int nbRessources= this.lancéDeDés(inventaireJoueur.listeOuvriersPlaces.get(this.niveauZone-1));
		int nbOutilsDuJoueur=inventaireJoueur.getNbOutilsDuTour();
		int outilChoisie;
		// le nombre d'outil que le joueur a choisi d'utiliser
		if (inventaireJoueur.getNbOutilsDuTour()>0){ // s'il a des outils on le laisse choisir le nombre qu'il veut sinon en retourne 0
			outilChoisie=J.placerOutils(nbOutilsDuJoueur,nbRessources,this);
		}
		else {
			outilChoisie=0;
		}
		nbRessources=nbRessources +outilChoisie;
		nbRessources=nbRessources / this.niveauZone;
		inventaireJoueur.setNbOutilsDuTour(inventaireJoueur.getNbOutilsDuTour()-outilChoisie);
		return nbRessources;
	}

	public String NomZone(){
		String[] nomZone={"Fabrication d'Outils","Chasse","foret","glaisière","carrière","rivière","champ","Civilisation 1","Civilisation 2","Civilisation 3","Civilisation 4","Batiment 1","Batiment 2","Batiment 3","Batiment 4","Hutte"};
		String nom=nomZone[niveauZone - 1];
		return nom;
	}
}
