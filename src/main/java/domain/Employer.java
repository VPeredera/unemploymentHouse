package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "employer")
public class Employer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employer")
    private Integer idEmployer;

    @Column(name = "employer_full_name")
    private String employerFullName;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "type_of_activity")
    private String typeOfActivity;

    @Column(name = "company_location")
    private String companyLocation;

    @Column(name = "employer_contact")
    private String employerContact;

    @Column(name = "job_vacancies")
    private int jobVacancies;

    @OneToMany(mappedBy = "employer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Jobs> jobsEmployer;

    public Integer getIdEmployer() {
        return this.idEmployer;
    }

    public void setIdEmployer(Integer idEmployer) {
        this.idEmployer = idEmployer;
    }

    public String getEmployerFullName() {
        return this.employerFullName;
    }

    public void setEmployerFullName(String employerFullName) {
        this.employerFullName = employerFullName;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTypeOfActivity() {
        return this.typeOfActivity;
    }

    public void setTypeOfActivity(String typeOfActivity) {
        this.typeOfActivity = typeOfActivity;
    }

    public String getCompanyLocation() {
        return this.companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public String getEmployerContact() {
        return this.employerContact;
    }

    public void setEmployerContact(String employerContact) {
        this.employerContact = employerContact;
    }

    public int getJobVacancies() {
        return this.jobVacancies;
    }

    public void setJobVacancies(int jobVacancies) {
        this.jobVacancies = jobVacancies;
    }
}
