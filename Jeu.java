package barbu;

import java.util.*;

public class Jeu {
    ArrayList<Joueur> joueurs;
    ArrayList<Carte> plateau;
    int nbcartes;
    int nbdames;
    int nbcoeurs;
    
    
    //CONSTRUCTEUR
    public Jeu() {
   	 super();
   	 this.joueurs = new ArrayList<Joueur>();
         this.plateau = new ArrayList<Carte>();
         this.nbcartes = 0;
         this.nbcoeurs = 0;
         this.nbdames = 0;
    }
    ///MÉTHODES
    
    /*
     * Méthode qui va choisir un joueur (le dernier de notre arraylist de joueurs) 
     *et qui va distribuer les cartes
     *
     */
    public void rotation () {
   	 int last =joueurs.size()-1; //donne la position du dernier joueur
   	 System.out.println("Le joueur "+ joueurs.get(last).toString()+" distribue les cartes");
   	 System.out.println("Le joueur "+ joueurs.get(0).toString()+ " lance la partie");
   	 // sens inverse des aiguilles d'une montre
   	 joueurs.add(joueurs.get(0));
   	 joueurs.remove(0);
   	 System.out.println(joueurs.toString());
    }
    
    
    /*Une fois qu'un joueur a tiré une carte et qu'il l'a posé, la méthode
     * va passer au joueur suivant
     */
    
    public void joueurSuivant(){
        // un for dans le main ou pas ??????
    }
    
    /*
     * Exécution d'une partie
    */
    public void partie(ArrayList<Joueur> joueurs){
        boolean finPartie=false;
        while (!finPartie){
            boolean finManche=false;
            while (!finManche) {
                for (int i=0; i<joueurs.size(); i++){
                    joueurs.get(i).choisirCarte(this);
                }
                // regarder qui a gagné
                /* on change la valeur de finManche si :
                 * les joueurs n'ont plus de cartes
                 * suivant les contrats
                 *
                */
            }
        }
        
    }
    
    public void creationJoueurs(){
        boolean creation=true;
        while (!creation){
            int i=1 ;
            Scanner sc= new Scanner (System.in);
            System.out.println("Entrez le nom du joueur");
            String nom = sc.nextLine();
            joueurs.add(new Joueur(nom,String.valueOf(i)));
            i++;
            System.out.println("Y-a-t-il un autre joueur ? (true/false)");
            creation = sc.nextBoolean();
            
        }
    }
    
    public static void main (String[] args) {
        Scanner sc = new Scanner (System.in);
        System.out.println("Voulez-vous jouer avec 32 ou 52 cartes ?"); //demande le nombre de cartes
        int nbcartes = sc.nextInt();
   	Paquet paquet = new Paquet(nbcartes);
   	Jeu jeu = new Jeu(); // initialise le jeu
        jeu.creationJoueurs(); //initialise les jouers
        paquet.cartesAJouer(jeu.joueurs.size()); // adapte le nombre de cartes en fonction du nombre de joueus
        paquet.distribuer(jeu.joueurs); //distribue les cartes entre les joueurs
        jeu.rotation();
        jeu.partie(jeu.joueurs);
         /*
         for (int i=0; i<jeu.joueurs.get(0).getMain().size(); i++){
                System.out.println(i + " : " + jeu.joueurs.get(0).getMain().get(i).toString());
          } 
        */
         
   	 //System.out.println(paquet.paquet.size());
   	 //System.out.println("Main :"+ j1.getMain().toString());
   	 //System.out.println(j1.getMain().size());
   	 ;
    }


}

