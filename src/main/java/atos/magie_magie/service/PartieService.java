/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.service;

import atos.magie_magie.dao.CarteDAO;
import atos.magie_magie.dao.CarteDAOCrud;
import atos.magie_magie.dao.JoueurDAO;
import atos.magie_magie.dao.JoueurDAOCrud;
import atos.magie_magie.dao.PartieDAO;
import atos.magie_magie.dao.PartieDAOCrud;
import atos.magie_magie.entity.Carte;
import atos.magie_magie.entity.Carte.TypeIngredient;
import atos.magie_magie.entity.Joueur;
import atos.magie_magie.entity.Partie;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrateur
 */
@Service
public class PartieService {

    @Autowired
    private PartieDAOCrud dAOCrud;
    
    @Autowired
    private JoueurDAOCrud joueurDAOCrud;
    
    @Autowired
    private CarteDAOCrud carteDAOCrud;
    
//    @Autowired
//    private CarteService carteService;
    private PartieDAO partiedao = new PartieDAO();
    private JoueurDAO joueurdao = new JoueurDAO();
    private CarteDAO carteDao = new CarteDAO();
    private CarteService carteService = new CarteService();

    /**
     * Liste des parties dont aucun joueur n'est à l'état A_LA_MAIN ou GAGNE
     *
     * @return
     */
    
    public void lancerSortSommeilProfond(long idVictime){
        
        Joueur joueurVictime = joueurdao.rechercheJoueurParId(idVictime);
        joueurVictime.setEtatJoueur(Joueur.EtatJoueur.SOMMEIL_PROFOND);
        joueurdao.majJoueur(joueurVictime);
    }
    
    public List<Joueur> lancerSortDivination (long idJoueurActuel, long idPartie) {
        
     return joueurdao.listerJoueursParPartieIdQuiSontPasIdSorciere(idJoueurActuel, idPartie);
    }
    
    public void passerSonTour( long idPartie, long idJoueur){
        
        piocher(idJoueur);
        passeJoueurSuivant(idPartie);
        
    }
    
    public Partie rechercherPartieParId( long idPartie){
        
       return partiedao.rechercherParId(idPartie);
       
    }
    
    public void lancerSortHypnose(long idJoueurActuel, long idVictime, long idCarte){
        
        //choisir un joueur 
        Joueur joueurVictime = joueurdao.rechercheJoueurParId(idVictime);
        Joueur joueurActuel = joueurdao.rechercheJoueurParId(idJoueurActuel);
        
        
        //Le joueur actuel donne une carte de son choix à la victime
        carteService.donnerUneCarteDeSonChoix(idJoueurActuel, idVictime, idCarte);
        
        // le joueur actuel prend 3 cartes au hasard à la victime
        for (int i = 0; i < 3; i++) {
        carteService.prendreCarteHasard(idJoueurActuel, idVictime);
        }
    }
    
    public void lancerSortphiltreDamour(long idPartie, long idJoueurActuel, long idVictime) {
        
        //choisir un joueur 
        Joueur joueurVictime = joueurdao.rechercheJoueurParId(idVictime);
        
        //Le joueur actuel prend 50% des cartes au hasard à la victime
        int moitieCarte ;
        
        if ((joueurVictime.getCartes().size()%2)==1) {
            moitieCarte = joueurVictime.getCartes().size()/2 +1;
        }else moitieCarte = joueurVictime.getCartes().size()/2;
        
        for (int i = 0; i < moitieCarte ; i++) {
            carteService.prendreCarteHasard(idJoueurActuel, idVictime);
            
        }
        // Victime perdu si cartes<1 perdu sinon continue
        
        int nrbCarteVictime = joueurVictime.getCartes().size();
        
        if (nrbCarteVictime <= 1) 
            
           joueurVictime.setEtatJoueur(Joueur.EtatJoueur.PERDU);
           
        joueurdao.majJoueur(joueurVictime);
        
    }
    
    public void lancerSortInvisibilite(long idPartie, long idJoueurActuel) {
        
        List<Joueur> joueursPartie = joueurdao.listerJoueursParPartieId(idPartie);
        
        for (Joueur victime : joueursPartie) {
            carteService.prendreCarteHasard(idJoueurActuel, victime.getId());
        }
    }

    public void lancerSort(long idPartie, long idJoueurActuel, long idCarte1, long idCarte2, long idCarteAEchanger, Long idVictime) {

        // reccupérer les cartes
        Carte c1 = carteDAOCrud.findOne(idCarte1);
        Carte c2 = carteDAOCrud.findOne(idCarte2);
        Carte c = carteDAOCrud.findOne(idCarteAEchanger);
        Joueur joueurActuel = joueurDAOCrud.findOne(idJoueurActuel);

        // Lancer le sort en fonction des ingrédients des deux cartes
        if ((c1.getTypeIngredient() == TypeIngredient.LICORNE && c2.getTypeIngredient() == TypeIngredient.CRAPAUD)
                || (c1.getTypeIngredient() == TypeIngredient.CRAPAUD && c2.getTypeIngredient() == TypeIngredient.LICORNE)) {
            System.out.println("Le sort est invisibilité : le joueur prend 1 carte(au hasard) chez tous ses adversaires");
            lancerSortInvisibilite(idPartie, idJoueurActuel);
            
        } else if ((c1.getTypeIngredient() == TypeIngredient.LICORNE && c2.getTypeIngredient() == TypeIngredient.MANDRAGORE)
                || (c1.getTypeIngredient() == TypeIngredient.MANDRAGORE && c2.getTypeIngredient() == TypeIngredient.LICORNE)) {
            System.out.println("Le sort est philtre d'amour : le joueur de votre choix vous donne la moitié de ses cartes(au hasard)");
            lancerSortphiltreDamour(idPartie, idVictime, idJoueurActuel);
            
        } else if ((c1.getTypeIngredient() == TypeIngredient.CRAPAUD && c2.getTypeIngredient() == TypeIngredient.LAPIS_LAZULI)
                || (c1.getTypeIngredient() == TypeIngredient.LAPIS_LAZULI && c2.getTypeIngredient() == TypeIngredient.CRAPAUD)) {
            System.out.println("Le sort est Hipnose : le joueur échange une carte de son choix contre trois cartes(au hasard) de la victime qu’il choisit");
            lancerSortHypnose(idJoueurActuel, idVictime, idCarteAEchanger);
            
        } else if ((c1.getTypeIngredient() == TypeIngredient.LAPIS_LAZULI && c2.getTypeIngredient() == TypeIngredient.CHAUVE_SOURIS)
                || (c1.getTypeIngredient() == TypeIngredient.CHAUVE_SOURIS && c2.getTypeIngredient() == TypeIngredient.LAPIS_LAZULI)) {
            System.out.println("Le sort est divination : le joueur peut voir les cartes de tous les autres joueurs");
            lancerSortDivination(idJoueurActuel, idPartie);
            
        } else if ((c1.getTypeIngredient() == TypeIngredient.MANDRAGORE && c2.getTypeIngredient() == TypeIngredient.CHAUVE_SOURIS)
                || (c1.getTypeIngredient() == TypeIngredient.CHAUVE_SOURIS && c2.getTypeIngredient() == TypeIngredient.MANDRAGORE)) {
            System.out.println("Le sort est sommeil-profond : le joueur choisit une victime qui ne pourra pas lancer de sorts pendant 2 tours");
            lancerSortSommeilProfond(idVictime);
        }

        //Supprimer les cartes qui viennent d'être lancées
        carteDAOCrud.delete(c1.getId());
        carteDAOCrud.delete(c2.getId());

        //Passer au joueur suivant
        passeJoueurSuivant(idPartie);

    }
    
    @Transactional
    private void piocher(long idJoueur) {

        // Le joueur pioche une carte
        Joueur joueur = joueurDAOCrud.findOne(idJoueur);
        
        Carte carte = nouvelleCarte();

        // on lie la carte au joueur
        carte.setJoueur(joueur);
        // on ajoute cette carte à sa liste de carte
        joueur.getCartes().add(nouvelleCarte());

        //enregistrer la carte
        carteDAOCrud.save(carte);
        //modifier le joueur
        joueurDAOCrud.save(joueur);

    }

    private void passeJoueurSuivant(long idPartie) {

        // Réccuper id du joueur que à la main
        Joueur joueurQuiALaMain = joueurdao.rechercherJoueurQuiALaMainParPartieId(idPartie);

        //Determine si tous les autres joueurs ont perdus
        //et passe le joueur à l'état gagné si c'est le cas puis quitte la fonction
        // pas besoin d'écrire true et else.. cela se fait automatiquement
        if (joueurdao.determineSiPlusQueUnJoueurDansPartie(idPartie)) {

            joueurQuiALaMain.setEtatJoueur(Joueur.EtatJoueur.GAGNE);
            joueurdao.modifier(joueurQuiALaMain);
            return;
            // return pour interompre la fonction
        }

        // (si j'arrive ici ) La partie n'est pas terminée donc joueur à gagné
        // sinon on continue 
        //Recupère l'ordre max des joueurs de la partie
        long ordreMax = partiedao.rechercheOrdreMaxJoueurPourPartieId(idPartie);

        //joueurEvalue = joueurQuiALaMain
        Joueur joueurEvalue = joueurQuiALaMain;

        while (true) {// c'est ma boucle qui permer de déterminer le joueur qui 'attrape' la main

            //Si joueurEvalue est le dernier joueur alors on evalue le premier
            if (joueurEvalue.getOrdre() >= ordreMax) {
                joueurEvalue = joueurdao.rechercheJoueurParPartieIdEtOrdre(idPartie, 0L);
            } else {
                joueurEvalue = joueurdao.rechercheJoueurParPartieIdEtOrdre(idPartie, joueurEvalue.getOrdre() + 1);
            }

            //Si tous les joueurs non éliminés étaient en sommeil profond ( et qu'on l'a juste reveillé)
            if (joueurEvalue.getId() == joueurQuiALaMain.getId()) {
                return;
            }
            // si joueur évalué en sommeil profond alors son état passe à pas la main

            if (joueurEvalue.getEtatJoueur() == Joueur.EtatJoueur.SOMMEIL_PROFOND) {
                joueurEvalue.setEtatJoueur(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
                joueurdao.modifier(joueurEvalue);
            } else {
                // N'était pas en sommeil profond

                // SI joueurEvalue à pas la main ? Alors c'est lui qui prend la main 
                if (joueurEvalue.getEtatJoueur() == Joueur.EtatJoueur.N_A_PAS_LA_MAIN) {
                    joueurQuiALaMain.setEtatJoueur(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
                }
                joueurdao.modifier(joueurQuiALaMain);

                joueurEvalue.setEtatJoueur(Joueur.EtatJoueur.A_LA_MAIN);
                joueurdao.modifier(joueurEvalue);

                return;
            }
        }

    }

    public List<Partie> listerPartiesNonDemarrees() {

        return dAOCrud.listerPartiesNonDemarrees();
    }
    
    @Transactional
    public Partie creerNouvellePartie(String nom) {

        // on crée la classe
        Partie p = new Partie();
        p.setNom(nom);
        //ajouter partie
        dAOCrud.save(p);

        return p;

    }

    @Transactional
    public void demarrerPartie(long idPartie) {

        // recherche par id  
        Partie p = dAOCrud.findOne(idPartie);

        // Erreur si on a pas au moins deux joueurs dans la partie       
        if (p.getJoueurs().size() < 2) {
            throw new RuntimeException("il n'y a pas assez de joueur");
        }

        // On passe le joueur d'ordre 0 à l'état A_LA_MAIN
        // Faire boucle et ajouter en base
        for (Joueur joueur : p.getJoueurs()) {
            if (joueur.getOrdre() == 0) {
                joueur.setEtatJoueur(Joueur.EtatJoueur.A_LA_MAIN);
                // modifier joueur
                joueurDAOCrud.save(joueur);
            }
        }

        // Distribue 7 cartes au hasard à chaque joueur de la partie
        for (Joueur joueur : p.getJoueurs()) {
            for (int i = 0; i < 7; i++) {
                Carte carte = nouvelleCarte();
                joueur.getCartes().add(nouvelleCarte());
                carte.setJoueur(joueur);
                //mise à jour carte
                carteDAOCrud.save(carte);
            }
        }
    }

    // tirage au hasard des cartes de 1 à 5 ingrédients
    private Carte nouvelleCarte() {

        TypeIngredient[] tabTypeIngredients = TypeIngredient.values();

        Random r = new Random();
        int n = r.nextInt(tabTypeIngredients.length);

        Carte carte = new Carte();
        carte.setTypeIngredient(tabTypeIngredients[n]);
        return carte;
    }
}
