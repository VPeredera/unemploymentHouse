package com.unemploymenthouse.unemploymenthouse.service;

import com.unemploymenthouse.unemploymenthouse.domain.Offers;
import com.unemploymenthouse.unemploymenthouse.repository.OffersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class OffersService{
    @Autowired public OffersRepository offersRepository;

    public List<Offers> listAll() {
        return (List<Offers>) offersRepository.findAll();
    }

    public void save(Offers offer){
        offersRepository.save(offer);
    }

    public Offers get(Integer id) throws EntityNotFoundException {
        Optional<Offers> result = offersRepository.findById(id);
        try{
            if(result.isPresent()){
                return result.get();
            }
            throw new EntityNotFoundException("Немає пропозицій з ID: " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void delete(Integer id) throws EntityNotFoundException {
        Long count = offersRepository.countByIdOffer(id);
        if(count == null || count == 0){
            throw new EntityNotFoundException("Немає пропозицій з ID: " + id);
        }
        offersRepository.deleteById(id);
    }

    public int getCount(){
        return offersRepository.findCount();
    }
}
