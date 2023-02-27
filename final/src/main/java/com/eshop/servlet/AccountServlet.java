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

        // Get the user object from the session
        User user = (User) request.getSession().getAttribute("user");

        if (!user.getPassword().equals(oldPassword)) {
//            request.setAttribute("error", "The old password is incorrect.");
            response.sendRedirect(request.getContextPath() + "/pages/account.jsp?formType=password&error=1");
            return;
        }
        if (!newPassword.equals(confirmNewPassword)) {
//            request.setAttribute("error", "The new passwords do not match.");
//            request.getRequestDispatcher(request.getHeader("Referer")+ "?formType=password").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/pages/account.jsp?formType=password&error=2");

            return;
        }
        user.setPassword(newPassword);
        userService.updateUser(user);

//        request.setAttribute("pswsuccess", "Password updated successfully.");
//        request.getRequestDispatcher(request.getHeader("Referer")+ "?formType=password").forward(request, response);
        response.sendRedirect(request.getContextPath() + "/pages/account.jsp?formType=password&pswsuccess=1");
        return;
    }

    private void changeEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newEmail = request.getParameter("newEmail");

        // Get the user object from the session
        User user = (User) request.getSession().getAttribute("user");
        user.setEmail(newEmail);
        userService.updateUser(user);
        // Send confirmation email
        String subject = "Email Update Confirmation";
        String message = "Dear" + user.getName() + "Your email has been successfully updated to " + newEmail + ".";
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
