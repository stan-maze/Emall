package dao;

import com.eshop.model.DAO.UserDAO;
import com.eshop.model.DAO.impl.UserDAOImpl;
import com.eshop.model.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class UserDAOTest {

    private UserDAO userDAO;

    @Before
    public void setUp() throws Exception {
        // Set up the UserDAO implementation
        userDAO = new UserDAOImpl();
    }

    @Test
    public void testGetNewUserId() {
        List<User> allUsers = userDAO.getAllUsers();
        int newUserId = userDAO.getnewUserId();
        assertEquals(newUserId, allUsers.size()+1);
    }

    @Test
    public void testInsertUser() throws SQLException {
        // Create a new user with a generated ID
        int newUserId = userDAO.getnewUserId();
        User newUser = new User(newUserId, "Test User", "test@example.com", "password");
        userDAO.insertUser(newUser);

        // Verify that the new user was inserted
        User insertedUser = userDAO.getUserById(newUserId);
        assertNotNull(insertedUser);
        assertEquals(newUserId, insertedUser.getId());
        assertEquals("Test User", insertedUser.getName());
        assertEquals("test@example.com", insertedUser.getEmail());
        assertEquals("password", insertedUser.getPassword());
    }

    @Test
    public void testUpdateUser() {
        User user = userDAO.getUserById(1);
        assertNotNull(user);

        user.setName("John Doe");
        user.setEmail("johndoe@example.com");
        user.setPassword("newpassword");
        userDAO.updateUser(user);

        User updatedUser = userDAO.getUserById(1);
        assertNotNull(updatedUser);
        assertEquals("John Doe", updatedUser.getName());
        assertEquals("johndoe@example.com", updatedUser.getEmail());
        assertEquals("newpassword", updatedUser.getPassword());
    }

    @Test
    public void testDeleteUser() {
        userDAO.deleteUser(2);
        assertNull(userDAO.getUserById(2));
    }
}
