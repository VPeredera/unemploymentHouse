package com.unemploymenthouse.unemploymenthouse.repository;

import com.unemploymenthouse.unemploymenthouse.domain.Resume;
import org.springframework.data.repository.CrudRepository;

public interface ResumeRepository extends CrudRepository<Resume, Integer> {
    public Long countByIdResume(Integer id);
}
