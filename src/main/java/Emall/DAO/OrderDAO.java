package Emall.DAO;

import Emall.entity.Order;

import java.util.List;

public interface OrderDAO {
    public int createOrder(Order order);
    public Order getOrderById(int orderId);
    public List<Order> getAllOrders();
    public List<Order> getOrdersByCustomerId(int customerId);
    public int updateOrder(Order order);
    public void deleteOrder(int orderId);
}
