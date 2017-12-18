package barbu;

import java.util.*;

public class Paquet {
    /*
     * l'objet Paquet contient deux attributs : un qui contient le nombre de cartes
     * pour pouvoir cr�er le "bon nombre" de cartes et le paquet de carte qui est
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
     * La m�thode creationPaquet(int nbCartes) permet de cr�er un paquet de
     * nbCarte cartes sachant que suivant le nombre de cartes, on "commence" � une
     * carte diff�rente : 2 pour 52 cartes, 7 pour 32... Au niveau de l'�xecution,
     * on cr�e les cartes pour chaque symbole et on les ajoute � un HashSet qui
     * nous permettera de les tirer au hasard au moment de la distribution
     */

    public static HashSet<Carte> creationPaquet(int nbCartes, int nbJoueurs, Contrat contrat) {
        HashSet<Carte> paquet = new HashSet<Carte>();
        String[] symbole = { "Carreau", "Pique", "Coeur", "Tr�fle" };
        for (int j = 0; j < symbole.length; j++) {
            for (int i = (14 * 4 - nbCartes) / 4 + 1; i <= 14; i++) {
                Carte carte = new Carte(symbole[j], i);
                if (nbJoueurs==4) {
                    paquet.add(carte);
                }else if (nbJoueurs == 3 && !contrat.getClass().toString().equals("class ContratReussite")) {
                    if (nbCartes == 32) {
                        if (!(carte.getValeur() == 7
                                && (carte.getSymbole().equals("Pique") || carte.getSymbole().equals("Tr�fle")))) {
                            paquet.add(carte);
                        }
                    } else {
                        if (!(carte.getValeur() == 2 && carte.getSymbole().equals("Tr�fle"))) {
                            paquet.add(carte);
                        }
                    }
                }
            }
        }
        return paquet;
    }

    /*
     * la m�thode permet, suivant le nombre
     * de joueurs sachant que le joueur qui "distribue" est le dernier de la liste.
     * Au niveau de l'ex�cution de la m�thode, on vide au fur et � mesure le
     * paquet de cartes en ajoutant une carte choisie au hasard avec un it�rateur
     * � la main du joueur puis on la supprime du paquet. On passe ensuite au
     * joueur suivant jusqu'� ce qu'il n'y aie plus de cartes.
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
     * M�thode qui permet de donner le nombre de cartes mises en jeu en fonction du
     * nombre de joueurs afins que ces derniers aient un nombre de cartes �gaux Au
     * niveau de l'exection...
     */

    @Override
    public String toString() {
        return ("Paquet [" + paquet + "]");
    }
}
