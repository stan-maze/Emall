package com.eshop.servlet;


import com.eshop.model.entity.CartItem;
import com.eshop.model.entity.MyOrder;
import com.eshop.model.entity.User;
import com.eshop.service.CartService;
import com.eshop.service.OrderService;
import com.eshop.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CheckoutServlet/*")
public class CheckoutServlet extends HttpServlet {

    private OrderService orderService;
    private CartService cartService;

    public CheckoutServlet() {
        orderService = new OrderService();
        cartService = new CartService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        Double total = (Double) request.getSession().getAttribute("total");
        String address = request.getParameter("address");

        List<CartItem> cartItems = (List<CartItem>) request.getSession().getAttribute("items");
        MyOrder order = new MyOrder();
        int oid = orderService.getnewOrderId();
        order.setId(oid);
        order.setUser(user);
        order.setItems(cartItems);
        order.setTotal(total);
        orderService.insertOrder(order);
//        需要把oid更新回原来的item里, 因为订单没创建时是没有oid的
        for (CartItem item:
             cartItems) {
            item.setOrderId(oid);
            cartService.updateCartItem(item);
        }
        String email = user.getEmail();
        String subject = "订单确认";
        String message = "尊敬的 " + user.getName() +"\n\t您在eMall的订单确认成功, 总金额为: " + total + ". 收货地址为 " + address + "\n\t商品详情:\n";
        for (CartItem item: cartItems) {
            message += item.tomailString() + "\t";
        }
        message += "\n\t感谢您的惠顾!";
        try {
            EmailUtil.sendEmail(email, subject, message);
        } catch (Exception e) {
            System.out.println("发送邮件超时");
        }
        request.getSession().removeAttribute("items");

        response.sendRedirect(request.getContextPath() +"/pages/purchase_success.jsp");
        return;
    }
}
