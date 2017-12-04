
package barbu;

public class ContratBarbu implements Contrat {

	public ContratBarbu(Jeu jeu) {
		jeu.partie(jeu.joueurs);
	}
	
	public boolean fin(Jeu jeu) {
		if (jeu.plateau.contains(new Carte("Coeur", 13))) {
			return true;
		}
		return false;
	}

	public int comptePoints(Joueur joueur) {
		if (joueur.getPlis().contains(new Carte("Coeur", 13))) {
			return (-60);
		}
		return 0;
	}


	@Override
	public boolean fin(Jeu jeu, int nombre) {
		// TODO Auto-generated method stub
		return false;
	}

}
