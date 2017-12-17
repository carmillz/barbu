package barbu;

import java.util.ArrayList;

public class ContratPli implements Contrat {

    public ContratPli(Jeu jeu) {
        new ContratPli();
    }

    public ContratPli() {

    }

    @Override
    public void comptePoints(ArrayList<Joueur> joueurs) {
        for (int i = 0; i < joueurs.size(); i++) {
            joueurs.get(i).getPoints().add((joueurs.get(i).getPlis().size()/joueurs.size())* 10);
        }
    }

    @Override
    //Méthode non utilisée
    public boolean fin(int nombre) {
        return false;
    }

}
