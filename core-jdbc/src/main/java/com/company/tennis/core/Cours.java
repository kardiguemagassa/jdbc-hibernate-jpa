package com.company.tennis.core;

import com.company.tennis.core.entity.Epreuve;
import com.company.tennis.core.entity.Joueur;
import com.company.tennis.core.entity.Match;
import com.company.tennis.core.entity.Score;
import com.company.tennis.core.service.JoueurService;
import com.company.tennis.core.service.MatchService;


public class Cours {
    public static void main(String[] args) {

        JoueurService joueurService = new JoueurService();
        MatchService matchService = new MatchService();

        /*List<Joueur> liste = joueurService.list();
        for (Joueur joueur : liste) {
            System.out.println(joueur.getPrenom() + " : " + joueur.getNom());
        }


        Joueur joueur = joueurService.getById(10L);
        System.out.println(joueur.getNom() + " " + joueur.getPrenom());
        System.out.println();*/

        /*Joueur noah = new Joueur();
        noah.setNom("Noah");
        noah.setPrenom("Yannik 1");
        noah.setSexe('H');
        joueurService.create(noah);
        //System.out.println(noah.getNom() + " " + noah.getPrenom());
        System.out.println("Id du Joueur créé : " + noah.getId());*/

        /*Joueur noahamodifier = joueurService.getById(44L);
        noahamodifier.setPrenom("Yannick");
        joueurRepository.update(noahamodifier);*/

        //joueurService.delete(47L);

        Match match = new Match();
        Score score = new Score();
        score.setSet1((byte)3);
        score.setSet2((byte)4);
        score.setSet3((byte)6);
        match.setScore(score);
        score.setMatch(match);

        Joueur federer = new Joueur();
        federer.setId(32L);
        Joueur murray = new Joueur();
        murray.setId(34L);
        match.setVainqueur(federer);
        match.setFinaliste(murray);
        Epreuve epreuve = new Epreuve();
        epreuve.setId(10L);
        match.setEpreuve(epreuve);

        matchService.enregistrerNouveauMatch(match);
        System.out.println("Id du match créé : " + match.getId());


    }

}
