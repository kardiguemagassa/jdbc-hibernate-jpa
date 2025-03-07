package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.EpreuveLightDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.entity.Epreuve;
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
    }

    public void afficherEpreuveHibernateRolangaros() {
        System.out.println("Quel est l'identifiant de l'Epreuve dont vous voulez afficher les informations ?");
        long id = scanner.nextLong();
        scanner.nextLine();  // Consommer le newline après nextLong
        EpreuveLightDto epreuveLightDto = epreuveServiceHibernate.getByIdSansTournoi(id);
    }

    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
