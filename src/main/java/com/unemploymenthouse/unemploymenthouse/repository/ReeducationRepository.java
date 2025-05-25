package com.unemploymenthouse.unemploymenthouse.repository;

import com.unemploymenthouse.unemploymenthouse.domain.Reeducation;
import com.unemploymenthouse.unemploymenthouse.query.ReeducationAmount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface ReeducationRepository extends CrudRepository<Reeducation, Integer> {
    Long countByIdReeduc(Integer id);

    @Transactional
    @Query("SELECT new com.unemploymenthouse.unemploymenthouse.query.ReeducationAmount(r.educInstitution, COUNT(r.idReeduc)) " +
            "FROM Reeducation AS r GROUP BY r.educInstitution")
    List<ReeducationAmount> findAmountReeducation();
}
