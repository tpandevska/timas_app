package mk.com.timas.service.impl;

import mk.com.timas.model.Category;
import mk.com.timas.model.exceptions.CategoryIsAssignedToProduct;
import mk.com.timas.model.exceptions.CategoryNotFoundException;
import mk.com.timas.repository.CategoryRepository;
import mk.com.timas.service.CategoryService;
import mk.com.timas.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductService productService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    @Override
    public Category create(String name, String description,String image)  {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException();
        Category c = new Category(name,description,image);
        this.categoryRepository.save(c);
        return c;
    }

    @Override
    public Category update(Long id, String name, String description,String image) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        category.setName(name);
        category.setDescription(description);
        category.setImage(image);
        return categoryRepository.save(category);
    }


    @Override
    public void delete(String name) {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException();
        categoryRepository.deleteByName(name);
    }

    @Override
    public void deleteById(Long id) {
        if(this.productService.findAll().stream().filter(p -> p.getCategory().getId().equals(id)).findFirst().isPresent()) {
            throw new CategoryIsAssignedToProduct();
        }
        this.categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> findById(Long id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        return Optional.of(category);
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public List<Category> searchCategories(String text) {
        return categoryRepository.findAllByNameLike(text);
    }
}
