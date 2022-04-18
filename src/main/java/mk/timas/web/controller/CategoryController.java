package mk.timas.web.controller;

import mk.timas.model.Category;
import mk.timas.model.exceptions.CategoryIsAssignedToProduct;
import mk.timas.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping( "/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getAllCategories(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Category> categories = this.categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "categories");
        return "master-template";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editCategoryPage(@PathVariable Long id, Model model) {
        if(this.categoryService.findById(id).isPresent()) {
            Category category = this.categoryService.findById(id).get();
            model.addAttribute("category",category);
            model.addAttribute("bodyContent","add-category");
            return "master-template";
        }
        return "redirect:/categories?error=CategoryNotFound";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCategoryPage(Model model) {
        model.addAttribute("bodyContent","add-category");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveCategory(@RequestParam(required = false) Long id,
                                   @RequestParam String name,
                                   @RequestParam String description,
                                   @RequestParam String image) {
        if(id != null)
            this.categoryService.update(id,name,description,image);
        else
            this.categoryService.create(name,description,image);
        return "redirect:/categories";
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteCategory(@PathVariable Long id)
    {
        try {
            this.categoryService.deleteById(id);
        }
        catch (CategoryIsAssignedToProduct ex) {
            return "redirect:/categories?error=" + ex.getLocalizedMessage();
        }
        return "redirect:/categories";
    }

}
