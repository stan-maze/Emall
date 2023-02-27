package com.eshop.model.DAO;
import com.eshop.model.entity.CartItem;

import java.util.List;

public interface CartItemDAO {
    CartItem getCartItemById(int id);
    List<CartItem> getCartItemsByOrderId(int orderId);

    List<CartItem> getCartItemsByUserId(int userId);
    List<CartItem> getUncompletedCartItemsByUserId(int userId);
    List<CartItem> getCartItemsByProductId(int productId);

    void insertCartItem(CartItem cartItem);
    void updateCartItem(CartItem cartItem);
    void deleteCartItem(int id);
    void deleteCartItemsByOrderId(int orderId);
    int getnewCartItemId();

    void deleteCartItemsByUserId(int userId);

}

