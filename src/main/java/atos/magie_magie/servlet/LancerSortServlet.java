/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.servlet;

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
@WebServlet(name = "LancerSortServlet", urlPatterns = {"/lancer-sort"})
public class LancerSortServlet extends AutowireServlet{

    private PartieService service = new PartieService();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        //On recup la session id partie
        long idPartie = (long) req.getSession().getAttribute("idPartie");
        long idJoueur = (long) req.getSession().getAttribute("idJoueur");
        
        //déclaration des parametres
        long idCarte1 = Long.parseLong(req.getParameter("idCarte1"));
        long idCarte2 = Long.parseLong(req.getParameter("idCarte2"));
        long idVictime = Long.parseLong(req.getParameter("idVictime"));
        long idCarte = Long.parseLong(req.getParameter("idCarte"));
        
        // On reccup la fonction lancer sort dans partie service 
        service.lancerSort(idPartie, idJoueur, idCarte1, idCarte2,idCarte, idVictime);
        
    }
}
