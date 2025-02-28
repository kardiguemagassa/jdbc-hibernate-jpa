package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import org.hibernate.Session;

public class JoueurRepositoryImpl {

    public JoueurRepositoryImpl() {}

    public Joueur getById(Long id) {

        Joueur joueur = null;
        Session session = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            joueur = session.get(Joueur.class, id);

            System.out.println("Joueur lu");

        } catch (Throwable t){
            t.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return joueur;
    }
}
