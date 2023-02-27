package com.eshop.servlet;
import com.eshop.model.entity.User;
import com.eshop.service.UserService;
import com.eshop.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AccountServlet/*")
public class AccountServlet extends HttpServlet {

    private UserService userService;

    public AccountServlet() {
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }

        switch (action) {
            case "/password":
                changePassword(request, response);
                break;
            case "/email":
                changeEmail(request, response);
                break;
        }
    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmNewPassword = request.getParameter("confirmPassword");
        User user = (User) request.getSession().getAttribute("user");
        if (!user.getPassword().equals(oldPassword)) {
            response.sendRedirect(request.getContextPath() + "/pages/account.jsp?formType=password&error=1");
            return;
        }
        if (!newPassword.equals(confirmNewPassword)) {
            response.sendRedirect(request.getContextPath() + "/pages/account.jsp?formType=password&error=2");
            return;
        }
        user.setPassword(newPassword);
        userService.updateUser(user);
        response.sendRedirect(request.getContextPath() + "/pages/account.jsp?formType=password&pswsuccess=1");
        return;
    }

    private void changeEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newEmail = request.getParameter("newEmail");

        User user = (User) request.getSession().getAttribute("user");
        user.setEmail(newEmail);
        userService.updateUser(user);

        String subject = "邮箱更新确认";
        String message = "尊敬的" + user.getName() + "\n\t 你成功将你的eMall邮箱更新为 " + newEmail + ".";
        try {
            EmailUtil.sendEmail(user.getEmail(), subject, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        request.setAttribute("emailsuccess", "Email updated successfully.");
//        request.getRequestDispatcher(request.getHeader("Referer") + "?formType=email").forward(request, response);
        response.sendRedirect(request.getContextPath() + "/pages/account.jsp?formType=profile&emailsuccess=1");
    }
}
