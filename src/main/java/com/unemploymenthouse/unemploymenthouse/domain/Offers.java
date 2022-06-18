package com.unemploymenthouse.unemploymenthouse.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "offers")
public class Offers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_offer")
    private Integer idOffer;

    @Column(name = "date_offer")
    private java.sql.Date dateOffer;

    @Column(name = "reply_on_offer")
    private String replyOnOffer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_unemployed", nullable = false)
    private Unemployed unemployedOffer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_job", nullable = false)
    private Jobs jobsOffer;

    public Integer getIdOffer() {
        return this.idOffer;
    }

    public void setIdOffer(Integer idOffer) {
        this.idOffer = idOffer;
    }

    public Unemployed getUnemployedOffer() {
        return unemployedOffer;
    }

    public void setUnemployedOffer(Unemployed unemployedOffer) {
        this.unemployedOffer = unemployedOffer;
    }

    public Jobs getJobsOffer() {
        return jobsOffer;
    }

    public void setJobsOffer(Jobs jobsOffer) {
        this.jobsOffer = jobsOffer;
    }

    public java.sql.Date getDateOffer() {
        return this.dateOffer;
    }

    public void setDateOffer(java.sql.Date dateOffer) {
        this.dateOffer = dateOffer;
    }

    public String getReplyOnOffer() {
        return this.replyOnOffer;
    }

    public void setReplyOnOffer(String replyOnOffer) {
        this.replyOnOffer = replyOnOffer;
    }
}
