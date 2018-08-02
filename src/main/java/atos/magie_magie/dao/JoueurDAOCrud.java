/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.dao;

import atos.magie_magie.entity.Joueur;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Administrateur
 */
public interface JoueurDAOCrud extends CrudRepository<Joueur, Long>{
    
    
    //listerJoueursParPartieIdQuiSontPasIdSorcier
    @Query("            SELECT j" 
                + "     FROM Joueur j "
                + "          JOIN j.partie p "
                + "     WHERE p.id =:partie_id"
                + "     AND j.id !=:id_Sorciere"
    )
    
    public List<Joueur> listerJoueursParPartieIdQuiSontPasIdSorcier(@Param("partie_id") long partieId , @Param("id_Sorciere") long sorciereId);
    
    //listerJoueursParPartieId(idPartie)
    public List<Joueur> findAllByPartieId( long idPartie);
    
    
    //rechercherJoueurQuiALaMainParPartieId
    @Query(        "    SELECT j "
                + "     FROM Joueur j"
                + "          JOIN j.partie p "
                + "     WHERE j.etatJoueur=atos.magie_magie.entity.Joueur.EtatJoueur.A_LA_MAIN"
                + "     AND p.id=:id_partie"
    )
    
    public Joueur rechercherJoueurQuiALaMainParPartieId(@Param("id_partie") long idPartie);
    
    
    //determineSiPlusQueUnJoueurDansPartie(idPartie)
    @Query(       "     SELECT j "
                + "     FROM Joueur j"
                + "          JOIN j.partie p"
                + "     WHERE p.id=:id_partie"
                + "           EXCEPT"
                + "     SELECT j "
                + "     FROM Joueur j"
                + "          JOIN j.partie p"
                + "     WHERE p.id=:id_partie"
                + "     AND j.etatJoueur=atos.magie_magie.entity.Joueur.EtatJoueur.PERDU"
    )
    
    public boolean determineSiPlusQueUnJoueurDansPartie(@Param("id_partie") long idPartie);
    
    public Joueur findOneByPartieIdAndOrdre(long idPartie, long ordre);
    
    
}
