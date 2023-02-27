package com.eshop.model.DAO.impl;

import com.eshop.model.DAO.OrderDAO;
import com.eshop.model.entity.CartItem;
import com.eshop.model.entity.MyOrder;
import com.eshop.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private Connection conn;

    public OrderDAOImpl() {
        conn = DbUtil.getConnection();
//        conn = JDBCUtil.getConnection();
    }

    @Override
    public MyOrder getOrderById(int id) {
        MyOrder order = null;
        String query = "SELECT * FROM myorder WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                order = new MyOrder();
                order.setId(rs.getInt("id"));
                order.setUser(new UserDAOImpl().getUserById(rs.getInt("uid")));
                order.setTotal(rs.getDouble("total"));
                order.setCreatedDate(rs.getTimestamp("created_date"));
                order.setItems(new CartItemDAOImpl().getCartItemsByOrderId(rs.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<MyOrder> getOrdersByUserId(int userId) {
        List<MyOrder> orders = new ArrayList<>();
        String query = "SELECT * FROM myorder WHERE uid = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MyOrder order = new MyOrder();
                order.setId(rs.getInt("id"));
                order.setUser(new UserDAOImpl().getUserById(rs.getInt("uid")));
                order.setTotal(rs.getDouble("total"));
                order.setCreatedDate(rs.getTimestamp("created_date"));
                order.setItems(new CartItemDAOImpl().getCartItemsByOrderId(rs.getInt("id")));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void insertOrder(MyOrder order) {
        String query = "INSERT INTO myorder (id, uid, total, created_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, order.getId());
            stmt.setInt(2, order.getUser().getId());
            stmt.setDouble(3, order.getTotal());
            stmt.setTimestamp(4, new java.sql.Timestamp(order.getCreatedDate().getTime()));
            stmt.executeUpdate();
//            CartItem有自己的更新, 在service里保证一致性
//            for (CartItem item : order.getItems()) {
//                new CartItemDAOImpl().insertCartItem(item);
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrder(MyOrder order) {
        String query = "UPDATE myorder SET total = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, order.getTotal());
            stmt.setInt(2, order.getId());
            stmt.executeUpdate();
            new CartItemDAOImpl().deleteCartItemsByOrderId(order.getId());
            for (CartItem item : order.getItems()) {
                new CartItemDAOImpl().insertCartItem(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int id) {
        String query = "DELETE FROM myorder WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            new CartItemDAOImpl().deleteCartItemsByOrderId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getnewOrderId() {
        int maxId= -1;
        String query = "SELECT MAX(id) FROM myorder";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                maxId = rs.getInt(1)+1;
                return maxId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxId;
    }

    public void closeConnection() {
        DbUtil.closeConnection();
//        JDBCUtil.closeConnection(conn);
    }
}