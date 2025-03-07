package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.*;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.service.EpreuveServiceHibernate;
import com.mycompany.tennis.core.service.MatchServiceHibernate;

import java.util.Scanner;

public class MatchControllerHibernate {

    private MatchServiceHibernate matchServiceHibernate;
    private Scanner scanner;

    public MatchControllerHibernate() {
        matchServiceHibernate = new MatchServiceHibernate();
        scanner = new Scanner(System.in);
    }

    public void afficherDetailleMatchHibernate() {

        System.out.println("Quel est l'identifiant de match dont vous voulez afficher les informations ?");
        long id = scanner.nextLong();
        scanner.nextLine();
        MatchDto matchDto = matchServiceHibernate.getMatch(id);

        System.out.println(String.format("Il s'agit d'un match de " + matchDto.getEpreuve().getAnnee()
                + " qui s'est déroulé à " + matchDto.getEpreuve().getTournoi().getNom()));

        System.out.println(String.format("Le nom et le prenom du vainqueur sont " + matchDto.getVainqueur().getNom()
                + " " + matchDto.getVainqueur().getPrenom()));

        System.out.println(String.format("Le nom et le prenom du finaliste " +  matchDto.getFinaliste().getNom()
                + " " + matchDto.getFinaliste().getPrenom()));

        System.out.println("Les sets du score sont " + matchDto.getScore().getSet1());

        if (matchDto.getScore() != null) {
            System.out.println("Les sets du score : ");
            System.out.println("Set 1 : " + matchDto.getScore().getSet1());
            System.out.println("Set 2 : " + matchDto.getScore().getSet2());
            if (matchDto.getScore().getSet3() != null) {
                System.out.println("Set 3 : " + matchDto.getScore().getSet3());
            }
            if (matchDto.getScore().getSet4() != null) {
                System.out.println("Set 4 : " + matchDto.getScore().getSet4());
            }
            if (matchDto.getScore().getSet5() != null) {
                System.out.println("Set 5 : " + matchDto.getScore().getSet5());
            }
        } else {
            System.out.println("Score non trouvé.");
        }
    }

    public void tapisVert () {

        System.out.println("Quel est l'identifiant de match dont vous voulez annulez ?");
        long id = scanner.nextLong();
        matchServiceHibernate.tapisVert(id);
    }

    public void ajouterMatchHibernate() {

        System.out.println("Quel est l'idenfifiant de l'epreuve  ?");
        long epreuveId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Quel est l'identifiant du vainqueur ?");
        long vainqueurId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Quel est l'identifiant de finaliste ?");
        long finalisteId = scanner.nextLong();
        scanner.nextLine();

        MatchDto matchDto = new MatchDto();
        matchDto.setEpreuve(new EpreuveFullDto());
        matchDto.getEpreuve().setId(epreuveId);
        matchDto.setFinaliste(new JoueurDto());
        matchDto.getFinaliste().setId(finalisteId);
        matchDto.setVainqueur(new JoueurDto());
        matchDto.getVainqueur().setId(vainqueurId);

        System.out.println("Quel est la valeur du premier set ?");
        byte set1 = scanner.nextByte();
        scanner.nextLine();
        System.out.println("Quel est la valeur du 2eme set ?");
        byte set2 = scanner.nextByte();
        scanner.nextLine();
        System.out.println("Quel est la valeur du 3eme set ?");
        byte set3 = scanner.nextByte();
        scanner.nextLine();
        System.out.println("Quel est la valeur du 4eme set ?");
        byte set4 = scanner.nextByte();
        scanner.nextLine();
        System.out.println("Quel est la valeur du 5eme set ?");
        byte set5 = scanner.nextByte();
        scanner.nextLine();

        ScoreFullDto scoreFullDto = new ScoreFullDto();
        scoreFullDto.setSet1(set1);
        scoreFullDto.setSet2(set2);
        scoreFullDto.setSet3(set3);
        scoreFullDto.setSet4(set4);
        scoreFullDto.setSet5(set5);

        matchDto.setScore(scoreFullDto);
        scoreFullDto.setMatch(matchDto);

        matchServiceHibernate.create(matchDto);

        String nom = scanner.nextLine();
        System.out.println("Quel est le code du tournoi ?");
        String code = scanner.nextLine();

        Tournoi tournoi = new Tournoi();
        tournoi.setNom(nom);
        tournoi.setCode(code);

        System.out.println("Le tournoi a été créé avec succès.");
    }

    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }

}
