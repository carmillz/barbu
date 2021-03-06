package barbu;

import java.util.*;

//important
public class Paquet {
	/*
	 * l'objet Paquet contient deux attributs : un qui contient le nombre de cartes
	 * pour pouvoir crÃƒÂ©er le "bon nombre" de cartes et le paquet de carte qui est
	 * un HashSet de cartes afin de faciliter la distribution au hasard entre les
	 * joueurs
	 */
	HashSet<Carte> paquet;
	int nbCartes;

	public Paquet(int nbCartes, int nbJoueurs) {
		this.paquet = creationPaquet(nbCartes, nbJoueurs);
		this.nbCartes = nbCartes;
	}

	/*
	 * La mÃƒÂ©thode creationPaquet(int nbCartes) permet de crÃƒÂ©er un paquet de
	 * nbCarte cartes sachant que suivant le nombre de cartes, on "commence" ÃƒÂ  une
	 * carte diffÃƒÂ©rente : 2 pour 52 cartes, 7 pour 32... Au niveau de l'ÃƒÂ©xecution,
	 * on crÃƒÂ©e les cartes pour chaque symbole et on les ajoute ÃƒÂ  un HashSet qui
	 * nous permettera de les tirer au hasard au moment de la distribution
	 */

	public HashSet<Carte> creationPaquet(int nbCartes, int nbJoueurs) {
		HashSet<Carte> paquet = new HashSet<Carte>();
		String[] symbole = { "Carreau", "Pique", "Coeur", "TrÃ¨fle" };
		for (int j = 0; j < symbole.length; j++) {
			for (int i = (14 * 4 - nbCartes) / 4 + 1; i <= 14; i++) {
				Carte carte = new Carte(symbole[j], i);
				if (nbJoueurs==4) {
					paquet.add(carte);
				}else if (nbJoueurs == 3) {
					if (nbCartes == 32) {
						if (!(carte.getValeur() == 7
								&& (carte.getSymbole().equals("Pique") || carte.getSymbole().equals("TrÃ¨fle")))) {
							paquet.add(carte);
						}
					} else {
						if (!(carte.getValeur() == 2 && carte.getSymbole().equals("TrÃ¨fle"))) {
							paquet.add(carte);
						}
					}
				}
			}
		}
		return paquet;
	}

	/*
	 * la mÃƒÂ©thode distribuer(ArrayList<Joueur> joueurs) permet, suivant le nombre
	 * de joueurs sachant que le joueur qui "distribue" est le dernier de la liste.
	 * Au niveau de l'exÃƒÂ©cution de la mÃƒÂ©thode, on vide au fur et ÃƒÂ  mesure le
	 * paquet de cartes en ajoutant une carte choisie au hasard avec un itÃƒÂ©rateur
	 * ÃƒÂ  la main du joueur puis on la supprime du paquet. On passe ensuite au
	 * joueur suivant jusqu'ÃƒÂ  ce qu'il n'y aie plus de cartes.
	 */
	public void distribuer(ArrayList<Joueur> joueurs) {
		Carte c;
		while (paquet.size() != 0) {
			for (int i = 0; i < joueurs.size(); i++) {
				c = paquet.iterator().next();
				paquet.remove(c);
				joueurs.get(i).getMain().add(c);
			}
		}
	}

	/*
	 * MÃ©thode qui permet de donner le nombre de cartes mises en jeu en fonction du
	 * nombre de joueurs afins que ces derniers aient un nombre de cartes ÃƒÂ©gaux Au
	 * niveau de l'exection...
	 */

    @Override
    public String toString() {
        return ("Paquet [" + paquet + "]");
    }
}
