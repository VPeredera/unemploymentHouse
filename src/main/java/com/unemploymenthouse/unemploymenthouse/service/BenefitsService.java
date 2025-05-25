package com.unemploymenthouse.unemploymenthouse.service;

import com.unemploymenthouse.unemploymenthouse.domain.UnemploymentBenefits;
import com.unemploymenthouse.unemploymenthouse.repository.BenefitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class BenefitsService {
    private final BenefitsRepository benefitsRepository;

    @Autowired
    public BenefitsService(BenefitsRepository benefitsRepository) {
        this.benefitsRepository = benefitsRepository;
    }

    public List<UnemploymentBenefits> listAll() {
        return (List<UnemploymentBenefits>) benefitsRepository.findAll();
    }

    public void save(UnemploymentBenefits benefits){
        benefitsRepository.save(benefits);
    }

    public UnemploymentBenefits get(Integer id) throws EntityNotFoundException {
        Optional<UnemploymentBenefits> result = benefitsRepository.findById(id);
        try{
            if(result.isPresent()){
                return result.get();
            } throw new EntityNotFoundException("Немає виплат з ID: " + id);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    public void delete(Integer id) throws EntityNotFoundException {
        Long count = benefitsRepository.countByIdBenefit(id);
        if(count == null || count == 0){
            throw new EntityNotFoundException("Немає виплат з ID: " + id);
        }
        benefitsRepository.deleteById(id);
    }

    public Double getSum(){
        return benefitsRepository.findSum();
    }
}
