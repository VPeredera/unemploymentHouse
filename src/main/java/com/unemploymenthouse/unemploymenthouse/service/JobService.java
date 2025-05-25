package com.unemploymenthouse.unemploymenthouse.service;

import com.unemploymenthouse.unemploymenthouse.domain.Jobs;
import com.unemploymenthouse.unemploymenthouse.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Jobs> listAll() {
        return (List<Jobs>) jobRepository.findAll();
    }

    public void save(Jobs job){
        jobRepository.save(job);
    }

    public Jobs get(Integer id) throws EntityNotFoundException {
        Optional<Jobs> result = jobRepository.findById(id);
        try{
            if(result.isPresent()){
                return result.get();
            }
            throw new EntityNotFoundException("Немає робіт з ID: " + id);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    public void delete(Integer id) throws EntityNotFoundException {
        Long count = jobRepository.countByIdJob(id);
        if(count == null || count == 0){
            throw new EntityNotFoundException("Немає робіт з ID: " + id);
        }
        jobRepository.deleteById(id);
    }

    public List<Jobs> getJobsByCompany(String companyName){
        return jobRepository.findJobsByCompany(companyName);
    }
    public List<Jobs> getJobsBySalary(double salary1, double salary2){
        return jobRepository.findJobsBySalary(salary1, salary2);
    }

    public List<Jobs> getMaxSalaryJob(){
        return jobRepository.findMaxSalaryJob();
    }
}
