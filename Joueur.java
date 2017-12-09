package barbu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Joueur implements Comparable<Carte> {
	private String nom;
	private String cle;
	private int nbplis;
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

	public int getNbplis() {
		return nbplis;
	}

	public void setNbplis(int nbplis) {
		this.nbplis = nbplis;
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

	public void choisirCarte(Jeu jeu) {
		for (int i = 1; i <= main.size(); i++) {
			System.out.println(i + " : " + main.get(i - 1).toString());
		}
		System.out.println("Choisissez votre carte ! Entrez  le numéro de la carte.");
		Scanner scan = new Scanner(System.in);
		int numero = scan.nextInt();
		jeu.plateau.add(main.get(numero - 1));
		main.remove(numero - 1);
		//scan.close();
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
		
		//DEMANDER A CASSANDRE POUR TRIER LES CARTES
		//carreau.sort();

	}

	public int compareTo(int c) {
		return (this.main.get(0).compareTo(c));
	}
	//FIN MÉTHODES

	@Override
	public int compareTo(Carte arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
