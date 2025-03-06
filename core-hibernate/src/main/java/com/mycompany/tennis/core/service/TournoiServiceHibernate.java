package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class TournoiServiceHibernate {

    private final TournoiRepositoryImpl tournoiRepositoryImpl;

    public TournoiServiceHibernate() {
        this.tournoiRepositoryImpl = new TournoiRepositoryImpl();
    }


    public List<Tournoi> list() {
        Session session = null;
        Transaction tx = null;
        List<Tournoi> tournois = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            tournois = tournoiRepositoryImpl.list();
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


    public TournoiDto getById(Long id) {

        //Session session = null;
        //Transaction tx = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        Tournoi tournoi = null;
        TournoiDto tournoiDto = null;

        try {
            //session = HibernateUtil.getSessionFactory().openSession();
            em = EntityManagerHolder.getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            tournoi = tournoiRepositoryImpl.getById(id);
            tournoiDto = new TournoiDto();
            tournoiDto.setId(tournoi.getId());
            tournoiDto.setNom(tournoi.getNom());
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return tournoiDto;
    }


    public void create(TournoiDto tournoiDto) {
        //Session session = null;
        //Transaction tx = null;
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = EntityManagerHolder.getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Tournoi tournoi = new Tournoi();
            tournoi.setId(tournoiDto.getId());
            tournoi.setNom(tournoiDto.getNom());
            tournoi.setCode(tournoiDto.getCode());
            tournoiRepositoryImpl.create(tournoi);
            tx.commit();
            System.out.println("Tournoi ajouté");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public void update(TournoiDto tournoiDto) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Tournoi tournoi = new Tournoi();
            tournoi.setId(tournoiDto.getId());
            tournoi.setNom(tournoiDto.getNom());
            tournoi.setCode(tournoiDto.getCode());
            tournoiRepositoryImpl.update(tournoi);

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
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = EntityManagerHolder.getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            tournoiRepositoryImpl.delete(id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
