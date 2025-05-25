package com.unemploymenthouse.unemploymenthouse.domain;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "unemployment_benefits")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
