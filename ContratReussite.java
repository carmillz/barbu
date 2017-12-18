package barbu;

import java.util.ArrayList;
import java.util.Scanner;

public class ContratReussite implements Contrat {

	public ContratReussite(Jeu jeu) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void comptePoints(ArrayList<Joueur> joueurs) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean fin(int nombre) {
		if (nombre>=3) {
			return true;
		}
		return false;
	}

	public String [][] creation (){
		return null;
		
	}



	
	public void partieReussite(Jeu jeu) {
		int cpt=0;
		while (!this.fin()) {
			for (int i=0; i>=jeu.joueurs.size();i++) {
				jeu.joueurs.get(i).choisirCarte(jeu);
				if (jeu.joueurs.get(i).getMain().isEmpty()) {
					cpt=cpt+1;
				}
				
			}
		}
	}
}
