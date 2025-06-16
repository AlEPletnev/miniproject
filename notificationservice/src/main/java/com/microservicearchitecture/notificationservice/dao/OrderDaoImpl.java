package com.microservicearchitecture.notificationservice.dao;

import com.microservicearchitecture.notificationservice.dto.OrderRequest1;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class OrderDaoImpl implements OrderDao{

    //private final String url = "jdbc:mysql://localhost:3306/app_db";
    private final String url = "jdbc:mysql://mysql:3306/app_db";

    private final String user = "app_user";

    private final String password = "userpass";

    @Override
    public void addOrderRequest(OrderRequest1 orderRequest1) {
        String sql = "INSERT INTO orders (product_id, quantity, user_email) VALUES (?,?,?)";
        try(Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,orderRequest1.getProductId());
            preparedStatement.setInt(2,orderRequest1.getQuantity());
            preparedStatement.setString(3,orderRequest1.getUserEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.err.println("Error saving order: " + e.getMessage());
        }
    }

    @Override
    public OrderRequest1 getOrderRequestById(String productId) {
        String sql = "SELECT product_id, quantity, user_email FROM orders WHERE product_id = ?";
        try(Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1,productId);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    OrderRequest1 order = new OrderRequest1();
                    order.setProductId(resultSet.getString("product_id"));
                    order.setQuantity(resultSet.getInt("quantity"));
                    order.setUserEmail(resultSet.getString("user_email"));
                    return order;
                }
            }
        } catch (SQLException e){
            System.err.println("Error fetching order by product_id: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void updateOrderRequest(OrderRequest1 orderRequest1) {
        String sql = "UPDATE orders SET quantity = ?, user_email = ? WHERE product_id = ?";
        try(Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, orderRequest1.getQuantity());
            preparedStatement.setString(2, orderRequest1.getUserEmail());
            preparedStatement.setString(3, orderRequest1.getProductId());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.err.println("Error updating order: " + e.getMessage());
        }
    }

    @Override
    public void deleteOrderRequest(String productId) {
        String sql = "DELETE FROM orders WHERE product_id = ?";
        try(Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.err.println("Error deleting order: " + e.getMessage());
        }
    }

    public void readAll(){
        System.out.println("testReadAll");
        try(Connection connection1 = DriverManager.getConnection(url,user,password);
            Statement stm = connection1.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM orders")){
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("product_id");
                String surname = rs.getString("quantity");
                String salary = rs.getString("user_email");
                System.out.println("id:" + id);
                System.out.println("name:" + name);
                System.out.println("surname:" + surname);
                System.out.println("salary:" + salary);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
