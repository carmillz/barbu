import java.util.ArrayList;
import java.util.Scanner;

public class ContratReussite implements Contrat {
    int debut;
    public final String vide="               ";
    public ContratReussite() {
        this.debut=7;
    }

    public int choixDebut(){
        System.out.println("Entrez la carte par laquelle vous voulez commencer !");
        Scanner sc = new Scanner(System.in);
        int debut = sc.nextInt();
        while (debut > 14 && debut < 2){
            System.out.println("Ce choix n'est pas valide, recommencez !");
            debut = sc.nextInt();
        }
        this.debut = debut;
        return debut;
    }

    @Override
    public void comptePoints(ArrayList<Joueur> joueurs) {
        int i =0;
        boolean premier=false;
        boolean second=false;
        while (i<joueurs.size()&&!premier){
            if (joueurs.get(i).getMain().isEmpty()) {
                joueurs.get(i).getPoints().add(-300);
                premier =true;
            }
            i++;
        }
        while (i<joueurs.size()){
            if (joueurs.get(i).getMain().isEmpty()) {
                joueurs.get(i).getPoints().add(-100);
                second=true;
            }
            i++;
        }
        //il faudrait un second gagnant
    }






    @Override
    public boolean fin(int nombre) {
        //méthode non utilisée
        return false;
    }

}
