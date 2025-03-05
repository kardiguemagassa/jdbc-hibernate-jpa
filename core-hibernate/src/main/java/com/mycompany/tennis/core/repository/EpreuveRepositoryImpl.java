package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class EpreuveRepositoryImpl {


    public Epreuve getById(Long id) {
        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // Trouver l'épreuve par son identifiant
            epreuve = session.find(Epreuve.class, id);

            // Vérifier si l'épreuve est trouvée avant d'initialiser les associations
            if (epreuve != null) {
                // Initialiser explicitement les propriétés avec des relations en Lazy Loading
                Hibernate.initialize(epreuve.getTournoi());        // Initialiser le tournoi
                Hibernate.initialize(epreuve.getParticipants());   // Initialiser les participants
            }

            tx.commit();
            System.out.println("Epreuve lue");

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

        return epreuve;
    }


    /*// cette class utilise que JPA
    public Epreuve getById(Long id) {
        //La sesion qui faite la connection avec la base de données
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        Epreuve epreuve = em.find(Epreuve.class,id);
        System.out.println("L'epreuve a été lu");
        return epreuve;
    }

    public List<Epreuve> list(String codeTournoi) {

        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        // La requete HDL qui retourne la liste filté de joueur
        // JPA
        TypedQuery<Epreuve> query = em.createQuery("select e from Epreuve e join fetch e.tournoi where e.tournoi.code=?1",Epreuve.class);
        query.setParameter(1,codeTournoi);
        //cette method getResultList va retourner une liste java des epreuves
        List<Epreuve> epreuves = query.getResultList();
        System.out.println("Les epreuves ont été lus");
        return epreuves;
    }

     */
}
