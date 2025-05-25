package com.unemploymenthouse.unemploymenthouse.domain;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jobs implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_job")
    private Integer idJob;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "requirements")
    private String requirements;

    @Column(name = "position_description")
    private String positionDescription;

    @Column(name = "salary")
    private double salary;

    @Column(name = "date_of_vacancy")
    private java.sql.Date dateOfVacancy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_spec", nullable = false)
    private Specialty specialtyJobs;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_employer", nullable = false)
    private Employer employerJobs;

    @OneToMany(mappedBy = "jobsOffer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Offers> offersJob;
}
