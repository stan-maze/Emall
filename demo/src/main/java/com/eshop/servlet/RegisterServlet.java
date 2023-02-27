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
        request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        try {
            userService.insertUser(user);
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("/pages/home.jsp").forward(request, response);
//            dao层必须要抛出异常才可以 捕获到sql Exception
        } catch (Exception e) {
            request.setAttribute("message", "注册失败, 该邮箱已被占用");
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
        }
    }
}
