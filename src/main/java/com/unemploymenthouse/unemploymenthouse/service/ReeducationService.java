package com.unemploymenthouse.unemploymenthouse.service;

import com.unemploymenthouse.unemploymenthouse.domain.Reeducation;
import com.unemploymenthouse.unemploymenthouse.query.ReeducationAmount;
import com.unemploymenthouse.unemploymenthouse.repository.ReeducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReeducationService {
    @Autowired private ReeducationRepository reeducationRepository;

    public static <E> List<E> makeCollection(Iterable<E> iter) {
        List<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }

    public List<Reeducation> listAll() {
        return (List<Reeducation>) reeducationRepository.findAll();
    }

    public void save(Reeducation reeducation){
        reeducationRepository.save(reeducation);
    }

    public Reeducation get(Integer id) throws EntityNotFoundException {
        Optional<Reeducation> result = reeducationRepository.findById(id);
        try{
            if(result.isPresent()){
                return result.get();
            }
            throw new EntityNotFoundException("Немає перенавчання з ID: " + id);
        } catch (EntityNotFoundException e){
            throw new RuntimeException();
        }
    }

    public void delete(Integer id) throws EntityNotFoundException {
        Long count = reeducationRepository.countByIdReeduc(id);
        if(count == null || count == 0){
            throw new EntityNotFoundException("Немає перенавчання з ID: " + id);
        }
        reeducationRepository.deleteById(id);
    }

    public List<ReeducationAmount> getAmountReeducation(){
        return reeducationRepository.findAmountReeducation();
    }
}
