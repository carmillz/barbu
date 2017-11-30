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
public class ContratDame implements Contrat{
    
    public boolean fin(Jeu jeu){
        if (jeu.nbcartes==7){
            return true;
        }
        return false;
    }
}
