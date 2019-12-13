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
        else if (this.coeff==3){ //le nombre de bois ne peut pas depasser 20.
            this.valeur=Math.min(this.valeur,20);
        }
        else if (this.coeff==4){ //le nombre d'argile ne peut pas depasser 16.
            this.valeur=Math.min(this.valeur,16);
        }
        else if (this.coeff==5){ //le nombre de pierre ne peut pas depasser 12.
            this.valeur=Math.min(this.valeur,12);
        }
        else if (this.coeff==6){ //le nombre d'or ne peut pas depasser 10
            this.valeur=Math.min(this.valeur,10);
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
