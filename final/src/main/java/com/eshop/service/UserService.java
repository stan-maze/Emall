package com.eshop.service;

import com.eshop.model.DAO.UserDAO;
import com.eshop.model.DAO.impl.UserDAOImpl;
import com.eshop.model.entity.CartItem;
import com.eshop.model.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAOImpl();
    }

    public int getNewUserId() {
        return userDAO.getnewUserId();
    }

    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    public double getUserTotalById(int id) {
        List<CartItem> items = new CartService().getCartItemsByUserId(id);
        double total = 0;
        for(CartItem item:items){
            total += item.getTotal();
        }
        return total;
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public int insertUser(User user) throws SQLException {
//        因为设计的原因这里要抛异常, 用户注册检错机制就是抛异常
        int id = getNewUserId();
        user.setId(id);
        userDAO.insertUser(user);
        return id;
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    public User login(String email, String password) {
        User user = userDAO.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }
}
