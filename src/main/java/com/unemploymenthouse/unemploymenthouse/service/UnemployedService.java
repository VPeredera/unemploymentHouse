package com.unemploymenthouse.unemploymenthouse.service;

import com.unemploymenthouse.unemploymenthouse.domain.Unemployed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unemploymenthouse.unemploymenthouse.repository.UnemployedRepository;

import java.util.List;

@Service
public class UnemployedService {
    @Autowired private UnemployedRepository unemployRepo;

    public List<Unemployed> listAll() {
        return (List<Unemployed>) unemployRepo.findAll();
    }
}
