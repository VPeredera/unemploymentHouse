package com.unemploymenthouse.unemploymenthouse.web;

import com.unemploymenthouse.unemploymenthouse.domain.Unemployed;
import com.unemploymenthouse.unemploymenthouse.domain.UnemploymentBenefits;
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
    private final BenefitsService benefitsService;
    private final UnemployedRepository unemployedRepository;

    @Autowired
    public BenefitsController(BenefitsService benefitsService, UnemployedRepository unemployedRepository) {
        this.benefitsService = benefitsService;
        this. unemployedRepository = unemployedRepository;
    }

    private static final String BENEFIT_ATTRIBUTE_NAME = "benefit";
    private static final String MESSAGE_ATTRIBUTE_NAME = "message";

    @GetMapping("/benefit")
    public String showBenefitsList(Model model) {
        List<UnemploymentBenefits> listBenefits = benefitsService.listAll();
        model.addAttribute("listBenefits", listBenefits);
        Double amount = benefitsService.getSum();
        model.addAttribute("amount", amount);
        return BENEFIT_ATTRIBUTE_NAME;
    }

    @GetMapping("/benefit/new")
    public String showNewForm(Model model) {
        List<Unemployed> listUnemployed = UnemployedService.makeCollection(unemployedRepository.findAll());
        model.addAttribute(BENEFIT_ATTRIBUTE_NAME, new UnemploymentBenefits());
        model.addAttribute("listUnemployed", listUnemployed);
        model.addAttribute("pageTitle", "Додати новий запис");
        return "benefit_form";
    }

    @PostMapping("/benefit/save")
    public String saveBenefits(UnemploymentBenefits benefit, RedirectAttributes ra) {
        benefitsService.save(benefit);
        ra.addFlashAttribute(MESSAGE_ATTRIBUTE_NAME, "Запис успішно збережений.");
        return "redirect:/benefit";
    }

    @GetMapping("/benefit/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        List<Unemployed> listUnemployed = UnemployedService.makeCollection(unemployedRepository.findAll());
        model.addAttribute("listUnemployed", listUnemployed);
        UnemploymentBenefits benefit = benefitsService.get(id);
        model.addAttribute(BENEFIT_ATTRIBUTE_NAME, benefit);
        model.addAttribute("pageTitle", "Редагувати запис (ID: " + id + ")");
        ra.addFlashAttribute(MESSAGE_ATTRIBUTE_NAME, "Запис з ID " + id + " успішно змінений!");
        return "benefit_form";
    }

    @GetMapping("/benefit/delete/{id}")
    public String deleteBenefit(@PathVariable("id") Integer id, RedirectAttributes ra){
        benefitsService.delete(id);
        ra.addFlashAttribute(MESSAGE_ATTRIBUTE_NAME, "Запис з ID" + id + " успішно видалений!");
        return "redirect:/benefit";
    }
}
