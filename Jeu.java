package barbu;

import java.util.*;

public class Jeu {
    ArrayList<Joueur> joueurs;
    ArrayList<Carte> plateau;
    
    
    //CONSTRUCTEUR
    public Jeu() {
   	 super();
   	 this.joueurs = new ArrayList<Joueur>();
         this.plateau = new ArrayList<Carte>();
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
        
    }
    
    public static void main (String[] args) {
   	 Paquet paquet = new Paquet(52);
   	 Jeu jeu = new Jeu();
   	 //demander le nombre de joueurs
   	 //demander le nombre de cartes
   	 Joueur j1 = new Joueur("1","");
   	 Joueur j2 = new Joueur("2","");
         Joueur j3 = new Joueur("3","");
         Joueur j4 = new Joueur("4","");
   	 jeu.joueurs.add(j1);
   	 jeu.joueurs.add(j2);
         jeu.joueurs.add(j3);
         jeu.joueurs.add(j4);
   	 //System.out.println("Paquet départ"+paquet.paquet.size());
   	 paquet.distribuer(jeu.joueurs);
         jeu.joueurs.get(0).choisirCarte(jeu);
         for (int i=0; i<jeu.joueurs.get(0).getMain().size(); i++){
                System.out.println(i + " : " + jeu.joueurs.get(0).getMain().get(i).toString());
          } 
   	 //System.out.println(paquet.paquet.size());
   	 //System.out.println("Main :"+ j1.getMain().toString());
   	 //System.out.println(j1.getMain().size());
   	 jeu.rotation();
   	 jeu.rotation();
   	 ;
    }


}



