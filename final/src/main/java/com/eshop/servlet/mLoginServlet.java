package com.eshop.servlet;

import com.eshop.model.entity.Product;
import com.eshop.model.entity.User;
import com.eshop.service.ProductService;
import com.eshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/mLoginServlet/*")
public class mLoginServlet extends HttpServlet {

    private UserService userService;

    public mLoginServlet() {
        userService = new UserService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // servlet里写绝对路径好些, 要带jsp
//        request.getRequestDispatcher("/pages/mlogin.jsp").forward(request, response);
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mid = request.getParameter("mid");
        String mpassword = request.getParameter("mpassword");
        if (mid.equals("1001") && mpassword.equals("123456")) {
            request.getSession().setAttribute("mlogin", "1");
            request.getSession().setAttribute("mid", mid);
            response.sendRedirect(request.getContextPath() + "/pages/mmenu.jsp");

        } else {
            System.out.println("login faild");
            request.setAttribute("message", "Login failed: invalid id or password.");
            request.getRequestDispatcher("/pages/mlogin.jsp").forward(request, response);
        }
    }
}
