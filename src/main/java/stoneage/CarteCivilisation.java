package stoneage;
import java.util.ArrayList;

/**
 * Cette classe représente les cartes civilisation.
 * Dans cette classe:
 *
 * Chaque carte a un numéro:
 *      @see int#numeroCarte :
 *        0-1:Potery
 *        2-3:Art
 *        4-5:Ecriture
 *        6-7:Medecine
 *        8-9:Cadran
 *        10-11:Transport
 *        12-13:Musique
 *        14-15:Tissage
 *        16-17-18-19-20: Hutte
 *        21-22-23-24-25: Agriculture
 *        26-27-28-29-30: Outils
 *        31-32-33-34-35: Figurine
 *
 * Chaque carte a fond de carte:
 *      @see int#fondDeCarte
 *      0: Fond vert.
 *      1: Fond jaune.
 *
 * Le numéro et fond de la carte permettent de spécifier le gain et la manière d'utiliser la carte.
 *
 * Il y a une liste générale de toutes les cartes
 *      @see #allCards
 *  Cette liste est instanciée avec la méthode addCards().
 *     @see #addCardsToList()
 */
public class CarteCivilisation {
    private int fondDeCarte;
    private int numeroCarte;
    private ArrayList<CarteCivilisation> allCards;

    /**
     * Ajoute toutes les tuiles bâtiment dans la liste cards.
     * @see #allCards
     * @see #addCardsToList()
     */
    public CarteCivilisation() {
    	addCardsToList();
    }

    /**
     * Constructeur de la classe CarteCivilisation
     * @param fondDeCarte:
     *                   int (0 ou 1)
     *                   Fond de la carte.
     * @param numeroCarte:
     *                   int (de 0 à 35)
     *                   Numéro de la carte.
     */
    public CarteCivilisation(int fondDeCarte, int numeroCarte) {
        this.fondDeCarte = fondDeCarte;
        this.numeroCarte = numeroCarte;
    }

    /**
     * Méthode pour ajouter toutes les cartes civilisation dans la liste allCards.
     * @see #allCards
     *
     */
    public  void addCardsToList(){
        //  FOND VERT
    	allCards= new ArrayList<>();
        allCards.add(new CarteCivilisation(0,0));
        allCards.add(new CarteCivilisation(0,1));
        allCards.add(new CarteCivilisation(0,2));
        allCards.add(new CarteCivilisation(0,3));
        allCards.add(new CarteCivilisation(0,4)); //Ex: vert, carte n°4-> Ecriture, pioche carteCiv.
        allCards.add(new CarteCivilisation(0,5));
        allCards.add(new CarteCivilisation(0,6));
        allCards.add(new CarteCivilisation(0,7));
        allCards.add(new CarteCivilisation(0,8));
        allCards.add(new CarteCivilisation(0,9));
        allCards.add(new CarteCivilisation(0,10));
        allCards.add(new CarteCivilisation(0,11));
        allCards.add(new CarteCivilisation(0,12));
        allCards.add(new CarteCivilisation(0,13));
        allCards.add(new CarteCivilisation(0,14));
        allCards.add(new CarteCivilisation(0,15));
        //  FOND JAUNE
        allCards.add(new CarteCivilisation(1,16)); //Ex: jaune, carte n°16-> hutte, nourriture, 1ouvrier.
        allCards.add(new CarteCivilisation(1,17));
        allCards.add(new CarteCivilisation(1,18));
        allCards.add(new CarteCivilisation(1,19));
        allCards.add(new CarteCivilisation(1,20));
        allCards.add(new CarteCivilisation(1,21));
        allCards.add(new CarteCivilisation(1,22));
        allCards.add(new CarteCivilisation(1,23));
        allCards.add(new CarteCivilisation(1,24));
        allCards.add(new CarteCivilisation(1,25));
        allCards.add(new CarteCivilisation(1,26));
        allCards.add(new CarteCivilisation(1,27));
        allCards.add(new CarteCivilisation(1,28));
        allCards.add(new CarteCivilisation(1,29));
        allCards.add(new CarteCivilisation(1,30));
        allCards.add(new CarteCivilisation(1,31));
        allCards.add(new CarteCivilisation(1,32));
        allCards.add(new CarteCivilisation(1,33));
        allCards.add(new CarteCivilisation(1,34));
        allCards.add(new CarteCivilisation(1,35));
    }
    public void recupResCarteCiv(Inventaire inventaireJoueur,Zone zone,Joueurs J){
        if (this.getFondDeCarte() == 0) {
            //cartes vertes
            inventaireJoueur.addNbCarteVert();
            if (this.getNumeroCarte() < 2) {
                //potery
                inventaireJoueur.addTypeCarteCivVerte(1); //type de la carte verte
                if (this.getNumeroCarte() == 0) {
                    //carte potery 0
                    inventaireJoueur.lesRessources.get(2).addvaleur(7);
                    zone.setGains( 7);
                    zone.setTypeGains( inventaireJoueur.lesRessources.get(2).getNom());
                    //la carte 0 permet au joueur de gagner 7 nourriture
                } else {
                    // Carte poterie 1
                    //lancement de 4 dé avec une methode dans joeur pour que les joueur choisi quelle ressource il veut
                    Partie.demanderCadeau(zone,StoneAge.listeDesInventaires, StoneAge.listeDesJoueurs, J, inventaireJoueur);
                    zone.setGains( -2);
                }
            }
            if (this.getNumeroCarte() > 1 && this.getNumeroCarte() < 4) {
                //Art
                inventaireJoueur.addTypeCarteCivVerte(2);
                if (this.getNumeroCarte() == 2) {
                    // carte Art 2
                    inventaireJoueur.lesRessources.get(1).addvaleur(1);
                    zone.setGains( 1);
                    zone.setTypeGains( inventaireJoueur.lesRessources.get(1).getNom());
                } else {
                    // carte Art 3
                    //LANCEMENT 2 DEE OR
                    int gainsOr = zone.lancéDeDés(2) / 6;
                    inventaireJoueur.lesRessources.get(6).addvaleur(gainsOr);
                    zone.setGains( gainsOr);
                    zone.setTypeGains(  inventaireJoueur.lesRessources.get(6).getNom());
                }
            }
            if (this.getNumeroCarte() > 3 && this.getNumeroCarte() < 6) {
                //Ecriture
                inventaireJoueur.addTypeCarteCivVerte(3);
                if (this.getNumeroCarte() == 4) {
                    // carte Ecriture 4 : c'est une carte civilisation qui sert que pour calcile Score final
                    inventaireJoueur.addNbcarteCiv();
                    zone.setGains( 1);
                    zone.setTypeGains( " Carte Civilisation");
                } else {
                    // carte Ecriture 5
                    Partie.demanderCadeau(zone,StoneAge.listeDesInventaires, StoneAge.listeDesJoueurs, J, inventaireJoueur);
                    zone.setGains( -2);
                    //lancement de 4 dé avec une methode dans joueur pour que les joueures choisient quelle ressouces il veut
                }
            }
            if (this.getNumeroCarte() > 5 && this.getNumeroCarte() < 8) {
                //Medecine
                inventaireJoueur.addTypeCarteCivVerte(4);
                if (this.getNumeroCarte() == 6) {
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
                            inventaireJoueur.lesRessources.get(3).addvaleur(1);
                            zone.setGains( -4);
                            zone.setTabTypeGains(zone.getTabTypeGains().length, inventaireJoueur.lesRessources.get(3).getNom());
                        } else if (res == 2) {
                            inventaireJoueur.lesRessources.get(4).addvaleur(1);
                            zone.setGains( -4);
                            zone.setTabTypeGains(zone.getTabTypeGains().length,  inventaireJoueur.lesRessources.get(4).getNom());
                        } else if (res == 3) {
                            inventaireJoueur.lesRessources.get(4).addvaleur(1);
                            zone.setGains( -4);
                            zone.setTabTypeGains(zone.getTabTypeGains().length , inventaireJoueur.lesRessources.get(5).getNom());
                        } else if (res == 4) {
                            inventaireJoueur.lesRessources.get(6).addvaleur(1);
                            zone.setGains( -4);
                            zone.setTabTypeGains(zone.getTabTypeGains().length, inventaireJoueur.lesRessources.get(6).getNom());
                        }
                    }
                }
                else {
                    //carte Medecine 7: ajoute 5 nourriture au joueur
                    inventaireJoueur.lesRessources.get(2).addvaleur(5);
                    zone.setGains( 5);
                    zone.setTypeGains( inventaireJoueur.lesRessources.get(2).getNom());
                }
            }
            if (this.getNumeroCarte() > 7 && this.getNumeroCarte() < 10) {
                // carte cadran solaire
                inventaireJoueur.addTypeCarteCivVerte(5);
                if (this.getNumeroCarte() == 8) {
                    // carte cadran solaire 8:  gagner 1 score champ donc niveau champ +1
                    inventaireJoueur.setScoreChamp(inventaireJoueur.getScoreChamp() + 1);
                    zone.setGains( 1);
                    zone.setTypeGains( "Niveau Champ ");
                } else {
                    // carte cadan solaire 9: lancer 4 dés
                    //chois de ressouces entre les 4
                    Partie.demanderCadeau(zone,StoneAge.listeDesInventaires, StoneAge.listeDesJoueurs, J, inventaireJoueur);
                    zone.setGains( -2);
                }
            }
            if (this.getNumeroCarte() > 9 && this.getNumeroCarte() < 12) {
                //transport
                inventaireJoueur.addTypeCarteCivVerte(6);
                if (this.getNumeroCarte() == 10) {
                    // carte transport 10:
                    //lancer 4 dé
                    //chois de ressouces entre les 4
                    Partie.demanderCadeau(zone,StoneAge.listeDesInventaires, StoneAge.listeDesJoueurs, J, inventaireJoueur);
                    zone.setGains( -2);
                } else {
                    // carte transport 11:  gagner 2 pierres
                    inventaireJoueur.lesRessources.get(5).addvaleur(2);
                    zone.setGains( 2);
                    zone.setTypeGains( inventaireJoueur.lesRessources.get(5).getNom());
                }
            }
            if (this.getNumeroCarte() > 11 && this.getNumeroCarte() < 14) {
                //musique
                inventaireJoueur.addTypeCarteCivVerte(7);
                //carte musique 12 : gagner 3 pts dans le score
                //carte musique 13 : gagner 3 pts dans le score
                inventaireJoueur.setScore(inventaireJoueur.getScore() + 3);
                zone.setGains( 3);
                zone.setTypeGains( " points de score ");
            }
            if (this.getNumeroCarte() > 13 && this.getNumeroCarte() < 16) {
                //tissage
                inventaireJoueur.addTypeCarteCivVerte(8);
                if (this.getNumeroCarte() == 14) {
                    //carte Tissage 14 : gagner 3 nourriture
                    inventaireJoueur.lesRessources.get(2).addvaleur(3);
                    zone.setGains( 3);
                    zone.setTypeGains( inventaireJoueur.lesRessources.get(2).getNom());
                } else {
                    //carte TISSAGE 15 : gagner 1 nourriture
                    inventaireJoueur.lesRessources.get(2).addvaleur(1);
                    zone.setGains( 1);
                    zone.setTypeGains( inventaireJoueur.lesRessources.get(2).getNom());
                }
            }
        }
        else {//Carte Jaune
            if (this.getNumeroCarte() > 15 && this.getNumeroCarte() < 21) {
                // Cartes Constructeur
                if (this.getNumeroCarte() == 16) {
                    //carte constructeur 16 : gagner 1 constructeur et  4 nourriture
                    inventaireJoueur.addNbConstructeur(1);
                    inventaireJoueur.lesRessources.get(2).addvaleur(4);
                    zone.setGains( 4);
                    zone.setTypeGains( inventaireJoueur.lesRessources.get(2).getNom());
                } else if (this.getNumeroCarte() == 17) {
                    //carte constructeur 17 : gagner 1 constructeur  et  1 type de ressource
                    inventaireJoueur.addNbConstructeur(1);
                    //lancememnt de 4 dé et choix de ressource avec methode choix de joueur
                    Partie.demanderCadeau(zone,StoneAge.listeDesInventaires, StoneAge.listeDesJoueurs, J, inventaireJoueur);
                    zone.setGains( -2);
                } else if (this.getNumeroCarte() == 18) {
                    //carte constructeur 18 : gagner 2 constructeur et 2 nourriture
                    inventaireJoueur.addNbConstructeur(2);
                    inventaireJoueur.lesRessources.get(2).addvaleur(2);
                    zone.setGains( 2);
                    zone.setTypeGains( inventaireJoueur.lesRessources.get(2).getNom());
                } else if (this.getNumeroCarte() == 19) {
                    //carte constructeur 19 : gagner 2 constructeur et  1 type de ressource
                    inventaireJoueur.addNbConstructeur(2);
                    //lancememnt de 4 dé et choix de ressource avec methode choix de joueur
                    Partie.demanderCadeau(zone,StoneAge.listeDesInventaires, StoneAge.listeDesJoueurs, J, inventaireJoueur);
                    zone.setGains( -2);
                } else if (this.getNumeroCarte() == 20) {
                    //carte constructeur 20 : gagner 3 constructeur et 3 pts dans score final
                    inventaireJoueur.addNbConstructeur(3);
                    inventaireJoueur.setScore(inventaireJoueur.getScore() + 3);
                    zone.setGains( 3);
                    zone.setTypeGains( " point dans le score ");

                }
            }
            if (this.getNumeroCarte() > 20 && this.getNumeroCarte() < 26) {
                //Paysan
                if (this.getNumeroCarte() == 21) {
                    //carte Paysan 21 : gagner 1 Paysan et 1 pierre
                    inventaireJoueur.addNbPaysan(1);
                    inventaireJoueur.lesRessources.get(5).addvaleur(1);
                    zone.setGains( 1);
                    zone.setTypeGains( inventaireJoueur.lesRessources.get(5).getNom());
                } else if (this.getNumeroCarte() == 22) {
                    //carte Paysan 22 : gagner 1 Paysan et 1 pts dans Niveau champ
                    inventaireJoueur.addNbPaysan(1);
                    inventaireJoueur.setScoreChamp(inventaireJoueur.getScoreChamp() + 1);
                    zone.setGains( 1);
                    zone.setTypeGains( " Niveau Champ ");

                } else if (this.getNumeroCarte() == 23) {
                    //carte Paysan 23 : gagner 1 Paysan et 1 TYPE DES resources parmie les 4 dé
                    inventaireJoueur.addNbPaysan(1);
                    //lancer les 4 dé et choisir une ressource avec methode chois
                    Partie.demanderCadeau(zone,StoneAge.listeDesInventaires, StoneAge.listeDesJoueurs, J, inventaireJoueur);
                    zone.setGains( -2);
                } else if (this.getNumeroCarte() == 24) {
                    //carte Paysan 24 : gagner 2 Paysan et 1 TYPE DES resources parmie les 4 dé
                    inventaireJoueur.addNbPaysan(2);
                    //lancer les 4 dé et choisir une ressource avec methode chois
                    Partie.demanderCadeau(zone,StoneAge.listeDesInventaires, StoneAge.listeDesJoueurs, J, inventaireJoueur);
                    zone.setGains( -2);
                } else if (this.getNumeroCarte() == 25) {
                    //carte Paysan 25 : gagner 2 Paysan et 3 nourriture
                    inventaireJoueur.addNbPaysan(2);
                    inventaireJoueur.lesRessources.get(2).addvaleur(3);
                    zone.setGains( 3);
                    zone.setTypeGains( inventaireJoueur.lesRessources.get(2).getNom());
                }
            }
            if (this.getNumeroCarte() > 25 && this.getNumeroCarte() < 31) {
                // FABRICANT D'outils
                if (this.getNumeroCarte() == 26) {
                    //carte Fabricant 26 : gagner 1 Fabricant et 3 outil
                    inventaireJoueur.lesRessources.get(1).addvaleur(3);
                    inventaireJoueur.addNbFabricant(1);
                    zone.setGains( 3);
                    zone.setTypeGains( inventaireJoueur.lesRessources.get(1).getNom());
                } else if (this.getNumeroCarte() == 27) {
                    //carte Fabricant 27 : gagner 1 Fabricant et 4 outil
                    inventaireJoueur.lesRessources.get(1).addvaleur(4);
                    inventaireJoueur.addNbFabricant(1);
                    zone.setGains( 4);
                    zone.setTypeGains( inventaireJoueur.lesRessources.get(1).getNom());
                } else if (this.getNumeroCarte() == 28) {
                    //carte Fabricant 28 : gagner 2 Fabricant et 2 OUTIL
                    inventaireJoueur.lesRessources.get(1).addvaleur(2);
                    inventaireJoueur.addNbFabricant(2);
                    zone.setGains( 2);
                    zone.setTypeGains( inventaireJoueur.lesRessources.get(1).getNom());
                } else if (this.getNumeroCarte() == 29) {
                    //carte Fabricant 29 : gagner 2 Fabricant et 1 type de ressouce au choix
                    inventaireJoueur.addNbFabricant(2);
                    //lancer les 4 dé et choisir une ressource avec methode chois
                    Partie.demanderCadeau(zone,StoneAge.listeDesInventaires, StoneAge.listeDesJoueurs, J, inventaireJoueur);
                    zone.setGains( -2);
                } else if (this.getNumeroCarte() == 30) {
                    //carte Fabricant 30 : gagner 2 Fabricant et 1 type de ressource au choix
                    //lancer les 4 dé et choisir une ressource avec methode chois
                    Partie.demanderCadeau(zone,StoneAge.listeDesInventaires, StoneAge.listeDesJoueurs, J, inventaireJoueur);
                    inventaireJoueur.addNbFabricant(2);
                    zone.setGains( -2);
                }
            }
            //if (carte.getNumeroCarte()>30 && carte.getNumeroCarte()<36) {
            else {
                //Chamane
                if (this.getNumeroCarte() == 31) {
                    //carte chamane 31 : gagner 1 Chamane  et 1 PIERRE
                    inventaireJoueur.addNbChamane(1);
                    inventaireJoueur.lesRessources.get(5).addvaleur(1);
                    zone.setGains( 1);
                    zone.setTypeGains( inventaireJoueur.lesRessources.get(5).getNom());
                } else if (this.getNumeroCarte() == 32) {
                    //carte chamane 32 : gagner 1 Chamane  et 1 OR
                    inventaireJoueur.addNbChamane(1);
                    inventaireJoueur.lesRessources.get(6).addvaleur(1);
                    zone.setGains( 1);
                    zone.setTypeGains( inventaireJoueur.lesRessources.get(6).getNom());
                } else if (this.getNumeroCarte() == 33) {
                    //carte chamane 33 : gagner 1 Chamane  et lancer 2 dé pierre
                    int gain = zone.lancéDeDés(2) / 5;
                    inventaireJoueur.lesRessources.get(5).addvaleur(gain);
                    inventaireJoueur.addNbChamane(1);
                    zone.setGains( gain);
                    zone.setTypeGains( inventaireJoueur.lesRessources.get(5).getNom());
                }
                else if (this.getNumeroCarte() == 34) {
                    //carte chamane 34 : gagner 2 Chamane  et 1 argile
                    inventaireJoueur.addNbChamane(2);
                    inventaireJoueur.lesRessources.get(4).addvaleur(1);
                    zone.setGains( 1);
                    zone.setTypeGains( inventaireJoueur.lesRessources.get(4).getNom());

                }
                else if (this.getNumeroCarte() == 35) {
                    //carte chamane 35 : gagner 2 Chamane  et 1 lancer 2 dé bois
                    inventaireJoueur.addNbChamane(2);
                    int gain = zone.lancéDeDés(2) / 3;
                    inventaireJoueur.lesRessources.get(3).addvaleur(gain);
                    zone.setGains( gain);
                    zone.setTypeGains(inventaireJoueur.lesRessources.get(3).getNom());
                }
            }
        }
        zone.setTypeGains (zone.getTypeGains()  +" avec la carte Civilisation");
    }
    
    public int getFondDeCarte() {
        return fondDeCarte;
    }
    public int getNumeroCarte() {
        return numeroCarte;
    }
    public  ArrayList<CarteCivilisation> getAllCards() {
        return allCards;
    }
    @Override
    public String toString(){
        return numeroCarte+"";
    }
}