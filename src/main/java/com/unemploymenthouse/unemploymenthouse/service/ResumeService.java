package com.unemploymenthouse.unemploymenthouse.service;

import com.unemploymenthouse.unemploymenthouse.domain.Resume;
import com.unemploymenthouse.unemploymenthouse.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    public Resume get(Integer id) throws EntityNotFoundException {
        Optional<Resume> result = resumeRepository.findById(id);
        try{
            if(result.isPresent()){
                return result.get();
            }
            throw new EntityNotFoundException("Немає резюме з ID: " + id);
        } catch (EntityNotFoundException e){
            throw new RuntimeException();
        }
    }

    public void delete(Integer id) throws EntityNotFoundException {
        Long count = resumeRepository.countByIdResume(id);
        if(count == null || count == 0){
            throw new EntityNotFoundException("Немає резюме з ID: " + id);
        }
        resumeRepository.deleteById(id);
    }

    public List<Resume> getResumeByName(String fullName){
        return resumeRepository.findResumeByName(fullName);
    }
}
