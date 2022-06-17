package com.unemploymenthouse.unemploymenthouse.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "specialty")
public class Specialty implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_spec")
    private Integer idSpec;

    @Column(name = "specialty_name")
    private String specialtyName;

    @Column(name = "field_of_knowledge")
    private String fieldOfKnowledge;

    @Column(name = "code_field")
    private int codeField;

    @Column(name = "specialty_code")
    private int specialtyCode;

    public Specialty(){}

    public Specialty(Integer idSpec){
        this.idSpec = idSpec;
    }

    @ManyToMany(mappedBy = "specialties", fetch = FetchType.LAZY)
    private Set<Unemployed> unemployedSpec = new HashSet<>();

    @OneToMany(mappedBy = "specialtyReeducation", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Reeducation> reeducationSpecialty;

    @OneToMany(mappedBy = "specialtyJobs", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Jobs> jobsSpecialty;

    public Set<Reeducation> getReeducationSpecialty() {
        return reeducationSpecialty;
    }

    public void setReeducationSpecialty(Set<Reeducation> reeducationSpecialty) {
        this.reeducationSpecialty = reeducationSpecialty;
    }

    public Set<Unemployed> getUnemployedSpec() {
        return unemployedSpec;
    }

    public void setUnemployedSpec(Set<Unemployed> unemployedSpec) {
        this.unemployedSpec = unemployedSpec;
    }

    public Integer getIdSpec() {
        return this.idSpec;
    }

    public void setIdSpec(Integer idSpec) {
        this.idSpec = idSpec;
    }

    public String getSpecialtyName() {
        return this.specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public String getFieldOfKnowledge() {
        return this.fieldOfKnowledge;
    }

    public void setFieldOfKnowledge(String fieldOfKnowledge) {
        this.fieldOfKnowledge = fieldOfKnowledge;
    }

    public int getCodeField() {
        return this.codeField;
    }

    public void setCodeField(int codeField) {
        this.codeField = codeField;
    }

    public int getSpecialtyCode() {
        return this.specialtyCode;
    }

    public void setSpecialtyCode(int specialtyCode) {
        this.specialtyCode = specialtyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialty specialty = (Specialty) o;
        return idSpec.equals(specialty.idSpec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSpec);
    }

    @Override
    public String toString() {
        return this.specialtyName;
    }
}
