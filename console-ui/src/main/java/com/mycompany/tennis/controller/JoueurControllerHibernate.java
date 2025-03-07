package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.service.JoueurServiceHibernate;

import java.util.Scanner;

public class JoueurControllerHibernate {

    private JoueurServiceHibernate joueurServiceHibernate;
    private Scanner scanner;

    public JoueurControllerHibernate() {
        joueurServiceHibernate = new JoueurServiceHibernate();
        scanner = new Scanner(System.in);
    }

    public void afficherListJoueurs(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Voulez vous  une liste hommes (H), ou femmes (F) ? ");
        char sexe = scanner.nextLine().charAt(0);

        for (JoueurDto joueurDto : joueurServiceHibernate.getListJoueurs(sexe)) {
            System.out.println(joueurDto.getPrenom() + " " + joueurDto.getNom());
        }

    }

    public void afficherJoueurHibernate() {

        System.out.println("Quel est l'identifiant du joueur dont vous voulez afficher les informations ?");
        long id = scanner.nextLong();
        scanner.nextLine();
        Joueur joueur = joueurServiceHibernate.getById(id);
        System.out.println(String.format("Le joueur sélectionné s'appelle %s %s", joueur.getPrenom(), joueur.getNom()));
    }

    public void creerJoueurHibernate() {
        System.out.println("Quel est le nom du joueur ?");
        String nom = scanner.nextLine();
        System.out.println("Quel est le prénom du joueur ?");
        String prenom = scanner.nextLine();
        System.out.println("Quel est le sexe du joueur ? (M/F)");
        char sexe = scanner.nextLine().charAt(0);

        Joueur joueur = new Joueur();
        joueur.setNom(nom);
        joueur.setPrenom(prenom);
        joueur.setSexe(sexe);
        joueurServiceHibernate.create(joueur);
        System.out.println("Le joueur a été créé avec succès, son identifiant est " +joueur.getId());
    }

    public void renommeJoueurHibernate() {
        System.out.println("Quel est l'identifiant du joueur que vous voulez renommer: ?");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Quel est le nouveau nom ?");
        String nom = scanner.nextLine();
        joueurServiceHibernate.renomme(id,nom);
    }

    public void updateJoueurHibernate() {

        System.out.println("Quel est l'identifiant du joueur que vous voulez renommer ?");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Quel est le nouveau nom ?");
        String nom = scanner.nextLine();

        Joueur joueur = joueurServiceHibernate.getById(id);
        if (joueur != null) {
            joueur.setNom(nom);
            joueurServiceHibernate.update(joueur);
            System.out.println("Le joueur a été renommé avec succès.");
        } else {
            System.out.println("Joueur non trouvé.");
        }
    }

    public void supprimeJoueurHibernate() {

        System.out.println("Quel est l'identifiant du joueur à supprimer ?");
        long id = scanner.nextLong();
        scanner.nextLine();
        joueurServiceHibernate.delete(id);
        System.out.println("Le joueur a été supprimé avec succès.");
    }

    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
