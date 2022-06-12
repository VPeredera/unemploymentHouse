package web;

import domain.Unemployed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import service.UnemployedService;

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
}
