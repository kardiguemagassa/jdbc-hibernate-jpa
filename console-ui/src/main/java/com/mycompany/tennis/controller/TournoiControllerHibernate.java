package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.service.TournoiServiceHibernate;
import java.util.Scanner;

public class TournoiControllerHibernate {

    private TournoiServiceHibernate tournoiServiceHibernate;
    private Scanner scanner;

    public TournoiControllerHibernate() {
        tournoiServiceHibernate = new TournoiServiceHibernate();
        scanner = new Scanner(System.in);  // Initialisation du scanner
    }

    public void afficherTournoiHibernate() {

        System.out.println("Quel est l'identifiant du Tournoi dont vous voulez afficher les informations ?");
        long id = scanner.nextLong();
        scanner.nextLine();  // Consommer le newline après nextLong
        TournoiDto tournoiDto = tournoiServiceHibernate.getById(id);
        if (tournoiDto != null) {
            System.out.println(String.format("Le tournoi sélectionné s'appelle %s avec le code %s", tournoiDto.getNom(), tournoiDto.getCode()));
        } else {
            System.out.println("Tournoi non trouvé.");
        }
    }

    public void creerTournoiHibernate() {

        System.out.println("Quel est le nom du tournoi ?");
        String nom = scanner.nextLine();
        System.out.println("Quel est le code du tournoi ?");
        String code = scanner.nextLine();

        TournoiDto tournoiDto = new TournoiDto();
        tournoiDto.setNom(nom);
        tournoiDto.setCode(code);
        tournoiServiceHibernate.create(tournoiDto);
        System.out.println("Le tournoi a été créé avec succès.");
    }

    public void renommeTournoiHibernate() {

        System.out.println("Quel est l'identifiant du tournoi que vous voulez renommer ?");
        long id = scanner.nextLong();
        scanner.nextLine();  // Consommer le newline après nextLong
        System.out.println("Quel est le nouveau nom ?");
        String nom = scanner.nextLine();

        TournoiDto tournoiDto = tournoiServiceHibernate.getById(id);
        if (tournoiDto != null) {
            tournoiDto.setNom(nom);  // Modifier le nom du tournoi
            tournoiServiceHibernate.update(tournoiDto);  // Sauvegarder la mise à jour
            System.out.println("Le tournoi a été renommé avec succès.");
        } else {
            System.out.println("Tournoi non trouvé.");
        }
    }

    public void supprimeTournoiHibernate() {
        System.out.println("Quel est l'identifiant du tournoi à supprimer ?");
        long id = scanner.nextLong();
        scanner.nextLine();  // Consommer le newline après nextLong

        tournoiServiceHibernate.delete(id);
        System.out.println("Le tournoi a été supprimé avec succès.");
    }

    // Optionnel : Vous pouvez ajouter une méthode pour fermer le scanner
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
