package com.company.tennis.core.service;

import com.company.tennis.core.entity.Match;
import com.company.tennis.core.repository.MatchRepositoryImpl;
import com.company.tennis.core.repository.ScoreRepositoryImpl;

public class MatchService {

    private ScoreRepositoryImpl scoreRepositoryImpl;
    private MatchRepositoryImpl matchRepositoryImpl;

    public MatchService() {

        this.scoreRepositoryImpl = new ScoreRepositoryImpl();
        this.matchRepositoryImpl = new MatchRepositoryImpl();

    }

    public void enregistrerNouveauMatch(Match match) {
        matchRepositoryImpl.create(match);
        scoreRepositoryImpl.create(match.getScore());
    }
}
