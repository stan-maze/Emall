package com.eshop.model.DAO;

import com.eshop.model.entity.MyOrder;

import java.util.List;

public interface OrderDAO {
    MyOrder getOrderById(int id);
    List<MyOrder> getOrdersByUserId(int userId);
    void insertOrder(MyOrder order);
    void updateOrder(MyOrder order);
    void deleteOrder(int id);
    int getnewOrderId();
}
