/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stoneage;

/**
 *
 * @author Ossama
 * @author Yasmine 
 */
public class Zone {
    private int nbOuvriersPlacés = 0;
    private int niveauZone = 3;
    private Dé dé;

    
    public Zone(int niveau) {
        this.niveauZone = niveau;
        dé=new Dé();
    }
    public Zone(int niveau,Dé dé){
        this.niveauZone = niveau;
        this.dé=dé;
    }
    
    
    public void placerOuvrier(Inventaire inventaireJoueur,int nbOuvriers){
        inventaireJoueur.removeAvailableWorkers(nbOuvriers);//pour placer un nbOuvrier il faut les retirer d'abord de l'inventaire du joueur  
        nbOuvriersPlacés=nbOuvriers;                        
    }

    public int resoudre(int nbOuvriersPlacés){ 
        return lancéDeDés(nbOuvriersPlacés)/niveauZone;
    }

    public String NomZone(){
    	String[] nomZone={"Agriculture","Chasse","foret","glaisière","carrière","rivière"};//ajout des zones glaisière,carrière,rivière
    	String nom=nomZone[niveauZone - 1];
    	return nom;
    }
    
    //retourne une valeur booléenne pour vérifier si tous les ouvrier ont était placer ou non.
    public boolean ouvrierPlace(int nbOuvriers){ 
        return (nbOuvriersPlacés == nbOuvriers);
    }
    
    /*on lance autant de Dés que des nbOuvriersPlacés
    et on retourne la somme des Dés jetées divisé par niveauZone
    */
    private int lancéDeDés(int nbOuvriersPlacés){  
        int sommeDés=0;                            
        for (int i = 0; i < nbOuvriersPlacés; i++) {
            sommeDés+=dé.Lancer();
        }
        return sommeDés / niveauZone;
    }
}