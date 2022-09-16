package com.unemploymenthouse.unemploymenthouse.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "resume")
@Getter
@Setter
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
}
