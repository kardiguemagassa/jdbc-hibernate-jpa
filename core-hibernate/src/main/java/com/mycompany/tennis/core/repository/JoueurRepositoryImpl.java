
package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class JoueurRepositoryImpl {

    public JoueurRepositoryImpl() {}

    public List<Joueur> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Joueur> joueurs = null;
        try {
            tx = session.beginTransaction();
            joueurs = session.createQuery("from Joueur", Joueur.class).list(); // Spécifier le type
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return joueurs;
    }

    public Joueur getById(Long id) {

        Transaction tx = null;
        Joueur joueur = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            tx = session.beginTransaction();
            joueur = session.find(Joueur.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return joueur;
    }

    public void create(Joueur joueur) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(joueur);
            tx.commit();
            System.out.println("Joueur ajouté");
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(joueur);
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

    public void delete(Long id) {
        Joueur joueur = getById(id);
        if (joueur != null) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.delete(joueur); // Supprimer le joueur
                tx.commit();
                System.out.println("Joueur supprimé");
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            } finally {
                session.close();
            }
        } else {
            System.out.println("Joueur non trouvé");
        }
    }


}

