package com.unemploymenthouse.unemploymenthouse.web;

import com.unemploymenthouse.unemploymenthouse.domain.Unemployed;
import com.unemploymenthouse.unemploymenthouse.exception.UnemployedNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.unemploymenthouse.unemploymenthouse.service.UnemployedService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UnemployedController {
    @Autowired private UnemployedService unemployedService;

    @GetMapping("/unemployed")
    public String showUnemployedList(Model model) {
        List<Unemployed> listUnemployed = unemployedService.listAll();
        model.addAttribute("listUnemployed", listUnemployed);
        return "unemployed";
    }

    @GetMapping("/unemployed/new")
    public String showNewForm(Model model) {
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
}
