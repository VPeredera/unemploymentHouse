package com.unemploymenthouse.unemploymenthouse.repository;

import com.unemploymenthouse.unemploymenthouse.domain.Employer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface EmployerRepository extends CrudRepository<Employer, Integer> {
    Long countByIdEmployer(Integer id);

    @Transactional
    @Query(value = "SELECT * FROM employer AS r WHERE EXISTS" +
            "(SELECT * FROM employer AS d WHERE r.id_employer = d.id_employer AND d.job_vacancies < ?1)", nativeQuery = true)
    List<Employer> findNotBigger(@Param("amount") Integer amount);
}
