package com.mycompany.tennis.webui.controller;

import com.mycompany.tennis.core.dto.JoueurDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.List;

@WebServlet("/listes")
public class ListeJoueursServlet extends HttpServlet {
    private JoueurService joueurService;

    public ListeJoueursServlet() {
        this.joueurService = new JoueurService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<JoueurDto> listeHommes = joueurService.getListeJoueurs('H');
        List<JoueurDto> listeFemmes = joueurService.getListeJoueurs('F');
        //afficheListeJoueurs

        request.setAttribute("listeHommes",listeHommes);
        request.setAttribute("listeFemmes",listeFemmes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("listes.jsp");
        dispatcher.forward(request,response);

        //http://localhost:8080/webui/listes
    }
}
