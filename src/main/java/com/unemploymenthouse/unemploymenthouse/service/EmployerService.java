package com.unemploymenthouse.unemploymenthouse.service;

import com.unemploymenthouse.unemploymenthouse.domain.Employer;
import com.unemploymenthouse.unemploymenthouse.domain.Unemployed;
import com.unemploymenthouse.unemploymenthouse.exception.EmployerNotFoundException;
import com.unemploymenthouse.unemploymenthouse.exception.UnemployedNotFoundException;
import com.unemploymenthouse.unemploymenthouse.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerService {
    @Autowired private EmployerRepository employerRepository;

    public List<Employer> listAll() {
        return (List<Employer>) employerRepository.findAll();
    }

    public void save(Employer employer){
        employerRepository.save(employer);
    }

    public Employer get(Integer id) throws EmployerNotFoundException {
        Optional<Employer> result = employerRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new EmployerNotFoundException("Немає роботодавців з ID: " + id);
    }

    public void delete(Integer id) throws EmployerNotFoundException {
        Long count = employerRepository.countByIdEmployer(id);
        if(count == null || count == 0){
            throw new EmployerNotFoundException("Немає роботодавців з ID: " + id);
        }
        employerRepository.deleteById(id);
    }
}
