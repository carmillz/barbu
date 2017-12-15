package barbu;

import java.util.ArrayList;

public class ContratSalade implements Contrat {

    public ContratSalade(Jeu jeu) {
        // TODO Auto-generated constructor stub
    }

    public void comptePoints(ArrayList<Joueur> joueurs) {
        Carte c;
        for (int i = 0; i <= joueurs.size(); i++) {
            int cptcoeur = 0;
            int cptdame = 0;
            int cptroi = 0;
            int cptpli = 0;
            Joueur joueur = joueurs.get(i); // On fait joueur par joueur, et on simplifie pour l'utilisation du joueur i plus tard
            while (!joueur.getPlis().isEmpty()) { // Tant que le joueur a des plis
                c = joueur.getPlis().iterator().next(); // On regarde carte par carte 
                if (c.getSymbole().equals("Coeur")) { // On compte le nombre de coeur
                    cptcoeur+=cptcoeur;
                }
                if (c.getValeur()==13) { // On compte le nombre de dame
                    cptdame+= cptdame;
                }
                if (c.getValeur() == 14 && c.getSymbole().equals("Coeur")){ // On donne les points du roi si il y est
                    cptroi= 60;
                }
                cptpli= joueurs.get(i).getNbplis() * 10; //  On donne les points pour tous les plis du joueur i
                joueur.getPlis().remove(c); // On enlève une carte 
            }
            joueur.getPoints().add(cptcoeur*20 + cptdame*20 + cptroi + cptpli); //Vérifier les points dans tous les contrats et celui-ci !!
        }
        
    }

    
    public boolean fin(Jeu jeu, int nombre) {
        if (jeu.nbcartesencours == 0) { // Si le nombre de carte en jeu est égal à 0 (donc plus de carte à jouer)
            return true;         // Alors la partie est finie 
        }

        return false;            // Sinon la partie continue 
    }
    
}