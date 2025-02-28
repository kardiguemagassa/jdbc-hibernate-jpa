package com.company.tennis.core.entity;

public class Epreuve {

    private Long id;
    private Short annee;
    private Tournoi tournoi;
    private Character typeEperuve;

    public Character getTypeEperuve() {
        return typeEperuve;
    }

    public void setTypeEperuve(Character typeEperuve) {
        this.typeEperuve = typeEperuve;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getAnnee() {
        return annee;
    }

    public void setAnnee(Short annee) {
        this.annee = annee;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }
}
