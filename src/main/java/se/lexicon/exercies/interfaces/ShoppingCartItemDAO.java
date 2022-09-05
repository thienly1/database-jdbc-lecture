package se.lexicon.exercies.interfaces;

import se.lexicon.exercies.model.ShoppingCartItem;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartItemDAO {

    public ShoppingCartItem save(ShoppingCartItem cartItem);
    public Optional<ShoppingCartItem> findById(int id);
    public List<ShoppingCartItem> findAll();
    public List<ShoppingCartItem> findByCartId( int cartId);
    public List<ShoppingCartItem> findByProductId( int productId);
    public void delete(int id);

}
