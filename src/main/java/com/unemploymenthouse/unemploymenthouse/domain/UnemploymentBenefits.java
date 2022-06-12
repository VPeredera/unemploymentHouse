package com.unemploymenthouse.unemploymenthouse.domain;

import com.unemploymenthouse.unemploymenthouse.domain.Unemployed;
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

//    @Column(name = "id_unemployed")
//    private Integer idUnemployed;

    @Column(name = "date_payment")
    private java.sql.Date datePayment;

    @Column(name = "days_on_record")
    private int daysOnRecord;

    @Column(name = "payment_amount")
    private Money paymentAmount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_unemployed", nullable = false)
    private Unemployed unemployedBenefit;

    public Integer getIdBenefit() {
        return this.idBenefit;
    }

    public void setIdBenefit(Integer idBenefit) {
        this.idBenefit = idBenefit;
    }

//    public Integer getIdUnemployed() {
//        return this.idUnemployed;
//    }
//
//    public void setIdUnemployed(Integer idUnemployed) {
//        this.idUnemployed = idUnemployed;
//    }

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

    public Money getPaymentAmount() {
        return this.paymentAmount;
    }

    public void setPaymentAmount(Money paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
