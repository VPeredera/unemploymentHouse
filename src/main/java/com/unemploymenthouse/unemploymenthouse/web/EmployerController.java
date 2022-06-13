package com.unemploymenthouse.unemploymenthouse.web;

import com.unemploymenthouse.unemploymenthouse.domain.Employer;
import com.unemploymenthouse.unemploymenthouse.exception.EmployerNotFoundException;
import com.unemploymenthouse.unemploymenthouse.exception.UnemployedNotFoundException;
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
    @Autowired
    private EmployerService employerService;

    @GetMapping("/employer")
    public String showEmployerList(Model model){
        List<Employer> listEmployer = employerService.listAll();
        model.addAttribute("listEmployer", listEmployer);
        return "employer";
    }

    @GetMapping("/employer/new")
    public String showNewForm(Model model) {
        model.addAttribute("employer", new Employer());
        model.addAttribute("pageTitle", "Додати новий запис");
        return "employer_form";
    }

    @PostMapping("/employer/save")
    public String saveEmployer(Employer employer, RedirectAttributes ra) {
        employerService.save(employer);
        ra.addFlashAttribute("message", "Запис успішно збережений.");
        return "redirect:/employer";
    }

    @GetMapping("/employer/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Employer employer = employerService.get(id);
            model.addAttribute("employer", employer);
            model.addAttribute("pageTitle", "Редагувати запис (ID: " + id + ")");
            ra.addFlashAttribute("message", "Запис з ID " + id + " успішно змінений!");
            return "employer_form";
        } catch (EmployerNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/employer";
        }
    }

    @GetMapping("/employer/delete/{id}")
    public String deleteEmployer(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            employerService.delete(id);
            ra.addFlashAttribute("message", "Запис з ID" + id + " успішно видалений!");
        } catch (EmployerNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/employer";
    }
}
