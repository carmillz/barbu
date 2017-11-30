package barbu;
import java.util.ArrayList;
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

    @Override
    public String toString() {
   	 return nom;
    }
    
        public void choisirCarte(Jeu jeu){
            for (int i=1; i<=main.size(); i++){
                System.out.println(i + " : " + main.get(i-1).toString());
            }            
            Scanner sc = new Scanner(System.in);
            System.out.println("Choisissez votre carte ! Entrez  le numéro de la carte.");
            int numero = sc.nextInt();
            sc.close();
            jeu.plateau.add(main.get(numero-1));
            main.remove(numero-1);
        }
    
}



