package com.eshop.servlet;


import com.eshop.model.entity.CartItem;
import com.eshop.model.entity.MyOrder;
import com.eshop.model.entity.Product;
import com.eshop.model.entity.User;
import com.eshop.service.CartService;
import com.eshop.service.OrderService;
import com.eshop.service.ProductService;
import com.eshop.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/ProductServlet/*")
public class ProductServlet extends HttpServlet {

    ProductService ProductService;
    HttpSession session;
    public ProductServlet() {
        ProductService = new ProductService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        if(session.getAttribute("mlogin") == null){
            return;
        }
        String action = request.getPathInfo();
        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }
        switch (action) {
            case "/update":
                update(request, response);
                break;
            case "/add":
                add(request, response);
                break;
            case "/delete":
                delete(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/error.jsp");
        }

    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        String imageUrl = request.getParameter("imageUrl");
        ProductService.updateProduct(new Product(id, name, description, price, imageUrl));

        response.sendRedirect(request.getContextPath() + "/pages/mproduct.jsp");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProductService.deleteProduct(id);

        response.sendRedirect(request.getContextPath() + "/pages/mproduct.jsp");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = new Product();
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        String imageUrl = request.getParameter("imageUrl");
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);
        ProductService.insertProduct(product);

        response.sendRedirect(request.getContextPath() + "/pages/mproduct.jsp");
    }
}
