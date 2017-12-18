

import java.util.ArrayList;

public class ContratBarbu implements Contrat {

    public ContratBarbu(Jeu jeu) {
        new ContratBarbu();
    }

    public ContratBarbu() {

    }

    public boolean fin(int nombre) {
        if (nombre==1) {
            return true;
        }
        return false;
    }

    @Override
    public void comptePoints(ArrayList<Joueur> joueurs) {
        for (int i = 0; i < joueurs.size(); i++) {
            int cpt = 0;
            while (!joueurs.get(i).getPlis().isEmpty()) {
                Carte c = joueurs.get(i).getPlis().iterator().next();
                if (c.getValeur() == 13 && c.getSymbole().equals("Coeur")) {
                    cpt++;
                }
                joueurs.get(i).getPlis().remove(c);
                System.out.println(cpt);

            }
            joueurs.get(i).getPoints().add(cpt * 80);
        }
    }
}
