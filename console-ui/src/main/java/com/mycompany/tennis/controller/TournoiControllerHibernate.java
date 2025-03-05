package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.service.JoueurServiceHibernate;

import java.util.Scanner;

public class JoueurControllerHibernate {

    private JoueurServiceHibernate joueurServiceHibernate;

    public JoueurControllerHibernate() {
        joueurServiceHibernate = new JoueurServiceHibernate();
    }

    public void afficherJoueurHibernate(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel l'idenfiant du joueur dont vous voulez afficher les informations ?");
        long id = scanner.nextLong();
        Joueur joueur = joueurServiceHibernate.getById(id);
        System.out.println(" Le joueur sélectionné s'appelle {}" + joueur.getPrenom() + " " + joueur.getNom());
    }
}
