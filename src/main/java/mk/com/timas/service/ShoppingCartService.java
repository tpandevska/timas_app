package mk.com.timas.service;

import mk.com.timas.model.Product;
import mk.com.timas.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<Product> listAllProductsInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addProductToShoppingCart(String username, Long productId);
    //void removeProductFromCart(String username,Long productId);
}
