
package com.mycompany.tennis.core;

import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;

public class TravauxPratiques {

    public static  void main(String... args){

        TournoiRepositoryImpl toutnoiRepository=new TournoiRepositoryImpl();
        toutnoiRepository.list().stream().forEach(
                tournoi -> System.out.println("Tournoi numéro "+tournoi.getId()
                +" nom: "+tournoi.getNom() +"code "+tournoi.getCode()));
    }
}

