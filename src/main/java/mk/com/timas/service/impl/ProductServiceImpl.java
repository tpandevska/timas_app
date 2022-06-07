package mk.com.timas.service.impl;

import mk.com.timas.model.Category;
import mk.com.timas.model.Manufacturer;
import mk.com.timas.model.Product;
import mk.com.timas.model.dto.ProductDto;
import mk.com.timas.model.exceptions.CategoryNotFoundException;
import mk.com.timas.model.exceptions.ManufacturerNotFoundException;
import mk.com.timas.model.exceptions.ProductNotFoundException;
import mk.com.timas.repository.CategoryRepository;
import mk.com.timas.repository.ManufacturerRepository;
import mk.com.timas.repository.ProductRepository;
import mk.com.timas.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ManufacturerRepository manufacturerRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    public List<Product> findByCategory(String name) {
        return this.productRepository.findAllByCategory_Name(name);
    }

    @Override
    public List<Product> findByCategoryId(Long id) {
        //return this.productRepository.findProductsByCategoryId(id);
        List<Product> products = this.productRepository.findAll();
       return products.stream().filter(p -> p.getCategory().getId().equals(id)).collect(Collectors.toList());
    }

/*    @Override
    @Transactional
    public Optional<Product> save(String name, Double price, Integer quantity,String code,Long barcode,String size, String material,String weight, String description, Long categoryId, Long manufacturerId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        this.productRepository.deleteByName(name);
        return Optional.of(this.productRepository.save(new Product(name, price, quantity,code,barcode,size, material,weight,description, category, manufacturer)));
    }*/


    @Override
    @Transactional
    public Product save(Product product, MultipartFile image) throws IOException {

        Category category = this.categoryRepository.findById(product.getCategory().getId())
                .orElseThrow(() -> new CategoryNotFoundException(product.getCategory().getId()));
        product.setCategory(category);
        Manufacturer manufacturer = this.manufacturerRepository
                .findById(product.getManufacturer().getId())
                .orElseThrow(() -> new ManufacturerNotFoundException(product.getManufacturer().getId()));
        product.setManufacturer(manufacturer);

        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            product.setImageBase64(base64Image);
        }
        else
            product.setImageBase64("");
        return this.productRepository.save(product);
    }

    @Override
    public Optional<Product> save(ProductDto productDto) {
        Category category = this.categoryRepository.findById(productDto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategory()));
        Manufacturer manufacturer = this.manufacturerRepository.findById(productDto.getManufacturer())
                .orElseThrow(() -> new ManufacturerNotFoundException(productDto.getManufacturer()));

        this.productRepository.deleteByName(productDto.getName());
        return Optional.of(this.productRepository.save(new Product(productDto.getName(), productDto.getPrice(), productDto.getQuantity(), productDto.getCode(),productDto.getBarcode(),productDto.getMaterial(), productDto.getSize(), productDto.getWeight(),productDto.getDescription(), category, manufacturer)));
    }
/*

    @Override
    @Transactional
    public Optional<Product> edit(Long id, String name, Double price, Integer quantity,String code,Long barcode,String size, String material,String weight, String description, Long categoryId, Long manufacturerId, MultipartFile image) throws IOException {

        Product product = this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCode(code);
        product.setBarcode(barcode);
        product.setMaterial(material);
        product.setSize(size);
        product.setWeight(weight);
        product.setDescription(description);

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        product.setCategory(category);

        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        product.setManufacturer(manufacturer);

        if(image != null && image.getOriginalFilename()!="" && !image.getName().equals("")) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            product.setImageBase64(base64Image);
        }
        else {
            product.setImageBase64(product.getImageBase64());
        }


        return Optional.of(this.productRepository.save(product));
    }
*/

    @Override
    @Transactional
    public Optional<Product> edit(Long id, Product product, MultipartFile image) throws IOException {

        Product p =  this.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        Manufacturer manufacturer = this.manufacturerRepository
                .findById(product.getManufacturer().getId())
                .orElseThrow(() -> new ManufacturerNotFoundException(product.getManufacturer().getId()));
        p.setManufacturer(manufacturer);
        Category category = this.categoryRepository.findById(product.getCategory().getId())
                .orElseThrow(() -> new CategoryNotFoundException(product.getCategory().getId()));
        p.setCategory(category);

        p.setName(product.getName());
        p.setPrice(product.getPrice());
        p.setQuantity(product.getQuantity());
        p.setCode(product.getCode());
        p.setMaterial(product.getMaterial());
        p.setBarcode(product.getBarcode());
        p.setSize(product.getSize());
        p.setWeight(product.getWeight());
        p.setDescription(product.getDescription());

        if(image != null && image.getOriginalFilename()!="" && !image.getName().equals("")) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            p.setImageBase64(base64Image);
        }
        else {
            p.setImageBase64(product.getImageBase64());
        }

        //this.productRepository.deleteById(p.getId());
        return Optional.of(this.productRepository.save(p));

    }

    @Override
    public Optional<Product> edit(Long id, ProductDto productDto) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setImageBase64(productDto.getImage());
        product.setCode(productDto.getCode());
        product.setSize(productDto.getSize());
        product.setMaterial(productDto.getMaterial());
        product.setBarcode(productDto.getBarcode());
        product.setWeight(productDto.getWeight());
        product.setDescription(productDto.getDescription());

        Category category = this.categoryRepository.findById(productDto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategory()));
        product.setCategory(category);

        Manufacturer manufacturer = this.manufacturerRepository.findById(productDto.getManufacturer())
                .orElseThrow(() -> new ManufacturerNotFoundException(productDto.getManufacturer()));
        product.setManufacturer(manufacturer);

        return Optional.of(this.productRepository.save(product));
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
