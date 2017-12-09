package barbu;

public class ContratCoeurs  implements Contrat {
	
	public ContratCoeurs(Jeu jeu) {
		jeu.partie(jeu.joueurs);
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


	public int comptePoints(Joueur joueur) {
		int cpt =0;
		while (!joueur.getPlis().isEmpty()) {
			Carte c = joueur.getPlis().iterator().next();
			if (c.getSymbole()=="Coeur") {
				cpt=cpt+1;
			}
			joueur.getPlis().remove(c);
		}
		return (cpt*10);
		
	}


}
