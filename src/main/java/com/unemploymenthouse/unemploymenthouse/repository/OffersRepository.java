package com.unemploymenthouse.unemploymenthouse.repository;

import com.unemploymenthouse.unemploymenthouse.domain.Offers;
import org.springframework.data.repository.CrudRepository;

public interface OffersRepository extends CrudRepository<Offers, Integer> {
    public Long countByIdOffer(Integer id);
}
