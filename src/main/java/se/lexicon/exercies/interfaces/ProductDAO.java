package se.lexicon.exercies.interfaces;

import se.lexicon.exercies.model.Product;

import java.util.List;

public interface ProductDAO extends GenericCRUD<Product, Integer> {
    public List<Product> findByName(String name);
    public List<Product> findByPriceBetween(double low, double high);

}
