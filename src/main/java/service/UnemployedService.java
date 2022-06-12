package service;

import domain.Unemployed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UnemployedRepository;

import java.util.List;

@Service
public class UnemployedService {
    @Autowired private UnemployedRepository unemployRepo;

    public List<Unemployed> listAll() {
        return (List<Unemployed>) unemployRepo.findAll();
    }
}
