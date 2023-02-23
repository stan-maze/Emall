package Emall.DAO.impl;

import Emall.DAO.OrderDAO;
import Emall.entity.Order;
import Emall.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private Connection connection;

    public OrderDAOImpl() {
        connection = DbUtil.getConnection();
    }
    @Override
    public int createOrder(Order order) {
        int id = -1;
        try {
            String query = "INSERT INTO `order` (customer_id, product_id, quantity, total_price, shipping_address) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, order.getCustomerId());
            statement.setInt(2, order.getProductId());
            statement.setInt(3, order.getQuantity());
            statement.setDouble(4, order.getTotalPrice());
            statement.setString(5, order.getShippingAddress());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating customer failed, no rows affected.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return  id;
    }

    @Override
    public Order getOrderById(int orderId) {
        Order order = null;
        try {
            String query = "SELECT * FROM `order` WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setCustomerId(resultSet.getInt("customer_id"));
                order.setProductId(resultSet.getInt("product_id"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setTotalPrice(resultSet.getDouble("total_price"));
                order.setShippingAddress(resultSet.getString("shipping_address"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            String query = "SELECT * FROM `order`";
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setCustomerId(resultSet.getInt("customer_id"));
                order.setProductId(resultSet.getInt("product_id"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setTotalPrice(resultSet.getDouble("total_price"));
                order.setShippingAddress(resultSet.getString("shipping_address"));
                orders.add(order);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersByCustomerId(int customerId) {
        List<Order> orders = new ArrayList<>();
        try {
            String query = "SELECT * FROM `order` WHERE customer_id = ?";
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setCustomerId(resultSet.getInt("customer_id"));
                order.setProductId(resultSet.getInt("product_id"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setTotalPrice(resultSet.getDouble("total_price"));
                order.setShippingAddress(resultSet.getString("shipping_address"));
                orders.add(order);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return orders;
    }

    @Override
    public int updateOrder(Order order) {
        int id = -1;
        try {
            String query = "UPDATE `order` SET customer_id = ?, product_id = ?, quantity = ?, total_price = ?, " +
                    "shipping_address = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, order.getCustomerId());
            statement.setInt(2, order.getProductId());
            statement.setInt(3, order.getQuantity());
            statement.setDouble(4, order.getTotalPrice());
            statement.setString(5, order.getShippingAddress());
            statement.setInt(6, order.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating customer failed, no rows affected.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return id;
    }
    @Override
    public void deleteOrder(int orderId) {
        try {
            String query = "DELETE FROM `order` WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, orderId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}

