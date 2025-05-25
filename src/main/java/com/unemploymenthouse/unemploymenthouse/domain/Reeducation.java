package com.unemploymenthouse.unemploymenthouse.domain;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reeducation")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"unemployedReeduc", "specialtyReeducation"})
public class Reeducation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reeduc")
    private Integer idReeduc;

    @Column(name = "educ_institution")
    private String educInstitution;

    @Column(name = "start_date")
    private java.sql.Date startDate;

    @Column(name = "end_date")
    private java.sql.Date endDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "unemployed_at_retraining",
            joinColumns = {
                    @JoinColumn(name = "id_reeduc", referencedColumnName = "id_reeduc",
                            nullable = false, updatable = false, insertable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_unemployed", referencedColumnName = "id_unemployed",
                            nullable = false, updatable = false, insertable = false)})
    private Set<Unemployed> unemployedReeduc = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_spec", nullable = false)
    private Specialty specialtyReeducation;

    @Override
    public String toString() {
        return "Reeducation{" +
                "idReeduc=" + idReeduc +
                ", educInstitution='" + educInstitution + '\'' +
                '}';
    }
}
