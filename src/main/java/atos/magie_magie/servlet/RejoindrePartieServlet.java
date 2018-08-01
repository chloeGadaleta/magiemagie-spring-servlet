/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.servlet;

import atos.magie_magie.entity.Joueur;
import atos.magie_magie.service.JoueurService;
import atos.magie_magie.spring.AutowireServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "RejoindrePartieServlet", urlPatterns = {"/rejoindre-partie"})
public class RejoindrePartieServlet extends AutowireServlet {

     private JoueurService service = new JoueurService();
  
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        
        long idPartie = (long) req.getSession().getAttribute("idPartie");
        List<Object[]> jouObjects = service.listerJoueursEtNombreCartes(idPartie);
        
        req.setAttribute("listeIdPseudo", jouObjects);
        req.getRequestDispatcher("rejoindrePartie.jsp").forward(req, resp);
    }
  
}
