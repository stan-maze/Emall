package com.eshop.model.entity;

public class CartItem {
    private int id;
    private Product product;
    private User user;
    private int orderId;
    private int quantity;
    private double total;

    public CartItem(int id, Product product, User user, int quantity, double total, int orderid) {
        this.id = id;
        this.product = product;
        this.user = user;
        this.quantity = quantity;
        this.total = total;
        this.orderId = orderid;
    }

    public CartItem() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", product=" + product +
                ", orderid=" + orderId +
                ", quantity=" + quantity +
                ", total=" + total +
                '}';
    }
    public String tomailString() {
        return "CartItem{" +
                ", product=" + product.getName() +
                ", orderid=" + orderId +
                ", quantity=" + quantity +
                ", total=" + total +
                '}';
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderid) {
        this.orderId = orderid;
    }
}