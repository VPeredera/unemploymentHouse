package com.unemploymenthouse.unemploymenthouse.repository;

import com.unemploymenthouse.unemploymenthouse.domain.UnemploymentBenefits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface BenefitsRepository extends CrudRepository<UnemploymentBenefits, Integer> {
    Long countByIdBenefit(Integer id);

    @Transactional
    @Query(value = "SELECT SUM(unemployment_benefits.payment_amount) FROM unemployment_benefits " +
            "WHERE EXTRACT(MONTH FROM unemployment_benefits.date_payment) = EXTRACT(MONTH FROM now())-1", nativeQuery = true)
    Double findSum();
}