package com.eshop.model.DAO;

import com.eshop.model.entity.Product;

import java.util.List;

public interface ProductDAO {
    Product getProductById(int id);
    List<Product> getAllProducts();
    void insertProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int id);
    int getnewProductId();
}
