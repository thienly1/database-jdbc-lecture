package se.lexicon.exercies.collections;

import se.lexicon.exercies.AbstractDAO;
import se.lexicon.exercies.MySqlConnection;
import se.lexicon.exercies.interfaces.ProductDAO;
import se.lexicon.exercies.interfaces.ShoppingCartDAO;
import se.lexicon.exercies.interfaces.ShoppingCartItemDAO;
import se.lexicon.exercies.model.ShoppingCartItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCartItemDAOImpl extends AbstractDAO implements ShoppingCartItemDAO {

   private static ShoppingCartItem mapToShoppingCartItem (ResultSet resultSet) throws SQLException {
       ProductDAO productDAO = new ProductDAOImpl();
       ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAOImpl();
       return new ShoppingCartItem(resultSet.getInt(1),resultSet.getInt(2), resultSet.getDouble(3),
                productDAO.findById(resultSet.getInt(4)).get(), shoppingCartDAO.findById(resultSet.getInt(5)).get());
 }

    @Override
    public ShoppingCartItem save(ShoppingCartItem cartItem) {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statement= null;
        ResultSet keySet = null;
        ShoppingCartItem shoppingCartItem = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("INSERT INTO shopping_cart_item (amount, total_price, product_id, shopping_cart_id) VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, cartItem.getAmount());
            statement.setDouble(2, cartItem.getTotalPrice());
            statement.setObject(3, cartItem.getItem());
            statement.setObject(4, cartItem.getShoppingCart());
            rowsAffected = statement.executeUpdate();
            keySet = statement.getGeneratedKeys();
                    while(keySet.next()){
                        shoppingCartItem = new ShoppingCartItem(keySet.getInt("id"), cartItem.getAmount(), cartItem.getTotalPrice(),
                                cartItem.getItem(), cartItem.getShoppingCart());
                    }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            closeAll(keySet,statement,connection);
        }
        if(rowsAffected == 0){
            return null;
        }
        return shoppingCartItem;
    }

    @Override
      public Optional<ShoppingCartItem> findById(int id) {
        Optional<ShoppingCartItem> found = Optional.empty();
        String findShoppingCartItem = "SELECT * FROM shopping_cart_item WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement= null;
        ResultSet rs = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(findShoppingCartItem);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while(rs.next()){
                found = Optional.of(mapToShoppingCartItem(rs));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(rs, statement,connection);
        }
           return found;

    }
    @Override
    public List<ShoppingCartItem> findAll() {
       List<ShoppingCartItem> cartItems = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement= null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM shopping_cart_item");
            rs = statement.executeQuery();
            while (rs.next()){
                cartItems.add(mapToShoppingCartItem(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            closeAll(rs, statement,connection);
        }
        return cartItems;
    }

    @Override
    public List<ShoppingCartItem> findByCartId(int cartId) {
       List<ShoppingCartItem> cartItems = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement= null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM shopping_cart_item WHERE shopping_cart_id= ?");
            statement.setInt(1, cartId);
            rs = statement.executeQuery();
            while (rs.next()){
                cartItems.add(mapToShoppingCartItem(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            closeAll(rs, statement,connection);
        }
        return cartItems;
    }

    @Override
    public List<ShoppingCartItem> findByProductId(int productId) {
        List<ShoppingCartItem> cartItems = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement= null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM shopping_cart_item WHERE product_id= ?");
            statement.setInt(1, productId);
            rs = statement.executeQuery();
            while (rs.next()){
                cartItems.add(mapToShoppingCartItem(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            closeAll(rs, statement,connection);
        }
        return cartItems;
    }

    @Override
    public void delete(int id) {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statement= null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM shopping_cart_item WHERE id= ?");
            statement.setInt(1, id);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            closeAll(statement,connection);
        }
        if(rowsAffected == 0){
            System.out.println("not found");
        }
        System.out.println("removed");
    }
}
