package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Tournoi;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TournoiRepositoryImpl {


    public List<Tournoi> list() {
        Session session = null;
        Transaction tx = null;
        List<Tournoi> tournois = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            tournois = session.createQuery("from Tournoi", Tournoi.class).list();
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
        return tournois;
    }


    public Tournoi getById(Long id) {
        Session session = null;
        Transaction tx = null;
        Tournoi tournoi = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            tournoi = session.find(Tournoi.class, id);
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
        return tournoi;
    }


    public void create(Tournoi tournoi) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(tournoi);
            tx.commit();
            System.out.println("Tournoi ajouté");
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


    public void update(Tournoi tournoi) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(tournoi);
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
            Tournoi tournoi = getById(id);
            if (tournoi != null) {
                session.delete(tournoi);
                tx.commit();
                System.out.println("Tournoi supprimé");
            } else {
                System.out.println("Tournoi non trouvé.");
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
}
