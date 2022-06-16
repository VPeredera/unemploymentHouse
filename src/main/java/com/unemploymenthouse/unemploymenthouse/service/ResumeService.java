package com.unemploymenthouse.unemploymenthouse.service;

import com.unemploymenthouse.unemploymenthouse.domain.Resume;
import com.unemploymenthouse.unemploymenthouse.exception.ResumeNotFoundException;
import com.unemploymenthouse.unemploymenthouse.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResumeService {
    @Autowired private ResumeRepository resumeRepository;

    public List<Resume> listAll() {
        return (List<Resume>) resumeRepository.findAll();
    }

    public void save(Resume resume){
        resumeRepository.save(resume);
    }

    public Resume get(Integer id) throws ResumeNotFoundException {
        Optional<Resume> result = resumeRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new ResumeNotFoundException("Немає резюме з ID: " + id);
    }

    public void delete(Integer id) throws ResumeNotFoundException {
        Long count = resumeRepository.countByIdResume(id);
        if(count == null || count == 0){
            throw new ResumeNotFoundException("Немає резюме з ID: " + id);
        }
        resumeRepository.deleteById(id);
    }
}
