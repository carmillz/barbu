package barbu;

import java.util.*;

public class Jeu {
	ArrayList<Joueur> joueurs;
	Collection<Carte> plateau;
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
		this.nbCartesReel=nbCartes;
	}

	// MÃ‰THODES

	/*
	 * MÃ©thode qui va choisir un joueur (le dernier de notre arraylist de joueurs)
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
	 * MÃ©thode qui permet d'ajouter au joueur gagnant le pli en cours Une fois les
	 * quatre cartes "posÃ©es" sur le plateau, on les compare avec les autres via une
	 * boucle qui permettra de rendre la carte la "plus forte" sachant que cette
	 * derniÃ¨re doit possÃ¨der le mÃ Âªme symboel que la premiÃ¨re carte jouÃ©e. Une
	 * fois que cette carte est trouvÃ©e, on l'ajoute Ã Â  l'attribut qui conserve les
	 * plis du joueur gagnant
	 */


	public void gagnant() {
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
		joueurs.get(max).getPlis().addAll(new HashSet<Carte>(plateau));
	}

	public boolean fin(Contrat contrat) {
		if (contrat.getClass().toString().equals("class barbu.ContratBarbu")) {
			return contrat.fin(nbRoi);
		} else if (contrat.getClass().toString().equals("class barbu.ContratCoeurs")) {
			return contrat.fin(nbCoeurs);
		} else if (contrat.getClass().toString().equals("class barbu.ContratDame")) {
			return contrat.fin(nbDames);
		}
		return false;
	}
	/*
	 * ExÃ©cution d'une partie
	 */

	public void partie() {
		if (joueurs.size()==3 && nbCartes==32) {
			nbCartesReel =30;
		}
		if (joueurs.size()==3 && nbCartes==52) {
			nbCartesReel=51;
		}
		Paquet paquet ;
		Contrat contrat = this.choixContrat();
		boolean finPartie = false;
		while (!finPartie) {
			paquet = new Paquet(nbCartes, this.joueurs.size());
			paquet.distribuer(this.joueurs); // distribue les cartes entre les joueurs
			this.rotation();
			manche(contrat);
			contrat.comptePoints(joueurs);
			// il faut vider les mains des joueurs si la manche s'est arrÃªtÃ©e avant de vider cette derniÃ¨re
			for (int i=0; i < joueurs.size(); i++) {
				joueurs.get(i).setMain(new ArrayList<Carte>()) ;
			}
			System.out.println("Les points gagnÃ©s lors de cette manche sont :");
			for (int i = 0; i < joueurs.size(); i++) {
				System.out.println("Le joueur " + joueurs.get(i).getNom() + " a " + joueurs.get(i).getPoints());
			}
			contrat = choixContrat();
			if (contrat == null) {
				finPartie = true;
			} else {
				partie();
			}
		}
	}

	public void manche(Contrat contrat) {
		int cartesJouees = 0;
		while (cartesJouees < nbCartesReel) {
			cartesJouees += this.tour();
			if (this.fin(contrat)) {
				cartesJouees = nbCartesReel;

			}
			gagnant();
			plateau = new ArrayList<Carte>();
		}
	}

	public int tour() {
		for (int j = 0; j < joueurs.size(); j++) {
			System.out.println("C'est au tour de " + joueurs.get(j).getNom() + " de jouer ");
			System.out.println(" Les cartes dÃ©jÃ Â  jouÃ©es sont : ");
			for (int k = 0; k < this.plateau.size(); k++) {
				System.out.println(((ArrayList<Carte>) plateau).get(k) + " jouÃ©e par " + joueurs.get(k).getNom());
			}
			joueurs.get(j).choisirCarte(this, plateau);
		}
		System.out.println(" Les cartes jouÃ©es lors de ce tour sont : ");
		for (int k = 0; k < this.plateau.size(); k++) {
			System.out.println(((ArrayList<Carte>) plateau).get(k) + " jouÃ©e par " + joueurs.get(k).getNom());
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

	public void creationJoueurs(int nbJoueurs) {
		boolean creation = true;
		Scanner sc = new Scanner(System.in);
		int i = 0;
		while (i < nbJoueurs ){
			System.out.println("Entrez le nom du joueur ou faites entrer s'il n'y en a plus");
			String nom = sc.nextLine();
			if (!nom.equals("")) {
				joueurs.add(new Joueur(nom, String.valueOf(i)));
				i++;
			}else {
				System.out.println("Le nom entré n'est pas valide, recommencez");
			}
		}

		while (creation) {

			i++;
		}
		// sc.close();
	}

	// MÃ©thode qui demande Ã Â  l'utilisateur le contrat qu'il veut
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
			System.out.println("5 : La RÃ©ussite");
			System.out.println("6 : La Salade");
			System.out.println("7 : Quitter");
			tmp = sc.nextInt();
			if (!quitter) {
				int choix = Integer.valueOf(tmp);
				switch (choix) {
					case 1:
						return new ContratPli(this);
					case 2:
						return new ContratDame(this);
					case 3:
						return new ContratCoeurs(this);
					case 4:
						return new ContratBarbu(this);
					//case 5:
						//return new ContratReussite(this);
					case 6:
						return new ContratSalade(this);
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

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Voulez-vous jouer avec 32 ou 52 cartes ?"); // demande le nombre de cartes
		int nbCartes = sc.nextInt();
		Jeu jeu = new Jeu(nbCartes);
		if (nbCartes == 32) {
			// il faut ajuster le nombre de coeurs pour la mÃ©thode fin du contrat coeur
			// Comme il n'y a que 8 coeurs lorsqu'on joue Ã  32 cartes et que la mÃ©thode
			// fin "arrÃªte" le jeu Ã  14
			jeu.nbCoeurs = 6;
		}
		//jeu.creationJoueurs(); // initialise les joueurs
		jeu.partie();

		// sc.close();
		/*
		 * for (int i=0; i<jeu.joueurs.get(0).getMain().size(); i++){
		 * System.out.println(i + " : " +
		 * jeu.joueurs.get(0).getMain().get(i).toString()); }
		 */

		// System.out.println(paquet.paquet.size());
		// System.out.println("Main :"+ j1.getMain().toString());
		// System.out.println(j1.getMain().size());
		;
	}

}
