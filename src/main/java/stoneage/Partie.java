package stoneage;
import java.util.ArrayList;
import java.util.Collections ;
/*
 *Cette Class gere le  tour qui va etres lancer par la class StoneAge(moteur du jeu),
 * elles va permettre de demander aux joueurs de choisire et poser leur ouvrier dans les zones qu'ils veulent
 *  elle va aussi redestribuer les gains et afficher le deroulement des tours
 * elle contient aussi les carte batiment et les carte civilisation qui vont etre enlever a chaque utilisation
 *
 */
public class Partie {
    private final ArrayList<Zone> LesZones ;
    public CarteCivilisation carte=new CarteCivilisation();
    public BuildingTiles building=new BuildingTiles();
    private ArrayList<CarteCivilisation> listeDesCivilisation ;
    private ArrayList<BuildingTiles> listeDesBatiments;
    public Partie(){
    	LesZones=new ArrayList<>();
	    listeDesCivilisation=new ArrayList<CarteCivilisation>();
	    listeDesCivilisation=carte.getAllCards();
        listeDesBatiments=new ArrayList<BuildingTiles>();
        listeDesBatiments=building.getCards();
        // c'est la liste general des zone pour le jeu 
	    for(int i=1; i<16;i++){
            Zone zone = new Zone(i);
            LesZones.add(zone);
	    }
    }
    protected void phaseAction( Inventaire  inv,Joueurs joueur) {
        for(int i =0;i<15;i++){
        	if (inv.listeZonesJouer.get(i)==true){
        		Zone choix = LesZones.get(i);
        		choix.recupeRes(listeDesCivilisation,listeDesBatiments,inv,joueur);
        		for (int de=0;de< choix.getListeDe().size();de++){
        		    if (choix.niveauZone>1&&choix.niveauZone<7) { //lorsqu'il ya un lancement de dé on les affiche
                        System.out.println("Dé "+(de+1)+" : " + choix.getListeDe().get(de));
                    }
                }
        		inv.listeZonesJouer.set(i,false); //la zone n'es pluas etuliser donc elle devient false pour le joueur (disponnible a nouveau)
                inv.listeOuvriersPlaces.set(i,0);
        		System.out.println(ConsoleColors.RED+"Le joueur " + joueur.getNum() + " reprend ses ouvriers de la zone "+choix+ConsoleColors.RESET);
        		if (choix.getGains()==-1){
        		    System.out.println("Le joueur decide d'abandonner sa carte civilisation.\n");
        		}
        		else if (choix.getGains()==-2){
        		    System.out.println("Le joueur a partagé sa carte avec les autre joueurs.\n");
        		}
        		else if (choix.getGains()==-3) {
        		    System.out.println("Le joueur decide d'abandonner sa carte batiment. \n ");
        		}
                        else if (choix.getGains()==-5) {
                            System.out.println("Le joueur n'a pas assez de ressources pour payer cette carte batiment \n");
                        }
        		else if (choix.getGains()==-4){
        		    System.out.println("Le joueur a gagner un "+choix.TypesGains[0]+" et un "+choix.TypesGains[1]+"  avec sa carte civilisation. \n");
                        }
        		else if (choix.getGains()>=0){
        		    System.out.println(ConsoleColors.RED+"Il gagne  "+choix.getGains() +" " +choix.TypeGains()+ConsoleColors.RESET  + " \n");
        		}
        	}
        }
        inv.resetAvailableWorkers();
    }
    protected void phasePlacement( Inventaire  inv, Joueurs joueur){
            Choix choix = joueur.placerOuvriers( LesZones,inv);
            inv.listeZonesJouer.set(choix.zoneChoisie,true); //la zone choisie est utliser donc devient true dans l'inventaire du joueur 
            inv.listeOuvriersPlaces.set(choix.zoneChoisie,choix.nbOuvriersChoisie);
            LesZones.get(choix.zoneChoisie).placerOuvrier(inv, choix.nbOuvriersChoisie);   
            System.out.println(ConsoleColors.BLUE+"Le joueur " + joueur.getNum() + " a choisi la zone "+LesZones.get(choix.zoneChoisie)+" pour y placer "+choix.nbOuvriersChoisie+" ouvrier(s)"+ConsoleColors.RESET);  
    }

    public  int getNbCarteDispo() { 
        return (listeDesCivilisation).size();
    }// cette methode va retourner le nombre des carte civilisation  disponnible
    public  int getNbBatiments() {
        return (listeDesBatiments).size();
    }// cette methode va retourner le nombre des carte batiment  disponnible

    protected void phaseNourrir(Inventaire  inv, Joueurs joueur){
        inv.setNourriture(inv.getNourriture()+inv.getScoreChamp());//chaque joueur prend une valeur de jetons nourriture egale a la valeur de son marqeur sur la piste agriculture
        System.out.println(ConsoleColors.GREEN+"Le joueur " + joueur.getNum()+ " a "+inv.getNourriture()+" nourriture et " + inv.getNbRessource() +" ressources"+ConsoleColors.RESET);
        int nm=inv.getNbOuvrierDispo()-inv.getNourriture();//nourriture qui manque
    	if (inv.getNourriture()>=inv.getNbOuvrierDispo()){//cas ou la nourriture du joueur est suffisante pour nourrie ses figurines
            inv.setNourriture(inv.getNourriture()-inv.getNbOuvrierDispo());
            System.out.println(ConsoleColors.GREEN+"Le joueur " + joueur.getNum() + " va nourrir ses ouvriers avec la nourritue qu'il possede "+ConsoleColors.RESET);

    	}
        else if(inv.getNourriture()<inv.getNbOuvrierDispo() && inv.getNbRessource()>=nm ){
            System.out.println(ConsoleColors.GREEN+"Le joueur " + joueur.getNum() + " n'a pas assez de nourriture,il lui manque " + nm + " nourritures" + ",il utilise donc les " +inv.getNourriture()+ " nourriture qu'il possede et ses ressources "+ConsoleColors.RESET );
            inv.setNourriture(inv.getNourriture()-inv.getNourriture());
            //inv.setNbRessource(inv.getNbRessource()-nm);
            if (nm!=0) {
                if (nm-inv.getNbBois()>0 && inv.getNbBois()!=0) {
                    nm=nm-inv.getNbBois();
                    System.out.println(ConsoleColors.GREEN+"Le joueur " + joueur.getNum() + " n'a pas assez de nourriture ,il nourrit ses figurines avec " + inv.getNbBois() + " bois "+ConsoleColors.RESET);
                    inv.setNbBois(inv.getNbBois()-inv.getNbBois());                         
                }     
                else if (nm-inv.getNbBois()<=0 ) {
                     System.out.println(ConsoleColors.GREEN+"Le joueur " + joueur.getNum() + " n'a pas assez de nourriture ,il nourrit ses figurines avec  " + nm + " bois"+ConsoleColors.RESET);
                    inv.setNbBois(inv.getNbBois()-nm);
                    nm=nm-nm;
                }                 
                }
            
            if (nm!=0) {
                if (nm-inv.getNbArgile()>0 && inv.getNbArgile()!=0) {
                    nm=nm-inv.getNbArgile();
                    System.out.println(ConsoleColors.GREEN+"Le joueur " + joueur.getNum() + " n'a pas assez de nourriture ,il nourrit ses figurines avec " + inv.getNbArgile() + " argile"+ConsoleColors.RESET);
                    inv.setNbArgile(inv.getNbArgile()-inv.getNbArgile());      
                }     
                else if (nm-inv.getNbArgile()<=0) {
                    System.out.println(ConsoleColors.GREEN+"Le joueur " + joueur.getNum() + " n'a pas assez de nourriture ,il nourrit ses figurines avec  " + nm + " argile"+ConsoleColors.RESET);
                    inv.setNbArgile(inv.getNbArgile()-nm);  
                    nm=nm-nm;
                }   
                }
            
            if (nm!=0) {
                if (nm-inv.getNbPierre()>0 && inv.getNbPierre()!=0) {
                    nm=nm-inv.getNbPierre();
                    System.out.println(ConsoleColors.GREEN+"Le joueur " + joueur.getNum() + " n'a pas assez de nourriture ,il nourrit ses figurines avec " + inv.getNbPierre() + " pierre"+ConsoleColors.RESET);
                    inv.setNbPierre(inv.getNbPierre()-inv.getNbPierre());      
                }     
                else if (nm-inv.getNbPierre()<=0) {
                    System.out.println(ConsoleColors.GREEN+"Le joueur " + joueur.getNum() + " n'a pas assez de nourriture ,il nourrit ses figurines avec  " + nm + " pierre"+ConsoleColors.RESET);
                    inv.setNbPierre(inv.getNbPierre()-nm);  
                    nm=nm-nm;
                }                   
                }
            
            if (nm!=0) {
                if (nm-inv.getNbOr()>0 && inv.getNbOr()!=0) {
                    nm=nm-inv.getNbOr();
                    System.out.println(ConsoleColors.GREEN+"Le joueur " + joueur.getNum() + " n'a pas assez de nourriture ,il nourrit ses figurines avec " + inv.getNbOr() + " or"+ConsoleColors.RESET);
                    inv.setNbOr(inv.getNbOr()-inv.getNbOr());      
                }     
                else if (nm-inv.getNbOr()<=0) {
                    inv.setNbOr(inv.getNbOr()-nm);  
                    System.out.println(ConsoleColors.GREEN+"Le joueur " + joueur.getNum() + " n'a pas assez de nourriture ,il nourrit ses figurines avec " + nm + " or"+ConsoleColors.RESET);
                    nm=nm-nm;
                }                    
                }
            }           
        
    	else {
            inv.setScore(inv.getScore()-10);
            System.out.println(ConsoleColors.GREEN+"Le joueur " + joueur.getNum() + " n'a pas assez de nourriture et ses ressources ne sont pas suffisantes pour nourrir ses figurines, son score diminue donc de 10 points"+ConsoleColors.RESET);
    	}	
    }
    public static void demanderCadeau( ArrayList<Inventaire> listeDesInventaires ,ArrayList<Joueurs> listeDesJoueurs,Joueurs J,Inventaire invJ ){
        ArrayList<Integer> listeDe=Zone.lancerNbDé(4); //lancer 4 dé pour les carte civilisation qui demande cette option
        int choixCad;
        ArrayList<Integer> listeIndJoueurs=new ArrayList<>();
        listeIndJoueurs.add(listeDesJoueurs.indexOf(J));
        for (int j=0;j<listeDesJoueurs.size();j++){
            if (j!=listeDesJoueurs.indexOf(J)) {
                listeIndJoueurs.add(listeDesJoueurs.indexOf(listeDesJoueurs.get(j)));
            }
        }// une liste qui contient l'indice des joueur en commencant par le joueur qui a choisi la carte
        /*cette methode va permetre a chaque joueur de
        recuperer une resource parmis les dispo (carte civilisation)*/

        for( int i : listeIndJoueurs ) {
            Inventaire inv=listeDesInventaires.get(i);
            choixCad=listeDesJoueurs.get(i).cadeauRes(listeDe);
            if (choixCad == 1) {
                inv.setNbBois(inv.getNbBois()+1);
                inv.setNbRessource(inv.getNbRessource()+1);
                System.out.println("Le joueur " + listeDesJoueurs.get(i).getNum() + " choisi de prendre 1 bois comme Cadeau!");
            }
            else if (choixCad == 2) {
                inv.setNbArgile(inv.getNbArgile()+1);
                inv.setNbRessource(inv.getNbRessource()+1);
                System.out.println("Le joueur " + listeDesJoueurs.get(i).getNum() + " choisi de prendre 1 Argile comme Cadeau!");
            }
            else if (choixCad == 3) {
                inv.setNbPierre(inv.getNbPierre()+1);
                inv.setNbRessource(inv.getNbRessource()+1);
                System.out.println("Le joueur " + listeDesJoueurs.get(i).getNum() + " choisi de prendre 1 Pierre comme Cadeau!");
            }
            else if (choixCad == 4) {
                inv.setNbOr(inv.getNbOr()+1);
                inv.setNbRessource(inv.getNbRessource()+1);
                System.out.println("Le joueur " + listeDesJoueurs.get(i).getNum() + " choisi de prendre 1 Or comme Cadeau!");
            }
            else if (choixCad == 5) {
                inv.setNbOutils(inv.getNbOutils()+1);
                System.out.println("Le joueur " + listeDesJoueurs.get(i).getNum() + " choisi de prendre 1 Outil comme Cadeau!");
            }
            else if (choixCad == 6) {
                inv.setScoreChamp(inv.getScoreChamp()+1);
                System.out.println("Le joueur " + listeDesJoueurs.get(i).getNum() + " choisi d'augmenter son nivau de champ de 1 comme Cadeau!");
            }
            listeDe.remove(listeDe.indexOf(choixCad));

        }
    }
}