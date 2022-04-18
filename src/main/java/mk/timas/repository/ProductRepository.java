package mk.timas.repository;

import mk.timas.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    List<Product> findAllByOrderByName();
    void deleteByName(String name);
    List<Product> findAllByOrderByPriceAsc();
    List<Product> findAllByOrderByPriceDesc();
    List<Product> findAllByCategory_Name(String categoryName);
    List<Product> findProductsByCategoryId(Long categoryId);

}

