

import java.util.ArrayList;

public class ContratCoeurs implements Contrat {

    public ContratCoeurs(Jeu jeu) {
        new ContratCoeurs();
    }

    public ContratCoeurs() {
        // TODO Auto-generated constructor stub
    }

    public boolean fin(int nombre) {
        if (nombre == 14) { // si on n'a pas 32 cartes, c'est forcement 52
            return true;
        }
        return false;

    }

    public void comptePoints(ArrayList<Joueur> joueurs) {
        Carte c;
        for (int i = 0; i < joueurs.size(); i++) {
            int cpt = 0;
            while (!joueurs.get(i).getPlis().isEmpty()) {
                c = joueurs.get(i).getPlis().iterator().next();
                if (c.getSymbole().equals("Coeur")) {
                    System.out.println(c);
                    cpt++;
                }
                joueurs.get(i).getPlis().remove(c);
                System.out.println("b" +cpt);
            }
            joueurs.get(i).getPoints().add(cpt * 10);
            System.out.println("c" +cpt);
        }

    }

}
