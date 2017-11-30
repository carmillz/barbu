/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbu;

/**
 *
 * @author Salmé
 */

public class ContratPli implements Contrat {

    public boolean fin(Jeu jeu) {
        if (jeu.nbcartes==0){
            return true;
        }
        return false;
    }
}
