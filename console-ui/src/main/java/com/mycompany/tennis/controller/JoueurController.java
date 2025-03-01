package com.mycompany.tennis.controller;

import com.company.tennis.core.entity.Joueur;
import com.company.tennis.core.service.JoueurService;

import java.util.Scanner;

public class JoueurController {

    private JoueurService joueurService;

    public JoueurController() {
        joueurService = new JoueurService();
    }

    public void afficherJoueur(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel l'idenfiant du joueur dont vous voulez afficher les informations ?");
        long id = scanner.nextLong();
        Joueur joueur = joueurService.getById(id);
        System.out.println(" Le joueur sélectionné s'appelle {}" + joueur.getPrenom() + " " + joueur.getNom());
    }
}
