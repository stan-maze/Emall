package com.eshop.model.DAO.impl;

import com.eshop.model.DAO.CartItemDAO;
import com.eshop.model.entity.CartItem;
import com.eshop.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAOImpl implements CartItemDAO {

    private Connection conn;

    public CartItemDAOImpl() {
        conn = DbUtil.getConnection();
//        conn = JDBCUtil.getConnection();
    }

    @Override
    public CartItem getCartItemById(int id) {
        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM cart_item WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    CartItem cartItem = new CartItem();
                    cartItem.setId(resultSet.getInt("id"));
                    cartItem.setUser(new UserDAOImpl().getUserById(resultSet.getInt("uid")));
                    cartItem.setProduct(new ProductDAOImpl().getProductById(resultSet.getInt("pid")));
                    cartItem.setOrderId(resultSet.getInt("oid"));
                    cartItem.setQuantity(resultSet.getInt("quantity"));
                    cartItem.setTotal(resultSet.getDouble("total"));
                    return cartItem;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CartItem> getCartItemsByOrderId(int orderId) {
        List<CartItem> cartItems = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM cart_item WHERE oid = ?")) {
            statement.setInt(1, orderId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    CartItem cartItem = new CartItem();
                    cartItem.setId(resultSet.getInt("id"));
                    cartItem.setUser(new UserDAOImpl().getUserById(resultSet.getInt("uid")));
                    cartItem.setProduct(new ProductDAOImpl().getProductById(resultSet.getInt("pid")));
                    cartItem.setOrderId(orderId);
                    cartItem.setQuantity(resultSet.getInt("quantity"));
                    cartItem.setTotal(resultSet.getDouble("total"));
                    cartItems.add(cartItem);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartItems;
    }

    @Override
    public List<CartItem> getCartItemsByUserId(int userId) {
        List<CartItem> cartItems = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM cart_item WHERE uid = ?")) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    CartItem cartItem = new CartItem();
                    cartItem.setId(resultSet.getInt("id"));
                    cartItem.setUser(new UserDAOImpl().getUserById(resultSet.getInt("uid")));
                    cartItem.setProduct(new ProductDAOImpl().getProductById(resultSet.getInt("pid")));
                    cartItem.setOrderId(resultSet.getInt("oid"));
                    cartItem.setQuantity(resultSet.getInt("quantity"));
                    cartItem.setTotal(resultSet.getDouble("total"));
                    cartItems.add(cartItem);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartItems;
    }

    @Override
    public List<CartItem> getCartItemsByProductId(int productId) {
        List<CartItem> cartItems = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM cart_item WHERE pid = ?")) {
            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    CartItem cartItem = new CartItem();
                    cartItem.setId(resultSet.getInt("id"));
                    cartItem.setUser(new UserDAOImpl().getUserById(resultSet.getInt("uid")));
                    cartItem.setProduct(new ProductDAOImpl().getProductById(resultSet.getInt("pid")));
                    cartItem.setOrderId(resultSet.getInt("oid"));
                    cartItem.setQuantity(resultSet.getInt("quantity"));
                    cartItem.setTotal(resultSet.getDouble("total"));
                    cartItems.add(cartItem);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartItems;
    }


    @Override
    public List<CartItem> getUncompletedCartItemsByUserId(int userId) {
        List<CartItem> cartItems = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM cart_item WHERE uid = ? and oid = 0")) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    CartItem cartItem = new CartItem();
                    cartItem.setId(resultSet.getInt("id"));
                    cartItem.setUser(new UserDAOImpl().getUserById(resultSet.getInt("uid")));
                    cartItem.setProduct(new ProductDAOImpl().getProductById(resultSet.getInt("pid")));
                    cartItem.setOrderId(userId);
                    cartItem.setQuantity(resultSet.getInt("quantity"));
                    cartItem.setTotal(resultSet.getDouble("total"));
                    cartItems.add(cartItem);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartItems;
    }


    @Override
    public void insertCartItem(CartItem cartItem) {
        try (PreparedStatement statement = conn.prepareStatement("INSERT INTO cart_item (id, uid, pid, quantity, total, oid) VALUES (?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, cartItem.getId());
            statement.setInt(2, cartItem.getUser().getId());
            statement.setInt(3, cartItem.getProduct().getId());
            statement.setInt(4, cartItem.getQuantity());
            statement.setDouble(5, cartItem.getTotal());
            statement.setInt(6, cartItem.getOrderId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        try (PreparedStatement statement = conn.prepareStatement("UPDATE cart_item SET uid = ?, pid = ?, quantity = ?, total = ?, oid = ? WHERE id = ?")) {
            statement.setInt(1, cartItem.getUser().getId());
            statement.setInt(2, cartItem.getProduct().getId());
            statement.setInt(3, cartItem.getQuantity());
            statement.setDouble(4, cartItem.getTotal());
            statement.setInt(5, cartItem.getOrderId());
            statement.setInt(6, cartItem.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCartItem(int id) {
        try (PreparedStatement statement = conn.prepareStatement("DELETE FROM cart_item WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteCartItemsByOrderId(int orderId) {
        String query = "DELETE FROM cart_item WHERE oid = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCartItemsByUserId(int userId) {
        String query = "DELETE FROM cart_item WHERE uid = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public int getnewCartItemId() {
        int maxId= -1;
        String query = "SELECT MAX(id) FROM cart_item";
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
