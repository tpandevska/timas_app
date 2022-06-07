package mk.com.timas.service;

import mk.com.timas.model.Product;
import mk.com.timas.model.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

    public interface ProductService {

        List<Product> findAll();

        Optional<Product> findById(Long id);

        Optional<Product> findByName(String name);

        List<Product> findByCategory(String name);

        List<Product> findByCategoryId(Long id);

        //Optional<Product> save(String name, Double price, Integer quantity,String code,Long barcode,String size, String material,String weight, String description,Long category, Long manufacturer);

        Product save(Product product, MultipartFile image) throws IOException;

        Optional<Product> save(ProductDto productDto);

        //Optional<Product> edit(Long id, String name, Double price, Integer quantity,String code,Long barcode,String size, String material,String weight, String description, Long category, Long manufacturer, MultipartFile image) throws IOException;

        Optional<Product> edit(Long id, Product product, MultipartFile image) throws IOException;

        Optional<Product> edit(Long id, ProductDto productDto);

        void deleteById(Long id);
    }


