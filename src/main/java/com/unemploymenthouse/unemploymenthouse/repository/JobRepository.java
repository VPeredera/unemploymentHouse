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
            "(SELECT employer.id_employer FROM employer WHERE employer.company_name = ?1) ORDER BY jobs.salary DESC", nativeQuery = true)
    List<Jobs> findJobsByCompany(@Param("companyName") String companyName);

    @Transactional
    @Query(value = "SELECT * FROM jobs WHERE jobs.salary BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Jobs> findJobsBySalary(@Param("salary1") double salary1, @Param("salary2") double salary2);

    @Transactional
    @Query(value = "SELECT * FROM jobs AS j WHERE j.salary = (SELECT MAX(k.salary) FROM jobs AS k WHERE j.id_spec = k.id_spec)", nativeQuery = true)
    List<Jobs> findMaxSalaryJob();
}
