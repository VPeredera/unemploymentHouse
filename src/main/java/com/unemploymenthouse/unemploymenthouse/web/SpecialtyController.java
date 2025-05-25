package com.unemploymenthouse.unemploymenthouse.web;

import com.unemploymenthouse.unemploymenthouse.domain.Specialty;
import com.unemploymenthouse.unemploymenthouse.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Controller
public class SpecialtyController {
    @Autowired
    private SpecialtyService specialtyService;

    @GetMapping("/specialty")
    public String showSpecialtyList(Model model){
        List<Specialty> listSpecialty = specialtyService.listAll();
        model.addAttribute("listSpecialty", listSpecialty);
        return "specialty";
    }

    @GetMapping("/specialty/new")
    public String showNewForm(Model model) {
        model.addAttribute("specialty", new Specialty());
        model.addAttribute("pageTitle", "Додати новий запис");
        return "specialty_form";
    }

    @PostMapping("/specialty/save")
    public String saveSpecialty(Specialty specialty, RedirectAttributes ra) {
        specialtyService.save(specialty);
        ra.addFlashAttribute("message", "Запис успішно збережений.");
        return "redirect:/specialty";
    }

    @GetMapping("/specialty/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        Specialty specialty = specialtyService.get(id);
        model.addAttribute("specialty", specialty);
        model.addAttribute("pageTitle", "Редагувати запис (ID: " + id + ")");
        ra.addFlashAttribute("message", "Запис з ID " + id + " успішно змінений!");
        return "specialty_form";
    }

    @GetMapping("/specialty/delete/{id}")
    public String deleteSpecialty(@PathVariable("id") Integer id, RedirectAttributes ra){
        specialtyService.delete(id);
        ra.addFlashAttribute("message", "Запис з ID" + id + " успішно видалений!");
        return "redirect:/specialty";
    }

    @RequestMapping("/specialty/searchByWordPart")
    public String findByLetter(Specialty specialty, Model model, String wordPart){
        if(wordPart != null){
            List<Specialty> listSpecialty = specialtyService.getSpecialtyByPart(wordPart);
            model.addAttribute("listSpecialty", listSpecialty);
        } else {
            List<Specialty> listSpecialty = specialtyService.listAll();
            model.addAttribute("listSpecialty", listSpecialty);
        }
        return "specialty";
    }
}
