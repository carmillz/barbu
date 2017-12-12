
package barbu;

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


	public int comptePoints(Joueur joueur) {
		int cpt =0;
		while (!joueur.getPlis().isEmpty()) {
			Carte c = joueur.getPlis().iterator().next();
			if (c.getValeur()==13) {
				cpt=cpt+1;
			}
			joueur.getPlis().remove(c);
		}
		return (cpt*20);
		
	}



}
