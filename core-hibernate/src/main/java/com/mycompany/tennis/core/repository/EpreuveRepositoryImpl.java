package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.entity.Epreuve;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EpreuveRepositoryImpl {

    // cette class utilise que JPA
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
}
