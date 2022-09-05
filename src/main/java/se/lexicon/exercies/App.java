package se.lexicon.exercies;

import se.lexicon.exercies.collections.ProductDAOImpl;
import se.lexicon.exercies.collections.ShoppingCartDAOImpl;
import se.lexicon.exercies.collections.ShoppingCartItemDAOImpl;
import se.lexicon.exercies.interfaces.ProductDAO;
import se.lexicon.exercies.interfaces.ShoppingCartDAO;
import se.lexicon.exercies.interfaces.ShoppingCartItemDAO;
import se.lexicon.exercies.model.Product;
import se.lexicon.exercies.model.ShoppingCart;

import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) {
        DatabaseCredentials.initialize("database/mySql.properties");
        ProductDAO productDAO = new ProductDAOImpl();
        System.out.println(productDAO.save(new Product( "pepsi", 20)));
        //System.out.println(productDAO.findById(3));
        //System.out.println(productDAO.findAll());
        //System.out.println(productDAO.findByName("ca%"));
        //productDAO.delete(4);
        //System.out.println(productDAO.save(new Product(4, "ice cream", 20)));
        //System.out.println(productDAO.findByPriceBetween(20,60));

        //-------------------------ShoppingCartDAO------------------
        ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAOImpl();
//        System.out.println(shoppingCartDAO.save(new ShoppingCart(0, LocalDateTime.parse("2022-09-16T09:00:00"), "ordered",
//                "Vislanda", "member")));
       // System.out.println(shoppingCartDAO.findById(1));
        ShoppingCartItemDAO shoppingCartItemDAO = new ShoppingCartItemDAOImpl();
       // System.out.println(shoppingCartItemDAO.findById(3));
        //System.out.println(shoppingCartItemDAO.findAll());
        System.out.println(shoppingCartItemDAO.findByCartId(3));
        System.out.println(shoppingCartItemDAO.findByProductId(4));




    }

}
