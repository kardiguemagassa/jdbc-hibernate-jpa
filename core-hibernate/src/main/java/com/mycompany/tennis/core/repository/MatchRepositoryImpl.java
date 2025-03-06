package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Match;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MatchRepositoryImpl {

    public List<Match> list() {
        Session session = null;
        List<Match> tournois = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tournois = session.createQuery("FROM Tournoi", Match.class).getResultList(); // Récupérer tous les tournois
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return tournois;
    }

    public Match getById(Long id) {
        Session session = null;
        Transaction tx = null;
        Match match = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // Requête HQL pour récupérer le match avec ses associations
            match = session.get(Match.class, id);

            if (match != null) {
                // Initialisation explicite des associations qui sont en Lazy Loading
                Hibernate.initialize(match.getFinaliste());  // Initialiser le finaliste
                Hibernate.initialize(match.getVainqueur()); // Initialiser le vainqueur
                Hibernate.initialize(match.getEpreuve());    // Initialiser l'épreuve
                Hibernate.initialize(match.getScore());      // Initialiser le score

                // Initialisation des propriétés imbriquées de l'épreuve et du tournoi
                if (match.getEpreuve() != null) {
                    Hibernate.initialize(match.getEpreuve().getTournoi()); // Initialiser le tournoi de l'épreuve
                    Hibernate.initialize(match.getEpreuve().getTournoi().getNom()); // Initialiser le nom du tournoi
                }

                // Initialisation de propriétés supplémentaires si nécessaire
                Hibernate.initialize(match.getScore().getMatch());  // Initialiser le match dans le score
                Hibernate.initialize(match.getScore().getMatch().getVainqueur());  // Initialiser le vainqueur dans le score
            }

            tx.commit();
            System.out.println("Match récupéré et initialisé avec succès.");

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
        return match;
    }


    public void create(Match match) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.persist(match);
        System.out.println("Match ajouté");
    }

    public void delete(Long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Match match = session.get(Match.class, id);
        session.delete(match);
        System.out.println("Match supprimé");
    }

}
