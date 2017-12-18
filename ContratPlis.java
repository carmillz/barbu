

import java.util.ArrayList;

public class ContratPlis implements Contrat {


    public ContratPlis() {

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
