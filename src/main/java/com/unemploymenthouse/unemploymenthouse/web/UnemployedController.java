package com.unemploymenthouse.unemploymenthouse.web;

import com.unemploymenthouse.unemploymenthouse.domain.Specialty;
import com.unemploymenthouse.unemploymenthouse.domain.Unemployed;
import com.unemploymenthouse.unemploymenthouse.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.unemploymenthouse.unemploymenthouse.service.UnemployedService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Controller
public class UnemployedController {
    private final UnemployedService unemployedService;
    private final SpecialtyService specialtyService;

    @Autowired
    public UnemployedController(UnemployedService unemployedService, SpecialtyService specialtyService) {
        this.unemployedService = unemployedService;
        this.specialtyService = specialtyService;
    }

    private static final String LIST_ATTRIBUTE_NAME = "listUnemployed";
    private static final String UNEMPLOYED_ATTRIBUTE_NAME = "unemployed";
    private static final String MESSAGE_ATTRIBUTE_NAME = "message";
    private static final String REDIRECT_UNEMPLOYED = "redirect:/unemployed";

    @GetMapping("/unemployed")
    public String showUnemployedList(Model model) {
        List<Unemployed> listUnemployed = unemployedService.listAll();
        model.addAttribute(LIST_ATTRIBUTE_NAME, listUnemployed);
        List<Unemployed> listOldest = unemployedService.getOldest();
        model.addAttribute("listOldest", listOldest);
        return UNEMPLOYED_ATTRIBUTE_NAME;
    }

    @GetMapping("/unemployed/new")
    public String showNewForm(Model model) {
        List<Specialty> listSpecialty = specialtyService.listAll();
        model.addAttribute("listSpecialty", listSpecialty);
        model.addAttribute(UNEMPLOYED_ATTRIBUTE_NAME, new Unemployed());
        model.addAttribute("pageTitle", "Додати новий запис");
        return "unemployed_form";
    }

    @PostMapping("/unemployed/save")
    public String saveUnemployed(Unemployed unemployed, RedirectAttributes ra) {
        unemployedService.save(unemployed);
        ra.addFlashAttribute(MESSAGE_ATTRIBUTE_NAME, "Запис успішно збережений.");
        return REDIRECT_UNEMPLOYED;
    }

    @GetMapping("/unemployed/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            List<Specialty> listSpecialty = specialtyService.listAll();
            model.addAttribute("listSpecialty", listSpecialty);
            Unemployed unemployed = unemployedService.get(id);
            model.addAttribute(UNEMPLOYED_ATTRIBUTE_NAME, unemployed);
            model.addAttribute("pageTitle", "Редагувати запис (ID: " + id + ")");
            ra.addFlashAttribute(MESSAGE_ATTRIBUTE_NAME, "Запис з ID " + id + " успішно змінений!");
            return "unemployed_form";
        } catch (EntityNotFoundException e) {
            ra.addFlashAttribute(MESSAGE_ATTRIBUTE_NAME, e.getMessage());
            return REDIRECT_UNEMPLOYED;
        }
    }

    @GetMapping("/unemployed/delete/{id}")
    public String deleteUnemployed(@PathVariable("id") Integer id, RedirectAttributes ra){
        unemployedService.delete(id);
        ra.addFlashAttribute(MESSAGE_ATTRIBUTE_NAME, "Запис з ID" + id + " успішно видалений!");
        return REDIRECT_UNEMPLOYED;
    }

    @GetMapping("/unemployed/searchByLetter")
    public String findByLetter(Model model, String fullName){
        if(fullName != null){
            List<Unemployed> listUnemployed = unemployedService.getUnemployedByLetter(fullName);
            model.addAttribute(LIST_ATTRIBUTE_NAME, listUnemployed);
        } else {
            List<Unemployed> listUnemployed = unemployedService.listAll();
            model.addAttribute(LIST_ATTRIBUTE_NAME, listUnemployed);
        }
        return UNEMPLOYED_ATTRIBUTE_NAME;
    }

    @GetMapping("/unemployed/searchByRegistration")
    public String findByDate(Model model, java.sql.Date date1, java.sql.Date date2){
        if(date1 != null && date2 != null){
            List<Unemployed> listUnemployed = unemployedService.getUnemployedByRegistration(date1, date2);
            model.addAttribute(LIST_ATTRIBUTE_NAME, listUnemployed);
        } else {
            List<Unemployed> listUnemployed = unemployedService.listAll();
            model.addAttribute(LIST_ATTRIBUTE_NAME, listUnemployed);
        }
        return UNEMPLOYED_ATTRIBUTE_NAME;
    }
}
