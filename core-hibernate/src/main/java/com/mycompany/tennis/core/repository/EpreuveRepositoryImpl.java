package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;

import org.hibernate.Session;

import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


public class EpreuveRepositoryImpl {

    //HQL
    public List<Epreuve> list(String codeTournoi) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Epreuve> query = session.createQuery("select e from Epreuve e join fetch e.tournoi where e.tournoi.code =:codeTournoi", Epreuve.class);
        query.setParameter("codeTournoi", codeTournoi);
        List<Epreuve> epreuves = query.getResultList();
        System.out.println("Epreuves lus");
        return epreuves;
    }

    public List<Epreuve> listJPA(String codeTournoi) {

        EntityManager em = EntityManagerHolder.getCurrentEntityManager();

        // JPQL
        /*TypedQuery<Epreuve> query = em.createQuery("select e from Epreuve e join fetch e.tournoi where e.tournoi.code=?1",Epreuve.class);
        query.setParameter(1,codeTournoi);*/
        TypedQuery<Epreuve> query = em.createQuery("select e from Epreuve e join fetch e.tournoi where e.tournoi.code =:codeTournoi", Epreuve.class);
        query.setParameter("codeTournoi", codeTournoi);

        List<Epreuve> epreuves = query.getResultList();
        System.out.println("Les epreuves ont été lus");
        return epreuves;
    }

    // cette class utilise que JPA
    public Epreuve getById(Long id) {

        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        Epreuve epreuve = em.find(Epreuve.class,id);
        System.out.println("L'epreuve a été lu");
        return epreuve;
    }

}
