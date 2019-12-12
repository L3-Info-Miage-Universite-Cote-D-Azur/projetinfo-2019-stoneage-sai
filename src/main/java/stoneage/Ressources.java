package stoneage;
public class Ressources {
    private String nom;
    private int coeff;
    private int valeur;
    Ressources(int coeff, String nom, int valeur){
        this.coeff =coeff;
        this.nom=nom;
        this.valeur=valeur;

    }
    public String getNom(){
        return this.nom;
    }

    public int getCoeff() {
        return coeff;
    }

    public int getValeur() {
        if(this.coeff ==1){ //le nombre d'outil ne peut pas depasser 12.
            this.valeur=Math.min(this.valeur,12);
        }
        return valeur;
    }

    public void addvaleur(int v){
        this.valeur+=v;

    }
    public void subvaleur(int v){
        this.valeur-=v;
    }



}
