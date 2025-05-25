package com.unemploymenthouse.unemploymenthouse.domain;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "offers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
