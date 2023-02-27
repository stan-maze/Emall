package service;

import com.eshop.model.entity.User;
import com.eshop.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {
    private UserService userService;
    private User testUser;
    @Before
    public void setUp() {
        userService = new UserService();
        testUser = new User();
        String email = "johndoe@example.com" + new Date();
        testUser.setName("John Doe");
        testUser.setEmail(email);
        testUser.setPassword("password");
    }
    @After
    public void tearDown() {
        userService.deleteUser(testUser.getId());
    }
    @Test
    public void testInsertUser() throws SQLException {
        userService.insertUser(testUser);
        User actualUser = userService.getUserById(testUser.getId());
        assertNotNull(actualUser);
        assertEquals(testUser.getName(), actualUser.getName());
        assertEquals(testUser.getEmail(), actualUser.getEmail());
        assertEquals(testUser.getPassword(), actualUser.getPassword());
    }
    @Test
    public void testUpdateUser() throws SQLException {
        userService.insertUser(testUser);
        testUser.setName("Jane Doe");
        userService.updateUser(testUser);
        User actualUser = userService.getUserById(testUser.getId());
        assertEquals(testUser.getName(), actualUser.getName());
        assertEquals(testUser.getEmail(), actualUser.getEmail());
        assertEquals(testUser.getPassword(), actualUser.getPassword());
    }
    @Test
    public void testGetAllUsers() throws SQLException {
        userService.insertUser(testUser);
        List<User> users = userService.getAllUsers();
        assertTrue(users.size() > 0);
    }
    @Test
    public void testDeleteUser() throws SQLException {
        userService.insertUser(testUser);
        userService.deleteUser(testUser.getId());
        User actualUser = userService.getUserById(testUser.getId());
        assertNull(actualUser);
    }
    @Test
    public void testLogin() throws SQLException {
        userService.insertUser(testUser);
        User actualUser = userService.login(testUser.getEmail(), testUser.getPassword());
        assertNotNull(actualUser);
        assertEquals(testUser.getName(), actualUser.getName());
        assertEquals(testUser.getEmail(), actualUser.getEmail());
        assertEquals(testUser.getPassword(), actualUser.getPassword());
    }
}
