package com.unemploymenthouse.unemploymenthouse.web;

import com.unemploymenthouse.unemploymenthouse.domain.Jobs;
import com.unemploymenthouse.unemploymenthouse.domain.Resume;
import com.unemploymenthouse.unemploymenthouse.domain.Unemployed;
import com.unemploymenthouse.unemploymenthouse.exception.ResumeNotFoundException;
import com.unemploymenthouse.unemploymenthouse.repository.UnemployedRepository;
import com.unemploymenthouse.unemploymenthouse.service.ResumeService;
import com.unemploymenthouse.unemploymenthouse.service.UnemployedService;
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
public class ResumeController {
    @Autowired private ResumeService resumeService;
    @Autowired private UnemployedRepository unemployedRepository;

    @GetMapping("/resume")
    public String showResumeList(Model model) {
        List<Resume> listResume = resumeService.listAll();
        model.addAttribute("listResume", listResume);
        return "resume";
    }

    @GetMapping("/resume/new")
    public String showNewForm(Model model) {
        List<Unemployed> listUnemployed = UnemployedService.makeCollection(unemployedRepository.findAll());
        model.addAttribute("resume", new Resume());
        model.addAttribute("listUnemployed", listUnemployed);
        model.addAttribute("pageTitle", "Додати новий запис");
        return "resume_form";
    }

    @PostMapping("/resume/save")
    public String saveResume(Resume resume, RedirectAttributes ra) {
        resumeService.save(resume);
        ra.addFlashAttribute("message", "Запис успішно збережений.");
        return "redirect:/resume";
    }

    @GetMapping("/resume/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            List<Unemployed> listUnemployed = UnemployedService.makeCollection(unemployedRepository.findAll());
            model.addAttribute("listUnemployed", listUnemployed);
            Resume resume = resumeService.get(id);
            model.addAttribute("resume", resume);
            model.addAttribute("pageTitle", "Редагувати запис (ID: " + id + ")");
            ra.addFlashAttribute("message", "Запис з ID " + id + " успішно змінений!");
            return "resume_form";
        } catch (ResumeNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/resume";
        }
    }

    @GetMapping("/resume/delete/{id}")
    public String deleteResume(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            resumeService.delete(id);
            ra.addFlashAttribute("message", "Запис з ID" + id + " успішно видалений!");
        } catch (ResumeNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/resume";
    }

    @RequestMapping("/resume/searchByName")
    public String findByName(Resume resume, Model model, String fullName){
        if(fullName != null){
            List<Resume> listResume = resumeService.getResumeByName(fullName);
            model.addAttribute("listResume", listResume);
        } else {
            List<Resume> listResume = resumeService.listAll();
            model.addAttribute("listResume", listResume);
        }
        return "resume";
    }
}
