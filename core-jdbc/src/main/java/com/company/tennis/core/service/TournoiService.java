package com.company.tennis.core.service;

import com.company.tennis.core.entity.Joueur;
import com.company.tennis.core.entity.Tournoi;
import com.company.tennis.core.repository.JoueurRepositoryImpl;
import com.company.tennis.core.repository.TournoiRepositoryImpl;

public class TournoiService {

    private TournoiRepositoryImpl tournoiRepositoryimpl;

    public TournoiService() {
        tournoiRepositoryimpl = new TournoiRepositoryImpl();
    }

    public void createTournoi(Tournoi tournoi) {
        tournoiRepositoryimpl.create(tournoi);
    }

    public Tournoi getTournoi(Long id) {
        return tournoiRepositoryimpl.getById(id);
    }
}
