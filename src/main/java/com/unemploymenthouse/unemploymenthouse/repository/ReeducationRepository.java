package com.unemploymenthouse.unemploymenthouse.repository;

import com.unemploymenthouse.unemploymenthouse.domain.Reeducation;
import org.springframework.data.repository.CrudRepository;

public interface ReeducationRepository extends CrudRepository<Reeducation, Integer> {
    public Long countByIdReeduc(Integer id);
}
