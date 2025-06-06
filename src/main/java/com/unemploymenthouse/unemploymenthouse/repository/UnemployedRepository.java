package com.unemploymenthouse.unemploymenthouse.repository;

import com.unemploymenthouse.unemploymenthouse.domain.Unemployed;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface UnemployedRepository extends CrudRepository<Unemployed, Integer> {
    Long countByIdUnemployed(Integer id);

    @Transactional
    @Query(value = "SELECT * FROM unemployed WHERE unemployed.full_name LIKE ?1%", nativeQuery = true)
    List<Unemployed> findUnemployedByLetter(@Param("fullName") String fullName);

    @Transactional
    @Query(value = "SELECT * FROM unemployed WHERE unemployed.registration_date BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Unemployed> findUnemployedByRegistration(@Param("date1") java.sql.Date date1, @Param("date2") java.sql.Date date2);

    @Transactional
    @Query(value = "SELECT * FROM unemployed WHERE unemployed.birthday <= ALL " +
            "(SELECT unemployed.birthday FROM unemployed)", nativeQuery = true)
    List<Unemployed> findOldest();
}
