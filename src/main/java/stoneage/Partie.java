package stoneage;
import java.util.ArrayList;
import java.util.Collections ;
public class Partie {
    public Zone zone;
    private final ArrayList<Zone> LesZones ;
    public CarteCivilisation carte=new CarteCivilisation();
    private  ArrayList<CarteCivilisation> listeDesCartes ;
    
    public Partie(){
    	LesZones=new ArrayList<>();
		listeDesCartes=new ArrayList<CarteCivilisation>();
		listeDesCartes=carte.getAllCards();
		
		  // c'est la liste general des zone pour le jeu 
		for(int i=1; i<12;i++){
			Zone zone = new Zone(i);
			LesZones.add(zone);
		}
    }
    
    protected void phaseAction( Inventaire  inv,Joueurs joueur) {
        for(int i =0;i<11;i++){
        	if (inv.listeZonesJouer.get(i)==true){
        		Zone choix = LesZones.get(i);
        		choix.recupeRes(listeDesCartes,inv,joueur);
        		inv.listeZonesJouer.set(i,false); //la zone n'es pluas etuliser donc elle devient false pour le joueur (disponnible a nouveau)
        		System.out.println(ConsoleColors.RED+"Le joueur " + joueur.getNum() + " reprend ses ouvriers de la zone "+choix+ConsoleColors.RESET);        		
        		System.out.println(ConsoleColors.RED+"Il gagne  "+choix.getGains() +" " +choix.TypeGains()+ConsoleColors.RESET  + ". \n");
        	}            
        }
        inv.resetAvailableWorkers();
    }
   
    protected void phasePlacement( Inventaire  inv, Joueurs joueur){
            Choix choix = joueur.placerOuvriers( LesZones,inv);
            inv.listeZonesJouer.set(choix.zoneChoisie,true); //la zone choisie est utliser donc devient true dans l'inventaire du joueur 
            LesZones.get(choix.zoneChoisie).placerOuvrier(inv, choix.nbOuvriersChoisie);   
            System.out.println(ConsoleColors.BLUE+"Le joueur " + joueur.getNum() + " a choisi la zone "+LesZones.get(choix.zoneChoisie)+" pour y placer "+choix.nbOuvriersChoisie+" ouvrier(s)"+ConsoleColors.RESET);  
    }

    public  int getNbCarteDispo() { 
        return (listeDesCartes).size();
    }// cette methode va retourner le nombre des carte disponnible 
    
    protected void phaseNourrir(Inventaire  inv, Joueurs joueur){
        inv.setNourriture(inv.getNourriture()+inv.getScoreChamp());//chaque joueur prend une valeur de jetons nourriture egale a la valeur de son marqeur sur la piste agriculture
        System.out.println(ConsoleColors.GREEN+"Le joueur " + joueur.getNum()+ " a "+inv.getNourriture()+" nourriture et " + inv.getNbRessource() +" ressources"+ConsoleColors.RESET);
        int nm=inv.getNbOuvrierDispo()-inv.getNourriture();//nourriture qui manque
    	if (inv.getNourriture()>=inv.getNbOuvrierDispo()){
            inv.setNourriture(inv.getNourriture()-inv.getNbOuvrierDispo());
            System.out.println(ConsoleColors.GREEN+"Le joueur " + joueur.getNum() + " va nourrir ses ouvriers avec la nourritue qu'il possede "+ConsoleColors.RESET);

    	}
        else if(inv.getNourriture()<inv.getNbOuvrierDispo() && inv.getNbRessource()>=nm ){
            System.out.println(ConsoleColors.GREEN+"Le joueur " + joueur.getNum() + " n'a pas assez de nourriture,il lui manque " + nm + " nourritures" + ",il utilise donc les " +inv.getNourriture()+ " nourriture qu'il possede et ses ressources "+ConsoleColors.RESET );
            inv.setNourriture(inv.getNourriture()-inv.getNourriture());
            inv.setNbRessource(inv.getNbRessource()-nm);
            if (nm!=0) {
                if (nm-inv.getNbBois()>0) {
                    nm=nm-inv.getNbBois();
                    System.out.println(ConsoleColors.GREEN+"Le joueur " + joueur.getNum() + " n'a pas assez de nourriture ,il nourrit ses figurines avec " + inv.getNbBois() + " bois "+ConsoleColors.RESET);
                    inv.setNbBois(inv.getNbBois()-inv.getNbBois());                         
                }     
                else{
                    System.out.println(ConsoleColors.GREEN+"Le joueur " + joueur.getNum() + " n'a pas assez de nourriture ,il nourrit ses figurines avec uniquement " + nm + " bois"+ConsoleColors.RESET);
                    inv.setNbBois(inv.getNbBois()-nm);
                    nm=nm-nm;
                }
            }
            if (nm!=0) {
                if (nm-inv.getNbArgile()>0) {
                    nm=nm-inv.getNbArgile();
                    System.out.println(ConsoleColors.GREEN+"Le joueur " + joueur.getNum() + " n'a pas assez de nourriture ,il nourrit ses figurines avec " + inv.getNbArgile() + " argile"+ConsoleColors.RESET);
                    inv.setNbArgile(inv.getNbArgile()-inv.getNbArgile());      
                }     
                else{
                    System.out.println(ConsoleColors.GREEN+"Le joueur " + joueur.getNum() + " n'a pas assez de nourriture ,il nourrit ses figurines avec " + nm + " argile"+ConsoleColors.RESET);
                    inv.setNbArgile(inv.getNbArgile()-nm);  
                    nm=nm-nm;
                }
            }
            if (nm!=0) {
                if (nm-inv.getNbPierre()>0) {
                    nm=nm-inv.getNbPierre();
                    System.out.println(ConsoleColors.GREEN+"Le joueur " + joueur.getNum() + " n'a pas assez de nourriture ,il nourrit ses figurines avec " + inv.getNbPierre() + " pierre"+ConsoleColors.RESET);
                    inv.setNbPierre(inv.getNbPierre()-inv.getNbPierre());      
                }     
                else{
                    System.out.println(ConsoleColors.GREEN+"Le joueur " + joueur.getNum() + " n'a pas assez de nourriture ,il nourrit ses figurines avec " + nm + " pierre"+ConsoleColors.RESET);
                    inv.setNbPierre(inv.getNbPierre()-nm);  
                    nm=nm-nm;
                }
            }
            if (nm!=0) {
                if (nm-inv.getNbOr()>0) {
                    nm=nm-inv.getNbOr();
                    System.out.println(ConsoleColors.GREEN+"Le joueur " + joueur.getNum() + " n'a pas assez de nourriture ,il nourrit ses figurines avec " + inv.getNbOr() + " or"+ConsoleColors.RESET);
                    inv.setNbOr(inv.getNbOr()-inv.getNbOr());      
                }     
                else{
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
}