package barbu;

import java.util.*;

public class Jeu {
	ArrayList<Joueur> joueurs;
	Collection<?> plateau;
	int nbCartes; // utile pour le contrat pli
	int nbDames; // utile pour le contrat dame
	int nbCoeurs; // utile pour le contrat coeur
	int nbRoi; // utile pour le contrat roi
	int nbCartesReel;

	// CONSTRUCTEUR
	public Jeu(int nbCartes) {
		super();
		this.joueurs = new ArrayList<Joueur>();
		this.plateau = new ArrayList<Carte>();
		this.nbCartes = nbCartes;
		this.nbCoeurs = 0;
		this.nbDames = 0;
		this.nbCartesReel = nbCartes;
	}

	// M�THODES

	/*
	 * M�thode qui va choisir un joueur (le dernier de notre arraylist de joueurs)
	 * et qui va distribuer les cartes
	 *
	 */
	public void rotation() {
		int last = joueurs.size() - 1; // donne la position du dernier joueur
		System.out.println("Le joueur " + joueurs.get(last).toString() + " distribue les cartes");
		System.out.println("Le joueur " + joueurs.get(0).toString() + " choisit le contrat et lance la partie");
		// sens inverse des aiguilles d'une montre
		joueurs.add(joueurs.get(0));
		joueurs.remove(0);
		System.out.println(joueurs.toString());
	}

	/*
	 * M�thode qui permet d'ajouter au joueur gagnant le pli en cours Une fois les
	 * quatre cartes "pos�es" sur le plateau, on les compare avec les autres via
	 * une boucle qui permettra de rendre la carte la "plus forte" sachant que cette
	 * derni�re doit poss�der le m�ªme symboel que la premi�re carte jou�e.
	 * Une fois que cette carte est trouv�e, on l'ajoute �  l'attribut qui
	 * conserve les plis du joueur gagnant
	 */

	public void gagnant(Contrat contrat) {
		if (!contrat.getClass().toString().equals("class ContratReussite")) {
			String symbole = ((ArrayList<Carte>) plateau).get(0).getSymbole();
			int valeur = ((ArrayList<Carte>) plateau).get(0).getValeur();
			int max = 0;
			for (int i = 1; i < joueurs.size(); i++) {
				if (((ArrayList<Carte>) plateau).get(i).getSymbole().equals(symbole)
						&& ((ArrayList<Carte>) plateau).get(i).getValeur() > valeur) {
					valeur = ((ArrayList<Carte>) plateau).get(i).getValeur();
					max = i;
				}
			}
			System.out.println("Le gagnant de ce pli est " + joueurs.get(max).getNom());
			joueurs.get(max).getPlis().addAll(new HashSet(plateau));
		}
	}

	public boolean fin(Contrat contrat) {
		if (contrat.getClass().toString().equals("class ContratBarbu")) {
			return contrat.fin(nbRoi);
		} else if (contrat.getClass().toString().equals("class ContratCoeurs")) {
			return contrat.fin(nbCoeurs);
		} else if (contrat.getClass().toString().equals("class ContratDame")) {
			return contrat.fin(nbDames);
		}
		return false;
	}
	/*
	 * Ex�cution d'une partie
	 */

	public void creationPlateauReussiteVide(int nbCartes) {
		String vide = "               ";
		ArrayList<ArrayList<String>> plateauVide = new ArrayList<ArrayList<String>>();
		// ajout de la ligne Coeur
		plateauVide.add(new ArrayList<String>());
		// ajout de la ligne tr�fle
		plateauVide.add(new ArrayList<String>());
		// ajout de la ligne carreau
		plateauVide.add(new ArrayList<String>());
		// ajout de la ligne pique
		plateauVide.add(new ArrayList<String>());
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < nbCartes; j++) {
				plateauVide.get(i).add(vide);
			}
		}
		plateau = plateauVide;
	}

	public void partie() {
		if (joueurs.size() == 3 && nbCartes == 32) {
			nbCartesReel = 30;
		}
		if (joueurs.size() == 3 && nbCartes == 52) {
			nbCartesReel = 51;
		}
		Paquet paquet;
		Contrat contrat = this.choixContrat();
		boolean finPartie = false;
		while (!finPartie) {
			paquet = new Paquet(nbCartes, this.joueurs.size(), contrat);
			paquet.distribuer(this.joueurs); // distribue les cartes entre les joueurs
			this.rotation();
			if (!contrat.getClass().toString().equals("class ContratReussite")) {
				manche(contrat);
				contrat.comptePoints(joueurs);
				// il faut vider les mains des joueurs si la manche s'est arr�t�e avant de
				// vider cette derni�re
				for (int i = 0; i < joueurs.size(); i++) {
					joueurs.get(i).setMain(new ArrayList<Carte>());
				}
				System.out.println("Les points gagn�s lors de cette manche sont :");
				for (int i = 0; i < joueurs.size(); i++) {
					System.out.println("Le joueur " + joueurs.get(i).getNom() + " a " + joueurs.get(i).getPoints());
				}
				contrat = choixContrat();
				if (contrat == null) {
					finPartie = true;
				} else {
					partie();
				}
			} else {
				creationPlateauReussiteVide(nbCartes);
				mancheReussite((ContratReussite) contrat);
				contrat.comptePoints(joueurs);
			}

		}
	}


	public boolean vide() {
		boolean vide = true;
		// on parcourt les lignes
		for (int i = 0; i < joueurs.size(); i++) {
			// on parcourt les colonnes
			for (int j = 0; j < nbCartes / joueurs.size(); j++) {
				if (!((ArrayList<ArrayList<String>>) plateau).get(i).get(j).equals(vide)) {
					return false;
				}
			}
		}
		return vide;
	}

	public void manche(Contrat contrat) {
		int cartesJouees = 0;
		while (cartesJouees < nbCartesReel) {
			cartesJouees += this.tour();
			if (this.fin(contrat)) {
				cartesJouees = nbCartesReel;

			}
			gagnant(contrat);
			plateau = new ArrayList<Carte>();
		}
	}

	public void mancheReussite(ContratReussite contrat) {
		boolean gagnant = false;
		while (!gagnant) {
			for (int i = 0; i < joueurs.size(); i++) {
				tourReussite(contrat.choixDebut());
				if (joueurs.get(i).getMain().isEmpty()) {
					gagnant = true;
				}
			}
		}
	}

	public void tourReussite(int debut) {
		for (int i = 0; i < joueurs.size(); i++) {
			joueurs.get(i).choisirCarteReussite(this, debut);
		}
	}

	public int tour() {
		for (int j = 0; j < joueurs.size(); j++) {
			System.out.println("C'est au tour de " + joueurs.get(j).getNom() + " de jouer ");
			if (plateau.getClass().toString().equals("class java.util.ArrayList")) {
				System.out.println(" Les cartes d�j�  jou�es sont : ");
				for (int k = 0; k < this.plateau.size(); k++) {
					System.out.println(((ArrayList<Carte>) plateau).get(k) + " jou�e par " + joueurs.get(k).getNom());
				}
				joueurs.get(j).choisirCarte(this);
			}

		}
		System.out.println(" Les cartes jou�es lors de ce tour sont : ");
		for (int k = 0; k < this.plateau.size(); k++) {
			System.out.println(((ArrayList<Joueur>) plateau).get(k) + " jou�e par " + joueurs.get(k).getNom());
			if (((ArrayList<Carte>) plateau).get(k).getSymbole().equals("Coeur")) {
				nbCoeurs++;
				if (((ArrayList<Carte>) plateau).get(k).getValeur() == 13) {
					nbRoi++;
				}
			}
			if (((ArrayList<Carte>) plateau).get(k).getValeur() == 12) {
				nbDames++;
			}
		}

		return plateau.size();
	}

	public void creationJoueurs() throws Exception {
		boolean creation = true;
		Scanner sc = new Scanner(System.in);
		int i = 1;
		while (creation) {
			System.out.println("Entrez le nom du joueur ou faites entrer s'il n'y en a plus");
			String nom = sc.nextLine();
			if (nom.equals("")) {
				creation = false;
			} else {
				joueurs.add(new Joueur(nom, String.valueOf(i)));
			}
			i++;
		}
		if (joueurs.size() <= 1) {
			throw new Exception("Vous ne pouvez pas jouer tout seul !");
		}
		// sc.close();
	}

	// M�thode qui demande �  l'utilisateur le contrat qu'il veut
	public Contrat choixContrat() {
		boolean quitter = false;
		int tmp;
		Scanner sc = new Scanner(System.in);
		while (!quitter) {
			System.out.println("Choississez votre contrat !");
			System.out.println("1 : Les plis");
			System.out.println("2 : Les dames");
			System.out.println("3 : Les coeurs");
			System.out.println("4 : Le barbu (ou le roi de Coeur)");
			System.out.println("5 : La R�ussite");
			System.out.println("6 : La Salade");
			System.out.println("7 : Quitter");
			tmp = sc.nextInt();
			if (!quitter) {
				int choix = Integer.valueOf(tmp);
				switch (choix) {
				case 1:
					return new ContratPli();
				case 2:
					return new ContratDame();
				case 3:
					return new ContratCoeurs();
				case 4:
					return new ContratBarbu();
				case 5:
					return new ContratReussite();
				case 6:
					return new ContratSalade();
				case 7:
					quitter = true;
					return null;
				default:
					System.out.println("Option inexistante, recommencez");
					break;
				}
				// sc.close();
			}
		}
		return null;
	}


	public void ajoutReussite(Carte carte) {
		// la carte peut �tre ajout�e
		int posI;
		int posJ = carte.getValeur() - 1;
		if (carte.getSymbole().equals("Coeur")) {
			posI = 0;
		} else if (carte.getSymbole().equals("Tr�fle")) {
			posI = 1;
		} else if (carte.getSymbole().equals("Carreau")) {
			posI = 2;
		} else {
			posI = 3;
		}
		((ArrayList<ArrayList<String>>) plateau).get(posI).set(posJ, carte.toString());
	}

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Voulez-vous jouer avec 32 ou 52 cartes ?"); // demande le nombre de cartes
		int nbCartes = sc.nextInt();
		Jeu jeu = new Jeu(nbCartes);
		if (nbCartes == 32) {
			// il faut ajuster le nombre de coeurs pour la m�thode fin du contrat coeur
			// Comme il n'y a que 8 coeurs lorsqu'on joue � 32 cartes et que la m�thode
			// fin "arr�te" le jeu � 14
			jeu.nbCoeurs = 6;
		}
		jeu.creationJoueurs(); // initialise les joueurs
		jeu.partie();

	}

}
