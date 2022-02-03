package mk.com.timas.web.controller;

import mk.com.timas.model.Category;
import mk.com.timas.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    private final CategoryService categoryService;

    public HomeController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getHomePage(Model model) {
        List<Category> categories = this.categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "categories");
        model.addAttribute("bodyContent", "home");
        return "master-template";
    }

    @GetMapping("/about")
    public String getAboutPage(Model model) {
        List<Category> categories = this.categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "categories");
        model.addAttribute("bodyContent","about");
        return "master-template";
    }

    @GetMapping("/contact")
    public String getContactPage(Model model) {
        List<Category> categories = this.categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "categories");
        model.addAttribute("bodyContent","contact");
        return "master-template";
    }

    @GetMapping("/access_denied")
    public String getAccessDeniedPage(Model model) {
        model.addAttribute("bodyContent","access_denied");
        return "master-template";
    }


}
