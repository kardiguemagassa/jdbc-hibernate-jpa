package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Score;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ScoreRepositoryImpl {

    // Récupérer la liste de tous les scores
    public List<Score> list() {
        Session session = null;
        Transaction tx = null;
        List<Score> scores = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();  // Utilisation de openSession
            tx = session.beginTransaction();
            scores = session.createQuery("from Score", Score.class).list();  // Correction ici
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close(); // Assurez-vous de fermer la session
            }
        }
        return scores;
    }

    public Score getById(Long id) {
        Session session = null;
        Transaction tx = null;
        Score score = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();  // Utilisation de openSession
            tx = session.beginTransaction();
            score = session.find(Score.class, id);  // Utilisation de find pour récupérer l'entité

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

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close(); // Assurez-vous de fermer la session
            }
        }
        return score;
    }


    // Ajouter un nouveau score
    public void create(Score score) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();  // Utilisation de openSession
            tx = session.beginTransaction();
            session.persist(score);  // Utilisation de persist pour ajouter l'entité
            tx.commit();
            System.out.println("Score ajouté");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close(); // Assurez-vous de fermer la session
            }
        }
    }

    // Mettre à jour un score existant
    public void update(Score score) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();  // Utilisation de openSession
            tx = session.beginTransaction();
            session.update(score);  // Utilisation de update pour modifier l'entité
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close(); // Assurez-vous de fermer la session
            }
        }
    }

    // Supprimer un score par ID
    public void delete(Long id) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();  // Utilisation de openSession
            tx = session.beginTransaction();
            Score score = getById(id);  // Récupérer le score par ID
            if (score != null) {
                session.delete(score);  // Supprimer le score
                tx.commit();
                System.out.println("Score supprimé");
            } else {
                System.out.println("Score non trouvé.");
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close(); // Assurez-vous de fermer la session
            }
        }
    }
}
