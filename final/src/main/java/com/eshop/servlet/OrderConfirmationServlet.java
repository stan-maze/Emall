package com.eshop.servlet;

import com.eshop.model.entity.MyOrder;
import com.eshop.model.entity.User;
import com.eshop.service.OrderService;
import com.eshop.service.UserService;
import com.eshop.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderConfirmationServlet extends HttpServlet {

    private UserService userService;
    private OrderService orderService;

    public OrderConfirmationServlet() {
        userService = new UserService();
        orderService = new OrderService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the user object from the session
        User user = (User) request.getSession().getAttribute("user");

        // Get the order ID from the request parameter
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        // Get the order object from the OrderService
        MyOrder order = orderService.getOrderById(orderId);

        // Send an email to the user to confirm their purchase
        String subject = "Order Confirmation";
        String message = "Thank you for your purchase. Your order has been placed and will be shipped shortly.\n\nOrder Details:\n" + order.toString();
        String recipient = user.getEmail();

        try {
            EmailUtil.sendEmail(recipient, subject, message);
            request.setAttribute("message", "An email has been sent to " + recipient + " to confirm your order.");
        } catch (Exception e) {
            request.setAttribute("message", "An error occurred while sending the email: " + e.getMessage());
        }

        // Forward to the order details page
        request.getRequestDispatcher("/orderDetails.jsp").forward(request, response);
    }
}
