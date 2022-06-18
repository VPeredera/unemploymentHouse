package com.unemploymenthouse.unemploymenthouse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unemploymenthouse.unemploymenthouse.domain.Specialty;
import com.unemploymenthouse.unemploymenthouse.domain.Unemployed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "reeducation")
public class Reeducation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reeduc")
    private Integer idReeduc;

    @Column(name = "educ_institution")
    private String educInstitution;

    @Column(name = "start_date")
    private java.sql.Date startDate;

    @Column(name = "end_date")
    private java.sql.Date endDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "unemployed_at_retraining",
            joinColumns = {
                    @JoinColumn(name = "id_reeduc", referencedColumnName = "id_reeduc",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_unemployed", referencedColumnName = "id_unemployed",
                            nullable = false, updatable = false)})
    private Set<Unemployed> unemployedReeduc = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_spec", nullable = false)
    private Specialty specialtyReeducation;

    public void addUnemployed(Unemployed unemployed){
        this.unemployedReeduc.add(unemployed);
    }

    public void removeUnemployed(Unemployed unemployed){
        this.unemployedReeduc.remove(unemployed);
    }

    public Set<Unemployed> getUnemployedReeduc() {
        return unemployedReeduc;
    }

    public void setUnemployedReeduc(Set<Unemployed> unemployedReeduc) {
        this.unemployedReeduc = unemployedReeduc;
    }

    public Specialty getSpecialtyReeducation() {
        return specialtyReeducation;
    }

    public void setSpecialtyReeducation(Specialty specialtyReeducation) {
        this.specialtyReeducation = specialtyReeducation;
    }

    public Integer getIdReeduc() {
        return this.idReeduc;
    }

    public void setIdReeduc(Integer idReeduc) {
        this.idReeduc = idReeduc;
    }

    public String getEducInstitution() {
        return this.educInstitution;
    }

    public void setEducInstitution(String educInstitution) {
        this.educInstitution = educInstitution;
    }

    public java.sql.Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(java.sql.Date startDate) {
        this.startDate = startDate;
    }

    public java.sql.Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(java.sql.Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reeducation that = (Reeducation) o;
        return idReeduc.equals(that.idReeduc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReeduc);
    }

    @Override
    public String toString() {
        return "Reeducation{" +
                "idReeduc=" + idReeduc +
                ", educInstitution='" + educInstitution + '\'' +
                '}';
    }
}
