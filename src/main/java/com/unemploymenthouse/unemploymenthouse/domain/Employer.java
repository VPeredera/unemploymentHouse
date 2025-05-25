package com.unemploymenthouse.unemploymenthouse.domain;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "employer")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employer")
    private Integer idEmployer;

    @Column(name = "employer_full_name")
    private String employerFullName;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "type_of_activity")
    private String typeOfActivity;

    @Column(name = "company_location")
    private String companyLocation;

    @Column(name = "employer_contact")
    private String employerContact;

    @Column(name = "job_vacancies")
    private int jobVacancies;

    @OneToMany(mappedBy = "employerJobs", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Jobs> jobsEmployer;
}
