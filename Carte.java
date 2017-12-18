package barbu;

public class Carte {
	/*
	 * La classe carte contient les informations relatives aux cartes soit un nom
	 * (de 2 � as) et un symbole (coeur, pique, tr�fle et carreau). On y a
	 * �galement ajout� la valeur qui est croissante de 2 � 14 afin de faire les
	 * comparaisons entre les cartes plus facilement � la fin d'un pli
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
		 * calcul�e � partir du nom via une m�thode annexe
		 */
		super();
		this.symbole = symbole;
		this.valeur = valeur;
		this.nom = valeurToNom(valeur);
	}

	/*
	 * La m�thode valeurToNom permet, � partir de la valeur qui est pass�e en
	 * param�tre de donner un nom � la carte pour simplifier la compr�hension,
	 * notamment du valet � l'as.
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
