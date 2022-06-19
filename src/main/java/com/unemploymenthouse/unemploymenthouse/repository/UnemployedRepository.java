package com.unemploymenthouse.unemploymenthouse.repository;

import com.unemploymenthouse.unemploymenthouse.domain.Unemployed;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UnemployedRepository extends CrudRepository<Unemployed, Integer> {
    public Long countByIdUnemployed(Integer id);

    @Transactional
    @Query(value = "SELECT * FROM unemployed WHERE unemployed.full_name LIKE ?1%", nativeQuery = true)
    List<Unemployed> findUnemployedByLetter(@Param("fullName") String fullName);
}
