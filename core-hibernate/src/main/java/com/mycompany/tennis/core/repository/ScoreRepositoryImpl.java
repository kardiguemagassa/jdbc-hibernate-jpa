package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Score;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ScoreRepositoryImpl {

    public List<Score> list() {

        List<Score> scores = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        scores = session.createQuery("from Score", Score.class).list();

        return scores;
    }

    public Score getById(Long id) {

        Score score = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        score = session.find(Score.class, id);

        if (score != null) {
            // Force l'initialisation des entités associées avant la fermeture de la session
            if (score.getMatch() != null) {
                Hibernate.initialize(score.getMatch().getEpreuve());  // Initialiser l'Epreuve associée
                Hibernate.initialize(score.getMatch().getVainqueur());  // Initialiser le Vainqueur
                Hibernate.initialize(score.getMatch().getFinaliste());  // Initialiser le Finaliste
            }

            if (score.getMatch().getEpreuve() != null) {
                Hibernate.initialize(score.getMatch().getEpreuve().getTournoi());  // Initialiser le Tournoi
            }
        }

        return score;
    }

    public void create(Score score) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.persist(score);
    }

    public void update(Score score) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.update(score);
    }

    public void delete(Long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Score score = getById(id);

        if (score != null) {
            session.delete(score);
            System.out.println("Score supprimé");
        } else {
                System.out.println("Score non trouvé.");
        }
    }
}
