package com.unemploymenthouse.unemploymenthouse.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "resume")
public class Resume implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resume")
    private Integer idResume;

    @Column(name = "date_record")
    private java.sql.Date dateRecord;

    @Column(name = "statements")
    private String statements;

    @Column(name = "more_details")
    private String moreDetails;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_unemployed", nullable = false)
    private Unemployed unemployedResume;

    public Unemployed getUnemployedResume() {
        return unemployedResume;
    }

    public void setUnemployedResume(Unemployed unemployedResume) {
        this.unemployedResume = unemployedResume;
    }

    public Integer getIdResume() {
        return this.idResume;
    }

    public void setIdResume(Integer idResume) {
        this.idResume = idResume;
    }


    public java.sql.Date getDateRecord() {
        return this.dateRecord;
    }

    public void setDateRecord(java.sql.Date dateRecord) {
        this.dateRecord = dateRecord;
    }

    public String getStatements() {
        return this.statements;
    }

    public void setStatements(String statements) {
        this.statements = statements;
    }

    public String getMoreDetails() {
        return this.moreDetails;
    }

    public void setMoreDetails(String moreDetails) {
        this.moreDetails = moreDetails;
    }
}
