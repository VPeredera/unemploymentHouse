package com.unemploymenthouse.unemploymenthouse.web;

import com.unemploymenthouse.unemploymenthouse.domain.*;
import com.unemploymenthouse.unemploymenthouse.exception.JobNotFoundException;
import com.unemploymenthouse.unemploymenthouse.service.EmployerService;
import com.unemploymenthouse.unemploymenthouse.service.JobService;
import com.unemploymenthouse.unemploymenthouse.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class JobController {
    @Autowired private JobService jobService;
    @Autowired private SpecialtyService specialtyService;
    @Autowired private EmployerService employerService;

    @GetMapping("/jobs")
    public String showJobsList(Model model) {
        List<Jobs> listJobs = jobService.listAll();
        model.addAttribute("listJobs", listJobs);
        List<Jobs> listMaxSalary = jobService.getMaxSalaryJob();
        model.addAttribute("listMaxSalary", listMaxSalary);
        return "jobs";
    }

    @GetMapping("/jobs/new")
    public String showNewForm(Model model) {
        List<Specialty> listSpecialties = specialtyService.listAll();
        model.addAttribute("listSpecialties", listSpecialties);
        List<Employer> listEmployers = employerService.listAll();
        model.addAttribute("listEmployers", listEmployers);
        model.addAttribute("jobs", new Jobs());
        model.addAttribute("pageTitle", "Додати новий запис");
        return "jobs_form";
    }

    @PostMapping("/jobs/save")
    public String saveJobs(Jobs jobs, RedirectAttributes ra) {
        jobService.save(jobs);
        ra.addFlashAttribute("message", "Запис успішно збережений.");
        return "redirect:/jobs";
    }

    @GetMapping("/jobs/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            List<Specialty> listSpecialties = specialtyService.listAll();
            model.addAttribute("listSpecialties", listSpecialties);
            List<Employer> listEmployers = employerService.listAll();
            model.addAttribute("listEmployers", listEmployers);
            Jobs jobs = jobService.get(id);
            model.addAttribute("jobs", jobs);
            model.addAttribute("pageTitle", "Редагувати запис (ID: " + id + ")");
            ra.addFlashAttribute("message", "Запис з ID " + id + " успішно змінений!");
            return "jobs_form";
        } catch (JobNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/jobs";
        }
    }

    @GetMapping("/jobs/delete/{id}")
    public String deleteJobs(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            jobService.delete(id);
            ra.addFlashAttribute("message", "Запис з ID" + id + " успішно видалений!");
        } catch (JobNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/jobs";
    }

    @RequestMapping("/jobs/searchByCompany")
    public String findByCompany(Jobs jobs, Model model, String companyName){
        if(companyName != null){
            List<Jobs> listJobs = jobService.getJobsByCompany(companyName);
            model.addAttribute("listJobs", listJobs);
        } else {
            List<Jobs> listJobs = jobService.listAll();
            model.addAttribute("listJobs", listJobs);
        }
        return "jobs";
    }

    @RequestMapping("/jobs/searchBySalary")
    public String findBySalary(Jobs jobs, Model model, double salary1, double salary2){
        if(salary1 != 0 && salary2 != 0){
            List<Jobs> listJobs = jobService.getJobsBySalary(salary1, salary2);
            model.addAttribute("listJobs", listJobs);
        } else {
            List<Jobs> listJobs = jobService.listAll();
            model.addAttribute("listJobs", listJobs);
        }
        return "jobs";
    }
}
