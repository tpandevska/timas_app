package mk.com.timas.repository;

import mk.com.timas.bootstrap.DataHolder;
import mk.com.timas.model.ShoppingCart;
import mk.com.timas.model.User;
import mk.com.timas.model.enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);

}

