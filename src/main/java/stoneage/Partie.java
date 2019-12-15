package stoneage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;


/**
 *  Cette clase représente le déroulé d'une partie.
 *  Elle gère le tour qui va être lancé par la classe StoneAge (moteur du jeu).
 *  @see StoneAge
 *
 *  Permet de demander aux joueurs de choisir et poser leur ouvrier dans les zones qu'ils veulent.
 *  @see #phasePlacement(Inventaire, Joueurs)
 *
 *  Permet de récuperer les ressources et ouvriers.
 *  @see #phaseAction(Inventaire, Joueurs)
 *
 *  Nourrit les ouvriers
 *  @see #phaseNourrir(Inventaire, Joueurs)
 *
 *  Contient les cartes bâtiment et civilisation(enlevées à chaque utilisation):
 *  @see #listeDesBatiments
 *  @see #listeDesCivilisation
 *
 *  @see #plateau
 *  Correspond au plateau:
 *      @see Plateau
 *
 * @see boolean#stat
 *      Si on veut des statistiques true, false sinon.
 *
 **/
public class Partie {
    private final ArrayList<Zone> plateau ;
    public CarteCivilisation carte=new CarteCivilisation();
    public BuildingTiles building=new BuildingTiles();
    private ArrayList<CarteCivilisation> listeDesCivilisation ;
    private ArrayList<BuildingTiles> listeDesBatiments;
    static boolean stat;

    /**
     * Constructeur de la classe Partie
     * @param statistique:
     *                   Pour savoir si on veut les statistique  ou la partie en détail.
     */
    public Partie(boolean statistique){
        listeDesCivilisation=new ArrayList<CarteCivilisation>();
        listeDesCivilisation=carte.getAllCards();
        listeDesBatiments=new ArrayList<BuildingTiles>();
        listeDesBatiments=building.getCards();
        Collections.shuffle(carte.getAllCards());//melange des cartes civilisations a la fin de chaque tour
        Collections.shuffle(building.getCards());//melange des cartes batiments a la fin de chaque tour
        // c'est la liste general des zone pour le jeu
        Plateau plate= new Plateau();
        plateau=plate.addAllZones();
        stat = statistique;
    }
    public ArrayList< Zone> getLesZones(){
        return plateau;
    }

    /**
     * Méthode qui permet de récuperer les ouvriers et ressources d'un joueur.
     * @param inv:
     *           Inventaire du joueur.
     * @param joueur:
     *              Joueur qui doit reprendre des ouvriers et ressources gagnées.
     */
    protected void phaseAction( Inventaire  inv,Joueurs joueur) {
        for(int i =0;i<16;i++){
            if (inv.listeZonesJouer.get(i)==true){
                Zone choix = plateau.get(i);
                choix.recupeRes(listeDesCivilisation,listeDesBatiments,inv,joueur);
                if (choix.getNiveauZone()>=2 && choix.getNiveauZone()<=6) {
                    String chaine="\nLancement des dés: ** ";
                    for (int de = 0; de < choix.getListeDe().size(); de++) {
                        if (choix.getNiveauZone() > 1 && choix.getNiveauZone() < 7) { //lorsqu'il ya un lancement de dé on les affiche
                            chaine += "Dé " + (de + 1) + " = " + choix.getListeDe().get(de) + "  **  ";
                        }
                    }
                    if (!stat) System.out.println(ConsoleColors.RED+chaine+ConsoleColors.RESET);
                }
                inv.listeZonesJouer.set(i,false); //la zone n'est plus utiliser donc elle devient false pour le joueur (disponnible a nouveau)
                inv.listeOuvriersPlaces.set(i,0);
        		if (choix.getGains()==-1){
        			if (!stat) System.out.println(ConsoleColors.RED+"\nLe joueur  " + joueur.getNum() + " decide d'abandonner sa carte civilisation, il reprend ses ouvriers."+ConsoleColors.RESET);
        		}
        		else if (choix.getGains()==-3) {
        			if (!stat) System.out.println(ConsoleColors.RED+"\nLe joueur  " + joueur.getNum() + " decide d'abandonner sa carte batiment, il reprend ses ouvriers.  "+ConsoleColors.RESET);
        		}
        		else if (choix.getGains()==-5) {
        			if (!stat) System.out.println(ConsoleColors.RED+"\nLe joueur  " + joueur.getNum() + " n'a pas assez de ressources pour payer cette carte batiment, il reprend ses ouvriers."+ConsoleColors.RESET);
        		}
        		else if (choix.getGains()==-4){
        			if (!stat) System.out.println(ConsoleColors.RED+"\nLe joueur  " + joueur.getNum() + " a gagner un "+choix.getTabTypeGains()[0]+" et un "+choix.getTabTypeGains()[1]+"  avec sa carte civilisation, il reprend ses ouvriers. "+ConsoleColors.RESET);
                        }
        		else if (choix.getGains()>=0){
        			if (!stat) System.out.println(ConsoleColors.RED+"\nLe joueur " + joueur.getNum() + " a gagner  "+choix.getGains() +" " +choix.getTypeGains()+ ", il reprend ses ouvriers de la zone "+choix  + "."+ConsoleColors.RESET );
                    if (choix.getNiveauZone()==12 ||choix.getNiveauZone()==13 ||choix.getNiveauZone()==14 ||choix.getNiveauZone()==15){
                        String chaine="Elle a coute :";
                        for (String c:joueur.lesResCouter.keySet()) {
                            chaine+= joueur.lesResCouter.get(c)+" "+c+" ||";
                        }
                        if (!stat) System.out.println(ConsoleColors.RED+chaine + "."+ConsoleColors.RESET);
                    }
                }
        	}
        }
        inv.resetAvailableWorkers();
    }

    /**
     * Méthode qui permet de placer des ouvriers sur une zone.
     * @param inv:
     *           Inventaire du joueur.
     * @param joueur:
     *              Joueur qui doit reprendre des ouvriers et ressources gagnées.
     */
    protected void phasePlacement( Inventaire  inv, Joueurs joueur){
        Choix choix = joueur.placerOuvriers( plateau,inv);
        inv.listeZonesJouer.set(choix.zoneChoisie,true); //la zone choisie est utliser donc devient true dans l'inventaire du joueur
        inv.listeOuvriersPlaces.set(choix.zoneChoisie,choix.nbOuvriersChoisie);
        plateau.get(choix.zoneChoisie).placerOuvrier(inv, choix.nbOuvriersChoisie);
        if (!stat) System.out.println(ConsoleColors.BLUE+"Le joueur " + joueur.getNum() + " a choisi la zone "+plateau.get(choix.zoneChoisie)+" pour y placer "+choix.nbOuvriersChoisie+" ouvrier(s)"+ConsoleColors.RESET);
    }
    public  int getNbCarteDispo() {
        return (listeDesCivilisation).size();
    }
    public  int getNbBatiments() {
        return (listeDesBatiments).size();
    }

    /**
     * Méthode pour nourrir les ouvriers
     * @param inv:
     *           Inventaire du joueur.
     * @param joueur:
     *              Joueur qui doit reprendre des ouvriers et ressources gagnées.
     */
    protected void phaseNourrir(Inventaire  inv, Joueurs joueur) {
        inv.addNourriture( inv.getScoreChamp());
        //chaque joueur prend une valeur de jetons nourriture egale a la valeur de son marqueur sur la piste agriculture
        if (!stat) System.out.println(ConsoleColors.GREEN + "Le joueur " + joueur.getNum() + " a " + inv.getNourriture() + " nourritures et " + inv.getNbRessource() + " ressources." + ConsoleColors.RESET);
        int nm = inv.getNbOuvrierDispo() - inv.getNourriture();//nourriture qui manque
        if (nm <= 0) {//cas ou la nourriture du joueur est suffisante pour nourrir ses figurines
            inv.addNourriture(-inv.getNbOuvrierDispo());
            if (!stat) System.out.println(ConsoleColors.GREEN + "Le joueur " + joueur.getNum() + " va nourrir ses ouvriers avec la nourritue qu'il possede." + ConsoleColors.RESET);

        } else {
            Map<String, Integer> nourrirOuv = joueur.NourrirOuv(inv, nm);
            if (!stat) System.out.println(ConsoleColors.GREEN + "Le joueur n'a pas assez de nourriture, il utilise donc :" + ConsoleColors.RESET);

            for (String res : nourrirOuv.keySet()) {
                if (!stat) System.out.println(ConsoleColors.GREEN + nourrirOuv.get(res) + " " + res + "." + ConsoleColors.RESET);
                if (res == "Pierre") {
                    inv.lesRessources.get(5).subvaleur(nourrirOuv.get(res)) ;
                } else if (res == "Bois") {
                    inv.lesRessources.get(3).subvaleur(nourrirOuv.get(res)) ;
                } else if (res == "Or") {
                    inv.lesRessources.get(6).subvaleur(nourrirOuv.get(res)) ;
                } else if (res == "Argile") {
                    inv.lesRessources.get(4).subvaleur(nourrirOuv.get(res)) ;
                } else if (res == "Point de Score") {
                    inv.setScore(inv.getScore() - nourrirOuv.get(res));
                }

            }
        }
    }

    /**
     * Méthode qui permet à chaque joueur de récuperer une ressource parmi celles disponibles d'une carte civilisation.
     * @param zone:
     *            Zone où le joueur se trouve
     * @param listeDesInventaires:
     *                           Liste de l'inventaire de chaque joueur.
     * @param listeDesJoueurs:
     *                       Liste de tous les joueurs.
     * @param J:
     *         Le Joueur qui est en train d'utiliser cette méthode
     * @param invJ:
     *            L'inventaire du joueur J.
     */
    public static void demanderCadeau( Zone zone, ArrayList<Inventaire> listeDesInventaires ,ArrayList<Joueurs> listeDesJoueurs,Joueurs J,Inventaire invJ ){
        ArrayList<Integer> listeDe=zone.lancerNbDé(4); //lancer 4 dé pour les carte civilisation qui demande cette option
        int choixCad;
        ArrayList<Integer> listeIndJoueurs=new ArrayList<>();
        listeIndJoueurs.add(listeDesJoueurs.indexOf(J));
        for (int j=0;j<listeDesJoueurs.size();j++){
            if (j!=listeDesJoueurs.indexOf(J)) {
                listeIndJoueurs.add(listeDesJoueurs.indexOf(listeDesJoueurs.get(j)));
            }
        }// une liste qui contient l'indice des joueur en commencant par le joueur qui a choisi la carte
        if (!stat) System.out.println(ConsoleColors.RED+"\nLe joueur " + J.getNum()+ " partage sa carte civilisation avec les autre joueurs:"+ConsoleColors.RESET);

        for( int i : listeIndJoueurs ) {
            Inventaire inv=listeDesInventaires.get(i);
            choixCad=listeDesJoueurs.get(i).cadeauRes(listeDe);
            if (choixCad == 1) {
                inv.lesRessources.get(3).addvaleur(1) ;
                if (!stat) System.out.println(ConsoleColors.RED+"Le joueur " + listeDesJoueurs.get(i).getNum() + " choisi de prendre 1 bois comme Cadeau!"+ConsoleColors.RESET);
            }
            else if (choixCad == 2) {
                inv.lesRessources.get(4).addvaleur(1) ;
                if (!stat) System.out.println(ConsoleColors.RED+"Le joueur " + listeDesJoueurs.get(i).getNum() + " choisi de prendre 1 Argile comme Cadeau!"+ConsoleColors.RESET);
            }
            else if (choixCad == 3) {
                inv.lesRessources.get(5).addvaleur(1) ;
                if (!stat) System.out.println(ConsoleColors.RED+"Le joueur " + listeDesJoueurs.get(i).getNum() + " choisi de prendre 1 Pierre comme Cadeau!"+ConsoleColors.RESET);
            }
            else if (choixCad == 4) {
                inv.lesRessources.get(6).addvaleur(1) ;
                if (!stat) System.out.println(ConsoleColors.RED+"Le joueur " + listeDesJoueurs.get(i).getNum() + " choisi de prendre 1 Or comme Cadeau!"+ConsoleColors.RESET);
            }
            else if (choixCad == 5) {
                inv.lesRessources.get(1).addvaleur(1) ;
                if (!stat) System.out.println(ConsoleColors.RED+"Le joueur " + listeDesJoueurs.get(i).getNum() + " choisi de prendre 1 Outil comme Cadeau!"+ConsoleColors.RESET);
            }
            else if (choixCad == 6) {
                inv.setScoreChamp(inv.getScoreChamp()+1);
                if (!stat) System.out.println(ConsoleColors.RED+"Le joueur " + listeDesJoueurs.get(i).getNum() + " choisi d'augmenter son nivau de champ de 1 comme Cadeau!"+ConsoleColors.RESET);
            }
            listeDe.remove(listeDe.indexOf(choixCad));

        }
    }
}