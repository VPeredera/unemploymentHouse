package com.unemploymenthouse.unemploymenthouse.repository;

import com.unemploymenthouse.unemploymenthouse.domain.Unemployed;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UnemployedRepository extends CrudRepository<Unemployed, Integer> {
    public Long countByIdUnemployed(Integer id);
}
