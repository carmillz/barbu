package barbu;

import java.util.ArrayList;

public class ContratBarbu implements Contrat {

    public ContratBarbu(Jeu jeu) {
        jeu.partie(new ContratBarbu());
    }

    public ContratBarbu() {

    }

    public boolean fin(Jeu jeu, int nombre) {

        if (jeu.plateau.contains(new Carte("Coeur", 14))) {
            return true;
        }
        return false;
    }

    @Override
    public void comptePoints(ArrayList<Joueur> joueurs) {
        for (int i = 0; i <= joueurs.size(); i++) {
            Joueur joueur = joueurs.get(i);
            int cpt = 0;
            while (!joueur.getPlis().isEmpty()) {
                Carte c = joueur.getPlis().iterator().next();
                if (c.getValeur() == 14 && c.getSymbole().equals("Coeur")) {
                    cpt++;
                }
                joueur.getPlis().remove(c);
                joueur.getPoints().add(cpt * 60);
            }
        }
    }
}
