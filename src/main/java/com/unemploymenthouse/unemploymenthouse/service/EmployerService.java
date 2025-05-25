package com.unemploymenthouse.unemploymenthouse.service;

import com.unemploymenthouse.unemploymenthouse.domain.Employer;
import com.unemploymenthouse.unemploymenthouse.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployerService {
    private final EmployerRepository employerRepository;

    @Autowired
    public EmployerService(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    public List<Employer> listAll() {
        return (List<Employer>) employerRepository.findAll();
    }

    public void save(Employer employer){
        employerRepository.save(employer);
    }

    public Employer get(Integer id) throws EntityNotFoundException {
        Optional<Employer> result = employerRepository.findById(id);
        try {
            if(result.isPresent()){
                return result.get();
            }
            throw new EntityNotFoundException("Немає роботодавців з ID: " + id);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    public void delete(Integer id) throws EntityNotFoundException {
        Long count = employerRepository.countByIdEmployer(id);
        if(count == null || count == 0){
            throw new EntityNotFoundException("Немає роботодавців з ID: " + id);
        }
        employerRepository.deleteById(id);
    }

    public List<Employer> getNotBigger(Integer amount){
        return employerRepository.findNotBigger(amount);
    }
}
