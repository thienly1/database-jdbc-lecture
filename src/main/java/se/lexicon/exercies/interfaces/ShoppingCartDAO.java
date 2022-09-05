package se.lexicon.exercies.interfaces;

import se.lexicon.exercies.model.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartDAO extends GenericCRUD<ShoppingCart, Integer >{

    public List<ShoppingCart> findByOrderStatus(String status);
    public List<ShoppingCart> findByReference(String customer);



}