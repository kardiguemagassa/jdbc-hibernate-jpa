package com.company.tennis.core.service;

import com.company.tennis.core.entity.Joueur;
import com.company.tennis.core.repository.JoueurRepositoryImpl;

import java.util.List;

public class JoueurService {

    private JoueurRepositoryImpl joueurRepositoryimpl;

    public JoueurService() {
        this.joueurRepositoryimpl = new JoueurRepositoryImpl();
    }

    public List<Joueur> list () {
        return joueurRepositoryimpl.list();
    }

    public Joueur getById (Long id) {
        return joueurRepositoryimpl.getById(id);
    }

    public void create(Joueur joueur) {
        joueurRepositoryimpl.create(joueur);
    }

    public void update(Joueur joueur) {
        joueurRepositoryimpl.update(joueur);
    }

    public void delete (Long id) {
        joueurRepositoryimpl.delete(id);
    }
}
