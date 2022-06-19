package com.unemploymenthouse.unemploymenthouse.service;

import com.unemploymenthouse.unemploymenthouse.domain.Unemployed;
import com.unemploymenthouse.unemploymenthouse.exception.UnemployedNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unemploymenthouse.unemploymenthouse.repository.UnemployedRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UnemployedService {
    @Autowired private UnemployedRepository unemployedRepository;

    public static <E> List<E> makeCollection(Iterable<E> iter) {
        List<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }

    public List<Unemployed> listAll() {
        return (List<Unemployed>) unemployedRepository.findAll();
    }

    public void save(Unemployed unemployed){
        unemployedRepository.save(unemployed);
    }

    public Unemployed get(Integer id) throws UnemployedNotFoundException {
        Optional<Unemployed> result = unemployedRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UnemployedNotFoundException("Немає безробітних з ID: " + id);
    }

    public void delete(Integer id) throws UnemployedNotFoundException {
        Long count = unemployedRepository.countByIdUnemployed(id);
        if(count == null || count == 0){
            throw new UnemployedNotFoundException("Немає безробітних з ID: " + id);
        }
        unemployedRepository.deleteById(id);
    }

    public List<Unemployed> getUnemployedByLetter(String fullName){
        return unemployedRepository.findUnemployedByLetter(fullName);
    }

    public List<Unemployed> getUnemployedByRegistration(java.sql.Date date1, java.sql.Date date2){
        return unemployedRepository.findUnemployedByRegistration(date1, date2);
    }

    public List<Unemployed> getOldest(){
        return unemployedRepository.findOldest();
    }
}
