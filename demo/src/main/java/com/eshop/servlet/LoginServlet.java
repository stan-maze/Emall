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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userService.login(email, password);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/pages/home.jsp");
        } else {
            request.setAttribute("message", "登陆失败, 错误的邮箱或者密码");
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
        }
    }
}
