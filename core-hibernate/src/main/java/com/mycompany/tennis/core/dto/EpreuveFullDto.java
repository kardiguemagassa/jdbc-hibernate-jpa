package com.mycompany.tennis.core.dto;

import java.util.Set;

public class EpreuveFullDto {
    private Long id;
    private Short annee;
    private TournoiDto tournoi;
    private Character typeEpreuve;
    private Set<JoueurDto> particitants;
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
    public TournoiDto getTournoi() {
        return tournoi;
    }
    public void setTournoi(TournoiDto tournoi) {
        this.tournoi = tournoi;
    }
    public Character getTypeEpreuve() {
        return typeEpreuve;
    }
    public void setTypeEpreuve(Character typeEpreuve) {
        this.typeEpreuve = typeEpreuve;
    }
    public Set<JoueurDto> getParticitants() {
        return particitants;
    }
    public void setParticitants(Set<JoueurDto> particitants) {
        this.particitants = particitants;
    }
}
