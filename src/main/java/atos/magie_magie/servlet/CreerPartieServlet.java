/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.servlet;

import atos.magie_magie.entity.Partie;
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
@WebServlet(name = "creerPartieServlet", urlPatterns = {"/creer-partie"})
public class CreerPartieServlet extends AutowireServlet {
    
    private PartieService service = new PartieService();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // On reccup le nom de la partie dans une variable 
        String nomPartie = req.getParameter("nom");
        
       // création d'une nouvelle partie
       // si on doit seulement le reccupérer on le prend ds une variable, on ne fait pas de new.
        Partie partie = new Partie();
        
        // On reccup les parametres name qui sont dans le formulaire
        partie.setNom(nomPartie);
        
        // On cree la partie avec la fonction ci-dessous
        service.creerNouvellePartie(nomPartie);
        
        // redirige vers la listePartie une fois que le formulaire est envoyé
        resp.sendRedirect("lister-partie");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("creerPartie.jsp").forward(req, resp);
    }

    
    
    
}
