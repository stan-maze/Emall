package com.eshop.servlet;

import com.eshop.model.entity.CartItem;
import com.eshop.model.entity.User;
import com.eshop.service.CartService;
import com.eshop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/CartServlet/*")
public class CartServlet extends HttpServlet {

    private CartService cartService;

    public CartServlet() {
        cartService = new CartService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Double total = (Double) request.getSession().getAttribute("total");
        if (user == null){
            request.setAttribute("message", "Have not login yet");
            response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
            return;
        }
        if (total == null) {
            total = 0.0;
        }
        String path = request.getPathInfo();
        if (path.equals("/add")) {
            // Default behavior: add item to cart
            int productId = Integer.parseInt(request.getParameter("product_id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            try {
                CartItem cartItem = new CartItem();
                cartItem.setUser(user);
                cartItem.setProduct(new ProductService().getProductById(productId));
                cartItem.setQuantity(quantity);
                cartItem.setTotal(quantity*cartItem.getProduct().getPrice());
                cartService.insertCartItem(cartItem);
                List<CartItem> items = (List<CartItem>) request.getSession().getAttribute("items");
                if (items == null) {
                    items = new ArrayList<>();
                    request.getSession().setAttribute("items", items);
                }
                total += cartItem.getTotal();
                items.add(cartItem);
                request.getSession().setAttribute("total", total);
                response.sendRedirect(request.getContextPath() + "/pages/home.jsp");
            } catch (Exception e) {
                request.setAttribute("message", "Failed to add product to cart: " + e.getMessage());
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } else if (path.equals("/delete")) {
            // Remove item from cart
            int id = Integer.parseInt(request.getParameter("cart_id"));
            List<CartItem> items = (List<CartItem>) request.getSession().getAttribute("items");
            if (items != null) {
                cartService.deleteCartItem(id);
//                cart_id是唯一的, 只有一项, 不会出错
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getId() == id) {
                        total -= items.get(i).getTotal();
                        request.getSession().setAttribute("total", total);
                        items.remove(i);
                        break;
                    }
                }
                response.sendRedirect(request.getContextPath() + "/pages/cart.jsp");
            }
        }
    }
}
