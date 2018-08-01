/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.dao;

import atos.magie_magie.entity.Joueur;
import atos.magie_magie.entity.Partie;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Administrateur
 */
public interface PartieDAOCrud extends CrudRepository<Partie, Long>{
    
    @Query(       " SELECT p "
                + " FROM Partie p "
                + " EXCEPT "
                + " SELECT p "
                + " FROM Partie p "
                + "      JOIN p.joueurs j "
                + " WHERE j.etatJoueur= atos.magie_magie.entity.Joueur.EtatJoueur.GAGNE"
                + " EXCEPT "
                + " SELECT p "
                + " FROM Partie p "
                + "      JOIN p.joueurs j "
                + " WHERE j.etatJoueur = atos.magie_magie.entity.Joueur.EtatJoueur.A_LA_MAIN ")
    
    public List<Partie> listerPartiesNonDemarrees();
    
    

}
