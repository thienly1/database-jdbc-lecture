package se.lexicon.exercies.collections;

import se.lexicon.exercies.AbstractDAO;
import se.lexicon.exercies.MySqlConnection;
import se.lexicon.exercies.interfaces.ProductDAO;
import se.lexicon.exercies.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAOImpl extends AbstractDAO implements ProductDAO {
    public static Product mapToProduct(ResultSet resultSet) throws SQLException{
        return new Product(resultSet.getInt(1),resultSet.getString(2),
                resultSet.getDouble(3));
    }
    @Override
    public Product save(Product product) {
        String saveProductToList = "INSERT INTO product(name, price) VALUES(?, ?)";
        Product result = null;
        ResultSet keySet = null;
        Connection connection =null;
        PreparedStatement statement = null;
        try{
            connection= getConnection();
            statement = connection.prepareStatement(saveProductToList, Statement.RETURN_GENERATED_KEYS);
            // insert into student WHERE id= 1000 AND first_name LIKE ?
            statement.setString(1, product.getName());
            // insert into product WHERE id = 1000 AND first_name = Simon
            statement.setDouble(2,product.getPrice());
            statement.execute();
            keySet = statement.getGeneratedKeys();
            while(keySet.next()){
                result = new Product(keySet.getInt(1), product.getName(), product.getPrice());

            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(keySet, statement, connection);
        }
            return result;
    }

    @Override
    public Optional<Product> findById(Integer id) {
        // SELECT *FROM product where id= 3
        String findByIdQuery = "SELECT * FROM product where id= ?";
        Optional<Product> product = Optional.empty();
        Connection connection =null;
        PreparedStatement statement = null;
        ResultSet resultSet= null;

        try{
            connection = getConnection();
            statement = connection.prepareStatement(findByIdQuery);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                product = Optional.of(mapToProduct(resultSet));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet,statement,connection);
        }
        return product;
    }

    @Override
    public List<Product> findAll(Product product) {
        String findAllProduct= "SELECT * FROM product";
        List<Product> products1= new ArrayList<>();
        Connection connection =null;
        Statement statement = null;
        ResultSet resultSet= null;
        try{
            connection = MySqlConnection.sqlConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(findAllProduct);

            while(resultSet.next()){
                products1.add(new Product(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3) ));

            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet,statement,connection);
        }
        return products1;
    }

    @Override
    public void delete(Integer id) {
        int rowsAffected = 0;
        String DELETE_QUERY = "DELETE FROM product WHERE id= ?";
        Connection connection =null;
        PreparedStatement preparedStatement = null;

        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1, id);
            rowsAffected = preparedStatement.executeUpdate();

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(preparedStatement, connection);
        }
        if(rowsAffected == 0){
            System.out.println("not found");
        }
        System.out.println("removed from table");
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> matchedName= new ArrayList<>();
        String findNameFromTable = "SELECT * FROM product WHERE name LIKE ?";
        Connection connection =null;
        PreparedStatement statement = null;
        ResultSet resultSet= null;

        try{
            connection = MySqlConnection.sqlConnection();
            statement = connection.prepareStatement(findNameFromTable);

            statement.setString(1, name);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                matchedName.add(mapToProduct(resultSet));
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet,statement,connection);
        }
        return matchedName;
    }

    @Override
    public List<Product> findByPriceBetween(double low, double high) {
        List<Product> matchedName= new ArrayList<>();
        String findByLowPrice = " SELECT * FROM product WHERE price > ? AND price <?";
        Connection connection =null;
        PreparedStatement statement1 = null;
        ResultSet resultSet1= null;
        try{
            connection = MySqlConnection.sqlConnection();
            statement1 = connection.prepareStatement(findByLowPrice);
            statement1.setDouble(1, low);
            statement1.setDouble(2, high);
            resultSet1 = statement1.executeQuery();

            while(resultSet1.next()){
                matchedName.add(mapToProduct(resultSet1));
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet1,statement1,connection);
        }
        return matchedName;

    }

}
