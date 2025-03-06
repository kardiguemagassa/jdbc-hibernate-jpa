package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.MatchDto;
import com.mycompany.tennis.core.dto.ScoreFullDto;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ScoreServiceHibernate {

    private final ScoreRepositoryImpl scoreRepository;

    public ScoreServiceHibernate() {
        this.scoreRepository = new ScoreRepositoryImpl();
    }

    // Récupérer la liste des scores
    public List<Score> list() {
        return scoreRepository.list();
    }


    public ScoreFullDto getByScore(Long id) {

        Session session = null;
        Transaction tx = null;
        Score score = null;
        ScoreFullDto scoreFullDto = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            score= scoreRepository.getById(id);

            scoreFullDto = new ScoreFullDto();
            scoreFullDto.setId(score.getId());
            scoreFullDto.setSet1(score.getSet1());
            scoreFullDto.setSet2(score.getSet2());
            scoreFullDto.setSet3(score.getSet3());
            scoreFullDto.setSet4(score.getSet4());
            scoreFullDto.setSet5(score.getSet5());

            MatchDto matchDto = new MatchDto();
            matchDto.setId(score.getMatch().getId());
            scoreFullDto.setMatch(matchDto);

            EpreuveFullDto epreuveFullDto = new EpreuveFullDto();
            epreuveFullDto.setId(score.getMatch().getEpreuve().getId());
            epreuveFullDto.setAnnee(score.getMatch().getEpreuve().getAnnee());
            epreuveFullDto.setTypeEpreuve(score.getMatch().getEpreuve().getTypeEpreuve());
            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(score.getMatch().getEpreuve().getTournoi().getId());
            tournoiDto.setNom(score.getMatch().getEpreuve().getTournoi().getNom());
            tournoiDto.setCode(score.getMatch().getEpreuve().getTournoi().getCode());
            epreuveFullDto.setTournoi(tournoiDto);

            matchDto.setEpreuve(epreuveFullDto);

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

        return scoreFullDto;
    }

    // Ajouter un nouveau score
    public void create(Score score) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            scoreRepository.create(score); // Appel à la méthode du repository
            tx.commit();
            System.out.println("Score ajouté avec succès");
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

    // Mettre à jour un score existant
    public void update(Score score) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            scoreRepository.update(score); // Appel à la méthode du repository
            tx.commit();
            System.out.println("Score mis à jour avec succès");
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

    // Supprimer un score par ID
    public void delete(Long id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            scoreRepository.delete(id); // Appel à la méthode du repository
            tx.commit();
            System.out.println("Score supprimé avec succès");
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
