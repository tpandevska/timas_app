package mk.timas.repository;

import mk.timas.model.ShoppingCart;
import mk.timas.model.User;
import mk.timas.model.enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);

}

