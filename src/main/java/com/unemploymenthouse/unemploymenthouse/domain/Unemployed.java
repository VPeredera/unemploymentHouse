package com.unemploymenthouse.unemploymenthouse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "unemployed")
public class Unemployed implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unemployed")
    private Integer idUnemployed;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "birthday")
    private java.sql.Date birthday;

    @Column(name = "place_of_residence")
    private String placeOfResidence;

    @Column(name = "criminal_record")
    private String criminalRecord;

    @Column(name = "contact_information")
    private String contactInformation;

    @Column(name = "registration_date")
    private java.sql.Date registrationDate;

    @Column(name = "status")
    private String status;

    @ManyToMany(mappedBy = "unemployedReeduc", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Reeducation> reeducation = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "unemployed_profession",
            joinColumns = {
                    @JoinColumn(name = "id_unemployed", referencedColumnName = "id_unemployed",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_spec", referencedColumnName = "id_spec",
                            nullable = false, updatable = false)})
    private Set<Specialty> specialties = new HashSet<>();

    @OneToMany(mappedBy = "unemployedResume", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Resume> resumes;

    @OneToMany(mappedBy = "unemployedBenefit", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<UnemploymentBenefits> benefits;

    @OneToMany(mappedBy = "unemployedOffer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Offers> offersUnemployed;

    public Set<Reeducation> getReeducation() {
        return reeducation;
    }

    public void setReeducation(Set<Reeducation> reeducation) {
        this.reeducation = reeducation;
    }

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    public void addSpecialty(Specialty specialty){
        this.specialties.add(specialty);
    }

    public void removeSpecialty(Specialty specialty){
        this.specialties.remove(specialty);
    }

    public Integer getIdUnemployed() {
        return this.idUnemployed;
    }

    public void setIdUnemployed(Integer idUnemployed) {
        this.idUnemployed = idUnemployed;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public java.sql.Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
    }

    public String getPlaceOfResidence() {
        return this.placeOfResidence;
    }

    public void setPlaceOfResidence(String placeOfResidence) {
        this.placeOfResidence = placeOfResidence;
    }

    public String getCriminalRecord() {
        return this.criminalRecord;
    }

    public void setCriminalRecord(String criminalRecord) {
        this.criminalRecord = criminalRecord;
    }

    public String getContactInformation() {
        return this.contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public java.sql.Date getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(java.sql.Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unemployed that = (Unemployed) o;
        return idUnemployed.equals(that.idUnemployed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUnemployed);
    }

    @Override
    public String toString() {
        return this.fullName;
    }
}
