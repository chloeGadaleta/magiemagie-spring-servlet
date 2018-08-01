/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.servlet;

import atos.magie_magie.entity.Joueur;
import atos.magie_magie.entity.Partie;
import atos.magie_magie.service.JoueurService;
import atos.magie_magie.service.PartieService;
import atos.magie_magie.spring.AutowireServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "creationCompteServlet", urlPatterns = {"/creation-compte"})
public class creationCompteServlet extends AutowireServlet {

    private JoueurService joueurService = new JoueurService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
         // On reccup le pseudo du joueur ds une variable
        String pseudoJoueur = req.getParameter("pseudo");
        
        // On reccup l'avatar du joueur ds une variable
        String avatarJoueur = req.getParameter("radio");
        long idPartie = (long) req.getSession().getAttribute("idPartie");
        
        //Création d'un nouveau joueur
        Joueur joueur = new Joueur();
        
        // On reccup les parametres name qui sont dans le formulaire
        joueur.setPseudo(pseudoJoueur);
        joueur.setAvatar(avatarJoueur);
        
        // On crée le joueur avec la fonction cree ds service
       Joueur joueur1 = joueurService.rejoindrePartie(pseudoJoueur, avatarJoueur, idPartie);
       req.getSession().setAttribute("idJoueur", joueur1.getId());
       
       // redirige vers la rejoindre Partie une fois que le formulaire est envoyé
       resp.sendRedirect("rejoindre-partie");
    }
       
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // Récup id de la partie et la place en session
        // converti du string vers long pr pouvoir le reutiliser ds les services
        // qui utilisent long pour id
        long id = Long.parseLong(req.getParameter("idPartie"));
        
        req.getSession().setAttribute("idPartie", id);
        
        req.getRequestDispatcher("creationCompte.jsp").forward(req, resp);
    }

}
