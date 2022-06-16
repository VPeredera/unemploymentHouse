package com.unemploymenthouse.unemploymenthouse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unemploymenthouse.unemploymenthouse.domain.Specialty;
import com.unemploymenthouse.unemploymenthouse.domain.Unemployed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reeducation")
public class Reeducation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reeduc")
    private Integer idReeduc;

//    @Column(name = "id_spec")
//    private Integer idSpec;

    @Column(name = "educ_institution")
    private String educInstitution;

    @Column(name = "start_date")
    private java.sql.Date startDate;

    @Column(name = "end_date")
    private java.sql.Date endDate;

    @ManyToMany(mappedBy = "reeducations", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Unemployed> unemployedReeducation = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_spec", nullable = false)
    private Specialty specialtyReeducation;

    /**many to many methods*/
    public Set<Unemployed> getUnemployed(){
        return unemployedReeducation;
    }

    public void setUnemployed(Set<Unemployed> unemployedReeducation){
        this.unemployedReeducation = unemployedReeducation;
    }

    public Integer getIdReeduc() {
        return this.idReeduc;
    }

    public void setIdReeduc(Integer idReeduc) {
        this.idReeduc = idReeduc;
    }

//    public Integer getIdSpec() {
//        return this.idSpec;
//    }
//
//    public void setIdSpec(Integer idSpec) {
//        this.idSpec = idSpec;
//    }

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
}
