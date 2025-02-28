package com.company.tennis.core;

import com.company.tennis.core.entity.Joueur;
import com.company.tennis.core.repository.JoueurRepositoryImpl;
import com.company.tennis.core.service.JoueurService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        JoueurService joueurService = new JoueurService();

        List<Joueur> liste = joueurService.list();
        for (Joueur joueur : liste) {
            System.out.println(joueur.getPrenom() + " : " + joueur.getNom());
        }

       /*
        Joueur joueur = joueurService.getById(10L);
        System.out.println(joueur.getNom() + " " + joueur.getPrenom());
        System.out.println();*/

        /*Joueur noah = new Joueur();
        noah.setNom("Noah");
        noah.setPrenom("Yannik 1");
        noah.setSexe('H');
        joueurService.create(noah);
        //System.out.println(noah.getNom() + " " + noah.getPrenom());
        System.out.println("Id du Joueur créée : " + noah.getId());*/

        /*Joueur noahamodifier = joueurService.getById(44L);
        noahamodifier.setPrenom("Yannick");
        joueurRepository.update(noahamodifier);*/

        //joueurService.delete(47L);











    }

}
