package com.eshop.servlet;

import com.eshop.model.entity.User;
import com.eshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/mHistoryServlet/*")
public class mHistoryServlet extends HttpServlet {

    private UserService userService;

    public mHistoryServlet() {
        userService = new UserService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
//        确认是否有管理员权限
        if (session.getAttribute("mlogin")==null){
            return;
        }

        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = userService.getUserById(userId);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/pages/mhistory.jsp");
        } else {
            request.setAttribute("message", "Login failed: invalid email or password.");
            request.getRequestDispatcher("/pages/mmenu.jsp").forward(request, response);
        }
    }
}
