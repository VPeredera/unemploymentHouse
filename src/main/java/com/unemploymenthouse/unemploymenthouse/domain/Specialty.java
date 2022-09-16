package com.unemploymenthouse.unemploymenthouse.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "specialty")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"unemployedSpec", "reeducationSpecialty", "jobsSpecialty"})
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

    @ManyToMany(mappedBy = "specialties", fetch = FetchType.LAZY)
    private Set<Unemployed> unemployedSpec = new HashSet<>();

    @OneToMany(mappedBy = "specialtyReeducation", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Reeducation> reeducationSpecialty;

    @OneToMany(mappedBy = "specialtyJobs", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Jobs> jobsSpecialty;

    public Specialty(Integer idSpec){
        this.idSpec = idSpec;
    }

    @Override
    public String toString() {
        return this.specialtyName;
    }
}
