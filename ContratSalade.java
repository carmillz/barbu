

import java.util.ArrayList;

public class ContratSalade implements Contrat {

    public ContratSalade() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void comptePoints(ArrayList<Joueur> joueurs) {
        Carte c;
        for (int i = 0; i < joueurs.size(); i++) {
            int cptcoeur = 0;
            int cptdame = 0;
            int cptroi = 0;
            int plis = joueurs.get(i).getPlis().size()/joueurs.size()* 10;
            System.out.println(joueurs.get(i).getPlis().toString());
            Joueur joueur = joueurs.get(i); // On fait joueur par joueur, et on simplifie pour l'utilisation du joueur i plus tard
            while (!joueur.getPlis().isEmpty()) { // Tant que le joueur a des plis
                c = joueur.getPlis().iterator().next(); // On regarde carte par carte
                if (c.getSymbole().equals("Coeur")) { // On compte le nombre de coeurs
                    cptcoeur++;
                }
                if (c.getValeur()==12) { // On compte le nombre de dames
                    cptdame++;
                }
                if (c.getValeur() == 13 && c.getSymbole().equals("Coeur")){ // On donne les points du roi si il y est
                    cptroi++;
                }
                joueur.getPlis().remove(c); // On enlève une carte
            }
            joueur.getPoints().add(plis + cptcoeur*10 + cptdame*20 + cptroi*80); //Vérifier les points dans tous les contrats et celui-ci !!
        }

    }

    //Méthode non utilisée
    @Override
    public boolean fin(int nombre) {
        return false;
    }

}
