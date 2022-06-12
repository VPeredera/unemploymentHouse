package com.unemploymenthouse.unemploymenthouse.web;

import com.unemploymenthouse.unemploymenthouse.domain.Unemployed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.unemploymenthouse.unemploymenthouse.service.UnemployedService;

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
        return "unemployed_form";
    }
}
