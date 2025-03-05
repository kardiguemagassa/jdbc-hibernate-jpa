package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class JoueurServiceHibernate {

    private final JoueurRepositoryImpl joueurRepositoryimpl;

    public JoueurServiceHibernate() {
        this.joueurRepositoryimpl = new JoueurRepositoryImpl();
    }

    public List<Joueur> list() {
        Session session = null;
        Transaction tx = null;
        List<Joueur> joueurs = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            joueurs = joueurRepositoryimpl.list();
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
        return joueurs;
    }

    public Joueur getById(Long id) {
        Session session = null;
        Transaction tx = null;
        Joueur joueur = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            joueur = joueurRepositoryimpl.getById(id);
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

        return joueur;
    }

    public void create(Joueur joueur) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            joueurRepositoryimpl.create(joueur);
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

    public void renomme(Long id, String nouveauNom) {

        Joueur joueur = getById(id); // créer un joueur detaché

        Session session = null;
        Transaction tx = null;

        try {
             session = HibernateUtil.getSessionFactory().getCurrentSession(); //réutiliser session déjà ouverte
            tx = session.beginTransaction();

            joueur.setNom(nouveauNom);
            Joueur joueur2 = (Joueur)session.merge(joueur); // utilisation de merge
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public void update(Joueur joueur) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            joueurRepositoryimpl.update(joueur);
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
            joueurRepositoryimpl.delete(id);
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
