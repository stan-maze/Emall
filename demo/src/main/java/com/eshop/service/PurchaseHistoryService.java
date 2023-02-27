package com.eshop.service;

import com.eshop.model.DAO.OrderDAO;
import com.eshop.model.DAO.impl.OrderDAOImpl;
import com.eshop.model.entity.MyOrder;

import java.util.List;

public class PurchaseHistoryService {
//    从功能上来讲是冗余的, 其他service可以配合完成功能

    private OrderDAO orderDAO;

    public PurchaseHistoryService() {
        orderDAO = new OrderDAOImpl();
    }

    public List<MyOrder> getOrdersByUserId(int userId) {
        return orderDAO.getOrdersByUserId(userId);
    }

}