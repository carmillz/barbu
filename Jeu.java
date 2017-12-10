package barbu;

import java.util.*;

public class Jeu {
	ArrayList<Joueur> joueurs;
	ArrayList<Carte> plateau;
	int nbcartesencours;
	int nbcartes; // utile pour le contrat pli
	int nbdames; // utile pour le contrat dame
	int nbcoeurs; // utilie pour le contrat coeur

	// CONSTRUCTEUR
	public Jeu(int nombre) {
		super();
		this.joueurs = new ArrayList<Joueur>();
		this.plateau = new ArrayList<Carte>();
		this.nbcartesencours = nombre;
		this.nbcartes = 0;
		this.nbcoeurs = 0;
		this.nbdames = 0;
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
		System.out.println("Le joueur " + joueurs.get(0).toString() + " lance la partie");
		// sens inverse des aiguilles d'une montre
		joueurs.add(joueurs.get(0));
		joueurs.remove(0);
		System.out.println(joueurs.toString());
	}
	
	/*
	 * Méthode qui permet d'ajouter au joueur gangnant le pli en cours
	 * Une fois les quatre cartes "posées" sur le plateau, on les 
	 * compare avec les autres via une boucle qui permettra de rendre
	 * la carte la "plus forte" sachant que cette dernière doit
	 * possèder le même symboel que la première carte jouée.
	 * Une fois que cette carte est trouvée, on l'ajoute à l'attribut 
	 * qui conserve les plis du joueur gagnant
	 */
	public void gagnant() { 
		String symbole = plateau.get(0).getSymbole();
		int valeur = plateau.get(0).getValeur();
		int max = 0;
		for (int i = 1; i < 4; i++) { 
			if (plateau.get(i).getSymbole().equals(symbole) && plateau.get(i).getValeur() > valeur) {
				max = i; 
			}
		}
		joueurs.get(max).setPlis(new HashSet<Carte>(plateau));
	}

	/*
	 * Exécution d'une partie
	 */
	public void partie(ArrayList<Joueur> joueurs) {
		boolean finPartie = false;
		while (!finPartie) {
			boolean finManche = false;
			while (!finManche) {
				for (int j = 0; j < 4; j++) {
					for (int i = 0; i < joueurs.size(); i++) {
						joueurs.get(i).choisirCarte(this);
						j++;
						this.gagnant();
					}
				}
				nbcartesencours = nbcartesencours - 4;
				finManche = (this.choixContrat().fin(this,0) || this.choixContrat().fin(this, nbcartesencours));

				/*
				 * on change la valeur de finManche si : les joueurs n'ont plus de cartes
				 * suivant les contrats
				 *
				 */
			}
		}
	}
	
	
	public void creationJoueurs() throws Exception{
		boolean creation = true;
		Scanner sc = new Scanner(System.in);
		int i = 1;
		while (creation) {
			System.out.println("Entrez le nom du joueur ou faites entrer s'il n'y en a plus");
			String nom = sc.nextLine();
			if (nom.equals("")){
				creation=false;
			}else {
				joueurs.add(new Joueur(nom,String.valueOf(i)));
			}
			i++;
		}
		if (joueurs.size()<=1){
			throw new Exception("Vous ne pouvez pas jouer tout seul !");
		}
		sc.close();
	}

	// Méthode qui demande à l'utilisateur le contrat qu'il veut
	public Contrat choixContrat() {
		boolean quitter = false;
		
		while (!quitter) {
			System.out.println("Choississez votre contrat !");
			System.out.println("1 : Les plis");
			System.out.println("2 : Les dames");
			System.out.println("3 : Les coeurs");
			System.out.println("4 : Le barbu (ou le roi de Coeur)");
			System.out.println("5 : La Réussite");
			System.out.println("6 : La Salade");
			System.out.println("7 : Quitter");
			Scanner sc = new Scanner(System.in);
			String tmp;
			if ((tmp=sc.nextLine())!=null){
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
					case 5:
						return new ContratReussite(this);
					case 6:
						return new ContratSalade(this);
					case 7:
						quitter=true;
						break;
					default:
						System.out.println("Option inexistante, recommencez");
						break;
					}
				sc.close();
				}
			}
			
		
		return null; // DEMANDER A CASSANDRE
	}

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Voulez-vous jouer avec 32 ou 52 cartes ?"); // demande le nombre de cartes
		int nbcartes = sc.nextInt();
		Paquet paquet = new Paquet(nbcartes);
		Jeu jeu = new Jeu(nbcartes); // initialise le jeu
		jeu.creationJoueurs(); // initialise les joueurs
		paquet.cartesAJouer(jeu.joueurs.size()); // adapte le nombre de cartes en fonction du nombre de joueurs
		paquet.distribuer(jeu.joueurs); // distribue les cartes entre les joueurs
		jeu.rotation();
		jeu.choixContrat();

		jeu.joueurs.get(0).choisirCarte(jeu);
		//sc.close();
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
