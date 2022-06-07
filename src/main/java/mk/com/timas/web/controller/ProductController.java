package mk.com.timas.web.controller;

import mk.com.timas.model.Category;
import mk.com.timas.model.Manufacturer;
import mk.com.timas.model.Product;
import mk.com.timas.service.CategoryService;
import mk.com.timas.service.ManufacturerService;
import mk.com.timas.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             ManufacturerService manufacturerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) Long categoryId, @RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        if(categoryId != null)
        {
            List<Product> productsByCategory = this.productService.findByCategoryId(categoryId);
            model.addAttribute("productsByCategory",productsByCategory);
        }

        List<Category> categories = this.categoryService.findAll();
        model.addAttribute("categories",categories);
        model.addAttribute("bodyContent","categories");
        List<Product> products = this.productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("bodyContent", "products");
        return "master-template";
    }

    @DeleteMapping("/product-details/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteProduct(@PathVariable Long id) {
        this.productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/product-details/{id}")
    public String showProduct(@PathVariable Long id, Model model) {
            if (this.productService.findById(id).isPresent()) {
                Product product = this.productService.findById(id).get();
                List<Manufacturer> manufacturers = this.manufacturerService.findAll();
                model.addAttribute("product", product);
                model.addAttribute("bodyContent", "product-details");
                return "master-template";
            }
            return "redirect:/products?error=ProductNotFound";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.productService.findById(id).isPresent()) {
            Product product = this.productService.findById(id).get();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            List<Category> categories = this.categoryService.findAll();
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("categories", categories);
            model.addAttribute("product", product);
            model.addAttribute("bodyContent", "add-product");
            return "master-template";
        }
        return "redirect:/products?error=ProductNotFound";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProductPage(Model model) {
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        List<Category> categories = this.categoryService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "add-product");
        return "master-template";
    }


   @PostMapping("/add")
    public String saveProduct(
            @RequestParam(required = false) Long id,
            Product product,
            @RequestParam(required = false) MultipartFile image
    ) {
        if (id != null) {
            try {
                this.productService.edit(id, product, image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                this.productService.save(product, image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/products";
    }


}
