
package barbu;

public class ContratPli implements Contrat {

	public ContratPli(Jeu jeu) {
		jeu.partie(new ContratPli());
	}
	
	public ContratPli(){
		
	}
	public int comptePoints (Joueur joueur) {
		return (joueur.getPlis().size()/joueur.getNbplis());
	}

	@Override
	public boolean fin(Jeu jeu, int nombre) {
		if (jeu.nbcartesencours == 0) {
			return true;
		}
		return false;
	}

}
