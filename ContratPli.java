
package barbu;

public class ContratPli implements Contrat {

	public ContratPli(Jeu jeu) {
		jeu.partie(jeu.joueurs);
	}
	public boolean fin(Jeu jeu) {
		if (jeu.nbcartesencours == 0) {
			return true;
		}
		return false;
	}
	
	public int comptePoints (Joueur joueur) {
		return (joueur.getPlis().size()/joueur.getNbplis());
	}
7
}
