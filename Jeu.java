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

	// MÉTHODES

	/*
	 * Méthode qui va choisir un joueur (le dernier de notre arraylist de joueurs)
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
	 * Méthode qui permet d'ajouter au joueur gagnant le pli en cours Une fois les
	 * quatre cartes "posées" sur le plateau, on les compare avec les autres via
	 * une boucle qui permettra de rendre la carte la "plus forte" sachant que cette
	 * dernière doit possèder le màÂªme symboel que la première carte jouée.
	 * Une fois que cette carte est trouvée, on l'ajoute àÂ  l'attribut qui
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
	 * Exécution d'une partie
	 */

	public void creationPlateauReussiteVide(int nbCartes) {
		String vide = "               ";
		ArrayList<ArrayList<String>> plateauVide = new ArrayList<ArrayList<String>>();
		// ajout de la ligne Coeur
		plateauVide.add(new ArrayList<String>());
		// ajout de la ligne trèfle
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
				// il faut vider les mains des joueurs si la manche s'est arrêtée avant de
				// vider cette dernière
				for (int i = 0; i < joueurs.size(); i++) {
					joueurs.get(i).setMain(new ArrayList<Carte>());
				}
				System.out.println("Les points gagnés lors de cette manche sont :");
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
				System.out.println(" Les cartes déjàÂ  jouées sont : ");
				for (int k = 0; k < this.plateau.size(); k++) {
					System.out.println(((ArrayList<Carte>) plateau).get(k) + " jouée par " + joueurs.get(k).getNom());
				}
				joueurs.get(j).choisirCarte(this);
			}

		}
		System.out.println(" Les cartes jouées lors de ce tour sont : ");
		for (int k = 0; k < this.plateau.size(); k++) {
			System.out.println(((ArrayList<Joueur>) plateau).get(k) + " jouée par " + joueurs.get(k).getNom());
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

	// Méthode qui demande àÂ  l'utilisateur le contrat qu'il veut
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
			System.out.println("5 : La Réussite");
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
		// la carte peut être ajoutée
		int posI;
		int posJ = carte.getValeur() - 1;
		if (carte.getSymbole().equals("Coeur")) {
			posI = 0;
		} else if (carte.getSymbole().equals("Trèfle")) {
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
			// il faut ajuster le nombre de coeurs pour la méthode fin du contrat coeur
			// Comme il n'y a que 8 coeurs lorsqu'on joue à 32 cartes et que la méthode
			// fin "arrête" le jeu à 14
			jeu.nbCoeurs = 6;
		}
		jeu.creationJoueurs(); // initialise les joueurs
		jeu.partie();

	}

}
