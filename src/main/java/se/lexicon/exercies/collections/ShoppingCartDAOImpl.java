package se.lexicon.exercies.collections;

import javafx.util.converter.LocalDateTimeStringConverter;
import se.lexicon.exercies.AbstractDAO;
import se.lexicon.exercies.MySqlConnection;
import se.lexicon.exercies.interfaces.ShoppingCartDAO;
import se.lexicon.exercies.model.ShoppingCart;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCartDAOImpl extends AbstractDAO implements ShoppingCartDAO {

    public static ShoppingCart mapToShoppingCart(ResultSet resultSet) throws SQLException {
        return new ShoppingCart(resultSet.getInt(1), resultSet.getTimestamp(2).toLocalDateTime(), resultSet.getString(3),
                resultSet.getString(4), resultSet.getString(5));

    }
    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        int rowAffected = 0;
        ShoppingCart result = null;
        Connection connection = null;
        PreparedStatement statement= null;
        ResultSet keySet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("INSERT INTO shopping_cart (last_update, order_status, delivery_address, customer_reference) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setTimestamp(1, Timestamp.valueOf(shoppingCart.getLastUpdate()));
            statement.setString(2, shoppingCart.getOrderStatus());
            statement.setString(3, shoppingCart.getDeliveryAddress());
            statement.setString(4, shoppingCart.getCustomerReference());
            rowAffected = statement.executeUpdate();
            keySet = statement.getGeneratedKeys();
            while (keySet.next()){
                result = new ShoppingCart(keySet.getInt(1), shoppingCart.getLastUpdate(),shoppingCart.getOrderStatus(),
                        shoppingCart.getDeliveryAddress(),shoppingCart.getCustomerReference());
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(keySet,statement,connection);

        }

        if(rowAffected ==0){
            return null;
        }
        return shoppingCart;
    }

    @Override
    public Optional<ShoppingCart> findById(Integer integer) {
        Optional<ShoppingCart> shoppingCart = Optional.empty();
        Connection connection = null;
        PreparedStatement statement= null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM shopping_cart WHERE id = ?");
            statement.setInt(1, integer);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                shoppingCart = Optional.of(mapToShoppingCart(resultSet));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet,statement,connection);
        }
        return shoppingCart;
    }

    @Override
    public List<ShoppingCart> findAll(ShoppingCart shoppingCart) {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM contact_info");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                shoppingCarts.add(mapToShoppingCart(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            closeAll(resultSet,preparedStatement,connection);
        }
        return shoppingCarts;
    }
    @Override
    public void delete(Integer id) {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statement= null;

        try {
            connection= getConnection();
            statement = connection.prepareStatement("DELETE FROM contact_info WHERE id = ?");
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
        System.out.println("removed from the table");
    }

    @Override
    public List<ShoppingCart> findByOrderStatus(String status) {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement= null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM contact_info WHERE order_status = ?");
            statement.setString(1, status);
            rs = statement.executeQuery();
            while(rs.next()){
                shoppingCarts.add(mapToShoppingCart(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            closeAll(rs, statement,connection);
        }
        return shoppingCarts;
    }

    @Override
    public List<ShoppingCart> findByReference(String customer) {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement= null;
        ResultSet rs = null;

        try {
            connection = MySqlConnection.sqlConnection();
            statement = connection.prepareStatement("SELECT * FROM contact_info WHERE customer_reference = ?");
            statement.setString(1, customer);
            rs = statement.executeQuery();
            while(rs.next()){
                shoppingCarts.add(mapToShoppingCart(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            closeAll(rs, connection, statement);
        }
        return shoppingCarts;
    }
}
