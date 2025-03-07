package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.*;
import com.mycompany.tennis.core.entity.*;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MatchServiceHibernate {

    private final MatchRepositoryImpl matchRepository;
    ScoreRepositoryImpl scoreRepository;
    EpreuveRepositoryImpl epreuveRepository;
    JoueurRepositoryImpl joueurRepository;

    public MatchServiceHibernate() {
        this.matchRepository = new MatchRepositoryImpl();
        this.scoreRepository = new ScoreRepositoryImpl();
        this.epreuveRepository = new EpreuveRepositoryImpl();
        this.joueurRepository = new JoueurRepositoryImpl();

    }

    public List<Match> list() {
        Session session = null;
        Transaction tx = null;
        List<Match> matches = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            matches = matchRepository.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return matches;
    }

    public MatchDto getMatch(Long id) {
        Session session = null;
        Transaction tx = null;
        Match match = null;
        MatchDto matchDto = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            match = matchRepository.getById(id);

            matchDto = new MatchDto();
            matchDto.setId(match.getId());

            JoueurDto joueurDtoFinaliste = new JoueurDto();
            joueurDtoFinaliste.setId(match.getFinaliste().getId());
            joueurDtoFinaliste.setNom(match.getFinaliste().getNom());
            joueurDtoFinaliste.setPrenom(match.getFinaliste().getPrenom());
            joueurDtoFinaliste.setSexe(match.getFinaliste().getSexe());
            matchDto.setFinaliste(joueurDtoFinaliste);

            JoueurDto joueurDtoVainqueur = new JoueurDto();
            joueurDtoVainqueur.setId(match.getVainqueur().getId());
            joueurDtoVainqueur.setNom(match.getVainqueur().getNom());
            joueurDtoVainqueur.setPrenom(match.getVainqueur().getPrenom());
            joueurDtoVainqueur.setSexe(match.getVainqueur().getSexe());
            matchDto.setVainqueur(joueurDtoVainqueur);

            EpreuveFullDto epreuveFullDto = new EpreuveFullDto();
            epreuveFullDto.setId(match.getEpreuve().getId());
            epreuveFullDto.setAnnee(match.getEpreuve().getAnnee());
            epreuveFullDto.setTypeEpreuve(match.getEpreuve().getTypeEpreuve());
            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(match.getEpreuve().getTournoi().getId());
            tournoiDto.setNom(match.getEpreuve().getTournoi().getNom());
            tournoiDto.setCode(match.getEpreuve().getTournoi().getCode());
            epreuveFullDto.setTournoi(tournoiDto);

            matchDto.setEpreuve(epreuveFullDto);

            ScoreFullDto scoreFullDto = new ScoreFullDto();
            scoreFullDto.setId(match.getScore().getId());
            scoreFullDto.setSet1(match.getScore().getSet1());
            scoreFullDto.setSet2(match.getScore().getSet2());
            scoreFullDto.setSet3(match.getScore().getSet3());
            scoreFullDto.setSet4(match.getScore().getSet4());
            scoreFullDto.setSet5(match.getScore().getSet5());

            matchDto.setScore(scoreFullDto);
            scoreFullDto.setMatch(matchDto);

            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return matchDto;
    }

    public void tapisVert(Long id) {

        Session session = null;
        Transaction tx = null;
        Match match = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // Récupérer le match complet avec ses associations via la méthode getById
            match = matchRepository.getById(id);

            if (match != null) {
                // Annuler le résultat du match : intervertir le vainqueur et le finaliste
                Joueur ancienVainqueur = match.getVainqueur();
                match.setVainqueur(match.getFinaliste());
                match.setFinaliste(ancienVainqueur);

                // Réinitialiser les sets du score du match
                match.getScore().setSet1((byte) 0);
                match.getScore().setSet2((byte) 0);
                match.getScore().setSet3((byte) 0);
                match.getScore().setSet4((byte) 0);
                match.getScore().setSet5((byte) 0);

                // Mettre à jour le match dans la base de données
                session.update(match);

                tx.commit();
                System.out.println("Match annulé et réinitialisé avec succès.");
            } else {
                System.out.println("Le match avec l'ID spécifié n'a pas été trouvé.");
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void create(MatchDto matchDto) {

        Session session = null;
        Transaction tx = null;
        Match match = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

             match = new Match();
             match.setEpreuve(epreuveRepository.getById(matchDto.getEpreuve().getId()));
             match.setVainqueur(joueurRepository.getById(matchDto.getVainqueur().getId()));
            match.setFinaliste(joueurRepository.getById(matchDto.getFinaliste().getId()));

            Score score = new Score();
            score.setMatch(match);
            match.setScore(score);

            score.setSet1(matchDto.getScore().getSet1());
            score.setSet2(matchDto.getScore().getSet2());
            score.setSet3(matchDto.getScore().getSet3());
            score.setSet4(matchDto.getScore().getSet4());
            score.setSet5(matchDto.getScore().getSet5());

            matchRepository.create(match);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public void delete(Long id) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            matchRepository.delete(id);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
