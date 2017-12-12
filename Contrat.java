
package barbu;

import java.util.ArrayList;

public interface Contrat {


	public abstract void comptePoints(ArrayList<Joueur> joueurs);

	public abstract boolean fin(Jeu jeu, int nombre);
	

	

}
