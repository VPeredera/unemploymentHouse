package com.unemploymenthouse.unemploymenthouse.repository;

import com.unemploymenthouse.unemploymenthouse.domain.UnemploymentBenefits;
import org.springframework.data.repository.CrudRepository;

public interface BenefitsRepository extends CrudRepository<UnemploymentBenefits, Integer> {
    public Long countByIdBenefit(Integer id);
}
