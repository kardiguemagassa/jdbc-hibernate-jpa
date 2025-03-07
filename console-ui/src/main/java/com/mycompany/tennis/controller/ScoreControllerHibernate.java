package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.ScoreFullDto;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.service.ScoreServiceHibernate;

import java.util.Scanner;

public class ScoreControllerHibernate {

    private ScoreServiceHibernate scoreServiceHibernate;
    private Scanner scanner;

    public ScoreControllerHibernate() {
        scoreServiceHibernate = new ScoreServiceHibernate();
        scanner = new Scanner(System.in);  // Initialisation du scanner
    }

    public void afficherScoreHibernate() {

        System.out.println("Quel est l'identifiant du Score dont vous voulez afficher les informations ?");
        long id = scanner.nextLong();
        scanner.nextLine();
        ScoreFullDto scoreFullDto = scoreServiceHibernate.getByScore(id);

        if (scoreFullDto != null) {
            System.out.println("Les sets du score : ");
            System.out.println("Set 1 : " + scoreFullDto.getSet1());
            System.out.println("Set 2 : " + scoreFullDto.getSet2());
            if (scoreFullDto.getSet3() != null) {
                System.out.println("Set 3 : " + scoreFullDto.getSet3());
            }
            if (scoreFullDto.getSet4() != null) {
                System.out.println("Set 4 : " + scoreFullDto.getSet4());
            }
            if (scoreFullDto.getSet5() != null) {
                System.out.println("Set 5 : " + scoreFullDto.getSet5());
            }
        } else {
            System.out.println("Score non trouvé.");
        }

        System.out.println("Il s'agit du tournoi "+scoreFullDto.getMatch().getEpreuve().getTournoi().getNom());
        System.out.println("L'epreuve s'est déroulée en "+ scoreFullDto.getMatch().getEpreuve().getAnnee() + " et il s'agit d'une épreuve "
                + (scoreFullDto.getMatch().getEpreuve().getTypeEpreuve().charValue() == 'H' ? "Homme" : "Femme"));
    }

    public void creerScoreHibernate() {
        System.out.println("Quel est le score pour Set 1 ?");
        Byte set1 = scanner.nextByte();
        System.out.println("Quel est le score pour Set 2 ?");
        Byte set2 = scanner.nextByte();
        System.out.println("Quel est le score pour Set 3 ?");
        Byte set3 = scanner.nextByte();
        System.out.println("Quel est le score pour Set 4 ?");
        Byte set4 = scanner.nextByte();
        System.out.println("Quel est le score pour Set 5 ?");
        Byte set5 = scanner.nextByte();
        scanner.nextLine(); // Consommer la ligne restante

        //Score score = new Score(set1, set2, set3, set4, set5, null); // Remplacez `null` par un objet `Match` si nécessaire
        Score score = new Score();
        scoreServiceHibernate.create(score);
        System.out.println("Le score a été créé avec succès.");
    }

    /*public void renommeScoreHibernate() {
        System.out.println("Quel est l'identifiant du score que vous voulez renommer ?");
        long id = scanner.nextLong();
        scanner.nextLine();  // Consommer le newline après nextLong

        Score score = scoreServiceHibernate.getByScore(id);
        if (score != null) {
            System.out.println("Quels sont les nouveaux scores ?");
            System.out.println("Set 1 : ");
            score.setSet1(scanner.nextByte());
            System.out.println("Set 2 : ");
            score.setSet2(scanner.nextByte());
            System.out.println("Set 3 : ");
            score.setSet3(scanner.nextByte());
            System.out.println("Set 4 : ");
            score.setSet4(scanner.nextByte());
            System.out.println("Set 5 : ");
            score.setSet5(scanner.nextByte());
            scanner.nextLine();  // Consommer la ligne restante
            scoreServiceHibernate.update(score);  // Mettre à jour le score
            System.out.println("Le score a été mis à jour avec succès.");
        } else {
            System.out.println("Score non trouvé.");
        }
    }*/

    public void supprimeScoreHibernate() {
        System.out.println("Quel est l'identifiant du score à supprimer ?");
        long id = scanner.nextLong();
        scanner.nextLine();  // Consommer le newline après nextLong

        scoreServiceHibernate.delete(id);
        System.out.println("Le score a été supprimé avec succès.");
    }

    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
