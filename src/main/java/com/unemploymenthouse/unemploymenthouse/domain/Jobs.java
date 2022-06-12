package com.unemploymenthouse.unemploymenthouse.domain;

import org.joda.money.Money;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "jobs")
public class Jobs implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_job")
    private Integer idJob;

//    @Column(name = "id_employer")
//    private Integer idEmployer;
//
//    @Column(name = "id_spec")
//    private Integer idSpec;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "requirements")
    private String requirements;

    @Column(name = "position_description")
    private String positionDescription;

    @Column(name = "salary")
    private Money salary;

    @Column(name = "date_of_vacancy")
    private java.sql.Date dateOfVacancy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_spec", nullable = false)
    private Specialty specialtyJobs;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_employer", nullable = false)
    private Employer employerJobs;

    @OneToMany(mappedBy = "jobsOffer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Offers> offersJob;

    public Integer getIdJob() {
        return this.idJob;
    }

    public void setIdJob(Integer idJob) {
        this.idJob = idJob;
    }

//    public Integer getIdEmployer() {
//        return this.idEmployer;
//    }
//
//    public void setIdEmployer(Integer idEmployer) {
//        this.idEmployer = idEmployer;
//    }
//
//    public Integer getIdSpec() {
//        return this.idSpec;
//    }
//
//    public void setIdSpec(Integer idSpec) {
//        this.idSpec = idSpec;
//    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getRequirements() {
        return this.requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getPositionDescription() {
        return this.positionDescription;
    }

    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }

    public Money getSalary() {
        return this.salary;
    }

    public void setSalary(Money salary) {
        this.salary = salary;
    }

    public java.sql.Date getDateOfVacancy() {
        return this.dateOfVacancy;
    }

    public void setDateOfVacancy(java.sql.Date dateOfVacancy) {
        this.dateOfVacancy = dateOfVacancy;
    }
}
