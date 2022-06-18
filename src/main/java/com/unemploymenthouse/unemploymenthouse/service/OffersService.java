package com.unemploymenthouse.unemploymenthouse.service;

import com.unemploymenthouse.unemploymenthouse.domain.Jobs;
import com.unemploymenthouse.unemploymenthouse.domain.Offers;
import com.unemploymenthouse.unemploymenthouse.exception.JobNotFoundException;
import com.unemploymenthouse.unemploymenthouse.exception.OfferNotFoundException;
import com.unemploymenthouse.unemploymenthouse.repository.OffersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Offers get(Integer id) throws OfferNotFoundException {
        Optional<Offers> result = offersRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new OfferNotFoundException("Немає пропозицій з ID: " + id);
    }

    public void delete(Integer id) throws OfferNotFoundException {
        Long count = offersRepository.countByIdOffer(id);
        if(count == null || count == 0){
            throw new OfferNotFoundException("Немає пропозицій з ID: " + id);
        }
        offersRepository.deleteById(id);
    }
}
