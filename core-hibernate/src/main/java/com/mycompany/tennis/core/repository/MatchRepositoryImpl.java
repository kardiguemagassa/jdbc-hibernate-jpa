package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Match;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MatchRepositoryImpl {

    public List<Match> list() {

        List<Match> tournois = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        tournois = session.createQuery("FROM Tournoi", Match.class).getResultList(); // Récupérer tous les tournois

        return tournois;
    }

    public Match getById(Long id) {

        Match match = null;


        Session session = HibernateUtil.getSessionFactory().openSession();
        //tx = session.beginTransaction();

        // Requête HQL pour récupérer le match avec ses associations
        match = session.get(Match.class, id);

        if (match != null) {

            Hibernate.initialize(match.getFinaliste());
            Hibernate.initialize(match.getVainqueur());
            Hibernate.initialize(match.getEpreuve());
            Hibernate.initialize(match.getScore());

            // Initialisation des propriétés imbriquées de l'épreuve et du tournoi
            if (match.getEpreuve() != null) {
                Hibernate.initialize(match.getEpreuve().getTournoi()); // Initialiser le tournoi de l'épreuve
                Hibernate.initialize(match.getEpreuve().getTournoi().getNom()); // Initialiser le nom du tournoi
            }

            // Initialisation de propriétés supplémentaires si nécessaire
            Hibernate.initialize(match.getScore().getMatch());  // Initialiser le match dans le score
            Hibernate.initialize(match.getScore().getMatch().getVainqueur());  // Initialiser le vainqueur dans le score
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
