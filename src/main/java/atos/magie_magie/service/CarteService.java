/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.service;

import atos.magie_magie.dao.CarteDAO;
import atos.magie_magie.dao.JoueurDAO;
import atos.magie_magie.entity.Carte;
import atos.magie_magie.entity.Joueur;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrateur
 */
@Service
public class CarteService {

    // recherche joueur par id
    private CarteDAO cartedao = new CarteDAO();
    private JoueurDAO joueurDao = new JoueurDAO();
    private CarteDAO carteDAO = new CarteDAO();
    
    public void donnerUneCarteDeSonChoix(long idJoueurActuel, long idVictime, long idCarte){
        
        Joueur joueurActuel = joueurDao.rechercheJoueurParId(idJoueurActuel);
        Joueur victime = joueurDao.rechercheJoueurParId(idVictime);
        Carte carteAuChoix = cartedao.reccupererCarteAPartirId(idCarte);
           
        // on supprime la carte au joueurActuel
        joueurActuel.getCartes().remove(carteAuChoix);
        // on ajoute la carte à la victime
        victime.getCartes().add(carteAuChoix);
        //  Relation bi-directionnelle entre le joueuract et la victime
        carteAuChoix.setJoueur(victime);
        
        cartedao.majCarte(carteAuChoix);
        
        
        
    }
    
    public void prendreCarteHasard(long idJoueurActuel, long idVictime) {

        Joueur joueurActuel = joueurDao.rechercheJoueurParId(idJoueurActuel);

        // Reccupérer un indice au hasard du nombre de carte d'un joueur victime
        List<Carte> cartesVictime = cartedao.listerCartesJoueur(idVictime);
        int nombreCarteVictime = cartesVictime.size();

        Random r = new Random();
        // l'indice dans la liste des cartes est choisi
        int indiceCarteVolee = r.nextInt(nombreCarteVictime);

        // carte que le joueur actuel va recevoir de la part de la victime
        Carte carteVolee = cartesVictime.get(indiceCarteVolee);
        cartesVictime.remove(carteVolee);
        
        // le joueurActuel reccupere la carte tiré au hasard ds le tas de son adversaire
        // On change le proprietaire de la carte
        carteVolee.setJoueur(joueurActuel);
        // je prends mes cartes et j'y ajoute la carte volee
        joueurActuel.getCartes().add(carteVolee);
        //maj de la carte
        cartedao.majCarte(carteVolee);
    }

    public List<Carte> listerCartesJoueur(long idJoueur) {

        return cartedao.listerCartesJoueur(idJoueur);
    }

    

}
