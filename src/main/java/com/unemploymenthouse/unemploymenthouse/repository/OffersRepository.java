package com.unemploymenthouse.unemploymenthouse.repository;

import com.unemploymenthouse.unemploymenthouse.domain.Offers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface OffersRepository extends CrudRepository<Offers, Integer> {
    Long countByIdOffer(Integer id);

    @Transactional
    @Query(value = "SELECT COUNT(offers.id_offer) FROM offers " +
            "WHERE EXTRACT('week' FROM offers.date_offer) = EXTRACT('week' FROM now())", nativeQuery = true)
    int findCount();
}
