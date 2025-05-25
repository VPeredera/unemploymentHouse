package com.unemploymenthouse.unemploymenthouse.web;

import com.unemploymenthouse.unemploymenthouse.domain.*;
import com.unemploymenthouse.unemploymenthouse.exporter.JobsPDFExporter;
import com.unemploymenthouse.unemploymenthouse.service.EmployerService;
import com.unemploymenthouse.unemploymenthouse.service.JobService;
import com.unemploymenthouse.unemploymenthouse.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class JobController {
    private final JobService jobService;
    private final SpecialtyService specialtyService;
    private final EmployerService employerService;

    @Autowired
    public JobController(JobService jobService, SpecialtyService specialtyService, EmployerService employerService) {
        this.jobService = jobService;
        this.specialtyService = specialtyService;
        this.employerService = employerService;
    }

    private static final String LIST_ATTRIBUTE_NAME = "listJobs";
    private static final String MESSAGE_ATTRIBUTE_NAME = "message";

    @GetMapping("/jobs")
    public String showJobsList(Model model) {
        List<Jobs> listJobs = jobService.listAll();
        model.addAttribute(LIST_ATTRIBUTE_NAME, listJobs);
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
        ra.addFlashAttribute(MESSAGE_ATTRIBUTE_NAME, "Запис успішно збережений.");
        return "redirect:/jobs";
    }

    @GetMapping("/jobs/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        List<Specialty> listSpecialties = specialtyService.listAll();
        model.addAttribute("listSpecialties", listSpecialties);
        List<Employer> listEmployers = employerService.listAll();
        model.addAttribute("listEmployers", listEmployers);
        Jobs jobs = jobService.get(id);
        model.addAttribute("jobs", jobs);
        model.addAttribute("pageTitle", "Редагувати запис (ID: " + id + ")");
        ra.addFlashAttribute(MESSAGE_ATTRIBUTE_NAME, "Запис з ID " + id + " успішно змінений!");
        return "jobs_form";
    }

    @GetMapping("/jobs/delete/{id}")
    public String deleteJobs(@PathVariable("id") Integer id, RedirectAttributes ra){
        jobService.delete(id);
        ra.addFlashAttribute(MESSAGE_ATTRIBUTE_NAME, "Запис з ID" + id + " успішно видалений!");
        return "redirect:/jobs";
    }

    @GetMapping("/jobs/searchByCompany")
    public String findByCompany(Model model, String companyName){
        if(companyName != null){
            List<Jobs> listJobs = jobService.getJobsByCompany(companyName);
            model.addAttribute(LIST_ATTRIBUTE_NAME, listJobs);
        } else {
            List<Jobs> listJobs = jobService.listAll();
            model.addAttribute(LIST_ATTRIBUTE_NAME, listJobs);
        }
        return "jobs";
    }

    @GetMapping("/jobs/searchBySalary")
    public String findBySalary(Model model, double salary1, double salary2){
        if(salary1 != 0 && salary2 != 0){
            List<Jobs> listJobs = jobService.getJobsBySalary(salary1, salary2);
            model.addAttribute(LIST_ATTRIBUTE_NAME, listJobs);
        } else {
            List<Jobs> listJobs = jobService.listAll();
            model.addAttribute(LIST_ATTRIBUTE_NAME, listJobs);
        }
        return "jobs";
    }

    @GetMapping("/jobs/export")
    public void exportToPDF2(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=reeducation_" + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);

        List<Jobs> listMaxSalary = jobService.getMaxSalaryJob();

        JobsPDFExporter exporter = new JobsPDFExporter(listMaxSalary);
        exporter.export(response);
    }
}
