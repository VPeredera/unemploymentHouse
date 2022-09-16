package com.unemploymenthouse.unemploymenthouse.repository;

import com.unemploymenthouse.unemploymenthouse.domain.Specialty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface SpecialtyRepository extends CrudRepository<Specialty, Integer> {
    Long countByIdSpec(Integer id);

    @Transactional
    @Query(value = "SELECT * FROM specialty WHERE specialty.specialty_name LIKE %?1%", nativeQuery = true)
    List<Specialty> findSpecialtyByPart(@Param("wordPart") String wordPart);
}
