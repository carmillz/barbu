
public class Carte {
    /*
     * La classe carte contient les informations relatives aux cartes
     * soit un nom (de 2 Ã  as) et un symbole (coeur, pique, trÃ¨fle et carreau).
     * On y a Ã©galement ajoutÃ© la valeur qui est croissante de 2 Ã  14 afin de
     * faire les comparaisons entre les cartes plus facilement Ã  la fin d'un pli
     */
    private String symbole;
    private int valeur;
    private String nom;

    public String getSymbole() {
        return symbole;
    }
    public void setSymbole(String symbole) {
        this.symbole = symbole;
    }
    public int getValeur() {
        return valeur;
    }
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom + " de " + symbole;
    }

    public Carte(String symbole, int valeur) {
        /*
         * Pour construire une carte, on a besoin du nom et du symbole, la valeur
         * est calculÃ©e Ã  partir du nom via une mÃ©thode annexe
         */
        super();
        this.symbole = symbole;
        this.valeur = valeur;
        this.nom = valeurToNom(valeur);
    }


    /*
     * La mÃ©thode valeurToNom permet, Ã  partir de la valeur qui est passÃ©e en paramÃ¨tre
     * de donner un nom Ã  la carte pour simplifier la comprÃ©hension, notamment du
     * valet Ã  l'as.
     */
    public String valeurToNom(int valeur){
        String nom="";
        if (valeur==11){
            nom = "Valet";
        }else if (valeur==12){
            nom = "Dame";
        }else if (valeur==13){
            nom = "Roi";
        }else if (valeur==14){
            nom = "As";
        }else {
            for (int i = 2; i <= 10; i++){
                if (valeur==i){
                    nom = String.valueOf(i);
                }
            }
        }
        return nom;
    }
    public int compareTo(int valeur) {
        return (this.compareTo(valeur));
    }
}

