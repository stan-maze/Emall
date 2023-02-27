package com.eshop.servlet;

import com.eshop.model.entity.User;
import com.eshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet/*")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    public LoginServlet() {
        userService = new UserService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // servlet里写绝对路径好些, 要带jsp
        request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the user input from the form
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        // Call the UserService to authenticate the user
        User user = userService.login(email, password);
        if (user != null) {
            // If the authentication succeeds, store the user object in the session and redirect to home page
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/pages/home.jsp");
        } else {
            // If the authentication fails, display an error message and render the login form again
            request.setAttribute("message", "Login failed: invalid email or password.");
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
        }
    }
}
