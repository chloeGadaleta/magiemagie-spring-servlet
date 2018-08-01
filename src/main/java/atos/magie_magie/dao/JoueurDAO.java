/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.dao;

import atos.magie_magie.entity.Carte;
import atos.magie_magie.entity.Joueur;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Administrateur
 */
public class JoueurDAO {
    
    /**
     * Renvoie le Joueur dont le pseudo existe en bdd, ou renvoie null si pas trouvé
     * @param pseudo
     * @return 
     */
    
    public Joueur rechercheJoueurParId(long id){
        
        EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();
        
        return em.find(Joueur.class, id);
    }
    
    public Long reccupIdJoueurMain(long idPartie) {
        
        EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();
        Query query = em.createQuery("SELECT j.id "
                + "                   FROM Joueur j "
                + "                        JOIN j.partie p"
                + "                   WHERE j.etatJoueur=:etat_alamain"
                + "                   AND p.id=:partie_id"
                );
        
        query.setParameter("etat_alamain", Joueur.EtatJoueur.A_LA_MAIN);
        query.setParameter("partie_id", idPartie);
        return (Long) query.getSingleResult();
    
    }
    
    
    public List<Object[]> listerJoueursEtNombreCartes(long idPartie){
        
        EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();
        
        Query query = em.createQuery("SELECT j.id, j.pseudo, j.avatar, COUNT(c) "
                + "                   FROM Joueur j "
                + "                        LEFT JOIN j.cartes c "
                + "                        LEFT JOIN j.partie p "
                + "                   WHERE p.id=:id_partie "
                + "                   GROUP BY j"
                );
        
        query.setParameter("id_partie", idPartie);
        return (List<Object[]>) query.getResultList();
    }
    
    // remplacé long l par long ordre
    public Joueur rechercheJoueurParPartieIdEtOrdre(long idPartie, long ordre) {
        
        EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();
        
        Query query = em.createQuery("SELECT j "
                + "                   FROM Joueur j"
                + "                        JOIN j.partie p"
                + "                   WHERE p.id=:id_partie"
                + "                   AND j.ordre=:ordre_joueur"
                );
        
        query.setParameter("ordre_joueur", ordre);
        query.setParameter("id_partie", idPartie);
        
        return (Joueur) query.getSingleResult();
    }
    
    public boolean determineSiPlusQueUnJoueurDansPartie( long partieId){
        
        EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();

        Query query = em.createQuery("SELECT j "
                + "                   FROM Joueur j"
                + "                        JOIN j.partie p"
                + "                   WHERE p.id=:id_partie"
                + "                   EXCEPT"
                + "                   SELECT j "
                + "                   FROM Joueur j"
                + "                        JOIN j.partie p"
                + "                   WHERE p.id=:id_partie"
                + "                   AND j.etatJoueur=:etat_perdu"
                );
        
        query.setParameter("etat_perdu", Joueur.EtatJoueur.PERDU);
        query.setParameter("id_partie", partieId);
        
        List res = query.getResultList();
        
        
        // ou return res.size()==1;
        if (res.size()== 1)
            return true;
        else
            return false;
                   
        
        
    }
    
    public Joueur rechercherJoueurQuiALaMainParPartieId(long idPartie){
        
        EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();
        
        Query query = em.createQuery("SELECT j "
                + "                   FROM Joueur j"
                + "                        JOIN j.partie p "
                + "                   WHERE j.etatJoueur=:etat_alamain"
                + "                   AND p.id=:id_partie"
                );
        query.setParameter("etat_alamain", Joueur.EtatJoueur.A_LA_MAIN);
        query.setParameter("id_partie", idPartie);
        
        return (Joueur) query.getSingleResult();
    }
    
    
    public void majJoueur(Joueur joueur){
        
        EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();
        
        em.getTransaction().begin();
        em.merge(joueur);
        em.getTransaction().commit();
    
    }
    
    
    // recherche si joueur existe par pseudo
    public Joueur rechercherParPseudo(String pseudo){
        
        EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();
        
        // recherche dans bdd le joueur par 
//        Query query = em.createQuery("SELECT j"
//                + "FROM Joueur j"
//                + "WHERE j.pseudo =:lepseudo"
//                );
              Query query = em.createQuery("select j from Joueur j where j.pseudo =:lePseudo");
              query.setParameter("lePseudo", pseudo);
        
        // si ma liste est vide alors il renvoie le return
        List<Joueur> joueursTrouves = query.getResultList();
        
        if (joueursTrouves.isEmpty())
            return null;
        // correspond au else car le return qui la fonction, dc si j'arrive ici c'est que la fonction était fausse
        return  (Joueur) query.getSingleResult();
    
    }
    
    public long rechercheOrdreNouveauJoueurPourPartieIs(long partieId){
        
        EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();
        
//        Query query = em.createQuery("SELECT MAX(j.ordre)+1"
//                + "FROM Joueur j"
//                + "     JOIN j.partie p"
//                + "WHERE j.id =:idPartie"
//                );
        Query query = em.createQuery("select Max(j.ordre) from Joueur j join j.partie p where p.id =:idPartie ");
        
        query.setParameter("idPartie", partieId);
        Object res = query.getSingleResult();
        if (res == null) {
            return 0;
        }
        return (long) res+1;
    } 


    public void ajouter(Joueur joueur) {
        
        EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();
          
           em.getTransaction().begin();
           
           em.persist(joueur);
           
           em.getTransaction().commit();
                
    }
    
    public void modifier(Joueur joueur) {
        
        EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();
          
            em.getTransaction().begin();
           
            em.merge(joueur);
           
            em.getTransaction().commit();
        
    }

    public List<Joueur> listerJoueursParPartieId(long idPartie) {
        
        EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();
        
        Query query = em.createQuery("SELECT j "
                + "                   FROM Joueur j "
                + "                        JOIN j.partie p "
                + "                   WHERE p.id=:partie_id");
        
        query.setParameter("partie_id", idPartie);
        
        
        // correspond au else car le return qui la fonction, dc si j'arrive ici c'est que la fonction était fausse
        return   query.getResultList();
    }

    public List<Joueur> listerJoueursParPartieIdQuiSontPasIdSorciere(long idSorciere, long idPartie) {
        
        EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();
        
        Query query = em.createQuery("SELECT j"
                + "                   FROM Joueur j "
                + "                   JOIN j.partie p "
                + "                   WHERE p.id =:partie_id"
                + "                   AND j.id !=:id_Sorciere");
        
        query.setParameter("partie_id", idPartie);
        query.setParameter("id_Sorciere", idSorciere);
        
        return query.getResultList();
    }

}
