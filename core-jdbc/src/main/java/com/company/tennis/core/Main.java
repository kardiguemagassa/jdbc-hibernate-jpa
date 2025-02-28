package com.company.tennis.core;

import com.company.tennis.core.entity.Joueur;
import com.company.tennis.core.repository.JoueurRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        JoueurRepositoryImpl joueurRepository = new JoueurRepositoryImpl();

        List<Joueur> liste = joueurRepository.list();
        for (Joueur joueur : liste) {
            System.out.println(joueur.getPrenom() + " : " + joueur.getNom());
        }

       /*
        Joueur joueur = joueurRepository.getById(10L);
        System.out.println(joueur.getNom() + " " + joueur.getPrenom());
        System.out.println();*/

        /*Joueur noah = new Joueur();
        noah.setNom("Noah");
        noah.setPrenom("Yannik 1");
        noah.setSexe('H');
        joueurRepository.create(noah);
        //System.out.println(noah.getNom() + " " + noah.getPrenom());
        System.out.println("Id du Joueur créée : " + noah.getId());*/

        /*Joueur noahamodifier = joueurRepository.getById(44L);
        noahamodifier.setPrenom("Yannick");
        joueurRepository.update(noahamodifier);*/

        //joueurRepository.delete(47L);











    }

}
