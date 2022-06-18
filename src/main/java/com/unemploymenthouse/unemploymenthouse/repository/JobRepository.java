package com.unemploymenthouse.unemploymenthouse.repository;

import com.unemploymenthouse.unemploymenthouse.domain.Jobs;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Jobs, Integer> {
    public Long countByIdJob(Integer id);
}
