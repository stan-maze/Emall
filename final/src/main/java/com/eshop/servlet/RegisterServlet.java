package com.eshop.servlet;

import com.eshop.model.entity.User;
import com.eshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {

    private UserService userService;

    public RegisterServlet() {
        userService = new UserService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Render the user registration form
        request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the user input from the form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Create a new user object with the input data
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        // Call the UserService to register the new user
        try {
            userService.insertUser(user);
//            request.setAttribute("message", "Registration successful! Please log in.");
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("/pages/home.jsp").forward(request, response);
//            dao必须要抛出异常才可以 捕获到sql Exception
        } catch (Exception e) {
            request.setAttribute("message", "注册失败, 该邮箱已被占用");
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
        }
    }
}
