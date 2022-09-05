package se.lexicon.exercies.collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.exercies.DatabaseCredentials;
import se.lexicon.exercies.interfaces.ProductDAO;
import se.lexicon.exercies.interfaces.ShoppingCartDAO;
import se.lexicon.exercies.interfaces.ShoppingCartItemDAO;
import se.lexicon.exercies.model.ShoppingCartItem;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartItemDAOImplTest {

    ShoppingCartItemDAO testObject = new ShoppingCartItemDAOImpl();
    private static DatabaseCredentials INSTANCE;
    ProductDAO productDAO = new ProductDAOImpl();
    ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAOImpl();

    @BeforeEach
    public void beforeEach(){
        DatabaseCredentials.initialize("database/mySql.properties");
    }
    @Test
    void save() {

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(30, 50,
                productDAO.findById(3).get(), shoppingCartDAO.findById(5).get());
       ShoppingCartItem returned=  testObject.save(shoppingCartItem);
        System.out.println(returned);
        assertNotNull(returned);
        assertFalse(returned.getId()==0);

    }
    @Test
    void test_find_by_id_successfully(){
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(12, 30, 50,
                productDAO.findById(3).get(), shoppingCartDAO.findById(5).get());
        Optional< ShoppingCartItem> optionalShoppingCartItemDAO = testObject.findById(12);
        assertEquals(optionalShoppingCartItemDAO, Optional.of(shoppingCartItem));

    }
    @Test
    void test_find_by_id_return_null(){
        Optional<ShoppingCartItem> optional = testObject.findById(15);
        assertEquals(Optional.empty(), optional);
    }

}