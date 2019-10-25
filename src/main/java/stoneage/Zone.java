/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stoneage;

/**
 *
 * @author Ossama
 */
public class Zone {
    private int nbOuvriersPlacés = 0;
    private int niveauZone = 3;
    private int constante;
    private int ouvrierDispo = 1;
    
    public Zone(int niveau) {
        this.niveauZone = niveau;
    }
    public Zone(int niveau,int constante){
        this.niveauZone = niveau;
        this.constante=constante;
    }
    public void placerOuvrier(int nbOuvriers){
        nbOuvriersPlacés=nbOuvriers;
        ouvrierDispo --;
    }
    public void resoudre(int constante){ 
        int nb_ressources=constante/3;
    }

    public boolean ouvrierDispo(){
        return (ouvrierDispo > 0);
    }
}