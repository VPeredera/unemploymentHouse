package com.unemploymenthouse.unemploymenthouse.service;

import com.unemploymenthouse.unemploymenthouse.domain.Specialty;
import com.unemploymenthouse.unemploymenthouse.exception.SpecialtyNotFoundException;
import com.unemploymenthouse.unemploymenthouse.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialtyService {
    @Autowired
    private SpecialtyRepository specialtyRepository;

    public List<Specialty> listAll() {
        return (List<Specialty>) specialtyRepository.findAll();
    }

    public void save(Specialty specialty){
        specialtyRepository.save(specialty);
    }

    public Specialty get(Integer id) throws SpecialtyNotFoundException {
        Optional<Specialty> result = specialtyRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new SpecialtyNotFoundException("Немає спеціальності з ID: " + id);
    }

    public void delete(Integer id) throws SpecialtyNotFoundException {
        Long count = specialtyRepository.countByIdSpec(id);
        if(count == null || count == 0){
            throw new SpecialtyNotFoundException("Немає спеціальності з ID: " + id);
        }
        specialtyRepository.deleteById(id);
    }
}
