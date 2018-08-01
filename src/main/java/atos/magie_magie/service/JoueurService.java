/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.service;

import atos.magie_magie.dao.CarteDAO;
import atos.magie_magie.dao.JoueurDAO;
import atos.magie_magie.dao.PartieDAO;
import atos.magie_magie.entity.Carte;
import atos.magie_magie.entity.Joueur;
import atos.magie_magie.entity.Partie;
import java.util.List;

/**
 *
 * @author Administrateur
 */
public class JoueurService {
    
    
    private JoueurDAO joueurDao = new JoueurDAO();
    private PartieDAO partiedao = new PartieDAO();
    private CarteDAO carteDAO = new  CarteDAO();
    
   
    public Joueur rechercherJoueurParId(long id){
        return joueurDao.rechercheJoueurParId(id);
    }
    
    public Long reccupererIdJoueurQuiALaMain(long idPartie){
        return joueurDao.reccupIdJoueurMain(idPartie);
    }
    
    public Joueur rejoindrePartie(String pseudo, String avatar, long idPartie){
        
        
        // recherche si le joueur existe déjà
        Joueur joueur = joueurDao.rechercherParPseudo(pseudo);
        if (joueur == null) {
            // joueur n'existe pas encore donc je l'instancie
            
            joueur = new Joueur();
            joueur.setPseudo(pseudo);
            joueur.setNbrPartiesGagnees(0L);
            joueur.setNbrPartiesJouees(0L);
        }
        
        // je prends le joueur et je set son avatar ( je sors du if)
          joueur.setAvatar(avatar);
        
        // la partie n'a pas encore démarrée donc aucun joueur n'a la main
        joueur.setEtatJoueur(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
        long ordre = joueurDao.rechercheOrdreNouveauJoueurPourPartieIs(idPartie);
        joueur.setOrdre(ordre);
//        
//      // reccupérer la partie
        Partie partie = partiedao.rechercherParId(idPartie);

//        
//      // maintenant qu'on a reccup la partie il faut associer le joueur à la partie
        joueur.setPartie(partie);
        
        List<Joueur> listeJoueurs = partie.getJoueurs();
        listeJoueurs.add(joueur);
        
        if(joueur.getId()==null){ //nouveau
            joueurDao.ajouter(joueur);
        }else{
            joueurDao.modifier(joueur);
        }
        
        partiedao.modifier(partie);
        
        return joueur;
    }
    
    public List<Object[]> listerJoueursEtNombreCartes(long idPartie){
      
        return joueurDao.listerJoueursEtNombreCartes(idPartie);
    }



    
    
}
