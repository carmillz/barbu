package barbu;

import java.util.ArrayList;
import java.util.Scanner;

public class ContratReussite implements Contrat {
	int debut;
	public final String vide = "               ";

	public ContratReussite() {
		this.debut = 7;
	}

	public int choixDebut() {
		System.out.println("Entrez la carte par laquelle vous voulez commencer !");
		Scanner sc = new Scanner(System.in);
		int debut = sc.nextInt();
		while (debut > 14 && debut < 2) {
			System.out.println("Ce choix n'est pas valide, recommencez !");
			debut = sc.nextInt();
		}
		this.debut = debut;
		return debut;
	}

	@Override
	public void comptePoints(ArrayList<Joueur> joueurs) {
		for (int i = 0; i < joueurs.size(); i++) {
			if (joueurs.get(i).getMain().isEmpty()) {
				joueurs.get(i).getPoints().add(-100);
			}
		}

	}

	@Override
	public boolean fin(int nombre) {
		// méthode non utilisée
		return false;
	}

}
