package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.EpreuveLightDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.service.EpreuveServiceHibernate;


import java.util.Scanner;

public class EpreuveControllerHibernate {

    private EpreuveServiceHibernate epreuveServiceHibernate;
    private Scanner scanner;

    public EpreuveControllerHibernate() {
        epreuveServiceHibernate = new EpreuveServiceHibernate();
        scanner = new Scanner(System.in);
    }

    public void afficherListEpreuve(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est le code du tournoi ?");
        String codeTournoi = scanner.nextLine();

        for (EpreuveFullDto epreuveFullDto : epreuveServiceHibernate.getListEpreuve(codeTournoi)) {
            System.out.println(epreuveFullDto.getId() + " " + epreuveFullDto.getAnnee()  + " " + epreuveFullDto.getTypeEpreuve()
                    + " " + epreuveFullDto.getTournoi().getNom());
        }

    }

    public void afficherDetailleeEpreuveHibernate() {
        System.out.println("Quel est l'identifiant de l'Epreuve dont vous voulez afficher les informations ?");
        long id = scanner.nextLong();
        scanner.nextLine();
        EpreuveFullDto epreuveFullDto = epreuveServiceHibernate.getEpreuveDetaillee(id);
        System.out.println("Le nom du tournoi est " + epreuveFullDto.getTournoi().getNom());

        for (JoueurDto joueurDto : epreuveFullDto.getParticitants()) {
            System.out.println(joueurDto.getPrenom() + " " + joueurDto.getNom());
            // ne pas oublier de taper id 11 dans le console pour afficher les joueurs
        }
//        if (epreuveFullDto != null) {
//            for (JoueurDto joueurDto : epreuveFullDto.getParticitants()) {
//                System.out.println(joueurDto.getPrenom() + " " + joueurDto.getNom());
//            }
//            //System.out.println(String.format("Le nom du tournoi est " +  epreuveFullDto.getTournoi().getNom()));
//        } else {
//            System.out.println("Tournoi non trouvé.");
//        }
    }

    public void afficherEpreuveHibernateRolangaros() {
        System.out.println("Quel est l'identifiant de l'Epreuve dont vous voulez afficher les informations ?");
        long id = scanner.nextLong();
        scanner.nextLine();  // Consommer le newline après nextLong
        EpreuveLightDto epreuveLightDto = epreuveServiceHibernate.getByIdSansTournoi(id);
        if (epreuveLightDto != null) {
            //System.out.println(String.format("L'Epreuve sélectionnée se déroule en " + epreuve.getAnnee() + " et il sagit du tournoi : %s", epreuve.getTournoi().getNom()));
        } else {
            System.out.println("Tournoi non trouvé.");
        }
    }

    public void creerTournoiHibernate() {
        System.out.println("Quel est le nom du tournoi ?");
        String nom = scanner.nextLine();
        System.out.println("Quel est le code du tournoi ?");
        String code = scanner.nextLine();

        Tournoi tournoi = new Tournoi();
        tournoi.setNom(nom);
        tournoi.setCode(code);
        //tournoiServiceHibernate.create(tournoi);
        System.out.println("Le tournoi a été créé avec succès.");
    }

    public void renommeTournoiHibernate() {
        System.out.println("Quel est l'identifiant du tournoi que vous voulez renommer ?");
        long id = scanner.nextLong();
        scanner.nextLine();  // Consommer le newline après nextLong
        System.out.println("Quel est le nouveau nom ?");
        String nom = scanner.nextLine();

//        Tournoi tournoi = tournoiServiceHibernate.getById(id);
//        if (tournoi != null) {
//            tournoi.setNom(nom);  // Modifier le nom du tournoi
//            tournoiServiceHibernate.update(tournoi);  // Sauvegarder la mise à jour
//            System.out.println("Le tournoi a été renommé avec succès.");
//        } else {
//            System.out.println("Tournoi non trouvé.");
//        }
    }

    public void supprimeTournoiHibernate() {
        System.out.println("Quel est l'identifiant du tournoi à supprimer ?");
        long id = scanner.nextLong();
        scanner.nextLine();  // Consommer le newline après nextLong

        //tournoiServiceHibernate.delete(id);
        System.out.println("Le tournoi a été supprimé avec succès.");
    }

    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
