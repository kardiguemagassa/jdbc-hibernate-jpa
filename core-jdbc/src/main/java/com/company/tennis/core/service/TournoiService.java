package com.company.tennis.core.service;

import com.company.tennis.core.entity.Joueur;
import com.company.tennis.core.entity.Tournoi;
import com.company.tennis.core.repository.JoueurRepositoryImpl;
import com.company.tennis.core.repository.TournoiRepositoryImpl;

import java.util.List;

public class TournoiService {

    private TournoiRepositoryImpl tournoiRepositoryimpl;

    public TournoiService() {
        tournoiRepositoryimpl = new TournoiRepositoryImpl();
    }

    public List<Tournoi> list() {
        return tournoiRepositoryimpl.list();
    }

    public Tournoi getById(Long id) {
        return tournoiRepositoryimpl.getById(id);
    }

    public void create(Tournoi tournoi) {
        tournoiRepositoryimpl.create(tournoi);
    }

    public void update(Tournoi tournoi) {
        tournoiRepositoryimpl.update(tournoi);
    }
    public void delete(Long id) {
        tournoiRepositoryimpl.delete(id);
    }
}
