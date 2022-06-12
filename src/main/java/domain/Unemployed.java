package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "unemployed")
public class Unemployed implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unemployed")
    private Integer idUnemployed;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "birthday")
    private java.sql.Date birthday;

    @Column(name = "place_of_residence")
    private String placeOfResidence;

    @Column(name = "criminal_record")
    private String criminalRecord;

    @Column(name = "contact_information")
    private String contactInformation;

    @Column(name = "registration_date")
    private java.sql.Date registrationDate;

    @Column(name = "status")
    private String status;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "unemployed_at_retraining",
            joinColumns = {
                    @JoinColumn(name = "id_unemployed", referencedColumnName = "id_unemployed",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_reeduc", referencedColumnName = "id_reeduc",
                            nullable = false, updatable = false)})
    private Set<Reeducation> reeducations = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "unemployed_profession",
            joinColumns = {
                    @JoinColumn(name = "id_unemployed", referencedColumnName = "id_unemployed",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_spec", referencedColumnName = "id_spec",
                            nullable = false, updatable = false)})
    private Set<Specialty> specialties = new HashSet<>();

    @OneToMany(mappedBy = "unemployed", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Resume> resumes;

    @OneToMany(mappedBy = "unemployed", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<UnemploymentBenefits> benefits;

    @OneToMany(mappedBy = "unemployed", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Offers> offersUnemployed;

    public Integer getIdUnemployed() {
        return this.idUnemployed;
    }

    public void setIdUnemployed(Integer idUnemployed) {
        this.idUnemployed = idUnemployed;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public java.sql.Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
    }

    public String getPlaceOfResidence() {
        return this.placeOfResidence;
    }

    public void setPlaceOfResidence(String placeOfResidence) {
        this.placeOfResidence = placeOfResidence;
    }

    public String getCriminalRecord() {
        return this.criminalRecord;
    }

    public void setCriminalRecord(String criminalRecord) {
        this.criminalRecord = criminalRecord;
    }

    public String getContactInformation() {
        return this.contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public java.sql.Date getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(java.sql.Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}