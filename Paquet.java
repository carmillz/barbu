package barbu;

import java.util.*;

public class Paquet {
    /*
     * l'objet Paquet contient deux attributs : un qui contient le nombre de cartes
     * pour pouvoir créer le "bon nombre" de cartes et le paquet de carte qui est
     * un HashSet de cartes afin de faciliter la distribution au hasard entre les
     * joueurs
     */
    Collection<Carte> paquet;
    int nbCartes;

    public Paquet(int nbCartes, int nbJoueurs,Contrat contrat) {
        this.paquet = creationPaquet(nbCartes, nbJoueurs, contrat);
        this.nbCartes = nbCartes;
    }

    /*
     * La méthode creationPaquet(int nbCartes) permet de créer un paquet de
     * nbCarte cartes sachant que suivant le nombre de cartes, on "commence" à une
     * carte différente : 2 pour 52 cartes, 7 pour 32... Au niveau de l'éxecution,
     * on crée les cartes pour chaque symbole et on les ajoute à un HashSet qui
     * nous permettera de les tirer au hasard au moment de la distribution
     */

    public static HashSet<Carte> creationPaquet(int nbCartes, int nbJoueurs, Contrat contrat) {
        HashSet<Carte> paquet = new HashSet<Carte>();
        String[] symbole = { "Carreau", "Pique", "Coeur", "Trèfle" };
        for (int j = 0; j < symbole.length; j++) {
            for (int i = (14 * 4 - nbCartes) / 4 + 1; i <= 14; i++) {
                Carte carte = new Carte(symbole[j], i);
                if (nbJoueurs==4) {
                    paquet.add(carte);
                }else if (nbJoueurs == 3 && !contrat.getClass().toString().equals("class ContratReussite")) {
                    if (nbCartes == 32) {
                        if (!(carte.getValeur() == 7
                                && (carte.getSymbole().equals("Pique") || carte.getSymbole().equals("Trèfle")))) {
                            paquet.add(carte);
                        }
                    } else {
                        if (!(carte.getValeur() == 2 && carte.getSymbole().equals("Trèfle"))) {
                            paquet.add(carte);
                        }
                    }
                }
            }
        }
        return paquet;
    }

    /*
     * la méthode permet, suivant le nombre
     * de joueurs sachant que le joueur qui "distribue" est le dernier de la liste.
     * Au niveau de l'exécution de la méthode, on vide au fur et à mesure le
     * paquet de cartes en ajoutant une carte choisie au hasard avec un itérateur
     * à la main du joueur puis on la supprime du paquet. On passe ensuite au
     * joueur suivant jusqu'à ce qu'il n'y aie plus de cartes.
     */
    public void distribuer(ArrayList<Joueur> joueurs) {
        Carte c;
        ArrayList<Joueur> temp = joueurs;
        while (paquet.size() != 0) {
            for (int i = 0; i < temp.size(); i++) {
                c = paquet.iterator().next();
                paquet.remove(c);
                temp.get(i).getMain().add(c);
            }
        }
		for (int h=0;h<temp.size();h++) {
			joueurs.get(h).trierMain();
		}
    }

    /*
     * Méthode qui permet de donner le nombre de cartes mises en jeu en fonction du
     * nombre de joueurs afins que ces derniers aient un nombre de cartes égaux Au
     * niveau de l'exection...
     */

    @Override
    public String toString() {
        return ("Paquet [" + paquet + "]");
    }
}
