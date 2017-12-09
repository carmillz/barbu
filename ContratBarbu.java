
package barbu;

public class ContratBarbu implements Contrat {

	public ContratBarbu(Jeu jeu) {
		jeu.partie(jeu.joueurs);
	}


	public int comptePoints(Joueur joueur) {
		if (joueur.getPlis().contains(new Carte("Coeur", 14))) {
			return (-60);
		}
		return 0;
	}


	@Override
	public boolean fin(Jeu jeu, int nombre) {
		if (jeu.plateau.contains(new Carte("Coeur", 14))) {
			return true;
		}
		return false;
	}

}
