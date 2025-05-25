package com.unemploymenthouse.unemploymenthouse.service;

import com.unemploymenthouse.unemploymenthouse.domain.Specialty;
import com.unemploymenthouse.unemploymenthouse.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
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

    public Specialty get(Integer id) throws EntityNotFoundException {
        Optional<Specialty> result = specialtyRepository.findById(id);
        try{
            if(result.isPresent()){
                return result.get();
            }
            throw new EntityNotFoundException("Немає спеціальності з ID: " + id);
        } catch (EntityNotFoundException e){
            throw new RuntimeException();
        }
    }

    public void delete(Integer id) throws EntityNotFoundException {
        Long count = specialtyRepository.countByIdSpec(id);
        if(count == null || count == 0){
            throw new EntityNotFoundException("Немає спеціальності з ID: " + id);
        }
        specialtyRepository.deleteById(id);
    }

    public List<Specialty> getSpecialtyByPart(String wordPart){
        return specialtyRepository.findSpecialtyByPart(wordPart);
    }
}
