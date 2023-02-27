package com.eshop.model.DAO;

import com.eshop.model.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    User getUserById(int id);
    List<User> getAllUsers();
    void insertUser(User user) throws SQLException;
    void updateUser(User user);
    void deleteUser(int id);
    int getnewUserId();
    User getUserByEmail(String email);
}
