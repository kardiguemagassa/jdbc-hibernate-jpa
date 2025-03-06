
package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class JoueurRepositoryImpl {

    public JoueurRepositoryImpl() {}

    public List<Joueur> list(char sexe) {

        //Session session = HibernateUtil.getSessionFactory().openSession();
        //Query<Joueur> query = session.createQuery("select j from Joueur j where j.sexe = :sexe", Joueur.class);
       //query.setParameter("sexe", sexe);

        // utilisation de nameQuery
        //Query<Joueur> query = session.createNamedQuery("given_sexe", Joueur.class);
        //query.setParameter("sexe", sexe);

        // JPA
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        TypedQuery<Joueur> query = em.createNamedQuery("given_sexe", Joueur.class);
        query.setParameter("sexe", sexe);

        List<Joueur> joueurs = query.getResultList();
        System.out.println("Joueurs lus");
        return joueurs;
    }

    public Joueur getById(Long id) {

        Joueur joueur = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        joueur = session.find(Joueur.class, id);

        return joueur;
    }

    public void create(Joueur joueur) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.persist(joueur);
    }

    public void update(Joueur joueur) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.update(joueur);
    }

    public void delete(Long id) {

        Joueur joueur = getById(id);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.delete(joueur);

    }

}

