package com.eshop.service;

import com.eshop.model.DAO.ProductDAO;
import com.eshop.model.DAO.impl.ProductDAOImpl;
import com.eshop.model.entity.CartItem;
import com.eshop.model.entity.Product;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductService {

    private ProductDAO productDAO;

    public ProductService() {
        productDAO = new ProductDAOImpl();
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }

    public int insertProduct(Product product) {
        int id = getnewProductId();
        product.setId(id);
        productDAO.insertProduct(product);
        return id;
    }

    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }

    public int getnewProductId(){
        return productDAO.getnewProductId();
    }

    public double getProductTotalsById(int id) {
        List<CartItem> items = new CartService().getCartItemsByProductId(id);
        double total = 0;
        for (CartItem item:items) {
            total += item.getTotal();
        }
        return total;
    }

    public int getProductSalesById(int id) {
        List<CartItem> items = new CartService().getCartItemsByProductId(id);
        int sales = 0;
        for (CartItem item:items) {
            sales += item.getQuantity();
        }
        return sales;
    }
    public int getProductUserById(int id) {
        List<CartItem> items = new CartService().getCartItemsByProductId(id);
        Set<Integer> uniqueUids = new HashSet<>(); // 用于记录不同的 uid 值
        for (CartItem item : items) {
            uniqueUids.add(item.getUser().getId()); // 将 uid 添加到 Set 集合中
        }
        return uniqueUids.size();
    }
}