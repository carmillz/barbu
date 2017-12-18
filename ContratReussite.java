import java.util.ArrayList;

public class ContratReussite implements Contrat {
    public static String vide="               ";
    public ContratReussite() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void comptePoints(ArrayList<Joueur> joueurs) {
        for (int i=0; i<joueurs.size(); i++){
            if (joueurs.get(i).getMain().isEmpty()) {
                joueurs.get(i).getPoints().add(-100);
            }
        }

    }


   public boolean vide(Jeu jeu){
        boolean vide = true;
        // on parcourt les lignes
        for (int i = 0; i<jeu.joueurs.size(); i++){
            // on parcourt les colonnes
            for (int j=0; j<jeu.nbCartes/jeu.joueurs.size(); j++){
                if (!((ArrayList<ArrayList<String>>) jeu.plateau).get(i).get(j).equals(vide)){
                    return false;
                }
            }
        }
        return vide;
    }



    @Override
    public boolean fin(int nombre) {
        //méthode non utilisée
        return false;
    }

}
