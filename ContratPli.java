package barbu;

import java.util.ArrayList;

public class ContratPli implements Contrat {

    public ContratPli(Jeu jeu) {
        jeu.partie(new ContratPli());
    }

    public ContratPli() {

    }

    @Override
    public void comptePoints(ArrayList<Joueur> joueurs) {
        for (int i = 0; i <= joueurs.size(); i++) {
            joueurs.get(i).getPoints().add(joueurs.get(i).getNbplis() * 10);
        }
    }

    @Override
    public boolean fin(Jeu jeu, int nombre) {
        return jeu.nbcartesencours == 0; // Si il y a encore des cartes en jeu, alors on renvoit true pour signifier la fin
    }

}
