package com.unemploymenthouse.unemploymenthouse.web;

import com.unemploymenthouse.unemploymenthouse.domain.Resume;
import com.unemploymenthouse.unemploymenthouse.domain.Unemployed;
import com.unemploymenthouse.unemploymenthouse.repository.UnemployedRepository;
import com.unemploymenthouse.unemploymenthouse.service.ResumeService;
import com.unemploymenthouse.unemploymenthouse.service.UnemployedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ResumeController {
    private final ResumeService resumeService;
    private final UnemployedRepository unemployedRepository;

    @Autowired
    public ResumeController(ResumeService resumeService, UnemployedRepository unemployedRepository) {
        this.resumeService = resumeService;
        this.unemployedRepository = unemployedRepository;
    }

    private static final String LIST_ATTRIBUTE_NAME = "listResume";
    private static final String RESUME_ATTRIBUTE_NAME = "resume";
    private static final String MESSAGE_ATTRIBUTE_NAME = "message";

    @GetMapping("/resume")
    public String showResumeList(Model model) {
        List<Resume> listResume = resumeService.listAll();
        model.addAttribute(LIST_ATTRIBUTE_NAME, listResume);
        return RESUME_ATTRIBUTE_NAME;
    }

    @GetMapping("/resume/new")
    public String showNewForm(Model model) {
        List<Unemployed> listUnemployed = UnemployedService.makeCollection(unemployedRepository.findAll());
        model.addAttribute(RESUME_ATTRIBUTE_NAME, new Resume());
        model.addAttribute("listUnemployed", listUnemployed);
        model.addAttribute("pageTitle", "Додати новий запис");
        return "resume_form";
    }

    @PostMapping("/resume/save")
    public String saveResume(Resume resume, RedirectAttributes ra) {
        resumeService.save(resume);
        ra.addFlashAttribute(MESSAGE_ATTRIBUTE_NAME, "Запис успішно збережений.");
        return "redirect:/resume";
    }

    @GetMapping("/resume/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        List<Unemployed> listUnemployed = UnemployedService.makeCollection(unemployedRepository.findAll());
        model.addAttribute("listUnemployed", listUnemployed);
        Resume resume = resumeService.get(id);
        model.addAttribute(RESUME_ATTRIBUTE_NAME, resume);
        model.addAttribute("pageTitle", "Редагувати запис (ID: " + id + ")");
        ra.addFlashAttribute(MESSAGE_ATTRIBUTE_NAME, "Запис з ID " + id + " успішно змінений!");
        return "resume_form";
    }

    @GetMapping("/resume/delete/{id}")
    public String deleteResume(@PathVariable("id") Integer id, RedirectAttributes ra){
        resumeService.delete(id);
        ra.addFlashAttribute(MESSAGE_ATTRIBUTE_NAME, "Запис з ID" + id + " успішно видалений!");
        return "redirect:/resume";
    }

    @GetMapping("/resume/searchByName")
    public String findByName(Model model, String fullName){
        if(fullName != null){
            List<Resume> listResume = resumeService.getResumeByName(fullName);
            model.addAttribute(LIST_ATTRIBUTE_NAME, listResume);
        } else {
            List<Resume> listResume = resumeService.listAll();
            model.addAttribute(LIST_ATTRIBUTE_NAME, listResume);
        }
        return RESUME_ATTRIBUTE_NAME;
    }
}
