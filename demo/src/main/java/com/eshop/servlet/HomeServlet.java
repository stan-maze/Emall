package com.eshop.servlet;

import com.eshop.model.entity.Product;
import com.eshop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/HomeServlet/*")
public class HomeServlet extends HttpServlet {

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getAttribute("fromHomeServlet") == null) {
            // redirect to HomeServlet
            List<Product> products = productService.getAllProducts();
            request.setAttribute("products", products);
            request.setAttribute("fromHomeServlet", true);
            request.getRequestDispatcher("/pages/home.jsp").forward(request, response);
        } else {
            // 防止循环
        }
    }


}
