package mk.com.timas.repository;

import mk.com.timas.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByNameLike(String text);
    Optional<Category> findById(Long id);
    void deleteByName(String name);

    List<Category> findAllByOrderByNameAsc();
}

