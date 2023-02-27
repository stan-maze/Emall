package com.eshop.service;

import com.eshop.model.DAO.CartItemDAO;
import com.eshop.model.DAO.OrderDAO;
import com.eshop.model.DAO.impl.CartItemDAOImpl;
import com.eshop.model.DAO.impl.OrderDAOImpl;
import com.eshop.model.entity.CartItem;
import com.eshop.model.entity.MyOrder;

import java.util.Date;
import java.util.List;

public class OrderService {

    private OrderDAO orderDAO;
    private CartItemDAO cartItemDAO;
    private UserService userService;

    public OrderService() {
        orderDAO = new OrderDAOImpl();
        cartItemDAO = new CartItemDAOImpl();
        userService = new UserService();
    }

    public MyOrder getOrderById(int id) {
        return orderDAO.getOrderById(id);
    }

    public List<MyOrder> getOrdersByUserId(int userId) {
        return orderDAO.getOrdersByUserId(userId);
    }

    public int insertOrder(MyOrder order) {
        int id = getnewOrderId();
        order.setId(id);
        order.setCreatedDate(new Date());
//        order.setItems(order.getItems());
//        System.out.println(order);
        orderDAO.insertOrder(order);
//        List<CartItem> cartItems = cartItemDAO.getCartItemsByOrderId(order.getId());
//        for (CartItem cartItem : cartItems) {
//            cartItem.setOrder(order);
//            cartItemDAO.updateCartItem(cartItem);
//        }
        return id;
    }

    public void updateOrder(MyOrder order) {
        orderDAO.updateOrder(order);
    }

    public void deleteOrder(int id) {
        orderDAO.deleteOrder(id);
//        数据库建表时设计为 cascade, 可以不需要这一步
        cartItemDAO.deleteCartItemsByOrderId(id);
    }

    public int getnewOrderId(){
        return orderDAO.getnewOrderId();
    }

}