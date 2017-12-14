package barbu;

public class ContratSalade implements Contrat{

	public ContratSalade(Jeu jeu) {
		// TODO Auto-generated constructor stub
	}


	@Override
	public int comptePoints(Joueur joueur) {
            int cpt =0;
            while (!joueur.getPlis().isEmpty()) { //Tant que le joueur a encore des plis on compte ses points
                Carte c = joueur.getPlis().iterator().next();
                if (){
                    
                }
            }
            joueur.getPlis().remove(c);
             
            return 0;
	}

	@Override
	public boolean fin(Jeu jeu, int nombre) {
            if (jeu.nbcartesencours == 0) { // Si le nombre de carte en jeu est égal à 0 (donc plus de carte à jouer)
                return true;         // Alors la partie est finie 
            }
                      
            return false;            // Sinon la partie continue 
		

}
