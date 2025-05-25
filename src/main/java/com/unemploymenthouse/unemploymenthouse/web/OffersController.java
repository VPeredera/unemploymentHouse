package com.unemploymenthouse.unemploymenthouse.web;

import com.unemploymenthouse.unemploymenthouse.domain.*;
import com.unemploymenthouse.unemploymenthouse.service.JobService;
import com.unemploymenthouse.unemploymenthouse.service.OffersService;
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
public class OffersController {
    private final OffersService offersService;
    private final UnemployedService unemployedService;
    private final JobService jobService;

    @Autowired
    public OffersController(OffersService offersService, UnemployedService unemployedService, JobService jobService) {
        this.offersService = offersService;
        this.unemployedService = unemployedService;
        this.jobService = jobService;
    }

    private static final String OFFERS_ATTRIBUTE_NAME = "offers";
    private static final String MESSAGE_ATTRIBUTE_NAME = "message";

    @GetMapping("/offers")
    public String showOffersList(Model model) {
        List<Offers> listOffers = offersService.listAll();
        model.addAttribute("listOffers", listOffers);
        int count = offersService.getCount();
        model.addAttribute("count", count);
        return OFFERS_ATTRIBUTE_NAME;
    }

    @GetMapping("/offers/new")
    public String showNewForm(Model model) {
        List<Unemployed> listUnemployed = unemployedService.listAll();
        model.addAttribute("listUnemployed", listUnemployed);
        List<Jobs> listJobs = jobService.listAll();
        model.addAttribute("listJobs", listJobs);
        model.addAttribute(OFFERS_ATTRIBUTE_NAME, new Offers());
        model.addAttribute("pageTitle", "Додати новий запис");
        return "offers_form";
    }

    @PostMapping("/offers/save")
    public String saveOffers(Offers offers, RedirectAttributes ra) {
        offersService.save(offers);
        ra.addFlashAttribute(MESSAGE_ATTRIBUTE_NAME, "Запис успішно збережений.");
        return "redirect:/offers";
    }

    @GetMapping("/offers/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        List<Unemployed> listUnemployed = unemployedService.listAll();
        model.addAttribute("listUnemployed", listUnemployed);
        List<Jobs> listJobs = jobService.listAll();
        model.addAttribute("listJobs", listJobs);
        Offers offers = offersService.get(id);
        model.addAttribute(OFFERS_ATTRIBUTE_NAME, offers);
        model.addAttribute("pageTitle", "Редагувати запис (ID: " + id + ")");
        ra.addFlashAttribute(MESSAGE_ATTRIBUTE_NAME, "Запис з ID " + id + " успішно змінений!");
        return "offers_form";
    }

    @GetMapping("/offers/delete/{id}")
    public String deleteOffers(@PathVariable("id") Integer id, RedirectAttributes ra){
        offersService.delete(id);
        ra.addFlashAttribute(MESSAGE_ATTRIBUTE_NAME, "Запис з ID" + id + " успішно видалений!");
        return "redirect:/offers";
    }
}
