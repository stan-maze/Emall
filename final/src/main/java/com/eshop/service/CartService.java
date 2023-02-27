package com.eshop.service;

import com.eshop.model.DAO.CartItemDAO;
import com.eshop.model.DAO.impl.CartItemDAOImpl;
import com.eshop.model.entity.CartItem;
import com.eshop.model.entity.Product;

import java.util.List;

public class CartService {

    private CartItemDAO cartItemDAO;
    private ProductService productService;

    public CartService() {
        cartItemDAO = new CartItemDAOImpl();
        productService = new ProductService();
    }

    public List<CartItem> getCartItemsByOrderId(int orderId) {
        return cartItemDAO.getCartItemsByOrderId(orderId);
    }

    public List<CartItem> getCartItemsByUserId(int userId) {
        return cartItemDAO.getCartItemsByUserId(userId);
    }
    public List<CartItem> getCartItemsByProductId(int productId) {
        return cartItemDAO.getCartItemsByProductId(productId);
    }

    public List<CartItem> getUncompletedCartItemsByUserId(int userId) {
        return cartItemDAO.getUncompletedCartItemsByUserId(userId);
    }

    public int insertCartItem(CartItem cartItem) {
        int id = getnewCartItemId();
        cartItem.setId(id);
        Product product = cartItem.getProduct();
//        Product product = productService.getProductById(cartItem.getProduct().getId());
        if (product == null) {
            throw new RuntimeException("Product not found.");
        }
        cartItem.setTotal(product.getPrice() * cartItem.getQuantity());
        cartItemDAO.insertCartItem(cartItem);
        return id;
    }

    public void updateCartItem(CartItem cartItem) {
        Product product = productService.getProductById(cartItem.getProduct().getId());
        if (product == null) {
            throw new RuntimeException("Product not found.");
        }
        cartItem.setTotal(product.getPrice() * cartItem.getQuantity());
        cartItemDAO.updateCartItem(cartItem);
    }

    public void deleteCartItem(int id) {
        cartItemDAO.deleteCartItem(id);
    }

    public void deleteCartItemsByOrderId(int orderId) {
        cartItemDAO.deleteCartItemsByOrderId(orderId);
    }
    public void deleteCartItemsByUserId(int userId) {
        cartItemDAO.deleteCartItemsByUserId(userId);
    }

    public CartItem getCartItemById(int id) {
        return cartItemDAO.getCartItemById(id);
    }

    public int getnewCartItemId(){
        return cartItemDAO.getnewCartItemId();
    }

}
