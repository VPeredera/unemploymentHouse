package com.unemploymenthouse.unemploymenthouse.web;

import com.unemploymenthouse.unemploymenthouse.domain.Jobs;
import com.unemploymenthouse.unemploymenthouse.domain.Specialty;
import com.unemploymenthouse.unemploymenthouse.domain.Unemployed;
import com.unemploymenthouse.unemploymenthouse.exception.UnemployedNotFoundException;
import com.unemploymenthouse.unemploymenthouse.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.unemploymenthouse.unemploymenthouse.service.UnemployedService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UnemployedController {
    @Autowired private UnemployedService unemployedService;
    @Autowired private SpecialtyService specialtyService;

    @GetMapping("/unemployed")
    public String showUnemployedList(Model model) {
        List<Unemployed> listUnemployed = unemployedService.listAll();
        model.addAttribute("listUnemployed", listUnemployed);
        List<Unemployed> listOldest = unemployedService.getOldest();
        model.addAttribute("listOldest", listOldest);
        return "unemployed";
    }

    @GetMapping("/unemployed/new")
    public String showNewForm(Model model) {
        List<Specialty> listSpecialty = specialtyService.listAll();
        model.addAttribute("listSpecialty", listSpecialty);
        model.addAttribute("unemployed", new Unemployed());
        model.addAttribute("pageTitle", "Додати новий запис");
        return "unemployed_form";
    }

    @PostMapping("/unemployed/save")
    public String saveUnemployed(Unemployed unemployed, RedirectAttributes ra) {
        unemployedService.save(unemployed);
        ra.addFlashAttribute("message", "Запис успішно збережений.");
        return "redirect:/unemployed";
    }

    @GetMapping("/unemployed/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            List<Specialty> listSpecialty = specialtyService.listAll();
            model.addAttribute("listSpecialty", listSpecialty);
            Unemployed unemployed = unemployedService.get(id);
            model.addAttribute("unemployed", unemployed);
            model.addAttribute("pageTitle", "Редагувати запис (ID: " + id + ")");
            ra.addFlashAttribute("message", "Запис з ID " + id + " успішно змінений!");
            return "unemployed_form";
        } catch (UnemployedNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/unemployed";
        }
    }

    @GetMapping("/unemployed/delete/{id}")
    public String deleteUnemployed(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            unemployedService.delete(id);
            ra.addFlashAttribute("message", "Запис з ID" + id + " успішно видалений!");
        } catch (UnemployedNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/unemployed";
    }

    @RequestMapping("/unemployed/searchByLetter")
    public String findByLetter(Unemployed unemployed, Model model, String fullName){
        if(fullName != null){
            List<Unemployed> listUnemployed = unemployedService.getUnemployedByLetter(fullName);
            model.addAttribute("listUnemployed", listUnemployed);
        } else {
            List<Unemployed> listUnemployed = unemployedService.listAll();
            model.addAttribute("listUnemployed", listUnemployed);
        }
        return "unemployed";
    }

    @RequestMapping("/unemployed/searchByRegistration")
    public String findByDate(Unemployed unemployed, Model model, java.sql.Date date1, java.sql.Date date2){
        if(date1 != null && date2 != null){
            List<Unemployed> listUnemployed = unemployedService.getUnemployedByRegistration(date1, date2);
            model.addAttribute("listUnemployed", listUnemployed);
        } else {
            List<Unemployed> listUnemployed = unemployedService.listAll();
            model.addAttribute("listUnemployed", listUnemployed);
        }
        return "unemployed";
    }
}
