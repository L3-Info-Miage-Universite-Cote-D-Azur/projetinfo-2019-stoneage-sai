package stoneage;
import java.util.ArrayList;
import java.util.Collections ;
public class Partie {
    public Zone zone;
    private final ArrayList<Zone> LesZones ;
    
    public Partie(){
	LesZones=new ArrayList<>();
	  // c'est la liste general des zone pour le jeu 
		for(int i=1; i<12;i++){
			Zone zone = new Zone(i);
			LesZones.add(zone);
		}
    }
    
    protected void phaseAction(  Inventaire  inv,Joueurs joueur,int joueurCourant) {
        for(int i =0;i<11;i++){
        	if (inv.listeZonesJouer.get(i)==true){
        		Zone choix = LesZones.get(i);
        		choix.recupeRes(inv,joueur);
        		inv.listeZonesJouer.set(i,false); //la zone n'es pluas etuliser donc elle devient false pour le joueur (disponnible a nouveau)
        		System.out.println("Le joueur " + joueurCourant + " reprend ses ouvriers de la zone "+choix.NomZone());
        		System.out.println("Il gagne : "+choix.getGains() +" " +choix.TypeGains()  + ". \n");
        	}            
        }
    }
   
    protected void phasePlacement( Inventaire  inv, Joueurs joueur, int joueurCourant){
            Choix choix = joueur.placerOuvriers( LesZones,inv);
            inv.listeZonesJouer.set(choix.zoneChoisie,true); //la zone choisie est utliser donc devient true dans l'inventaire du joueur 
            LesZones.get(choix.zoneChoisie).placerOuvrier(inv, choix.nbOuvriersChoisie);    		
            System.out.println("Le joueur " + joueurCourant + " a choisi la zone "+(LesZones.get(choix.zoneChoisie)).NomZone()+" pour y placer "+choix.nbOuvriersChoisie+" ouvrier(s)");  
    }
    
    protected void phaseNourrir(Inventaire  inv, Joueurs joueur, int joueurCourant){
        System.out.println("Le joueur " + joueurCourant+ " a "+inv.getNourriture()+" nourriture et " + inv.getNbRessource() +" ressources");
        int nm=inv.getNbOuvrierDispo()-inv.getNourriture();//nourriture qui manque
    	if (inv.getNourriture()>=inv.getNbOuvrierDispo()){
            inv.setNourriture(inv.getNourriture()-inv.getNbOuvrierDispo());
            System.out.println("Le joueur " + joueurCourant + " va nourrir ses ouvriers avec la nourritue qu'il possede. \n ");

    	}
        else if(inv.getNourriture()<inv.getNbOuvrierDispo() && nm>inv.getNourriture() && inv.getNbRessource()>=nm ){
            System.out.println("Le joueur " + joueurCourant + " n'a pas assez de nourriture,il lui manque " + nm + " nourritures" + ",il utilise donc les " +inv.getNourriture()+ " nourriture qu'il possede et ses ressources. \n " );
            inv.setNourriture(inv.getNourriture()-inv.getNourriture());
            inv.setNbRessource(inv.getNbRessource()-nm);
            if (nm!=0) {
                if (nm-inv.getNbBois()>0) {
                    nm=nm-inv.getNbBois();
                    System.out.println("Le joueur " + joueurCourant + " n'a pas assez de nourriture ,il nourrit ses figurines avec " + inv.getNbBois() + " bois. \n ");
                    inv.setNbBois(inv.getNbBois()-inv.getNbBois());                         
                }     
                else{
                    System.out.println("Le joueur " + joueurCourant + " n'a pas assez de nourriture ,il nourrit ses figurines avec uniquement " + nm + " bois. \n");
                    inv.setNbBois(inv.getNbBois()-nm);
                    nm=nm-nm;
                }
            }
            if (nm!=0) {
                if (nm-inv.getNbArgile()>0) {
                    nm=nm-inv.getNbArgile();
                    System.out.println("Le joueur " + joueurCourant + " n'a pas assez de nourriture ,il nourrit ses figurines avec " + inv.getNbArgile() + " argile. \n");
                    inv.setNbArgile(inv.getNbArgile()-inv.getNbArgile());      
                }     
                else{
                    System.out.println("Le joueur " + joueurCourant + " n'a pas assez de nourriture ,il nourrit ses figurines avec " + nm + " argile. \n");
                    inv.setNbArgile(inv.getNbArgile()-nm);  
                    nm=nm-nm;
                }
            }
            if (nm!=0) {
                if (nm-inv.getNbPierre()>0) {
                    nm=nm-inv.getNbPierre();
                    System.out.println("Le joueur " + joueurCourant + " n'a pas assez de nourriture ,il nourrit ses figurines avec " + inv.getNbPierre() + " pierre. \n");
                    inv.setNbPierre(inv.getNbPierre()-inv.getNbPierre());      
                }     
                else{
                    System.out.println("Le joueur " + joueurCourant + " n'a pas assez de nourriture ,il nourrit ses figurines avec " + inv.getNbPierre() + " pierre. \n");
                    inv.setNbPierre(inv.getNbPierre()-nm);  
                    nm=nm-nm;
                }
            }
            if (nm!=0) {
                if (nm-inv.getNbOr()>0) {
                    nm=nm-inv.getNbOr();
                    System.out.println("Le joueur " + joueurCourant + " n'a pas assez de nourriture ,il nourrit ses figurines avec " + inv.getNbOr() + " or. \n");
                    inv.setNbOr(inv.getNbOr()-inv.getNbOr());      
                }     
                else{
                    inv.setNbOr(inv.getNbOr()-nm);  
                    System.out.println("Le joueur " + joueurCourant + " n'a pas assez de nourriture ,il nourrit ses figurines avec " + inv.getNbBois() + " or. \n");
                    nm=nm-nm;
                }
            }           
        }
    	else {
            inv.setScore(inv.getScore()-10);
            System.out.println("Le joueur " + joueurCourant + " n'a pas assez de nourriture et ses ressources ne sont pas suffisantes pour nourrir ses figurines, son score diminue donc de 10 points. \n");
    	}	
    }
}