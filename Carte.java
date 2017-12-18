package barbu;

public class Carte {
	/*
	 * La classe carte contient les informations relatives aux cartes soit un nom
	 * (de 2 à  as) et un symbole (coeur, pique, trèfle et carreau). On y a
	 * également ajouté la valeur qui est croissante de 2 à  14 afin de faire les
	 * comparaisons entre les cartes plus facilement à  la fin d'un pli
	 */
	private String symbole;
	private int valeur;
	private String nom;

	public String getSymbole() {
		return symbole;
	}

	public void setSymbole(String symbole) {
		this.symbole = symbole;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return nom + " de " + symbole;
	}

	public Carte(String symbole, int valeur) {
		/*
		 * Pour construire une carte, on a besoin du nom et du symbole, la valeur est
		 * calculée à  partir du nom via une méthode annexe
		 */
		super();
		this.symbole = symbole;
		this.valeur = valeur;
		this.nom = valeurToNom(valeur);
	}

	/*
	 * La méthode valeurToNom permet, à  partir de la valeur qui est passée en
	 * paramètre de donner un nom à  la carte pour simplifier la compréhension,
	 * notamment du valet à  l'as.
	 */
	public String valeurToNom(int valeur) {
		String nom = "";
		if (valeur == 11) {
			nom = "Valet";
		} else if (valeur == 12) {
			nom = "Dame";
		} else if (valeur == 13) {
			nom = "Roi";
		} else if (valeur == 14) {
			nom = "As";
		} else {
			for (int i = 2; i <= 10; i++) {
				if (valeur == i) {
					nom = String.valueOf(i);
				}
			}
		}
		return nom;
	}
}
