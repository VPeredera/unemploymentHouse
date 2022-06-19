package com.unemploymenthouse.unemploymenthouse.web;

import com.unemploymenthouse.unemploymenthouse.domain.Unemployed;
import com.unemploymenthouse.unemploymenthouse.domain.UnemploymentBenefits;
import com.unemploymenthouse.unemploymenthouse.exception.BenefitsNotFoundException;
import com.unemploymenthouse.unemploymenthouse.repository.UnemployedRepository;
import com.unemploymenthouse.unemploymenthouse.service.BenefitsService;
import com.unemploymenthouse.unemploymenthouse.service.UnemployedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BenefitsController {
    @Autowired private BenefitsService benefitsService;
    @Autowired private UnemployedRepository unemployedRepository;

    @GetMapping("/benefit")
    public String showBenefitsList(Model model) {
        List<UnemploymentBenefits> listBenefits = benefitsService.listAll();
        model.addAttribute("listBenefits", listBenefits);
        double amount = benefitsService.getSum();
        model.addAttribute("amount", amount);
        return "benefit";
    }

    @GetMapping("/benefit/new")
    public String showNewForm(Model model) {
        List<Unemployed> listUnemployed = UnemployedService.makeCollection(unemployedRepository.findAll());
        model.addAttribute("benefit", new UnemploymentBenefits());
        model.addAttribute("listUnemployed", listUnemployed);
        model.addAttribute("pageTitle", "Додати новий запис");
        return "benefit_form";
    }

    @PostMapping("/benefit/save")
    public String saveBenefits(UnemploymentBenefits benefit, RedirectAttributes ra) {
        benefitsService.save(benefit);
        ra.addFlashAttribute("message", "Запис успішно збережений.");
        return "redirect:/benefit";
    }

    @GetMapping("/benefit/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            List<Unemployed> listUnemployed = UnemployedService.makeCollection(unemployedRepository.findAll());
            model.addAttribute("listUnemployed", listUnemployed);
            UnemploymentBenefits benefit = benefitsService.get(id);
            model.addAttribute("benefit", benefit);
            model.addAttribute("pageTitle", "Редагувати запис (ID: " + id + ")");
            ra.addFlashAttribute("message", "Запис з ID " + id + " успішно змінений!");
            return "benefit_form";
        } catch (BenefitsNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/benefit";
        }
    }

    @GetMapping("/benefit/delete/{id}")
    public String deleteBenefit(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            benefitsService.delete(id);
            ra.addFlashAttribute("message", "Запис з ID" + id + " успішно видалений!");
        } catch (BenefitsNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/benefit";
    }
}
