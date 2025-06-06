package com.unemploymenthouse.unemploymenthouse.web;

import com.unemploymenthouse.unemploymenthouse.domain.Reeducation;
import com.unemploymenthouse.unemploymenthouse.exporter.ReeducationPDFExporter;
import com.unemploymenthouse.unemploymenthouse.domain.Specialty;
import com.unemploymenthouse.unemploymenthouse.domain.Unemployed;
import com.unemploymenthouse.unemploymenthouse.query.ReeducationAmount;
import com.unemploymenthouse.unemploymenthouse.service.ReeducationService;
import com.unemploymenthouse.unemploymenthouse.service.SpecialtyService;
import com.unemploymenthouse.unemploymenthouse.service.UnemployedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ReeducationController {
    private final ReeducationService reeducationService;
    private final SpecialtyService specialtyService;
    private final UnemployedService unemployedService;

    @Autowired
    public ReeducationController(ReeducationService reeducationService, SpecialtyService specialtyService, UnemployedService unemployedService) {
        this.reeducationService = reeducationService;
        this.specialtyService = specialtyService;
        this.unemployedService = unemployedService;
    }

    private static final String REEDUCATION_ATTRIBUTE_NAME = "reeducation";
    private static final String MESSAGE_ATTRIBUTE_NAME = "message";

    @GetMapping("/reeducation")
    public String showReeducationList(Model model) {
        List<Reeducation> listReeducation = reeducationService.listAll();
        model.addAttribute("listReeducation", listReeducation);
        List<ReeducationAmount> listReeducationAmount = reeducationService.getAmountReeducation();
        model.addAttribute("listReeducationAmount", listReeducationAmount);
        return REEDUCATION_ATTRIBUTE_NAME;
    }

    @GetMapping("/reeducation/new")
    public String showNewForm(Model model) {
        List<Unemployed> listUnemployed = unemployedService.listAll();
        model.addAttribute("listUnemployed", listUnemployed);
        List<Specialty> listSpecialties = specialtyService.listAll();
        model.addAttribute("listSpecialties", listSpecialties);
        model.addAttribute(REEDUCATION_ATTRIBUTE_NAME, new Reeducation());
        model.addAttribute("pageTitle", "Додати новий запис");
        return "reeducation_form";
    }

    @PostMapping("/reeducation/save")
    public String saveReeducation(Reeducation reeducation, RedirectAttributes ra) {
        reeducationService.save(reeducation);
        ra.addFlashAttribute(MESSAGE_ATTRIBUTE_NAME, "Запис успішно збережений.");
        return "redirect:/reeducation";
    }

    @GetMapping("/reeducation/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        List<Unemployed> listUnemployed = unemployedService.listAll();
        model.addAttribute("listUnemployed", listUnemployed);
        List<Specialty> listSpecialties = specialtyService.listAll();
        model.addAttribute("listSpecialties", listSpecialties);
        Reeducation reeducation = reeducationService.get(id);
        model.addAttribute(REEDUCATION_ATTRIBUTE_NAME, reeducation);
        model.addAttribute("pageTitle", "Редагувати запис (ID: " + id + ")");
        ra.addFlashAttribute(MESSAGE_ATTRIBUTE_NAME, "Запис з ID " + id + " успішно змінений!");
        return "reeducation_form";
    }

    @GetMapping("/reeducation/delete/{id}")
    public String deleteReeducation(@PathVariable("id") Integer id, RedirectAttributes ra){
        reeducationService.delete(id);
        ra.addFlashAttribute(MESSAGE_ATTRIBUTE_NAME, "Запис з ID" + id + " успішно видалений!");
        return "redirect:/reeducation";
    }

    @GetMapping("/reeducation/export")
    public void exportToPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=reeducation_" + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);

        List<ReeducationAmount> listReeducationAmount = reeducationService.getAmountReeducation();

        ReeducationPDFExporter exporter = new ReeducationPDFExporter(listReeducationAmount);
        exporter.export(response);
    }
}
