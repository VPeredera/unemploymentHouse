package com.unemploymenthouse.unemploymenthouse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "unemployed")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"reeducation", "specialties", "resumes", "benefits", "offersUnemployed"})
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
                            nullable = false, updatable = false, insertable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_spec", referencedColumnName = "id_spec",
                            nullable = false, updatable = false, insertable = false)})
    private Set<Specialty> specialties = new HashSet<>();

    @OneToMany(mappedBy = "unemployedResume", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Resume> resumes;

    @OneToMany(mappedBy = "unemployedBenefit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UnemploymentBenefits> benefits;

    @OneToMany(mappedBy = "unemployedOffer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Offers> offersUnemployed;

    @Override
    public String toString() {
        return this.fullName;
    }
}
