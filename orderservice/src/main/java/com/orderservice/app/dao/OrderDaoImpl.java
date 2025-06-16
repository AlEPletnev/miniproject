package com.orderservice.app.dao;

import com.orderservice.app.dto.OrderRequest;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class OrderDaoImpl implements OrderDao{

    private final String url = "jdbc:postgresql://postgres:5432/order_service_db";

    private final String user = "postgres";

    private final String pass = "1234";

    @Override
    public void addOrder(OrderRequest order) {
        String sql = "INSERT INTO order_request (product_id, quantity, user_email, buyer_id) VALUES (?,?,?,?)";
        try(Connection connection = DriverManager.getConnection(url,user,pass);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, order.productId());
            preparedStatement.setInt(2,order.quantity());
            preparedStatement.setString(3,order.userEmail());
            preparedStatement.setString(4, order.buyerId());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.err.println("Error saving order: " + e.getMessage());
        }
    }

    @Override
    public OrderRequest getOrderRequest(String productId) {
       String sql = "SELECT product_id, quantity, user_email, buyer_id FROM order_request WHERE product_id = ?";
       try(Connection connection = DriverManager.getConnection(url,user,pass);
           PreparedStatement preparedStatement = connection.prepareStatement(sql)){
           preparedStatement.setString(1,productId);
           try(ResultSet resultSet = preparedStatement.executeQuery()){
               if(resultSet.next()){
                    return new OrderRequest(resultSet.getString("product_id"),
                                                          resultSet.getInt("quantity"),
                                                          resultSet.getString("user_email"),
                                                          resultSet.getString("buyer_id"));
                }
            }
        } catch (SQLException e){
            System.err.println("Error fetching order by product_id: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void updateOrderRequest(OrderRequest order) {
        String sql = "UPDATE order_request SET quantity = ?, user_email = ? WHERE product_id = ?";
        try(Connection connection = DriverManager.getConnection(url,user,pass);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, order.quantity());
            preparedStatement.setString(2, order.userEmail());
            preparedStatement.setString(3, order.productId());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.err.println("Error updating order: " + e.getMessage());
        }
    }

    @Override
    public void deleteOrderRequest(String productId) {
        String sql = "DELETE FROM order_request WHERE product_id = ?";
        try(Connection connection = DriverManager.getConnection(url,user,pass);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.err.println("Error deleting order: " + e.getMessage());
        }
    }
}
