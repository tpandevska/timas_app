package mk.com.timas.service;

import mk.com.timas.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

        Category create(String name, String description, String image);

        Category update(Long id, String name, String description, String image);

        void delete(String name);
        void deleteById(Long id);

        Optional<Category> findById(Long id);
        List<Category> findAll();
        List<Category> findAllOrderByNameAsc();
        List<Category> searchCategories(String searchText);

    }
