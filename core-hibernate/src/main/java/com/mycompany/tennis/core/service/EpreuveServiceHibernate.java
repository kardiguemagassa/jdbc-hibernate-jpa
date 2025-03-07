package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.EpreuveLightDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class EpreuveServiceHibernate {

    private final EpreuveRepositoryImpl epreuveRepositoryImpl;

    public EpreuveServiceHibernate() {
        this.epreuveRepositoryImpl = new EpreuveRepositoryImpl();
    }

    public List<EpreuveFullDto> getListEpreuve(String codeTournoi) {

        EntityManager em = null;
        EntityTransaction tx = null;
        List<EpreuveFullDto> epreuveFullDtos = new ArrayList<>();

        try {

            em = EntityManagerHolder.getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            List<Epreuve> epreuves = epreuveRepositoryImpl.listJPA(codeTournoi);

            for (Epreuve epreuve : epreuves) {
                final EpreuveFullDto epreuveFullDto = new EpreuveFullDto();
                epreuveFullDto.setId(epreuve.getId());
                epreuveFullDto.setAnnee(epreuve.getAnnee());
                epreuveFullDto.setTypeEpreuve(epreuve.getTypeEpreuve());
                TournoiDto tournoiDto = new TournoiDto();
                tournoiDto.setId(epreuve.getTournoi().getId());
                tournoiDto.setNom(epreuve.getTournoi().getNom());
                tournoiDto.setCode(epreuve.getTournoi().getCode());
                epreuveFullDto.setTournoi(tournoiDto);
                epreuveFullDtos.add(epreuveFullDto);
            }

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
        return epreuveFullDtos;
    }

    public EpreuveFullDto getEpreuveDetaillee(Long id) {
        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;
        EpreuveFullDto epreuveFullDto = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepositoryImpl.getById(id);

            epreuveFullDto = new EpreuveFullDto();
            epreuveFullDto.setId(epreuve.getId());
            epreuveFullDto.setAnnee(epreuve.getAnnee());
            epreuveFullDto.setTypeEpreuve(epreuve.getTypeEpreuve());
            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(epreuve.getTournoi().getId());
            tournoiDto.setNom(epreuve.getTournoi().getNom());
            tournoiDto.setCode(epreuve.getTournoi().getCode());
            epreuveFullDto.setTournoi(tournoiDto);

            epreuveFullDto.setParticitants(new HashSet<>());

            for (Joueur joueur: epreuve.getParticipants()) {
                final JoueurDto joueurDto = new JoueurDto();
                joueurDto.setId(joueur.getId());
                joueurDto.setNom(joueur.getNom());
                joueurDto.setPrenom(joueur.getPrenom());
                joueurDto.setSexe(joueur.getSexe());
                epreuveFullDto.getParticitants().add(joueurDto);
            }

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

        return epreuveFullDto;
    }

    public EpreuveLightDto getByIdSansTournoi(Long id) {
        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;
        EpreuveLightDto epreuveLightDto = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepositoryImpl.getById(id);

            epreuveLightDto = new EpreuveLightDto();
            epreuveLightDto.setId(epreuve.getId());
            epreuveLightDto.setAnnee(epreuve.getAnnee());
            epreuveLightDto.setTypeEpreuve(epreuve.getTypeEpreuve());

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

        return epreuveLightDto;
    }

    public void create(Tournoi tournoi) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            //epreuveRepositoryImpl.(tournoi);  // Ajouter le tournoi via le repository
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
            //epreuveRepositoryImpl.update(tournoi);
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
            //epreuveRepositoryImpl.delete(id);
            tx.commit();
            System.out.println("Tournoi supprimé");
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
