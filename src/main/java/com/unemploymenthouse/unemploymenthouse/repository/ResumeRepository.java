package com.unemploymenthouse.unemploymenthouse.repository;

import com.unemploymenthouse.unemploymenthouse.domain.Jobs;
import com.unemploymenthouse.unemploymenthouse.domain.Resume;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ResumeRepository extends CrudRepository<Resume, Integer> {
    Long countByIdResume(Integer id);

    @Transactional
    @Query(value = "SELECT * FROM resume WHERE resume.id_unemployed IN " +
            "(SELECT unemployed.id_unemployed FROM unemployed WHERE unemployed.full_name = ?1 )", nativeQuery = true)
    List<Resume> findResumeByName(@Param("companyName") String fullName);
}
