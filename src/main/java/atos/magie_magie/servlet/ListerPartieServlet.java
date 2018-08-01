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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "ListerPartieServlet", urlPatterns = {"/lister-partie"})
public class ListerPartieServlet extends AutowireServlet {
    
    @Autowired
    private PartieService service;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        List<Partie> parties = service.listerPartiesNonDemarrees();

        req.setAttribute("listePartie", parties);
        
        req.getRequestDispatcher("listeParties.jsp").forward(req, resp);
    }
}
