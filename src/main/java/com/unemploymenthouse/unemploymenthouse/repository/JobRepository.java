package com.unemploymenthouse.unemploymenthouse.repository;

import com.unemploymenthouse.unemploymenthouse.domain.Jobs;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface JobRepository extends CrudRepository<Jobs, Integer> {
    public Long countByIdJob(Integer id);

    @Transactional
    @Query(value = "SELECT * FROM jobs WHERE jobs.id_employer IN " +
            "(SELECT employer.id_employer FROM employer WHERE employer.company_name = ?1 )", nativeQuery = true)
    List<Jobs> findJobsByCompany(@Param("companyName") String companyName);
}
