import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

public class Joueur {
    private String nom;
    private String cle;
    private ArrayList<Carte> main;
    private ArrayList<Integer> points;
    private HashSet<Carte> plis;

    // CONSTRUCTEUR
    public Joueur(String nom, String cle) {
        super();
        this.nom = nom;
        this.cle = cle;
        this.main = new ArrayList<Carte>();
        this.points = new ArrayList<Integer>();
        this.plis = new HashSet<Carte>();
    }

    // GETTERS ET SETTERS
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCle() {
        return cle;
    }

    public void setCle(String cle) {
        this.cle = cle;
    }


    public ArrayList<Carte> getMain() {
        return main;
    }

    public void setMain(ArrayList<Carte> main) {
        this.main = main;
    }

    public ArrayList<Integer> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Integer> points) {
        this.points = points;
    }

    public HashSet<Carte> getPlis() {
        return plis;
    }

    public void setPlis(HashSet<Carte> plis) {
        this.plis = plis;
    }

    // MÉTHODES
    @Override
    public String toString() {
        return nom;
    }

    public void choisirCarte(Jeu jeu, Collection<Carte> plateau) {
        boolean possible = false;
        System.out.println("Vos cartes sont :");
        if (plateau.isEmpty()) {
            possible = true;
        }
        for (int i = 1; i <= main.size(); i++) {
            System.out.println(i + " : " + main.get(i - 1).toString());
            if (!plateau.isEmpty()
                    && ( ((ArrayList<Carte>) plateau).get(0).getSymbole().equals(main.get(i - 1).getSymbole())))
                possible = true;
        }
        boolean pasJoue = true;
        while (pasJoue) {
            if (possible) {
                System.out.println("Choisissez votre carte ! Entrez  le numéro de la carte.");
                Scanner scan = new Scanner(System.in);
                int numero = scan.nextInt();
                if (numero - 1 >= main.size()) {
                    System.out.println("Cette carte n'existe pas... Réessayez !");
                } else if (jeu.plateau.size() == 0 || ((ArrayList<Carte>) plateau).get(0).getSymbole()
                        .equals(main.get(numero - 1).getSymbole())) {
                    ((ArrayList<Carte>)jeu.plateau).add(main.get(numero - 1));
                    pasJoue = false;
                    main.remove(numero - 1);
                } else {
                    System.out.println(" Vous ne pouvez pas jouer cette carte !");
                }
            } else {
                System.out.println("Vous ne pouvez pas jouer la couleur demandée, jouez n'importe quelle carte !");
                Scanner scan = new Scanner(System.in);
                int numero = scan.nextInt();
                if (numero - 1 >= main.size()) {
                    System.out.println("Cette carte n'existe pas... Réessayez !");
                } else {
                    ((ArrayList<Carte>)jeu.plateau).add(main.get(numero - 1));
                    main.remove(numero - 1);
                    pasJoue = false;
                }
            }
        }

    }




    public void choisirCarte(Jeu jeu, HashSet plateau) {
        boolean possible=false;
        System.out.println("Vos cartes sont :");
        if (jeu.plateau.isEmpty()){
            possible=true;
        }
        for (int i = 1; i <= main.size(); i++) {
            System.out.println(i + " : " + main.get(i - 1).toString());
            if (plateau.isEmpty()){
                possible=true;
            }
        }

        boolean pasJoue=true;
        while (pasJoue){
            if (possible){
                System.out.println("Choisissez votre carte ! Entrez  le numéro de la carte.");
                Scanner scan = new Scanner(System.in);
                int numero = scan.nextInt();
                if (numero-1>=main.size()){
                    System.out.println("Cette carte n'existe pas... Réessayez !");
                }else if (jeu.plateau.size()==0 || ((ArrayList<Carte>) jeu.plateau).get(0).getSymbole().equals(main.get(numero-1).getSymbole())){
                    ((ArrayList<Carte>)jeu.plateau).add(main.get(numero-1));
                    pasJoue=false;
                    main.remove(numero-1);
                }else {System.out.println("Choisissez votre carte ! Entrez  le numéro de la carte.");
                    scan = new Scanner(System.in);
                    numero = scan.nextInt();
                    if (numero-1>=main.size()){
                        System.out.println("Cette carte n'existe pas... Réessayez !");
                    }
                    System.out.println(" Vous ne pouvez pas jouer cette carte !");
                }
            }else {
                System.out.println("Vous ne pouvez pas jouer la couleur demandée, jouez n'importe quelle carte !");
                Scanner scan = new Scanner(System.in);
                int numero = scan.nextInt();
                if (numero-1>=main.size()){
                    System.out.println("Cette carte n'existe pas... Réessayez !");
                }else {
                    ((ArrayList<Carte>)jeu.plateau).add(main.get(numero-1));
                    main.remove(numero-1);
                    pasJoue=false;
                }
            }
        }

    }

    public Carte choisirCarteReussite(Jeu jeu){
        int i = 0;
        boolean pasJoue=true;
        boolean possible=true;
        Carte carte = null;
        Carte sup = null;
        Carte inf = null;
        while (i<main.size() && possible){
            if (!(main.get(i).getValeur()==14)){
                sup = new Carte(main.get(i).getSymbole(), main.get(i).getValeur()+1) ;
            }
            if (!((main.get(i).getValeur()==2))){
                inf = new Carte(main.get(i).getSymbole(), main.get(i).getValeur()-1) ;
            }
            if ((sup==null && inf==null) || ((!jeu.plateau.contains(sup.toString())) && (!jeu.plateau.contains(inf.toString())))){
                possible=false;
            }

        }
        if (!possible){
            System.out.println("Désolé, vous le pouvez pas jouer ! Passer votre tour");
        }
        while (possible && pasJoue){
            System.out.println("Choisissez votre carte ! Entrez  le numéro de la carte.");
            Scanner scan = new Scanner(System.in);
            int numero = scan.nextInt();
            if (numero-1>=main.size()){
                System.out.println("Cette carte n'existe pas... Réessayez !");
            }else {
                carte = main.get(numero);
                if (!(main.get(i).getValeur()==14)){
                    sup = new Carte(carte.getSymbole(), carte.getValeur()+1) ;
                }
                if (!((main.get(i).getValeur()==2))){
                    inf = new Carte(carte.getSymbole(), carte.getValeur()-1) ;
                }
                if (jeu.plateau.contains(sup)){
                    main.remove(carte);
                    pasJoue=false;
                    jeu.ajoutReussite(carte);
                }else if (jeu.plateau.contains(inf)){
                    main.remove(carte);
                    pasJoue=false;
                    jeu.ajoutReussite(carte);
                }else {
                    System.out.println("Vous ne pouvez pas jouer cette carte ! Essayez-en une autre !");
                }
            }
        }
        return null;
    }

    public void trierMain() {
        ArrayList<Carte> carreau = new ArrayList<Carte>();
        ArrayList<Carte> pique = new ArrayList<Carte>();
        ArrayList<Carte> trefle = new ArrayList<Carte>();
        ArrayList<Carte> coeur = new ArrayList<Carte>();
        for (int i = 0; i < main.size(); i++) {
            switch (main.get(i).getSymbole()) {
                case "Carreau":
                    carreau.add(main.get(i));
                    break;
                case "Pique":
                    pique.add(main.get(i));
                    break;
                case "Trèfle":
                    trefle.add(main.get(i));
                    break;
                case "coeur":
                    coeur.add(main.get(i));
                    break;
            }
        }

        // DEMANDER A CASSANDRE POUR TRIER LES CARTES
        // carreau.sort();

    }

    public int compareTo(int c) {
        return (this.main.get(0).compareTo(c));
    }
    // FIN MÉTHODES
