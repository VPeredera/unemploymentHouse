package com.unemploymenthouse.unemploymenthouse.service;

import com.unemploymenthouse.unemploymenthouse.domain.Jobs;
import com.unemploymenthouse.unemploymenthouse.domain.UnemploymentBenefits;
import com.unemploymenthouse.unemploymenthouse.exception.BenefitsNotFoundException;
import com.unemploymenthouse.unemploymenthouse.exception.JobNotFoundException;
import com.unemploymenthouse.unemploymenthouse.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired private JobRepository jobRepository;

    public List<Jobs> listAll() {
        return (List<Jobs>) jobRepository.findAll();
    }

    public void save(Jobs job){
        jobRepository.save(job);
    }

    public Jobs get(Integer id) throws JobNotFoundException {
        Optional<Jobs> result = jobRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new JobNotFoundException("Немає робіт з ID: " + id);
    }

    public void delete(Integer id) throws JobNotFoundException {
        Long count = jobRepository.countByIdJob(id);
        if(count == null || count == 0){
            throw new JobNotFoundException("Немає робіт з ID: " + id);
        }
        jobRepository.deleteById(id);
    }

    public List<Jobs> getJobsByCompany(String companyName){
        return jobRepository.findJobsByCompany(companyName);
    }
    public List<Jobs> getJobsBySalary(double salary1, double salary2){
        return jobRepository.findJobsBySalary(salary1, salary2);
    }
}
