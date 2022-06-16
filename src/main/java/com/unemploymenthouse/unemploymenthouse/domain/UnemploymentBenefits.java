package com.unemploymenthouse.unemploymenthouse.domain;

import org.joda.money.Money;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "unemployment_benefits")
public class UnemploymentBenefits implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_benefit")
    private Integer idBenefit;

    @Column(name = "date_payment")
    private java.sql.Date datePayment;

    @Column(name = "days_on_record")
    private int daysOnRecord;

    @Column(name = "payment_amount")
    private double paymentAmount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_unemployed", nullable = false)
    private Unemployed unemployedBenefit;

    public Unemployed getUnemployedBenefit() {
        return unemployedBenefit;
    }

    public void setUnemployedBenefit(Unemployed unemployedBenefit) {
        this.unemployedBenefit = unemployedBenefit;
    }

    public Integer getIdBenefit() {
        return this.idBenefit;
    }

    public void setIdBenefit(Integer idBenefit) {
        this.idBenefit = idBenefit;
    }

    public java.sql.Date getDatePayment() {
        return this.datePayment;
    }

    public void setDatePayment(java.sql.Date datePayment) {
        this.datePayment = datePayment;
    }

    public int getDaysOnRecord() {
        return this.daysOnRecord;
    }

    public void setDaysOnRecord(int daysOnRecord) {
        this.daysOnRecord = daysOnRecord;
    }

    public double getPaymentAmount() {
        return this.paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
