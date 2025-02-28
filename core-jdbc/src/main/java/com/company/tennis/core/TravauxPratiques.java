package com.company.tennis.core;

import com.company.tennis.core.entity.Tournoi;
import com.company.tennis.core.repository.TournoiRepositoryImpl;
import com.company.tennis.core.service.TournoiService;

public class TravauxPratiques {

    public static void main(String... args){

        // Read list
        TournoiService tournoiService = new TournoiService();
        tournoiService.list().stream()
                .forEach(tournoi -> System.out.println("Tourmoi numéro "
                        + tournoi.getId() + " nom: " + tournoi.getNom()
                        +"code: " + tournoi.getCode())
                );
        /*
        // Create
        Tournoi parisBercy = new Tournoi();
        parisBercy.setNom("Paris Bercy");
        parisBercy.setCode("PB");
        tournoiService.createTournoi(parisBercy);
        System.out.println("id du tournoi Paris Bercy: " + parisBercy.getId());
        */

        /*
        // Read
        Tournoi parisBercy = tournoiService.getTournoi(8L);
        System.out.println(parisBercy.getNom() + " " + parisBercy.getCode());
         */

    }
}
