
package barbu;

import java.util.ArrayList;

public class ContratDame implements Contrat {

	public ContratDame(Jeu jeu) {
		jeu.partie(new ContratDame());
	}
	public ContratDame() {
		// TODO Auto-generated constructor stub
	}
	public boolean fin(Jeu jeu, int nombre) {
		if (jeu.nbdames == 4) {
			return true;
		          }
		return false;
	}


	public void comptePoints(ArrayList<Joueur> joueurs) {
		
                for (int i=0; i<=joueurs.size();i++){
                    int cpt =0;
                    Joueur joueur = joueurs.get(i);
                    while (!joueur.getPlis().isEmpty()) {
                        Carte c = joueur.getPlis().iterator().next();
                        if (c.getValeur()==13) {
                                cpt=cpt+1;
                        }
                        joueur.getPlis().remove(c);
                        joueur.getPoints().add(cpt*20);
                    }
                }
		
	}



}
