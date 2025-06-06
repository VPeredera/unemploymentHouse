package com.unemploymenthouse.unemploymenthouse.web;

import com.unemploymenthouse.unemploymenthouse.domain.Employer;
import com.unemploymenthouse.unemploymenthouse.domain.Resume;
import com.unemploymenthouse.unemploymenthouse.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmployerController {
    private final EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    private static final String LIST_ATTRIBUTE_NAME = "listEmployer";
    private static final String EMPLOYER_ATTRIBUTE_NAME = "employer";
    private static final String MESSAGE_ATTRIBUTE_NAME = "message";

    @GetMapping("/employer")
    public String showEmployerList(Model model){
        List<Employer> listEmployer = employerService.listAll();
        model.addAttribute(LIST_ATTRIBUTE_NAME, listEmployer);
        return EMPLOYER_ATTRIBUTE_NAME;
    }

    @GetMapping("/employer/new")
    public String showNewForm(Model model) {
        model.addAttribute(EMPLOYER_ATTRIBUTE_NAME, new Employer());
        model.addAttribute("pageTitle", "Додати новий запис");
        return "employer_form";
    }

    @PostMapping("/employer/save")
    public String saveEmployer(Employer employer, RedirectAttributes ra) {
        employerService.save(employer);
        ra.addFlashAttribute(MESSAGE_ATTRIBUTE_NAME, "Запис успішно збережений.");
        return "redirect:/employer";
    }

    @GetMapping("/employer/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        Employer employer = employerService.get(id);
        model.addAttribute(EMPLOYER_ATTRIBUTE_NAME, employer);
        model.addAttribute("pageTitle", "Редагувати запис (ID: " + id + ")");
        ra.addFlashAttribute(MESSAGE_ATTRIBUTE_NAME, "Запис з ID " + id + " успішно змінений!");
        return "employer_form";
    }

    @GetMapping("/employer/delete/{id}")
    public String deleteEmployer(@PathVariable("id") Integer id, RedirectAttributes ra){
        employerService.delete(id);
        ra.addFlashAttribute(MESSAGE_ATTRIBUTE_NAME, "Запис з ID" + id + " успішно видалений!");
        return "redirect:/employer";
    }

    @GetMapping("/employer/notBigger")
    public String findNotBigger(Model model, Integer amount){
        if(amount != 0){
            List<Employer> listEmployer = employerService.getNotBigger(amount);
            model.addAttribute(LIST_ATTRIBUTE_NAME, listEmployer);
        } else {
            List<Employer> listEmployer = employerService.listAll();
            model.addAttribute(LIST_ATTRIBUTE_NAME, listEmployer);
        }
        return EMPLOYER_ATTRIBUTE_NAME;
    }
}
