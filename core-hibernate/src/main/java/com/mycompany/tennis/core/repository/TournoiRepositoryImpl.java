package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Tournoi;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TournoiRepositoryImpl {

    public List<Tournoi> list() {

        List<Tournoi> tournois = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        tournois = session.createQuery("from Tournoi", Tournoi.class).list();

        return tournois;
    }

    public Tournoi getById(Long id) {

        Tournoi tournoi = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        tournoi = session.find(Tournoi.class, id);

        return tournoi;
    }

    public void create(Tournoi tournoi) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.persist(tournoi);
    }

    public void update(Tournoi tournoi) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.update(tournoi);
    }

    public void delete(Long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Tournoi tournoi = getById(id);

        if (tournoi != null) {
            session.delete(tournoi);
            System.out.println("Tournoi supprimé");
        } else {
            System.out.println("Tournoi non trouvé.");
        }
    }
}
