package com.company.tennis.core.service;

import com.company.tennis.core.entity.Joueur;
import com.company.tennis.core.repository.JoueurRepositoryImpl;

public class JoueurService {

    private JoueurRepositoryImpl joueurRepositoryimpl;

    public JoueurService() {
        joueurRepositoryimpl = new JoueurRepositoryImpl();
    }

    public void createJoueur(Joueur joueur) {
        joueurRepositoryimpl.create(joueur);
    }

    public Joueur getJoueur(Long id) {
        return joueurRepositoryimpl.getById(id);
    }
}
