package mk.com.timas.web.controller;

import mk.com.timas.model.Manufacturer;
import mk.com.timas.service.ManufacturerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manufacturers")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }


    @GetMapping
    public String getAllManufacturers(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("bodyContent", "manufacturers");
        return "master-template";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editManufacturerPage(@PathVariable Long id, Model model) {
        if(this.manufacturerService.findById(id).isPresent()) {
            Manufacturer manufacturer = this.manufacturerService.findById(id).get();
            model.addAttribute("manufacturer",manufacturer);
            model.addAttribute("bodyContent","add-manufacturer");
            return "master-template";
        }
        return "redirect:/manufacturers?error=ManufacturerNotFound";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProductPage(Model model) {
        model.addAttribute("bodyContent","add-manufacturer");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveManufacturer(@RequestParam(required = false) Long id,
                                   @RequestParam String name,
                                   @RequestParam String address) {
        if(id != null)
            this.manufacturerService.edit(id,name,address);
        else
            this.manufacturerService.save(name,address);
        return "redirect:/manufacturers";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteManufacturer(@PathVariable Long id)
    {
        this.manufacturerService.deleteById(id);
        return "redirect:/manufacturers";
    }

}
