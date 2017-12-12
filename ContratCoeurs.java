package barbu;

import java.util.ArrayList;

public class ContratCoeurs  implements Contrat {
	
	public ContratCoeurs(Jeu jeu) {
		jeu.partie(new ContratCoeurs());
	}

	public ContratCoeurs() {
		// TODO Auto-generated constructor stub
	}

	public boolean fin(Jeu jeu, int nombre) {
		if ( nombre==32) {	//on vérifie le nmobre de cartes pour la partie 
			if (jeu.nbcoeurs == 8) { // on compte le nombre de coeurs en fonction du nombre total de cartes
				return true;
			}
		}
		if (jeu.nbcoeurs == 13) { //si on n'a pas 32 cartes, c'est forcement 52
			return true;
		}
			return false; 
		
	}


	public void comptePoints(ArrayList<Joueur> joueurs) {
		Carte c;
		for (int i=0; i<joueurs.size(); i++){
			while (!joueurs.get(i).getPlis().isEmpty()){
				c = joueurs.get(i).getPlis().iterator().next();
				joueurs.get(i).getPlis().remove(c);
			}
		}
		
	}


}
